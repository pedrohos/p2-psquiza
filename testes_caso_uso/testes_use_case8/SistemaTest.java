package testes_use_case8;

import static org.junit.jupiter.api.Assertions.*;

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
		
		assertEquals(s.busca("reconhe"),"SAU1: Reconhecimento de pes| P1: Reconhecer curvaturas atraves de algoritmos | "
									  + "O1: Reconhecer tipo de pe atraves do processamento da imagem fotografada do pe | "
									  + "A1: Retirar fotos de pes a fim de reconhecimento");
	}
	
	@Test
	void testBuscaDoisParametros() {
		Sistema s = new Sistema();
	}
	
	@Test
	void testContaResultadoPesquisa() {
		Sistema s = new Sistema();
	}

}
