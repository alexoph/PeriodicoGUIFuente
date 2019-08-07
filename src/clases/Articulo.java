package clases;

public class Articulo {

	private String tema;
	private int paginasMin;
	private int paginasMax;
	private int potencial;
	
	public Articulo(String tema, int paginasMin, int paginasMax, int potencial) {
		this.tema = tema;
		this.paginasMin = paginasMin;
		this.paginasMax = paginasMax;
		this.potencial = potencial;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public int getPaginasMin() {
		return paginasMin;
	}

	public void setPaginasMin(int paginasMin) {
		this.paginasMin = paginasMin;
	}

	public int getPaginasMax() {
		return paginasMax;
	}

	public void setPaginasMax(int paginasMax) {
		this.paginasMax = paginasMax;
	}

	public int getPotencial() {
		return potencial;
	}

	public void setPotencial(int potencial) {
		this.potencial = potencial;
	}
	
}
