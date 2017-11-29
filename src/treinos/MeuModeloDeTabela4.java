package treinos;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class MeuModeloDeTabela4 extends AbstractTableModel {
	private ArrayList<Treino> linhas;
	private String colunas[] = {
		"Treinos que contém o exercício informado"	
	};
	
	public MeuModeloDeTabela4(ArrayList<Treino> linhas) {
		this.linhas = linhas;
	}
	
	public int getColumnCount() {
		return colunas.length;
	}

	public int getRowCount() {
		return linhas.size();
	}
	
	public Object getValueAt(int linha, int coluna) throws NullPointerException {
		Treino treino = linhas.get(linha);
		
		if(coluna == 0){
			return treino.getNomeDoTreino();
		}else{
			return null;
		}
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	
	public String getColumnName(int column) {
		return colunas[column];
	}
}
