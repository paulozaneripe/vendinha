package br.com.paulo.vendinha;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.SplittableRandom;

public class Cliente {
	
	private final String nome;
	private BigDecimal qtdDinheiro;
	private List<ItemCompra> carrinho;
	private List<Compra> compras;
	private Long id;
	
	public Cliente(String nome, Integer qtdDinheiro) {
		this.nome = nome;
		this.qtdDinheiro =  BigDecimal.valueOf(qtdDinheiro);
		this.carrinho = new ArrayList<ItemCompra>();
		this.compras = new ArrayList<Compra>();
		this.id = new SplittableRandom().nextLong(1, 10);
	}
	
	public ItemCompra adicionarAoCarrinho(Produto produto, int qtd) {
		
		if(qtd <= produto.getEstoque()) {
			ItemCompra itemCompra = new ItemCompra(produto, qtd);
			carrinho.add(itemCompra);
			return itemCompra;
		}
			
		throw new RuntimeException("O produto " + produto.getNome() + " não possui estoque suficiente para essa quantidade!");
	}
	
	public Pedido fazerPedido(List<ItemCompra> carrinho) {
		BigDecimal valorTotal = BigDecimal.valueOf(0);
		
		for(ItemCompra item : carrinho) {
			valorTotal = valorTotal.add(item.getProduto().getValor().multiply(BigDecimal.valueOf(item.getQtd())));
		}
			
		List<ItemCompra> itens = new ArrayList<ItemCompra>(carrinho);
		Pedido pedido = new Pedido(this, itens, valorTotal);
		esvaziarCarrinho();
		return pedido;
	}
	
	public Compra comprar(Pedido pedido, BigDecimal dinheiro) {
		BigDecimal troco = BigDecimal.valueOf(0);
		
		if(qtdDinheiro.compareTo(dinheiro) >= 0) {
			qtdDinheiro = qtdDinheiro.subtract(dinheiro);
			troco = troco.add(dinheiro.subtract(pedido.getValorTotal())); 
			qtdDinheiro = qtdDinheiro.add(troco);
		
			Compra compra = pedido.pagar(this, dinheiro);
			compras.add(compra);
			return compra;
		}
		
		throw new RuntimeException(nome + " não possui dinheiro suficiente para pagar o pedido!");
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getQtdDinheiro() {
		return qtdDinheiro;
	}
	
	public List<ItemCompra> getCarrinho() {
		return carrinho;
	}

	public Long getId() {
		return id;
	}
	
	public void esvaziarCarrinho() {
		carrinho.clear();
	}
	
	public void listarCompras() {		
		System.out.println("Lista de compras de " + nome + "\n");
		
		if(compras.size() > 0 && !compras.isEmpty()) {
			for(Compra compra : compras) {
				System.out.println("Compra " + compra.getId() + " | Itens: " + compra.getItens().size() + " | Total: R$ " + compra.getValorTotal());
				for(ItemCompra item : compra.getItens()) {
					System.out.println("[Produto " + item.getProduto().getId() + ": " +
					item.getProduto().getNome() + ", Qtd: " +
					item.getQtd() + ", Valor: R$ " +
					item.getProduto().getValor() + ", SubTotal: R$ " +
					item.getProduto().getValor().multiply(BigDecimal.valueOf(item.getQtd())) + "]");
				}
				System.out.print("\n");
			}
		} else {
			System.err.println("Nenhuma compra efetuda por " + nome);
		}
	}
}
