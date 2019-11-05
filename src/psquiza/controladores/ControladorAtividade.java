package psquiza.controladores;

import java.util.HashMap;

import psquiza.Util;
import psquiza.entidades.Atividade;
import psquiza.entidades.Atividade;

/**
 * Representacao do gerenciador de Atividades no sistema.
 * 
 * Possui um ID gerador e um ID processado. Possui um mapa de atividades que sao
 * cadastradas e recuperadas pelo ID: "'A' + IDPROCESSADO" E possivel cadastrar,
 * exibir e apagar uma atividade. E possivel cadastrar e exibir itens de uma
 * atividade, bem contar os itens pendentes e os realizados.
 * 
 * @author Pedro Henrique
 */
public class ControladorAtividade {

	/**
	 * Armazena um mapa que mapeia um id a uma Atividade.
	 */
	private HashMap<String, Atividade> atividades;

	/**
	 * Representa o id final da atividade.
	 */
	private String idAtual;

	/**
	 * Representa o id gerador do id da atividade.
	 */
	private int codigoId;

	/**
	 * Verifica se existe uma atividade a partir de seu codigo. Se a atividade
	 * existir no mapa de atividades e retornado true, caso contrario false.
	 * 
	 * @param codigo e o id que identifica a atividade.
	 * @return e retornado um boolean indicando se atividade existe ou nao.
	 */
	private boolean existeAtividade(String codigo) {
		if (this.atividades.containsKey(codigo)) {
			return true;
		}
		return false;
	}

	/**
	 * Constroi o controlador criando um novo mapa de atividades, definindo o id
	 * gerador como 1 inicialmente e armazenando um ID final.
	 */
	public ControladorAtividade() {
		this.atividades = new HashMap<>();
		this.codigoId = 1;
		this.idAtual = "A" + codigoId;
	}

	/**
	 * Cadastra uma atividade no mapa de atividades. Cada atividade deve ser
	 * cadastrada com uma descricao, nivel de risco e descricao do risco.
	 * 
	 * O nivel de risco pode (deve) ser: BAIXO MEDIO ALTO
	 * 
	 * O id e mapeado como chave da atividade e um novo e gerado.
	 * 
	 * @param descricao      e a descricao da atividade.
	 * @param nivelRisco     e o nivel de risco da atividade.
	 * @param descricaoRisco e a descricao do risco da atividade.
	 */
	public void cadastraAtividade(String descricao, String nivelRisco, String descricaoRisco) {
		this.atividades.put(this.idAtual, new Atividade(descricao, nivelRisco, descricaoRisco, this.idAtual));
		this.codigoId++;
		this.idAtual = "A" + codigoId;
	}

	/**
	 * Exclui uma atividade a partir de seu id.
	 * 
	 * Caso o id seja nulo ou vazio sera lancado um IllegalArgumentException: "Campo
	 * codigo nao pode ser nulo ou vazio." Caso o id nao remeta a nenhuma atividade
	 * sera lancado um IllegalArgumentException: "Atividade nao encontrada"
	 * 
	 * @param id e o id que identifica a atividade.
	 */
	public void apagaAtividade(String id) {
		Util.validaAtributo(id, "Campo codigo nao pode ser nulo ou vazio.");
		if (!existeAtividade(id))
			throw new IllegalArgumentException("Atividade nao encontrada");

		this.atividades.remove(id);
	}

	/**
	 * Cadastra um item em uma atividade. O item e composto por uma String.
	 * 
	 * Caso o id seja nulo ou vazio sera lancado um IllegalArgumentException: "Campo
	 * codigo nao pode ser nulo ou vazio." Caso o item seja nulo ou vazio sera
	 * lancado um IllegalArgumentException: "Item nao pode ser nulo ou vazio." Caso
	 * o id nao remeta a nenhuma atividade sera lancado um IllegalArgumentException:
	 * "Atividade nao encontrada"
	 * 
	 * @param id   e o id que remete a uma atividade.
	 * @param item e a descricao do Item.
	 */
	public void cadastraItem(String id, String item) {
		Util.validaAtributo(id, "Campo codigo nao pode ser nulo ou vazio.");
		Util.validaAtributo(item, "Item nao pode ser nulo ou vazio.");
		if (!existeAtividade(id))
			throw new IllegalArgumentException("Atividade nao encontrada");

		this.atividades.get(id).cadastraItem(item);
	}

