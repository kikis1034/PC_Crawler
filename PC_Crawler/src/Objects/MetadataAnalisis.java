package Objects;

import java.io.Serializable;
import java.time.LocalDateTime;

public class MetadataAnalisis implements Serializable {
	private String directorio;
	private LocalDateTime ultimoAnalisis;
	
	public MetadataAnalisis(String directorio) {
		this.ultimoAnalisis=LocalDateTime.now();
		this.directorio=directorio;
	}
	public String getDirectorio() {
		return directorio;
	}
	public void setDirectorio(String directorio) {
		this.directorio = directorio;
	}
	public LocalDateTime getUltimoAnalisis() {
		return ultimoAnalisis;
	}
	public void setUltimoAnalisis(LocalDateTime ultimoAnalisis) {
		this.ultimoAnalisis = ultimoAnalisis;
	}
	
	@Override
	public String toString() {
		
		return "Directorio: "+directorio+"\n"+"Ultimo Analisis: "+ultimoAnalisis+"\n";
	}
}
