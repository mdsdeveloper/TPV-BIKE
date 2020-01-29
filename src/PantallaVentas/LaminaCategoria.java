package PantallaVentas;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import util.ConstantesUtil;
/**
 * 
 * @author Two hands Technology
 *
 */
public class LaminaCategoria extends JPanel{
	private JLabel lblNombreCate;
	
	public LaminaCategoria(double anchoVentana, double altoVentana){
		setBounds((int)(anchoVentana*0.3888), 0,  (int)(anchoVentana*0.5854), (int)(altoVentana*0.2633));
		setLayout(null);
		Border border = LineBorder.createBlackLineBorder();		
		lblNombreCate = new JLabel(ConstantesUtil.categorias);
		lblNombreCate.setBackground(Color.WHITE);
		lblNombreCate.setForeground(Color.BLACK);
		lblNombreCate.setFont(ConstantesUtil.ancho <= 1280 ? new Font("Lucida Grande", Font.BOLD, 15) : new Font("Lucida Grande", Font.BOLD, 20));
		lblNombreCate.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreCate.setBounds((int)(anchoVentana*0.1722), 0, (int)(anchoVentana*0.2409), (int)(altoVentana*0.0411));
		setBorder(border);
		add(lblNombreCate);
		
		
		
	}
}
