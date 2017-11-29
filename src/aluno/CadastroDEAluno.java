package aluno;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import treinos.JPanelWithBackground;

public class CadastroDEAluno extends ClasseParaCriarJanela {

	private Font fonte = new Font("Calibri", Font.PLAIN, 17);
	public JTextField TFdata;
	public JTextField TFnome;
	public JFormattedTextField TFcpf;
	public JFormattedTextField TFidentidade;
	public JTextField TFidade;
	public JTextField TFemail;
	public JTextField TFendereco;
	public JFormattedTextField TFtelefone;
	private JComboBox<String> combo;

	public CadastroDEAluno() {
		super("Cadastro de Aluno", 580, 380);
		this.adicionarLabel();
		this.adicionarJTextField(this);
		this.adicionarButon();
		this.getContentPane().setBackground(Color.WHITE);
		this.repaint();
	}

	// metodo para adicionar os botoes cadastrar, limpar voltar
	private void adicionarButon() {

		// bot�o cadastrar e seu ouvinte
		// ImageIcon img = new ImageIcon(getClass().getResource("icones/ok.png"));
		JButton botaoCadastrar = new JButton("Cadastrar");

		botaoCadastrar.setPreferredSize(new Dimension(20, 20));
		botaoCadastrar.setBounds(300, 310, 150, 35);
		botaoCadastrar.setFont(fonte);
		add(botaoCadastrar);

		OuvinteDoBotaoCadastrar ouvinteCadastrar = new OuvinteDoBotaoCadastrar(this);
		botaoCadastrar.addActionListener(ouvinteCadastrar);

		// limpar e seu ouvinte
		JButton botaoLimpar = new JButton("Limpar");
		botaoLimpar.setBounds(170, 310, 120, 35);
		botaoLimpar.setFont(fonte);

		add(botaoLimpar);

		OuvinteDoBotaoLimparDoAluno ouvinteLimpar = new OuvinteDoBotaoLimparDoAluno(this);
		botaoLimpar.addActionListener(ouvinteLimpar);

		// voltar
		JButton botaoVoltar = new JButton("Cancelar");
		botaoVoltar.setBounds(40, 310, 110, 35);
		botaoVoltar.setFont(fonte);
		botaoVoltar.addActionListener(new OuvinteDoBTVoltarCadastroAluno(this));
		add(botaoVoltar);
	}

	private void adicionarJTextField(JDialog janela) {

		Date data = new Date(System.currentTimeMillis());
		SimpleDateFormat formatarDate = new SimpleDateFormat("dd/MM/yyyy");

		String d = formatarDate.format(data);

		TFdata = new JTextField(d);
		TFdata.setBounds(220, 65, 100, 23);
		janela.add(TFdata);

		TFnome = new JTextField();
		TFnome.setBounds(220, 90, 230, 23);
		janela.add(TFnome);

		TFidade = new JTextField();
		TFidade.setBounds(410, 115, 40, 23);
		janela.add(TFidade);

		try {

			// mascara do RG
			MaskFormatter mascaraDeRG = new MaskFormatter("#.###.###");

			TFidentidade = new JFormattedTextField(mascaraDeRG);
			TFidentidade.setBounds(220, 165, 230, 23);
			janela.add(TFidentidade);

			// configurar uma m�scara para CPF
			MaskFormatter mascaraDeCpf = new MaskFormatter("###.###.###-##");

			TFcpf = new JFormattedTextField(mascaraDeCpf);
			TFcpf.setBounds(220, 140, 230, 23);
			janela.add(TFcpf);

			// mascara para telefone
			MaskFormatter mascaraDeTelefone = new MaskFormatter("(##)#####-####");

			TFtelefone = new JFormattedTextField(mascaraDeTelefone);
			TFtelefone.setBounds(220, 190, 230, 23);
			janela.add(TFtelefone);

		} catch (ParseException e) {
		}

		TFemail = new JTextField();
		TFemail.setBounds(220, 215, 230, 23);
		janela.add(TFemail);

		TFendereco = new JTextField();
		TFendereco.setBounds(220, 240, 230, 23);
		janela.add(TFendereco);
	}

	private void adicionarLabel() {
		// ImageIcon imagem = new
		// ImageIcon(getClass().getResource("icones/adduser_4961.jpg"));
		JLabel imageIcon = new JLabel();
		imageIcon.setBounds(480, 0, 90, 90);
		add(imageIcon);

		JLabel titulo = new JLabel("Identificação");
		titulo.setFont(new Font("Cabril", Font.BOLD, 20));
		titulo.setForeground(Color.black);
		titulo.setBounds(40, 05, 200, 60);
		add(titulo);

		this.criarLabol("Data:", 130, 60, 90, 30);
		this.criarLabol("Nome:", 130, 85, 90, 30);
		this.criarLabol("Sexo:", 130, 110, 90, 30);
		this.criarLabol("Idade:", 350, 110, 90, 30);
		this.criarLabol("CPF:", 130, 135, 90, 30);
		this.criarLabol("RG:", 132, 160, 90, 30);
		this.criarLabol("Telefone:", 130, 185, 90, 30);
		this.criarLabol("Email:", 130, 210, 90, 30);
		this.criarLabol("Endereço:", 130, 235, 90, 30);
//		this.criarLabol("E-Treinador", 480, 310, 100, 30);

		// adiciona o combobox
		String[] sexoTipo = { "Outros", "Masculino", "Feminino" };
		combo = new JComboBox<>(sexoTipo);
		combo.setBounds(220, 115, 90, 23);
		add(combo);
		// sexo = (String) combo.getSelectedItem();
	}

	public JTextField getTFnome() {
		return TFnome;
	}

	public void setTFnome(JTextField tFnome) {
		TFnome = tFnome;
	}

	public JTextField getTFcpf() {
		return TFcpf;
	}

	public void setTFcpf(JFormattedTextField tFcpf) {
		TFcpf = tFcpf;
	}

	public JTextField getTFidentidade() {
		return TFidentidade;
	}

	public void setTFidentidade(JFormattedTextField tFidentidade) {
		TFidentidade = tFidentidade;
	}

	public JTextField getTFidade() {
		return TFidade;
	}

	public void setTFidade(JTextField tFidade) {
		TFidade = tFidade;
	}

	public JTextField getTFemail() {
		return TFemail;
	}

	public void setTFemail(JTextField tFemail) {
		TFemail = tFemail;
	}

	public JTextField getTFendereco() {
		return TFendereco;
	}

	public void setTFendereco(JTextField tFendereco) {
		TFendereco = tFendereco;
	}

	public JTextField getTFtelefone() {
		return TFtelefone;
	}

	public void setTFtelefone(JFormattedTextField tFtelefone) {
		TFtelefone = tFtelefone;
	}

	public JTextField getTFdata() {
		return TFdata;
	}

	public void setTFdata(JFormattedTextField tFdata) {
		TFdata = tFdata;
	}

	public JComboBox<String> getCombo() {
		return combo;
	}

	public void setCombo(JComboBox<String> combo) {
		this.combo = combo;
	}
}