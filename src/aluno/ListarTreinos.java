package aluno;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import treinos.Academia;
import treinos.Aluno;
import treinos.Persistencia;
import treinos.Treino;

public class ListarTreinos extends ClasseParaCriarJanela implements ActionListener{
	
	private Font fonte = new Font("Calibri", Font.PLAIN, 17);
	private int indice;
	private JTable tabela;
	Persistencia persistencia = new Persistencia();
	
	public ListarTreinos(int indice){
		super("Teinos da semana", 580, 380);
		this.indice = indice;
		this.getContentPane().setBackground(Color.WHITE);
		this.adicionarLabel();
		this.button();
		this.adicionarTabela(this);
	}
	
	public void button(){
		
		//direciona para tela de edi��o de treinos
		JButton editarTreino = new JButton("Concluir");
		editarTreino.setFont(fonte);
		editarTreino.setBounds(350, 310, 120, 30);
		add(editarTreino);
		
		editarTreino.addActionListener(this);
	}
	
	//adiciona o label � treinador
	public void adicionarLabel(){
		this.criarLabol("E-Treinador", 480, 310, 100, 30);
	}
	
	//adiciona a tabela
	private void adicionarTabela(JDialog frame){
		Academia academia = persistencia.recuperar("academia.xml");
		
		ArrayList<Aluno> alunos = academia.getAlunos();
		Treino[] treinos = alunos.get(indice).getTreinosDoAluno();
		
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("");
		model.addColumn("Nome do Treino");
		model.addColumn("Duração");
		
		String[] diasDaSemana = {"Domingo","Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sabado"};
		int cont = 0;
		
		for(Treino t: treinos){
			
			Object[] linhas = new Object[3];
			
			if(t == null || t.getNomeDoTreino().equals("Não Treinar")){
				linhas[0] = diasDaSemana[cont++];
				linhas[1] = "Descanso";
				linhas[2] = "Descanso";
			}
			else{
				linhas[0] = diasDaSemana[cont++];
				linhas[1] = t.getNomeDoTreino();
				linhas[2] = t.duracao();
			}
			
			model.addRow(linhas);
			
			tabela = new JTable(model);
			
			JScrollPane painel = new JScrollPane(tabela);
			
			painel.setBounds(35, 20, 500, 200);
			
			add(painel);
		}
	}
	public int getJanela() {
		return indice;
	}
	public void setJanela(int janela) {
		this.indice = janela;
	}
	public JTable getTabela() {
		return tabela;
	}
	public void setTabela(JTable tabela) {
		this.tabela = tabela;
	}
	public Persistencia getPersistencia() {
		return persistencia;
	}
	public void setPersistencia(Persistencia persistencia) {
		this.persistencia = persistencia;
	}

	public void actionPerformed(ActionEvent e) {
		this.dispose();
//		new TelaEdicaoTreinoDoAluno(indice);
	}

}
