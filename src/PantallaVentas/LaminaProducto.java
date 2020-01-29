package PantallaVentas;

import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
/**
 * 
 * @author Two hands Technology
 *
 */
public class LaminaProducto extends JPanel{
//	private JLabel lblNombreProd;
	
	public LaminaProducto(double anchoVentana, double altoVentana){
//		setBounds(560, 237, 843, 360);
		setBounds((int)(anchoVentana*0.3888), (int)(altoVentana*0.2633), (int)(anchoVentana*0.5854), (int)(altoVentana*0.4));
		setLayout(null);
		Border border = LineBorder.createBlackLineBorder();
		setBorder(border);
	
	}
}
