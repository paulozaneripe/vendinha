package br.com.paulo.vendinha;

import java.math.BigDecimal;

public class App {

	public static void main(String[] args) {

		Produto p1 = new Produto("Refrigerante", BigDecimal.valueOf(6.3), 50);
		Produto p2 = new Produto("Energético", BigDecimal.valueOf(8.5), 35);

		Cliente c1 = new Cliente("Lucas", 1200);

		c1.adicionarAoCarrinho(p1, 20);
		c1.adicionarAoCarrinho(p2, 30);
		Pedido pd1 = c1.fazerPedido(c1.getCarrinho());
		pd1.cancelarPedido();
		pd1.reativarPedido();

		c1.comprar(pd1, BigDecimal.valueOf(500));

		c1.listarCompras();

		System.out.println("Carteira do cliente " + c1.getNome() + ": R$ " + c1.getQtdDinheiro());
		c1.estornarUltimaCompra();
		System.out.println("Carteira do cliente " + c1.getNome() + ": R$ " + c1.getQtdDinheiro() + "\n");

		c1.listarCompras();
	}
}
