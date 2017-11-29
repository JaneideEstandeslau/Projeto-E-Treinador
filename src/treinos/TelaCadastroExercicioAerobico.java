package treinos;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TelaCadastroExercicioAerobico extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField campoNomeExercicio;
	private JPanel painelNomeExercicioECampo;
	private JLabel nomeExercicioLabel;
	private JButton cadastrar;
	private JButton limpar;
//	private ImageIcon iconeLimpar = new ImageIcon(getClass().getResource("icones/limpar.png"));
//	private ImageIcon iconeCadastrar = new ImageIcon(getClass().getResource("icones/cadastroAerobico.png"));
	public TelaCadastroExercicioAerobico() {
		setSize(580, 380);
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		adicionarComponentes();
		setModal(true);
		setTitle("Cadastro de Exercício Aeríbico");
		setVisible(true);
		repaint();
	}
	
	public void adicionarComponentes(){
		JPanel painel = (JPanel) getContentPane();
		
		painel.setBackground(Color.WHITE);
		
		BoxLayout bl = new BoxLayout(painel, BoxLayout.Y_AXIS);
		painel.setLayout(bl);
		
		painelNomeExercicioECampo = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		nomeExercicioLabel = new JLabel("Nome do exercício");
		nomeExercicioLabel.setFont(new Font("Calibri", Font.PLAIN, 17));
				
		campoNomeExercicio = new JTextField(15);
		campoNomeExercicio.setFont(new Font("Calibri", Font.PLAIN, 17));
		
		painelNomeExercicioECampo.add(nomeExercicioLabel);
		painelNomeExercicioECampo.add(campoNomeExercicio);
		
		
		cadastrar = new JButton("Cadastrar");
		cadastrar.setFont(new Font("Calibri", Font.PLAIN, 17));
		limpar = new JButton("Limpar");
		limpar.setFont(new Font("Calibri", Font.PLAIN, 17));
		
		JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		
		painelBotoes.setBackground(Color.WHITE);
		painelNomeExercicioECampo.setBackground(Color.WHITE);
		
		painelBotoes.add(cadastrar);
		painelBotoes.add(limpar);
		
		painel.add(painelNomeExercicioECampo);
		painel.add(painelBotoes);
		
		OuvinteBotaoCadastrarAerobico ouvinteBotaoCadastrar = new OuvinteBotaoCadastrarAerobico(this);
		OuvinteBotaoLimparAerobico ouvinteBotaoLimparAerobico = new OuvinteBotaoLimparAerobico(this);
		cadastrar.addActionListener(ouvinteBotaoCadastrar);
		limpar.addActionListener(ouvinteBotaoLimparAerobico);
	}

	public JTextField getCampoNomeExercicio() {
		return campoNomeExercicio;
	}

	public void setCampoNomeExercicio(JTextField campoNomeExercicio) {
		this.campoNomeExercicio = campoNomeExercicio;
	}

	public JPanel getPainelNomeExercicioECampo() {
		return painelNomeExercicioECampo;
	}

	public void setPainelNomeExercicioECampo(JPanel painelNomeExercicioECampo) {
		this.painelNomeExercicioECampo = painelNomeExercicioECampo;
	}

	public JLabel getNomeExercicioLabel() {
		return nomeExercicioLabel;
	}

	public void setNomeExercicioLabel(JLabel nomeExercicioLabel) {
		this.nomeExercicioLabel = nomeExercicioLabel;
	}

	public JButton getCadastrar() {
		return cadastrar;
	}

	public void setCadastrar(JButton cadastrar) {
		this.cadastrar = cadastrar;
	}

	public JButton getLimpar() {
		return limpar;
	}

	public void setLimpar(JButton limpar) {
		this.limpar = limpar;
	}

}
