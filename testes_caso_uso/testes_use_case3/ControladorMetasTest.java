package testes_use_case3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import psquiza.controladores.ControladorMetas;
import psquiza.entidades.Objetivo;

class ControladorMetasTest {

	@Test
	void testCadastraProblemaDescricaoInvalida() {
		ControladorMetas cm = new ControladorMetas();
		
		// Problema descricao vazia.
		try {
			cm.cadastraProblema("", 5);
			fail("");
		} catch (Exception e) {
			assertTrue(true);
		}
		
		// Problema descricao nula.
		try {
			cm.cadastraProblema(null, 5);
			fail("");
		} catch (Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	void testCadastraViabilidadeInvalida() {
		ControladorMetas cm = new ControladorMetas();
		
		// Valor de viabilidade inválido abaixo.
		try {
			cm.cadastraProblema("Preconceito contra racistas", 0);
			fail("");
		} catch (Exception e) {
			assertTrue(true);
		}
				
		// Valor de viabilidade inválido acima.
		try {
			cm.cadastraProblema("Preconceito contra racistas", 6);
			fail("");
		} catch (Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	void testConstroiObjetivoTipoInvalido() {
		ControladorMetas cm = new ControladorMetas();
		
		// Objetivo com tipo vazio
		try {
			cm.cadastraObjetivo("", "Ajudar animais ameacados pelo vazamento de petroleo", 3, 4);
			fail("");
		} catch (Exception e) {
			
		}
		
		// Objetivo com tipo nulo
		try {
			cm.cadastraObjetivo(null, "Ajudar animais ameacados pelo vazamento de petroleo", 3, 4);
			fail("");
		} catch (Exception e) {
					
		}
	}
	
	@Test
	void testConstroiObjetivoDescricaoInvalido() {
		ControladorMetas cm = new ControladorMetas();
		
		// Objetivo com descicao vazia
		try {
			cm.cadastraObjetivo("ESPECIFICO", "", 3, 4);
			fail("");
		} catch (Exception e) {
			
		}
		
		// Objetivo com descricao nula
		try {
			cm.cadastraObjetivo("ESPECIFICO", null, 3, 4);
			fail("");
		} catch (Exception e) {
					
		}
	}
	
	@Test
	void testConstroiObjetivoAderenciaInvalida() {
		ControladorMetas cm = new ControladorMetas();
		
		// Objetivo com aderencia abaixo do limite
		try {
			cm.cadastraObjetivo("ESPECIFICO", "Ajudar animais ameacados pelo vazamento de petroleo", 0, 4);
			fail("");
		} catch (Exception e) {
			
		}
		
		// Objetivo com aderencia acima do limite
		try {
			cm.cadastraObjetivo("ESPECIFICO", "Ajudar animais ameacados pelo vazamento de petroleo", 6, 4);
			fail("");
		} catch (Exception e) {
					
		}
	}
	
	@Test
	void testConstroiObjetivoViabilidadeInvalido() {
		ControladorMetas cm = new ControladorMetas();
		
		// Objetivo com viabilidade abaixo do limite
		try {
			cm.cadastraObjetivo("ESPECIFICO", "Ajudar animais ameacados pelo vazamento de petroleo", 3, 0);
			fail("");
		} catch (Exception e) {
			
		}
		
		// Objetivo com viabilidade acima do limite
		try {
			cm.cadastraObjetivo("ESPECIFICO", "Ajudar animais ameacados pelo vazamento de petroleo", 3, 6);
			fail("");
		} catch (Exception e) {
					
		}
	}
	
	@Test
	void testCadastraProblema() {
		ControladorMetas cm = new ControladorMetas();
		cm.cadastraProblema("Racismo", 5);
		cm.cadastraProblema("Preconceito racial", 4);
		cm.cadastraProblema("Intolerancia contra pessoas negras", 3);
		cm.cadastraProblema("Preocupacao por classes oprimidas pelo seu status social marcado pelo nascimento oprimido", 3);
		cm.cadastraProblema("Falta de consciencia sobre o estado social vivenciado pelas pessoas com cor de pele diferente da dominante", 1);
	}
	
	@Test
	void exibeProblema() {
		ControladorMetas cm = new ControladorMetas();
		cm.cadastraProblema("Desligar freezer por 12h", 3);
		assertEquals(cm.exibeProblema("P1"), "P1 - Desligar freezer por 12h - 3");
	}
	
	@Test
	void exibeObjetivo() {
		ControladorMetas cm = new ControladorMetas();
		cm.cadastraObjetivo("GERAL", "Trocar a placa Saborear", 5, 4);
		assertEquals(cm.exibeObjetivo("O1"), "O1 - GERAL - Trocar a placa Saborear - 9");
	}
	
	@Test
	void apagaProblema() {
		ControladorMetas cm = new ControladorMetas();
		cm.cadastraProblema("Desligar freezer por 12h", 3);
		try {
			cm.exibeProblema("P1");
		} catch (Exception e) {
			fail("");
		}
		cm.apagarProblema("P1");
		try {
			cm.exibeProblema("P1");
			fail("");
		} catch (Exception e) {
			
		}
	}
	
	@Test
	void apagaObjetivo() {
		ControladorMetas cm = new ControladorMetas();
		cm.cadastraObjetivo("GERAL", "Trocar a placa Saborear", 5, 4);
		try {
			cm.exibeObjetivo("O1");
		} catch (Exception e) {
			fail("");
		}
		cm.apagarObjetivo("O1");
		try {
			cm.exibeObjetivo("O1");
			fail("");
		} catch (Exception e) {
			
		}
	}
}
