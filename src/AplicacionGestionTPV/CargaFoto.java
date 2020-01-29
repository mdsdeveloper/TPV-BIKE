package AplicacionGestionTPV;

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
public class CargaFoto extends JFrame {

	private JPanel contentPane;
	public static JFileChooser fchCargarFoto;
	private double alto, ancho;

	/**
	 * Create the frame.
	 */
	public CargaFoto() {
		ancho = 558;
		alto = 391;
		setBounds((int)ConstantesUtil.ancho/2 - (int)ancho/2, (int)ConstantesUtil.alto/2 - (int)alto/2, 558, 391);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		fchCargarFoto = new JFileChooser();
		fchCargarFoto.setBounds(5, 5, 548, 359);
		contentPane.add(fchCargarFoto);
	}

}
