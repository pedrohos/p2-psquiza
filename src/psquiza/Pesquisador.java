package psquiza;

public class Pesquisador {

	private String nome;
	private String biografia;
	private String email;
	private String foto;
	private String funcao;
	private boolean ativo;

	public Pesquisador(String nome, String biografia, String email, String foto, String funcao) {
		this.nome = nome;
		this.biografia = biografia;
		this.email = email;
		this.foto = foto;
		this.funcao = funcao;
		this.ativo = true;
	}

	public boolean ehAtivo() {
		return ativo;
	}
	
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		Pesquisador other = (Pesquisador) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("%s (%s) - %s - %s - %s", nome, funcao, biografia, email, foto);
	}
}
