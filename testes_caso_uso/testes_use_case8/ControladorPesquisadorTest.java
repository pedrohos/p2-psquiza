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
		
		assertEquals(c.buscaPesquisador("passou"), "luciano.eaeman@xmail.com: Passou pelo IF e UF.");
		assertEquals(c.buscaPesquisador("pASSou"), "luciano.eaeman@xmail.com: Passou pelo IF e UF.");
		assertEquals(c.buscaPesquisador("PASSOU"), "luciano.eaeman@xmail.com: Passou pelo IF e UF.");
		
		c.cadastraPesquisador("Eniedsono", "PROFESSOR", "Instruiu no IF e UF.", "eniedson.kkeae@xmail.com", "https://NApesquisador.com");
		
		assertEquals(c.buscaPesquisador("if"), "luciano.eaeman@xmail.com: Passou pelo IF e UF. | eniedson.kkeae@xmail.com: Instruiu no IF e UF.");
		assertEquals(c.buscaPesquisador("iF"), "luciano.eaeman@xmail.com: Passou pelo IF e UF. | eniedson.kkeae@xmail.com: Instruiu no IF e UF.");
		assertEquals(c.buscaPesquisador("IF"), "luciano.eaeman@xmail.com: Passou pelo IF e UF. | eniedson.kkeae@xmail.com: Instruiu no IF e UF.");
		
		assertEquals(c.buscaPesquisador("if else"), "");
	}

}
