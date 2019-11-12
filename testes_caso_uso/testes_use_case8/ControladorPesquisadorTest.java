package testes_use_case8;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import psquiza.controladores.ControladorPesquisador;

class ControladorPesquisadorTest {

	@Test
	void testBuscaPesquisador() {
		ControladorPesquisador c = new ControladorPesquisador();
		assertEquals(c.buscaPesquisador("IF"), "");
		
		c.cadastraPesquisador("Luciano", "ESTUDANTE", "Passou pelo IF e UF.", "luciano.eaeman@xmail.com", "https://NApesquisador.com");
		
		assertEquals(c.buscaPesquisador("Passou"), "luciano.eaeman@xmail.com - Passou pelo IF e UF.");
		
		c.cadastraPesquisador("Eniedsono", "ESTUDANT", "Passou pelo IF e UF.", "luciano.eaeman@xmail.com", "https://NApesquisador.com");
	}

}
