package mercado.model;

import mercado.controller.exceptions.NomeProdutoException;
import mercado.controller.exceptions.QuantidadeProdutoException;

public class Produto {
	private String nome;
	private int quantidade;

	public Produto(String nome, int quantidade) throws NomeProdutoException, QuantidadeProdutoException {
		this.setNome(nome);
		this.setQuantidade(quantidade);
	}

	public String getNome() {
		return nome;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setNome(String nome) throws NomeProdutoException {
		if (nome.isBlank())
			throw new NomeProdutoException("Entre com um nome v�lido");
		else
			this.nome = nome;
	}

	public void setQuantidade(int quantidade) throws QuantidadeProdutoException {
		if (quantidade < 0)
			throw new QuantidadeProdutoException("N�o pode ter uma quantidade negativa");
		else
			this.quantidade = quantidade;
	}
}
