package br.com.paulo.vendinha;

import java.math.BigDecimal;
import java.util.SplittableRandom;

public class Produto {
	
	private String nome;
	private BigDecimal valor;
	private Integer estoque;
	private Long id;
	
	public Produto(String nome, BigDecimal valor, int estoque) {
		this.nome = nome;
		this.valor =  valor;
		this.estoque = estoque;
		this.id = new SplittableRandom().nextLong(1, 10);
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
	
	public Long getId() {
		return id;
	}
}
