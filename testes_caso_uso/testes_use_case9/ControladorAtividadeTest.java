package testes_use_case9;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import psquiza.controladores.Sistema;

class ControladorAtividadeTest {

	private Sistema sistema;
	private String a1 = "A1";
	private String a2 = "A2";
	private String a3 = "A3";
	private String a4 = "A4";

	@BeforeEach
	void criaControle() {
		sistema = new Sistema();
		sistema.cadastraAtividade("Monitoramento de chats dos alunos de computacao do primeiro periodo.", "BAIXO",
				"Por se tratar de apenas um monitoramento, o risco nao e elevado.");
		sistema.cadastraAtividade("Provar o café do Central Perk", "BAIXO", "Por se tratar de uma degustação de café");
		sistema.cadastraAtividade("Assistir friends enquanto faz testes", "ALTO",
				"Por se tratar de duas atividades ao mesmo tempo");
		sistema.cadastraAtividade("Fazer os testes de use case us9", "MEDIO", "Por se tratar dos testes do projeto");

	}

	@Test
	void testDefineProximaAtividadeIdPrecedenteVazio() {
		try {
			sistema.defineProximaAtividade("", a1);
			fail("deve lancar uma excessao");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	void testDefineProximaAtividadeIdPrecedenteNulo() {
		try {
			sistema.defineProximaAtividade(null, a1);
			fail("deve lancar uma excessao");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	void testDefineProximaAtividadeInexistente() {
		try {
			sistema.defineProximaAtividade("a5", a1);
			fail("deve lancar uma excessao");
		} catch (NoSuchElementException e) {

		}
	}

	@Test
	void testDefineProximaAtividadeIdSubsequenteVazio() {
		try {
			sistema.defineProximaAtividade(a1, "");
			fail("deve lancar uma excessao");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	void testDefineProximaAtividadeIdSubsequenteNulo() {
		try {
			sistema.defineProximaAtividade(a1, null);
			fail("deve lancar uma excessao");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	void testDefineProximaAtividadeSubsequenteInexistente() {
		try {
			sistema.defineProximaAtividade(a1, "a5");
			fail("deve lancar uma excessao");
		} catch (NoSuchElementException e) {

		}
	}

	@Test
	void testDefineProximaAtividade() {
		sistema.defineProximaAtividade(a1, a2);
		sistema.defineProximaAtividade(a2, a3);
		sistema.defineProximaAtividade(a3, a4);
	}

	@Test
	void testDefineProximaAtividadeCriacaoDeLoop() {
		try {
			sistema.defineProximaAtividade(a1, a2);
			sistema.defineProximaAtividade(a2, a1);
			fail("Deve lancar uma excessao");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	void testDefineProximaAtividadeCriacaoDeLoopTresAtividades() {
		try {
			sistema.defineProximaAtividade(a1, a2);
			sistema.defineProximaAtividade(a2, a3);
			sistema.defineProximaAtividade(a3, a1);
			fail("Deve lancar uma excessao");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	void testDefineProximaAtividadeMesmaAtiidadePrecedenteSubsequente() {
		try {
			sistema.defineProximaAtividade(a1, a1);
			fail("Deve lancar uma excessao");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	void testDefineProximaAtividadeDuasVezes() {
		sistema.defineProximaAtividade(a1, a2);
		try {
			sistema.defineProximaAtividade(a1, a2);
			fail("Deve lancar uma excessao");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	void testDefineProximaAtividadeDuasVezesAtividadesDiferente() {
		sistema.defineProximaAtividade(a1, a2);
		try {
			sistema.defineProximaAtividade(a1, a3);
			fail("Deve lancar uma excessao");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	void testTiraProximaAtividadeIdVazio() {
		sistema.defineProximaAtividade(a1, a2);
		try {
			sistema.tiraProximaAtividade("");
			;
			fail("Deve lancar uma excessao");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	void testTiraProximaAtividadeIdNulo() {
		sistema.defineProximaAtividade(a1, a2);
		try {
			sistema.tiraProximaAtividade(null);
			;
			fail("Deve lancar uma excessao");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	void testTiraProximaAtividadeIdInexistente() {
		sistema.defineProximaAtividade(a1, a2);
		try {
			sistema.tiraProximaAtividade("a5");
			;
			fail("Deve lancar uma excessao");
		} catch (NoSuchElementException e) {

		}
	}

	@Test
	void testTiraProximaAtividadeSemProxima() {
		sistema.tiraProximaAtividade(a1);
	}

	@Test
	void testTiraProximaAtividade() {
		sistema.defineProximaAtividade(a1, a2);
		sistema.tiraProximaAtividade(a1);
	}

	@Test
	void testContaProximoVazio() {
		try {
			sistema.contaProximos("");
			fail("Deve lancar uma excessao");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	void testContaProximoNulo() {
		try {
			sistema.contaProximos(null);
			fail("Deve lancar uma excessao");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	void testContaProximoAtividadeInexistente() {
		try {
			sistema.contaProximos("A5");
			fail("Deve lancar uma excessao");
		} catch (NoSuchElementException e) {

		}
	}

	@Test
	void testContaProximo() {

		assertEquals(0, sistema.contaProximos(a1));
		sistema.defineProximaAtividade(a1, a2);
		assertEquals(1, sistema.contaProximos(a1));
		sistema.defineProximaAtividade(a2, a3);
		assertEquals(2, sistema.contaProximos(a1));
		sistema.defineProximaAtividade(a3, a4);
		assertEquals(3, sistema.contaProximos(a1));
	}

	@Test
	void testPegaProximoIdVazio() {
		try {
			sistema.pegaProximo("", 2);
			fail("Deve lancar uma excessao");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	void testPegaProximoIdNulo() {
		try {
			sistema.pegaProximo(null, 2);
			fail("Deve lancar uma excessao");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	void testPegaProximoEnesimaNegativa() {
		try {
			sistema.pegaProximo(a1, -1);
			fail("Deve lancar uma excessao");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	void testPegaProximoInexistente() {
		try {

			sistema.defineProximaAtividade(a1, a2);
			sistema.defineProximaAtividade(a2, a3);

			assertEquals("A2", sistema.pegaProximo(a1, 3));
			fail("Deve lancar uma excessao");
		} catch (NoSuchElementException e) {

		}
	}

	@Test
	void testPegaProximo2() {
		sistema.defineProximaAtividade(a1, a2);
		sistema.defineProximaAtividade(a2, a3);

		assertEquals("A3", sistema.pegaProximo(a1, 2));

	}

	@Test
	void testPegaProximo1() {

		sistema.defineProximaAtividade(a1, a2);
		sistema.defineProximaAtividade(a2, a3);

		assertEquals("A2", sistema.pegaProximo(a1, 1));

	}

	@Test
	void testPegaProximo3() {
		
		sistema.defineProximaAtividade(a1, a2);
		sistema.defineProximaAtividade(a2, a3);
		sistema.defineProximaAtividade(a3, a4);
		
		assertEquals("A4", sistema.pegaProximo(a1, 3));
	}
	
	@Test
	void testPegaProximo12e3() {
		
		sistema.defineProximaAtividade(a1, a2);
		sistema.defineProximaAtividade(a2, a3);
		sistema.defineProximaAtividade(a3, a4);
		
		assertEquals("A2", sistema.pegaProximo(a1, 1));
		assertEquals("A3", sistema.pegaProximo(a1, 2));
		assertEquals("A4", sistema.pegaProximo(a1, 3));
	}
	
	@Test
	void testPegaMaiorRiscoVazio() {
		try {
			sistema.pegaMaiorRiscoAtividades("");
			fail("Deve lancar uma excessao");
		} catch (IllegalArgumentException e) {

		}
	}
	
	@Test
	void testPegaMaiorRiscoNulo() {
		try {
			sistema.pegaMaiorRiscoAtividades(null);
			fail("Deve lancar uma excessao");
		} catch (IllegalArgumentException e) {

		}
	}
	
	@Test
	void testPegaMaiorRiscoInexistente() {
		try {
			sistema.pegaMaiorRiscoAtividades("A5");
			fail("Deve lancar uma excessao");
		} catch (NoSuchElementException e) {

		}
	}
	
	@Test
	void testPegaMaiorRisco() {
		
		sistema.defineProximaAtividade(a1, a2);
		sistema.defineProximaAtividade(a2, a3);
		sistema.defineProximaAtividade(a3, a4);
		
		assertEquals("A3", sistema.pegaMaiorRiscoAtividades(a1));
		
	}	

}
