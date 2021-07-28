package br.com.paulo.vendinha;

import java.math.BigDecimal;
import java.util.List;
import java.util.SplittableRandom;

public class Compra {
	
	private Cliente cliente;
	private List<ItemCompra> itens;
	private BigDecimal valorTotal;
	private Long id;
	
	public Compra(Cliente cliente, List<ItemCompra> itens, BigDecimal valorTotal) {
		this.cliente = cliente;
		this.itens = itens;
		this.valorTotal = valorTotal;
		this.id = new SplittableRandom().nextLong(1, 10);
	}

	public String getCliente() {
		return cliente.getNome();
	}
	
	public List<ItemCompra> getItens() {
		return itens;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	
	public Long getId() {
		return id;
	}
}
