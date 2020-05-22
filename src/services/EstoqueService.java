package services;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import exceptions.BuscaProdutoException;
import exceptions.CadastrarProdutoException;
import exceptions.NomeProdutoException;
import exceptions.QuantidadeProdutoException;
import models.Estoque;
import models.Historico;
import models.Produto;

public class EstoqueService {
	
	private Estoque estoque;

	public EstoqueService(Estoque estoque) {
		setEstoque(estoque);
	}
	
	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}
	
	public void cadastrarProduto(String produto, int quantidade)
			throws CadastrarProdutoException, NomeProdutoException, QuantidadeProdutoException {
			if(existeProduto(produto))
				throw new CadastrarProdutoException("O produto " + produto + " já está cadastrado no estoque");
			HashMap<String, Produto> produtos = estoque.getProdutos();
			produtos.put(produto, new Produto(produto, quantidade));
			estoque.setProdutos(produtos);
	}

	public boolean existeProduto(String produto) {
		Produto produto_buscado = estoque.getProduto(produto);
		if(produto_buscado == null)
			return false;
		return true;
	}
	
	public void alterarProduto(String anterior, String novo)
			throws BuscaProdutoException, CadastrarProdutoException, NomeProdutoException, QuantidadeProdutoException {
		buscaProduto(anterior);
		int quantidade = estoque.getProduto(anterior).getQuantidade();
		cadastrarProduto(novo, quantidade);
		HashMap<String, Produto> produtos = estoque.getProdutos();
		produtos.remove(anterior);
		estoque.setProdutos(produtos);
		estoque.getHistorico().forEach(item -> {
			if (item.getNome_produto().equalsIgnoreCase(anterior))
				item.setNome_produto(novo);
		});
	}

	public void deletarProduto(String produto) throws BuscaProdutoException {
		buscaProduto(produto);
		HashMap<String, Produto> produtos = estoque.getProdutos();
		produtos.remove(produto);
		estoque.setProdutos(produtos);
	}

	public void transacao(String produto, int quantidade) throws BuscaProdutoException, QuantidadeProdutoException {
		Date data = new Date();
		Produto item = buscaProduto(produto);
		int total = item.getQuantidade() + quantidade;
		try {
			item.setQuantidade(total);
		} catch (QuantidadeProdutoException e) {
			throw new QuantidadeProdutoException("Não foi possível realizar a venda, você pode vender no máximo "
					+ item.getQuantidade() + " unidades desse produto.");
		}
		atualizaHistorico(data, quantidade, produto);
	}

	public void atualizaHistorico(Date data, int quantidade, String produto) {
		List<Historico> historico = estoque.getHistorico();
		historico.add(new Historico(data, quantidade, produto));
		estoque.setHistorico(historico);
	}

	public Produto buscaProduto(String produto) throws BuscaProdutoException {
		Produto produto_buscado = estoque.getProduto(produto);
		if (produto_buscado == null)
			throw new BuscaProdutoException("O produto " + produto + " não foi encontrado no estoque");
		else
			return produto_buscado;
	}
	
}
