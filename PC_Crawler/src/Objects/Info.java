package Objects;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;



public class Info {

	ArrayList<String> sinonimos;
	
	public void completarSinonimos(String token) throws FileNotFoundException {
		
		//Hacer que no lea espacios en blanco, ni "(fif)". La separación es por ';'. Saltarse los comentarios (empiezan por #).
		
		//TODO
		
		BufferedReader br = new BufferedReader (new FileReader ("Thesaurus_es_ES.txt"));
        String linea;
        
        
        
	}
}
