package AplicacionGestionTPV;

import java.awt.Color;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import util.ConstantesUtil;
/**
 * 
 * @author Two hands Technology
 *
 */
public class SelecionaDirectorio extends JFrame {

	private JPanel contentPane;
	public static JFileChooser fchCargarRuta;
	private double alto, ancho;

	/**
	 * Create the frame.
	 */
	public SelecionaDirectorio() {
		ancho = 558;
		alto = 391;
		setBounds((int)ConstantesUtil.ancho/2 - (int)ancho/2, (int)ConstantesUtil.alto/2 - (int)alto/2, 558, 391);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 102, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		fchCargarRuta = new JFileChooser();
		fchCargarRuta.setBounds(5, 5, 548, 359);
		contentPane.add(fchCargarRuta);
	}

}
