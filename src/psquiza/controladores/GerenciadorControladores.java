package psquiza.controladores;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;

import psquiza.entidades.Atividade;
import psquiza.entidades.Objetivo;
import psquiza.entidades.Pesquisa;
import psquiza.entidades.Pesquisador;
import psquiza.entidades.Problema;

public class GerenciadorControladores {
	
	private HashMap<String, Atividade> atividades;
	private int idAtividade;
	
	private HashMap<String, Problema> problemas;
	private int contadorProblema;
	private HashMap<String, Objetivo> objetivos;
	private int contadorObjetivo;
	
	private LinkedHashMap<String, Pesquisa> pesquisas;
	private String estrategia;
	
	private LinkedHashMap<String, Pesquisador> pesquisadores;
	
	public void salva(String estadoSistema, ControladorAtividade controladorAtividade, ControladorMetas controladorMetas, ControladorPesquisa controladorPesquisa, ControladorPesquisador controladorPesquisador) throws IOException {
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream(estadoSistema));
			out.writeObject(controladorAtividade.getMapaAtividades());
			out.writeObject(controladorAtividade.getCodigoId());
			
			out.writeObject(controladorMetas.getMapaProblemas());
			out.writeObject(controladorMetas.getContadorProblema());
			out.writeObject(controladorMetas.getMapaObjetivos());
			out.writeObject(controladorMetas.getContadorObjetivo());
			
			out.writeObject(controladorPesquisa.getMapaPesquisas());
			out.writeObject(controladorPesquisa.getEstrategia());
			
			out.writeObject(controladorPesquisador.getMapaPesquisadores());
		} finally {
			out.close();
		}
	}
	
	public void carrega(String estadoSistema) throws IOException, ClassNotFoundException{
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream(estadoSistema));
			this.atividades = (HashMap<String, Atividade>) in.readObject();
			this.idAtividade = (Integer) in.readObject();
			
			this.problemas = (HashMap<String, Problema>) in.readObject();
			this.contadorProblema = (Integer) in.readObject();
			this.objetivos = (HashMap<String, Objetivo>) in.readObject();
			this.contadorObjetivo = (Integer) in.readObject();
			
			this.pesquisas = (LinkedHashMap<String, Pesquisa>) in.readObject();
			this.estrategia = (String) in.readObject();
			
			this.pesquisadores = (LinkedHashMap<String, Pesquisador>) in.readObject();
		} finally {
			in.close();
		}
	}

	public ControladorAtividade carregaAtividade() {
		return new ControladorAtividade(this.atividades, this.idAtividade);
	}

	public ControladorMetas carregaMetas() {
		return new ControladorMetas(this.problemas, this.objetivos, this.contadorProblema, this.contadorObjetivo);
	}

	public ControladorPesquisa carregaPesquisa() {
		return new ControladorPesquisa(this.pesquisas, this.estrategia);
	}

	public ControladorPesquisador carregaPesquisador() {
		return new ControladorPesquisador(this.pesquisadores);
	}
}
