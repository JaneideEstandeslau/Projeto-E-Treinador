package aluno;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import treinos.Academia;
import treinos.Aluno;
import treinos.Persistencia;
import treinos.Treino;

public class TelaEdicaoTreinoDoAluno extends ClasseParaCriarJanela implements ActionListener{

	private Font fonte = new Font("Calibri", Font.PLAIN, 17);
	private JComboBox[] arrayComOsCombo = new JComboBox[7];
	private int indiceDoAluno;
	Persistencia persistencia  = new Persistencia();
	Academia academia;
	
	public TelaEdicaoTreinoDoAluno(int indiceDoAluno){
		super("Editar Treinos", 580, 380);
		this.indiceDoAluno = indiceDoAluno;
		this.adicionarButton();
		this.adicionarLabel();
		this.adicionarCombobox();
		this.repaint();
	}
	
	public void adicionarButton(){
		
		//bot�o cadastrar e seu ouvinte
		ImageIcon img = new ImageIcon("cadastro_converted.jpg");
		JButton botaoCadastrar = new JButton("Salvar");
		botaoCadastrar.setFont(fonte);
		botaoCadastrar.setBounds(340, 310, 120, 30);
		add(botaoCadastrar);
		
		botaoCadastrar.addActionListener(this);
				
	}
	
	public void adicionarLabel(){
		
		academia = persistencia.recuperar("academia.xml");
		
		ArrayList<Aluno> alunos = academia.getAlunos();
		
		Aluno aluno = alunos.get(indiceDoAluno);
		
		
		JLabel titulo = new JLabel("Editar Treinos da Semana"); 
		titulo.setFont(new Font("Cabril",Font.BOLD, 20));
		titulo.setForeground(Color.black);
		titulo.setBounds(20, 10, 300, 60);
		add(titulo);
	}
		
	public void adicionarCombobox(){
		CriarCombo criar = new CriarCombo();
		this.setArrayComOsCombo(criar.adicionarCombo(this, indiceDoAluno));
	}

	public JComboBox[] getArrayComOsCombo() {
		return arrayComOsCombo;
	}

	public void setArrayComOsCombo(JComboBox[] arrayComOsCombo) {
		this.arrayComOsCombo = arrayComOsCombo;
	}

	public int getJanela(){
		return indiceDoAluno;
	}

	public void setJanela(int janela) {
		this.indiceDoAluno = janela;
	}

	public void actionPerformed(ActionEvent e) {
		
		academia = persistencia.recuperar("academia.xml");
		
		ArrayList<Aluno> alunos = academia.getAlunos();
		
		Aluno aluno = alunos.get(indiceDoAluno);
		
		Treino[] treinos = new Treino[7];
		
		for(int i = 1; i < arrayComOsCombo.length;i++){
			treinos[i] = (Treino) arrayComOsCombo[i].getSelectedItem();
		}
		
		//seta os treinos do aluno
		aluno.setTreinosDoAluno(treinos);
		
		//recupera o xml
		
		//salva a academia com o novo aluno
		persistencia.salvar(academia, "academia.xml");
		
		JOptionPane.showMessageDialog(this, "Treino editado");
		
		//volta pata a tele inicial
		new  ListarTreinos(indiceDoAluno);
		
		this.dispose();
	}
	
}
