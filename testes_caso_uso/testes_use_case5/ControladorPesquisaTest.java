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
	String objetivo1;
	String objetivo2;
	String atividade1;

	@BeforeEach
	void criaControle() {
		sistema = new Sistema();

		pesquisa1 = sistema.cadastraPesquisa("Pesquisa sobre vasamento de petroleo", "petroleo, animais, vasamento");
		sistema.cadastraAtividade(
				"Monitoramento de chats dos alunos de computacao do primeiro periodo que falam sobre vazamento de petroleo.",
				"BAIXO", "Por se tratar de apenas um monitoramento, o risco nao e elevado.");
		sistema.cadastraItem("A1", "Olhar o zapzap deles");
		sistema.cadastraItem("A1", "Perguntar sobre o oleo");
		sistema.cadastraItem("A1", "Descobrir o responsavel");
		sistema.cadastraObjetivo("ESPECIFICO", "Ajudar animais ameacados pelo vazamento de petroleo", 3, 4);
		sistema.cadastraObjetivo("GERAL", "Descobrir se o culpado pelo óleo ta na ufcg", 2, 3);
		atividade1 = "A1";
		objetivo1 = "O1";
		objetivo2 = "O2";

	}

	
	@Test
	void associaAtividadeIdPesquisaVazia() {
		try {
		sistema.associaAtividade("", "A1");
		fail("Deve lancar uma excessao");
		}catch(IllegalArgumentException e) {
			
		}
	}
	
	@Test
	void associaAtividadeIdPesquisaNulo() {
		try {
		sistema.associaAtividade(null, "A1");
		fail("Deve lancar uma excessao");
		}catch(IllegalArgumentException e) {
			
		}
	}
	
	@Test
	void associaAtividadeIdAtividadeVazio() {
		try {
		sistema.associaAtividade(pesquisa1, "");
		fail("Deve lancar uma excessao");
		}catch(IllegalArgumentException e) {
			
		}
	}
	
	@Test
	void associaProblemaIdAtividadeNulo() {
		try {
		sistema.associaAtividade(pesquisa1, null);
		fail("Deve lancar uma excessao");
		}catch(IllegalArgumentException e) {
			
		}
	}
	
	@Test
	void associaObjetivo () {
		try {
		sistema.associaAtividade(pesquisa1, null);
		fail("Deve lancar uma excessao");
		}catch(IllegalArgumentException e) {
			
		}
	}
	

}
