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

public class FichContPalabras{
	
		private Map mapaPalabras;
		
		public FichContPalabras() {
			mapaPalabras=new TreeMap();
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
		public void EscribirCuenta(String ficheroSalida) throws IOException {
			List claves = new ArrayList (mapaPalabras.keySet ());
            Collections.sort (claves);

            PrintWriter pr = new PrintWriter (new FileWriter (ficheroSalida));
            Iterator i = claves.iterator ();
            while (i.hasNext ()) {
                    Object k = i.next ();
                    pr.println (k + " : " + mapaPalabras.get (k));
            }
            pr.close ();
            
//            FileOutputStream fos = new FileOutputStream(ficheroSalida);
//            ObjectOutputStream oos = new ObjectOutputStream(fos);
//            oos.writeObject(mapaPalabras);
//            oos.close();
//            fos.close();
		}
		
		
		public void EscribirCuentaPalabra(String ficheroSalida, String palabra) throws IOException {
			List claves = new ArrayList (mapaPalabras.keySet ());
            Collections.sort (claves);

            PrintWriter pr = new PrintWriter (new FileWriter (ficheroSalida));
            Iterator i = claves.iterator ();
            while (i.hasNext ()) {
                    Object k = i.next ();
                    if (k == palabra) {
                    	pr.println (k + " : " + mapaPalabras.get (k));
                    }
            }
            pr.close ();
            
		}
		
		private String limpiarCadena(String cadena) {
			  
			        cadena = Normalizer.normalize(cadena, Normalizer.Form.NFD);
			        cadena = cadena.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
			        return cadena;
			    
		}

}
