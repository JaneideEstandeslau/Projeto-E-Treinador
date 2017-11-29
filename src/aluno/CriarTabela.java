package aluno;


import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import treinos.Academia;
import treinos.Aluno;
import treinos.Persistencia;



public class CriarTabela {
	
	private JTable tabela;
	
	public  JTable adicionarTabela(JDialog frame){
		
		Persistencia persistencia = new Persistencia();
		//recupera a academia
		Academia academia = persistencia.recuperar("academia.xml");
		
		ArrayList<Aluno> alunos = academia.getAlunos();
		
		//descreve as colunas que a tabela tera
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Nome");
		model.addColumn("Telefone");
		model.addColumn("CPF");
		
		//adiciona as linhas da tabela em model
		for(Aluno al: alunos){
			Object[] linhas = new Object[3];
			linhas[0] = al.getNome();
			linhas[1] = al.getTelefone();
			linhas[2] = al.getCPF();
			
			model.addRow(linhas);
		}
		
		tabela = new JTable(model);
		
		JScrollPane painel = new JScrollPane(tabela);
		
		painel.setBounds(35, 80, 500, 200);
		
		frame.add(painel);
		
		
		return tabela;
	}

	public JTable getTabela() {
		return tabela;
	}

	public void setTabela(JTable tabela) {
		this.tabela = tabela;
	}
	
	
}
