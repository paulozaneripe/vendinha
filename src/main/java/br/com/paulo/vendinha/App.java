package br.com.paulo.vendinha;


public class App {

    public static void main(String[] args) {
    	
    	Produto p1 = new Produto("Refrigerante",6,50);
    	Produto p2 = new Produto("Energético",8,35);
    	Produto p3 = new Produto("Café",4,70);
    	
    	Cliente c1 = new Cliente("Lucas",1200);
    	
    	c1.comprar(p3, 70);
    	c1.comprar(p2, 30);
    	c1.comprar(p2, 5);
    	c1.comprar(p1, 1);
    	c1.listarCompras();
    }
}
