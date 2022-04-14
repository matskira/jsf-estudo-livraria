package io.github.com.matskira.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import io.github.com.matskira.dao.DAO;
import io.github.com.matskira.model.Autor;
import io.github.com.matskira.model.Livro;

@ManagedBean
@ViewScoped
public class LivroBean {

	private Livro livro = new Livro();

	private List<Autor> autores = new DAO<Autor>(Autor.class).listaTodos();
	
	private List<Autor> autoresDoLivro = new ArrayList<Autor>();

	private List<Livro> livros = new ArrayList<Livro>();
	
	public List<Livro> getLivros() {
		return new DAO<Livro>(Livro.class).listaTodos();
	}

	public List<Autor> getAutoresDoLivro() {
		return this.getLivro().getAutores();
	}

	private Integer autorId;

	public List<Autor> getAutores() {
		return autores;
	}

	public Livro getLivro() {
		return livro;
	}

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}
	
	public void gravarAutor() {
		Autor autor = new DAO<Autor>(Autor.class).buscaPorId(this.getAutorId());
		this.getLivro().adicionaAutor(autor);
	}

	public void gravar() {
		System.out.println("Gravando livro " + this.livro.getTitulo());

		if (livro.getAutores().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("autor", new FacesMessage("Livro deve ter pelo menos um Autor"));
			return;
		}

		new DAO<Livro>(Livro.class).adiciona(this.livro);
		
		this.livro = new Livro();
	}
	
	public void validacaoISBN(FacesContext fc, UIComponent component, Object object) throws ValidatorException{
		String valor = object.toString();
		if (!valor.startsWith("1")) {
			throw new ValidatorException(new FacesMessage("Campo ISBN deveria começar com 1"));
		}
	}

}
