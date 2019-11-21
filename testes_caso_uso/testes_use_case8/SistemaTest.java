package testes_use_case8;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import psquiza.controladores.Sistema;

class SistemaTest {

	@Test
	void testBuscaUmParametro() {
		Sistema s = new Sistema();
		
		s.cadastraPesquisa("Reconhecimento de pes", "saude");
		s.cadastraPesquisador("Charleu Luie", "PROFESSOR", "Professor renomado no ambito medicinal", "charleu@xac.lulel.com", "https://charleu.com");
		s.cadastraProblema("Reconhecer curvaturas atraves de algoritmos", 4);
		s.cadastraObjetivo("GERAL", "Reconhecer tipo de pe atraves do processamento da imagem fotografada do pe", 3, 5);
		s.cadastraAtividade("Retirar fotos de pes a fim de reconhecimento", "BAIXO", "Retirar fotos dos pes de voluntarios");
		
		assertEquals(s.busca("reconhe"),"SAU1: Reconhecimento de pes | P1: Reconhecer curvaturas atraves de algoritmos | "
									  + "O1: Reconhecer tipo de pe atraves do processamento da imagem fotografada do pe | "
									  + "A1: Retirar fotos de pes a fim de reconhecimento");
	}
	
	@Test
	void testBuscaDoisParametros() {
		Sistema s = new Sistema();
		
		s.cadastraPesquisa("Reconhecimento de pes", "saude");
		s.cadastraPesquisador("Charleu Luie", "PROFESSOR", "Professor renomado no ambito medicinal", "charleu@xac.lulel.com", "https://charleu.com");
		s.cadastraProblema("Reconhecer curvaturas atraves de algoritmos", 4);
		s.cadastraObjetivo("GERAL", "Reconhecer tipo de pe atraves do processamento da imagem fotografada do pe", 3, 5);
		s.cadastraAtividade("Retirar fotos de pes a fim de reconhecimento", "BAIXO", "Retirar fotos dos pes de voluntarios");
		
		assertEquals(s.busca("re", 4), "O1: Reconhecer tipo de pe atraves do processamento da imagem fotografada do pe");
	}
	
	@Test
	void testContaResultadoPesquisa() {
		Sistema s = new Sistema();
		
		s.cadastraPesquisa("Reconhecimento de pes", "saude");
		s.cadastraPesquisador("Charleu Luie", "PROFESSOR", "Professor renomado no ambito medicinal", "charleu@xac.lulel.com", "https://charleu.com");
		s.cadastraProblema("Reconhecer curvaturas atraves de algoritmos", 4);
		s.cadastraObjetivo("GERAL", "Reconhecer tipo de pe atraves do processamento da imagem fotografada do pe", 3, 5);
		s.cadastraAtividade("Retirar fotos de pes a fim de reconhecimento", "BAIXO", "Retirar fotos dos pes de voluntarios");
		
		assertEquals(s.contaResultadosBusca("re"), 6);
	}
	
	@Test
	void testBuscaTermoInvalido() {
		Sistema s = new Sistema();
		try {
			s.busca("");
			fail();
		} catch (IllegalArgumentException iae) {
			
		}
		
		try {
			s.busca(null);
			fail();
		} catch (IllegalArgumentException iae) {
			
		}
		
		try {
			s.busca("", 0);
			fail();
		} catch (IllegalArgumentException iae) {
			
		}
		
		try {
			s.busca(null, 0);
			fail();
		} catch (IllegalArgumentException iae) {
			
		}
		
		try {
			s.contaResultadosBusca("");
			fail();
		} catch (IllegalArgumentException iae) {
			
		}
		
		try {
			s.contaResultadosBusca(null);
			fail();
		} catch (IllegalArgumentException iae) {
			
		}
	}
	
	@Test
	void testBuscaNumeroResultadoInvalido() {
		Sistema s = new Sistema();
		
		try {
			s.busca("REACAO", -5);
			fail();
		} catch (IllegalArgumentException iae) {
			
		}
	}
	
	@Test
	void testBuscas() {
		Sistema s = new Sistema();
		
		s.cadastraPesquisa("Desenvolver novo jogo de plataforma o miraio", "JOGO");
		s.cadastraPesquisador("Manuel", "PROFESSOR", "Criador do conceito de miraio", "manuel@gamemaker.com", "https://manel.com");
		s.cadastraProblema("Colisoes do miraio se sobrepondo", 4);
		s.cadastraObjetivo("GERAL", "Definir limitacoes do miraio", 4, 5);
		s.cadastraObjetivo("GERAL", "Definir limitacoes do luigiu", 4, 5);
		s.cadastraAtividade("Reescrever implementacao de colisao do mirario", "MEDIO", "Pode dificultar parte do projeto");
		
		assertEquals(s.busca("miraio"), "JOG1: Desenvolver novo jogo de plataforma o miraio | "
									  + "manuel@gamemaker.com: Criador do conceito de miraio | "
									  + "P1: Colisoes do miraio se sobrepondo | "
									  + "O1: Definir limitacoes do miraio");
		
		assertEquals(s.busca("miraio", 1), "JOG1: Desenvolver novo jogo de plataforma o miraio");
		assertEquals(s.busca("miraio", 2), "manuel@gamemaker.com: Criador do conceito de miraio");
		assertEquals(s.busca("miraio", 3), "P1: Colisoes do miraio se sobrepondo");
		
		try {
			assertEquals(s.busca("miraio", 5), "JOG1: Desenvolver novo jogo de plataforma o miraio");
			fail();
		} catch(NoSuchElementException aoobe) {
			
		}
		
	}

}
