package br.com.paulo.vendinha;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.SplittableRandom;

public class Compra {

	private Cliente cliente;
	private List<ItemCompra> itens;
	private BigDecimal valorTotal;
	private BigDecimal troco;
	private Long id;
	private LocalDateTime dataPagamento;

	public Compra(Cliente cliente, List<ItemCompra> itens, BigDecimal valorTotal, BigDecimal troco) {
		this.cliente = cliente;
		this.itens = itens;
		this.valorTotal = valorTotal;
		this.troco = troco;
		this.id = new SplittableRandom().nextLong(1, 10);
		this.dataPagamento = LocalDateTime.now();
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

	public BigDecimal getTroco() {
		return troco;
	}

	public Long getId() {
		return id;
	}

	public String getDataPagamento() {
		return dataPagamento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss:SSS"));
	}

	public void estornarCompra(Cliente cliente) {
		cliente.adicionarFundos(valorTotal);
	}
}
