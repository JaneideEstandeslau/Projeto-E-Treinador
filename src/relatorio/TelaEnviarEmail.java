package relatorio;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;
import javax.swing.text.MaskFormatter;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import Excecoes.CampoVazioException;
import Excecoes.ValidacaoSistema;
import aluno.ClasseParaCriarJanela;
import aluno.OuvinteDoBTVoltarCadastroAluno;
import aluno.OuvinteDoBotaoCadastrar;
import aluno.OuvinteDoBotaoLimparDoAluno;
import treinos.Academia;
import treinos.Aluno;
import treinos.Persistencia;

public class TelaEnviarEmail extends ClasseParaCriarJanela implements ActionListener{

	public JTextField TFRemetenteEmail;
	public JTextField TFNomeRemetente;
	public JPasswordField TFSenhaRemetente;
	public JTextField TFAssunto;
	public JTextArea textMensagem;
	public JTextField EnderecoAnexo;
	public File[] Anexo;
	private Font fonte = new Font("Calibri", Font.PLAIN, 17);

	public TelaEnviarEmail() {
		super("Enviar Email", 650, 550);
		this.adicionarLabel();
		this.adicionarJTextField(this);
		this.adicionarButon();
		this.getContentPane().setBackground(Color.WHITE);
		this.repaint();
	}

