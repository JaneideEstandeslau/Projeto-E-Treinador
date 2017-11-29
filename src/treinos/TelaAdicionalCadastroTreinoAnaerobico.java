package treinos;

import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JTextField;
import javax.swing.UIManager;

import Excecoes.CampoVazioException;
import Excecoes.SelecioneUmValorNoSpinnerException;

public class TelaAdicionalCadastroTreinoAnaerobico extends JDialog {
	private Font fonte = new Font("Calibri", Font.PLAIN, 17);
	private JTextField campoQtdSeries;
	private JTextField campoQtdRepeticoes;
	private Verificacao verificar = new Verificacao();
	private ImageIcon erro = new ImageIcon("src/icones/erro.png");
	private Boolean clicouEmOk = false;

	public TelaAdicionalCadastroTreinoAnaerobico(JDialog dialog) {
		setSize(380, 150);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(dialog);
		setModal(true);
		adicionarComponentes(this);
		repaint();
		setVisible(true);
	}
	
	public void adicionarComponentes(JDialog dialog){
		JPanel painel = (JPanel) getContentPane();
		painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
		
		painel.setBackground(Color.WHITE);
		
		JPanel painelLabelEcampo = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		painelLabelEcampo.setBackground(Color.WHITE);
		
		JLabel labelQtdSeries = new JLabel("Quantidade de séries");
		labelQtdSeries.setFont(fonte);
		campoQtdSeries = new JTextField();
		campoQtdSeries.setPreferredSize(new Dimension(30, 24));
		campoQtdSeries.setFont(fonte);
		painelLabelEcampo.add(labelQtdSeries);
		painelLabelEcampo.add(campoQtdSeries);
		painel.add(painelLabelEcampo);
		
		JPanel painelLabelEcampo2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		painelLabelEcampo2.setBackground(Color.WHITE);
		
		JLabel labelQtdRepeticoes = new JLabel("Quantidade de repetiçĩes");
		labelQtdRepeticoes.setFont(fonte);
		campoQtdRepeticoes = new JTextField();
		campoQtdRepeticoes.setPreferredSize(new Dimension(30, 24));
		campoQtdRepeticoes.setFont(fonte);
		painelLabelEcampo2.add(labelQtdRepeticoes);
		painelLabelEcampo2.add(campoQtdRepeticoes);
		painel.add(painelLabelEcampo2);
		
		JPanel painelbotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		painelbotoes.setBackground(Color.WHITE);
		
		JButton btOk = new JButton("Ok");
		JButton btCancelar = new JButton("Cancelar");
		btOk.setFont(fonte);
		btCancelar.setFont(fonte);
		btCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		painelbotoes.add(btOk);
		painelbotoes.add(btCancelar);
		painel.add(painelbotoes);
		
		verificacao(btOk, this);
	}

	public void verificacao(JButton btOk, JDialog dialog){
		btOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					verificar.stringVazia(campoQtdSeries.getText());
					verificar.stringVazia(campoQtdRepeticoes.getText());
					verificar.igualAZero(Integer.parseInt(campoQtdSeries.getText()));
					verificar.igualAZero(Integer.parseInt(campoQtdRepeticoes.getText()));
					Integer.parseInt(campoQtdSeries.getText());
					Integer.parseInt(campoQtdRepeticoes.getText());
					clicouEmOk = true;
					dispose();
				}catch(CampoVazioException c){	
					UIManager.put("OptionPane.messageFont", fonte);
					UIManager.put("OptionPane.buttonFont", fonte);
					JOptionPane.showMessageDialog(dialog, "Preencha todos os campos.", "Erro", JOptionPane.PLAIN_MESSAGE, erro);
					
				}catch(SelecioneUmValorNoSpinnerException c){		
					UIManager.put("OptionPane.messageFont", fonte);
					UIManager.put("OptionPane.buttonFont", fonte);
					JOptionPane.showMessageDialog(dialog, "A quantidade de séries e de repetições devem ser maiores que zero", "Erro", JOptionPane.PLAIN_MESSAGE, erro);
					
				}catch(NumberFormatException c){		
					UIManager.put("OptionPane.messageFont", fonte);
					UIManager.put("OptionPane.buttonFont", fonte);
					JOptionPane.showMessageDialog(dialog, "Informe apenas números.", "Erro", JOptionPane.PLAIN_MESSAGE, erro);
					
				}
			}
		});
	}
	
	public Boolean getClicouEmOk() {
		return clicouEmOk;
	}

	public void setClicouEmOk(Boolean clicouEmOk) {
		this.clicouEmOk = clicouEmOk;
	}

	public JTextField getCampoQtdSeries() {
		return campoQtdSeries;
	}

	public void setCampoQtdSeries(JTextField campoQtdSeries) {
		this.campoQtdSeries = campoQtdSeries;
	}

	public JTextField getCampoQtdRepeticoes() {
		return campoQtdRepeticoes;
	}

	public void setCampoQtdRepeticoes(JTextField campoQtdRepeticoes) {
		this.campoQtdRepeticoes = campoQtdRepeticoes;
	}
	
}
