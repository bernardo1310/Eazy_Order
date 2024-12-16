package SistemaPedidos;

public class Desconto {
    private String codigo;
    private double porcentagem;

    public Desconto(String codigo, double porcentagem) {
        this.codigo = codigo;
        this.porcentagem = porcentagem;
    }
}