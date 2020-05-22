package services;

import exceptions.CadastrarProdutoException;
import exceptions.NomeProdutoException;
import exceptions.QuantidadeProdutoException;
import models.Produto;

public class EstoqueService {
	
	public void cadastrarProduto(String produto, int quantidade)
			throws CadastrarProdutoException, NomeProdutoException, QuantidadeProdutoException {
			if(existeProduto(produto))
				throw new CadastrarProdutoException("O produto " + produto + " já está cadastrado no estoque");
			produtos.put(produto, new Produto(produto, quantidade));
	}

	public boolean existeProduto(String produto) {
		Produto produto_buscado = this.produtos.get(produto);
		if(produto_buscado == null)
			return false;
		return true;
	}
}
