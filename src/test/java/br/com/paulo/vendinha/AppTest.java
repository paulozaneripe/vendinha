package br.com.paulo.vendinha;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class AppTest {
	
    @Test
    public void compraQtdMaiorEstoqueTest() {
    	
    	Produto p1 = new Produto("Refrigerante",6,10);
    	Cliente c1 = new Cliente("Lucas",1200);
    	
		Exception exception = assertThrows(RuntimeException.class, () -> {
			c1.comprar(p1, 20);
		});
		
		assertEquals("O produto Refrigerante não possui estoque suficiente para essa quantidade!", exception.getMessage());
    }
    
    @Test
    public void compraDinheiroInsuficienteTest() {
    	
    	Produto p1 = new Produto("Refrigerante",6,10);
    	Cliente c1 = new Cliente("Lucas",5);
    	
		Exception exception = assertThrows(RuntimeException.class, () -> {
			c1.comprar(p1, 1);
		});
		
		assertEquals("Lucas não possui dinheiro suficiente para efetuar a compra do Refrigerante!", exception.getMessage());
    }
}
