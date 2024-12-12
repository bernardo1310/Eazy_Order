package Trabalho_Final;
public abstract class Usuario {
	private String nomeUsuario;
	private String senha;
	
	public Usuario(String nomeUsuario, String senha) {
		this.nomeUsuario = nomeUsuario;
		this.senha = senha;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		if(nomeUsuario == null || nomeUsuario.isEmpty()) {
			System.out.println("Usuario Invalido");
		} else {
			this.nomeUsuario = nomeUsuario;
		}
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		if(senha.length()<6) {
			System.out.println("A senha deve conter pelo menos 6 caracteres.");
		} else {
			this.senha = senha;
		}
	}
	public abstract void validarUsuario();
}
