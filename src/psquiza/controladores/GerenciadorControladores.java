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

/**
 * Representacao da classe responsavel por salvar e carregar o
 * conteudo de todos os controladores.
 * A classe salva todos os atributos de todos os controladores do
 * sistema em um arquivo.
 * Ao carregar, a classe salva todos os atributos localmente e depois
 * retorna um novo controlador instanciado a partir dos atributos
 * salvos localmente anteriormente.
 * 
 * @author Pedro Henrique
 */
public class GerenciadorControladores {
	
	/**
	 * Mapa de atividades do controlador de atividade.
	 */
	private HashMap<String, Atividade> atividades;
	/**
	 * Identificador atual da atividade do controlador de atividade.
	 */
	private int idAtividade;
	
	/**
	 * Mapa de problemas do controlador de metas.
	 */
	private HashMap<String, Problema> problemas;
	/**
	 * Contador de problema do controlador de metas.
	 */
	private int contadorProblema;
	/**
	 * Mapa de objetivos do controlador de metas.
	 */
	private HashMap<String, Objetivo> objetivos;
	/**
	 * Contador de objetivo do controlador de metas.
	 */
	private int contadorObjetivo;
	
	/**
	 * Mapa de pesquisas do controlador de pesquisa.
	 */
	private LinkedHashMap<String, Pesquisa> pesquisas;
	/**
	 * Estrategia atual do controlador de estrategia.
	 */
	private String estrategia;
	
	/**
	 * Mapa de pesquisadores do controlador de pesquisadores.
	 */
	private LinkedHashMap<String, Pesquisador> pesquisadores;
	
	/**
	 * Salva todos os atributos de todos os controladores do sistema
	 * no arquivo localizado no parametro estadoSistema.
	 * 
	 * @param estadoSistema e o local e nome do arquivo onde sera
	 * salvo os atributos dos controladores.
	 * @param controladorAtividade e o controlador de atividade cujos
	 * atributos serao salvos.
	 * @param controladorMetas e o controlador de metas cujos
	 * atributos serao salvos.
	 * @param controladorPesquisa e o controlador de pesquisa cujos
	 * atributos serao salvos.
	 * @param controladorPesquisador e o controlador de pesquisadores
	 * cujos atributos serao salvos.
	 * @throws IOException e lancado caso ocorra um erro durante o
	 * salvamento.
	 */
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
	
	/**
	 * Le os atributos dos controladores salvos no arquivo localizado
	 * em estadoSistema e atribui esses atributos aos atributos locais.
	 * 
	 * @param estadoSistema e o local e nome do arquivo de onde sera
	 * lido os atributos dos controladores.
	 * @throws IOException e lancado caso ocorra um
	 * erro durante a leitura dos arquivos.
	 * @throws ClassNotFoundException e lancado caso ocorra um erro
	 * durante o carregamento.
	 */
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

	/**
	 * Retorna um novo controlador de atividade com os atributos
	 * que foram carregados localmente.
	 * 
	 * @return e retornado um controlador de atividade com os
	 * atributos carregados localmente.
	 */
	public ControladorAtividade carregaAtividade() {
		return new ControladorAtividade(this.atividades, this.idAtividade);
	}

	/**
	 * Retorna um novo controlador de metas com os atributos
	 * que foram carregados localmente.
	 * 
	 * @return e retornado um controlador de metas com os
	 * atributos carregados localmente.
	 */
	public ControladorMetas carregaMetas() {
		return new ControladorMetas(this.problemas, this.objetivos, this.contadorProblema, this.contadorObjetivo);
	}

	/**
	 * Retorna um novo controlador de pesquisa com os atributos
	 * que foram carregados localmente.
	 * 
	 * @return e retornado um controlador de pesquisa com os
	 * atributos carregados localmente.
	 */
	public ControladorPesquisa carregaPesquisa() {
		return new ControladorPesquisa(this.pesquisas, this.estrategia);
	}

	/**
	 * Retorna um novo controlador de pesquisador com os atributos
	 * que foram carregados localmente.
	 * 
	 * @return e retornado um controlador de pesquisador com os
	 * atributos carregados localmente.
	 */
	public ControladorPesquisador carregaPesquisador() {
		return new ControladorPesquisador(this.pesquisadores);
	}
}
