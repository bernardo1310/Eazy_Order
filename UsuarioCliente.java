package SistemaPedidos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UsuarioCliente {
	private String nome;
	private String senha;
	private int id;
	private double valorTotal;
	private String produtosEscolhidos;

	public void login() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Digite a senha: ");
		this.senha = scanner.next();

		// Simulação do login
		if (this.senha.equals("cliente123")) {
			System.out.println("Login bem-sucedido!");
			menu();
		} else {
			System.out.println("Senha inválida.");
		}
	}

	public void cadastrar() {
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.print("Digite seu nome: ");
			this.nome = scanner.nextLine();
			System.out.print("Digite sua senha: ");
			this.senha = scanner.nextLine();
		}
		System.out.println("Cadastro realizado com sucesso!");
		menu();
	}

	public void menu() {
		int opcao;
		Scanner scanner = new Scanner(System.in);
		do {
			System.out.println("\nMenu - Cliente");
			System.out.println("0 - Ver Produtos");
			System.out.println("1 - Adicionar ao Carrinho");
			System.out.println("2 - Forma de pagamento (d para dinheiro, c para cartão)");
			System.out.println("3 - Finalizar Pedido");
			System.out.println("4 - Avaliar Pedido");
			System.out.println("5 - Voltar");
			System.out.print("Escolha uma opção: ");
			opcao = scanner.nextInt();

			switch (opcao) {
			case 0:
				visualizarProdutos();
				break;
			case 1:
				adicionarAoCarrinho();
				break;
			case 2:
				formaPagamento();
				break;
			case 3:
				finalizarPedido();
				break;
			case 4:
				System.out.println("Avaliar Pedido - Funcionalidade ainda não implementada.");
				break;
			case 5:
				System.out.println("Voltando ao menu inicial.");
				break;
			default:
				System.out.println("Opção inválida. Tente novamente.");
			}
		} while (opcao != 5);
	}

	private void visualizarProdutos() {
		System.out.println("\n---------------------------------------------------------");
		System.out.println("------------         PRODUTOS DISPONÍVEIS   --------------");
		System.out.println("---------------------------------------------------------");

		try (Connection conn = ConexaoBD.conectar()) {
			String sql = "SELECT * FROM produtos";
			var stmt = conn.createStatement();
			var rs = stmt.executeQuery(sql);

			if (!rs.isBeforeFirst()) {
				System.out.println("Nenhum produto disponível.");
			} else {
				while (rs.next()) {
					System.out.println(rs.getInt("id") + ". " +
							rs.getString("nome") + " - R$" +
							rs.getDouble("preco") + "\nDescrição: " +
							rs.getString("descricao"));
				}
			}
		} catch (SQLException e) {
			System.out.println("Erro ao visualizar produtos: " + e.getMessage());
		}
	}

	private void adicionarAoCarrinho() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("\nDigite o ID do produto que deseja adicionar ao carrinho: ");
		int produtoId = scanner.nextInt();

		try (Connection conn = ConexaoBD.conectar()) {
			String sql = "SELECT * FROM produtos WHERE id = ?";
			var stmt = conn.prepareStatement(sql);
			stmt.setInt(1, produtoId);
			var rs = stmt.executeQuery();

			if (rs.next()) {
				String nome = rs.getString("nome");
				double preco = rs.getDouble("preco");

				System.out.println("Produto adicionado ao carrinho: " + nome + " - R$" + preco);

				// Atualiza o valor total e os produtos escolhidos
				this.produtosEscolhidos += nome + ", ";
				this.valorTotal += preco;

				System.out.print("Deseja continuar? (1) Sim (2) Não: ");
				int continuar = scanner.nextInt();

				if (continuar == 2) {
					System.out.println("Voltando ao menu.");
				} else {
					adicionarAoCarrinho(); // Continuar adicionando ao carrinho
				}
			} else {
				System.out.println("Produto não encontrado.");
			}
		} catch (SQLException e) {
			System.out.println("Erro ao adicionar ao carrinho: " + e.getMessage());
		}
	}

	private void formaPagamento() {
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.print("Escolha a forma de pagamento (d para dinheiro, c para cartão): ");
			char formaPagamento = scanner.next().charAt(0);

			switch (formaPagamento) {
			case 'd':
			case 'D':
				System.out.println("Pagamento selecionado: Dinheiro.");
				break;
			case 'c':
			case 'C':
				System.out.println("Pagamento selecionado: Cartão.");
				break;
			default:
				System.out.println("Forma de pagamento inválida.");
			}
		}
	}

	public void finalizarPedido() {
		Scanner scanner = new Scanner(System.in);

		// Solicitar o nome completo da pessoa
		System.out.print("Digite o nome completo para retirada: ");
		String nomePessoa = scanner.nextLine();

		try (Connection conn = ConexaoBD.conectar()) {
			String sqlPedido = "INSERT INTO pedidos (nome_pessoa, valor_total, produtos, forma_pagamento) VALUES (?, ?, ?, ?)";
			var stmt = conn.prepareStatement(sqlPedido);

			stmt.setString(1, nomePessoa);

			// Preencher o valor total e produtos escolhidos
			stmt.setDouble(2, this.valorTotal);
			stmt.setString(3, this.produtosEscolhidos);
			stmt.setString(4, "d"); // Forma de pagamento ('d' para dinheiro como exemplo)

			int linhasAfetadas = stmt.executeUpdate();
			if (linhasAfetadas > 0) {
				System.out.println("Pedido finalizado com sucesso!");
			} else {
				System.out.println("Erro ao finalizar o pedido.");
			}
		} catch (SQLException e) {
			System.out.println("Erro ao finalizar o pedido: " + e.getMessage());
		}
	}

	private double calcularValorTotal() {
		return this.valorTotal; // Retorna o valor acumulado do carrinho
	}

	// Método para obter produtos escolhidos do carrinho
	private String obterProdutosEscolhidos() {
		return this.produtosEscolhidos; // Retorna os produtos armazenados no carrinho
	}

	// Getters e setters adicionais
	public String getNome() {
		return nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
}
