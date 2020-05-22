package mercado.model;

import java.util.Date;

public class Historico {
	private Date data;
	private int quantidade;
	private String nome_produto;

	public Historico(Date data, int quantidade, String nome) {
		this.setData(data);
		this.setQuantidade(quantidade);
		this.setNome_produto(nome);
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getNome_produto() {
		return nome_produto;
	}

	public void setNome_produto(String nome_produto) {
		this.nome_produto = nome_produto;
	}

}
