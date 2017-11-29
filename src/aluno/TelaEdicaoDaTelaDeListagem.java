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

public class TelaEdicaoDaTelaDeListagem extends ClasseParaCriarJanela implements ActionListener{
	
	private JComboBox[] arrayComOsCombo = new JComboBox[7];
	private JanelaTelaListagem janela;
	Persistencia persistencia  = new Persistencia();
	Academia academia;
	
	public TelaEdicaoDaTelaDeListagem(JanelaTelaListagem janela){
		super("Editar Treinos", 580, 380);
		this.janela = janela;
		this.adicionarButton();
		this.adicionarLabel();
		
	}
	
	public void adicionarButton(){
		
		//bot�o cadastrar e seu ouvinte
//		ImageIcon img = new ImageIcon("cadastro_converted.jpg");
		JButton botaoCadastrar = new JButton("Salvar");
		botaoCadastrar.setBounds(340, 310, 120, 30);
		add(botaoCadastrar);
		
		botaoCadastrar.addActionListener(this);
				
	}
	
	public void adicionarLabel(){
		int i = 1;
		JLabel titulo = new JLabel("Editar Treinos da Semana"); 
		titulo.setFont(new Font("Cabril",Font.BOLD, 20));
		titulo.setForeground(Color.black);
		titulo.setBounds(20, 10, 300, 60);
		add(titulo);
		
		CriarCombo criar = new CriarCombo();
		int indice = janela.getTabela().getSelectedRow();
		this.setArrayComOsCombo(criar.adicionarCombo(this, indice));
	}

	public JComboBox[] getArrayComOsCombo() {
		return arrayComOsCombo;
	}

	public void setArrayComOsCombo(JComboBox[] arrayComOsCombo) {
		this.arrayComOsCombo = arrayComOsCombo;
	}

	public JanelaTelaListagem getJanela(){
		return janela;
	}

	public void setJanela(JanelaTelaListagem janela) {
		this.janela = janela;
	}

	public void actionPerformed(ActionEvent e) {
		
		academia = persistencia.recuperar("academia.xml");
		
		ArrayList<Aluno> alunos = academia.getAlunos();
		
		int select = janela.getTabela().getSelectedRow();
		Aluno aluno = alunos.get(select);
		
		Treino[] treinos = new Treino[7];
		Treino[] teinosJaCadastrados = aluno.getTreinosDoAluno();
		
		int i = 0;
		for(JComboBox combo: arrayComOsCombo){
			if(combo != null){
				treinos[i] = (Treino) combo.getSelectedItem();
			}
			else{
				treinos[i] = teinosJaCadastrados[i];
			}
			i++;
			System.out.println(teinosJaCadastrados[1]);
		}
		
		//seta os treinos do aluno
		aluno.setTreinosDoAluno(treinos);
		
		//recupera o xml
		
		//salva a academia com o novo aluno
		persistencia.salvar(academia, "academia.xml");
		
		JOptionPane.showMessageDialog(this, "Treino editado");
		
		//volta pata a tele inicial
		new  ListarTreinos(select);
		
		this.dispose();
	}
	
}
