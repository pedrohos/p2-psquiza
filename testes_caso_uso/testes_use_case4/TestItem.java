package testes_use_case4;

import static org.junit.Assert.*;
import org.junit.Test;

import psquiza.entidades.Item;

public class TestItem {

	@Test
	public void criaItem() {
		new Item("Janela");
	}

	@Test
	public void criaItemVazio() {
		try {
			new Item("");
			fail("");
		} catch (Exception e) {
		}
	}

	@Test
	public void pegaEstadoItem() {
		Item item = new Item("Janela");
		assertEquals("PENDENTE", item.getEstado());
	}
}
