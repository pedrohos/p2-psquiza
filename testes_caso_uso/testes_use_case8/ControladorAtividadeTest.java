package testes_use_case8;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import psquiza.controladores.ControladorAtividade;

class ControladorAtividadeTest {

	@Test
	void testBuscaAtividade() {
		ControladorAtividade c = new ControladorAtividade();
		
		c.cadastraAtividade("Marinheiro", "BAIXO", "Baixa chance de ferir os biceps");
		
		assertEquals(c.buscaAtividade("baixa"), "A1: Baixa chance de ferir os biceps");
		assertEquals(c.buscaAtividade("BAIXA"), "A1: Baixa chance de ferir os biceps");
		assertEquals(c.buscaAtividade("bAiXa"), "A1: Baixa chance de ferir os biceps");
		
		c.cadastraAtividade("Retirar oleo das praias", "BAIXO", "Baixa quantidade de oleo restante a ser retirado");
		
		assertEquals(c.buscaAtividade("baixa"), "A2: Baixa quantidade de oleo restante a ser retirado | A1: Baixa chance de ferir os biceps");
		assertEquals(c.buscaAtividade("BAIXA"), "A2: Baixa quantidade de oleo restante a ser retirado | A1: Baixa chance de ferir os biceps");
		assertEquals(c.buscaAtividade("bAiXa"), "A2: Baixa quantidade de oleo restante a ser retirado | A1: Baixa chance de ferir os biceps");
		
		assertEquals(c.buscaAtividade("baixo"), "");
	}

}
