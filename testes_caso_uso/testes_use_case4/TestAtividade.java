package testes_use_case4;

import static org.junit.Assert.*;

import org.junit.Test;

import psquiza.entidades.Atividade;

public class TestAtividade {

	@Test
	public void criaAtividade() {
		Atividade ati = new Atividade("aaa", "BAIXO", "fui", "A1");
	}

	@Test
	public void criaAtividadeDescricaoVazia() {
		try {
			Atividade ati = new Atividade("", "BAIXO", "fui", "A1");
			fail("");
		} catch (Exception e) {
		}
	}

	@Test
	public void criaAtividadeDescricaoRiscoVazio() {
		try {
			Atividade ati = new Atividade("aaa", "BAIXO", "", "A1");
			fail("");
		} catch (Exception e) {
		}
	}

	@Test
	public void criaAtividadeRiscoBaixo() {
		Atividade ati = new Atividade("aaa", "BAIXO", "fui", "A1");
	}

	@Test
	public void criaAtividadeRiscoMedio() {
		Atividade ati = new Atividade("aaa", "MEDIO", "fui", "A1");
	}

	@Test
	public void criaAtividadeRiscoAlto() {
		Atividade ati = new Atividade("aaa", "ALTO", "fui", "A1");
	}

	@Test
	public void criaAtividadeRiscoDiferente() {
		try {
			Atividade ati = new Atividade("aaa", "alto demais", "fui", "A1");
			fail("");
		} catch (Exception e) {
		}
	}

	@Test
	public void cadastraItem() {
		Atividade ati = new Atividade("aaa", "BAIXO", "fui", "A1");
		ati.cadastraItem("janela");
	}

	@Test
	public void cadastraItemVazio() {
		try {
			Atividade ati = new Atividade("aaa", "BAIXO", "fui", "A1");
			ati.cadastraItem("");
			fail("");
		} catch (Exception e) {
		}
	}

	@Test
	public void pegaItensPendentes() {
		Atividade ati = new Atividade("aaa", "BAIXO", "fui", "A1");
		ati.cadastraItem("janela");
		ati.cadastraItem("porta");
		ati.cadastraItem("celeiro");
		ati.cadastraItem("cachaca");
		assertEquals(4, ati.getItensPendentes());
	}
 
	@Test
	public void pegaItensRealizados() {
		Atividade ati = new Atividade("aaa", "BAIXO", "fui", "A1");
		ati.cadastraItem("janela");
		ati.cadastraItem("porta");
		ati.cadastraItem("celeiro");
		ati.cadastraItem("cachaca");
		assertEquals(0, ati.getItensRealizados());
	}

	@Test
	public void toStringAtividade() {
		Atividade ati = new Atividade("Monitoramento de chats dos alunos de computacao do primeiro periodo.", "BAIXO",
				"Por se tratar de apenas um monitoramento, o risco nao e elevado.", "A1");
		assertEquals(
				"Monitoramento de chats dos alunos de computacao do primeiro periodo. (BAIXO - Por se tratar de apenas um monitoramento, o risco nao e elevado.)",
				ati.toString());
	}
}
