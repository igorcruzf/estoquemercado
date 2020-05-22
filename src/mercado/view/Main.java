package mercado.view;

import java.util.Scanner;

import mercado.controller.exceptions.BuscaProdutoException;
import mercado.controller.exceptions.CadastrarProdutoException;
import mercado.controller.exceptions.NomeProdutoException;
import mercado.controller.exceptions.QuantidadeProdutoException;
import mercado.model.Estoque;

public class Main {

	public static void main(String[] args) {
		Estoque estoque = new Estoque();
		Scanner scan = new Scanner(System.in);
		int operacao = 1;
		int quantidade = 0;
		String produto;

		while (operacao != 0) {
			EstoqueView.menuEstoque();
			operacao = scan.nextInt();
			switch (operacao) {
			case 0:
				scan.close();
				break;
			case 1:
				System.out.println("Digite o nome do produto a ser cadastrado: ");
				produto = scan.next();
				try {
					estoque.cadastrarProduto(produto, 0);
				} catch (CadastrarProdutoException | NomeProdutoException | QuantidadeProdutoException e) {
					System.out.println(e.getMessage());
				}

				break;
			case 2:
				System.out.println("Digite o nome do produto a ser alterado: ");
				produto = scan.next();
				String anterior = produto;
				System.out.println("Qual será o novo nome do produto?");
				produto = scan.next();
				try {
					estoque.alterarProduto(anterior, produto);
				} catch (BuscaProdutoException | CadastrarProdutoException | NomeProdutoException
						| QuantidadeProdutoException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 3:
				System.out.println("Digite o nome do produto a ser deletado: ");
				produto = scan.next();
				try {
					estoque.deletarProduto(produto);
				} catch (BuscaProdutoException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 4:
				System.out.println("Digite o nome do produto comprado:");
				produto = scan.next();
				System.out.println("Qual a quantidade comprada?");
				quantidade = scan.nextInt();
				try {
					estoque.transacao(produto, quantidade);
				} catch (BuscaProdutoException | QuantidadeProdutoException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 5:
				System.out.println("Digite o nome do produto vendido:");
				produto = scan.next();
				System.out.println("Qual a quantidade vendida?");
				quantidade = scan.nextInt();
				try {
					estoque.transacao(produto, -quantidade);
				} catch (BuscaProdutoException | QuantidadeProdutoException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 6:
				System.out.println("Deseja ver o histórico de um produto específico (1) ou geral (2)?");
				int aux = scan.nextInt();
				if (aux == 1) {
					System.out.println("Digite o nome do produto a ser buscado no histórico");
					produto = scan.next();
					try {
						EstoqueView.mostrarHistoricoProduto(estoque.buscaProduto(produto), estoque.getHistorico());
					} catch (BuscaProdutoException e) {
						System.out.println(e.getMessage());
					}
				} else if (aux == 2) {
					EstoqueView.mostrarHistoricoGeral(estoque.getHistorico());
				} else
					System.out.println("Número inválido");
				break;
			case 7:
				System.out.println("Deseja listar os produtos sem a quantidade atual (1) ou com (2)?");
				EstoqueView.listarProdutos(scan.nextInt(), estoque.getProdutos());
				break;
			default:
				System.out.println("Entrada inválida");
				break;
			}
		}
	}

}