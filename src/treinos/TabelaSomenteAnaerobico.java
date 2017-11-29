package treinos;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TabelaSomenteAnaerobico extends JDialog{
	private Persistencia persistencia = new Persistencia();
	private Academia academia = new Academia();
	public TabelaSomenteAnaerobico() {
		setSize(580, 380);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Tabela dos exercícios anaeróbicos cadastrados");
		setLocationRelativeTo(null);
		adicionarTabela();
		setModal(true);
		repaint();
		setVisible(true);
	}
	
	private void adicionarTabela(){
		JPanel painel = (JPanel) getContentPane();
		
		painel.setBackground(Color.WHITE);
		
		academia = persistencia.recuperar(academia.getArquivo());
		MeuModeloDeTabela modelo = new MeuModeloDeTabela(academia.listarTodosExerciciosAnaerobicos());
		JTable tabela = new JTable(modelo);
		JScrollPane scrol = new JScrollPane(tabela);
		scrol.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		JPanel painelTabela = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		painelTabela.setBackground(Color.WHITE);
		
		painelTabela.add(scrol);
		painel.add(painelTabela);
	}
}