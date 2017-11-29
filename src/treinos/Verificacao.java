package treinos;

import java.util.ArrayList;

import Excecoes.ArrayVazioException;
import Excecoes.CampoVazioException;
import Excecoes.CaracterInvalidoException;
import Excecoes.NumeroIgualAZeroException;
import Excecoes.SelecioneUmValorNoSpinnerException;

public class Verificacao {

	public void stringVazia(String nome) throws CampoVazioException {
		if(nome.matches("\\s+") || nome.equals("")){
			throw new CampoVazioException();
		}
	}

	public void caracterInvalido(String nome) throws CaracterInvalidoException {
		for(int i = 0; i < nome.length(); i++){
			if(!Character.isAlphabetic(nome.charAt(i))){
				if(Character.isSpaceChar(nome.charAt(i))){}
				else{
					throw new CaracterInvalidoException();
				}
			}
		}
	}

	public void igualAZero(Integer numero) throws SelecioneUmValorNoSpinnerException {
		if(numero == 0){
			throw new SelecioneUmValorNoSpinnerException();
		}
	}
	
	public void igualAZeroFloat(Float numero) throws NumeroIgualAZeroException {
		if(numero == 0){
			throw new NumeroIgualAZeroException();
		}
	}
}
