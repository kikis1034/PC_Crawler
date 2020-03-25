package utilidades;

import java.io.*;
import java.util.*;

import Objects.MetadataAnalisis;
/**
 * Clase para cargar objetos serializables en memoria
 * @author kike
 *
 */
public class CargarObjeto {
		/**
		 * Carga un Mapa en memoria a partir de un fichero.
		 * @return
		 */
        public static Map cargarDiccionario () {
                try {
                        FileInputStream fis = new FileInputStream("diccionario.txt");
                        ObjectInputStream ois = new ObjectInputStream(fis);
                        Map diccionario = (Map) ois.readObject();
                        return diccionario;
                }
                catch (Exception e) { 
                	System.out.println(e); 
                	return null;
                }
        }
        /**
         * Carga una instancia de MetadataAnalisis en memoria a partir de un fichero
         * @return
         */
        public static MetadataAnalisis cargarMetadata () {
            try {
                    FileInputStream fis = new FileInputStream("metaAnalisis.txt");
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    MetadataAnalisis meta = (MetadataAnalisis) ois.readObject();
                    return meta;
            }
            catch (Exception e) { 
            	return null;
            }
            
    }
}
