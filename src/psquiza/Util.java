package psquiza;

import java.text.SimpleDateFormat;

/**
 * Classe responsavel por dispor metodos de uso geral, a fim de incentivar
 * o reuso de codigo.
 * 
 * @author Pedro, Lucian, Eniedson, Regina
 */
public final class Util {
	
	/**
	 * Valida se o valor recebido por parametro e nulo ou vazio, caso seja
	 * sera lancado um IllegalArgumentException com o erro recebido por
	 * parametro.
	 * 
	 * @param valor e o valor a ser validado.
	 * @param erro e o erro que sera lancado caso o valor seja invalido.
	 */
	public static void validaAtributo(String valor, String erro) {
		if (valor == null || valor.equals(""))
			throw new IllegalArgumentException(erro);
	}
	
	/**
	 * Valida se o valor recebido por parametro pertence ao intervalo
	 * [intervaloX, intervaloY], caso nao pertenca sera lancado um
	 * IllegalArgumentException com o erro recebido por parametro.
	 * 
	 * @param valor e o valor a ser validado.
	 * @param intervaloX e valor minimo do intervalo.
	 * @param intervaloY e o valor maximo do intervalo.
	 * @param erro e o erro que sera lancado caso esteja fora do
	 * intervalo.
	 */
	public static void validarLimite(int valor, int intervaloX, int intervaloY, String erro) {
		if(valor < intervaloX || valor > intervaloY)
			throw new IllegalArgumentException(erro);		
	}
	
	/**
	 * Verifica se um email e valido. Um email valido e caracterizado
	 * por possuir um @, por possui ao menos um caractere antes do @
	 * e ao menos outro depois do @ . Caso o email seja invalido,
	 * sera lancado um IllegalArgumentException
	 * "Formato de email invalido.".
	 * 
	 * @param email e o email recebido por parametro que sera
	 * validado.
	 */
	public static void validaEmail(String email) {
		if (!email.contains("@") || email.split("@").length != 2 || email.split("@")[0].trim().isEmpty() || email.split("@")[1].trim().isEmpty())
			throw new IllegalArgumentException("Formato de email invalido.");	
	}

	/**
	 * Verifica se o link de uma foto e valido. Um link valido e
	 * caracterizado por possuir ao menos 7 letras, possuir um dos
	 * seguintes inicios "http://" ou "https://". Caso o link seja
	 * invalido, sera lancado um IllegalArgumentException
	 * "Formato de email invalido.".
	 * 
	 * @param foto e o link, referente a foto, que sera validado.
	 */
	public static void validaFoto(String foto) {
		if (foto.length() < 7 || (!foto.substring(0, 7).equals("http://") && !foto.substring(0, 8).equals("https://")))
			throw new IllegalArgumentException("Formato de foto invalido.");
	} 
	
	/**
	 * Verifica se uma data e valida. Uma data valida deve seguir o
	 * formato "DD/MM/YYYY". Caso o formato seja invalido sera
	 * lancado um IllegalArgumentException
	 * "Atributo data com formato invalido.".
	 * 
	 * @param data e a data recebido por parametro que sera validada.
	 */
	public static void validaData(String data) {
		try {
			String[] datas = data.split("/");
			if(datas[0].length() != 2 || datas[1].length() != 2 || datas[2].length() != 4 ) {
				throw new IllegalArgumentException();
			}
			
			SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
			formatoData.setLenient(false);
			formatoData.parse(data);
		}catch(Exception e) {
			throw new IllegalArgumentException("Atributo data com formato invalido.");
		}
	}

	/**
	 * Verifica se o Indice de Eficiencia Academico (IEA) e valido.
	 * Caso o IEA seja menor que 0 ou maior que 10, sera lancado um
	 * IllegalArgumentException "Atributo IEA com formato invalido.".
	 * 
	 * @param IEA e o indice de eficiencia academico, e o campo que
	 * sera validado.
	 */
	public static void validaIEA(double IEA) {
		if(IEA < 0.0 || IEA > 10.0)
			throw new IllegalArgumentException("Atributo IEA com formato invalido.");
	}
	
	/**
	 * Valida se um numero e maior que 0 ou nao. Caso o numero nao
	 * seja positivo sera lancado um IllegalArgumentException
	 * com o erro recebido por parametro.
	 * 
	 * @param numero e o numero a ser validado.
	 * @param erro e o nome do erro que sera lancado caso o numero
	 * seja invalido.
	 */
	public static void validaNumero(int numero, String erro) {
		if(numero <= 0)
			throw new IllegalArgumentException(erro);
	}
	
	/**
	 * Metodo reponsavel por remover espacos vazios de uma string.
	 * 
	 * @param v e a string a ser retirado os espacos vazios.
	 * @return e retornado a string sem os espacos vazios.
	 */
	public static String removeVazios(String v) {
		v = v.replace("|  ", "");
		
		int len = v.length();
        int st = 0;

        while ((st < len) && (v.charAt(st) == ' ' || v.charAt(st) == '|')) {
            st++;
        }
        while ((st < len) && (v.charAt(len - 1) == ' ' || v.charAt(len - 1) == '|')) {
            len--;
        }
        return ((st > 0) || (len < v.length())) ? v.substring(st, len) : v;
	}
}