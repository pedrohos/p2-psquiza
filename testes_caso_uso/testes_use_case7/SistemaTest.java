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
		sistema.cadastraPesquisa("Pesquisa", "pesquisar");
		sistema.associaAtividade("PES1", "A1");
	}
	
	@Test
	void testAssociaAtividade() {
		sistema.cadastraAtividade("Atividade", "ALTO", "alto");
		assertTrue(sistema.associaAtividade("PES1", "A2"));
	}
	
	@Test
	void testAssociaAtividadeJaAssociada() {
		assertFalse(sistema.associaAtividade("PES1", "A1"));
	}
	
	@Test
	void testAssociaAtividadeCodigoNulo() {
		try {
			sistema.associaAtividade("PES2", null);
			fail("Excessao deve ser lancada");
		} catch (IllegalArgumentException e) {}
	}

	@Test
	void testDesassociaAtividade() {
		fail("Not yet implemented");
	}

	@Test
	void testExecutaAtividade() {
		fail("Not yet implemented");
	}

}
