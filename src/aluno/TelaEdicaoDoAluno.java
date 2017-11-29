package aluno;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import treinos.Academia;
import treinos.Aluno;
import treinos.Persistencia;

public class TelaEdicaoDoAluno extends ClasseParaCriarJanela implements ActionListener{
 
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
	private int select;
	private Aluno aluno;
	
	public TelaEdicaoDoAluno(int indice){
		super("Edição de Aluno", 580, 380);	
		
		this.select = indice;
		
		Persistencia persistencia = new Persistencia();
		Academia academia;
		
		academia = persistencia.recuperar("academia.xml");
		ArrayList<Aluno> alunos = academia.getAlunos();
			
		aluno = alunos.get(select);
	
		this.getContentPane().setBackground(Color.WHITE);
		this.adicionarLabel();
		this.adicionarButton();
		this.adicinarTextField();
		this.repaint();
	}
	
	public void adicinarTextField(){
		
		TFdata = new JTextField(aluno.getDataDeCadastro());
		TFdata.setBounds(180, 65, 80, 20);
		add(TFdata);
	
		TFnome = new JTextField(aluno.getNome());
		TFnome.setBounds(180, 90, 230, 20);
		add(TFnome);
		
		TFidade = new JTextField(aluno.getIdade()+"");
		TFidade.setBounds(370, 115, 40, 20);
		add(TFidade);
		
		
		try{
			
			//mascara do RG
			MaskFormatter mascaraDeRG = new MaskFormatter("#.###.###");
			
			TFidentidade = new JFormattedTextField(mascaraDeRG);
			
			TFidentidade.setBounds(180, 165, 230, 20);
			TFidentidade.setText(aluno.getIdentidade());
			add(TFidentidade);
			
			//configurar uma m�scara para CPF
			MaskFormatter mascaraDeCpf = new MaskFormatter("###.###.###-##");
			
			TFcpf = new JFormattedTextField(mascaraDeCpf);
			TFcpf.setBounds(180, 140, 230, 20);
			TFcpf.setText(aluno.getCPF());
			add(TFcpf);
			
			//mascara para telefone
			MaskFormatter mascaraDeTelefone = new MaskFormatter("(##) # ####-####");
			
			TFtelefone = new JFormattedTextField(mascaraDeTelefone);
			
			TFtelefone.setBounds(190, 190, 220, 20);
			TFtelefone.setText(aluno.getTelefone());
			add(TFtelefone);
			
		}catch(ParseException e) {}
		
		TFemail = new JTextField(aluno.getEmail());
		TFemail.setBounds(180, 215, 230, 20);
		add(TFemail);
		
		TFendereco = new JTextField(aluno.getEndereco());
		TFendereco.setBounds(190, 240, 220, 20);
		add(TFendereco);
		
	}
	
	public void adicionarLabel(){
		
		JLabel img = new JLabel(new ImageIcon("src/icones/editar.jpg"));
		img.setBounds(480, 0, 90, 90);
//		add(img);

		JLabel titulo = new JLabel("Editar Aluno");
		titulo.setFont(new Font("Cabril",Font.BOLD, 17));
		titulo.setForeground(Color.black);
		titulo.setBounds(40, 05, 200, 60);
		add(titulo);
		
		
		this.criarLabol("E-Treinador", 480, 310, 100, 30);
		this.criarLabol("Data:", 130, 60, 90, 30);
		this.criarLabol("Nome:", 130, 85, 90, 30);
		this.criarLabol("Sexo:", 130, 110, 90, 30);
		this.criarLabol("Idade:", 330, 110, 90, 30);
		this.criarLabol("CPF:", 130, 135, 90, 30);
		this.criarLabol("RG:", 132, 160, 90, 30);
		this.criarLabol("Telefone:", 130, 185, 90, 30);
		this.criarLabol("Email:", 130, 210, 90, 30);
		this.criarLabol("Endereço:", 130, 235, 90, 30);
		this.criarLabol("E-Treinador", 480, 310, 100, 30);
		
		//adiciona o combobox
		String[] sexoTipo = {"Outos", "Masculino", "Feminino"};
		combo = new JComboBox<>(sexoTipo);
		combo.setBounds(180, 115, 90, 17);
		combo.setSelectedItem(aluno.getSexo());
		add(combo);
	}
	
	public void adicionarButton(){
		
		//Bot�o edi��o concluida e seu ouvinte
		JButton botaoEditar = new JButton("Salvar");
		botaoEditar.setFont(fonte);
		botaoEditar.setBounds(350, 310, 120, 30);
		add(botaoEditar);
		
		botaoEditar.addActionListener(new OuvinteCrecenciaisAluno(this));
		
		JButton cancelar = new JButton("Cancelar");
		cancelar.setFont(fonte);
		cancelar.setBounds(220, 310, 120, 30);
		add(cancelar);
		
		cancelar.addActionListener(this);
	}
	
	public JTextField getTFdata() {
		return TFdata;
	}

	public void setTFdata(JTextField tFdata) {
		TFdata = tFdata;
	}

	public JTextField getTFnome() {
		return TFnome;
	}

	public void setTFnome(JTextField tFnome) {
		TFnome = tFnome;
	}

	public JFormattedTextField getTFcpf() {
		return TFcpf;
	}

	public void setTFcpf(JFormattedTextField tFcpf) {
		TFcpf = tFcpf;
	}

	public JFormattedTextField getTFidentidade() {
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

	public JFormattedTextField getTFtelefone() {
		return TFtelefone;
	}

	public void setTFtelefone(JFormattedTextField tFtelefone) {
		TFtelefone = tFtelefone;
	}

	public JComboBox<String> getCombo() {
		return combo;
	}

	public void setCombo(JComboBox<String> combo) {
		this.combo = combo;
	}

	public int getSelect() {
		return select;
	}

	public void setSelect(int select) {
		this.select = select;
	}

	public void actionPerformed(ActionEvent arg0) {
		this.dispose();
	}
}
