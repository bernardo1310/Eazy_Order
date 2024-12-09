package Trabalho_Final;
public class Produto {
	List<Produto> produtos = new ArrayList<Produto>();
	private int Carrinho;
	public void setCarrinho(int carrinho) {
		Carrinho = carrinho;
	}
	private String Nome;
	private double Preco;
	private String Descricao;
	public List<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	public List<Produto> getCarrinho() {
		return carrinho;
	}
	public void setCarrinho(List<Produto> carrinho) {
		this.carrinho = carrinho;
	}
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public double getPreco() {
		return Preco;
	}
	public void setPreco(double preco) {
		Preco = preco;
	}
	public String getDescricao() {
		return Descricao;
	}
	public void setDescricao(String descricao) {
		Descricao = descricao;
	}
	
}
