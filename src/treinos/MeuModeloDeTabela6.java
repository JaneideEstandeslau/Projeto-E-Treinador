package treinos;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class MeuModeloDeTabela6 extends AbstractTableModel {

	String coluna[] = {"Exercícios que compôem o treino"};
	ArrayList<Exercicio> linhas;
	
	public MeuModeloDeTabela6(ArrayList<Exercicio> linhas) {
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
	
	public Exercicio getValueAt(int linha, int coluna){
		Exercicio e = linhas.get(linha);
		
		if(coluna == 0)
			return e;
		return e;
		
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
}
