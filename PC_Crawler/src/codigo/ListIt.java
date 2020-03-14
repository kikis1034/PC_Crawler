package codigo;
/*
 * ListIt.java
 * Imprime caracter�sticas de ficheros textuales
 * (c) F�lix R. Rodr�guez, EPCC, Universidad de Extremadura, 2009
 * http://madiba.unex.es/felix
 */

import java.io.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.commons.io.FilenameUtils;

import Objects.MetadataAnalisis;
import utilidades.CargarObjeto;
import utilidades.SalvarObjeto;

public class ListIt {
		
		public static Map diccionario;
		private static Queue <File> colaFicheros;
		private static FichContPalabras contadorWords;
		private static final String extensiones []= {"yaml","txt","java","cpp","c","h","html","css","py","odt","docx","js","json","xml"};
		
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
        public static int analyze (String file) throws Exception {
        	int retorno=-1;
        	if (!actualizado(file)) {
                File fichero = new File(file);
                if (!fichero.exists() || !fichero.canRead()) {
                        System.out.println("No puedo leer " + fichero);
                        return -1;
                }
                contadorWords=new FichContPalabras();
                colaFicheros=new ConcurrentLinkedQueue<File>();
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
                contadorWords.EscribirCuenta(file);
                retorno=0;
        	}
        	else {
        		retorno=1;
        	}
        	diccionario=CargarObjeto.cargarDiccionario();  
        	return retorno;
        	
      }

		private static boolean actualizado(String file) {
			boolean actualizado=false;
			MetadataAnalisis metaActual=new MetadataAnalisis(file);
			MetadataAnalisis meta=CargarObjeto.cargarMetadata();
            if (meta.getDirectorio().equals(metaActual.getDirectorio())) {
            	 long minutes = meta.getUltimoAnalisis().until( metaActual.getUltimoAnalisis(), ChronoUnit.MINUTES );
            	 if (minutes>10) actualizado=false;
            	 else actualizado=true;
            }
            else actualizado=false;
            
			return actualizado;
		}
}
              