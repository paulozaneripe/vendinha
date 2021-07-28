package br.com.paulo.vendinha;

import java.math.BigDecimal;

public class App {

    public static void main(String[] args) {
    	
    	Produto p1 = new Produto("Refrigerante",BigDecimal.valueOf(6.3),50);
    	Produto p2 = new Produto("Energético",BigDecimal.valueOf(8.5),35);
    	Produto p3 = new Produto("Café",BigDecimal.valueOf(4.6),70);
    	
    	Cliente c1 = new Cliente("Lucas",1200);
    	
    	c1.adicionarAoCarrinho(p1, 30);
    	c1.adicionarAoCarrinho(p2, 20);
    	
    	Pedido pd1 = c1.fazerPedido(c1.getCarrinho());
    	
    	pd1.getStatus();
    	
    	c1.comprar(pd1, BigDecimal.valueOf(500));
    	c1.listarCompras();
    	
    	pd1.getStatus();
    	
    	c1.adicionarAoCarrinho(p3, 40);
    	c1.adicionarAoCarrinho(p2, 5);
    	
    }
}
