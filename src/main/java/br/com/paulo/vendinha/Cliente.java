package br.com.paulo.vendinha;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
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
			produto.subtrairEstoque(qtd);
			ItemCompra itemCompra = new ItemCompra(produto, qtd);
			carrinho.add(itemCompra);
			return itemCompra;
		}
			
		throw new RuntimeException("O produto " + produto.getNome() + " não possui estoque suficiente para essa quantidade!");
	}
	
	public ItemCompra removerDoCarrinho(Produto produto) {
		
		if(carrinho.size() > 0 && !carrinho.isEmpty()) {
			for (Iterator<ItemCompra> it = carrinho.iterator(); it.hasNext();) {
				ItemCompra proximoItem = it.next();
	            if(proximoItem.getProduto().getId().equals(produto.getId())) {
	                it.remove();
	                proximoItem.getProduto().adicionarEstoque(proximoItem.getQtd());
	                return proximoItem;
	            }
			}
			
			throw new RuntimeException("Este produto não está no carrinho!");
		}

		throw new RuntimeException("Carrinho vazio!");
	}
	
	public Pedido fazerPedido(List<ItemCompra> carrinho) {
		
		if(carrinho.size() > 0 && !carrinho.isEmpty()) {
			BigDecimal valorTotal = BigDecimal.valueOf(0);
			
			for(ItemCompra item : carrinho) {
				valorTotal = valorTotal.add(item.getProduto().getValor().multiply(BigDecimal.valueOf(item.getQtd())));
			}
				
			List<ItemCompra> itens = new ArrayList<ItemCompra>(carrinho);
			Pedido pedido = new Pedido(this, itens, valorTotal);
			carrinho.clear();
			return pedido;
		}
		
		throw new RuntimeException("Não é possível efetuar um pedido com o carrinho vazio!");
	}
	
	public Compra comprar(Pedido pedido, BigDecimal dinheiro) {
		BigDecimal troco = BigDecimal.valueOf(0);
		
		if(qtdDinheiro.compareTo(dinheiro) >= 0) {
			qtdDinheiro = qtdDinheiro.subtract(dinheiro);
			troco = troco.add(dinheiro.subtract(pedido.getValorTotal())); 
			qtdDinheiro = qtdDinheiro.add(troco);
		
			Compra compra = pedido.pagar(this, dinheiro);
			compras.add(compra);
			System.out.println("Compra efetuada do pedido " + pedido.getId() + " por " + nome + " - Troco: " + troco + "\n");
			return compra;
		}
		
		throw new RuntimeException(nome + " não possui esse dinheiro!");
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
		if(carrinho.size() > 0 && !carrinho.isEmpty()) {
			for(ItemCompra item : carrinho) {
				item.getProduto().adicionarEstoque(item.getQtd());
			}
			
			carrinho.clear();
		}
	}
	
	public void listarCompras() {		
		System.out.print("------------------------------------------------------\n\n");
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
			System.out.print("------------------------------------------------------\n\n");
		} else {
			System.err.println("Nenhuma compra efetuda por " + nome);
		}
	}
}
