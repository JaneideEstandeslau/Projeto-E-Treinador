package aluno;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import treinos.Academia;
import treinos.Aluno;
import treinos.Persistencia;


public class EditarAluno extends ClasseParaCriarJanela implements ActionListener{
	
	private Font fonte = new Font("Calibri", Font.PLAIN, 17);
	private  JTable tabela;
	Persistencia persistencia = new Persistencia();
	CriarTabela table = new CriarTabela();
	CriarButton button = new CriarButton();
	private JScrollPane painel;
	private JFormattedTextField TFPesquisa;
	
	public EditarAluno(){
		super("Alunos Cadastrados", 580, 380);
		this.getContentPane().setBackground(Color.WHITE);
		this.adicionarTabela();
		this.adicionarButton();
		this.adicionarPesquisador();
		this.repaint();
	}
	
	public void adicionarPesquisador() {

		JLabel l = new JLabel("Informe o CPF:");
		l.setBounds(40, 05, 200, 60);
		l.setFont(fonte);
		add(l);

		MaskFormatter mascaraDeCpf;
		try {
			mascaraDeCpf = new MaskFormatter("###.###.###-##");
			TFPesquisa = new JFormattedTextField(mascaraDeCpf);
			TFPesquisa.setBounds(190, 25, 200, 23);
			add(TFPesquisa);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	public void adicionarButton(){
		
		JButton botaoPesquisar = new JButton("Pesquisar");
		botaoPesquisar.setFont(fonte);
		botaoPesquisar.setBounds(415, 23, 120, 27);
		add(botaoPesquisar);
		botaoPesquisar.addActionListener(new ObservadorpEditarAluno(this));
		
		this.criarLabol("E-Treinador", 480, 310, 100, 30);
		
		JButton botaoaCancelar = new JButton("Cancelar");
		botaoaCancelar.setBounds(230, 310, 110, 34);
		botaoaCancelar.setFont(fonte);
		add(botaoaCancelar);
		
		botaoaCancelar.addActionListener(this);
		
		//editar
		JButton botaoEdittar = new JButton("Editar");
		botaoEdittar.setBounds(350, 310, 110, 34);
		botaoEdittar.setFont(fonte);
		add(botaoEdittar);
		
		botaoEdittar.addActionListener(new OuvinteEditarDaTelaEdicaoDoAluno(this));
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

	
	public void actionPerformed(ActionEvent arg0) {
		 this.dispose();
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

	public CriarButton getButton() {
		return button;
	}

	public void setButton(CriarButton button) {
		this.button = button;
	}

	public JScrollPane getPainel() {
		return painel;
	}

	public void setPainel(JScrollPane painel) {
		this.painel = painel;
	}

	public JFormattedTextField getTFPesquisa() {
		return TFPesquisa;
	}

	public void setTFPesquisa(JFormattedTextField tFPesquisa) {
		TFPesquisa = tFPesquisa;
	}
	
	
}
