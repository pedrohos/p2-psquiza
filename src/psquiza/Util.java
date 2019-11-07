package psquiza;

import java.text.SimpleDateFormat;

public final class Util {
	public static void validaAtributo(String valor, String erro) {
		if (valor == null || valor.equals(""))
			throw new IllegalArgumentException(erro);
	}
	
	public static void validarLimite(int valor, int intervaloX, int intervaloY, String erro) {
		if(valor < intervaloX || valor > intervaloY)
			throw new IllegalArgumentException(erro);		
	}
	
	public static void validaEmail(String email) {
		if (!email.contains("@") || email.split("@").length != 2 || email.split("@")[0].trim().isEmpty() || email.split("@")[1].trim().isEmpty())
			throw new IllegalArgumentException("Formato de email invalido.");	
	}

	public static void validaFoto(String foto) {
		if (foto.length() < 7 || (!foto.substring(0, 7).equals("http://") && !foto.substring(0, 8).equals("https://")))
			throw new IllegalArgumentException("Formato de foto invalido.");
	} 
	
	
	public static void validaData(String data) {
		try {
			String[] datas = data.split("/");
			if(datas[0].length() != 2 || datas[1].length() != 2 || datas[2].length() != 4 ) {
				throw new IllegalArgumentException();
			}
			
			SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
			formatoData.setLenient(false);
			formatoData.parse(data);
		}catch(Exception e ) {
			throw new IllegalArgumentException("Atributo data com formato invalido.");
		}
	}

	public static void validaIEA(double IEA) {
		if( IEA < 0.0 || IEA > 10.0)
			throw new IllegalArgumentException("Atributo IEA com formato invalido.");
	}
	
	public static void validaNumero(int numero, String erro) {
		if(numero < 0)
			throw new IllegalArgumentException(erro);
	}
}