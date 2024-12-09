package Trabalho_Final;

import java.util.ArrayList;
import java.util.List;

public class Produto {
	List<Produto> produtos = new ArrayList<Produto>();
	private List<Produto> Carrinho;
	public void setCarrinho1(List<Produto> carrinho) {
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
		return Carrinho;
	}
	public void setCarrinho(List<Produto> carrinho) {
		this.Carrinho = carrinho;
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
