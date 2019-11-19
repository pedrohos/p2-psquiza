package testes_use_case5;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import psquiza.controladores.ControladorPesquisa;
import psquiza.controladores.Sistema;
import psquiza.entidades.Atividade;
import psquiza.entidades.Pesquisa;

class ControladorPesquisaTest {

	private Sistema sistema;
	String pesquisa1;
	String pesquisa2;
	String objetivo1;
	String objetivo2;
	String atividade1;
	String problema1;
	String problema2;

	@BeforeEach
	void criaControle() {
		sistema = new Sistema();

		pesquisa1 = sistema.cadastraPesquisa("Pesquisa sobre vasamento de petroleo", "petroleo, animais, vasamento");
		pesquisa2 = sistema.cadastraPesquisa("Pesquisa sobre produtos quimicos nos mares", "Produtos quimicos, mar, problemas");
		sistema.cadastraObjetivo("ESPECIFICO", "Ajudar animais ameacados pelo vazamento de petroleo", 3, 4);
		sistema.cadastraObjetivo("GERAL", "Descobrir se o culpado pelo óleo ta na ufcg", 2, 3);
		sistema.cadastraProblema("Tirar o oleo do mar e salvar os peixinhos", 1);
		sistema.cadastraProblema("Ajudar a tirar o oleo do mar", 4);
		atividade1 = "A1";
		objetivo1 = "O1";
		objetivo2 = "O2";
		problema1 = "P1";
		problema2 = "P2";

	}

	@Test
	void associaProblemaIdPesquisaVazia() {
		try {
			sistema.associaAtividade("", problema1);
			fail("Deve lancar uma excessao");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	void associaProblemaIdPesquisaNulo() {
		try {
			sistema.associaProblema(null, problema1);
			fail("Deve lancar uma excessao");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	void associaProblemaIdProblemaVazio() {
		try {
			sistema.associaAtividade(pesquisa1, "");
			fail("Deve lancar uma excessao");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	void associaProblemaIdProblemaNulo() {
		try {
			sistema.associaAtividade(pesquisa1, null);
			fail("Deve lancar uma excessao");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	void associaProblemaInexistente() {
		try {
			sistema.associaAtividade(pesquisa1, "P3");
			fail("Deve lancar uma excessao");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	void associaProblemaPesquisaInexistente() {
		try {
			sistema.associaAtividade("COM5", problema1);
			fail("Deve lancar uma excessao");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	void associaProblemaJaAssociado() {
		assertTrue(sistema.associaProblema(pesquisa1, problema1));
		assertFalse(sistema.associaProblema(pesquisa1, problema1));
	}
	
	@Test
	void associaProblemaJaAssociadoAUmaPesquisa() {
		assertTrue(sistema.associaProblema(pesquisa1, problema1));
		try{
			sistema.associaProblema(pesquisa2, problema1);
			fail("deve ser lancada uma excessao");
		}catch(IllegalArgumentException e) {
			
		}
	}

	@Test
	void associaObjetivo() {
		try {
			sistema.associaAtividade(pesquisa1, null);
			fail("Deve lancar uma excessao");
		} catch (IllegalArgumentException e) {

		}
	}

}
