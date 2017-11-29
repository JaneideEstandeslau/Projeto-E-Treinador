package treinos;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class MeuModeloDeTabela5 extends AbstractTableModel {

	String coluna[] = {"Alunos usando o treino selecionado"};
	ArrayList<Aluno> linhas;
	
	public MeuModeloDeTabela5(ArrayList<Aluno> linhas) {
		this.linhas = linhas;
	}
	
	public int getColumnCount() {
		return coluna.length;
	}

	public int getRowCount() {
		return linhas.size();
	}

	public String getColumnName(int column) {
		return coluna[column];
	}
	
	public Aluno getValueAt(int linha, int coluna){
		Aluno a = linhas.get(linha);
		
		if(coluna == 0)
			return a;
		return a;
		
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
}
