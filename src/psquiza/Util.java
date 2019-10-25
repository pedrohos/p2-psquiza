package psquiza;

public final class Util {
	public static void validaAtributo(String valor, String erro) {
		if (valor == null || valor.equals(""))
			throw new IllegalArgumentException(erro);
	}
}
