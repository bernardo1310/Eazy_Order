package SistemaPedidos;

import java.util.ArrayList;

public class Carrinho {
    private ArrayList<Produto> produtos;
    private double total;

    public Carrinho() {
        produtos = new ArrayList<>();
        total = 0.0;
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
        total += produto.getPreco();
    }

    public void removerProduto(Produto produto) {
        if (produtos.remove(produto)) {
            total -= produto.getPreco();
        }
    }

    public double getTotal() {
        return total;
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void exibirProdutos() {
        System.out.println("Produtos no carrinho:");
        for (Produto produto : produtos) {
            System.out.println(produto.getInfo());
        }
        System.out.println("Total: R$ " + total);
    }
}