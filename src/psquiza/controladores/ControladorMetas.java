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
	
	/**
	 * Constroi um controlador a partir dos atributos carregados.
	 * 
	 * @param problemas e o mapa de problemas a ser atribuido.
	 * @param objetivos e o mapa de problemas a ser atribuido.
	 * @param contadorProblema e o contador de problema a ser atribuido.
	 * @param contadorObjetivo e o contador de objetivo a ser atribuido.
	 */
	public ControladorMetas(Map<String, Problema> problemas, Map<String, Objetivo> objetivos, int contadorProblema, int contadorObjetivo) {
		this.problemas = (HashMap<String, Problema>) problemas;
		this.objetivos = (HashMap<String, Objetivo>) objetivos;
		this.contadorProblema = contadorProblema;
		this.contadorObjetivo =  contadorObjetivo;
	}

	/**
	 * Cadastrar um problema no mapa de problemas.
	 * Cada problema possui uma descricao e uma viabildiade que deve
	 * estar entre 1 e 5. E atribui o codigo ao problema formado por:
	 * "PX" com X sendo o contador problema que e incrementado apos
	 * cada cadastro de problema.
	 * 
	 * Caso a descricao seja nula ou vazia ser lancado um IllegalArgumentException:
	 * "Campo descricao nao pode ser nulo ou vazio."
	 * Caso a viabilidade nao esteja entre 1 e 5 ser lancado um IllegalArgumentException:
	 * "Valor invalido de viabilidade."
	 * 
	 * @param descricao   e a descricao do problema
	 * @param viabilidade e a viabilidade do problema
	 */
	public void cadastraProblema(String descricao, int viabilidade) {
		Util.validaAtributo(descricao, "Campo descricao nao pode ser nulo ou vazio.");
		Util.validarLimite(viabilidade, 1, 5, "Valor invalido de viabilidade.");

		String codigo = "P" + this.contadorProblema;
		this.problemas.put(codigo, new Problema(descricao, viabilidade, codigo));
		this.contadorProblema += 1;
	}

	/**
	 * Cadastra um objetivo no mapa objetivos.
	 * Cada objetivo possui um tipo, descricao, aderencia, viabilidade
	 * e id. O tipo deve ser GERAL ou ESPECIFICO, e a aderencia e a
	 * viabilidade devem ser de 1 a 5.
	 * 
	 *  Caso o tipo seja nulo ou vazio sera lancado um IllegalArgumentException:
	 *  "Campo tipo nao pode ser nulo ou vazio."
	 *  Caso a descricao seja nula ou vazia sera lancado um IllegalArgumentException:
	 *  "Campo descricao nao pode ser nulo ou vazio."
	 *  Caso a aderencia nao esteja entre 1 e 5 sera lancado um IllegalArgumentException:
	 *  "Valor invalido de aderencia"
	 *  Caso a viabilidade nao esteja entre 1 e 5 sera lancado um IllegalArgumentException:
	 * "Valor invalido de viabilidade."
	 * 
	 * @param tipo        e tipo do objetivo, podendo ser GERAL ou ESPECIFICO.
	 * @param descricao   e a descricao do objetivo.
	 * @param aderencia   e a aderencia do objetivo, estando entre 1 e 5.
	 * @param viabilidade e a viabilidade do objetivo, estando entre 1 e 5.
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
	 * Apaga um problema do mapa de problemas atraves do codigo que
	 * identifica o problema.
	 * 
	 * Caso o problema seja vazio ou nulo sera lancado um IllegalArgumentException:
	 * "Campo codigo nao pode ser nulo ou vazio.".
	 * Caso o problema nao seja encontrado sera lancado um IllegalArgumentException:
	 * "Problema nao encontrado".
	 * 
	 * @param codigo e o identificador unico do problema.
	 */
	public void apagarProblema(String codigo) {
		Util.validaAtributo(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		if (!problemas.containsKey(codigo)) {
			throw new IllegalArgumentException("Problema nao encontrado");
		}

		problemas.remove(codigo);
	}

	/**
	 * Apaga um objetivo do mapa de objetivoss atraves do codigo que
	 * identifica o objetivo.
	 * 
	 * Caso o objetivo seja vazio ou nulo sera lancado um IllegalArgumentException:
	 * "Campo codigo nao pode ser nulo ou vazio."
	 * Caso o objetivo nao seja encontrado sera lancado um IllegalArgumentException:
	 * "Objetivo nao encontrado"
	 * 
	 * @param codigo e o identificador unico do objetivo.
	 */
	public void apagarObjetivo(String codigo) {
		Util.validaAtributo(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		if (!objetivos.containsKey(codigo)) {
			throw new IllegalArgumentException("Objetivo nao encontrado");
		}

		objetivos.remove(codigo);
	}

	/**
	 * Exibe a representacao em texto de um problema no seguinte formato:
	 * CODIGO - DESCRICAO - VIABILIDADE
	 * 
	 * Caso o problema nao seja encontrado sera lancado um
	 * IllegalArgumentException: "Problema nao encontrado".
	 * 
	 * @param codigo e o codigo referente ao problema.
	 * @return retorna a representacao em texto do problema.
	 */
	public String exibeProblema(String codigo) {
		if (!problemas.containsKey(codigo)) {
			throw new IllegalArgumentException("Problema nao encontrado");
		}

		return problemas.get(codigo).toString();
	}

	/**
	 * Exibe a representacao em texto de um objetivo no seguinte formato:
	 * CODIGO - TIPO - DESCRICAO - (ADERENCIA VIABILIDADE)
	 * 
	 * Caso o problema nao seja encontrado sera lancado um
	 * IllegalArgumentException: "Problema nao encontrado".
	 * 
	 * @param codigo e o codigo referente ao objetivo.
	 * @return retorna a representacao em texto do objetivo.
	 */
	public String exibeObjetivo(String codigo) {
		if (!objetivos.containsKey(codigo)) {
			throw new IllegalArgumentException("Objetivo nao encontrado");
		}

		return objetivos.get(codigo).toString();
	}

	/**
	 * Verifica se um objetivo existe dado seu codigo, retornado true
	 * caso exista, caso contrario false.
	 * 
	 * @param codigo e o codigo que sera verificado se remete a algum
	 * objetivo.
	 * @return e retornado true caso exista, caso contrario, false.
	 */
	private boolean existeObjetivo(String codigo) {
		for (Objetivo o : objetivos.values()) {
			if (o.getCodigo().equals(codigo)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Associa uma pesquisa a um objetivo. Salvando o codigo da
	 * pesquisa no objetivo. Retorna true caso a associacao tenha
	 * sido executada com sucesso, caso contrario, false.
	 * 
	 * Caso o idPesquisa seja nulo ou vazio sera lancado um
	 * IllegalArgumentException: "Campo idPesquisa nao pode ser nulo ou vazio."
	 * Caso o idObjetivo seja nulo ou vazio sera lancado um
	 * IllegalArgumentException: "Campo idObjetivo nao pode ser nulo ou vazio."
	 * Caso o idObjetivo nao seja encontrado sera lancado um
	 * IllegalArgumentException: "Objetivo nao encontrado."
	 * 
	 * @param idPesquisa e o identificador da pesquisa.
	 * @param idObjetivo e o identificador do objetivo.
	 * @return e retornado um boolean indicando se a associacao foi
	 * efetuada com sucesso ou nao.
	 */
	public boolean associaPesquisa(String idPesquisa, String idObjetivo) {
		Util.validaAtributo(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		Util.validaAtributo(idObjetivo, "Campo idObjetivo nao pode ser nulo ou vazio.");
		if (!existeObjetivo(idObjetivo))
			throw new IllegalArgumentException("Objetivo nao encontrado.");

		return this.objetivos.get(idObjetivo).associaPesquisa(idPesquisa);
	}

	/**
	 * Desassocia uma pesquisa a um objetivo. Removendo o codigo da
	 * pesquisa do objetivo. Retorna true caso a desassociacao tenha
	 * sido executada com sucesso, caso contrario, false.
	 * 
	 * Caso o idPesquisa seja nulo ou vazio sera lancado um
	 * IllegalArgumentException: "Campo idPesquisa nao pode ser nulo ou vazio."
	 * Caso o idObjetivo seja nulo ou vazio sera lancado um
	 * IllegalArgumentException: "Campo idObjetivo nao pode ser nulo ou vazio."
	 * Caso o idObjetivo nao seja encontrado sera lancado um
	 * IllegalArgumentException: "Objetivo nao encontrado."
	 * 
	 * @param idPesquisa e o identificador da pesquisa.
	 * @param idObjetivo e o identificador do objetivo.
	 * @return e retornado um boolean indicando se a desassociacao foi
	 * efetuada com sucesso ou nao.
	 */
	public boolean desassociaPesquisa(String idPesquisa, String idObjetivo) {
		Util.validaAtributo(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		Util.validaAtributo(idObjetivo, "Campo idObjetivo nao pode ser nulo ou vazio.");
		if (!existeObjetivo(idObjetivo))
			throw new IllegalArgumentException("Objetivo nao encontrado.");

		return this.objetivos.get(idObjetivo).desassociaPesquisa(idPesquisa);
	}

	/**
	 * Busca no mapa de problemas os problemas que possuem o termo
	 * recebido por parametro em sua descricao, cada elemento que
	 * possuir e adicionado a string final. Posteriormente e
	 * retornado a lista de elementos na forma:
	 * CODIGO1: DESCRICAO1 | CODIGOX: DESCRICAOX
	 * 
	 * @param termo e o termo a ser buscado na descricao.
	 * @return e retornado uma string no formato acima, formada pelos
	 * elementos encontrados com o dado termo.
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
	 * Busca no mapa de objetivos os objetivos que possuem o termo
	 * recebido por parametro em sua descricao, cada elemento que
	 * possuir e adicionado a string final. Posteriormente e
	 * retornado a lista de elementos na forma:
	 * CODIGO1: DESCRICAO1 | CODIGOX: DESCRICAOX
	 * 
	 * @param termo e o termo a ser buscado na descricao.
	 * @return e retornado a uma string no formato acima formada pelos
	 * elementos encontrados com o dado termo.
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
