package psquiza.controladores;

import java.io.Serializable;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.LinkedHashMap;
import psquiza.Util;
import psquiza.entidades.Atividade;
import psquiza.entidades.Pesquisa;
import psquiza.entidades.Pesquisador;
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
public class ControladorPesquisa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Colecao de pesquisas, no formato: Chave - Pesquisa, onde um codigo gerado
	 * pelo sistema identifica unicamente uma pesquisa.
	 */
	private LinkedHashMap<String, Pesquisa> pesquisas;
	
	private String estrategia;
	/**
	 * Cria um controle de pesquisa e inicializa a coleção de pesquisas.
	 */
	public ControladorPesquisa() {
		pesquisas = new LinkedHashMap<String, Pesquisa>();
		this.estrategia = "MAIS_ANTIGA";
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
	private void existePesquisa(String codigo) {
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
					if (campo.trim().length() < 3) {
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
		existePesquisa(codigo);

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
		existePesquisa(codigo);
		pesquisas.get(codigo).encerraPesquisa();
	}

	/**
	 * Ativa uma pesquisa desativada, retorna uma excessao caso a pesquisa
	 * soliciatada ja esteja ativada.
	 * 
	 * @param codigo codigo da pesquisa
	 */
	public void ativaPesquisa(String codigo) {
		Util.validaAtributo(codigo, "Codigo nao pode ser nulo ou vazio.");
		existePesquisa(codigo);
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
		existePesquisa(codigo);
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
		existePesquisa(codigo);
		if (pesquisas.get(codigo).ehAtiva())
			return true;
		return false;
	}

	/**
	 * Associa um problema a uma pesquisa e retorna se a operacao foi efetuada com
	 * sucesso ou nao.
	 * 
	 * Caso o id da pesquisa seja nulo ou vazio sera lancado um
	 * IllegalArgumentException: "Campo idPesquisa nao pode ser nulo ou vazio." Caso
	 * o id do problema seja nulo ou vazio sera lancado um IllegalArgumentException:
	 * "Campo idProblema nao pode ser nulo ou vazio." Caso o id da pesquisa nao
	 * remeta a nenhuma pesquisa sera lancado um IllegalArgumentException: "Pesquisa
	 * nao encontrada." Caso a pesquisa nao esteja ativa sera lancado um
	 * IllegalArgumentException: "Pesquisa desativada."
	 * 
	 * @param idPesquisa e o id que remete a pesquisa.
	 * @param idProblema e o id que remete ao problema.
	 * @return e retornado um boolean indicando se a operacao foi efetuada com
	 *         sucesso ou nao.
	 */
	public boolean associaProblema(String idPesquisa, String idProblema) {
		Util.validaAtributo(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		Util.validaAtributo(idProblema, "Campo idProblema nao pode ser nulo ou vazio.");
		existePesquisa(idPesquisa);
		if (!ehAtiva(idPesquisa))
			throw new IllegalArgumentException("Pesquisa desativada.");

		return this.pesquisas.get(idPesquisa).associaProblema(idProblema);
	}

	/**
	 * Desassocia um problema a uma pesquisa e retorna se a operacao foi efetuada
	 * com sucesso ou nao.
	 * 
	 * Caso o id da pesquisa seja nulo ou vazio sera lancado um
	 * IllegalArgumentException: "Campo idPesquisa nao pode ser nulo ou vazio." Caso
	 * o id do problema seja nulo ou vazio sera lancado um IllegalArgumentException:
	 * "Campo idProblema nao pode ser nulo ou vazio." Caso o id da pesquisa nao
	 * remeta a nenhuma pesquisa sera lancado um IllegalArgumentException: "Pesquisa
	 * nao encontrada." Caso a pesquisa nao esteja ativa sera lancado um
	 * IllegalArgumentException: "Pesquisa desativada."
	 * 
	 * @param idPesquisa e o id que remete a pesquisa.
	 * @param idProblema e o id que remete ao problema.
	 * @return e retornado um boolean indicando se a operacao foi efetuada com
	 *         sucesso ou nao.
	 */
	public boolean desassociaProblema(String idPesquisa) {
		Util.validaAtributo(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		existePesquisa(idPesquisa);
		if (!ehAtiva(idPesquisa))
			throw new IllegalArgumentException("Pesquisa desativada.");

		return this.pesquisas.get(idPesquisa).desassociaProblema();
	}

	/**
	 * Associa um objetivo a uma pesquisa e retorna se a operacao foi efetuada com
	 * sucesso ou nao.
	 * 
	 * Caso o id da pesquisa seja nulo ou vazio sera lancado um
	 * IllegalArgumentException: "Campo idPesquisa nao pode ser nulo ou vazio." Caso
	 * o id do objetivo seja nulo ou vazio sera lancado um IllegalArgumentException:
	 * "Campo idObjetivo nao pode ser nulo ou vazio." Caso o id da pesquisa nao
	 * remeta a nenhuma pesquisa sera lancado um IllegalArgumentException: "Pesquisa
	 * nao encontrada." Caso a pesquisa nao esteja ativa sera lancado um
	 * IllegalArgumentException: "Pesquisa desativada."
	 * 
	 * @param idPesquisa e o id que remete a pesquisa.
	 * @param idObjetivo e o id que remete ao problema.
	 * @return e retornado um boolean indicando se a operacao foi efetuada com
	 *         sucesso ou nao.
	 */
	public boolean associaObjetivo(String idPesquisa, String idObjetivo) {
		Util.validaAtributo(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		Util.validaAtributo(idObjetivo, "Campo idObjetivo nao pode ser nulo ou vazio.");
		existePesquisa(idPesquisa);
		if (!ehAtiva(idPesquisa))
			throw new IllegalArgumentException("Pesquisa desativada.");

		return this.pesquisas.get(idPesquisa).associaObjetivo(idObjetivo);
	}

	/**
	 * Associa um objetivo a uma pesquisa e retorna se a operacao foi efetuada com
	 * sucesso ou nao.
	 * 
	 * Caso o id da pesquisa seja nulo ou vazio sera lancado um
	 * IllegalArgumentException: "Campo idPesquisa nao pode ser nulo ou vazio." Caso
	 * o id do objetivo seja nulo ou vazio sera lancado um IllegalArgumentException:
	 * "Campo idObjetivo nao pode ser nulo ou vazio." Caso o id da pesquisa nao
	 * remeta a nenhuma pesquisa sera lancado um IllegalArgumentException: "Pesquisa
	 * nao encontrada." Caso a pesquisa nao esteja ativa sera lancado um
	 * IllegalArgumentException: "Pesquisa desativada."
	 * 
	 * @param idPesquisa e o id que remete a pesquisa.
	 * @param idObjetivo e o id que remete ao problema.
	 * @return e retornado um boolean indicando se a operacao foi efetuada com
	 *         sucesso ou nao.
	 */
	public boolean desassociaObjetivo(String idPesquisa, String idObjetivo) {
		Util.validaAtributo(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		Util.validaAtributo(idObjetivo, "Campo idObjetivo nao pode ser nulo ou vazio.");
		existePesquisa(idPesquisa);
		if (!ehAtiva(idPesquisa))
			throw new IllegalArgumentException("Pesquisa desativada.");

		return this.pesquisas.get(idPesquisa).desassociaObjetivo(idObjetivo);
	}

	/**
	 * Define o criterio de ordenacao a partir da string recebida, ordena e retorna
	 * as pesquisas atuais com o criterio definido.
	 *
	 * Caso a ordenacao nao exista sera lancado um IllegalArgumentException: "Valor
	 * invalido da ordem"
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
	 * "CODIGO1 - DESCRICAO1 - CAMPODEINTERESSE1 | "CODIGOX - DESCRICAOX -
	 * CAMPODEINTERESSEX"
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

	/**
	 * Metodo que busca na colecao de pesquisas que possuam o termo informado na
	 * descricao e nos campos de interesses.
	 * 
	 * @param termo termo a ser buscado.
	 * 
	 * @return lista de resultados.
	 */
	public String buscaPesquisa(String termo) {
		String listagem = "";
		List<Pesquisa> aux = pesquisas.values().stream().collect(Collectors.toList());
		Collections.sort(aux, new CriterioPesquisa());
		for (Pesquisa pesquisa : aux) {
			if (pesquisa.getDescricao().toLowerCase().contains(termo.toLowerCase())) {
				if (listagem.isEmpty()) {
					listagem += pesquisa.getCodigo() + ": " + pesquisa.getDescricao();
				} else {
					listagem += " | " + pesquisa.getCodigo() + ": " + pesquisa.getDescricao();
				}
			}
			if (pesquisa.getCampoDeInteresse().toLowerCase().contains(termo.toLowerCase())) {
				if (listagem.isEmpty()) {
					listagem += pesquisa.getCodigo() + ": " + pesquisa.getCampoDeInteresse();
				} else {
					listagem += " | " + pesquisa.getCodigo() + ": " + pesquisa.getCampoDeInteresse();
				}
			}
		}

		return listagem;
	}

	/**
	 * Metodo que associa uma atividade a uma pesquisa
	 * 
	 * @param codigoPesquisa codigo da pesquisa
	 * @param atividade      atividade que seja associada
	 * @return retorna true caso a atividade seja associada, false caso nao seja
	 */
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

	/**
	 * Metodo que desassocia uma atividade a uma pesquisa
	 * 
	 * @param codigoPesquisa codigo da pesquisa
	 * @param atividade      codigo da atividade
	 * @return retorna true caso a atividade seja removida, falso caso nao seja
	 */
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

	/**
	 * Metodo para verifica se uma ativiade esta associada a uma pesquisa
	 * 
	 * @param codigoAtividade codigo da atividade
	 * @return retorna true se estiver associada, false caso nao esteja
	 */
	public boolean verificaAssosiacaoAtividade(String codigoAtividade) {
		for (String chave : pesquisas.keySet()) {
			Pesquisa p = pesquisas.get(chave);
			if (p.verificaAssosiacaoAtividade(codigoAtividade)) {
				return true;
			}
		}
		throw new IllegalArgumentException("Atividade sem associacoes com pesquisas.");
	}

	/**
	 * 
	 * Recebe um pesquisador que será associado a pesquisa passada. Lanca erros em
	 * caso da pesquisa não existir ou estar desativada e ainda se os parametros
	 * recebios forme nulos, vazios ou com formato inválido. Uma pesquisa é
	 * associada a um pesquisador quando a pesquisa ainda não tem aquele pesquisador
	 * em seu array e não é associada caso a pesquisa já exista no array.
	 * 
	 * @param pesquisador pesquisador que sera adicionado na pesquisa.
	 * @param codigo      codigo da pesquisa que recebera o pesquisador
	 * @return (true) caso a pesquisa seja associada e (false) caso não possa
	 *         ocorrer a associacao.
	 */
	public boolean associaPesquisador(Pesquisador pesquisador, String codigo) {
		Util.validaAtributo(codigo, "Campo idPesquisa nao pode ser nulo ou vazio.");
		if (pesquisas.containsKey(codigo)) {
			return pesquisas.get(codigo).associaPesquisador(pesquisador);
		} else {
			throw new NullPointerException("Pesquisa nao encontrada.");
		}

	}

	/**
	 * 
	 * Recebe um pesquisador que será desassociado a pesquisa passada. Lanca erros
	 * em caso da pesquisa não existir ou estar desativada e ainda se os parametros
	 * recebios forme nulos, vazios ou com formato inválido. Uma pesquisa é
	 * desassociada a um pesquisador quando a pesquisa ainda não tem aquele
	 * pesquisador em seu array e não é desassociada caso a pesquisa já exista no
	 * array.
	 * 
	 * @param pesquisador pesquisador que sera removido na pesquisa.
	 * @param codigo      codigo da pesquisa que recebera o pesquisador
	 * @return (true) caso a pesquisa seja associada e (false) caso não possa
	 *         ocorrer a associacao.
	 */
	public boolean desassociaPesquisador(Pesquisador pesquisador, String codigo) {
		Util.validaAtributo(codigo, "Campo idPesquisa nao pode ser nulo ou vazio.");
		if (pesquisas.containsKey(codigo)) {
			return pesquisas.get(codigo).desassociaPesquisador(pesquisador);
		} else {
			throw new NullPointerException("Pesquisa nao encontrada.");
		}

	}
	
	/**
	 * {@link psquiza.controladores.Sistema#configuraEstrategia(String)}
	 */
	public void configuraEstrategia(String estrategia) {
		Util.validaAtributo(estrategia, "Estrategia nao pode ser nula ou vazia.");
		if(estrategia.equals("MAIS_ANTIGA") || estrategia.equals("MENOS_PENDENCIAS") || estrategia.equals("MAIOR_RISCO") || estrategia.equals("MAIOR_DURACAO")){
			this.estrategia = estrategia;
		}else {
			throw new IllegalArgumentException("Valor invalido da estrategia");
		}
		
	}
	/**
	 * {@link psquiza.controladores.Sistema#proximaAtividade(String)}
	 */
	public String proximaAtividade(String codigoPesquisa) {
		Util.validaAtributo(codigoPesquisa, "Pesquisa nao pode ser nula ou vazia.");
		if(!pesquisas.containsKey(codigoPesquisa)) {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}
		if(!pesquisas.get(codigoPesquisa).ehAtiva()) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}
		return pesquisas.get(codigoPesquisa).proximaAtividade(this.estrategia);
	}
	
	public static void escritor(String path, Pesquisa p) throws IOException {
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));

		buffWrite.append("novo arquivo");
		buffWrite.close();
	}

	public void gravarResumo(String codigoPesquisa) {
		Util.validaAtributo(codigoPesquisa, "Pesquisa nao pode ser nula ou vazia.");
		existePesquisa(codigoPesquisa);
		
		String caminho = String.format("%s/easyaccept/%s.txt",System.getProperty("user.dir"),codigoPesquisa);
		
		try {
			new File(caminho);
			escritor(caminho, pesquisas.get(codigoPesquisa));
		}catch(Exception e) {
			System.out.println(e);
		}
		

	}

	public void gravarResultados(String codigoPesquisa) {
		Util.validaAtributo(codigoPesquisa, "Pesquisa nao pode ser nula ou vazia.");
		existePesquisa(codigoPesquisa);
		
		String caminho = String.format("%s/easyaccept/%s-Resultados.txt",System.getProperty("user.dir"),codigoPesquisa);
		
		try {
			new File(caminho);
			escritor(caminho, pesquisas.get(codigoPesquisa));
		}catch(Exception e) {
			System.out.println(e);
		}
		
	}

}