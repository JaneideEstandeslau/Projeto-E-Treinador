package aluno;


import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JLabel;

public class ClasseParaCriarJanela extends JDialog{
	
	private int x;
	private int y;
	private String titulo;
	private Font fonte = new Font("Calibri", Font.PLAIN, 17);
	public ClasseParaCriarJanela(String titulo, int x, int y){
		this.titulo = titulo;
		this.x = x;
		this.y = y;
		this.janela();
	}

	public void janela(){
		this.setTitle(titulo);
		this.setSize(x, y);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
	}
	
	public void criarLabol(String titulo, int x, int y, int w, int h){
		JLabel idade = new JLabel(titulo);
		idade.setBounds(x, y, w, h);
		idade.setFont(fonte);
		add(idade);
	}
}
