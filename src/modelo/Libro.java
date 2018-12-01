package modelo;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;

public class Libro implements Serializable{
	private String isbn;
	private String titulo;
	private String autor;
	private String tema;
	private int paginas;
	private String formato;
	private String estado;
	private String editorial;
	private int unidades;
	
	public Libro(HashMap<String, Object> datosLibro) throws IllegalArgumentException, IllegalAccessException, SecurityException {
	    Field[] declaredFields = this.getClass().getDeclaredFields();
	    for (int i = 0; i < declaredFields.length; i++) {
	      declaredFields[i].set(this, datosLibro.get(declaredFields[i].getName()));
	    }
	  }

	public boolean esIgualQue(Libro libro) {
		if(this.autor.equals(libro.getAutor()))return false;
		if(this.editorial.equals(libro.getEditorial()))return false;
		if(this.estado.equals(libro.getEstado()))return false;
		if(this.formato.equals(libro.getFormato()))return false;
		if(this.paginas==libro.getPaginas())return false;
		if(this.tema.equals(libro.getTema()))return false;
		if(this.titulo.equals(libro.getTitulo()))return false;
		return (this.unidades==libro.getUnidades());
	}

	public String getIsbn() {
		return isbn;
	}
	
	public int getUnidades() {
		return unidades;
	}

	public String getTitulo() {
		return titulo;
	}
	public String getAutor() {
		return autor;
	}
	public String getTema() {
		return tema;
	}
	public int getPaginas() {
		return paginas;
	}
	public String getFormato() {
		return formato;
	}
	public String getEstado() {
		return estado;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}

	public String getEditorial() {
		return editorial;
	}

}
