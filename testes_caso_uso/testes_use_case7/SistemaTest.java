package testes_use_case7;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import psquiza.controladores.Sistema;

class SistemaTest {

	private Sistema sistema;

	@BeforeEach
	void inicializaSistema() {
		sistema = new Sistema();
		sistema.cadastraAtividade("Atividade", "BAIXO", "e baixo");
		sistema.cadastraItem("A1", "Alguma coisa");
		sistema.cadastraAtividade("Atividade2", "BAIXO", "e baixo");
		sistema.cadastraPesquisa("Pesquisa", "pesquisar");
		sistema.associaAtividade("PES1", "A1");
		sistema.cadastraPesquisa("Pesquisa", "fazer");
		sistema.encerraPesquisa("FAZ1", "Algum");
	}

	@Test
	void testAssociaAtividade() {
		assertTrue(sistema.associaAtividade("PES1", "A2"));
	}

	@Test
	void testAssociaAtividadeJaAssociada() {
		assertFalse(sistema.associaAtividade("PES1", "A1"));
	}

	@Test
	void testAssociaAtividadePesquisaInexistente() {
		try {
			sistema.associaAtividade("PES5", "A1");
			fail("Excessao deve ser lancada");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testAssociaAtividadePesquisaInativa() {
		try {
			sistema.associaAtividade("FAZ1", "A1");
			fail("Excessao deve ser lancada");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testDesassociaAtividade() {
		assertTrue(sistema.desassociaAtividade("PES1", "A1"));
	}

	@Test
	void testDesassociaAtividadeNaoAssociada() {
		assertFalse(sistema.desassociaAtividade("PES1", "A2"));
	}

	@Test
	void testDesassociaAtividadeCodigoPesquisaInexistente() {
		try {
			sistema.desassociaAtividade("PES5", "A1");
			fail("Excessao deve ser lancada");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testDesassociaAtividadeCodigoPesquisaInativa() {
		try {
			sistema.desassociaAtividade("FAZ1", "A1");
			fail("Excessao deve ser lancada");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testExecutaAtividade() {
		sistema.executaAtividade("A1", 1, 20);
	}

	@Test
	void testExecutaAtividadeCodigoNulo() {
		try {
			sistema.executaAtividade(null, 0, 20);
			fail("Excessao deve ser lancada");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testExecutaAtividadeCodigoVazio() {
		try {
			sistema.executaAtividade("", 0, 20);
			fail("Excessao deve ser lancada");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testExecutaAtividadeNaoAssociada() {
		try {
			sistema.executaAtividade("A2", 0, 20);
		} catch (IllegalArgumentException e) {
		}
	}

}
