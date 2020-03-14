package codigo;
/*
 * FichContPalabras.java
 * Contabiliza palabras contenidas en un fichero
 * (i) Félix R. Rodríguez, EPCC, Universidad de Extremadura, 2009
 * http://madiba.unex.es/felix
 */

import java.io.*;
import java.text.Normalizer;
import java.util.*;

import Objects.Info;
import Objects.MetadataAnalisis;
import utilidades.CargarObjeto;
import utilidades.SalvarObjeto;

public class FichContPalabras{
	
		private Map mapaPalabras;
		private Map<String, Info> thesaurus;
		
		public FichContPalabras() {
			mapaPalabras=new TreeMap();
			thesaurus = new TreeMap();
		}
		
		public void ContarPalabras(String ficheroEntrada) throws IOException {
            BufferedReader br = new BufferedReader (new FileReader (ficheroEntrada));
            String linea;
            
            while ( (linea = br.readLine() ) != null) {
                    StringTokenizer st = new StringTokenizer (limpiarCadena(linea.toLowerCase()),", .!¡?¿\"0123456789-“”/_[]{#}&%$ç+*><-_;':|~½¬@=()\t\n\\"); 
                    while (st.hasMoreTokens () ) {
                            String s = st.nextToken();
                            Object o = mapaPalabras.get(s);
                            if (o == null) mapaPalabras.put (s, new Integer (1));
                            else {
                                    Integer cont = (Integer) o;
                                    mapaPalabras.put (s, new Integer (cont.intValue () + 1));
                            }
                    }
            }
            br.close ();
		}
		public void EscribirCuenta(String file) throws IOException {
			SalvarObjeto.salvarDiccionario(mapaPalabras);
			MetadataAnalisis meta= new MetadataAnalisis(file);
			SalvarObjeto.salvarMetadata(meta);
		}
		
		private String limpiarCadena(String cadena) {		  
			        cadena = Normalizer.normalize(cadena, Normalizer.Form.NFD);
			        cadena = cadena.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
			        return cadena;
			    
		}

}
