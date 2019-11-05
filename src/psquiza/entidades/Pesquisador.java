package psquiza.entidades;

import psquiza.Util;
import psquiza.enums.Funcao;

/**
 * Classe que representa o pesquisador no sistema.
 * 
 * @author Eniedson Fabiano Pereira da Silva Junior
 */
public class Pesquisador {

	/**
	 * Atributo que representa o nome do pesquisador.
	 */
	private String nome;

	/**
	 * Atributo que representa a biografia do pesquisador.
	 */
	private String biografia;

	/**
	 * Atributo que representa o email do pesquisador.
	 */
	private String email;

	/**
	 * Atributo que representa o url da foto do pesquisador.
	 */
	private String foto;

	/**
	 * Atributo que representa a função do pesquisador (estudante, professor ou
	 * externo).
	 */
	private Funcao funcao;

	/**
	 * Atributo que informa se o pesquisador esta ativo ou não.
	 */
	private boolean ativo;

	/**
	 * Metodo responsavel por construir um pesquisador, o pesquisador inicia ativo.
	 * 
	 * @param nome      nome do pesquisador.
	 * @param biografia biografia do pesquisador.
	 * @param email     email do pesquisador.
	 * @param foto      url da foto do pesquisador.
	 * @param funcao    funcao do pesquisador.
	 */
	public Pesquisador(String nome, String biografia, String email, String foto, String funcao) {
		Util.validaAtributo(nome, "Campo nome nao pode ser nulo ou vazio.");
		Util.validaAtributo(funcao, "Campo funcao nao pode ser nulo ou vazio.");
		Util.validaAtributo(biografia, "Campo biografia nao pode ser nulo ou vazio.");
		Util.validaAtributo(email, "Campo email nao pode ser nulo ou vazio.");
		Util.validaAtributo(foto, "Campo fotoURL nao pode ser nulo ou vazio.");
		Util.validaEmail(email);
		Util.validaFoto(foto);

		this.nome = nome;
		this.biografia = biografia;
		this.email = email;
		this.foto = foto;
		this.funcao = Funcao.atribuiFuncao(funcao);
		this.ativo = true;
	}

	/**
	 * Metodo que retorna se o pesquisador e ativo ou nao.
	 * 
	 * @return boolean que representa se o pesquisador e ativo ou nao.
	 */
	public boolean ehAtivo() {
		return ativo;
	}

	/**
	 * Metodo responsavel por alterar o atributo ativo.
	 * 
	 * @param ativo novo valor para ativo.
	 */
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	/**
	 * Metodo responsavel por alterar o atributo nome.
	 * 
	 * @param ativo novo valor para nome.
	 */
	public void setNome(String nome) {
		Util.validaAtributo(nome, "Campo nome nao pode ser nulo ou vazio.");
		this.nome = nome;
	}

	/**
	 * Metodo responsavel por retornar o atributo biografia.
	 * 
	 * @return valor da biografia.
	 */
	public String getBiografia() {
		return biografia;
	}

	/**
	 * Metodo responsavel por alterar o atributo biografia.
	 * 
	 * @param ativo novo valor para biografia.
	 */
	public void setBiografia(String biografia) {
		Util.validaAtributo(biografia, "Campo biografia nao pode ser nulo ou vazio.");
		this.biografia = biografia;
	}

	/**
	 * Metodo responsavel por retornar o atributo email.
	 * 
	 * @return valor do email.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Metodo responsavel por alterar o atributo email.
	 * 
	 * @param ativo novo valor para email.
	 */
	public void setEmail(String email) {
		Util.validaAtributo(email, "Campo email nao pode ser nulo ou vazio.");
		Util.validaEmail(email);
		this.email = email;
	}

	/**
	 * Metodo responsavel por alterar o atributo foto.
	 * 
	 * @param ativo novo valor para foto.
	 */
	public void setFoto(String foto) {
		Util.validaAtributo(foto, "Campo foto nao pode ser nulo ou vazio.");
		Util.validaFoto(foto);
		this.foto = foto;
	}

	/**
	 * Metodo responsavel por alterar o atributo funcao.
	 * 
	 * @param ativo novo valor para funcao.
	 */
	public void setFuncao(String funcao) {
		Util.validaAtributo(funcao, "Campo funcao nao pode ser nulo ou vazio.");
		this.funcao = Funcao.atribuiFuncao(funcao);
	}

	/**
	 * Metodo responsavel por gerar e retornar o hashcode do pesquisador.
	 * 
	 * @return hashcode do objeto.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	/**
	 * Metodo que verifica a igualdade entre o pesquisador e um outro objeto.
	 * 
	 * @param obj objeto a ser comparado.
	 * 
	 * @return boolean que informa se o objeto e igual ou nao.
	 */
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

	/**
	 * Metodo que retorna a representacao string do pesquisador.
	 * 
	 * @return representacao string do pesquisador.
	 */
	@Override
	public String toString() {
		return String.format("%s (%s) - %s - %s - %s", nome, funcao.getFuncao(), biografia, email, foto);
	}
}
