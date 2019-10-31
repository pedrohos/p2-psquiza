package psquiza.entidades;

import java.time.Period;
import java.util.LinkedHashSet;

import psquiza.Util;
import psquiza.enums.Risco;

/**
 * Representacao de uma Atividade no sistema.
 * Cada Atividade apresenta uma descricao, um nivel
 * de risco, uma descricao do risco, uma duracao (periodo),
 * um id que o identifica unicamente em seu controlador e
 * uma lista de itens que compoem a Atividade.
 * 
 * O nivel de risco deve ser um dos 3:
 * BAIXO
 * MEDIO
 * ALTO
 * 
 * E possivel alem de criar uma Atividade, cadastrar um item,
 * retornar a quantidade de itens no status de PENDENTE e de
 * REALIZADO.
 * 
 * @author Pedro Henrique
 */
public class Atividade {
	
	/**
	 * Armazena a descricao da Atividade.
	 */
	private String descricao;
	
	/**
	 * Armazena a descricao do risco da Atividade.
	 */
	private String descricaoRisco;
	
	/**
	 * Armazena o nivel de risco da Atividade, podem estar em um
	 * de 3 estados:
	 * BAIXO, MEDIO ou ALTO
	 */
	private Risco nivelRisco;
	
	/**
	 * Representa a duracao da Atividade.
	 */
	private Period periodo;
	
	/**
	 * Representa o identificador unico da Atividade.
	 */
	private String id;
	
	/**
	 * Armazena um conjunto ordenado de itens que compoem a atividade.
	 */
	private LinkedHashSet<Item> itens;

	/**
	 * Constroi um nova atividade, validando se os parametros passados
	 * sao vazios ou nulos, caso sejam sera lancado um IllegalArgumentException:
	 * 
	 * Descricao vazia ou nulo: "Campo Descricao nao pode ser nulo ou vazio."
	 * Nivel de Risco vazio ou nulo: "Campo nivelRisco nao pode ser nulo ou vazio."
	 * ID vazio ou nulo: "Campo codigo nao pode ser nulo ou vazio."
	 * Descricao do Risco vazia ou nula: "Campo descricaoRisco nao pode ser nulo ou vazio."
	 * 
	 * Caso o Nivle de Risco nao seja BAIXO, MEDIO ou ALTO, sera lancado um
	 * IllegalArgumentException: "Valor invalido do nivel do risco."
	 * 
	 * @param descricao e a descricao da atividade.
	 * @param nivelRisco e o nivel de risco da ativdade.
	 * @param descricaoRisco e a descricao do risco da atividade.
	 * @param id e o identificador unico da atividade.
	 */
	public Atividade(String descricao, String nivelRisco, String descricaoRisco, String id) {
		Util.validaAtributo(descricao, "Campo Descricao nao pode ser nulo ou vazio.");
		Util.validaAtributo(nivelRisco, "Campo nivelRisco nao pode ser nulo ou vazio.");
		Util.validaAtributo(id, "Campo codigo nao pode ser nulo ou vazio.");
		this.nivelRisco = Risco.atribuiRisco(nivelRisco);
		Util.validaAtributo(descricaoRisco, "Campo descricaoRisco nao pode ser nulo ou vazio.");

		this.descricao = descricao;
		this.descricaoRisco = descricaoRisco;
		this.id = id;
		this.itens = new LinkedHashSet<>();
	}

	/**
	 * Cadastra um item no conjunto ordenado de itens da Atividade.
	 * 
	 * Caso o item seja vazio ou nulo sera lancado um IllegalArgumentException:
	 * "Item nao pode ser nulo ou vazio."
	 * 
	 * @param item e o item que sera adicionado.
	 */
	public void cadastraItem(String item) {
		Util.validaAtributo(item, "Item nao pode ser nulo ou vazio.");

		this.itens.add(new Item(item));
	}

	/**
	 * Retorna a quantidade de itens com o status de PENDENTE.
	 * 
	 * @return e retornado a quantidade de itens PENDENTE.
	 */
	public int getItensPendentes() {
		int resultado = 0;
		for (Item item : itens) {
			if (item.getEstado().equals("PENDENTE")) {
				resultado++;
			}
		}
		return resultado;
	}

	/**
	 * Retorna a quantidade de itens com o status de REALIZADO.
	 * 
	 * @return e retornado a quantidade de itens REALIZADO.
	 */
	public int getItensRealizados() {
		int resultado = 0;
		for (Item item : itens) {
			if (item.getEstado().equals("REALIZADO")) {
				resultado++;
			}
		}
		return resultado;
	}

	private String getDescricao() {
		return this.descricao;
	}

	private String getNivelRisco() {
		return this.nivelRisco.getRisco();
	}

	private String getDescricaoRisco() {
		return this.descricaoRisco;
	}

	/**
	 * Retorna a representacao toString de Atividade no formato:
	 * DESCRICAO (NIVELRISCO - DESCRICAORISCO)
	 * 
	 * @return e retornado a representacao toString de Atividade.
	 */
	@Override
	public String toString() {
		return String.format("%s (%s - %s)", getDescricao(), getNivelRisco(), getDescricaoRisco());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Atividade other = (Atividade) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}