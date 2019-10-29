package psquiza;

import java.util.HashMap;
import java.util.Map;

public class ControladorMetas {
	
	private Map<String, Problema> problemas;
	private Map<String, Objetivo> objetivos;
	private int contadorProblema = 1;
	private int contadorObjetivo = 1;
	
	public ControladorMetas() {
		this.problemas = new HashMap<String, Problema>();
		this.objetivos = new HashMap<String, Objetivo>();
	}

	public void cadastraProblema(String descricao, int viabilidade) {
		Util.validaAtributo(descricao, "Campo descricao nao pode ser nulo ou vazio.");
		Util.validarLimite(viabilidade, 1, 5, "Valor invalido de viabilidade.");
		String codigo = "P"+this.contadorProblema;
		this.problemas.put(codigo, new Problema(descricao, viabilidade,codigo));
		this.contadorProblema += 1;
	}

	public void cadastraObjetivo(String tipo, String descricao, int aderencia, int viabilidade) {
		Util.validaAtributo(tipo, "Campo tipo nao pode ser nulo ou vazio.");
		Util.validaAtributo(descricao, "Campo descricao nao pode ser nulo ou vazio.");
		Util.validarLimite(aderencia, 1, 5, "Valor invalido de aderencia");
		Util.validarLimite(viabilidade, 1, 5, "Valor invalido de viabilidade.");
		if(tipo.equals("GERAL") || tipo.equals("ESPECIFICO")) {
			String codigo = "O"+contadorObjetivo;
			this.objetivos.put(codigo, new Objetivo(tipo, descricao, aderencia, viabilidade, codigo));
			this.contadorObjetivo += 1;
		}else {
			throw new IllegalArgumentException("Valor invalido de tipo.");
		}
	}

	public void apagarProblema(String codigo) {
		Util.validaAtributo(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		if(!problemas.containsKey(codigo)) {
			throw new IllegalArgumentException("Problema nao encontrado");
		}
		problemas.remove(codigo);
	}

	public void apagarObjetivo(String codigo) {
		Util.validaAtributo(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		if(!objetivos.containsKey(codigo)) {
			throw new IllegalArgumentException("Objetivo nao encontrado");
		}
		objetivos.remove(codigo);
		
	}

	public String exibeProblema(String codigo) {
		if(!problemas.containsKey(codigo)) {
			throw new IllegalArgumentException("Problema nao encontrado");
		}
		return problemas.get(codigo).toString();
		
	}

	public String exibeObjetivo(String codigo) {
		if(!objetivos.containsKey(codigo)) {
			throw new IllegalArgumentException("Objetivo nao encontrado");
		}
		return objetivos.get(codigo).toString();
	}

	
	

}
