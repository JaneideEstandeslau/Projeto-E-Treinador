package treinos;

import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import Excecoes.CampoVazioException;
import Excecoes.CaracterInvalidoException;
import Excecoes.NumeroIgualAZeroException;
import Excecoes.SelecioneUmValorNoSpinnerException;

public class EditarTreino extends JDialog {
	private Academia academia = new Academia();
	private Persistencia persistencia = new Persistencia();
	private Font fonte = new Font("Calibri", Font.PLAIN, 17);
	private JLabel novoNomeLabel = null;
	private JTextField campoNovoNome = null;
	private Exercicio exercicio;
//	private ImageIcon erro = new ImageIcon(getClass().getResource("ProjetoLuiz.icones/erro.png"));
//	private ImageIcon exclamacao = new ImageIcon(getClass().getResource("ProjetoLuiz.icones/exclamacao.png"));
//	private ImageIcon interrogacao = new ImageIcon(getClass().getResource("icones/interrogacao.png"));
//	private ImageIcon info = new ImageIcon(getClass().getResource("ProjetoLuiz.icones/info.png"));
//	private ImageIcon salvar = new ImageIcon(getClass().getResource("ProjetoLuiz.icones/save.png"));
//	private ImageIcon cancelar = new ImageIcon(getClass().getResource("ProjetoLuiz.icones/cancelar.png"));
	private Verificacao verificar = new Verificacao();
	public EditarTreino() {
		setSize(580, 380);
		setTitle("Editar treino");
		setResizable(false);
		adicionarComponentes(this);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setModal(true);
		setLocationRelativeTo(null);
		repaint();
		setVisible(true);
	}

