package mercado.model;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mercado.controller.exceptions.BuscaProdutoException;
import mercado.controller.exceptions.CadastrarProdutoException;
import mercado.controller.exceptions.NomeProdutoException;
import mercado.controller.exceptions.QuantidadeProdutoException;

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

	public void cadastrarProduto(String produto, int quantidade)
			throws CadastrarProdutoException, NomeProdutoException, QuantidadeProdutoException {
			if(this.existeProduto(produto))
				throw new CadastrarProdutoException("O produto " + produto + " já está cadastrado no estoque");
			this.produtos.put(produto, new Produto(produto, quantidade));
	
	}

	public void alterarProduto(String anterior, String novo)
			throws BuscaProdutoException, CadastrarProdutoException, NomeProdutoException, QuantidadeProdutoException {
		this.buscaProduto(anterior);
		int quantidade = this.produtos.get(anterior).getQuantidade();
		this.cadastrarProduto(novo, quantidade);
		this.produtos.remove(anterior);
		this.historico.forEach(item -> {
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
	
	public boolean existeProduto(String produto) {
		Produto produto_buscado = this.produtos.get(produto);
		if(produto_buscado == null)
			return false;
		return true;
	}

}
