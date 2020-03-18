package codigo;
/**
 * @author kike y Gonzalo
 * Esta clase contienen métodos relacionados con el análisis de una ruta específica
 */

import java.io.*;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

import org.apache.commons.io.FilenameUtils;

import Objects.MetadataAnalisis;
import utilidades.CargarObjeto;

public class ListIt {
		
		//Diccionario en el que se encuentran las palabras:String y su frecuencia:Integer
		public static Map <String,Integer>diccionario;
		//Cola de ficheros que se irá recorriendo en el análisis
		private static Queue <File> colaFicheros;
		//Clase que actualiza el diccionario y que contiene los métodos relacionados con la cuenta de palabras en los fichero
		private static FichContPalabras contadorWords;
		//Lista de extensiones de archivos permitidas en el análisis.
		private static final String extensiones []= {"yaml","txt","java","cpp","c","h","html","css","py","odt","docx","js","json","xml"};
		
		/**
		 * Si un file es un directorio entra en esta clase
		 * @param fichero: Ruta del file
		 */
		private static void isDirectory(File fichero) {
            File [] listaFicheros = fichero.listFiles();
            for (int i=0; i<listaFicheros.length; i++) {
            	colaFicheros.add(listaFicheros[i]);
            }              
		}
		
		/**
		 * Si un file es archivo entra en esta clase 
		 * @param fichero: Ruta del archivo
		 */
		private static void isArchivo(File fichero){
			try {
				String extension=FilenameUtils.getExtension(fichero.getPath());
				//Comprueba que el archivo tiene una extensión válida y si es así cuenta las palabras aceptables
				if(Arrays.asList(extensiones).contains(extension)) {
					contadorWords.ContarPalabras(fichero.getPath());
				}
			

			}catch (Exception e) {
				System.out.println("El fichero: "+fichero.getName()+" no se puede abrir");
			}

		}
        public static int analyze (String file) throws Exception {
        	//Si ocurre cualquierexcepción no controlada
        	int retorno=-1;
        	//Comprueba si el diccioanrio actual está actualizado
        	if (!actualizado(file)) {
                File fichero = new File(file);
                if (!fichero.exists() || !fichero.canRead()) {
                        System.out.println("No puedo leer " + fichero);
                        return -1;
                }
                //Cargams el Thesauro con las palabras aceptables y con frecuencia 0
                diccionario=Thesauro.cargarThesauro();
                //Creamos una clase contador yle pasamos el diccionario para que lo actualice a medida que cuenta las palabras
                contadorWords=new FichContPalabras(diccionario);
                //Inicializamos la cola de ficheros
                colaFicheros=new LinkedList<File>();
                
                if (fichero.isDirectory()) {
                	isDirectory(fichero);

                	while(colaFicheros.size()>0) {
                		
                		File ficheroActual = colaFicheros.poll();
                		
                		if (ficheroActual.isDirectory()) {
                			
                			isDirectory(ficheroActual);
                			
                		}
                		else {
                			isArchivo(ficheroActual);
                		}
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
        		//Si el diccionario ya esta cargado, ya que no se ha cerrado el programa, mantenemos la variable
        		if (diccionario==null) diccionario=CargarObjeto.cargarDiccionario();
        	}
        	
        	return retorno;
        	
      }
        
        /**
         * Comprueba si el análisis último se ha hecho sobre el mismo directorio y hace menos de 10 minutos
         * @param file: Ruta sobre la que se va a hacer el análisis
         * @return boolean
         */
		private static boolean actualizado(String file) {
			boolean actualizado=false;
			MetadataAnalisis metaActual=new MetadataAnalisis(file);
			MetadataAnalisis meta=CargarObjeto.cargarMetadata();
            if (meta!=null&&meta.getDirectorio().equals(metaActual.getDirectorio())) {
            	 long minutes = meta.getUltimoAnalisis().until( metaActual.getUltimoAnalisis(), ChronoUnit.MINUTES );
            	 if (minutes>10) actualizado=false;
            	 else actualizado=true;
            }
            else actualizado=false;
            
			return actualizado;
		}
		
		/**
		 * Devuelve el resultado del último análisis. Es decir, las palabras que tienen una frecuencia mayor a cero.
		 */
		public static Map resultadoAnalisis() {
			
			Map<String, Integer> mapaResultado = new TreeMap<String, Integer>();
			
			Iterator it = diccionario.keySet().iterator();
			while(it.hasNext()){
				String key = (String) it.next();
				if (diccionario.get(key) > 0) { 
					mapaResultado.put(key, diccionario.get(key));
				}
			}
			return mapaResultado;
		}
		
//		public static void mostrarColaFicheros () {
//			System.out.println("Mostrando cola de ficheros...");
//			ArrayList<File> listaFicheros = new ArrayList(colaFicheros);
//			for (int i=0 ; i<listaFicheros.size() ; i++) {
//				System.out.println(listaFicheros.get(i));
//			}
//		}
}
              
