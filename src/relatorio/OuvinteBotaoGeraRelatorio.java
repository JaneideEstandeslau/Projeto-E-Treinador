package relatorio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class OuvinteBotaoGeraRelatorio implements ActionListener{

	
	private GeraRelatorio janela;
	
	public OuvinteBotaoGeraRelatorio(GeraRelatorio janela){
		this.janela = janela;
	}

	public void actionPerformed(ActionEvent e) {

		Relatorio relatorio = new Relatorio();
		
		int select = janela.getTabela().getSelectedRow();
		
		if(select == -1){
			JOptionPane.showMessageDialog(janela, "Selecione um aluno valido");
		}
		else{
			
			relatorio.gerarRelatorio(select);
			JOptionPane.showMessageDialog(janela, "Relatorio concluido");
			janela.dispose();
		}
	}

}
