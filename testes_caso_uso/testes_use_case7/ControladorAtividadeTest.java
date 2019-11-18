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
		controller.cadastraAtividade("A", "BAIXO", "a");
		controller.cadastraItem("A1", "Oi");
		controller.cadastraResultado("A1", "R1");
		controller.cadastraResultado("A1", "R2");
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
		assertEquals(3, controller.cadastraResultado("A1", "R"));
	}

	@Test
	void testCadastraResultadoAtividadeInexistente() {
		try {
			controller.cadastraResultado("A10", "OI");
			fail("Excessao deveria ser lancada");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testCadastraResultadoCodigoNulo() {
		try {
			controller.cadastraResultado(null, "R");
			fail("excessao deveria ser lancada");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testCadastraResultadoCodigoVazio() {
		try {
			controller.cadastraResultado("", "R");
			fail("excessao deveria ser lancada");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testCadastraResultadoNulo() {
		try {
			controller.cadastraResultado("A1", null);
			fail("excessao deveria ser lancada");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testCadastraResultadoVazio() {
		try {
			controller.cadastraResultado("A1", "");
			fail("excessao deveria ser lancada");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testRemoveResultado() {
		assertTrue(controller.removeResultado("A1", 1));
	}

	@Test
	void testRemoveResultadoCodigoNulo() {
		try {
			controller.removeResultado(null, 1);
			fail("excessao deveria ser lancada");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testRemoveResultadoCodigoVazio() {
		try {
			controller.removeResultado("", 1);
			fail("excessao deveria ser lancada");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testRemoveResultadoAtividadeInexistente() {
		try {
			controller.removeResultado("A5", 1);
			fail("excessao deveria ser lancada");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testRemoveResultadoNumeroResultadoNegativo() {
		try {
			controller.removeResultado("A5", -1);
			fail("excessao deveria ser lancada");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testRemoveResultadoNumeroResultadoZero() {
		try {
			controller.removeResultado("A5", 0);
			fail("excessao deveria ser lancada");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testRemoveResultadoNumeroResultadoNaoCadastrado() {
		try {
			controller.removeResultado("A5", 4);
			fail("excessao deveria ser lancada");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testListaResultados() {
		assertEquals("R1 | R2", controller.listaResultados("A1"));
	}

	@Test
	void testListaResultadosCodigoNulo() {
		try {
			controller.listaResultados(null);
			fail("Excessao deve ser lancada");
		} catch (IllegalArgumentException e) {
		}
	}
	
	@Test
	void testListaResultadosCodigoVazio() {
		try {
			controller.listaResultados(" ");
			fail("Excessao deve ser lancada");
		} catch (IllegalArgumentException e) {
		}
	}
	
	@Test
	void testListaResultadosAtividadeInexistente() {
		try {
			controller.listaResultados("A9");
			fail("Excessao deve ser lancada");
		} catch (IllegalArgumentException e) {
		}
	}
	
	@Test
	void testListaResultadosAtividadeSemResultados() {
		assertEquals("", controller.listaResultados("A2"));
	}

	@Test
	void testGetDuracao() {
		controller.executaAtividade("A1", 1, 20);
		controller.cadastraItem("A1", "OI");
		controller.executaAtividade("A1", 2, 7);
		assertEquals(27, controller.getDuracao("A1"));
	}
	
	@Test
	void testGetDuracaoCodigoNulo() {
		try {
			controller.getDuracao(null);
			fail("Excessao deveria ser lancada");
		} catch (IllegalArgumentException e) {
		}
	}
	
	@Test
	void testGetDuracaoCodigoVazio() {
		try {
			controller.getDuracao(" ");
			fail("Excessao deveria ser lancada");
		} catch (IllegalArgumentException e) {
		}
	}
	
	@Test
	void testGetDuracaoCodigoAtividadeInexistente() {
		try {
			controller.getDuracao("A5");
			fail("Excessao deveria ser lancada");
		} catch (IllegalArgumentException e) {
		}
	}

}
