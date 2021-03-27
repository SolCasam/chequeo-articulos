package estructuraTP.modelo;

import java.time.LocalDate;

public class Investigacion 
{
	private int id;
	private String categoria;
	private String tema;
	private String palabraClave; 
	private String titulo;
	private String epigrafe;
	private String contenido;
	private LocalDate fecha;
	
	public Investigacion() {
		
	}
	
	public Investigacion(String titulo, String epigrafe, String contenido, LocalDate fecha) {
		
		this.titulo=titulo;
		this.epigrafe=epigrafe;
		this.contenido=contenido;
		this.fecha=fecha;
		
	}
	
	public Investigacion(int id,String categoria, String tema, String palabra_clave, String titulo, String epigrafe, String contenido, LocalDate fecha) {
		this.id=id;
		this.categoria=categoria;
		this.tema=tema;
		this.palabraClave=palabra_clave;
		this.titulo=titulo;
		this.epigrafe=epigrafe;
		this.contenido=contenido;
		this.fecha=fecha;
		
	}

	public Investigacion(String categoria, String tema, String palabra_clave, String titulo, String epigrafe, String contenido, LocalDate fecha) {
		this.categoria=categoria;
		this.tema=tema;
		this.palabraClave=palabra_clave;
		this.titulo=titulo;
		this.epigrafe=epigrafe;
		this.contenido=contenido;
		this.fecha=fecha;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public String getPalabraClave() {
		return palabraClave;
	}

	public void setPalabraClave(String palabraClave) {
		this.palabraClave = palabraClave;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getEpigrafe() {
		return epigrafe;
	}

	public void setEpigrafe(String epigrafe) {
		this.epigrafe = epigrafe;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	

}
