package aluno;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import treinos.Academia;
import treinos.Aluno;
import treinos.Persistencia;

public class JanelaVisualizarCredenciais extends ClasseParaCriarJanela implements ActionListener{

	private int idice;
	private Font fonte = new Font("Calibri", Font.PLAIN, 17);
	
	public JanelaVisualizarCredenciais(int selectedRow) {
		
		super("Credenciais do Aluno", 580, 380);
		this.idice = selectedRow;
		adicionarLabel();
		adicionarCredenciais();
		adicionarButon();
		this.getContentPane().setBackground(Color.WHITE);
		this.repaint();
	}
	
	private void adicionarButon() {
		JButton botaoVoltar = new JButton("Concluir");
		botaoVoltar.setBounds(450, 310, 110, 35);
		botaoVoltar.setFont(fonte);
		botaoVoltar.addActionListener(this);
		add(botaoVoltar);
	}
	
	private void adicionarCredenciais() {
		
		Persistencia persistencia = new Persistencia();
		//recupera a academia
		Academia academia = persistencia.recuperar("academia.xml");
		ArrayList<Aluno> alunos = academia.getAlunos();
		
		Aluno al = alunos.get(idice);

		String data = al.getDataDeCadastro();
		
		String nome = al.getNome();
		int idade = al.getIdade();
		String id = Integer.toString(idade);
		String cpf = al.getCPF();
		String rg = al.getIdentidade();
		String fone = al.getTelefone();
		String email = al.getEmail();
		String ende = al.getEndereco();
		String sexo = al.getSexo();
		
		this.criarLabol(data, 220, 65, 100, 23);
		this.criarLabol(nome, 220, 90, 230, 23);
		this.criarLabol(id, 410, 115, 40, 23);
		this.criarLabol(cpf, 220, 140, 230, 23);
		this.criarLabol(rg, 220, 165, 230, 23);
		this.criarLabol(fone,220, 190, 230, 23);
		this.criarLabol(email,220, 215, 230, 23);
		this.criarLabol(ende,220, 240, 230, 23);
		this.criarLabol(sexo,220, 115, 90, 23);
		
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

	
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.dispose();
		
	}
	
	

}
