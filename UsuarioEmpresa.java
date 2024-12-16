package SistemaPedidos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UsuarioEmpresa {
	private static final String LOGIN_FIXO = "empresa";
	private static final String SENHA_FIXA = "poosenhadaempresa123";
	private Scanner scanner;

	public UsuarioEmpresa() {
		this.scanner = new Scanner(System.in);
	}

	public void login() {
		System.out.print("Digite o nome de usuário: ");
		String nomeEmpresa = scanner.next();
		System.out.print("Digite a senha: ");
		String senha = scanner.next();

		if (LOGIN_FIXO.equals(nomeEmpresa) && SENHA_FIXA.equals(senha)) {
			System.out.println("Login bem-sucedido! Bem-vindo ao EAZY ORDER.");
			menu();
		} else {
			System.out.println("Nome de usuário ou senha inválidos. Tente novamente.");
		}
	}

	private void menu() {
		int opcao;
		do {
			System.out.println("\n---------------------------------------------------------");
			System.out.println("------------        MENU - EMPRESA         --------------");
			System.out.println("---------------------------------------------------------");
			System.out.println("0 - Gerenciar Produtos");
			System.out.println("1 - Consultar Relatórios");
			System.out.println("2 - Configurar Descontos");
			System.out.println("3 - Voltar");
			System.out.print("Escolha uma opção: ");
			opcao = scanner.nextInt();

			switch (opcao) {
			case 0:
				gerenciarProdutos();
				break;
			case 1:
				consultarRelatorios();
				break;
			case 2:
				System.out.println("Não possui descontos registrados");
				break;
			case 3:
				System.out.println("Voltando ao menu inicial.");
				break;
			default:
				System.out.println("Opção inválida. Tente novamente.");
			}
		} while (opcao != 3);
	}

	private void gerenciarProdutos() {
		int opcao;
		do {
			System.out.println("\n---------------------------------------------------------");
			System.out.println("------------     GERENCIAMENTO DE PRODUTOS   ------------");
			System.out.println("---------------------------------------------------------");
			System.out.println("0 - Visualizar Produtos");
			System.out.println("1 - Adicionar Produto");
			System.out.println("2 - Excluir Produto");
			System.out.println("3 - Voltar");
			System.out.print("Escolha uma opção: ");
			opcao = scanner.nextInt();

			switch (opcao) {
			case 0:
				visualizarProdutos();
				break;
			case 1:
				adicionarProduto();
				break;
			case 2:
				excluirProduto();
				break;
			case 3:
				System.out.println("Voltando ao menu anterior.");
				break;
			default:
				System.out.println("Opção inválida. Tente novamente.");
			}
		} while (opcao != 3);
	}

	private void visualizarProdutos() {
		System.out.println("\n---------------------------------------------------------");
		System.out.println("------------         PRODUTOS CADASTRADOS    ------------");
		System.out.println("---------------------------------------------------------");

		try (Connection conn = ConexaoBD.conectar()) {
			String sql = "SELECT * FROM produtos";
			var stmt = conn.createStatement();
			var rs = stmt.executeQuery(sql);

			if (!rs.isBeforeFirst()) {
				System.out.println("Nenhum produto cadastrado.");
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

	private void adicionarProduto() {
		scanner.nextLine(); // Limpar o buffer de entrada

		System.out.print("Digite o nome do produto: ");
		String nome = scanner.nextLine();

		double preco = -1;
		while (preco < 0) {
			System.out.print("Digite o preço do produto: ");
			try {
				preco = scanner.nextDouble();
				if (preco < 0) {
					System.out.println("O preço não pode ser negativo. Tente novamente.");
				}
			} catch (java.util.InputMismatchException e) {
				System.out.println("Entrada inválida. Por favor, insira um número válido.");
				scanner.next(); // limpar o buffer
			}
		}

		scanner.nextLine(); // Limpar o buffer novamente
		System.out.print("Digite a descrição do produto: ");
		String descricao = scanner.nextLine();

		try (Connection conn = ConexaoBD.conectar()) {
			String sql = "INSERT INTO produtos (nome, preco, descricao) VALUES (?, ?, ?)";
			var stmt = conn.prepareStatement(sql);
			stmt.setString(1, nome);
			stmt.setDouble(2, preco);
			stmt.setString(3, descricao);
			stmt.executeUpdate();
			System.out.println("Produto adicionado com sucesso!");
		} catch (SQLException e) {
			System.out.println("Erro ao adicionar produto: " + e.getMessage());
		}
	}


	private void excluirProduto() {
		visualizarProdutos();

		System.out.print("Digite o ID do produto que deseja excluir: ");
		int id = scanner.nextInt();

		try (Connection conn = ConexaoBD.conectar()) {
			String sql = "DELETE FROM produtos WHERE id = ?";
			var stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			int linhasAfetadas = stmt.executeUpdate();

			if (linhasAfetadas > 0) {
				System.out.println("Produto excluído com sucesso!");
			} else {
				System.out.println("Produto não encontrado.");
			}
		} catch (SQLException e) {
			System.out.println("Erro ao excluir produto: " + e.getMessage());
		}
	}
	public void finalizarPedido(int pedidoId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/eazyorder", "usuario", "senha");

			// Buscar nome do cliente
			String sqlCliente = "SELECT nome FROM clientes WHERE id = (SELECT cliente_id FROM pedidos WHERE id = ?)";
			stmt = conn.prepareStatement(sqlCliente);
			stmt.setInt(1, pedidoId);
			rs = stmt.executeQuery();

			String nomeCliente = null;
			if (rs.next()) {
				nomeCliente = rs.getString("nome");
			}

			// Finalizar pedido
			String sqlPedido = "UPDATE pedidos SET status = 'entregue' WHERE id = ?";
			stmt = conn.prepareStatement(sqlPedido);
			stmt.setInt(1, pedidoId);
			stmt.executeUpdate();

			System.out.println("Pedido finalizado com sucesso para o cliente: " + nomeCliente);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao finalizar o pedido: " + e.getMessage());
		} finally {
			try {
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	private void consultarRelatorios() {
	    System.out.println("\n---------------- RELATÓRIOS DE PEDIDOS ----------------");

	    try (Connection conn = ConexaoBD.conectar()) {
	        String sql = "SELECT * FROM pedidos";
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        ResultSet rs = stmt.executeQuery();

	        if (!rs.isBeforeFirst()) { // Verifica se há registros
	            System.out.println("Nenhum pedido encontrado.");
	        } else {
	            while (rs.next()) {
	                System.out.println("ID do Pedido: " + rs.getInt("id"));
	                System.out.println("Nome do Cliente: " + rs.getString("nome_pessoa"));
	                System.out.println("Produtos: " + rs.getString("produtos"));
	                System.out.println("Valor Total: R$" + rs.getDouble("valor_total"));
	                System.out.println("Forma de Pagamento: " + 
	                    (rs.getString("forma_pagamento").equalsIgnoreCase("d") ? "Dinheiro" : "Cartão"));
	                System.out.println("------------------------------------------------------");
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("Erro ao consultar os relatórios: " + e.getMessage());
	    }
	}
}
