package utilidades;

/**
 * Clase para salvar objetos de memoria a un fichero de texto
 */

import java.io.*;
import java.util.*;

import Objects.MetadataAnalisis;

public class SalvarObjeto {
		/**
		 * Salva un Mapa en un fichero
		 * @param diccionario
		 */
        public static void salvarDiccionario (Map diccionario) {   
                try {
                        FileOutputStream fos = new FileOutputStream("diccionario.txt");
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        oos.writeObject(diccionario);
                }
                catch (Exception e) { System.out.println(e); }
        }
		/**
		 * Salva una instancia de la clase MetadataAnalisis en un fichero
		 * @param diccionario
		 */
        public static void salvarMetadata (MetadataAnalisis meta) {
            try {
                FileOutputStream fos = new FileOutputStream("metaAnalisis.txt");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(meta);
	        }
	        catch (Exception e) { System.out.println(e); }
        }
}
