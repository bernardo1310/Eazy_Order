package SistemaPedidos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/EazyOrder";
    private static final String USUARIO = "root";
    private static final String SENHA = "sua_senha";

    // Adicionar um pedido
    public void adicionarPedido(int numero, String status) {
        String sql = "INSERT INTO pedidos (numero, status) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, numero);
            stmt.setString(2, status);

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Pedido adicionado com sucesso!");
            } else {
                System.out.println("Erro ao adicionar pedido.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    // Listar todos os pedidos
    public List<Pedido> listarPedidos() {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedidos";

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Pedido pedido = new Pedido(rs.getInt("numero"), rs.getDate("data"), rs.getString("status"));
                pedidos.add(pedido);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar pedidos: " + e.getMessage());
        }

        return pedidos;
    }

    // Atualizar o status de um pedido
    public void atualizarStatusPedido(int numero, String novoStatus) {
        String sql = "UPDATE pedidos SET status = ? WHERE numero = ?";

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, novoStatus);
            stmt.setInt(2, numero);

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Status do pedido atualizado com sucesso!");
            } else {
                System.out.println("Erro ao atualizar o status do pedido.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
}