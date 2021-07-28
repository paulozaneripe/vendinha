package br.com.paulo.vendinha;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.SplittableRandom;

public class Pedido {
	
	private Cliente cliente;
	private List<ItemCompra> itens;
	private BigDecimal valorTotal;
	private Long id;
	private EnumStatusPagamento status;
	
	
	public Pedido(Cliente cliente, List<ItemCompra> itens, BigDecimal valorTotal) {
		this.cliente = cliente;
		this.itens = itens;
		this.valorTotal = valorTotal;
		this.id = new SplittableRandom().nextLong(1, 10);
		this.status = EnumStatusPagamento.AGUARDANDO;
	}
	
	public Cliente getCliente() {
		return cliente;
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
	
	public void getStatus() {
		System.out.println("Status do pedido " + id + ": " + status.toString() + "\n");
	}
	
	public Compra pagar(Cliente cliente, BigDecimal dinheiro) {
		if(status != EnumStatusPagamento.CANCELADO) {
			List<ItemCompra> itensCompra = new ArrayList<ItemCompra>(itens);
			itens.clear();
			Compra compra = new Compra(cliente, itensCompra, getValorTotal());
			
			status = EnumStatusPagamento.PAGO;
			
			return compra;
		}
		
		throw new RuntimeException("Pedido já está cancelado!");
	}
	
	public void cancelarPedido() {
		status = EnumStatusPagamento.CANCELADO;
	}
}
