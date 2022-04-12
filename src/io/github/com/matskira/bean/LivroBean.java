package io.github.com.matskira.bean;

import javax.faces.bean.ManagedBean;

import io.github.com.matskira.model.Livro;

@ManagedBean
public class LivroBean {

	Livro livro = new Livro();

	public Livro getLivro() {
		return livro;
	}

	public void gravar() {
		System.out.println("Gravado com sucesso " + this.livro.getNome());
	}
}
