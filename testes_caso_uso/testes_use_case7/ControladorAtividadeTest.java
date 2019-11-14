package testes_use_case7;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import psquiza.controladores.ControladorAtividade;
import psquiza.entidades.Atividade;

class ControladorAtividadeTest {

	private ControladorAtividade controller;

	@BeforeEach
	void preparaController() {
		controller = new ControladorAtividade();
		controller.cadastraAtividade("Atividade", "ALTO", "e");
		controller.cadastraItem("A1", "Oi");
	}

	@Test
	void testGetAtividade() {
		assertEquals(controller.getAtividade("A1"), new Atividade("Atividade", "ALTO", "e", "A1"));
	}

	@Test
	void testGetAtividadeCodigoNulo() {
		try {
			controller.getAtividade(null);
			fail("Excessao deve ser lancada");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testGetAtividadeCodigoVazio() {
		try {
			controller.getAtividade("");
			fail("Excessao deve ser lancada");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testGetAtividadeInexistente() {
		try {
			controller.getAtividade("A5");
			fail("Excessao deve ser lancada");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testExecutaAtividade() {
		controller.executaAtividade("A1", 1, 20);
	}

	@Test
	void testExecutaAtividadeItemNegativo() {
		try {
			controller.executaAtividade("A1", -1, 20);
			fail("Excessao deve ser lancada");
		} catch (IllegalArgumentException e) {
		}
	}
	
	@Test
	void testExecutaAtividadeDuracaoNegativa() {
		try {
			controller.executaAtividade("A1", 1, -1);
			fail("Excessao deve ser lancada");
		} catch (IllegalArgumentException e) {
		}
	}
	
	@Test
	void testExecutaAtividadeInexistente() {
		try {
			controller.executaAtividade("A10", 1, 20);
			fail("Excessao deve ser lancada");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testCadastraResultado() {
		fail("Not yet implemented");
	}

	@Test
	void testRemoveResultado() {
		fail("Not yet implemented");
	}

	@Test
	void testListaResultados() {
		fail("Not yet implemented");
	}

	@Test
	void testGetDuracao() {
		fail("Not yet implemented");
	}

}
