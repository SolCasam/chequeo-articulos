package estructuraTP.modelo;

import java.time.LocalDate;

public class Sugerencia {
	
	private int id;
	private String frase;
	private String medio;
	private String enlace;
	private LocalDate fecha;

	public Sugerencia(int id, String frase, String medio, String enlace, LocalDate fecha) {
		this.id=id;
		this.frase=frase;
		this.medio=medio;
		this.enlace=enlace;
		this.fecha=fecha;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFrase() {
		return frase;
	}
	public void setFrase(String frase) {
		this.frase = frase;
	}
	public String getMedio() {
		return medio;
	}
	public void setMedio(String medio) {
		this.medio = medio;
	}
	public String getEnlace() {
		return enlace;
	}
	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
}
