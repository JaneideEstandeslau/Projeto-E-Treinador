package treinos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

public class DetalharTreino extends JDialog {
	private Font fonte = new Font("Calibri", Font.PLAIN, 17);
	private Persistencia persistencia = new Persistencia();
//	private ImageIcon exclamacao = new ImageIcon(getClass().getResource("icones/exclamacao.png"));
	private Academia academia = new Academia();
	private int indice = 0;
	public DetalharTreino() {
		setSize(580, 380);
		setTitle("Detalhar treino");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setModal(true);
		setLocationRelativeTo(null);
		setLayout(null);
		setResizable(false);
		adicionarComponentes(this);
		repaint();
		setVisible(true);
	}

	private void adicionarComponentes(JDialog dialog) {
		academia = persistencia.recuperar(academia.getArquivo());
		
		JPanel painel = (JPanel) getContentPane();
		painel.setBackground(Color.WHITE);
		painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
		
		JPanel labelECombo = new JPanel(new FlowLayout(FlowLayout.CENTER));
		labelECombo.setBackground(Color.WHITE);
		JLabel selecioneLabel = new JLabel("Selecione o treino");
		selecioneLabel.setFont(fonte);
		ArrayList<Object> treinos = new ArrayList<>();
		treinos.add(0, "");
		for(Treino t : academia.getTreinos()){
			treinos.add(t);
		}
		JComboBox<Object> comboBoxTreinos = new JComboBox<>(treinos.toArray());
		labelECombo.add(selecioneLabel);
		comboBoxTreinos.setFont(fonte);
		labelECombo.add(comboBoxTreinos);
		painel.add(labelECombo);
		
		JPanel paineltabela = new JPanel(new GridLayout(1, 2, 10, 10));
		paineltabela.setBackground(Color.WHITE);
		JTable tabelaAlunos = new JTable();
		JTable tabelaExercicios = new JTable();
		
		JScrollPane scrol = new JScrollPane(tabelaAlunos);
		JScrollPane scrol2 = new JScrollPane(tabelaExercicios);
		
		paineltabela.add(scrol);
		paineltabela.add(scrol2);
		
		comboBoxTreinos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				indice = comboBoxTreinos.getSelectedIndex();
				if(indice != 0){
					indice -= 1;
					
					ArrayList<Aluno> alunos = academia.alunosUsandoTreino(comboBoxTreinos.getSelectedItem().toString());
					MeuModeloDeTabela5 modelo = new MeuModeloDeTabela5(alunos);
					tabelaAlunos.setModel(modelo);
					
					ArrayList<Exercicio> exer = academia.obterExerciciosDoTreino(indice);
					MeuModeloDeTabela6 modelo2 = new MeuModeloDeTabela6(exer);
					tabelaExercicios.setModel(modelo2);
					
					tabelaAlunos.setVisible(true);
					tabelaExercicios.setVisible(true);
				}else{
					tabelaAlunos.setVisible(false);
					tabelaExercicios.setVisible(false);
				}
			}
		});
		painel.add(paineltabela);
		painel.repaint();
		painel.revalidate();
		
	}
}
