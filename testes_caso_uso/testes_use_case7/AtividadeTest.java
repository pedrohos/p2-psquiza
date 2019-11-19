package testes_use_case7;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import psquiza.entidades.Atividade;

class AtividadeTest {

	private Atividade a1;
	
	@BeforeEach
	void criaAtividades() {
		a1 = new Atividade("Atividade", "BAIXO", "A", "A1");
		a1.cadastraItem("I1");
		a1.cadastraItem("I2");
		a1.executaAtividade(2, 10);
		a1.cadastraResultado("R1");
		a1.cadastraResultado("R2");
		a1.removeResultado(2);
		a1.cadastraResultado("R4");
	}
	
	@Test
	void testExecutaAtividade() {
		a1.executaAtividade(1, 15);
		assertEquals(2, a1.getItensRealizados());
		assertEquals(25, a1.getDuracao());
	}
	
	@Test
	void testExecutaAtividadeNaoCadastrada() {
		try {
			a1.executaAtividade(3, 15);
			fail("Excessao deveria ser lancada");
		} catch (IllegalArgumentException e) {
		}
	}
	
	@Test
	void testExecutaAtividadeJaRealizada() {
		try {
			a1.executaAtividade(2, 15);
			fail("Excessao deveria ser lancada");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testCadastraResultado() {
		assertEquals(4, a1.cadastraResultado("R3"));
	}

	@Test
	void testRemoveResultado() {
		assertTrue(a1.removeResultado(1));
	}
	
	@Test
	void testRemoveResultadoFalse() {
		assertFalse(a1.removeResultado(2));
	}
	
	@Test
	void testRemoveResultadoInexistente() {
		try {
			a1.removeResultado(5);
			fail("Excessao deveria ser lancada");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testListaResultados() {
		assertEquals("R1 | R4", a1.listaResultados());
	}
	
	@Test
	void testListaResultadosVazio() {
		Atividade a2 = new Atividade("a", "BAIXO", "a", "oi");
		assertEquals("", a2.listaResultados());
	}

}
