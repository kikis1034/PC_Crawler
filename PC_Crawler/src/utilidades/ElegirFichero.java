package utilidades;
import javax.swing.JFileChooser;

public class ElegirFichero{

	/**
	 * Crea un JFileChooser y devuelve la ruta elegida
	 */

	public static String elegir() {
		JFileChooser fc=new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int returnVal = fc.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			return fc.getSelectedFile().getPath();
		}
		else {
			return "";
		}	
	}

}
