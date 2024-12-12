public class UsuarioCliente extends Usuario {
    private String cpf;

    // Construtor específico
    public UsuarioCliente(String nomeUsuario, String senha, String cpf) {
        super(nomeUsuario, senha); // Chama o construtor da classe base
        this.cpf = cpf;
    }

    // Getter e Setter para CPF
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if (cpf.length() != 11) {
            System.out.println("CPF inválido! Deve ter 11 caracteres.");
        } else {
            this.cpf = cpf;
        }
    }

    @Override
    public void validarUsuario() {
        if (cpf == null || cpf.length() != 11) {
            System.out.println("CPF inválido! Deve ter 11 caracteres.");
        } else {
            System.out.println("Usuário Cliente validado com sucesso!");
        }
    }
}
