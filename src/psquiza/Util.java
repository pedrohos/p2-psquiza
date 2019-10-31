package psquiza;

public final class Util {
	public static void validaAtributo(String valor, String erro) {
		if (valor == null || valor.equals(""))
			throw new IllegalArgumentException(erro);
	}
	
	public static void validarLimite(int valor, int intervaloX, int intervaloY, String erro) {
		if(valor < intervaloX || valor > intervaloY) {
			throw new IllegalArgumentException(erro);
		}
	}
	
	public static void validaEmail(String email) {
		if (!email.contains("@") || email.split("@").length != 2 || email.split("@")[0].trim().isEmpty() || email.split("@")[1].trim().isEmpty()) {
			throw new IllegalArgumentException("Formato de email invalido.");
		}
	}

	public static void validaFoto(String foto) {
		if (foto.length() < 7 || (!foto.substring(0, 7).equals("http://") && !foto.substring(0, 8).equals("https://"))) {
			throw new IllegalArgumentException("Formato de foto invalido.");
		}
	} 
} 
