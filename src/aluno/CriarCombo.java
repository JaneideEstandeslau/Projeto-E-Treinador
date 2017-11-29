package aluno;


import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;

import treinos.Academia;
import treinos.Aluno;
import treinos.Persistencia;
import treinos.Treino;


public class CriarCombo {
	
	private JComboBox[] arrayComOsCombo = new JComboBox[7];
	private JDialog frame;
	Persistencia persistencia  = new Persistencia();
	Academia academia;
	
	public JComboBox[] adicionarCombo(JDialog frame, int indice){
		this.frame = frame;
		int i = 1;
		
		academia = persistencia.recuperar("academia.xml");
		
		ArrayList<Aluno> alunos = academia.getAlunos();
		Treino[] treinos = alunos.get(indice).getTreinosDoAluno();
		
		
		//.setSelectedIndex(1);
		//adiciona um label e um combobox para inserir o treino da segunda
		
		this.criarLabol("Segunda:", 80, 80, 90, 30);
		JComboBox<Treino> comboseg = new JComboBox<>(criarCombobox());
		comboseg.setBounds(80, 105, 130, 25);
		frame.add(comboseg);
		comboseg.setSelectedItem(treinos[1]);
		arrayComOsCombo[i++] = comboseg;
		
		//adiciona um label e um combobox para inserir o treino da ter�a
		this.criarLabol("Ter�a:", 340, 80, 90, 30);
		JComboBox<Treino> comboter = new JComboBox<>(criarCombobox());
		comboter.setBounds(340, 105, 130, 25);
		frame.add(comboter);
		comboter.setSelectedItem(treinos[2]);
		arrayComOsCombo[i++] = comboter;
		
		//adiciona um label e um combobox para inserir o treino da quarta
		this.criarLabol("Quarta:", 80, 140, 90, 30);
		JComboBox<Treino> comboquart = new JComboBox<>(criarCombobox());
		comboquart.setBounds(80, 165, 130, 25);
		frame.add(comboquart);
		comboquart.setSelectedItem(treinos[3]);
		arrayComOsCombo[i++] = comboquart;

		
		//adiciona um label e um combobox para inserir o treino da quinta
		this.criarLabol("Quinta:", 340, 140, 90, 30);
		JComboBox<Treino> comboquin = new JComboBox<>(criarCombobox());
		comboquin.setBounds(340, 165, 130, 25);
		frame.add(comboquin);
		comboquin.setSelectedItem(treinos[4]);
		arrayComOsCombo[i++] = comboquin;
		
		//adiciona um label e um combobox para inserir o treino da sexta
		this.criarLabol("Sexta:", 80, 200, 90, 30);
		JComboBox<Treino> combosex = new JComboBox<>(criarCombobox());
		combosex.setBounds(80, 225, 130, 25);
		frame.add(combosex);
		combosex.setSelectedItem(treinos[5]);
		arrayComOsCombo[i++] = combosex;
		
		//adiciona um label e um combobox para inserir o treino da sexta
		this.criarLabol("S�bado:", 340, 200, 90, 30);
		JComboBox<Treino> combosab = new JComboBox<>(criarCombobox());
		combosab.setBounds(340, 225, 130, 25);
		frame.add(combosab);
		combosab.setSelectedItem(treinos[6]);
		arrayComOsCombo[i++] = combosab;
		
		this.criarLabol("E-Treinador", 480, 310, 100, 30);
		return arrayComOsCombo;
	}
	
	private Treino[] criarCombobox(){
		
		academia = persistencia.recuperar("academia.xml");
		
		ArrayList<Treino> origem = academia.getTreinos();
		Treino treino = new Treino("N�o Treinar");
		int indice = origem.size();
		origem.add(indice, treino);
		
		Treino[] destino;  
		
		destino = (Treino[]) origem.toArray(new Treino[origem.size()]);
		return destino;
	}
	public void criarLabol(String titulo, int x, int y, int w, int h){
		JLabel idade = new JLabel(titulo);
		idade.setBounds(x, y, w, h);
		frame.add(idade);
	}

}
