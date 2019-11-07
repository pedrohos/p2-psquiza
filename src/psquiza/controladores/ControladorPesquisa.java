package psquiza.controladores;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

import psquiza.Util;
import psquiza.entidades.Atividade;
import psquiza.entidades.Pesquisa;
import psquiza.ordenacao.*;

/**
 * 
 * Representa um controlador das funcionalidades de uma pesquisa, possui metodos
 * para gerar o codigo de um pesquisador, validar o campo de interesse passado
 * como parametro, retornar se existe a pesquisa solicitada, fora as
 * funcionalidades basicas como criar, editar, desativar e ativar uma pesquisa.
 * 
 * @author Regina Leticia Santos Felipe
 *
 */
public class ControladorPesquisa {

	/**
	 * Colecao de pesquisas, no formato: Chave - Pesquisa, onde um codigo gerado
	 * pelo sistema identifica unicamente uma pesquisa.
	 */
	private LinkedHashMap<String, Pesquisa> pesquisas;

	/**
	 * Cria um controle de pesquisa e inicializa a coleção de pesquisas.
	 */
	public ControladorPesquisa() {
		pesquisas = new LinkedHashMap<String, Pesquisa>();
	}

	/**
	 * 
	 * Metodo interno da classe que gera o codigo de uma Pesquisa, uma string no
	 * formato "SSSI", composto por tres strings, as tres primeiras letras do campo
	 * de interesse da pesquisa e um inteiro que representa a quantidade de vezes
	 * que aquelas tres primeiras letras ja foram usadas, iniciando do 1.
	 * 
	 * @param pesquisas HasMAp que possui todas pesquisas cadastradas
	 * @param nome      Campo de Interesse da pesquisa a se gerar o codigo
	 * @return codigo no formato "SSSI"
	 */
	private String geraCodigo(HashMap<String, Pesquisa> pesquisas, String nome) {

		int id = 1;
		String codigo = nome.substring(0, 3).toUpperCase() + id;
		while (pesquisas.containsKey(codigo)) {
			id += 1;
			codigo = nome.substring(0, 3).toUpperCase() + id;
		}
		return codigo;
	}

	/**
	 * 
	 * Metodo interno da classe que lança uma excessao caso o codigo do pesquisador
	 * passado nao exista na colecao de pesquisadores.
	 * 
	 * @param pesquisas HasMAp que possui todas pesquisas cadastradas
	 * @param codigo    codigo da pesquisa solicitada
	 */
	private void validaPesquisa(String codigo) {
		if (!pesquisas.containsKey(codigo)) {
			throw new NullPointerException("Pesquisa nao encontrada.");
		}
	}

	/**
	 * 
	 * Metodo interno da classe que valida a string do campo de interesse e lanca
	 * uma excessao caso nao siga o formato solicitado. Para um Campo de interesse
	 * ser valido e necessario que possua ate 255 caracteres e no minimo 3.
	 * Separados por virgulas, uma pesquisa pode possuir no maximo quatro campos de
	 * interesses.
	 * 
	 * @param campoDeInteresse
	 */
	private void validaCampoDeInteresse(String campoDeInteresse) {

		if (campoDeInteresse == null || campoDeInteresse.trim().isEmpty()) {
			throw new IllegalArgumentException("Formato do campo de interesse invalido.");
		}

		if (campoDeInteresse.contains(",,") || (campoDeInteresse.length() < 3 || campoDeInteresse.length() > 255)) {
			throw new IllegalArgumentException("Formato do campo de interesse invalido.");
		} else {
			String[] campos = campoDeInteresse.split(",");
			if (campos.length > 4) {
				throw new IllegalArgumentException("Formato do campo de interesse invalido.");
			} else {
				for (String campo : campos) {
					if (campo.trim().isEmpty() || campo.trim().length() < 3) {
						throw new IllegalArgumentException("Formato do campo de interesse invalido.");
					}
				}
			}
		}
	}

	/**
	 * 
	 * Funcao que cadastra uma nova pesquisa no sistema atraves de uma descricao e
	 * campo de interesse passados.
	 * 
	 * @param descricao        pequeno texto que resume o assunto da pesquisa
	 * @param campoDeInteresse Campo(s) a que a pesquisa se relaciona
	 * @return o codigo da pesquisa cadastrada
	 */
	public String cadastraPesquisa(String descricao, String campoDeInteresse) {
		Util.validaAtributo(descricao, "Descricao nao pode ser nula ou vazia.");
		validaCampoDeInteresse(campoDeInteresse);
		String codigo = geraCodigo(pesquisas, campoDeInteresse);
		pesquisas.put(codigo, new Pesquisa(descricao, campoDeInteresse, codigo));
		return codigo;

	}

