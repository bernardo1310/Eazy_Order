package SistemaPedidos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
    public static Connection conectar() {
        String url = "jdbc:mysql://localhost:3306/EazyOrder";
        String usuario = "empresa"; // nome do usuário no banco de dados
        String senha = "poosenhadaempresa123"; // senha do usuário no banco de dados

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conexão ao banco de dados estabelecida com sucesso!");
            return conn;
        } catch (ClassNotFoundException e) {
            System.out.println("Driver JDBC não encontrado: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }

        return null;
    }
}
