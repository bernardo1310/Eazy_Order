package SistemaPedidos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Pagamento {
    public void confirmarPagamento(double valorTotal) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("O valor total da compra é R$" + valorTotal);
        System.out.print("Deseja pagar em dinheiro ou com cartão? (Digite 'dinheiro' ou 'cartao'): ");
        String formaPagamento = scanner.nextLine().toLowerCase();

        try (Connection conn = ConexaoBD.conectar()) {
            String sql;
            if ("dinheiro".equals(formaPagamento)) {
                sql = "INSERT INTO pagamentos (valor, forma_pagamento, confirmado) VALUES (?, 'dinheiro', TRUE)";
            } else if ("cartao".equals(formaPagamento)) {
                sql = "INSERT INTO pagamentos (valor, forma_pagamento, confirmado) VALUES (?, 'cartao', TRUE)";
            } else {
                System.out.println("Forma de pagamento inválida.");
                return;
            }

            var stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, valorTotal);
            stmt.executeUpdate();

            System.out.println("Pagamento confirmado. Pedido em preparação para entrega.");
        } catch (SQLException e) {
            System.out.println("Erro ao confirmar pagamento: " + e.getMessage());
        }
    }
}