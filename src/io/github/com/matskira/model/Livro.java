package io.github.com.matskira.model;

public class Livro {

	private String nome;
	private String isbn;
	private double preco;
	private String dataLancamento;

	public Livro() {
	}

	public Livro(String nome, String isbn, double preco, String dataLancamento) {
		this.nome = nome;
		this.isbn = isbn;
		this.preco = preco;
		this.dataLancamento = dataLancamento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public String getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(String dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

}
