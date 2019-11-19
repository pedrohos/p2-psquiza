package testes_use_case5;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import psquiza.controladores.Sistema;

class ControladorPesquisaTest {

	private Sistema sistema;
	String pesquisa1;
	String pesquisa2;
	String objetivo1;
	String objetivo2;
	String atividade1;
	String problema1;
	String problema2;

	@BeforeEach
	void criaControle() {
		sistema = new Sistema();

		sistema.cadastraObjetivo("ESPECIFICO", "Ajudar animais ameacados pelo vazamento de petroleo", 3, 4);
		sistema.cadastraObjetivo("GERAL", "Descobrir se o culpado pelo óleo ta na ufcg", 2, 3);
		sistema.cadastraProblema("Tirar o oleo do mar e salvar os peixinhos", 1);
		sistema.cadastraProblema("Ajudar a tirar o oleo do mar", 4);
		pesquisa1 = sistema.cadastraPesquisa("Pesquisa sobre vasamento de petroleo", "petroleo, animais, vasamento");
		pesquisa2 = sistema.cadastraPesquisa("Pesquisa sobre produtos quimicos nos mares",
				"Produtos quimicos, mar, problemas");
		atividade1 = "A1";
		objetivo1 = "O1";
		objetivo2 = "O2";
		problema1 = "P1";
		problema2 = "P2";

	}

