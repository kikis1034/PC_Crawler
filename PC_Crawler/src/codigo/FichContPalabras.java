package codigo;

import java.io.*;
import java.text.Normalizer;
import java.util.*;

import org.apache.commons.io.FilenameUtils;
import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

import AnalisisLexico.HerramientasTika;
import Objects.Info;
import Objects.MetadataAnalisis;
import utilidades.SalvarObjeto;
/**
 * Clase que contiene los métodos relacioandos con contar las palabras aceptables
 * @author kike
 *
 */
public class FichContPalabras{
		//Apunta a la misma dirección que el diccionario de ListIt
		private Map mapaPalabras;
		
		/**
		 * Constructor parametrizado inicializa el mapa que ira actualizando a medida que cuenta laspalabras de los ficheros
		 * @param diccionario
		 */
		public FichContPalabras(Map diccionario) {
			mapaPalabras=diccionario;
		}
		/**
		 * Cuenta las palabras de un fichero y actualiza el mapa
		 * @param ficheroEntrada
		 * @throws IOException
		 * @throws TikaException 
		 * @throws SAXException 
		 */
		public void ContarPalabras(String ficheroEntrada) throws IOException, SAXException, TikaException {
			String extension=FilenameUtils.getExtension(ficheroEntrada);

	        String contenido=HerramientasTika.leerArchivo(ficheroEntrada);
	        StringTokenizer st = new StringTokenizer (limpiarCadena(contenido.toLowerCase()),", .!¡?¿\"0123456789-“”/_[]{#}&%$ç+*><-_;':|~½¬@=()\t\n\\");//Elimina carácteres especiales, puntuación y número 
	        while (st.hasMoreTokens () ) {
	        	String s = st.nextToken();
	            Object o = mapaPalabras.get(s);
	            //Solo actualiza la frecuencia de las palabras aceptables que hemos cargado con el Thesauro
	            if (o != null){
	                Integer cont = (Integer) o;
	                mapaPalabras.put (s, new Integer (cont.intValue () + 1));
	            }
	         }
		}
		/**
		 * Salva el diccionario y los metadatos del análisis
		 * @param file
		 * @throws IOException
		 */
		public void EscribirCuenta(String file) throws IOException {
			SalvarObjeto.salvarDiccionario(mapaPalabras);
			MetadataAnalisis meta= new MetadataAnalisis(file);
			SalvarObjeto.salvarMetadata(meta);
		}
		
		/**
		 * Limpia una cadena de símbolos diacríticos
		 * @param cadena
		 * @return
		 */
		private static String limpiarCadena(String cadena) {		  
			        cadena = Normalizer.normalize(cadena, Normalizer.Form.NFD);
			        cadena = cadena.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
			        return cadena;
			    
		}

}