	public void adicionarComponentes(JDialog dialog) {
		JPanel painel = (JPanel) getContentPane();
		painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
		
		painel.setBackground(Color.WHITE);

		academia = persistencia.recuperar(academia.getArquivo());

		ArrayList<Object> cadastrados = new ArrayList<>();
		cadastrados.add(0, "");
		for (int i = 0; i < academia.getTreinos().size(); i++) {
			cadastrados.add(academia.getTreinos().get(i));
		}
		JLabel labelNomeTreino = new JLabel("Selecione o treino que você deseja editar");
		labelNomeTreino.setFont(fonte);
		JComboBox<Object> comboBoxTreinos = new JComboBox<>(cadastrados.toArray());
		comboBoxTreinos.setFont(fonte);
		JPanel painelComboBox = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		painelComboBox.setBackground(Color.WHITE);
		
		painelComboBox.add(labelNomeTreino);
		painelComboBox.add(comboBoxTreinos);
		painel.add(painelComboBox);
		JPanel painelNovoNome = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		painelNovoNome.setBackground(Color.WHITE);
		
		novoNomeLabel = new JLabel("Novo nome");
		campoNovoNome = new JTextField();
		novoNomeLabel.setFont(fonte);
		campoNovoNome.setFont(fonte);
		campoNovoNome.setPreferredSize(new Dimension(200, 24));
		painelNovoNome.add(novoNomeLabel);
		painelNovoNome.add(campoNovoNome);
		painelNovoNome.setVisible(false);
		painel.add(painelNovoNome);
		JPanel painelAgrupar = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel painelCheckBox = new JPanel(new GridLayout(0, 3, 5, 5));
		JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton botaoSalvar = new JButton("Salvar");
		JButton botaoCancelar = new JButton("Cancelar");
		
		painelAgrupar.setBackground(Color.WHITE);
		painelCheckBox.setBackground(Color.WHITE);
		painelBotoes.setBackground(Color.WHITE);
		
		botaoCancelar.setFont(fonte);
		botaoCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		botaoSalvar.setFont(fonte);
		painelBotoes.add(botaoSalvar);
		painelBotoes.add(botaoCancelar);
		painelBotoes.setVisible(false);
		ArrayList<JCheckBox> todos = new ArrayList<>();
		comboBoxTreinos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer indice = comboBoxTreinos.getSelectedIndex();
				todos.clear();
				painelNovoNome.setVisible(true);
				painelCheckBox.removeAll();
				painelAgrupar.removeAll();
				painelCheckBox.setVisible(true);
				painelBotoes.setVisible(true);
				if (indice != 0) {
					campoNovoNome.setText(academia.getTreinos().get(indice -1).getNomeDoTreino());

					for (int i = 0; i < academia.getExercicios().size(); i++) {

						if (academia.exercicioNoTreino(indice - 1, academia.getExercicios().get(i).getNome())) {

							JCheckBox check = new JCheckBox(academia.getExercicios().get(i).getNome(), true);
							todos.add(check);

						} else {

							JCheckBox check = new JCheckBox(academia.getExercicios().get(i).getNome(), false);
							todos.add(check);
						}
					}

					for (JCheckBox c : todos) {
						c.setFont(fonte);
						painelCheckBox.add(c);

					}
					JScrollPane scrol = new JScrollPane(painelCheckBox);
					painelAgrupar.add(scrol);
					painel.add(painelAgrupar);
					painel.add(painelBotoes);
					painel.repaint();
					painel.revalidate();

				} else {
					painelNovoNome.setVisible(false);
					painelCheckBox.setVisible(false);
					painelBotoes.setVisible(false);
				}
				ArrayList<Exercicio> arrayTmp = new ArrayList<>();

				for (JCheckBox c : todos) {

					c.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							if (c.isSelected() && academia.obterTipoDoExercicio(c.getText()) == 1) {
								TelaAdicionalCadastrarTreinoAerobico tela = new TelaAdicionalCadastrarTreinoAerobico(dialog);
								if (tela.getClicouEmOk()) {
									try{
										
										Float duracao = Float.parseFloat(tela.getCampoDuracao().getText());
										verificar.igualAZeroFloat(duracao);
										exercicio = new Aerobico(c.getText(), duracao);
										arrayTmp.add(exercicio);
										
									}catch(NumberFormatException n){
										
										UIManager.put("OptionPane.messageFont", fonte);
										UIManager.put("OptionPane.buttonFont", fonte);
										JOptionPane.showMessageDialog(dialog, "Informe apenas números", "Erro", JOptionPane.PLAIN_MESSAGE);
										
									}catch(NumeroIgualAZeroException n){
										
										UIManager.put("OptionPane.messageFont", fonte);
										UIManager.put("OptionPane.buttonFont", fonte);
										JOptionPane.showMessageDialog(dialog, "Informe uma duração maior que zero", "Erro", JOptionPane.PLAIN_MESSAGE);
									
									}
								} else {
									c.setSelected(false);
								}
							} else if (c.isSelected() && academia.obterTipoDoExercicio(c.getText()) == 2) {
								TelaAdicionalCadastroTreinoAnaerobico tela = new TelaAdicionalCadastroTreinoAnaerobico(dialog);
								if(tela.getClicouEmOk()){
									try{
										
										Integer qtdSeries = Integer.parseInt(tela.getCampoQtdSeries().getText());
										Integer qtdRepeticoes = Integer.parseInt(tela.getCampoQtdRepeticoes().getText());
										verificar.igualAZero(qtdSeries);
										verificar.igualAZero(qtdRepeticoes);
										exercicio = new Anaerobico(c.getText(), qtdSeries, qtdRepeticoes, academia.obterDuracao10Repeticoes(c.getText()));
										arrayTmp.add(exercicio);
									}catch(NumberFormatException n){
										
										UIManager.put("OptionPane.messageFont", fonte);
										UIManager.put("OptionPane.buttonFont", fonte);
										JOptionPane.showMessageDialog(dialog, "Informe apenas números.", "Erro", JOptionPane.PLAIN_MESSAGE);
										
									}catch(SelecioneUmValorNoSpinnerException s){
										
										UIManager.put("OptionPane.messageFont", fonte);
										UIManager.put("OptionPane.buttonFont", fonte);
										JOptionPane.showMessageDialog(dialog, "A quantidade de séries e a quantidade de repetições deve ser igual a zero.", "Erro", JOptionPane.PLAIN_MESSAGE);
										
									}
								}else{
									c.setSelected(false);
								}
							} else {
								UIManager.put("OptionPane.messageFont", fonte);
								UIManager.put("OptionPane.buttonFont", fonte);
								Integer x = JOptionPane.showConfirmDialog(dialog,"Ao desmarcar um exercício ele é removido do treino, você deseja fazer isso ?","Atenção", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
								if (x == 0) {
									boolean aux = false;
									for(int i = 0; i < arrayTmp.size(); i++){
										if(c.getText().equals(arrayTmp.get(i).getNome())){
											aux  = true;
											arrayTmp.remove(i);
										}
									}
									if(aux == false){
										academia.getTreinos().get(indice -1).getExerciciosDoTreino().remove(academia.obterIndiceDeExercicioNoTreino(indice -1, c.getText()));
										persistencia.salvar(academia, academia.getArquivo());										
									}
									c.setSelected(false);
								} else {
									c.setSelected(true);
								}
							}
						}
					});

				}
				botaoSalvar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try{
							verificar.stringVazia(campoNovoNome.getText());
							verificar.caracterInvalido(campoNovoNome.getText());
							for(Exercicio exer : arrayTmp){
								academia.getTreinos().get(indice -1).getExerciciosDoTreino().add(exer);
							}
							if(academia.getTreinos().get(indice -1).duracao() <= 60){
								academia.getTreinos().get(indice -1).setNomeDoTreino(campoNovoNome.getText());
								
								if(persistencia.salvar(academia, academia.getArquivo())){
									
									UIManager.put("OptionPane.messageFont", fonte);
									UIManager.put("OptionPane.buttonFont", fonte);
									JOptionPane.showConfirmDialog(null,"O treino foi editado com sucesso.","Informação", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
									int opcao = JOptionPane.showConfirmDialog(dialog, "Vocâ ainda deseja editar mais algum treino?", "Pergunta", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
									if(opcao == 0){
										dispose();
										new EditarTreino();
									}else{
										dispose();
									}
								}
								
							}else{
								UIManager.put("OptionPane.messageFont", fonte);
								UIManager.put("OptionPane.buttonFont", fonte);
								JOptionPane.showConfirmDialog(null,"Seu treino está ultrapassando 60 minutos, não vai ser possivel concluir a edição.","Erro", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
							}
						}catch(CampoVazioException c){
							UIManager.put("OptionPane.messageFont", fonte);
							UIManager.put("OptionPane.buttonFont", fonte);
							JOptionPane.showConfirmDialog(null,"Informe o nome do treino.","Erro", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
						}catch(CaracterInvalidoException c){
							UIManager.put("OptionPane.messageFont", fonte);
							UIManager.put("OptionPane.buttonFont", fonte);
							JOptionPane.showConfirmDialog(null,"O nome do treino informado não é válido.","Erro", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
						}
					}
				});
			}
		});
	}
}
