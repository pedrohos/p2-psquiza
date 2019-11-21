package testes_use_case10;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import psquiza.controladores.ControladorAtividade;
import psquiza.controladores.ControladorPesquisa;
import psquiza.controladores.Sistema;

class ControladorAtividadeTest {

	Sistema sistema;
	
	@BeforeEach
	void testPopulaAtividade() {
		sistema = new Sistema();
		
		sistema.cadastraPesquisa("Identificar o moco que vende balinha", "Fome");
		sistema.encerraPesquisa("FOM1", "Ele foi preso ;-;");
		
		sistema.cadastraPesquisa("Descobrir qual feira da fruta o batman foi", "Gotham");
		sistema.cadastraAtividade("Comprar mandioca", "BAIXO", "Conseguir o dinheiro");
		sistema.associaAtividade("GOT1", "A1");
		
		
		
		sistema.cadastraPesquisa("Confirmar males do oculus VR", "Saude");
		
		sistema.cadastraAtividade("Testar a coordenacao motora de idosos durante o VR", "ALTO",
								  "Pessoas idosas tendem a perder o equilibrio facilmente");
		sistema.cadastraItem("A2", "Testar equilibrio");
		sistema.cadastraItem("A2", "Testar movimentacao durante eventos de alta descarga de adrenalina");
		sistema.associaAtividade("SAU1", "A2");
		
		sistema.cadastraAtividade("Verificar a nocao de espaco do usuario", "BAIXO",
				                  "Pode ocorrer acidentes leves sem os devidos cuidados");
		sistema.cadastraItem("A3", "Pedir ao usuario que ande 10 passos");
		sistema.cadastraItem("A3", "Pedir que o usuario decore os limites do espaco no mundo real");
		sistema.associaAtividade("SAU1", "A3");
		sistema.executaAtividade("A3", 1, 15);
		
		sistema.cadastraAtividade("Realizar testes com os raios infra-vermelhos do equipamento", "MEDIO",
								  "Efeitos possivelmente nocivos a saude humana, como cancer por uso excessivo"
								  + "de controle remoto.");
		sistema.cadastraItem("A4", "Ligar todos os sensores simultaneamente");
		sistema.cadastraItem("A4", "Colocar dispositivos extras no caminho");
		sistema.cadastraItem("A4", "Ligar multiplos sistemas VR proximas a mesma pessoa");
		sistema.associaAtividade("SAU1", "A4");
		sistema.executaAtividade("A4", 1, 20);
	}
	
	@Test
	void testBuscaMaisAntiga() {
		sistema.configuraEstrategia("MAIS_ANTIGA");
		assertEquals(sistema.proximaAtividade("SAU1"), "A2");
		sistema.desassociaAtividade("SAU1", "A2");
		assertEquals(sistema.proximaAtividade("SAU1"), "A3");
		sistema.desassociaAtividade("SAU1", "A3");
		assertEquals(sistema.proximaAtividade("SAU1"), "A4");
	}
	
	@Test
	void testBuscaMenosPendencias() {
		sistema.configuraEstrategia("MENOS_PENDENCIAS");
		assertEquals(sistema.proximaAtividade("SAU1"), "A3");
		sistema.desassociaAtividade("SAU1", "A3");
		assertEquals(sistema.proximaAtividade("SAU1"), "A2");
		sistema.desassociaAtividade("SAU1", "A2");
		assertEquals(sistema.proximaAtividade("SAU1"), "A4");
	}
	
	@Test
	void testBuscaMaiorRisco() {
		sistema.configuraEstrategia("MAIOR_RISCO");
		assertEquals(sistema.proximaAtividade("SAU1"), "A2");
		sistema.desassociaAtividade("SAU1", "A2");
		assertEquals(sistema.proximaAtividade("SAU1"), "A4");
		sistema.desassociaAtividade("SAU1", "A4");
		assertEquals(sistema.proximaAtividade("SAU1"), "A3");
	}
	
	@Test
	void testBuscaMaiorDuracao() {
		sistema.configuraEstrategia("MAIOR_DURACAO");
		assertEquals(sistema.proximaAtividade("SAU1"), "A4");
		sistema.desassociaAtividade("SAU1", "A4");
		assertEquals(sistema.proximaAtividade("SAU1"), "A3");
		sistema.desassociaAtividade("SAU1", "A3");
		assertEquals(sistema.proximaAtividade("SAU1"), "A2");
	}
	
	@Test
	void testBuscaPesquisaInvalida() {
		try {
			sistema.proximaAtividade("");
			fail();
		} catch(IllegalArgumentException iae) {
			
		}
		
		try {
			sistema.proximaAtividade(null);
			fail();
		} catch(IllegalArgumentException iae) {
			
		}
	}
	
	@Test
	void testDefineEstrategiaInvalida() {
		try {
			sistema.configuraEstrategia("");
			fail();
		} catch(IllegalArgumentException iae) {
			
		}
		
		try {
			sistema.configuraEstrategia(null);
			fail();
		} catch(IllegalArgumentException iae) {
			
		}
	}
	
	@Test
	void testBuscaPesquisaInexistente() {
		try {
			sistema.proximaAtividade("12P");
			fail();
		} catch (IllegalArgumentException iae) {
			
		}
	}
	
	@Test
	void testBuscaPesquisaDesativada() {
		try {
			sistema.proximaAtividade("FOM1");
			fail();
		} catch (IllegalArgumentException iae) {
			
		}
	}
	
	@Test
	void testBuscaPesquisaSemPendencias() {
		try {
			sistema.proximaAtividade("GOT1");
			fail();
		} catch (IllegalArgumentException iae) {
			
		}
	}

}
