package SistemaPedidos;

import java.util.Scanner;

public class UsuarioCliente {
    private String nome;
    private String cpf;
    private String senha;
    private Endereco endereco;

    public void login() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o CPF: ");
        this.cpf = scanner.next();
        System.out.print("Digite a senha: ");
        this.senha = scanner.next();

        if (this.cpf.equals("12345678900") && this.senha.equals("cliente123")) {
            System.out.println("Login bem-sucedido!");
            menu();
        } else {
            System.out.println("CPF ou senha inválidos.");
        }
    }

    public void cadastrar() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite seu nome: ");
        this.nome = scanner.nextLine();
        System.out.print("Digite seu CPF: ");
        this.cpf = scanner.nextLine();
        System.out.print("Digite sua senha: ");
        this.senha = scanner.nextLine();

        System.out.println("Digite seu endereço:");
        System.out.print("Rua: ");
        String rua = scanner.nextLine();
        System.out.print("Número: ");
        String numero = scanner.nextLine();
        System.out.print("Cidade: ");
        String cidade = scanner.nextLine();
        System.out.print("Estado: ");
        String estado = scanner.nextLine();
        System.out.print("CEP: ");
        String cep = scanner.nextLine();

        this.endereco = new Endereco(rua, numero, cidade, estado, cep);
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
            System.out.println("2 - Finalizar Pedido");
            System.out.println("3 - Avaliar Pedido");
            System.out.println("4 - Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 0:
                    System.out.println("Visualizar Produtos - Funcionalidade ainda não implementada.");
                    break;
                case 1:
                    System.out.println("Adicionar ao Carrinho - Funcionalidade ainda não implementada.");
                    break;
                case 2:
                    System.out.println("Finalizar Pedido - Funcionalidade ainda não implementada.");
                    break;
                case 3:
                    System.out.println("Avaliar Pedido - Funcionalidade ainda não implementada.");
                    break;
                case 4:
                    System.out.println("Voltando ao menu inicial.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 4);
    }

    // Getters e setters adicionais
    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getSenha() {
        return senha;
    }

    public Endereco getEndereco() {
        return endereco;
    }
}
