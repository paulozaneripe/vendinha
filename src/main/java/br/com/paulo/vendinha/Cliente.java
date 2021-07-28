package br.com.paulo.vendinha;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cliente {
	
	private final String nome;
	private BigDecimal qtdDinheiro;
	private List<Compra> compras = new ArrayList<Compra>();
	
	public Cliente(String nome, Integer qtdDinheiro) {
		this.nome = nome;
		this.qtdDinheiro =  BigDecimal.valueOf(qtdDinheiro);
	}
	
	public Compra comprar(Produto produto, int qtd) {
		
		BigDecimal valorTotal = produto.getValor().multiply(BigDecimal.valueOf(qtd));
		
		if(qtdDinheiro.compareTo(valorTotal) == 1) {
			if(qtd <= produto.getEstoque()) {
				produto.subtrairEstoque(qtd);
				qtdDinheiro = qtdDinheiro.subtract(valorTotal);
				Compra c = new Compra(this, produto, qtd, valorTotal);
				compras.add(c);
				return c;
			}
				
			throw new RuntimeException("O produto " + produto.getNome() + " não possui estoque suficiente para essa quantidade!");
		}
		
		throw new RuntimeException(nome + " não possui dinheiro suficiente para efetuar a compra do " + produto.getNome() + "!");
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getQtdDinheiro() {
		return qtdDinheiro;
	}
	
	public void listarCompras() {
		Integer i = 0;
		
		System.out.println("Lista de compras de " + nome + "\n");
		
		if(compras.size() > 0 && !compras.isEmpty()) {
			for(Compra c : compras) {
				i++;
				System.out.println(i + "ª Compra - [Produto: " + c.getProduto() + ", Qtd: " + c.getQtd() + ", Preço: R$ " + c.getValorProduto() + ", Total: R$ " + c.getTotal() + "]");
			}
			System.out.print("\n");
		} else {
			System.err.println("Nenhuma compra efetuda por " + nome);
		}
	}
}
