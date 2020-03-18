package codigo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
/**
 * Clase que contiene los métodos relacionados con el Thesauro
 * @author kike y Gonzalo
 *
 */
public class Thesauro {
	
	/**
	 * Devuelve un mapa con todas las palabras aceptables del Thesauro con una frecuencia=0
	 */
	public static Map cargarThesauro() {
		Map mapaAceptables= new TreeMap();
		BufferedReader br = null;
		try {
			br = new BufferedReader (new FileReader ("Thesaurus_es_ES.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
        String linea = "";
        
        try {
			while ((linea= br.readLine())!=null) {  // linea no es null ni es un comentario (comienza por #)
				//Que no sea una línea de comentario
				if( !linea.startsWith("#")) {
					linea.replaceAll("\\(\\w+\\)","");  // eliminar anotaciones entre paréntesis
					linea.replaceAll(",",";");  // cambiar ',' por ';'
					
					String[] candidatos = linea.split(";");
					
					mapaAceptables.put(candidatos[0], new Integer(0));
				}
			}
			return mapaAceptables;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
