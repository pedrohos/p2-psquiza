package psquiza;

public class Item {
	private String nome;
	private Estado estadoAtual;
	
	public Item(String nome) {
		Util.validaAtributo(nome, "Item nao pode ser nulo ou vazio.");
		
		this.nome = nome;
		this.estadoAtual = Estado.PENDENTE;
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
}
