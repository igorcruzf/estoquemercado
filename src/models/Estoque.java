package models;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import exceptions.BuscaProdutoException;
import exceptions.CadastrarProdutoException;
import exceptions.NomeProdutoException;
import exceptions.QuantidadeProdutoException;

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


	public void alterarProduto(String anterior, String novo)
			throws BuscaProdutoException, CadastrarProdutoException, NomeProdutoException, QuantidadeProdutoException {
		buscaProduto(anterior);
		int quantidade = produtos.get(anterior).getQuantidade();
		cadastrarProduto(novo, quantidade);
		produtos.remove(anterior);
		historico.forEach(item -> {
			if (item.getNome_produto().equalsIgnoreCase(anterior))
				item.setNome_produto(novo);
		});
	}

	public void deletarProduto(String nome) throws BuscaProdutoException {
		this.buscaProduto(nome);
		this.produtos.remove(nome);
	}

	public void transacao(String produto, int quantidade) throws BuscaProdutoException, QuantidadeProdutoException {
		Date data = new Date();
		Produto item = this.buscaProduto(produto);
		int total = item.getQuantidade() + quantidade;
		try {
			item.setQuantidade(total);
		} catch (QuantidadeProdutoException e) {
			throw new QuantidadeProdutoException("Não foi possível realizar a venda, você pode vender no máximo "
					+ item.getQuantidade() + " unidades desse produto.");
		}
		this.atualizaHistorico(data, quantidade, produto);
	}

	public void atualizaHistorico(Date data, int quantidade, String produto) {
		this.historico.add(new Historico(data, quantidade, produto));
	}

	public Produto buscaProduto(String produto) throws BuscaProdutoException {
		Produto produto_buscado = this.produtos.get(produto);
		if (produto_buscado == null)
			throw new BuscaProdutoException("O produto " + produto + " não foi encontrado no estoque");
		else
			return produto_buscado;
	}
	


}