	@Test
	void associaProblemaIdPesquisaVazia() {
		try {
			sistema.associaProblema("", problema1);
			fail("Deve lancar uma excessao");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	void associaProblemaIdPesquisaNulo() {
		try {
			sistema.associaProblema(null, problema1);
			fail("Deve lancar uma excessao");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	void associaProblemaIdProblemaVazio() {
		try {
			sistema.associaProblema(pesquisa1, "");
			fail("Deve lancar uma excessao");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	void associaProblemaIdProblemaNulo() {
		try {
			sistema.associaProblema(pesquisa1, null);
			fail("Deve lancar uma excessao");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	void associaProblemaInexistente() {
		try {
			sistema.associaProblema(pesquisa1, "P3");
			fail("Deve lancar uma excessao");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	void associaProblemaPesquisaInexistente() {
		try {
			sistema.associaProblema("COM5", problema1);
			fail("Deve lancar uma excessao");
		} catch (NullPointerException e) {

		}
	}

	@Test
	void associaProblemaJaAssociado() {
		assertTrue(sistema.associaProblema(pesquisa1, problema1));
		assertFalse(sistema.associaProblema(pesquisa1, problema1));
	}

	@Test
	void associaProblemaJaAssociadoAUmaPesquisa() {
		assertTrue(sistema.associaProblema(pesquisa1, problema1));
		try {
			sistema.associaProblema(pesquisa1, problema2);
			fail("Deve lancada uma excessao");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	void associaProblemaAUmaPesquisaDesativada() {
		sistema.encerraPesquisa(pesquisa1, "Tanto faz");
		try {
			sistema.associaProblema(pesquisa1, problema1);
			fail("Deve lancada uma excessao");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	void desassociaProblemaNaoAssociado() {
		assertFalse(sistema.desassociaProblema(pesquisa1));
	}

	@Test
	void desassociaProblemaPesquisaVazia() {
		try {
			sistema.desassociaProblema("");
			fail("Deve lancar uma excessao");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	void desassociaProblemaPesquisaNula() {
		try {
			sistema.desassociaProblema(null);
			fail("Deve lancar uma excessao");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	void desassociaProblemaPesquisaInexistente() {
		try {
			sistema.desassociaProblema("LAB5");
			fail("Deve lancar uma excessao");
		} catch (NullPointerException e) {

		}
	}

	@Test
	void associaObjetivoPesquisaVazio() {
		try {
			sistema.associaObjetivo("", objetivo1);
			fail("Deve lancar uma excessao");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void associaObjetivoPesquisaNula() {
		try {
			sistema.associaObjetivo(null, objetivo1);
			fail("Deve lancar uma excessao");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	void associaObjetivoPesquisaDesativada() {
		sistema.encerraPesquisa(pesquisa1, "Porque eu quero");
		try {
			sistema.associaObjetivo(pesquisa1, objetivo1);
			fail("Deve lancar uma excessao");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	void associaObjetivoPesquisaInexistente() {
		try {
			sistema.associaObjetivo("LAB5", objetivo1);
			fail("Deve lancar uma excessao");
		} catch (NullPointerException e) {

		}
	}

	@Test
	void associaObjetivoIdObjetivoVazio() {
		try {
			sistema.associaObjetivo(pesquisa1, "");
			fail("Deve lancar uma excessao");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	void associaObjetivoIdObjetivoNulo() {
		try {
			sistema.associaObjetivo(pesquisa1, null);
			fail("Deve lancar uma excessao");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	void associaObjetivoInexistente() {
		try {
			sistema.associaObjetivo(pesquisa1, "O5");
			fail("Deve lancar uma excessao");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	void associaObjetivoADuasPesquisas() {
		assertTrue(sistema.associaObjetivo(pesquisa1, objetivo1));
		try {
			sistema.associaObjetivo(pesquisa2, objetivo1);
			fail("Deve lancar uma excessao");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	void associaObjetivoJaAssociado() {
		assertTrue(sistema.associaObjetivo(pesquisa1, objetivo1));
		assertFalse(sistema.associaObjetivo(pesquisa1, objetivo1));
	}

	@Test
	void desassociaObjetivoNaoAssociado() {
		assertFalse(sistema.desassociaObjetivo(pesquisa1, objetivo1));
	}

	@Test
	void desassociaObjetivoPesquisaVazia() {
		try {
			sistema.desassociaObjetivo("", objetivo1);
			fail("Deve lancar uma excessao");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	void desassociaObjetivoPesquisaNull() {
		try {
			sistema.desassociaObjetivo(null, objetivo1);
			fail("Deve lancar uma excessao");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	void desassociaObjetivoPesquisaInexistente() {
		try {
			sistema.desassociaObjetivo("LAB5", objetivo1);
			fail("Deve lancar uma excessao");
		} catch (NullPointerException e) {

		}
	}

	@Test
	void desassociaObjetivoIdObjetivoVazio() {
		try {
			sistema.desassociaObjetivo(pesquisa1, "");
			fail("Deve lancar uma excessao");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	void desassociaObjetivoIdObjetivoNulo() {
		try {
			sistema.desassociaObjetivo(pesquisa1, null);
			fail("Deve lancar uma excessao");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	void desassociaObjetivoIdObjetivoInexistente() {
		try {
			sistema.desassociaObjetivo(pesquisa1, "O15");
			fail("Deve lancar uma excessao");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	void listaPesquisasOrdemVazia() {
		try {
			sistema.listaPesquisas("");
			fail("Deve lancar uma excessao");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	void listaPesquisasOrdemNula() {
		try {
			sistema.listaPesquisas(null);
			fail("Deve lancar uma excessao");
		} catch (NullPointerException e) {

		}
	}

	@Test
	void listaPesquisasOrdemInvalida() {
		try {
			sistema.listaPesquisas("ATIVIDADE");
			fail("Deve lancar uma excessao");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	void listaPesquisaPeloObjetivo() {
		sistema.associaObjetivo(pesquisa1, objetivo1);
		sistema.associaObjetivo(pesquisa2, objetivo2);

		assertEquals(sistema.listaPesquisas("OBJETIVOS"),
				"PRO1 - Pesquisa sobre produtos quimicos nos mares - Produtos quimicos, mar, problemas | PET1 - Pesquisa sobre vasamento de petroleo - petroleo, animais, vasamento");
	}

	@Test
	void listaPesquisaPeloProblema() {
		sistema.associaProblema(pesquisa1, problema1);
		sistema.associaProblema(pesquisa2, problema2);

		assertEquals(sistema.listaPesquisas("PROBLEMA"),
				"PRO1 - Pesquisa sobre produtos quimicos nos mares - Produtos quimicos, mar, problemas | PET1 - Pesquisa sobre vasamento de petroleo - petroleo, animais, vasamento");
	}
	
	@Test
	void listaPesquisaPelaPesquisa() {
		sistema.associaObjetivo(pesquisa1, objetivo1);
		sistema.associaObjetivo(pesquisa2, objetivo2);
		sistema.associaProblema(pesquisa1, problema1);
		sistema.associaProblema(pesquisa2, problema2);

		assertEquals(sistema.listaPesquisas("PESQUISA"),
				"PRO1 - Pesquisa sobre produtos quimicos nos mares - Produtos quimicos, mar, problemas | PET1 - Pesquisa sobre vasamento de petroleo - petroleo, animais, vasamento");
	}

}
