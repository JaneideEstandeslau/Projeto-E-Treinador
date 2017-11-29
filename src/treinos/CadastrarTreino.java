package treinos;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
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
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import Excecoes.ArrayVazioException;
import Excecoes.CampoVazioException;
import Excecoes.CaracterInvalidoException;

public class CadastrarTreino extends JDialog {
//	private ImageIcon info = new ImageIcon(getClass().getResource("icones/info.png"));
//	private ImageIcon erro = new ImageIcon(getClass().getResource("icones/erro.png"));
//	private ImageIcon interrogacao = new ImageIcon(getClass().getResource("icones/interrogacao.png"));
//	private ImageIcon cancelar = new ImageIcon(getClass().getResource("icones/cancelar.png"));
//	private ImageIcon treino = new ImageIcon(getClass().getResource("icones/treino.png"));
	private Verificacao verificar = new Verificacao();
	private Academia academia = new Academia();
	private Persistencia persistencia = new Persistencia();
	private JPanel painel = (JPanel) getContentPane();
	private JTextField campo;
	private JButton btCadastrar;
	private Exercicio exercicio;
	private JCheckBox check;
	private Font fonte = new Font("Calibri", Font.PLAIN, 17);

	public CadastrarTreino() {
		setSize(580, 380);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setModal(true);
		setTitle("E-Treinador | Cadastrar treino");
		adicionarComponentes();
		repaint();
		setVisible(true);
	}

	private void adicionarComponentes() {
		painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));

		JPanel painelLabelECampo = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel nome = new JLabel("Nome do treino");
		nome.setFont(new Font("Calibri", Font.PLAIN, 17));
		campo = new JTextField(15);
		campo.setFont(new Font("Calibri", Font.PLAIN, 17));
		painelLabelECampo.add(nome);
		painelLabelECampo.add(campo);
		academia = persistencia.recuperar(academia.getArquivo());

		ArrayList<JCheckBox> itens = new ArrayList<>();
		JPanel painelCentralizar = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel painelJCheckBox = new JPanel(new GridLayout(0, 3, 5, 5));
		for (Exercicio exer : academia.getExercicios()) {
			check = new JCheckBox(exer.toString());
			check.setFont(new Font("Calibri", Font.PLAIN, 17));
			itens.add(check);
			painelJCheckBox.add(check);
		}

		JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
		btCadastrar = new JButton("Cadastrar treino");
		btCadastrar.setFont(new Font("Calibri", Font.PLAIN, 17));
		JButton btCancelar = new JButton("Cancelar");
		btCancelar.setFont(fonte);
		btCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		painelBotoes.add(btCadastrar);
		painelBotoes.add(btCancelar);
		academia = persistencia.recuperar(academia.getArquivo());

		JScrollPane scrol = new JScrollPane(painelJCheckBox);
		scrol.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		painelCentralizar.add(scrol);
		painel.add(painelLabelECampo);
		painel.add(painelCentralizar);
		painel.add(painelBotoes);

		manipulandoCheckBox(itens, this);
	}

	public void manipulandoCheckBox(ArrayList<JCheckBox> itens, JDialog dialog) {
		academia = persistencia.recuperar(academia.getArquivo());
		ArrayList<Exercicio> arrayTmp = new ArrayList<>();

		for (JCheckBox check : itens) {

			check.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					if (check.isSelected() && academia.obterTipoDoExercicio(check.getText()) == 1) {
						TelaAdicionalCadastrarTreinoAerobico telaAdicionalAerobico = new TelaAdicionalCadastrarTreinoAerobico(
								dialog);

						if (telaAdicionalAerobico.getClicouEmOk()) {
							exercicio = new Aerobico(check.getText(),
									Float.parseFloat(telaAdicionalAerobico.getCampoDuracao().getText()));

							arrayTmp.add(exercicio);

						} else {
							check.setSelected(false);
						}
					} else if (!check.isSelected()) {

						for (int i = 0; i < arrayTmp.size(); i++) {
							if (arrayTmp.get(i).getNome().equals(check.getText())) {
								arrayTmp.remove(i);
							}
						}
					}
					if (check.isSelected() && academia.obterTipoDoExercicio(check.getText()) == 2) {
						TelaAdicionalCadastroTreinoAnaerobico telaAdicionalAnaerobico = new TelaAdicionalCadastroTreinoAnaerobico(
								dialog);
						if (telaAdicionalAnaerobico.getClicouEmOk()) {
							exercicio = new Anaerobico(check.getText(),
									Integer.parseInt(telaAdicionalAnaerobico.getCampoQtdSeries().getText()),
									Integer.parseInt(telaAdicionalAnaerobico.getCampoQtdRepeticoes().getText()),
									academia.obterDuracao10Repeticoes(check.getText()));

							arrayTmp.add(exercicio);
						} else {
							check.setSelected(false);
						}
					} else if (!check.isSelected()) {
						for (int i = 0; i < arrayTmp.size(); i++) {
							if (arrayTmp.get(i).getNome().equals(check.getText())) {
								arrayTmp.remove(i);
							}
						}
					}
				}
			});
		}

		btCadastrar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				try {

					verificar.stringVazia(campo.getText());
					verificar.caracterInvalido(campo.getText());
					Treino treino = new Treino(campo.getText());
					for (int i = 0; i < arrayTmp.size(); i++) {
						treino.adicinarExercicio(arrayTmp.get(i));
					}
					try {
						if (arrayTmp.size() == 0) {
							throw new ArrayVazioException();
						} else {
							if (treino.duracao() <= 60) {
								academia.adicionarTreino(treino);
								if (persistencia.salvar(academia, academia.getArquivo())) {

									UIManager.put("OptionPane.messageFont", fonte);
									UIManager.put("OptionPane.buttonFont", fonte);
									JOptionPane.showMessageDialog(dialog, "Treino cadastrado com sucesso.",
											"Informa��o", JOptionPane.PLAIN_MESSAGE);
									Integer opcao = JOptionPane.showConfirmDialog(dialog,
											"Voc� ainda quer cadastrar algum treino?", "Pergunta",
											JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);

									if (opcao == 0) {
										dispose();
										new CadastrarTreino();
									} else {
										dispose();
									}

								}
							} else {

								UIManager.put("OptionPane.messageFont", fonte);
								UIManager.put("OptionPane.buttonFont", fonte);
								JOptionPane.showMessageDialog(dialog,
										"N�o vai ser possivel concluir o cadastro, seu treino ultrapassou 60 minutos.",
										"Erro", JOptionPane.PLAIN_MESSAGE);

							}
						}
					} catch (ArrayVazioException a) {
						UIManager.put("OptionPane.messageFont", fonte);
						UIManager.put("OptionPane.buttonFont", fonte);
						JOptionPane.showMessageDialog(dialog, "Selecione pelo menos um exerc�cio.", "Erro",
								JOptionPane.PLAIN_MESSAGE);
					}

				} catch (CampoVazioException c) {

					UIManager.put("OptionPane.messageFont", fonte);
					UIManager.put("OptionPane.buttonFont", fonte);
					JOptionPane.showMessageDialog(dialog, "Informe o nome do treino.", "Erro",
							JOptionPane.PLAIN_MESSAGE);

				} catch (CaracterInvalidoException c) {

					UIManager.put("OptionPane.messageFont", fonte);
					UIManager.put("OptionPane.buttonFont", fonte);
					JOptionPane.showMessageDialog(dialog, "Nome do treino inv�lido, informe apenas letras.", "Erro",
							JOptionPane.PLAIN_MESSAGE);

				}
			}
		});

	}

}