/*
 * ListIt.java
 * Imprime caracter’sticas de ficheros textuales
 * (c) FŽlix R. Rodr’guez, EPCC, Universidad de Extremadura, 2009
 * http://madiba.unex.es/felix
 */

import java.io.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.commons.io.FilenameUtils;

class ListIt {
	
		private static Queue <File> colaFicheros=new ConcurrentLinkedQueue<File>();
		private static final String extensiones []= {"java","cpp","c","html"};
		
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
		           FileReader fr = new FileReader(fichero);
		           BufferedReader br = new BufferedReader(fr);
		           String linea;
		           while ((linea=br.readLine()) != null)
		                   System.out.println(linea);   
			   }
	        }
	        catch (FileNotFoundException fnfe) {
	           System.out.println("Fichero desaparecido en combate  ;-)");
	        }
		}
        public static void main (String [] args) throws Exception {
                if (args.length<1) {
                        System.out.println("ERROR. formato: >java ListIt nombre_archivo");
                        return;
                }
                File fichero = new File(args[0]);
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
      }
}
              
