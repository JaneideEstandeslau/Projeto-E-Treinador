package treinos;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class MeuModeloDeTabela2 extends AbstractTableModel {

	String coluna[] = {"Nome do exercício","Grupo Muscular"};
	ArrayList<Exercicio> linhas;
	
	public MeuModeloDeTabela2(ArrayList<Exercicio> linhas) {
		this.linhas = linhas;
	}
	
	public int getColumnCount() {
		return coluna.length;
	}

	@Override
	public int getRowCount() {
		return linhas.size();
	}

	@Override
	public String getColumnName(int column) {
		return coluna[column];
	}
	
	@Override
	public Object getValueAt(int l, int c){
		Exercicio e = linhas.get(l);
		
		if(c == 0)
			return e.getNome();
		else if(c == 1){
			if(e instanceof Anaerobico){
				return ((Anaerobico) e).getGrupoMuscular();
			}
		}else{
			throw new IndexOutOfBoundsException("Coluna inválida");
		}
		return null;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
}
