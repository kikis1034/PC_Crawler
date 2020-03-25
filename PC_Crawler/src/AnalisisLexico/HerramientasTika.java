package AnalisisLexico;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;
/**
 * Clase con herramientas de Apache Tika para parsear archivos de distintas extensiones
 * @author kike y Gonzalo
 *
 */
public class HerramientasTika {
	/**
	 * Modulo para parsear archivos de cualquier extensión.
	 * @param file
	 * @return
	 * @throws IOException
	 * @throws SAXException
	 * @throws TikaException
	 */
	public static String leerArchivo(String file) throws IOException, SAXException, TikaException {
		//Para soportar archivos de mas de 100k caracteres
		StringWriter any = new StringWriter();
		BodyContentHandler handler= new BodyContentHandler(any);
		//Metadatos
		Metadata metadata=new Metadata();
		FileInputStream inputStream=new FileInputStream(new File (file));
		ParseContext pcontext = new ParseContext();
		//Parser genérico
		AutoDetectParser parser=new AutoDetectParser();
		PDFParser pdfparser=new PDFParser();
		parser.parse(inputStream,handler,metadata,pcontext);
		//Devuelve el contenido del archivo
		return handler.toString();
		
	}
}
