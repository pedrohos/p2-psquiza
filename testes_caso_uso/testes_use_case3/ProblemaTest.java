package testes_use_case3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import psquiza.entidades.Problema;

class ProblemaTest {

	@Test
	void testConstroiProblemaDescricaoInvalida() {
		// Problema com descricao vazio
		try {
			Problema p =  new Problema("", 1, "15");
			fail("");
		} catch (Exception e) {
			
		}
		
		// Problema com descricao nula
		try {
			Problema p =  new Problema(null, 1, "15");
			fail("");
		} catch (Exception e) {
			
		}
	}
	
	@Test
	void testConstroiProblemaViabilidadeInvalida() {
		// Problema com viabilidade invalida acima do limite.
		try {
			Problema p =  new Problema("Vazamento de petroleo no oceano", 6, "P15");
			fail("");
		} catch (Exception e) {
			
		}
		
		// Problema com viabilidade invalida abaixo do limite.
		try {
			Problema p =  new Problema("Vazamento de petroleo no oceano", 0, "P15");
			fail("");
		} catch (Exception e) {
			
		}
	}
	
	@Test
	void testConstroiProblemaCodigoInvalido() {
		// Problema com codigo vazio.
		try {
			Problema p =  new Problema("Vazamento de petroleo no oceano", 3, "");
			fail("");
		} catch (Exception e) {
			
		}
		// Problema com codigo nulo.
		try {
			Problema p =  new Problema("Vazamento de petroleo no oceano", 3, null);
			fail("");
		} catch (Exception e) {
			
		}	
	}
	
	@Test
	void testToString() {
		Problema p = new Problema("Aquecimento Global", 4, "P1");
		assertEquals(p.toString(), "P1 - Aquecimento Global - 4");
	}

}