	/**
	 * Exibe a representacao String de atividade, que segue o padrao: DESCRICAO
	 * (NIVELRISCO - DESCRICAORISCO)
	 * 
	 * Caso o id seja nulo ou vazio sera lancado um IllegalArgumentException: "Campo
	 * codigo nao pode ser nulo ou vazio." Caso o id nao remeta a nenhuma atividade
	 * sera lancado um IllegalArgumentException: "Atividade nao encontrada"
	 * 
	 * @param id e o id que remete a uma atividade.
	 * @return retorna a representacao toString de dada atividade.
	 */
	public String exibeAtividade(String id) {
		Util.validaAtributo(id, "Campo codigo nao pode ser nulo ou vazio.");
		if (!existeAtividade(id))
			throw new IllegalArgumentException("Atividade nao encontrada");

		return this.atividades.get(id).toString();
	}

	/**
	 * Retorna a quantidade itens que possui o status de PENDENTE de determinada
	 * atividade.
	 * 
	 * Caso o id nao remeta a nenhuma atividade sera lancado um
	 * IllegalArgumentException: "Atividade nao encontrada" Caso o id seja nulo ou
	 * vazio sera lancado um IllegalArgumentException: "Campo codigo nao pode ser
	 * nulo ou vazio."
	 * 
	 * @param id e o id que remete a determinada atividade.
	 * @return e retornado um int indicando a quantidade de itens PENDENTE.
	 */
	public int contaItensPendentes(String id) {
		if (!existeAtividade(id))
			throw new IllegalArgumentException("Atividade nao encontrada");
		Util.validaAtributo(id, "Campo codigo nao pode ser nulo ou vazio.");

		return this.atividades.get(id).getItensPendentes();
	}

	/**
	 * Retorna a quantidade itens que possui o status de REALIZADO de determinada
	 * atividade.
	 * 
	 * Caso o id nao remeta a nenhuma atividade sera lancado um
	 * IllegalArgumentException: "Atividade nao encontrada" Caso o id seja nulo ou
	 * vazio sera lancado um IllegalArgumentException: "Campo codigo nao pode ser
	 * nulo ou vazio."
	 * 
	 * @param id e o id que remete a determinada atividade.
	 * @return e retornado um int indicando a quantidade de itens REALIZADO.
	 */
	public int contaItensRealizados(String id) {
		if (!existeAtividade(id))
			throw new IllegalArgumentException("Atividade nao encontrada");
		Util.validaAtributo(id, "Campo codigo nao pode ser nulo ou vazio.");

		return this.atividades.get(id).getItensRealizados();
	}

	public String buscaAtividade(String termo) {
		String listagem = "";
		for (Atividade atividade : atividades.values()) {
			if (atividade.getDescricao().toLowerCase().contains(termo.toLowerCase())) {
				if (listagem.isEmpty()) {
					listagem += atividade.getId() + " - " + atividade.getDescricao();
				} else {
					listagem += " | " + atividade.getId() + " - " + atividade.getDescricao();
				}
			}
			if (atividade.getDescricaoRisco().toLowerCase().contains(termo.toLowerCase())) {
				if (listagem.isEmpty()) {
					listagem += atividade.getId() + " - " + atividade.getDescricaoRisco();
				} else {
					listagem += " | " + atividade.getId() + " - " + atividade.getDescricaoRisco();
				}
			}
		}
		return listagem;
	}
}
