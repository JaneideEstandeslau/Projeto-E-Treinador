package treinos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class Persistencia {
	
	XStream xstream = new XStream(new DomDriver());
	private boolean salvo = false;

	public boolean salvar(Academia academia, String nomeDoArquivo) { // SALVA O OBJETO ACADEMIA EM UM ARQUIVO XML.
		File arquivo = new File(nomeDoArquivo);
		try {
			arquivo.createNewFile();
			FileWriter escrever = new FileWriter(arquivo);
			nomeDoArquivo = xstream.toXML(academia);
			escrever.write(nomeDoArquivo);
			escrever.flush();
			escrever.close();
			salvo = true;
		} catch (IOException e) {
			salvo = false;
			e.printStackTrace();
		}
		return salvo;
	}
	
	public Academia recuperar(String nomeDoArquivo) { // RECUPERA UM ARQUIVO EM FORMA DE OBJETO.
		File arquivo = new File(nomeDoArquivo);
		FileReader ler = null;
		if (arquivo.exists()) {
			try {
				
				ler = new FileReader(arquivo);
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			Academia academia = (Academia) xstream.fromXML(ler);
			return academia;
		} else {
			return new Academia();
		}
	}
}
