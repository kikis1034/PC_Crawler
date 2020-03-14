package utilidades;
/*
 * SalvarObjeto.java
 * Salva un objeto serializable en un fichero
 * (i) F�lix R. Rodr�guez, EPCC, Universidad de Extremadura, 2009
 * http://madiba.unex.es/felix
 */

import java.io.*;
import java.util.*;

import Objects.MetadataAnalisis;

public class SalvarObjeto {
	
        public static void salvarDiccionario (Map diccionario) {   
                try {
                        FileOutputStream fos = new FileOutputStream("diccionario.txt");
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        oos.writeObject(diccionario);
                }
                catch (Exception e) { System.out.println(e); }
        }
        
        public static void salvarMetadata (MetadataAnalisis meta) {
            try {
                FileOutputStream fos = new FileOutputStream("metaAnalisis.txt");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(meta);
	        }
	        catch (Exception e) { System.out.println(e); }
        }
}