	private void adicionarButon() {

		// bot�o cadastrar e seu ouvinte
		// ImageIcon img = new ImageIcon(getClass().getResource("icones/ok.png"));
		JButton botaoCadastrar = new JButton("Enviar");

		botaoCadastrar.setPreferredSize(new Dimension(20, 20));
		botaoCadastrar.setBounds(420, 480, 100, 35);
		botaoCadastrar.setFont(fonte);

		botaoCadastrar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				BotaoEnviarActionPerformed(evt);
			}
		});

		add(botaoCadastrar);

		JButton botaoCancelar = new JButton("Cancelar");
		botaoCancelar.setBounds(540, 480, 100, 35);
		botaoCancelar.setFont(fonte);
		botaoCancelar.addActionListener(this);
		add(botaoCancelar);

		// limpar e seu ouvinte
		JButton botaoLimpar = new JButton("Remover");
		botaoLimpar.setBounds(280, 277, 90, 30);
		botaoLimpar.setFont(fonte);

		botaoLimpar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				BotaoExcluirActionPerformed(evt);
			}
		});

		// add(botaoLimpar);

		// voltar
		JButton botapAdicionar = new JButton("Anexar");
		botapAdicionar.setBounds(550, 122, 90, 30);
		botapAdicionar.setFont(fonte);

		botapAdicionar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				BotaoAnexoActionPerformed(evt);
			}
		});

		add(botapAdicionar);
	}

	private void adicionarJTextField(JDialog janela) {

		TFRemetenteEmail = new JTextField();
		TFRemetenteEmail.setBounds(10, 55, 250, 23);
		janela.add(TFRemetenteEmail);

		TFNomeRemetente = new JTextField();
		TFNomeRemetente.setBounds(390, 55, 250, 23);
		janela.add(TFNomeRemetente);

		TFSenhaRemetente = new JPasswordField();
		TFSenhaRemetente.setBounds(10, 125, 250, 23);
		janela.add(TFSenhaRemetente);

		// TfDestinatario = new JTextField();
		// TfDestinatario.setBounds(125, 180, 250, 23);
		// janela.add(TfDestinatario);

		TFAssunto = new JTextField();
		TFAssunto.setBounds(10, 195, 250, 23);
		janela.add(TFAssunto);

		textMensagem = new JTextArea();
		textMensagem.setLineWrap(true);
		textMensagem.setWrapStyleWord(true);
		textMensagem.setBounds(10, 270, 620, 190);
		janela.add(textMensagem);

		EnderecoAnexo = new JTextField();
		EnderecoAnexo.setBounds(390, 125, 150, 23);
		janela.add(EnderecoAnexo);

	}

	private void adicionarLabel() {

		this.criarLabol("Email:", 10, 30, 180, 30);
		this.criarLabol("Nome:", 390, 30, 170, 30);
		this.criarLabol("Senha:", 10, 100, 170, 30);
		// this.criarLabol("Destinatário:", 10, 180, 130, 30);
		this.criarLabol("Anexo:", 390, 100, 90, 30);
		this.criarLabol("Assunto:", 10, 170, 90, 30);
		this.criarLabol("Mensagem:", 10, 240, 110, 30);

	}

	private void BotaoAnexoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_BotaoAnexoActionPerformed

		JFileChooser file = new JFileChooser();
		file.setMultiSelectionEnabled(true);
		file.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int i = file.showOpenDialog(null);
		if (i == 1) {
			EnderecoAnexo.setText("");
			Anexo = null;
		} else {
			Anexo = file.getSelectedFiles();

			String SomaAnexo1 = "";
			String SomaAnexo2 = "";
			for (File enderec : Anexo) {
				EnderecoAnexo.setText(enderec.getPath());
				SomaAnexo1 = EnderecoAnexo.getText();
				SomaAnexo2 = SomaAnexo2 + SomaAnexo1 + ";";

			}
		}

	}

	private void BotaoExcluirActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_BotaoExcluirActionPerformed
		EnderecoAnexo.setText("");
		Anexo = null;

	}

	public void EmailAnexo() {
		
		Persistencia persistencia = new Persistencia();
		Academia academia = persistencia.recuperar("academia.xml");
		
		

		if (Anexo == null) {

			try {
				
				ValidacaoSistema v = new ValidacaoSistema();
				v.stringNula(TFRemetenteEmail.getText());
				v.stringNula(TFNomeRemetente.getText());
				v.stringNula(TFSenhaRemetente.getText());
				v.stringNula(TFAssunto.getText());

				SimpleEmail email = new SimpleEmail();
				// Utilize o hostname do seu provedor de email
				
				email.setHostName("smtp.gmail.com");
				email.setSmtpPort(465);
				email.setSSL(true);

				// Adicione os destinatários
				
				for(Aluno al: academia.getAlunos()) {
					
					email.addTo(al.getEmail(), al.getNome());
	
				}
				// Configure o seu email do qual enviará
				email.setFrom(TFRemetenteEmail.getText(), TFNomeRemetente.getText());

				// Adicione um assunto
				email.setSubject(TFAssunto.getText());
				// Adicione a mensagem do email
				email.setMsg(textMensagem.getText());

				

				email.setAuthentication(TFRemetenteEmail.getText(), TFSenhaRemetente.getText());
				
				email.send();
				
				JOptionPane.showMessageDialog(null, "Email Enviado com Sucesso");
				this.dispose();

			}catch (CampoVazioException  e) {
				JOptionPane.showMessageDialog(null, "Preemcha todos os campos");
			} 
			catch (EmailException e) {
				JOptionPane.showMessageDialog(null, "Houve um erro no Envio !\n" + e);
			}

		} else

		{
			try {
				
				ValidacaoSistema v = new ValidacaoSistema();
				v.stringNula(TFRemetenteEmail.getText());
				v.stringNula(TFNomeRemetente.getText());
				v.stringNula(TFSenhaRemetente.getText());
				v.stringNula(TFAssunto.getText());

				// usuario e senha do seu gmail
				final String usuario = TFRemetenteEmail.getText();
				final String senha = TFSenhaRemetente.getText();

				// config. do gmail
				Properties mailProps = new Properties();
				mailProps.put("mail.transport.protocol", "smtp");
				mailProps.put("mail.smtp.starttls.enable", "true");
				mailProps.put("mail.smtp.host", "smtp.gmail.com");
				mailProps.put("mail.smtp.auth", "true");
				mailProps.put("mail.smtp.user", usuario);
				mailProps.put("mail.debug", "true");
				mailProps.put("mail.smtp.port", 465);
				mailProps.put("mail.smtp.socketFactory.port", 465);
				mailProps.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
				mailProps.put("mail.smtp.socketFactory.fallback", "false");

				// eh necessario autenticar
				Session mailSession = Session.getInstance(mailProps, new Authenticator() {

					public PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(usuario, senha);
					}
				});
				mailSession.setDebug(false);

				// config. da mensagem
				Message mailMessage = new MimeMessage(mailSession);

				// remetente
				mailMessage.setFrom(new InternetAddress(TFRemetenteEmail.getText(), TFNomeRemetente.getText()));

				// destinatario
				
				for(Aluno al: academia.getAlunos()) {
					mailMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(al.getEmail()));
				}
				// mensagem que vai no corpo do email
				MimeBodyPart mbpMensagem = new MimeBodyPart();
				mbpMensagem.setText(TFAssunto.getText());

				// partes do email
				Multipart mp = new MimeMultipart();
				mp.addBodyPart(mbpMensagem);

				String Endereco_Anexo = "";
				if (Anexo != null) { // se tiver alguma coisa anexada ela inicializar o comando abaixo
					for (File element : Anexo) {

						Endereco_Anexo = element.getPath();
						String imagem = Endereco_Anexo;
						File Arquivo = new File(imagem);
						// setando o anexo
						MimeBodyPart mbpAnexo = new MimeBodyPart();
						mbpAnexo.setDataHandler(new DataHandler(new FileDataSource(Arquivo)));
						mbpAnexo.setFileName(Arquivo.getName());
						mp.addBodyPart(mbpAnexo);
					}
				}

				// assunto do email
				mailMessage.setSubject(textMensagem.getText());

				// seleciona o conteudo
				mailMessage.setContent(mp);

				// envia o email
				Transport.send(mailMessage);
				JOptionPane.showMessageDialog(null, "Email Enviado com Sucesso");
				this.dispose();
			}catch (CampoVazioException e) {
				JOptionPane.showMessageDialog(null, "Preemcha todos os campos");
			}  
			catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Houve um erro no Envio !\n" + e);
			}
		}
	}

	private void BotaoEnviarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_BotaoEnviarActionPerformed
		EmailAnexo();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.dispose();
		
	}

}
