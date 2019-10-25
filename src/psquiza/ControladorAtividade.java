package psquiza;

import java.util.HashMap;

public class ControladorAtividade {
	private HashMap<String, Atividade> atividades;
	private String idAtual;
	private int codigoId;

	private boolean existeAtividade(String codigo) {
		if (this.atividades.containsKey(codigo)) {
			return true;
		}
		return false;
	}

	public ControladorAtividade() {
		this.atividades = new HashMap<>();
		this.codigoId = 1;
		this.idAtual = "A" + codigoId;
	}

	public void cadastraAtividade(String descricao, String nivelRisco, String descricaoRisco) {
		this.atividades.put(this.idAtual, new Atividade(descricao, nivelRisco, descricaoRisco, this.idAtual));
		this.codigoId++;
		this.idAtual = "A" + codigoId;
	}

	public void apagaAtividade(String id) {
		Util.validaAtributo(id, "Campo codigo nao pode ser nulo ou vazio.");
		if (!existeAtividade(id))
			throw new IllegalArgumentException("Atividade nao encontrada");

		this.atividades.remove(id);
	}

	public void cadastraItem(String id, String item) {
		Util.validaAtributo(id, "Campo codigo nao pode ser nulo ou vazio.");
		if (!existeAtividade(id))
			throw new IllegalArgumentException("Atividade nao encontrada");

		this.atividades.get(id).cadastraItem(item);
	}

	public String exibeAtividade(String id) {
		Util.validaAtributo(id, "Campo codigo nao pode ser nulo ou vazio.");
		if (!existeAtividade(id))
			throw new IllegalArgumentException("Atividade nao encontrada");

		return this.atividades.get(id).toString();
	}

	public int contaItensPendentes(String id) {
		if (!existeAtividade(id))
			throw new IllegalArgumentException("Atividade nao encontrada");
		Util.validaAtributo(id, "Campo codigo nao pode ser nulo ou vazio.");

		return this.atividades.get(id).getItensPendentes();
	}

	public int contaItensRealizados(String id) {
		if (!existeAtividade(id))
			throw new IllegalArgumentException("Atividade nao encontrada");
		Util.validaAtributo(id, "Campo codigo nao pode ser nulo ou vazio.");

		return this.atividades.get(id).getItensRealizados();
	}
}
