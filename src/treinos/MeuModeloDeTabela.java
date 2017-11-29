package treinos;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class MeuModeloDeTabela extends AbstractTableModel {
	private String[] coluna = {"Nome do exercício", "Tipo do exercício"};
	private ArrayList<Exercicio> exercicios;

	public MeuModeloDeTabela(ArrayList<Exercicio> exercicios) {
		this.exercicios = exercicios;
	}
	
	@Override
	public int getColumnCount() {
		return coluna.length;
	}

	@Override
	public int getRowCount() {
		return exercicios.size();
	}

	public void removerLinha(int linha){
		exercicios.remove(linha);
	}
	
	public String obterNomeLinha(Integer linhaSelecionada){
		return exercicios.get(linhaSelecionada).getNome();
	}
	
	@Override
	public Object getValueAt(int linha, int coluna) {
		Exercicio exercicio = exercicios.get(linha);
		
		if(coluna == 0){
			return exercicio.getNome();
		}else if(coluna == 1){
			return exercicio.getTipo();
		}else{
			throw new IndexOutOfBoundsException();
		}
	}

	@Override
	public String getColumnName(int column) {
		return coluna[column];
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
}
