package treinos;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import Excecoes.CampoVazioException;
import Excecoes.CaracterInvalidoException;

public class TabelaTreinosComExerciciosEspecificos extends JDialog {
	private Font fonte = new Font("Calibri", Font.PLAIN, 17);
	private Academia academia = new Academia();
	private Persistencia persistencia = new Persistencia();
//	private ImageIcon info = new ImageIcon(getClass().getResource("icones/info.png"));
//	private ImageIcon erro = new ImageIcon(getClass().getResource("icones/erro.png"));
//	private ImageIcon exclamacao = new ImageIcon(getClass().getResource("icones/exclamacao.png"));
//	private ImageIcon cancelar = new ImageIcon(getClass().getResource("icones/cancelar.png"));
//	private ImageIcon lupa = new ImageIcon(getClass().getResource("icones/lupa.png"));
	private Verificacao verificar = new Verificacao();
	public TabelaTreinosComExerciciosEspecificos() {
		setSize(580, 380);
		setTitle("Treinos que possuem um exercício específico");
		setResizable(false);
		adicionarComponentes(this);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setModal(true);
		setLocationRelativeTo(null);
		repaint();
		setVisible(true);
	}

	private void adicionarComponentes(JDialog dialog) {
		academia = persistencia.recuperar(academia.getArquivo());
		
		JPanel painel = (JPanel) getContentPane();
		painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
		
		painel.setBackground(Color.WHITE);
		
		JPanel labelECampo = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		labelECampo.setBackground(Color.WHITE);
		
		JLabel nomeExercicioLabel = new JLabel("Informe o nome do exercício");
		nomeExercicioLabel.setFont(fonte);
		JTextField campoNomeExercicio = new JTextField(15);
		campoNomeExercicio.setFont(fonte);
		labelECampo.add(nomeExercicioLabel);
		labelECampo.add(campoNomeExercicio);
		painel.add(labelECampo);
		JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		painelBotoes.setBackground(Color.WHITE);
		
		JButton btProcurar = new JButton("Procurar");
		btProcurar.setFont(fonte);
		JButton btCancelar = new JButton("Cancelar");
		btCancelar.setFont(fonte);
		btCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		painelBotoes.add(btProcurar);
		painelBotoes.add(btCancelar);
		painel.add(painelBotoes);
		
		JPanel painelTabela = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		painelTabela.setBackground(Color.WHITE);
		
		JTable tabela = new JTable();
		JScrollPane scrol = new JScrollPane(tabela);
		scrol.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		painelTabela.add(scrol);
		painel.add(painelTabela);
		btProcurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					verificar.stringVazia(campoNomeExercicio.getText());
					verificar.caracterInvalido(campoNomeExercicio.getText());
					MeuModeloDeTabela4 modelo = new MeuModeloDeTabela4(academia.procurarExercicioNoTreino(campoNomeExercicio.getText()));
					if(academia.procurarExercicioNoTreino(campoNomeExercicio.getText()).size() == 0){
						tabela.removeAll();
						tabela.setVisible(false);
						UIManager.put("OptionPane.messageFont", fonte);
						UIManager.put("OptionPane.buttonFont", fonte);
						JOptionPane.showMessageDialog(dialog, "O exercício "+campoNomeExercicio.getText()+" não foi encontrado em nenhum treino, verifique o nome digitado e tente novamente.", "Atenção", JOptionPane.PLAIN_MESSAGE);
					}else{
						tabela.setVisible(true);
						tabela.setModel(modelo);
						UIManager.put("OptionPane.messageFont", fonte);
						UIManager.put("OptionPane.buttonFont", fonte);
						JOptionPane.showMessageDialog(dialog, "O exercício "+campoNomeExercicio.getText()+" foi encontrado em "+academia.procurarExercicioNoTreino(campoNomeExercicio.getText()).size()+" treinos.", "Informaçõo", JOptionPane.PLAIN_MESSAGE);
					}
				}catch(CampoVazioException c){
					UIManager.put("OptionPane.messageFont", fonte);
					UIManager.put("OptionPane.buttonFont", fonte);
					JOptionPane.showMessageDialog(dialog, "Informe o nome do exercício.", "Atenção", JOptionPane.PLAIN_MESSAGE);
				}catch(CaracterInvalidoException c){
					UIManager.put("OptionPane.messageFont", fonte);
					UIManager.put("OptionPane.buttonFont", fonte);
					JOptionPane.showMessageDialog(dialog, "O nome do exercício informado não á válido, digite apenas letras.", "Atenção", JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		
		
		
	}
}
