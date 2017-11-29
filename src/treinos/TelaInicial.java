package treinos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import aluno.CadastroDEAluno;
import aluno.EditarAluno;
import aluno.JanelaTelaListagem;
import aluno.TelaEdicaoDoAluno;
import aluno.TelaEdicaoTreinoDoAluno;
import aluno.TelaExcluirAluno;
import aluno.TelaListarAlunoParaEdicaoDoTreino;
import aluno.TelaListrCadastrarTreino;
import aluno.TelaVisualizarTreinos;
import relatorio.EnviarEmailAlunoEspecifico;
import relatorio.GeraRelatorio;
import relatorio.TelaEnviarEmail;

public class TelaInicial extends JFrame {
	
	
	private ImageIcon iconeErro = new ImageIcon("src/icones/erro.png");
	private Font fonte = new Font("Calibri", Font.PLAIN, 17);

	public TelaInicial() {
	 // setExtendedState(JFrame.MAXIMIZED_VERT);
//		setUndecorated(true);
//		setBackground(new Color(1.0f,1.0f,1.0f,0.5f));
		setSize(820, 650);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		adicionarComponentes();
		setTitle("E-Treinador");
		lookAndFeel(this);
		imagemFundo();
		repaint();
		setVisible(true);
		setResizable(false);
	 }

	public void lookAndFeel(JFrame frame) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(this);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			JOptionPane.showMessageDialog(frame,
					"Ocorreu um erro interno, entre em contato com o desenvolvedor do sistema.", "Erro",
					JOptionPane.PLAIN_MESSAGE, iconeErro);
		}
	}

	private void adicionarComponentes() {
		JMenuBar barraMenu = new JMenuBar();
		JMenu exercicio = new JMenu("Exercício");
		JMenu cadastrarExercicio = new JMenu("Cadastrar Exercício");
		JMenuItem cadastrarAerobico = new JMenuItem("Aeróbico");
		JMenuItem cadastrarAnaerobico = new JMenuItem("Anaeróbico");
		barraMenu.add(exercicio);
		exercicio.add(cadastrarExercicio);
		cadastrarExercicio.add(cadastrarAerobico);
		cadastrarExercicio.add(cadastrarAnaerobico);
		exercicio.setFont(fonte);
		cadastrarExercicio.setFont(fonte);
		cadastrarAerobico.setFont(fonte);
		cadastrarAnaerobico.setFont(fonte);
		JMenu listar = new JMenu("Listar Exercícios Cadastrados");
		JMenuItem todos = new JMenuItem("Todos");
		listar.add(todos);
		JMenuItem somenteAerobico = new JMenuItem("Somente Aeróbicos");
		JMenuItem somenteAnaerobico = new JMenuItem("Somente Anaeróbicos");
		listar.add(somenteAerobico);
		somenteAnaerobico.setFont(fonte);
		listar.add(somenteAnaerobico);
		somenteAerobico.setFont(fonte);
		todos.setFont(fonte);
		listar.setFont(fonte);
		JMenuItem filtrarPorGrupo = new JMenuItem("Filtrar Por Grupo Muscular");
		filtrarPorGrupo.setFont(fonte);
		listar.add(filtrarPorGrupo);
		exercicio.add(listar);
		cadastrarAerobico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaCadastroExercicioAerobico();
			}
		});
		cadastrarAnaerobico.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new TelaCadastroExercicioAnaerobico();
			}
		});
		todos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new TabelaTodosExercicios();
			}
		});
		somenteAerobico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TabelaSomenteAerobico();
			}
		});
		somenteAnaerobico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TabelaSomenteAnaerobico();
			}
		});
		filtrarPorGrupo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TabelaFiltrarGrupoMuscular();
			}
		});
		JMenuItem excluirExercicio = new JMenuItem("Excluir exercício");
		excluirExercicio.setFont(fonte);
		exercicio.add(excluirExercicio);
		JMenu treino = new JMenu("Treino");
		treino.setFont(fonte);
		barraMenu.add(treino);
		excluirExercicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ExcluirExercicio();
			}
		});
		JMenuItem cadastrarTreino = new JMenuItem("Cadastrar treino");
		cadastrarTreino.setFont(fonte);
		cadastrarTreino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CadastrarTreino();
			}
		});
		JMenuItem editarTreino = new JMenuItem("Editar treino");
		editarTreino.setFont(fonte);
		editarTreino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new EditarTreino();
			}
		});
		treino.add(cadastrarTreino);
		treino.add(editarTreino);
		JMenuItem excluirTreino = new JMenuItem("Excluir treino");
		excluirTreino.setFont(fonte);
		excluirTreino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ExcluirTreino();
			}
		});
		treino.add(excluirTreino);
		JMenu listarTreinos = new JMenu("Listar treinos");
		listarTreinos.setFont(fonte);
		JMenuItem listarTodosTreinos = new JMenuItem("Todos os treinos");
		listarTodosTreinos.setFont(fonte);
		listarTodosTreinos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new TabelaTreinosCadastrados();
			}
		});
		JMenuItem listarTreinosComExerciciosEspecificos = new JMenuItem(
				"Listar os treinos que possuem um exercício específico");
		listarTreinosComExerciciosEspecificos.setFont(fonte);
		listarTreinosComExerciciosEspecificos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TabelaTreinosComExerciciosEspecificos();
			}
		});
		listarTreinos.add(listarTodosTreinos);
		listarTreinos.add(listarTreinosComExerciciosEspecificos);
		treino.add(listarTreinos);
		JMenuItem detalharTreino = new JMenuItem("Detalhar treino");
		detalharTreino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new DetalharTreino();
			}
		});
		detalharTreino.setFont(fonte);
		treino.add(detalharTreino);
		JMenu aluno = new JMenu("Aluno");
		aluno.setFont(fonte);
		JMenuItem cadastrarAluno = new JMenuItem("Cadastrar aluno");
		cadastrarAluno.setFont(fonte);
		aluno.add(cadastrarAluno);
		cadastrarAluno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new CadastroDEAluno();
			}
		});
		barraMenu.add(aluno);
		JMenuItem excluirAluno = new JMenuItem("Excluir Aluno");
		excluirAluno.setFont(fonte);
		excluirAluno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaExcluirAluno();
			}
		});
		aluno.add(excluirAluno);
		
		JMenuItem listarAlunos = new JMenuItem("Visualizar alunos");
		listarAlunos.setFont(fonte);
		listarAlunos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new JanelaTelaListagem();
			}
		});
		aluno.add(listarAlunos);
