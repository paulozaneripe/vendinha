package br.com.paulo.vendinha;

import java.math.BigDecimal;

public class Compra {
	
	private Cliente cliente;
	private Produto produto;
	private Integer qtd;
	private BigDecimal total;
	
	public Compra(Cliente cliente, Produto produto, int qtd, BigDecimal total) {
		this.cliente = cliente;
		this.produto = produto;
		this.qtd = qtd;
		this.total = total;
	}

	public String getCliente() {
		return cliente.getNome();
	}

	public String getProduto() {
		return produto.getNome();
	}

	public BigDecimal getValorProduto() {
		return produto.getValor();
	}
	
	public Integer getQtd() {
		return qtd;
	}

	public BigDecimal getTotal() {
		return total;
	}
}
