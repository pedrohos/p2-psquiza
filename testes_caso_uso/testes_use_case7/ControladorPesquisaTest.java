package testes_use_case7;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import psquiza.controladores.ControladorPesquisa;
import psquiza.entidades.Atividade;

class ControladorPesquisaTest {

	private ControladorPesquisa controller;
	private Atividade a;

	@BeforeEach
	void preparaController() {
		controller = new ControladorPesquisa();
		a = new Atividade("A", "BAIXO", "A", "A1");
		controller.cadastraPesquisa("OI", "alto");
		controller.cadastraPesquisa("OI", "BAIXO");
		controller.encerraPesquisa("BAI1", "Eu quero");
	}

	@Test
	void testAssociaAtividade() {
		assertTrue(controller.associaAtividade("ALT1", a));
	}

	@Test
	void testAssociaAtividadeFalse() {
		controller.associaAtividade("ALT1", a);
		assertFalse(controller.associaAtividade("ALT1", a));
	}
	
	@Test
	void testAssociaAtividadeCodigoNulo() {
		try {
			controller.associaAtividade(null, a);
			fail("Excessao deveria ser lancada");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testAssociaAtividadeCodigoVazio() {
		try {
			controller.associaAtividade(" ", a);
			fail("Excessao deveria ser lancada");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testAssociaAtividadePesquisaInexistente() {
		try {
			controller.associaAtividade("ALT2", a);
			fail("Excessao deveria ser lancada");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testAssociaAtividadePesquisaEncerrada() {
		try {
			controller.associaAtividade("BAI1", a);
			fail("Excessao deveria ser lancada");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testDesdesassociaAtividade() {
		controller.associaAtividade("ALT1", a);
		assertTrue(controller.desassociaAtividade("ALT1", a));
	}
	
	@Test
	void testDesdesassociaAtividadeFalse() {
		assertFalse(controller.desassociaAtividade("ALT1", a));
	}

	@Test
	void testDesdesassociaAtividadeCodigoNulo() {
		try {
			controller.desassociaAtividade(null, a);
			fail("Excessao deveria ser lancada");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testDesdesassociaAtividadeCodigoVazio() {
		try {
			controller.desassociaAtividade(" ", a);
			fail("Excessao deveria ser lancada");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testDesdesassociaAtividadePesquisaInexistente() {
		try {
			controller.desassociaAtividade("ALT2", a);
			fail("Excessao deveria ser lancada");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testDesdesassociaAtividadePesquisaEncerrada() {
		try {
			controller.desassociaAtividade("BAI1", a);
			fail("Excessao deveria ser lancada");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testVerificaAssosiacaoAtividade() {
		controller.associaAtividade("ALT1", a);
		assertTrue(controller.verificaAssosiacaoAtividade("A1"));
	}

	@Test
	void testVerificaAssosiacaoAtividadeSemAssociacoes() {
		try {
			controller.verificaAssosiacaoAtividade("A1");
			fail("Excessao deveria ser lancada");
		} catch (IllegalArgumentException e) {
		}
	}

}
