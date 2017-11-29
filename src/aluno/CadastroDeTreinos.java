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


public class CadastroDeTreinos extends ClasseParaCriarJanela implements ActionListener{
	
	private Aluno aluno;
	private JComboBox[] arrayComOsCombo = new JComboBox[7];
	Persistencia persistencia  = new Persistencia();
	Academia academia;
	
	public CadastroDeTreinos(Aluno aluno){
		super("Cadastrar Treinos", 580, 380);
		this.aluno = aluno;
		this.adicionarCombobox();
		this.adicionarButton();
		this.getContentPane().setBackground(Color.WHITE);
		this.repaint();
	}

	public void adicionarButton(){
			
		//bot�o cadastrar e seu ouvinte
		ImageIcon img = new ImageIcon("cadastro_converted.jpg");
		JButton botaoCadastrar = new JButton("Salvar", img);
		botaoCadastrar.setBounds(340, 310, 120, 30);
		add(botaoCadastrar);
			
		botaoCadastrar.addActionListener(this);
			
	}
	
	public void adicionarCombobox(){
		
		JLabel titulo = new JLabel("Cadastrar Treinos da Semana"); 
		titulo.setFont(new Font("Cabril",Font.BOLD, 17));
		titulo.setForeground(Color.black);
		titulo.setBounds(20, 10, 300, 60);
		add(titulo);
		

		int i = 1;
		
		//adiciona um label e um combobox para inserir o treino da segunda
		
		this.criarLabol("Segunda:", 80, 80, 90, 30);
		JComboBox<Treino> comboseg = new JComboBox<>(criarCombobox());
		comboseg.setBounds(80, 105, 130, 25);
		add(comboseg);
		arrayComOsCombo[i++] = comboseg;
		
		//adiciona um label e um combobox para inserir o treino da ter�a
		this.criarLabol("Terça:", 340, 80, 90, 30);
		JComboBox<Treino> comboter = new JComboBox<>(criarCombobox());
		comboter.setBounds(340, 105, 130, 25);
		add(comboter);
		arrayComOsCombo[i++] = comboter;
		
		//adiciona um label e um combobox para inserir o treino da quarta
		this.criarLabol("Quarta:", 80, 140, 90, 30);
		JComboBox<Treino> comboquart = new JComboBox<>(criarCombobox());
		comboquart.setBounds(80, 165, 130, 25);
		add(comboquart);
		arrayComOsCombo[i++] = comboquart;

		
		//adiciona um label e um combobox para inserir o treino da quinta
		this.criarLabol("Quinta:", 340, 140, 90, 30);
		JComboBox<Treino> comboquin = new JComboBox<>(criarCombobox());
		comboquin.setBounds(340, 165, 130, 25);
		add(comboquin);
		arrayComOsCombo[i++] = comboquin;
		
		//adiciona um label e um combobox para inserir o treino da sexta
		this.criarLabol("Sexta:", 80, 200, 90, 30);
		JComboBox<Treino> combosex = new JComboBox<>(criarCombobox());
		combosex.setBounds(80, 225, 130, 25);
		add(combosex);
		arrayComOsCombo[i++] = combosex;
		
		//adiciona um label e um combobox para inserir o treino da sexta
		this.criarLabol("Sábado:", 340, 200, 90, 30);
		JComboBox<Treino> combosab = new JComboBox<>(criarCombobox());
		combosab.setBounds(340, 225, 130, 25);
		add(combosab);
		arrayComOsCombo[i++] = combosab;
		
		this.criarLabol("E-Treinador", 480, 310, 100, 30);
	}
	
	private Treino[] criarCombobox(){
		
		academia = persistencia.recuperar("academia.xml");
		
		ArrayList<Treino> origem = academia.getTreinos();
		Treino treino = new Treino("Não Treinar");
		int indice = origem.size();
		origem.add(indice, treino);
		
		Treino[] destino;  
		
		destino = (Treino[]) origem.toArray(new Treino[origem.size()]);
		return destino;
	}

	public void actionPerformed(ActionEvent e) {
		Treino[] treinos = new Treino[7];
		
		int i = 1;
		for(JComboBox combo: arrayComOsCombo){
			if(combo != null){
				treinos[i++] = (Treino) combo.getSelectedItem();
			}
		}
		//seta os treinos do aluno
		aluno.setTreinosDoAluno(treinos);
		
		//recupera o xml
		academia = persistencia.recuperar("academia.xml");
		
		//adiciona o aluno a academai
		academia.adicionarAluno(aluno);
		
		//salva a academia com o novo aluno
		persistencia.salvar(academia, "academia.xml");
		
		JOptionPane.showMessageDialog(null, "Treino salvo");
	
		this.dispose();
	}

	public JComboBox[] getArrayComOsCombo() {
		return arrayComOsCombo;
	}

	public void setArrayComOsCombo(JComboBox[] arrayComOsCombo) {
		this.arrayComOsCombo = arrayComOsCombo;
	}
}
