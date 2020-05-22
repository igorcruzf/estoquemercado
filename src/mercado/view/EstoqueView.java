package mercado.view;

import java.util.HashMap;
import java.util.List;

import mercado.model.Historico;
import mercado.model.Produto;

public class EstoqueView {

	public static void menuEstoque() {
		System.out.println("---------------------------");
		System.out.println("|    Escolha uma opção    |");
		System.out.println("| 1 - Cadastrar produto   |");
		System.out.println("| 2 - Alterar produto     |");
		System.out.println("| 3 - Deletar produto     |");
		System.out.println("| 4 - Fazer uma compra    |");
		System.out.println("| 5 - Fazer uma venda     |");
		System.out.println("| 6 - Verificar histórico |");
		System.out.println("| 7 - Listar produtos     |");
		System.out.println("| 0 - Sair                |");
		System.out.println("---------------------------");
	}

	public static void mostrarHistoricoGeral(List<Historico> historico) {
		System.out.println("Data                           ---   Nome    ---   Quantidade");
		historico.forEach(item -> System.out
				.println(item.getData() + " gi  ---   " + item.getNome_produto() + "   ---   " + item.getQuantidade()));
	}

	public static void mostrarHistoricoProduto(Produto produto, List<Historico> historico) {
		System.out.println("Data                        ---   Quantidade");
		historico.forEach(hist -> {
			if (hist.getNome_produto().equals(produto.getNome()))
				System.out.println(hist.getData() + "   ---   " + hist.getQuantidade());
		});
		System.out.println();
		System.out.println("Nome   ---   Quantidade");
		System.out.println(produto.getNome() + "   ---    " + produto.getQuantidade());
	}

	public static void listarProdutos(int com_quantidade, HashMap<String, Produto> produtos) {
		if (com_quantidade == 2) {
			System.out.println("Nome   ---   Quantidade");
			produtos.forEach((nome, produto) -> System.out.println(nome + "   ---    " + produto.getQuantidade()));
			;
		} else {
			produtos.forEach((nome, produto) -> System.out.println(nome));
		}
	}

}
