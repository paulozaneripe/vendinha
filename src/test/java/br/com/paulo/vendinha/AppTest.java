package br.com.paulo.vendinha;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class AppTest {
	
    @Test
    public void compraQtdMaiorEstoqueTest() {
    	
    	Produto p1 = new Produto("Café",BigDecimal.valueOf(8.5),10);
    	
    	Cliente c1 = new Cliente("Lucas",1200);
    	
    	
    	
    	Exception exception = assertThrows(RuntimeException.class, () -> {
			c1.adicionarAoCarrinho(p1, 11);
		});
		
		assertEquals("O produto Café não possui estoque suficiente para essa quantidade!", exception.getMessage());
    }
    
    @Test
    public void compraDinheiroInsuficienteTest() {
    	
    	/*Produto p1 = new Produto("Refrigerante",BigDecimal.valueOf(6.3),50);
    	Produto p2 = new Produto("Energético",BigDecimal.valueOf(8.5),35);

    	Cliente c1 = new Cliente("Lucas",20);
    	
    	c1.adicionarAoCarrinho(p1, 30);
    	c1.adicionarAoCarrinho(p2, 20);
    	
		Exception exception = assertThrows(RuntimeException.class, () -> {
			c1.comprar(c1.getCarrinho());
		});
		
		assertEquals("Lucas não possui dinheiro suficiente para efetuar a compra!", exception.getMessage());*/
    }
}
