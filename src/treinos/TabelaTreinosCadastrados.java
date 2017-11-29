package treinos;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TabelaTreinosCadastrados extends JDialog {
	private Academia academia = new Academia();
	private Persistencia persistencia = new Persistencia();
	public TabelaTreinosCadastrados() {
		setSize(580, 380);
		setTitle("Treinos cadastrados");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		adicionarTabela();
	}
	
	private void adicionarTabela(){
		academia = persistencia.recuperar(academia.getArquivo());
		
		JPanel painel = (JPanel) getContentPane();
		painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
		
		painel.setBackground(Color.WHITE);
		
		MeuModeloDeTabela3 modelo = new MeuModeloDeTabela3(academia.getTreinos());
		JTable tabela = new JTable(modelo);
		JPanel painelTabela = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		painelTabela.setBackground(Color.WHITE);
		
		JScrollPane scrol = new JScrollPane(tabela);
		scrol.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		painelTabela.add(scrol);
		painel.add(painelTabela);
	}
}
