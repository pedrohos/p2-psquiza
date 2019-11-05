package psquiza.controladores;

import java.util.HashMap;
import java.util.Map;

import psquiza.Util;
import psquiza.entidades.Objetivo;
import psquiza.entidades.Problema;

/**
 * Classe controller responsavel pelos problemas e objetivos do sistema
 * 
 * @author Lucian Julio
 *
 */
public class ControladorMetas {
	/**
	 * Estrutura para guardar os objetos do tipo problema do sistema
	 */
	private Map<String, Problema> problemas;
	/**
	 * Estrutura para guardar os objetos do tipo objetivo do sistema
	 */
	private Map<String, Objetivo> objetivos;
	/**
	 * Contador para gerar o id de um problema
	 */
	private int contadorProblema = 1;
	/**
	 * COntador para gerar o id de um objetivo
	 */
	private int contadorObjetivo = 1;
	
	/**
	 * Constroi um controlador de metas
	 */
	public ControladorMetas() {
		this.problemas = new HashMap<String, Problema>();
		this.objetivos = new HashMap<String, Objetivo>();
	}

	/**
	 * Metodo para cadastrar um novo problema
	 * 
	 * @param descricao   descricao do problema
	 * @param viabilidade viabilidade do problema
	 */
	public void cadastraProblema(String descricao, int viabilidade) {
		Util.validaAtributo(descricao, "Campo descricao nao pode ser nulo ou vazio.");
		Util.validarLimite(viabilidade, 1, 5, "Valor invalido de viabilidade.");
		String codigo = "P" + this.contadorProblema;
		this.problemas.put(codigo, new Problema(descricao, viabilidade, codigo));
		this.contadorProblema += 1;
	}

	/**
	 * Metodo para cadastrar um objetivo
	 * 
	 * @param tipo        tipo do objetivo
	 * @param descricao   descricao do objetivo
	 * @param aderencia   aderencia do objetivo
	 * @param viabilidade viabilidade do objetivo
	 */
	public void cadastraObjetivo(String tipo, String descricao, int aderencia, int viabilidade) {
		Util.validaAtributo(tipo, "Campo tipo nao pode ser nulo ou vazio.");
		Util.validaAtributo(descricao, "Campo descricao nao pode ser nulo ou vazio.");
		Util.validarLimite(aderencia, 1, 5, "Valor invalido de aderencia");
		Util.validarLimite(viabilidade, 1, 5, "Valor invalido de viabilidade.");
		if (tipo.equals("GERAL") || tipo.equals("ESPECIFICO")) {
			String codigo = "O" + contadorObjetivo;
			this.objetivos.put(codigo, new Objetivo(tipo, descricao, aderencia, viabilidade, codigo));
			this.contadorObjetivo += 1;
		} else {
			throw new IllegalArgumentException("Valor invalido de tipo.");
		}
	}

	/**
	 * Metodo para apagar um problema
	 * 
	 * @param codigo identificador do problema
	 */
	public void apagarProblema(String codigo) {
		Util.validaAtributo(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		if (!problemas.containsKey(codigo)) {
			throw new IllegalArgumentException("Problema nao encontrado");
		}
		problemas.remove(codigo);
	}

	/**
	 * Metodo para apagar um objetivo
	 * 
	 * @param codigo codigo do objetivo
	 */
	public void apagarObjetivo(String codigo) {
		Util.validaAtributo(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		if (!objetivos.containsKey(codigo)) {
			throw new IllegalArgumentException("Objetivo nao encontrado");
		}
		objetivos.remove(codigo);

	}

	/**
	 * Metodo para exibir a representacao em texto de um problema
	 * 
	 * @param codigo codigo do problema
	 * @return retorna a representacao em texto do problema
	 */
	public String exibeProblema(String codigo) {
		if (!problemas.containsKey(codigo)) {
			throw new IllegalArgumentException("Problema nao encontrado");
		}
		return problemas.get(codigo).toString();

	}

	/**
	 * metodo para exibir a representacao em texto de um objetivo
	 * 
	 * @param codigo codigo do objetivo
	 * @return retorna a representacao em texto de um objetivo
	 */
	public String exibeObjetivo(String codigo) {
		if (!objetivos.containsKey(codigo)) {
			throw new IllegalArgumentException("Objetivo nao encontrado");
		}
		return objetivos.get(codigo).toString();
	}
	
	private boolean existeObjetivo(String codigo) {
		for (Objetivo o: objetivos.values()) {
			if (o.getCodigo().equals(codigo)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean associaPesquisa(String idPesquisa, String idObjetivo) {
		Util.validaAtributo(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		Util.validaAtributo(idObjetivo, "Campo idObjetivo nao pode ser nulo ou vazio.");
		if(!existeObjetivo(idObjetivo))
			throw new IllegalArgumentException("Objetivo nao encontrado.");
		
		return this.objetivos.get(idObjetivo).associaPesquisa(idPesquisa);
	}

	public boolean desassociaPesquisa(String idPesquisa, String idObjetivo) {
		Util.validaAtributo(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		Util.validaAtributo(idObjetivo, "Campo idObjetivo nao pode ser nulo ou vazio.");
		if(!existeObjetivo(idObjetivo))
			throw new IllegalArgumentException("Objetivo nao encontrado.");
		
		return this.objetivos.get(idObjetivo).desassociaPesquisa(idPesquisa);
	}
}
