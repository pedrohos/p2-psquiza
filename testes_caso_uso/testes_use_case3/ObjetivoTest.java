package testes_use_case3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import psquiza.entidades.Objetivo;

class ObjetivoTest {

	@Test
	void testConstroiObjetivoTipoInvalido() {
		// Objetivo com tipo vazio
		try {
			new Objetivo("", "Ajudar animais ameacados pelo vazamento de petroleo", 3, 4, "O12");
			fail("");
		} catch (Exception e) {
			
		}
		
		// Objetivo com tipo nulo
		try {
			new Objetivo(null, "Ajudar animais ameacados pelo vazamento de petroleo", 3, 4, "O12");
			fail("");
		} catch (Exception e) {
					
		}
		
		// Objetivo com tipo invalido
		try {
			new Objetivo("Meio Ambiente", "Ajudar animais ameacados pelo vazamento de petroleo", 3, 4, "O12");
			fail("");
		} catch (Exception e) {
					
		}
	}
	
	@Test
	void testConstroiObjetivoDescricaoInvalido() {
		// Objetivo com descicao vazia
		try {
			new Objetivo("ESPECIFICO", "", 3, 4, "O12");
			fail("");
		} catch (Exception e) {
			
		}
		
		// Objetivo com descricao nula
		try {
			new Objetivo("ESPECIFICO", null, 3, 4, "O12");
			fail("");
		} catch (Exception e) {
					
		}
	}
	
	@Test
	void testConstroiObjetivoAderenciaInvalida() {
		// Objetivo com aderencia abaixo do limite
		try {
			new Objetivo("ESPECIFICO", "Ajudar animais ameacados pelo vazamento de petroleo", 0, 4, "O12");
			fail("");
		} catch (Exception e) {
			
		}
		
		// Objetivo com aderencia acima do limite
		try {
			new Objetivo("ESPECIFICO", "Ajudar animais ameacados pelo vazamento de petroleo", 6, 4, "O12");
			fail("");
		} catch (Exception e) {
					
		}
	}
	
	@Test
	void testConstroiObjetivoViabilidadeInvalido() {
		// Objetivo com viabilidade abaixo do limite
		try {
			new Objetivo("ESPECIFICO", "Ajudar animais ameacados pelo vazamento de petroleo", 3, 0, "O12");
			fail("");
		} catch (Exception e) {
			
		}
		
		// Objetivo com viabilidade acima do limite
		try {
			new Objetivo("ESPECIFICO", "Ajudar animais ameacados pelo vazamento de petroleo", 3, 6, "O12");
			fail("");
		} catch (Exception e) {
					
		}
	}
	
	@Test
	void testConstroiObjetivoCodigoInvalido() {
		// Objetivo com codigo vazio
		try {
			new Objetivo("ESPECIFICO", "Ajudar animais ameacados pelo vazamento de petroleo", 3, 4, "");
			fail("");
		} catch (Exception e) {
			
		}
		
		// Objetivo com codigo nulo
		try {
			new Objetivo("ESPECIFICO", "Ajudar animais ameacados pelo vazamento de petroleo", 3, 4, null);
			fail("");
		} catch (Exception e) {
					
		}
	}
	
	
	@Test
	void testToString() {
		Objetivo o =  new Objetivo("ESPECIFICO", "Ajudar animais ameacados pelo vazamento de petroleo", 3, 4, "O12");
		assertEquals(o.toString(), "O12 - ESPECIFICO - Ajudar animais ameacados pelo vazamento de petroleo - 7");
	}

}
