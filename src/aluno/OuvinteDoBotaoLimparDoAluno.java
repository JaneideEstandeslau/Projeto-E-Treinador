package aluno;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OuvinteDoBotaoLimparDoAluno implements ActionListener{

	private CadastroDEAluno janela;
	
	public OuvinteDoBotaoLimparDoAluno(CadastroDEAluno janela){
		this.janela = janela;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		janela.getTFnome().setText("");
		janela.getTFidade().setText("");
		janela.getTFcpf().setText("");
		janela.getTFidentidade().setText("");
		janela.getTFemail().setText("");
		janela.getTFendereco().setText("");
		janela.getTFtelefone().setText("");
	}
}
