package mercado.view;

import java.util.HashMap;
import java.util.List;

import mercado.controller.exceptions.BuscaProdutoException;
import mercado.model.Historico;
import mercado.model.Produto;

public class EstoqueView {
	
	public void mostrarHistoricoGeral(List<Historico> historico) {
		System.out.println("Data                           ---   Nome    ---   Quantidade");
		historico.forEach(item -> System.out
				.println(item.getData() + " gi  ---   " + item.getNome_produto() + "   ---   " + item.getQuantidade()));
	}

	public void mostrarHistoricoProduto(Produto produto, List<Historico> historico) throws BuscaProdutoException {
		System.out.println("Data                        ---   Quantidade");
		historico.forEach(hist -> {
			if (hist.getNome_produto().equals(produto.getNome()))
				System.out.println(hist.getData() + "   ---   " + hist.getQuantidade());
		});
		System.out.println();
		System.out.println("Nome   ---   Quantidade");
		System.out.println(produto.getNome() + "   ---    " + produto.getQuantidade());
	}
	
	public void listarProdutos(int com_quantidade, HashMap<String, Produto> produtos) {
		if (com_quantidade == 2) {
			System.out.println("Nome   ---   Quantidade");
			produtos.forEach((nome, produto) -> System.out.println(nome + "   ---    " + produto.getQuantidade()));
			;
		} else {
			produtos.forEach((nome, produto) -> System.out.println(nome));
		}
	}

}
