package treinos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import Excecoes.CampoVazioException;
import Excecoes.NumeroIgualAZeroException;

public class TelaAdicionalCadastrarTreinoAerobico extends JDialog {
	private JTextField campoDuracao;
	private JButton btOk;
	private Verificacao verificar = new Verificacao();
	private ImageIcon erro = new ImageIcon("src/icones/cancelar.png");
	private Font fonte = new Font("Calibri",Font.PLAIN,17);
	private Boolean clicouEmOk = false;
	
	public TelaAdicionalCadastrarTreinoAerobico(JDialog dialog) {
		setSize(350, 150);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(dialog);
		adicionarComponentes();
		setModal(true);
		repaint();
		setVisible(true);
	}
	
	private void adicionarComponentes() {
		JPanel painel = (JPanel) getContentPane();
		painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
		
		painel.setBackground(Color.WHITE);
		
		JPanel labelETextF = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		labelETextF.setBackground(Color.WHITE);
		
		JLabel duracaoLabel = new JLabel("Duração");
		duracaoLabel.setFont(fonte);
		campoDuracao = new JTextField();
		campoDuracao.setFont(fonte);
		campoDuracao.setPreferredSize(new Dimension(30, 24));
		labelETextF.add(duracaoLabel);
		labelETextF.add(campoDuracao);
		painel.add(labelETextF);
		
		JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		painelBotoes.setBackground(Color.WHITE);
		
		btOk = new JButton("OK");
		btOk.setFont(fonte);
		JButton btCancelar = new JButton("Cancelar");
		btCancelar.setFont(fonte);
		btCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		painelBotoes.add(btOk);
		painelBotoes.add(btCancelar);
		painel.add(painelBotoes);
		verificacao(btOk, this);
	}
	public void verificacao(JButton btOk, JDialog dialog){
		
		btOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					verificar.igualAZeroFloat(Float.parseFloat(getCampoDuracao().getText()));
					verificar.stringVazia(getCampoDuracao().getText());
					Float.parseFloat(getCampoDuracao().getText());
					clicouEmOk = true;
					dispose();
				}catch(CampoVazioException c){
					UIManager.put("OptionPane.messageFont", fonte);
					UIManager.put("OptionPane.buttonFont", fonte);
					JOptionPane.showMessageDialog(dialog, "Informe a duração do exercício.", "Erro", JOptionPane.PLAIN_MESSAGE, erro);
				}catch(NumeroIgualAZeroException c){
					UIManager.put("OptionPane.messageFont", fonte);
					UIManager.put("OptionPane.buttonFont", fonte);
					JOptionPane.showMessageDialog(dialog, "Informe uma duração maior que zero.", "Erro", JOptionPane.PLAIN_MESSAGE, erro);
				}catch(NumberFormatException c){
					UIManager.put("OptionPane.messageFont", fonte);
					UIManager.put("OptionPane.buttonFont", fonte);
					JOptionPane.showMessageDialog(dialog, "Duração inválida, informe apenas némeros ou némeros e pontos.", "Erro", JOptionPane.PLAIN_MESSAGE, erro);
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

	public JButton getBtOk() {
		return btOk;
	}

	public void setBtOk(JButton btOk) {
		this.btOk = btOk;
	}

	public JTextField getCampoDuracao() {
		return campoDuracao;
	}

	public void setCampoDuracao(JTextField campoDuracao) {
		this.campoDuracao = campoDuracao;
	}
	
}
