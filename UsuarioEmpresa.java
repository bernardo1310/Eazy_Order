package Trabalho_Final;
public class UsuarioEmpresa {
	private String NomeEmpresa;
	private String CNPJ;
	private String Senha;
	private String Relatiorio;
	
	public String getNomeEmpresa() {
		return NomeEmpresa;
	}
	public void setNomeEmpresa(String nomeEmpresa) {
		NomeEmpresa = nomeEmpresa;
	}
	public String getCNPJ() {
		return CNPJ;
	}
	public void setCNPJ(String cNPJ) {
		CNPJ = cNPJ;
	}
	public String getSenha() {
		return Senha;
	}
	public void setSenha(String senha) {
		Senha = senha;
	}
	public String getRelatiorio() {
		return Relatiorio;
	}
	public void setRelatiorio(String relatiorio) {
		Relatiorio = relatiorio;
	}
}