	/**
	 * 
	 * Altera um atributo da pesquisa, ou a descricao ou o Campo de Interesse
	 * 
	 * @param codigo               codigo da pesquisa a ser alterada
	 * @param conteudoASerAlterado atributo a ser alterado
	 * @param novoConteudo         novo valor a ser adicionado
	 */
	public void alteraPesquisa(String codigo, String conteudoASerAlterado, String novoConteudo) {

		Util.validaAtributo(conteudoASerAlterado, "Conteudo a ser alterado nao pode ser nulo ou vazio");
		Util.validaAtributo(codigo, "Codigo nao pode ser nulo ou vazio.");
		validaPesquisa(codigo);

		if (!pesquisas.get(codigo).ehAtiva()) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}
		if (conteudoASerAlterado.equals("DESCRICAO")) {
			Util.validaAtributo(novoConteudo, "Descricao nao pode ser nula ou vazia.");
			pesquisas.get(codigo).setDescricao(novoConteudo);
		} else if (conteudoASerAlterado.equals("CAMPO")) {
			validaCampoDeInteresse(novoConteudo);
			pesquisas.get(codigo).setCampoDeInteresse(novoConteudo);
		} else {
			throw new IllegalArgumentException("Nao e possivel alterar esse valor de pesquisa.");
		}
	}

	/**
	 * 
	 * Funcao que encerra (desativa) uma pesquisa e recebe o motivo da desativacao.
	 * 
	 * @param codigo codigo da pesquisa
	 * @param motivo motivo de ser desativada a pesquisa.
	 */
	public void encerraPesquisa(String codigo, String motivo) {
		Util.validaAtributo(codigo, "Codigo nao pode ser nulo ou vazio.");
		Util.validaAtributo(motivo, "Motivo nao pode ser nulo ou vazio.");
		validaPesquisa(codigo);
		pesquisas.get(codigo).encerraPesquisa();
	}

	/**
	 * 
	 * Ativa uma pesquisa desativada, retorna uma excessao caso a pesquisa
	 * soliciatada ja esteja ativada.
	 * 
	 * @param codigo
	 */
	public void ativaPesquisa(String codigo) {
		Util.validaAtributo(codigo, "Codigo nao pode ser nulo ou vazio.");
		validaPesquisa(codigo);
		pesquisas.get(codigo).ativaPesquisa();
	}

	/**
	 * 
	 * Retorna a representacao em string de uma pesquisa no formato: "Codigo -
	 * Descricao - Campo de Interesse."
	 * 
	 * @param codigo codigo da pesquisa
	 * @return string que representa uma pesquisa.
	 */
	public String exibePesquisa(String codigo) {
		Util.validaAtributo(codigo, "Codigo nao pode ser nulo ou vazio.");
		validaPesquisa(codigo);
		return pesquisas.get(codigo).toString();
	}

	/**
	 * Retorna o estado de uma pesquisa, se e ativa ou desativa.
	 * 
	 * @param codigo codigo da pesquisa
	 * @return string true caso a pesquisa esteja ativa e false caso nao esteja.
	 */
	public boolean ehAtiva(String codigo) {
		Util.validaAtributo(codigo, "Codigo nao pode ser nulo ou vazio.");
		validaPesquisa(codigo);

		if (pesquisas.get(codigo).ehAtiva())
			return true;
		return false;
	}

	/**
	 * Associa um problema a uma pesquisa e retorna se a operacao foi efetuada com
	 * sucesso ou nao.
	 * 
	 * Caso o id da pesquisa seja nulo ou vazio sera lancado um IllegalArgumentException:
	 * "Campo idPesquisa nao pode ser nulo ou vazio."
	 * Caso o id do problema seja nulo ou vazio sera lancado um IllegalArgumentException:
	 * "Campo idProblema nao pode ser nulo ou vazio."
	 * Caso o id da pesquisa nao remeta a nenhuma pesquisa sera lancado um IllegalArgumentException:
	 * "Pesquisa nao encontrada."
	 * Caso a pesquisa nao esteja ativa sera lancado um IllegalArgumentException:
	 * "Pesquisa desativada."
	 * 
	 * @param idPesquisa e o id que remete a pesquisa.
	 * @param idProblema e o id que remete ao problema.
	 * @return e retornado um boolean indicando se a operacao foi efetuada com sucesso
	 * ou nao.
	 */
	public boolean associaProblema(String idPesquisa, String idProblema) {
		Util.validaAtributo(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		Util.validaAtributo(idProblema, "Campo idProblema nao pode ser nulo ou vazio.");
		validaPesquisa(idPesquisa);
		if (!ehAtiva(idPesquisa))
			throw new IllegalArgumentException("Pesquisa desativada.");

		return this.pesquisas.get(idPesquisa).associaProblema(idProblema);
	}
	
	/**
	 * Desassocia um problema a uma pesquisa e retorna se a operacao foi efetuada com
	 * sucesso ou nao.
	 * 
	 * Caso o id da pesquisa seja nulo ou vazio sera lancado um IllegalArgumentException:
	 * "Campo idPesquisa nao pode ser nulo ou vazio."
	 * Caso o id do problema seja nulo ou vazio sera lancado um IllegalArgumentException:
	 * "Campo idProblema nao pode ser nulo ou vazio."
	 * Caso o id da pesquisa nao remeta a nenhuma pesquisa sera lancado um IllegalArgumentException:
	 * "Pesquisa nao encontrada."
	 * Caso a pesquisa nao esteja ativa sera lancado um IllegalArgumentException:
	 * "Pesquisa desativada."
	 * 
	 * @param idPesquisa e o id que remete a pesquisa.
	 * @param idProblema e o id que remete ao problema.
	 * @return e retornado um boolean indicando se a operacao foi efetuada com sucesso
	 * ou nao.
	 */
	public boolean desassociaProblema(String idPesquisa, String idProblema) {
		Util.validaAtributo(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		Util.validaAtributo(idProblema, "Campo idProblema nao pode ser nulo ou vazio.");
		validaPesquisa(idPesquisa);
		if (!ehAtiva(idPesquisa))
			throw new IllegalArgumentException("Pesquisa desativada.");

		return this.pesquisas.get(idPesquisa).desassociaProblema(idProblema);
	}

	/**
	 * Associa um objetivo a uma pesquisa e retorna se a operacao foi efetuada com
	 * sucesso ou nao.
	 * 
	 * Caso o id da pesquisa seja nulo ou vazio sera lancado um IllegalArgumentException:
	 * "Campo idPesquisa nao pode ser nulo ou vazio."
	 * Caso o id do objetivo seja nulo ou vazio sera lancado um IllegalArgumentException:
	 * "Campo idObjetivo nao pode ser nulo ou vazio."
	 * Caso o id da pesquisa nao remeta a nenhuma pesquisa sera lancado um IllegalArgumentException:
	 * "Pesquisa nao encontrada."
	 * Caso a pesquisa nao esteja ativa sera lancado um IllegalArgumentException:
	 * "Pesquisa desativada."
	 * 
	 * @param idPesquisa e o id que remete a pesquisa.
	 * @param idObjetivo e o id que remete ao problema.
	 * @return e retornado um boolean indicando se a operacao foi efetuada com sucesso
	 * ou nao.
	 */
	public boolean associaObjetivo(String idPesquisa, String idObjetivo) {
		Util.validaAtributo(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		Util.validaAtributo(idObjetivo, "Campo idObjetivo nao pode ser nulo ou vazio.");
		validaPesquisa(idPesquisa);
		if (!ehAtiva(idPesquisa))
			throw new IllegalArgumentException("Pesquisa desativada.");

		return this.pesquisas.get(idPesquisa).associaObjetivo(idObjetivo);
	}
	
	/**
	 * Associa um objetivo a uma pesquisa e retorna se a operacao foi efetuada com
	 * sucesso ou nao.
	 * 
	 * Caso o id da pesquisa seja nulo ou vazio sera lancado um IllegalArgumentException:
	 * "Campo idPesquisa nao pode ser nulo ou vazio."
	 * Caso o id do objetivo seja nulo ou vazio sera lancado um IllegalArgumentException:
	 * "Campo idObjetivo nao pode ser nulo ou vazio."
	 * Caso o id da pesquisa nao remeta a nenhuma pesquisa sera lancado um IllegalArgumentException:
	 * "Pesquisa nao encontrada."
	 * Caso a pesquisa nao esteja ativa sera lancado um IllegalArgumentException:
	 * "Pesquisa desativada."
	 * 
	 * @param idPesquisa e o id que remete a pesquisa.
	 * @param idObjetivo e o id que remete ao problema.
	 * @return e retornado um boolean indicando se a operacao foi efetuada com sucesso
	 * ou nao.
	 */
	public boolean desassociaObjetivo(String idPesquisa, String idObjetivo) {
		Util.validaAtributo(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		Util.validaAtributo(idObjetivo, "Campo idObjetivo nao pode ser nulo ou vazio.");
		validaPesquisa(idPesquisa);
		if (!ehAtiva(idPesquisa))
			throw new IllegalArgumentException("Pesquisa desativada.");

		return this.pesquisas.get(idPesquisa).desassociaObjetivo(idObjetivo);
	}

	/**
	 * Define o criterio de ordenacao a partir da string recebida,
	 * ordena e retorna as pesquisas atuais com o criterio definido.
	 *
	 * Caso a ordenacao nao exista sera lancado um IllegalArgumentException: 
	 * "Valor invalido da ordem"
	 * 
	 * @param ordem e o tipo de ordenacao.
	 * @return e retornado a lista de pesquisas ordenadas.
	 */
	private ArrayList<Pesquisa> ordenaPesquisas(String ordem) {
		OrdenaPesquisa criterio;
		switch (ordem) {
			case "PROBLEMA":
				criterio = new CriterioProblema();
				break;
			case "OBJETIVOS":
				criterio = new CriterioObjetivo();
				break;
			case "PESQUISA":
				criterio = new CriterioPesquisa();
				break;		
			default:
				throw new IllegalArgumentException("Valor invalido da ordem");
		}
		
		ArrayList<Pesquisa> pesquisasOrdenadas = new ArrayList<>();
		pesquisasOrdenadas.addAll(this.pesquisas.values());
		
		Collections.sort(pesquisasOrdenadas, criterio);
		return pesquisasOrdenadas;
	}
	
	/**
	 * Ordena as pesquisas a partir de uma ordem e lista elas no seguinte formato:
	 * "CODIGO1 - DESCRICAO1 - CAMPODEINTERESSE1 | "CODIGOX - DESCRICAOX - CAMPODEINTERESSEX"
	 * 
	 * @param ordem e o tipo de ordenacao.
	 * @return e retornado a lista ordenada de pesquisas no formato acima.
	 */
	public String listaPesquisas(String ordem) {
		ArrayList<Pesquisa> pesquisasOrdenadas = ordenaPesquisas(ordem);
		
		String resultado = "";
		Iterator<Pesquisa> it = pesquisasOrdenadas.iterator();
		while (it.hasNext()) {
			Pesquisa pesquisa = it.next();
			if (it.hasNext()) {
				resultado += String.format("%s - %s - %s", pesquisa.getCodigo(), pesquisa.getDescricao(),
				pesquisa.getCampoDeInteresse()) + " | ";
			} else {
				resultado += String.format("%s - %s - %s", pesquisa.getCodigo(), pesquisa.getDescricao(),
				pesquisa.getCampoDeInteresse());
			}
		}
		return resultado;
	}

	public String buscaPesquisa(String termo) {
		String listagem = "";
		for (Pesquisa pesquisa : pesquisas.values()) {
			if (pesquisa.getDescricao().toLowerCase().contains(termo.toLowerCase())) {
				if (listagem.isEmpty()) {
					listagem += pesquisa.getCodigo() + " - " + pesquisa.getDescricao();
				} else {
					listagem += " | " + pesquisa.getCodigo() + " - " + pesquisa.getDescricao();
				}
			}
			if (pesquisa.getCampoDeInteresse().toLowerCase().contains(termo.toLowerCase())) {
				if (listagem.isEmpty()) {
					listagem += pesquisa.getCodigo() + " - " + pesquisa.getCampoDeInteresse();
				} else {
					listagem += " | " + pesquisa.getCodigo() + " - " + pesquisa.getCampoDeInteresse();
				}
			}
		}
		return listagem;
	}

	public boolean associaAtividade(String codigoPesquisa, Atividade atividade) {
		Util.validaAtributo(codigoPesquisa, "Campo codigoPesquisa nao pode ser nulo ou vazio.");
		if (!pesquisas.containsKey(codigoPesquisa)) {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}
		if (!pesquisas.get(codigoPesquisa).ehAtiva()) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}
		return pesquisas.get(codigoPesquisa).associaAtividade(atividade);

	}

	public boolean desassociaAtividade(String codigoPesquisa, Atividade atividade) {
		Util.validaAtributo(codigoPesquisa, "Campo codigoPesquisa nao pode ser nulo ou vazio.");
		if (!pesquisas.containsKey(codigoPesquisa)) {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}
		if (!pesquisas.get(codigoPesquisa).ehAtiva()) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}
		return pesquisas.get(codigoPesquisa).desassociaAtividade(atividade);
	}
}