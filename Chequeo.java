package estructuraTP.modelo;


import java.time.LocalDate;

public class Chequeo {
	
	private String categoria;
	private String palabraClave;
	private String frase;
	private String medio;
	private String enlace;
	private LocalDate fecha;
	private String verificacion;
	private int id;
	
	public Chequeo(int id,String frase, LocalDate fecha, String medio, String enlace, String palabraClave, String verificacion, String categoria) {
		
		this.id = id;
		this.frase = frase;
		this.fecha = fecha;
		this.medio = medio;
		this.enlace = enlace;
		this.palabraClave = palabraClave;
		this.verificacion = verificacion;
		this.categoria = categoria;
	}
	
	public Chequeo(String frase, LocalDate fecha, String medio, String enlace, String palabraClave, String verificacion, String categoria) {
		
		this.frase = frase;
		this.fecha = fecha;
		this.medio = medio;
		this.enlace = enlace;
		this.palabraClave = palabraClave;
		this.verificacion = verificacion;
		this.categoria = categoria;
	}
	
	public Chequeo(String frase, LocalDate fecha, String medio, String palabraClave, String verificacion, String categoria) {
		
		this.frase = frase;
		this.fecha = fecha;
		this.medio = medio;
		this.palabraClave = palabraClave;
		this.verificacion = verificacion;
		this.categoria = categoria;
	}
	
	public Chequeo() {
		
	}

	public Chequeo(String frase, String medio, String link, LocalDate fecha) {
		this.frase = frase;
		this.fecha = fecha;
		this.medio = medio;
		this.enlace =link;
	}

	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getPalabraClave() {
		return palabraClave;
	}
	public void setPalabraClave(String palabraClave) {
		this.palabraClave = palabraClave;
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
	public String getVerificacion() {
		return verificacion;
	}
	public void setVerificacion(String verificacion) {
		this.verificacion = verificacion;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
