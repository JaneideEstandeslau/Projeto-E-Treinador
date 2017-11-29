package aluno;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import treinos.Academia;
import treinos.Aluno;
import treinos.Persistencia;

public class ObservadorBTCadastrarTreinos implements ActionListener{
	
	
	private TelaListrCadastrarTreino janela;
	
	public ObservadorBTCadastrarTreinos(TelaListrCadastrarTreino janela) {
		
		this.janela = janela;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		Persistencia persistencia = new Persistencia();
		Academia academia;
		
		//captura a linha selecionada
		
		if(janela.getTabela().getSelectedRow() == -1){
			JOptionPane.showMessageDialog(janela, "Por favor Selecione uma linha valida");
		}
		else {
			
			int select = janela.getTabela().getSelectedRow();
			academia = persistencia.recuperar("academia.xml");
			ArrayList<Aluno> alunos = academia.getAlunos();
			
			Aluno aluno = alunos.get(select);
			
			new CadastroDeTreinos(aluno);
			
			janela.dispose();
			
		}
		
	}

}
