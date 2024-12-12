public class UsuarioEmpresa extends Usuario {
    private String cnpj;

    // Construtor específico
    public UsuarioEmpresa(String nomeUsuario, String senha, String cnpj) {
        super(nomeUsuario, senha); // Chama o construtor da classe base
        this.cnpj = cnpj;
    }

    // Getter e Setter para CNPJ
    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        if (cnpj.length() != 14) {
            System.out.println("CNPJ inválido! Deve ter 14 caracteres.");
        } else {
            this.cnpj = cnpj;
        }
    }

    @Override
    public void validarUsuario() {
        if (cnpj == null || cnpj.length() != 14) {
            System.out.println("CNPJ inválido! Deve ter 14 caracteres.");
        } else {
            System.out.println("Usuário Empresa validado com sucesso!");
        }
    }
}
