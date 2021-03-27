package estructuraTP.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Explicacion {
	int id;
	String Titulo;
	String Epigrafe;
	String Contenido;
	LocalDate Fecha;
	ArrayList<Chequeo> Chequeos=new ArrayList<Chequeo>();
	
	public Explicacion(String titulo, String epigrafe, String contenido, LocalDate fecha){
		
		this.Titulo=titulo;
		this.Epigrafe=epigrafe;
		this.Contenido=contenido;
		this.Fecha=fecha;
		
	}
	public Explicacion(int id,String Titulo,String Epigrafe,String Contenido,LocalDate Fecha) {
		this.id=id;
		this.Titulo=Titulo;
		this.Epigrafe=Epigrafe;
		this.Contenido=Contenido;
		this.Fecha=Fecha;
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String gettitulo() {
		return Titulo;
	}
	public void settitulo(String titulo) {
		this.Titulo=titulo;
	}
	
	public String getepigrafe() {
		return Epigrafe;
	}
	public void setepigrafe(String epigrafe) {
		this.Epigrafe=epigrafe;
	}
	
	public String getcontenido() {
		return Contenido;
	}
	public void setcontenido(String contenido) {
		this.Contenido=contenido;
	}
	
	public LocalDate getfecha() {
		return Fecha;
	}
	public void setfecha(LocalDate fecha) {
		this.Fecha=fecha;
	}
	
}