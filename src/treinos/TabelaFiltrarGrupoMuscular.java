package treinos;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TabelaFiltrarGrupoMuscular extends JDialog	{
	private	Academia academia = new Academia();
	private Persistencia persistencia = new Persistencia();
	public TabelaFiltrarGrupoMuscular() {
		setSize(580, 380);
		setTitle("Exercícios filtrados por grupo muscular");
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setModal(true);
		adicionarTabela();
		setLocationRelativeTo(null);
		repaint();
		setVisible(true);
	}

	private void adicionarTabela() {
		JPanel painel = (JPanel) getContentPane();
		painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
		
		painel.setBackground(Color.WHITE);
		
		academia = persistencia.recuperar(academia.getArquivo());
		
		MeuModeloDeTabela2 modelo = new MeuModeloDeTabela2(academia.listarTodosExerciciosAnaerobicos());
		
		JTable tabela = new JTable(modelo);
		
		JScrollPane scrol = new JScrollPane(tabela);
		scrol.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		JPanel painelTabela = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		painelTabela.setBackground(Color.WHITE);
		
		painelTabela.add(scrol);
		painel.add(painelTabela);
	}
	
	
}
