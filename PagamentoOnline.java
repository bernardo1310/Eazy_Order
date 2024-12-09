package Trabalho_Final;
public class PagamentoOnline extends formaPagamento{
	private String Cartao;
	private String Validade;
	private String CVV;
	public String getCartao() {
		return Cartao;
	}
	public void setCartao(String cartao) {
		Cartao = cartao;
	}
	public String getValidade() {
		return Validade;
	}
	public void setValidade(String validade) {
		Validade = validade;
	}
	public String getCVV() {
		return CVV;
	}
	public void setCVV(String cVV) {
		CVV = cVV;
	}
	
}
