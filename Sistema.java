package SistemaPedidos;

import java.util.Scanner;

public class Sistema {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("---------------------------------------------------------");
            System.out.println("----------- Bem-vindo ao EAZY ORDER -----------");
            System.out.println("---------------------------------------------------------");
            System.out.println("Deseja realizar o login como:");
            System.out.println("0 - EMPRESA   1 - CLIENTE");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer

            if (opcao == 0) {
                // Login como empresa
                UsuarioEmpresa usuarioEmpresa = new UsuarioEmpresa();
                usuarioEmpresa.login();
            } else if (opcao == 1) {
                // Login como cliente
                UsuarioCliente usuarioCliente = new UsuarioCliente();
                usuarioCliente.menu();
            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}