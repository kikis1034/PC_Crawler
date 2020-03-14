package Objects;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;



public class Info {

	ArrayList<String> sinonimos;
	
	
	//Hacer que no lea espacios en blanco, ni "(fig)" y otras cadenas entre paréntesis. 
	//La separación es por ';'. Saltarse los comentarios (empiezan por #).
	
	public void limpiarFichero() {
		
	}
	
	// Inserta en el arrayList sinonimos la lista de los sinonimos de la palabra insertada por parámetro
	public void completarSinonimos(String token) {
		
		BufferedReader br = null;
		try {
			br = new BufferedReader (new FileReader ("Thesaurus_es_ES.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
        String linea = "";
        
        try {
			while ((linea= br.readLine())!=null) {
				if(linea.equalsIgnoreCase(token)) {
					
					String[] candidatos = linea.split(";");
					
					for (int i=0 ; i<candidatos.length ; i++) {
						this.sinonimos.add(candidatos[i]);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        
        
	}
}
