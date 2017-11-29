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
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class TelaCadastroExercicioAnaerobico extends JDialog {
	private OuvinteBotaoCadastrarAnaerobico ouvinteBotaoCadastrarAnaerobico = new OuvinteBotaoCadastrarAnaerobico(this);
	private OuvinteBotaoLimparAnaerobico ouvinteBotaoLimparAnaerobico = new OuvinteBotaoLimparAnaerobico(this);
	private JTextField campoNomeExercicio;
	private JComboBox<String> comboBoxGruposMusculares;
	private JSpinner spinner;
//	private ImageIcon iconeLimpar = new ImageIcon(getClass().getResource("icones/limpar.png"));
//	private ImageIcon iconeCadastroAnaerobico = new ImageIcon(getClass().getResource("icones/cadastroAnaerobico.png"));
	public TelaCadastroExercicioAnaerobico() {
		setSize(580, 380);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Cadastro de Exercício Anaeróbico");
		adicionarComponentes();
		setModal(true);
		setResizable(false);
		repaint();
		setVisible(true);
	}
	public void adicionarComponentes() {
		JPanel painel = (JPanel) getContentPane();
		
		painel.setBackground(Color.WHITE);
		
		painel.setBackground(Color.WHITE);
		painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
		
		JPanel parte1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel nomeExercicioLabel = new JLabel("Nome do exercício");
		nomeExercicioLabel.setFont(new Font("Calibri", Font.PLAIN, 17));
		campoNomeExercicio = new JTextField(15);
		campoNomeExercicio.setFont(new Font("Calibri", Font.PLAIN, 17));
		parte1.add(nomeExercicioLabel);
		parte1.add(campoNomeExercicio);
		
		parte1.setBackground(Color.WHITE);
		
		JPanel parte2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel grupoMuscularLabel = new JLabel("Informe o grupo muscular do exercício");
		grupoMuscularLabel.setFont(new Font("Calibri", Font.PLAIN, 17));
		String nomes[] = { "", "bíceps", "tríceps", "costas", "abdómen", "ombro", "pernas" };
		comboBoxGruposMusculares = new JComboBox<>(nomes);
		comboBoxGruposMusculares.setFont(new Font("Calibri", Font.PLAIN, 17));
		parte2.add(grupoMuscularLabel);
		parte2.add(comboBoxGruposMusculares);
		
		parte2.setBackground(Color.WHITE);
		
		JPanel parte3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel duracaoLabel = new JLabel("Média de tempo (em minutos) para uma série de 10 repetições");
		duracaoLabel.setFont(new Font("Calibri", Font.PLAIN, 17));
		spinner = new JSpinner();
		spinner.setFont(new Font("Calibri", Font.PLAIN, 17));
		parte3.add(duracaoLabel);
		parte3.add(spinner);
		
		parte3.setBackground(Color.WHITE);
		
		JPanel parte4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton cadastrar = new JButton("Cadastrar");
		cadastrar.setFont(new Font("Calibri", Font.PLAIN, 17));
		JButton limpar = new JButton("Limpar");
		limpar.setFont(new Font("Calibri", Font.PLAIN, 17));
		parte4.add(cadastrar);
		parte4.add(limpar);
		
		parte4.setBackground(Color.WHITE);
		parte1.add(parte2);
		parte1.add(parte3);
		parte1.add(parte4);
		painel.add(parte1);
		
		cadastrar.addActionListener(ouvinteBotaoCadastrarAnaerobico);
		limpar.addActionListener(ouvinteBotaoLimparAnaerobico);
	}

	public JTextField getCampoNomeExercicio() {
		return campoNomeExercicio;
	}

	public void setCampoNomeExercicio(JTextField campoNomeExercicio) {
		this.campoNomeExercicio = campoNomeExercicio;
	}

	public JComboBox<String> getComboBoxGruposMusculares() {
		return comboBoxGruposMusculares;
	}

	public void setComboBoxGruposMusculares(JComboBox<String> comboBoxGruposMusculares) {
		this.comboBoxGruposMusculares = comboBoxGruposMusculares;
	}

	public JSpinner getSpinner() {
		return spinner;
	}

	public void setSpinner(JSpinner spinner) {
		this.spinner = spinner;
	}

}
