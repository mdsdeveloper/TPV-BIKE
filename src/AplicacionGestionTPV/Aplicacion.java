package AplicacionGestionTPV;

import java.awt.Dimension;
import java.awt.Toolkit;

import util.ConstantesUtil;

public class Aplicacion {
/**
 * 
 * @author Two hands solution
 *
 */
	public static void main(String[] args) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension tamanyoPantalla = toolkit.getScreenSize();
		ConstantesUtil.alto = tamanyoPantalla.getHeight();
		ConstantesUtil.ancho =  tamanyoPantalla.getWidth();
		PantallaPrincipal ventanaPrincipal = new PantallaPrincipal();
		ventanaPrincipal.setVisible(true);
		ventanaPrincipal.setTitle("PANTALLA INICIO");
		
		
		
//		String path2 = System.getProperty("user.dir");
//		JOptionPane.showMessageDialog(null, path2);
//		String[] plit = path2.split("/");
//		System.out.println(plit[0]);
//		System.out.println(plit[1]);
//		System.out.println(plit[2]);	
//		System.out.println(plit[3]);
//		JOptionPane.showMessageDialog(null, plit[0]);
//		JOptionPane.showMessageDialog(null, plit[1]);
//		JOptionPane.showMessageDialog(null, plit[2]);
//	    JOptionPane.showMessageDialog(null, plit[3]);
//	    JOptionPane.showMessageDialog(null, plit[0]+"\"" + plit[1] + "\"" + plit[2] + "\"" + plit[3] + "\"" + "NuevoDir");
		 
	}

}