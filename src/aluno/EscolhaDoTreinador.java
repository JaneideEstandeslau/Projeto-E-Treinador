package aluno;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class EscolhaDoTreinador extends ClasseParaCriarJanela implements ActionListener{

	private JanelaTelaListagem janela;
	
	public EscolhaDoTreinador(JanelaTelaListagem janela){
		super("Editar", 400, 200);
		this.janela = janela;
		this.adicionarButton();
		this.adicionarlabel();
		this.repaint();
	}
	
	public void adicionarlabel(){
		
		JLabel img = new JLabel(new ImageIcon("src/icones/interrogacao.png"));
		img.setBounds(20, 25, 50, 50);
		add(img);
		
		JLabel titulo = new JLabel("O que deseja editar"); 
		titulo.setFont(new Font("Cabril",Font.BOLD, 20));
		titulo.setForeground(Color.black);
		titulo.setBounds(110, 20, 300, 60);
		add(titulo);
	}
	
	public  void adicionarButton(){
		
		//botao para a escolha de edi��o dos treinos da semana do aluno
		JButton editarTreino = new JButton("Treino");
		editarTreino.setBounds(50, 110, 100, 30);
		add(editarTreino);
		
		editarTreino.addActionListener(this);
		
		//botao escolha editar informa��es pessoais
		JButton editarAluno = new JButton("Credenciais");
		editarAluno.setBounds(230, 110, 110, 30);
		add(editarAluno);
		
		editarAluno.addActionListener(new OuvinteBotaoEditarInformacoesPessoaisDoAluno(this));
		
	}
	
	public void actionPerformed(ActionEvent e) {
		new TelaEdicaoTreinoDoAluno(janela.getTabela().getSelectedRow());
		janela.dispose();
		this.dispose();
	}

	public JanelaTelaListagem getJanela() {
		return janela;
	}

	public void setJanela(JanelaTelaListagem janela) {
		this.janela = janela;
	}
	
}
