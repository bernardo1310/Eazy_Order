package SistemaPedidos;

public class Pagamento {
    private String metodo;
    private double valor;

    public Pagamento(String metodo, double valor) {
        this.metodo = metodo;
        this.valor = valor;
    }

    public boolean realizarPagamento() {
        System.out.println("Pagamento de R$ " + valor + " realizado com sucesso via " + metodo);
        return true;
    }
}
