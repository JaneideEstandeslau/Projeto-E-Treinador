package treinos;

import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TabelaTodosExercicios extends JDialog{
	private Persistencia persistencia = new Persistencia();
	private Academia academia = new Academia();
	public TabelaTodosExercicios() {
		setSize(580, 380);
		setTitle("Todos os exercícios cadastrados");
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setModal(true);
		setLocationRelativeTo(null);
		adicionarComponentes();
		repaint();
		setVisible(true);
	}
	
	public void adicionarComponentes(){
		JPanel painel = (JPanel) getContentPane();
		
		painel.setBackground(Color.WHITE);
		
		academia = persistencia.recuperar(academia.getArquivo());
		MeuModeloDeTabela modelo = new MeuModeloDeTabela(academia.getExercicios());
		JTable tabela = new JTable(modelo);
		JScrollPane scrol = new JScrollPane(tabela);
		scrol.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		JPanel painelTabela = new JPanel();
		
		painelTabela.setBackground(Color.WHITE);
		
		painelTabela.add(scrol);
		painel.add(painelTabela);
	}
}
