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

	private Integer livroId;
	
	private List<Livro> livros = new ArrayList<Livro>();
	
	private Integer autorId;

	public List<Livro> getLivros() {
		return new DAO<Livro>(Livro.class).listaTodos();
	}

	public List<Autor> getAutoresDoLivro() {
		return this.getLivro().getAutores();
	}

	public Integer getLivroId() {
		return livroId;
	}

	public void setLivroId(Integer livroId) {
		this.livroId = livroId;
	}

	public List<Autor> getAutores() {
		return autores;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
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
		
		if (this.livro.getId() == null) {
			new DAO<Livro>(Livro.class).adiciona(this.livro);
		}else {
			new DAO<Livro>(Livro.class).atualiza(this.livro);			
		}

		
		this.livro = new Livro();
	}
	
	public void remover(Livro livro) {
		System.out.println("Removendo livro " + livro.getTitulo());
		new DAO<Livro>(Livro.class).remove(livro);
		this.livros.remove(livro);
	}
	
	public void carregar(Livro livro) {
		System.out.println("Carregando livro "+ livro.getTitulo());
		this.livro = livro;
	}
	
	public void removerAutorDoLivro(Autor autor) {
		this.livro.removeAutor(autor);
	}
	
	public void validacaoISBN(FacesContext fc, UIComponent component, Object object) throws ValidatorException{
		String valor = object.toString();
		if (!valor.startsWith("1")) {
			throw new ValidatorException(new FacesMessage("Campo ISBN deveria come?ar com 1"));
		}
	}
	
	public void carregarLivroPelaId() {
	    this.livro = new DAO<Livro>(Livro.class).buscaPorId(this.livroId);
	}

}
