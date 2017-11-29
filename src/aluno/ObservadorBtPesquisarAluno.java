package aluno;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Excecoes.CampoEmBranco;
import Excecoes.ValidacaoSistema;
import Excecoes.ValidarCPFException;
import treinos.Academia;
import treinos.Aluno;
import treinos.Persistencia;

public class ObservadorBtPesquisarAluno implements ActionListener {

	private JanelaTelaListagem t;
	private Persistencia persistencia = new Persistencia();
	private Academia academia;

	public ObservadorBtPesquisarAluno(JanelaTelaListagem t) {
		this.t = t;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		int cont = 0;

		ValidacaoSistema validacao = new ValidacaoSistema();
		String cpf = t.getTFPesquisa().getText();

		try {

			validacao.StringVazia(cpf);

			academia = persistencia.recuperar("academia.xml");

			ArrayList<Aluno> alunos = academia.getAlunos();
			ArrayList<Aluno> alunosEncontrados = new ArrayList<>();

			for (Aluno a : alunos) {
				if (a.getCPF().equals(cpf)) {
					alunosEncontrados.add(a);
				}
			}

			if (alunosEncontrados.size() >= 1) {

				// descreve as colunas que a tabela tera
				DefaultTableModel model = new DefaultTableModel();
				model.addColumn("Nome");
				model.addColumn("Telefone");
				model.addColumn("CPF");

				// adiciona as linhas da tabela em model
				for (Aluno al : alunosEncontrados) {
					Object[] linhas = new Object[3];
					linhas[0] = al.getNome();
					linhas[1] = al.getTelefone();
					linhas[2] = al.getCPF();

					model.addRow(linhas);
				}

				JTable tabela = new JTable(model);

				JScrollPane painel = new JScrollPane(tabela);

				painel.setBounds(35, 80, 500, 200);

				t.removerComponent(painel);
				t.add(painel);
				t.repaint();
			} else {
				JOptionPane.showMessageDialog(t, "Aluno não encontrado no sistema", "Informação",
						JOptionPane.ERROR_MESSAGE);
			}

		} catch (ValidarCPFException e1) {
			JOptionPane.showMessageDialog(t, e1.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}

	}

}
