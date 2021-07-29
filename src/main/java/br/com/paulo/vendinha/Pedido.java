package br.com.paulo.vendinha;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.SplittableRandom;

public class Pedido {

	private Cliente cliente;
	private List<ItemCompra> itens;
	private BigDecimal valorTotal;
	private Long id;
	private EnumStatusPagamento status;
	private LocalDateTime dataPedido;
	private LocalDateTime dataCancelamento;
	private Boolean expirado;

	public Pedido(Cliente cliente, List<ItemCompra> itens, BigDecimal valorTotal) {
		this.cliente = cliente;
		this.itens = itens;
		this.valorTotal = valorTotal;
		this.id = new SplittableRandom().nextLong(1, 10);
		this.status = EnumStatusPagamento.AGUARDANDO;
		this.dataPedido = LocalDateTime.now();
		this.expirado = false;
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

	public EnumStatusPagamento getStatus() {
		return status;
	}

	public String getDataPedido() {
		return dataPedido.format(DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss:SSS"));
	}

	public String getDataCancelamento() {
		return dataCancelamento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss:SSS"));
	}

	public Compra pagar(Cliente cliente, BigDecimal dinheiro, BigDecimal troco) {
		if (status != EnumStatusPagamento.CANCELADO) {
			if (status != EnumStatusPagamento.PAGO) {
				List<ItemCompra> itensCompra = new ArrayList<ItemCompra>(itens);
				itens.clear();
				Compra compra = new Compra(cliente, itensCompra, getValorTotal(), troco);

				status = EnumStatusPagamento.PAGO;

				return compra;
			}

			throw new RuntimeException("Pedido já está pago!");
		}

		throw new RuntimeException("Pedido já está cancelado!");
	}

	public EnumStatusPagamento cancelarPedido() {
		if (itens.size() > 0 && !itens.isEmpty() && status == EnumStatusPagamento.AGUARDANDO) {
			for (Iterator<ItemCompra> it = itens.iterator(); it.hasNext();) {
				ItemCompra proximoItem = it.next();

				proximoItem.getProduto().adicionarEstoque(proximoItem.getQtd());
			}

			dataCancelamento = LocalDateTime.now();
			return status = EnumStatusPagamento.CANCELADO;
		}

		throw new RuntimeException("Pedido já está cancelado ou pago!");
	}

	public EnumStatusPagamento reativarPedido() {
		if (expirado == false) {
			if (status == EnumStatusPagamento.CANCELADO) {
				expirado = true;
				return status = EnumStatusPagamento.AGUARDANDO;
			}

			throw new RuntimeException("Pedido não está cancelado!");
		}

		throw new RuntimeException("Pedido está expirado e não pode ser mais reativado!");
	}
}
