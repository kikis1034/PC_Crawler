package codigo;
/*
 * ListIt.java
 * Imprime caracter�sticas de ficheros textuales
 * (c) F�lix R. Rodr�guez, EPCC, Universidad de Extremadura, 2009
 * http://madiba.unex.es/felix
 */

import java.io.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.commons.io.FilenameUtils;

public class ListIt {
	
		private static Queue <File> colaFicheros=new ConcurrentLinkedQueue<File>();
		private static FichContPalabras contadorWords=new FichContPalabras();
		private static final String extensiones []= {"yaml","txt","java","cpp","c","html"};
		
		private static void isDirectory(File fichero) {
            File [] listaFicheros = fichero.listFiles();
            for (int i=0; i<listaFicheros.length; i++) {
            	colaFicheros.add(listaFicheros[i]);
            }              
		}
		
		private static void isArchivo(File fichero) throws IOException {
			try {
				String extension=FilenameUtils.getExtension(fichero.getPath());
				if(Arrays.asList(extensiones).contains(extension)) {
					contadorWords.ContarPalabras(fichero.getPath());
				}
			

			}catch (Exception e) {
				System.out.println("El fichero: "+fichero.getName()+" no se puede abrir");
			}

		}
        public static void analyze (String file) throws Exception {
                File fichero = new File(file);
                if (!fichero.exists() || !fichero.canRead()) {
                        System.out.println("No puedo leer " + fichero);
                        return;
                }
                if (fichero.isDirectory()) {
                	isDirectory(fichero);
                	Iterator<File> it=colaFicheros.iterator();
                	while(it.hasNext()) {
                		File ficheroActual=it.next();
                		if (ficheroActual.isDirectory()) isDirectory(ficheroActual);
                		else isArchivo(ficheroActual);
                	}
                }
                else  {
                	isArchivo(fichero);
                }
                contadorWords.EscribirCuenta("salida.txt");
      }
}
              
