package psquiza.controladores;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class GerenciadorControladores {
	
	private ControladorAtividade controladorAtividade;
	private ControladorMetas controladorMetas;
	private ControladorPesquisa controladorPesquisa;
	private ControladorPesquisador controladorPesquisador;
	
	public GerenciadorControladores() {
		
	}
	
	public void salva(String estadoSistema, ControladorAtividade controladorAtividade, ControladorMetas controladorMetas, ControladorPesquisa controladorPesquisa, ControladorPesquisador controladorPesquisador) throws IOException {
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(estadoSistema)));
			out.writeObject(controladorAtividade);
			out.writeObject(controladorMetas);
			out.writeObject(controladorPesquisa);
			out.writeObject(controladorPesquisador);
		} finally {
			out.close();
		}
	}
	public void carrega(String estadoSistema) throws IOException, ClassNotFoundException{
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(estadoSistema)));
			this.controladorAtividade = (ControladorAtividade) in.readObject();
			in.readObject();
			in.readObject();
			in.readObject();
			
		} finally {
			in.close();
		}
	}

	public ControladorAtividade carregaAtividade() {
		// TODO Auto-generated method stub
		return null;
	}

	public ControladorMetas carregaMetas() {
		// TODO Auto-generated method stub
		return null;
	}

	public ControladorPesquisa carregaPesquisa() {
		// TODO Auto-generated method stub
		return null;
	}

	public ControladorPesquisador carregaPesquisador() {
		// TODO Auto-generated method stub
		return null;
	}
}
