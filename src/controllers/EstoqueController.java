package controllers;

import java.util.Scanner;

import exceptions.BuscaProdutoException;
import exceptions.CadastrarProdutoException;
import exceptions.NomeProdutoException;
import exceptions.QuantidadeProdutoException;
import models.Estoque;
import services.EstoqueService;
import views.EstoqueView;

public class EstoqueController {

	public static void main(String[] args) {
		Estoque estoque = new Estoque();
		Scanner scan = new Scanner(System.in);
		EstoqueService estoque_service = new EstoqueService(estoque);
		EstoqueView estoque_view = new EstoqueView();
		int operacao = 1;
		int quantidade = 0;
		String produto;

		while (operacao != 0) {
			EstoqueView.menuEstoque();
			operacao = scan.nextInt();
			switch (operacao) {
			// sair
			case 0:
				scan.close();
				break;
			// cadastrar
			case 1:
				estoque_view.mostrarMensagem("Digite o nome do produto a ser cadastrado: ");
				produto = scan.next();
				try {
					estoque_service.cadastrarProduto(produto, 0);
				} catch (CadastrarProdutoException | NomeProdutoException | QuantidadeProdutoException e) {
					estoque_view.mostrarException(e.getMessage());
				}

				break;
			// alterar
			case 2:
				estoque_view.mostrarMensagem("Digite o nome do produto a ser alterado: ");
				produto = scan.next();
				String anterior = produto;
				estoque_view.mostrarMensagem("Qual será o novo nome do produto?");
				produto = scan.next();
				try {
					estoque_service.alterarProduto(anterior, produto);
				} catch (BuscaProdutoException | CadastrarProdutoException | NomeProdutoException
						| QuantidadeProdutoException e) {
					estoque_view.mostrarException(e.getMessage());
				}
				break;
			// deletar
			case 3:
				estoque_view.mostrarMensagem("Digite o nome do produto a ser deletado: ");
				produto = scan.next();
				try {
					estoque_service.deletarProduto(produto);
				} catch (BuscaProdutoException e) {
					estoque_view.mostrarException(e.getMessage());
				}
				break;
			// compra
			case 4:
				estoque_view.mostrarMensagem("Digite o nome do produto comprado:");
				produto = scan.next();
				estoque_view.mostrarMensagem("Qual a quantidade comprada?");
				quantidade = scan.nextInt();
				try {
					estoque_service.transacao(produto, quantidade);
				} catch (BuscaProdutoException | QuantidadeProdutoException e) {
					estoque_view.mostrarException(e.getMessage());
				}
				break;
			// venda
			case 5:
				estoque_view.mostrarMensagem("Digite o nome do produto vendido:");
				produto = scan.next();
				estoque_view.mostrarMensagem("Qual a quantidade vendida?");
				quantidade = scan.nextInt();
				try {
					estoque_service.transacao(produto, -quantidade);
				} catch (BuscaProdutoException | QuantidadeProdutoException e) {
					estoque_view.mostrarException(e.getMessage());
				}
				break;
			// historico
			case 6:
				estoque_view.mostrarMensagem("Deseja ver o histórico de um produto específico (1) ou geral (2)?");
				int aux = scan.nextInt();
				if (aux == 1) {
					estoque_view.mostrarMensagem("Digite o nome do produto a ser buscado no histórico");
					produto = scan.next();
					try {
						estoque_view.mostrarHistoricoProduto(estoque_service.buscaProduto(produto), estoque.getHistorico());
					} catch (BuscaProdutoException e) {
						estoque_view.mostrarException(e.getMessage());
					}
				} else if (aux == 2) {
					estoque_view.mostrarHistoricoGeral(estoque.getHistorico());
				} else
					estoque_view.mostrarMensagem("Número inválido");
				break;
			// listagem
			case 7:
				estoque_view.mostrarMensagem("Deseja listar os produtos sem a quantidade atual (1) ou com (2)?");

				estoque_view.mostrarListaProdutos(scan.nextInt(), estoque.getProdutos());
				break;
			default:
				estoque_view.mostrarMensagem("Entrada inválida");
				break;
			}
		}
	}

}
