package aluno;

import java.awt.Color;
import java.awt.Font;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import treinos.Academia;
import treinos.Aluno;
import treinos.Persistencia;

public class TelaVisualizarTreinos extends ClasseParaCriarJanela{
	
	private Font fonte = new Font("Calibri", Font.PLAIN, 17);
	private JTable tabela;
	private JScrollPane painel;
	private Persistencia persistencia = new Persistencia();
	private CriarTabela table = new CriarTabela();
	private JFormattedTextField TFPesquisa;
	
	public TelaVisualizarTreinos(){
		super("Alunos Cadastrados", 580, 380);
		this.getContentPane().setBackground(Color.WHITE);
		this.adicionarTabela();
		this.adicionarLabel();
		this.adicionarButton();
		this.adicionarPesquisador();
		this.repaint();
	}
	
	public void adicionarPesquisador() {
		
		JLabel l = new JLabel("Informe o CPF:");
		l.setBounds(50, 05, 200, 60);
		l.setFont(fonte);
		add(l);
		
		MaskFormatter mascaraDeCpf;
		try {
			mascaraDeCpf = new MaskFormatter("###.###.###-##");
			TFPesquisa = new JFormattedTextField(mascaraDeCpf);
			TFPesquisa.setBounds(195, 25, 200, 23);
			add(TFPesquisa);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void adicionarButton(){
		
		//cria bot�o concluir
		JButton botaoConcluir = new JButton("Visualizar");
		botaoConcluir.setFont(fonte);
		botaoConcluir.setBounds(350, 310, 120, 33);
		add(botaoConcluir);
		
		//define o ouvinte para o bot�o concluir
		botaoConcluir.addActionListener(new ObservadorVisualizarTreinos(this));
		
		JButton botaoPesquisar = new JButton("Pesquisar");
		botaoPesquisar.setFont(fonte);
		botaoPesquisar.setBounds(410, 23, 120, 27);
		add(botaoPesquisar);

		botaoPesquisar.addActionListener(new ObservadorPVisualizarTreinos(this));
		
		
	}
	
	public void adicionarLabel(){
		this.criarLabol("E-Treinador", 480, 310, 100, 30);
	}
	public void adicionarTabela(){
		
		Persistencia persistencia = new Persistencia();
		//recupera a academia
		Academia academia = persistencia.recuperar("academia.xml");
		
		ArrayList<Aluno> alunos = academia.getAlunos();
		
		//descreve as colunas que a tabela tera
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Nome");
		model.addColumn("Telefone");
		model.addColumn("CPF");
		
		//adiciona as linhas da tabela em model
		for(Aluno al: alunos){
			Object[] linhas = new Object[3];
			linhas[0] = al.getNome();
			linhas[1] = al.getTelefone();
			linhas[2] = al.getCPF();
			
			model.addRow(linhas);
		}
		
		tabela = new JTable(model);
		
		painel = new JScrollPane(tabela);
		
		painel.setBounds(35, 80, 500, 200);
		
		add(painel);
		
	}
	
	public void removerComponent(JScrollPane painel) {
		this.remove(this.painel);
		this.painel = painel;
	}
	
	public JTable getTabela() {
		return tabela;
	}

	public void setTabela(JTable tabela) {
		this.tabela = tabela;
	}

	public Font getFonte() {
		return fonte;
	}

	public void setFonte(Font fonte) {
		this.fonte = fonte;
	}

	public Persistencia getPersistencia() {
		return persistencia;
	}

	public void setPersistencia(Persistencia persistencia) {
		this.persistencia = persistencia;
	}

	public CriarTabela getTable() {
		return table;
	}

	public void setTable(CriarTabela table) {
		this.table = table;
	}

	public JFormattedTextField getTFPesquisa() {
		return TFPesquisa;
	}

	public void setTFPesquisa(JFormattedTextField tFPesquisa) {
		TFPesquisa = tFPesquisa;
	}
	
	
}
