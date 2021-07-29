package br.com.paulo.vendinha;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class AppTest {
	
    @Test
    public void compraQtdMaiorQueEstoqueTest() {
    	
    	Produto p1 = new Produto("Café",BigDecimal.valueOf(8.5),10);
    	
    	Cliente c1 = new Cliente("Lucas",1200);
    	
    	Exception exception = assertThrows(RuntimeException.class, () -> {
			c1.adicionarAoCarrinho(p1, 11);
		});
		
		assertEquals("O produto Café não possui estoque suficiente para essa quantidade!", exception.getMessage());
    }
    
    @Test
    public void compraDinheiroInsuficienteTest() {
    	
    	Produto p1 = new Produto("Refrigerante",BigDecimal.valueOf(6.3),50);
    	Produto p2 = new Produto("Energético",BigDecimal.valueOf(8.5),35);

    	Cliente c1 = new Cliente("Lucas",20);
    	
    	c1.adicionarAoCarrinho(p1, 30);
    	c1.adicionarAoCarrinho(p2, 20);
    	
    	Pedido pd1 = c1.fazerPedido(c1.getCarrinho());
    	
		Exception exception = assertThrows(RuntimeException.class, () -> {
			c1.comprar(pd1, BigDecimal.valueOf(500));
		});
		
		assertEquals("Lucas não possui esse dinheiro!", exception.getMessage());
    }
    
    @Test
    public void fazerPedidoSemItensTest() {
    	
    	Produto p1 = new Produto("Refrigerante", BigDecimal.valueOf(6.3),50);
    	
    	Cliente c1 = new Cliente("Lucas",1200);
    	
    	c1.adicionarAoCarrinho(p1, 5);
    	c1.removerDoCarrinho(p1);
    	
    	Exception exception = assertThrows(RuntimeException.class, () -> {
    		c1.fazerPedido(c1.getCarrinho());
    	});
  
    	assertEquals("Não é possível efetuar um pedido com o carrinho vazio!", exception.getMessage());
    }
    
    @Test
    public void removerItemDeCarrinhoVazioTest() {
    	
    	Produto p1 = new Produto("Refrigerante",BigDecimal.valueOf(6.3),50);
    	
    	Cliente c1 = new Cliente("Lucas",1200);
    	
    	Exception exception = assertThrows(RuntimeException.class, () -> {
    		c1.removerDoCarrinho(p1);
    	});
    	
    	assertEquals("Carrinho vazio!", exception.getMessage());
    }
    
    @Test
    public void removerItemNaoAdicionadoAoCarrinhoTest() {
    	
    	Produto p1 = new Produto("Refrigerante",BigDecimal.valueOf(6.3),50);
    	Produto p2 = new Produto("Energético",BigDecimal.valueOf(8.5),35);
    	
    	Cliente c1 = new Cliente("Lucas",1200);
    	
    	c1.adicionarAoCarrinho(p2, 5);
    	
    	Exception exception = assertThrows(RuntimeException.class, () -> {
    		c1.removerDoCarrinho(p1);
    	});
    	
    	assertEquals("Este produto não está no carrinho!", exception.getMessage());
    }
    
    @Test
    public void comprarPedidoCanceladoTest() {
    	
    	Produto p1 = new Produto("Refrigerante", BigDecimal.valueOf(6.3),50);
    	
    	Cliente c1 = new Cliente("Lucas",1200);
    	
    	c1.adicionarAoCarrinho(p1, 30);
    	
    	Pedido pd1 = c1.fazerPedido(c1.getCarrinho());
    	
    	pd1.cancelarPedido();
    	
    	Exception exception = assertThrows(RuntimeException.class, () -> {
    		c1.comprar(pd1, BigDecimal.valueOf(500));
    	});
    	
    	assertEquals("Pedido já está cancelado!", exception.getMessage());
    }
}
