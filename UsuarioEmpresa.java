package SistemaPedidos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class UsuarioEmpresa {
    private static final String LOGIN_FIXO = "empresa";
    private static final String SENHA_FIXA = "poosenhadaempresa123";

    public void login() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome de usuário: ");
        String nomeEmpresa = scanner.next();
        System.out.print("Digite a senha: ");
        String senha = scanner.next();

        if (LOGIN_FIXO.equals(nomeEmpresa) && SENHA_FIXA.equals(senha)) {
            System.out.println("Login bem-sucedido! Bem-vindo ao EAZY ORDER.");
            menu(); // Chama o menu após o login bem-sucedido
        } else {
            System.out.println("Nome de usuário ou senha inválidos. Tente novamente.");
        }
    }

    private void menu() {
        int opcao;
        Scanner scanner = new Scanner(System.in);
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
                    System.out.println("Funcionalidade de Consultar Relatórios não implementada");
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
        Scanner scanner = new Scanner(System.in);
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
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome do produto: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o preço do produto: ");
        double preco = scanner.nextDouble();
        scanner.nextLine(); // Limpar o buffer
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
        Scanner scanner = new Scanner(System.in);

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
}
