package testes_use_case8;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import psquiza.controladores.ControladorMetas;

class ControladorMetasTest {

	@Test
	void testBuscaProblema() {
		ControladorMetas c = new ControladorMetas();
		
		c.cadastraProblema("Aceitacao de USB 3.0 em portas 1.0", 3);
		
		assertEquals(c.buscaProblema("usb"), "P1: Aceitacao de USB 3.0 em portas 1.0");
		assertEquals(c.buscaProblema("uSb"), "P1: Aceitacao de USB 3.0 em portas 1.0");
		assertEquals(c.buscaProblema("USB"), "P1: Aceitacao de USB 3.0 em portas 1.0");
		
		c.cadastraProblema("Correlacao entre CC-I e CC-D", 5);
		
		assertEquals(c.buscaProblema("acao"), "P2: Correlacao entre CC-I e CC-D | P1: Aceitacao de USB 3.0 em portas 1.0");
		assertEquals(c.buscaProblema("aCAo"), "P2: Correlacao entre CC-I e CC-D | P1: Aceitacao de USB 3.0 em portas 1.0");
		assertEquals(c.buscaProblema("ACAO"), "P2: Correlacao entre CC-I e CC-D | P1: Aceitacao de USB 3.0 em portas 1.0");
		
		assertEquals(c.buscaProblema("acaocao"), "");
	}
	
	@Test
	void testBuscaObjetivo () {
		ControladorMetas c = new ControladorMetas();
		
		c.cadastraObjetivo("GERAL", "Identificar bit de giro em mercado digital", 1, 3);
		
		assertEquals(c.buscaObjetivo("bit"), "O1: Identificar bit de giro em mercado digital");
		assertEquals(c.buscaObjetivo("bIt"), "O1: Identificar bit de giro em mercado digital");
		assertEquals(c.buscaObjetivo("BIT"), "O1: Identificar bit de giro em mercado digital");
		
		c.cadastraObjetivo("GERAL", "Validar novo corretor de erro de precisao de qubit", 4, 4);
		
		assertEquals(c.buscaObjetivo("bit"), "O2: Validar novo corretor de erro de precisao de qubit | O1: Identificar bit de giro em mercado digital");
		assertEquals(c.buscaObjetivo("biT"), "O2: Validar novo corretor de erro de precisao de qubit | O1: Identificar bit de giro em mercado digital");
		assertEquals(c.buscaObjetivo("BIT"), "O2: Validar novo corretor de erro de precisao de qubit | O1: Identificar bit de giro em mercado digital");
		
		assertEquals(c.buscaObjetivo("bits"), "");
	}

}
