package clases;

import java.util.ArrayList;

public class Periodico {

	private int paginas;
	ArrayList<Articulo> articulos = new ArrayList<Articulo>();
	
	public Periodico() {
		this.paginas = 10;
		this.articulos.add(new Articulo("International", 5, 9, 1500));
		this.articulos.add(new Articulo("National", 4, 7, 2000));
		this.articulos.add(new Articulo("Local News", 2, 5, 1000));
		this.articulos.add(new Articulo("Sports", 2, 4, 1500));
		this.articulos.add(new Articulo("Culture", 1, 3, 750));
	}
	
	public void addArticle(Articulo articulo){
		this.articulos.add(articulo);
	}
	
	public void deleteArticle(int index) {
		this.articulos.remove(index);
	}

	public int getPaginas() {
		return paginas;
	}

	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}

	public ArrayList<Articulo> getArticulos() {
		return articulos;
	}
}
