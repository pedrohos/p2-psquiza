package testes_use_case8;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import psquiza.controladores.ControladorPesquisa;

class ControladorPesquisaTest {

	@Test
	void testBuscaPesquisa() {
		ControladorPesquisa c = new ControladorPesquisa();
		c.cadastraPesquisa("Queimadas nos Oceanos.", "GERAL");

		assertEquals(c.buscaPesquisa("quei"), "GER1: Queimadas nos Oceanos.");
		assertEquals(c.buscaPesquisa("Quei"), "GER1: Queimadas nos Oceanos.");
		assertEquals(c.buscaPesquisa("QUEI"), "GER1: Queimadas nos Oceanos.");

		assertEquals(c.buscaPesquisa("GERA"), "GER1: GERAL");
		assertEquals(c.buscaPesquisa("geRa"), "GER1: GERAL");
		assertEquals(c.buscaPesquisa("gera"), "GER1: GERAL");

		c.cadastraPesquisa("Queimadas em icebergs.", "GERAL");

		assertEquals(c.buscaPesquisa("quei"), "GER2: Queimadas em icebergs. | GER1: Queimadas nos Oceanos.");
		assertEquals(c.buscaPesquisa("Quei"), "GER2: Queimadas em icebergs. | GER1: Queimadas nos Oceanos.");
		assertEquals(c.buscaPesquisa("QUEI"), "GER2: Queimadas em icebergs. | GER1: Queimadas nos Oceanos.");
		
		assertEquals(c.buscaPesquisa("GERA"), "GER2: GERAL | GER1: GERAL");
		assertEquals(c.buscaPesquisa("geRa"), "GER2: GERAL | GER1: GERAL");
		assertEquals(c.buscaPesquisa("gera"), "GER2: GERAL | GER1: GERAL");
		
		assertEquals(c.buscaPesquisa("geraldo"), "");
	}
}
