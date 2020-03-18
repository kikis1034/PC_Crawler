package Objects;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Modelo que guarda los metadatos del análisis.
 * @author kike
 *
 */
public class MetadataAnalisis implements Serializable {
	//Directorio del análisis
	private String directorio;
	//Ultima actualización del análisis
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
