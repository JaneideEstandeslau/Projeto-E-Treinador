package treinos;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class ExcluirTreino extends JDialog {
	private Font fonte = new Font("Calibri", Font.PLAIN, 17);
	private Academia academia = new Academia();
	private Persistencia persistencia = new Persistencia();
//	private ImageIcon exclamacao = new ImageIcon(getClass().getResource("icones/exclamacao.png"));
//	private ImageIcon info = new ImageIcon(getClass().getResource("icones/info.png"));
//	private ImageIcon erro = new ImageIcon(getClass().getResource("icones/erro.png"));
//	private ImageIcon excluir = new ImageIcon(getClass().getResource("icones/excluir.png"));
//	private ImageIcon cancelar = new ImageIcon(getClass().getResource("icones/cancelar.png"));
	private int indice = 0;
	public ExcluirTreino() {
		setSize(580, 380);
		setTitle("Excluir treino");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		adicionarComponentes(this);
		setModal(true);
		setVisible(true);
		repaint();
	}
	
	public void adicionarComponentes(JDialog dialog){
		JPanel painel = (JPanel) getContentPane();
		painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
		
		painel.setBackground(Color.WHITE);
		
		JPanel labelEComboBox = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		labelEComboBox.setBackground(Color.WHITE);
		
		JLabel msgLabel = new JLabel("Selecione o treino que você deseja excluir");
		msgLabel.setFont(fonte);
		academia = persistencia.recuperar(academia.getArquivo());
		JComboBox<Object> comboBoxNomeTreino = new JComboBox<>();
		comboBoxNomeTreino.addItem("");
		for(int i = 0; i < academia.getTreinos().size(); i++){
			comboBoxNomeTreino.addItem(academia.getTreinos().get(i).toString());
		}
		comboBoxNomeTreino.setFont(fonte);
		labelEComboBox.add(msgLabel);
		labelEComboBox.add(comboBoxNomeTreino);
		painel.add(labelEComboBox);
		JPanel botoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		botoes.setBackground(Color.WHITE);
		
		JButton btExcluir = new JButton("Excluir");
		JButton btCancelar = new JButton("Cancelar");
		btCancelar.setFont(fonte);
		btCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btExcluir.setFont(fonte);
		comboBoxNomeTreino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				indice = comboBoxNomeTreino.getSelectedIndex();
			}
		});
		btExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(indice != 0){
					if(!academia.treinoUsadoPorAluno(comboBoxNomeTreino.getSelectedItem().toString())){
						academia.getTreinos().remove(indice -1);
						comboBoxNomeTreino.removeItemAt(indice);
						if(persistencia.salvar(academia, academia.getArquivo())){
							UIManager.put("OptionPane.messageFont", fonte);
							UIManager.put("OptionPane.messageButton", fonte);
							JOptionPane.showMessageDialog(dialog, "Treino removido com sucesso.", "Informaçõo", JOptionPane.PLAIN_MESSAGE);
						}else{
							UIManager.put("OptionPane.messageFont", fonte);
							UIManager.put("OptionPane.messageButton", fonte);
							JOptionPane.showMessageDialog(dialog, "Ocorreu um erro ao salvar o treino, entre em contato com o desenvolvedor do sistema.", "Informaçõo", JOptionPane.PLAIN_MESSAGE);
						}
					}else{
						UIManager.put("OptionPane.messageFont", fonte);
						UIManager.put("OptionPane.messageButton", fonte);
						JOptionPane.showMessageDialog(dialog, "Não será possivel excluir esse treino, ele está sendo usado por um aluno.", "Erro", JOptionPane.PLAIN_MESSAGE);
					}
					
				}else{
					UIManager.put("OptionPane.messageFont", fonte);
					UIManager.put("OptionPane.messageButton", fonte);
					JOptionPane.showMessageDialog(dialog, "Selecione o treino.", "Atenção", JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		botoes.add(btExcluir);
		botoes.add(btCancelar);
		painel.add(botoes);
	}
}
