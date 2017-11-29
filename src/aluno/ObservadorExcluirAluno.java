package aluno;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import treinos.Academia;
import treinos.Aluno;
import treinos.Persistencia;

public class ObservadorExcluirAluno implements ActionListener{
	
	private TelaExcluirAluno janela;

	public ObservadorExcluirAluno(TelaExcluirAluno janela){
		this.janela = janela;
	}
	public void actionPerformed(ActionEvent e) {
		
		Persistencia persistencia = new Persistencia();
		Academia academia;
		
		//captura a linha selecionada
		int select = janela.getTabela().getSelectedRow();
		
		if(select == -1){
			JOptionPane.showMessageDialog(janela, "Por favor Selecione uma linha valida");
		}
		
		else{
			academia = persistencia.recuperar("academia.xml");
			ArrayList<Aluno> alunos = academia.getAlunos();
			
			alunos.remove(select);
			
			persistencia.salvar(academia, "academia.xml");
			
			DefaultTableModel modelo = (DefaultTableModel) janela.getTabela().getModel();
			modelo.removeRow(select);
			janela.getTabela().repaint();
		}
		
	}

}
