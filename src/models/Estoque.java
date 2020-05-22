package models;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class Estoque {
	private HashMap<String, Produto> produtos = new HashMap<String, Produto>();
	private List<Historico> historico = new ArrayList<Historico>();

	public HashMap<String, Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(HashMap<String, Produto> produtos) {
		this.produtos = produtos;
	}

	public List<Historico> getHistorico() {
		return historico;
	}

	public void setHistorico(List<Historico> historico) {
		this.historico = historico;
	}

	public Produto getProduto(String produto) {
		return produtos.get(produto);
	}
}
