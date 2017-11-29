package relatorio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import treinos.Academia;
import treinos.Aluno;
import treinos.Persistencia;
import treinos.Treino;

public class Relatorio {

	public void gerarRelatorio(int select) {

		Persistencia persistencia = new Persistencia();
		Academia academia = persistencia.recuperar("academia.xml");

		ArrayList<Aluno> alunos = academia.getAlunos();

		Treino[] treinos = alunos.get(select).getTreinosDoAluno();

		Document document = new Document();
		try {
			String caminho = System.getProperty("user.home");
			PdfWriter.getInstance(document, new FileOutputStream("C:\\ITextTest.pdf"));
			document.open();
			document.setPageSize(PageSize.A4);

			PdfPTable table = new PdfPTable(3);

			PdfPCell header = new PdfPCell(new Paragraph("Treinos da Semana"));
			header.setColspan(3);

			String[] diasDaSemana = { "Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sabado" };

			table.addCell("");
			table.addCell("Nome do Treino");
			table.addCell("Duração");

			int i = 0;
			for (Treino t : treinos) {
				if (t == null || t.getNomeDoTreino().equals("Não Treinar")) {
					table.addCell(diasDaSemana[i++]);
					table.addCell("Descanso");
					table.addCell("Descanso");
				} else {
					table.addCell(diasDaSemana[i++]);
					table.addCell(t.getNomeDoTreino());
					table.addCell(Double.toString(t.duracao()));
				}
			}
			document.add(table);
			document.close();
		} catch (FileNotFoundException | DocumentException ex) {
			ex.printStackTrace();
		}
	}

}
