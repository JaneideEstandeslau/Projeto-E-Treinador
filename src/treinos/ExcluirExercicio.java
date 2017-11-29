package treinos;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;


public class ExcluirExercicio extends JDialog {
	private Font fonte = new Font("Calibri", Font.PLAIN, 17);
	private JTable tabela;
	private Persistencia persistencia = new Persistencia();
	private Academia academia = new Academia();
	private MeuModeloDeTabela modelo;
//	private ImageIcon info = new ImageIcon(getClass().getResource("icones/info.png"));
//	private ImageIcon erro = new ImageIcon(getClass().getResource("icones/cancelar.png"));
//	private ImageIcon exclamacao = new ImageIcon(getClass().getResource("icones/exclamacao.png"));
//	private ImageIcon iconeNovo = new ImageIcon(getClass().getResource("icones/novo.png"));
//	private ImageIcon iconeExcluir = new ImageIcon(getClass().getResource("icones/excluir.png"));
//	private ImageIcon iconeInterrogacao = new ImageIcon(getClass().getResource("icones/interrogacao.png"));
	public ExcluirExercicio() {
		setSize(580, 380);
		setTitle("Excluir exercício");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setModal(true);
		setLocationRelativeTo(null);
		setLayout(null);
		setResizable(false);
		adicionarComponentes(this);
		repaint();
		setVisible(true);
	}

	public void adicionarComponentes(JDialog dialog) {
		
		JPanel painel = (JPanel) getContentPane();
		painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
		
		painel.setBackground(Color.WHITE);
		
		JPanel painelTabela = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		painelTabela.setBackground(Color.WHITE);
		
		academia = persistencia.recuperar(academia.getArquivo());
		modelo = new MeuModeloDeTabela(academia.getExercicios());
		tabela = new JTable(modelo);
		JScrollPane scrol = new JScrollPane(tabela);
		scrol.setFont(new Font("Calibri", Font.PLAIN, 17));
		scrol.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		painelTabela.add(scrol);
		JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		painelBotoes.setBackground(Color.WHITE);
		
		JButton novo = new JButton("Novo");
		novo.setFont(fonte);
		novo.addActionListener(new ActionListener() {
			String opcao[] = { "Aeróbico", "Anaeróbico" };

			public void actionPerformed(ActionEvent arg0) {
				UIManager.put("OptionPane.messageFont", fonte);
				UIManager.put("OptionPane.buttonFont", fonte);
				Integer x = JOptionPane.showOptionDialog(dialog, "Qual o tipo do exercício que você deseja cadastrar ?",
						"Novo cadastro", 0, JOptionPane.PLAIN_MESSAGE, null, opcao, opcao[0]);
				if (x == 0) {
					dispose();
					new TelaCadastroExercicioAerobico();
				} else if (x == 1) {
					dispose();
					new TelaCadastroExercicioAnaerobico();
				}
			}
		});
		academia = persistencia.recuperar(academia.getArquivo());
		painelBotoes.add(novo);
		JButton excluir = new JButton("Excluir");
		excluir.setFont(fonte);
		excluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				Integer linhaSelecionada = tabela.getSelectedRow();
				if(linhaSelecionada != -1){
					String nomeLinha = modelo.obterNomeLinha(linhaSelecionada);
					if(academia.exercicioUsadoNoTreino(nomeLinha) == 1){
						UIManager.put("OptionPane.messageFont", fonte);
						UIManager.put("OptionPane.messageButton", fonte);
						JOptionPane.showMessageDialog(dialog, "Não será possivel excluir esse exercício, ele está sendo usado para compor um treino.", "Erro", JOptionPane.PLAIN_MESSAGE);
					}else{
						modelo.removerLinha(linhaSelecionada);
						modelo.fireTableDataChanged();
						academia.excluirExercicio(linhaSelecionada);
						if(persistencia.salvar(academia, academia.getArquivo())){
							UIManager.put("OptionPane.messageFont", fonte);
							UIManager.put("OptionPane.messageButton", fonte);
							JOptionPane.showMessageDialog(dialog, "Exercício excluído com sucesso.", "Mensagem", JOptionPane.PLAIN_MESSAGE);
						}
					}
				}else{
					UIManager.put("OptionPane.messageFont", fonte);
					UIManager.put("OptionPane.messageButton", fonte);
					JOptionPane.showMessageDialog(dialog, "Selecione o exercício.", "Atenção", JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		painelBotoes.add(excluir);
		painel.add(painelTabela);
		painel.add(painelBotoes);
}
}