package SistemaPedidos;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n---------------------------------------------------------");
            System.out.println("----------- Bem vindo ao EAZY ORDER -----------");
            System.out.println("---------------------------------------------------------");
            System.out.println("Deseja realizar o login como:");
            System.out.println("0 - EMPRESA");
            System.out.println("1 - CLIENTE");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 0:
                    UsuarioEmpresa usuarioEmpresa = new UsuarioEmpresa();
                    usuarioEmpresa.login();
                    break;
                case 1:
                    UsuarioCliente usuarioCliente = new UsuarioCliente();
                    usuarioCliente.menu();
                    break;
                case 2:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 2);

        scanner.close();
    }
}