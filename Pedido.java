package SistemaPedidos;

import java.util.Date;

public class Pedido {
    private int numeroPedido;
    private Date data;
    private String status;

    public Pedido(int numeroPedido, Date data, String status) {
        this.numeroPedido = numeroPedido;
        this.data = data;
        this.status = status;
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public Date getData() {
        return data;
    }

    public String getStatus() {
        return status;
    }

    public void atualizarStatus(String novoStatus) {
        this.status = novoStatus;
    }
}