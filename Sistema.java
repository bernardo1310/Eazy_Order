package SistemaPedidos;

import java.util.Scanner;

public class Sistema {
    private Scanner scanner = new Scanner(System.in);

    public void iniciar() {
        int opcao;
        do {
            exibirTelaInicial();
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

    private void exibirTelaInicial() {
        System.out.println("---------------------------------------------------------");
        System.out.println("------------      BEM-VINDO AO EAZY ORDER     -----------");
        System.out.println("---------------------------------------------------------");
        System.out.println("REALIZE SEU LOGIN PARA PROSSEGUIR");
        System.out.println("USUÁRIO EMPRESA  - 0");
        System.out.println("USUÁRIO CLIENTE  - 1");
        System.out.println("SAIR             - 2");
        System.out.print("Escolha uma opção: ");
    }
}
