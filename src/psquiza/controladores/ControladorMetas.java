package psquiza.controladores;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import psquiza.Util;
import psquiza.entidades.Objetivo;
import psquiza.entidades.Problema;
import psquiza.ordenacao.OrdenaObjetivo;
import psquiza.ordenacao.OrdenaProblema;

/**
 * Classe controller responsavel pelos problemas e objetivos do sistema
 * 
 * @author Lucian Julio
 *
 */
public class ControladorMetas implements Serializable {

	private static final long serialVersionUID = 1L;
	
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
	
	public ControladorMetas(Map<String, Problema> problemas, Map<String, Objetivo> objetivos, int contadorProblema, int contadorObjetivo) {
		this.problemas = (HashMap<String, Problema>) problemas;
		this.objetivos = (HashMap<String, Objetivo>) objetivos;
		this.contadorProblema = contadorProblema;
		this.contadorObjetivo =  contadorObjetivo;
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
		for (Objetivo o : objetivos.values()) {
			if (o.getCodigo().equals(codigo)) {
				return true;
			}
		}
		return false;
	}

	public boolean associaPesquisa(String idPesquisa, String idObjetivo) {
		Util.validaAtributo(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		Util.validaAtributo(idObjetivo, "Campo idObjetivo nao pode ser nulo ou vazio.");
		if (!existeObjetivo(idObjetivo))
			throw new IllegalArgumentException("Objetivo nao encontrado.");

		return this.objetivos.get(idObjetivo).associaPesquisa(idPesquisa);
	}

	public boolean desassociaPesquisa(String idPesquisa, String idObjetivo) {
		Util.validaAtributo(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		Util.validaAtributo(idObjetivo, "Campo idObjetivo nao pode ser nulo ou vazio.");
		if (!existeObjetivo(idObjetivo))
			throw new IllegalArgumentException("Objetivo nao encontrado.");

		return this.objetivos.get(idObjetivo).desassociaPesquisa(idPesquisa);
	}

	/**
	 * Metodo que busca na colecao de problemas que possuam o termo informado na
	 * descricao.
	 * 
	 * @param termo termo a ser buscado.
	 * 
	 * @return lista de resultados.
	 */
	public String buscaProblema(String termo) {
		String listagem = "";
		List<Problema> aux = problemas.values().stream().collect(Collectors.toList());
		Collections.sort(aux, new OrdenaProblema());
		for (Problema problema : aux) {
			if (problema.getDescricao().toLowerCase().contains(termo.toLowerCase())) {
				if (listagem.isEmpty()) {
					listagem += problema.getCodigo() + ": " + problema.getDescricao();
				} else {
					listagem += " | " + problema.getCodigo() + ": " + problema.getDescricao();
				}
			}
		}

		return listagem;
	}

	/**
	 * Metodo que busca na colecao de objetivos que possuam o termo informado na
	 * descricao.
	 * 
	 * @param termo termo a ser buscado.
	 * 
	 * @return lista de resultados.
	 */
	public String buscaObjetivo(String termo) {
		String listagem = "";
		List<Objetivo> aux = objetivos.values().stream().collect(Collectors.toList());
		Collections.sort(aux, new OrdenaObjetivo());
		for (Objetivo objetivo : aux) {
			if (objetivo.getDescricao().toLowerCase().contains(termo.toLowerCase())) {
				if (listagem.isEmpty()) {
					listagem += objetivo.getCodigo() + ": " + objetivo.getDescricao();
				} else {
					listagem += " | " + objetivo.getCodigo() + ": " + objetivo.getDescricao();
				}
			}
		}

		return listagem;
	}
	
	public HashMap<String, Problema> getMapaProblemas() {
		return (HashMap<String, Problema>) this.problemas;
	}
	
	public HashMap<String, Objetivo> getMapaObjetivos() {
		return (HashMap<String, Objetivo>) this.objetivos;
	}
	
	public int getContadorProblema() {
		return this.contadorProblema;
	}
	
	public int getContadorObjetivo() {
		return this.contadorObjetivo;
	}

	public Problema getProblema(String idProblema) {
		Util.validaAtributo(idProblema, "Campo idProblema nao pode ser nulo ou vazio.");
		if(!problemas.containsKey(idProblema)) {
			throw new IllegalArgumentException("Problema nao existe!");
		}
		return problemas.get(idProblema);
	}

	public Objetivo getObjetivo(String idObjetivo) {
		Util.validaAtributo(idObjetivo, "Campo idObjetivo nao pode ser nulo ou vazio.");
		if(!objetivos.containsKey(idObjetivo)) {
			throw new IllegalArgumentException("");
		}
		return objetivos.get(idObjetivo);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + contadorObjetivo;
		result = prime * result + contadorProblema;
		result = prime * result + ((objetivos == null) ? 0 : objetivos.hashCode());
		result = prime * result + ((problemas == null) ? 0 : problemas.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ControladorMetas other = (ControladorMetas) obj;
		if (contadorObjetivo != other.contadorObjetivo)
			return false;
		if (contadorProblema != other.contadorProblema)
			return false;
		if (objetivos == null) {
			if (other.objetivos != null)
				return false;
		} else if (!objetivos.equals(other.objetivos))
			return false;
		if (problemas == null) {
			if (other.problemas != null)
				return false;
		} else if (!problemas.equals(other.problemas))
			return false;
		return true;
	}
}
