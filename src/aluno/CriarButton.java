package aluno;


import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;

public class CriarButton{
	
	public JButton adicionarJButton(String nome, int x, int y, int w, int h, JDialog frame){
		JButton botao = new JButton(nome);
		botao.setFont(new Font("Calibri", Font.PLAIN, 17));
		botao.setBounds(x, y, w, h);
		frame.add(botao);
		return botao;
	}
}
