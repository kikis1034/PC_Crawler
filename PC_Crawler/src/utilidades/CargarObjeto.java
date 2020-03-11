package utilidades;
/*
 * CargarObjeto.java
 * Carga un objeto serializable en memoria previamente salvado en un fichero
 * (i) Félix R. Rodríguez, EPCC, Universidad de Extremadura, 2009
 * http://madiba.unex.es/felix
 */

import java.io.*;
import java.util.*;

import Objects.MetadataAnalisis;
//hola
public class CargarObjeto {
        public static TreeMap cargarDiccionario () {
                try {
                        FileInputStream fis = new FileInputStream("diccionario.txt");
                        ObjectInputStream ois = new ObjectInputStream(fis);
                        TreeMap diccionario = (TreeMap) ois.readObject();
                        System.out.println(diccionario);
                        return diccionario;
                }
                catch (Exception e) { 
                	System.out.println(e); 
                	return null;
                }
        }
        
        public static MetadataAnalisis cargarMetadata () {
            try {
                    FileInputStream fis = new FileInputStream("metaAnalisis.txt");
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    MetadataAnalisis meta = (MetadataAnalisis) ois.readObject();
                    System.out.println(meta);
                    return meta;
            }
            catch (Exception e) { 
            	System.out.println(e); 
            	return null;
            }
    }
}
