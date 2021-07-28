package br.com.paulo.vendinha;

import java.util.SplittableRandom;

public class ItemCompra {
	
	private Produto produto;
	private Integer qtd;
	private Long id;
	
	public ItemCompra(Produto produto, Integer qtd) {
		this.produto = produto;
		this.qtd = qtd;
		this.id = new SplittableRandom().nextLong(1, 10);
	}
	
	public Produto getProduto() {
		return produto;
	}
	
	public Integer getQtd() {
		return qtd;
	}
	
	public Long getId() {
		return id;
	}
}
