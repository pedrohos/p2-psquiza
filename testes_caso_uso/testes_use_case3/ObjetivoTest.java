package testes_use_case3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import psquiza.entidades.Objetivo;
import psquiza.entidades.Problema;

class ObjetivoTest {

	@Test
	void testConstroiObjetivoTipoInvalido() {
		// Objetivo com tipo vazio
		try {
			Objetivo o =  new Objetivo("", "Ajudar animais ameacados pelo vazamento de petroleo", 3, 4, "O12");
			fail("");
		} catch (Exception e) {
			
		}
		
		// Objetivo com tipo nulo
		try {
			Objetivo o =  new Objetivo(null, "Ajudar animais ameacados pelo vazamento de petroleo", 3, 4, "O12");
			fail("");
		} catch (Exception e) {
					
		}
	}
	
	@Test
	void testConstroiObjetivoDescricaoInvalido() {
		// Objetivo com descicao vazia
		try {
			Objetivo o =  new Objetivo("ESPECIFICO", "", 3, 4, "O12");
			fail("");
		} catch (Exception e) {
			
		}
		
		// Objetivo com descricao nula
		try {
			Objetivo o =  new Objetivo("ESPECIFICO", null, 3, 4, "O12");
			fail("");
		} catch (Exception e) {
					
		}
	}
	
	@Test
	void testConstroiObjetivoAderenciaInvalida() {
		// Objetivo com aderencia abaixo do limite
		try {
			Objetivo o =  new Objetivo("ESPECIFICO", "Ajudar animais ameacados pelo vazamento de petroleo", 0, 4, "O12");
			fail("");
		} catch (Exception e) {
			
		}
		
		// Objetivo com aderencia acima do limite
		try {
			Objetivo o =  new Objetivo("ESPECIFICO", "Ajudar animais ameacados pelo vazamento de petroleo", 6, 4, "O12");
			fail("");
		} catch (Exception e) {
					
		}
	}
	
	@Test
	void testConstroiObjetivoViabilidadeInvalido() {
		// Objetivo com viabilidade abaixo do limite
		try {
			Objetivo o =  new Objetivo("ESPECIFICO", "Ajudar animais ameacados pelo vazamento de petroleo", 3, 0, "O12");
			fail("");
		} catch (Exception e) {
			
		}
		
		// Objetivo com viabilidade acima do limite
		try {
			Objetivo o =  new Objetivo("ESPECIFICO", "Ajudar animais ameacados pelo vazamento de petroleo", 3, 6, "O12");
			fail("");
		} catch (Exception e) {
					
		}
	}
	
	@Test
	void testConstroiObjetivoCodigoInvalido() {
		// Objetivo com codigo vazio
		try {
			Objetivo o =  new Objetivo("ESPECIFICO", "Ajudar animais ameacados pelo vazamento de petroleo", 3, 4, "");
			fail("");
		} catch (Exception e) {
			
		}
		
		// Objetivo com codigo nulo
		try {
			Objetivo o =  new Objetivo("ESPECIFICO", "Ajudar animais ameacados pelo vazamento de petroleo", 3, 4, null);
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
