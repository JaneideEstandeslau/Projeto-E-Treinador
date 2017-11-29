package aluno;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import treinos.Academia;
import treinos.Aluno;
import treinos.Persistencia;
import treinos.Treino;

public class JanelaVisualizarTreino extends ClasseParaCriarJanela implements ActionListener{

	private int idice;
	private Font fonte = new Font("Calibri", Font.PLAIN, 17);

	public JanelaVisualizarTreino(int selectedRow) {
		super("Treinos do Aluno", 580, 380);
		this.idice = selectedRow;
		adicionarButon();
		adicionarCombobox();
		this.getContentPane().setBackground(Color.WHITE);
		this.repaint();
		// TODO Auto-generated constructor stub
	}
	
	
	public void adicionarCombobox(){
		
		JLabel titulo = new JLabel("Treinos da Semana"); 
		titulo.setFont(new Font("Cabril",Font.BOLD, 17));
		titulo.setForeground(Color.black);
		titulo.setBounds(20, 10, 300, 60);
		add(titulo);
		
		Persistencia persistencia = new Persistencia();
		//recupera a academia
		Academia academia = persistencia.recuperar("academia.xml");
		ArrayList<Aluno> alunos = academia.getAlunos();
		
		Aluno al = alunos.get(idice);
		
		Treino[] treinos = al.getTreinosDoAluno();

		int i = 1;
		this.criarLabol("Segunda:", 80, 80, 90, 30);
		this.criarLabol(treinos[i++].getNomeDoTreino(), 80, 105, 130, 25);
		
		//adiciona um label e um combobox para inserir o treino da ter�a
		this.criarLabol("Terça:", 340, 80, 90, 30);
		this.criarLabol(treinos[i++].getNomeDoTreino(), 340, 105, 130, 25);
		
		
		//adiciona um label e um combobox para inserir o treino da quarta
		this.criarLabol("Quarta:", 80, 140, 90, 30);
		this.criarLabol(treinos[i++].getNomeDoTreino(), 80, 165, 130, 25);
		
		
		//adiciona um label e um combobox para inserir o treino da quinta
		this.criarLabol("Quinta:", 340, 140, 90, 30);
		this.criarLabol(treinos[i++].getNomeDoTreino(), 340, 165, 130, 25);
		
		
		//adiciona um label e um combobox para inserir o treino da sexta
		this.criarLabol("Sexta:", 80, 200, 90, 30);
		this.criarLabol(treinos[i++].getNomeDoTreino(), 80, 225, 130, 25);
		
		
		//adiciona um label e um combobox para inserir o treino da sexta
		this.criarLabol("Sábado:", 340, 200, 90, 30);
		this.criarLabol(treinos[i++].getNomeDoTreino(), 340, 225, 130, 25);
		
		
	}
	
	private void adicionarButon() {
		JButton botaoVoltar = new JButton("Concluir");
		botaoVoltar.setBounds(450, 310, 110, 35);
		botaoVoltar.setFont(fonte);
		botaoVoltar.addActionListener(this);
		add(botaoVoltar);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.dispose();
		
	}

}