//		JMenuItem relatorio = new JMenuItem("Gerar relatório");
//		relatorio.setFont(fonte);
//		relatorio.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//
//			}
//		});
//		aluno.add(relatorio);
		JMenuItem editarAluno = new JMenuItem("Editar credenciais aluno");
		editarAluno.setFont(fonte);
		editarAluno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new EditarAluno();
			}
		});
		aluno.add(editarAluno);
		
		JMenuItem cadastrarTreinoAluno = new JMenuItem("Cadastrar treino do aluno");
		cadastrarTreinoAluno.setFont(fonte);
		cadastrarTreinoAluno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new TelaListrCadastrarTreino();
			}
		});
		aluno.add(cadastrarTreinoAluno);
		
		JMenuItem listarAlunoEditarTreino = new JMenuItem("Editar treino do Aluno");
		listarAlunoEditarTreino.setFont(fonte);
		listarAlunoEditarTreino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaListarAlunoParaEdicaoDoTreino();
			}
		});
		aluno.add(listarAlunoEditarTreino);
		JMenuItem listarTreino = new JMenuItem("Visualizar treino do Aluno");
		listarTreino.setFont(fonte);
		listarTreino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaVisualizarTreinos();
				
			}
		});
		aluno.add(listarTreino);
		barraMenu.add(aluno);
		
		JMenu gerenciamento = new JMenu("Gerenciamento");
		gerenciamento.setFont(fonte);
		
		JMenuItem enviarEmail = new JMenuItem("Enviar e-mail a todos os alunos");
		enviarEmail.setFont(fonte);
		gerenciamento.add(enviarEmail);
		enviarEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaEnviarEmail();
				
			}
		});
		
		JMenuItem enviarEmailAluno = new JMenuItem("Enviar e-mail a um aluno específico");
		enviarEmailAluno.setFont(fonte);
		gerenciamento.add(enviarEmailAluno);
		enviarEmailAluno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new EnviarEmailAlunoEspecifico();
				
			}
		});
		
		JMenuItem gerarRelatorio = new JMenuItem("Gerar relatório com os treinos do aluno");
		gerarRelatorio.setFont(fonte);
		gerarRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GeraRelatorio();
				
			}
		});
		gerenciamento.add(gerarRelatorio);
		
		
		barraMenu.add(gerenciamento);
		setJMenuBar(barraMenu);
	}

	public void imagemFundo() {

//		 o caminho da imagem é passado na instância do objeto:
//		 lembre-se de passar o caminho da sua imagem no disco, porque, caso contrário,
//		 vai dar erro na execução
		JPanelWithBackground background = new JPanelWithBackground("imagemFundo (2).jpg");
//		background.setSize(0, 0);
		getContentPane().add(background);
		
	}
}
