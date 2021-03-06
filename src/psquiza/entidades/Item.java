package psquiza.entidades;

import java.io.Serializable;

import psquiza.Util;
import psquiza.enums.Estado;

/**
 * Representacao de um Item de uma Atividade.
 * 
 * Cada Item possui um nome e um estado atual.
 * 
 * @author Pedro Henrique
 */
public class Item implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Armazena o nome do item. 
	 */
	private String nome;
	
	/**
	 * Armazena o estado atual do item.
	 */
	private Estado estadoAtual;
	
	/**
	 * Constroi um item a partir de seu nome e define por padrao
	 * o estado deste item como PENDENTE.
	 * 
	 * Caso o nome do item seja vazio ou nulo, sera lancada um IllegalArgumentException:
	 * "Item nao pode ser nulo ou vazio."
	 * 
	 * @param nome e o nome do item.
	 */
	public Item(String nome) {
		Util.validaAtributo(nome, "Item nao pode ser nulo ou vazio.");
		
		this.nome = nome;
		this.estadoAtual = Estado.PENDENTE;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public String getEstado() {
		return this.estadoAtual.getEstado();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Item other = (Item) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	public void setEstado(String string) {
		this.estadoAtual = Estado.REALIZADO;	
	}
}
