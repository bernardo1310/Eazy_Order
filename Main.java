package Trabalho_Final;
import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		Scanner read= new Scanner(System.in);
		Avaliacao avaliacao = new Avaliacao();
		Desconto desconto = new Desconto();
		Endereco endereco = new Endereco();
		Entrega statusPedido = new Entrega();
		formaPagamento tipoPagamento = new formaPagamento();
		PagamentoOnline pagOnline = new PagamentoOnline();
		Pedido pedido = new Pedido();
		Produto produto = new Produto();
		UsuarioCliente cliente = new UsuarioCliente();
		UsuarioEmpresa empresa = new UsuarioEmpresa();
		int usuarioEmpresa=0, usuarioCliente=1;
		
		System.out.println("BEM-VINDO AO EAZY ORDER!");
		System.out.println("Você deseja fazer um login como:");
		System.out.println("Empresa: 0  |  Cliente:  1");
		int usuario = read.nextInt();
		
		if(usuario==usuarioEmpresa) {
			
		} else if(usuario==usuarioCliente) {
			
		}
	}
}
