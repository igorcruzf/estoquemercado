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
		String texto;

		while (operacao != 0) {
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
			operacao = scan.nextInt();
			switch (operacao) {
			case 0:
				scan.close();
				break;
			case 1:
				System.out.println("Digite o nome do produto a ser cadastrado: ");
				texto = scan.next();
				try {
					estoque.cadastrarProduto(texto, 0);
				} catch (CadastrarProdutoException | NomeProdutoException | QuantidadeProdutoException e) {
					System.out.println(e.getMessage());
				}

				break;
			case 2:
				System.out.println("Digite o nome do produto a ser alterado: ");
				texto = scan.next();
				String anterior = texto;
				System.out.println("Qual será o novo nome do produto?");
				texto = scan.next();
				try {
					estoque.alterarProduto(anterior, texto);
				} catch (BuscaProdutoException | CadastrarProdutoException | NomeProdutoException
						| QuantidadeProdutoException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 3:
				System.out.println("Digite o nome do produto a ser deletado: ");
				texto = scan.next();
				try {
					estoque.deletarProduto(texto);
				} catch (BuscaProdutoException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 4:
				System.out.println("Digite o nome do produto comprado:");
				texto = scan.next();
				System.out.println("Qual a quantidade comprada?");
				quantidade = scan.nextInt();
				try {
					estoque.transacao(texto, quantidade);
				} catch (BuscaProdutoException | QuantidadeProdutoException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 5:
				System.out.println("Digite o nome do produto vendido:");
				texto = scan.next();
				System.out.println("Qual a quantidade vendida?");
				quantidade = scan.nextInt();
				try {
					estoque.transacao(texto, -quantidade);
				} catch (BuscaProdutoException | QuantidadeProdutoException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 6:
				System.out.println("Deseja ver o histórico de um produto específico (1) ou geral (2)?");
				int aux = scan.nextInt();
				if (aux == 1) {
					System.out.println("Digite o nome do produto a ser buscado no histórico");
					texto = scan.next();
					try {
						estoque.mostrarHistoricoProduto(texto);
					} catch (BuscaProdutoException e) {
						System.out.println(e.getMessage());
					}
				} else if (aux == 2) {
					estoque.mostrarHistoricoGeral();
				} else
					System.out.println("Número inválido");
				break;
			case 7:
				System.out.println("Deseja listar os produtos sem a quantidade atual (1) ou com (2)?");
				estoque.listarProdutos(scan.nextInt());
				break;
			default:
				System.out.println("Entrada inválida");
				break;
			}
		}
	}

}