package SistemaPedidos;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("---------------------------------------------------------");
            System.out.println("----------- Bem vindo ao EAZY ORDER -----------");
            System.out.println("---------------------------------------------------------");
            System.out.println("Deseja realizar o login como:");
            System.out.println("0 - EMPRESA   1 - CLIENTE");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 0:
                    UsuarioEmpresa empresa = new UsuarioEmpresa();
                    empresa.login();
                    break;
                case 1:
                    UsuarioCliente cliente = new UsuarioCliente();
                    cliente.login();
                    break;
                case 2:
                    System.out.println("\nSaindo do sistema. Até logo!");
                    break;
                default:
                    System.out.println("\nOpção inválida. Tente novamente.");
            }
        } while (opcao != 2);
    }
}
