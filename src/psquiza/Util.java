package psquiza;

import com.sun.xml.internal.txw2.IllegalAnnotationException;

public final class Util {
	public static void validaAtributo(String valor, String erro) {
		if (valor == null || valor.equals(""))
			throw new IllegalArgumentException(erro);
	}
	
	public static void validarLimite(int valor, int intervaloX, int intervaloY, String erro) {
		if(valor < intervaloX || valor > intervaloY) {
			throw new IllegalAnnotationException(erro);
		}
	}
}
