package br.com.paulo.vendinha;

import java.math.BigDecimal;

public class Produto {
	
	private String nome;
	private BigDecimal valor;
	private Integer estoque;
	
	public Produto(String nome, int valor, int estoque) {
		this.nome = nome;
		this.valor =  BigDecimal.valueOf(valor);
		this.estoque = estoque;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getValor() {
		return valor;
	}
	
	public Integer getEstoque() {
		return estoque;
	}

	public void setValor(Integer valor) {
		this.valor = BigDecimal.valueOf(valor);
	}
	
	public void adicionarEstoque(int valor) {
		estoque += valor;
	}
	
	public void subtrairEstoque(int valor) {
		estoque -= valor;
	}
}
