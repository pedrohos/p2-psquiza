package psquiza;

import java.time.Period;
import java.util.LinkedHashSet;

public class Atividade {
	private String descricao;
	private String descricaoRisco;
	private Risco nivelRisco;
	private Period periodo;
	private String id;
	private LinkedHashSet<Item> itens;

	public Atividade(String descricao, String nivelRisco, String descricaoRisco, String id) {
		Util.validaAtributo(descricao, "Campo Descricao nao pode ser nulo ou vazio.");
		Util.validaAtributo(nivelRisco, "Campo nivelRisco nao pode ser nulo ou vazio.");
		switch (nivelRisco) {
		case "BAIXO":
			this.nivelRisco = Risco.BAIXO;
			break;
		case "MEDIO":
			this.nivelRisco = Risco.MEDIO;
			break;
		case "ALTO":
			this.nivelRisco = Risco.ALTO;
			break;
		default:
			throw new IllegalArgumentException("Valor invalido do nivel do risco.");
		}
		Util.validaAtributo(descricaoRisco, "Campo descricaoRisco nao pode ser nulo ou vazio.");

		this.descricao = descricao;
		this.descricaoRisco = descricaoRisco;
		this.id = id;
		this.itens = new LinkedHashSet<>();
	}

	public void cadastraItem(String item) {
		Util.validaAtributo(item, "Item nao pode ser nulo ou vazio.");

		this.itens.add(new Item(item));
	}

	public int getItensPendentes() {
		int resultado = 0;
		for (Item item : itens) {
			if (item.getEstado().equals("PENDENTE")) {
				resultado++;
			}
		}
		return resultado;
	}

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
