package PantallaVentas;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import util.ConstantesUtil;
/**
 * 
 * @author Two hands Technology
 *
 */
public class LaminaSuperior extends JPanel {

	public LaminaSuperior(){
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new FlowLayout());
		setVisible(true);
		setBackground(Color.GRAY);
		
		LaminaFecha lf = new LaminaFecha(1440,900);
		add(lf);
		LaminaCategoria lc = new LaminaCategoria(ConstantesUtil.ancho, ConstantesUtil.alto);
		add(lc);
	}
}
