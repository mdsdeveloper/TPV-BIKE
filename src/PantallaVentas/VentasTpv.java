package PantallaVentas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Calendar;

import javax.swing.Box;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
/**
 * 
 * @author Two hands Technology
 *
 */
public class VentasTpv extends JDialog {

	private JPanel contentPane, panelFecha, panelLista, panelTotal, panelCalculadora, panelCategoria, panelProducto, panelBotonera;
	private Box h1, v1, v2, vFecha, vLineas, vTotal, vCalculadora, vCategoria, vProductos, vBotonera;
	private JLabel JLfecha, JLhoras, JLlista, JLcalcu, JLcate, JLprod, JLbotones;
	private String hora, minutos, segundos, ampm, fecha;
	private int dia;
	private Calendar calendario;
	private Thread hilo;
	private int alto, ancho, xPanelFecha,yPanelFecha;
	private JLabel JLempleado;
	/**
	 * Create the frame.
	 */
	public VentasTpv(int ancho, int alto) {
//		setResizable(false);
		this.alto = alto;
		this.ancho = ancho;
		setMinimumSize(new Dimension(ancho, alto));
        setLocationRelativeTo(null); 
        setTitle("MCBIKE");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setModal(true);
	
		LaminaSuperior laminaSuperior = new LaminaSuperior();
		
//		
//		JSplitPane splitSuperior = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
//		splitSuperior.setDividerSize(1);
//		int size = ancho / 4;
//		splitSuperior.setDividerLocation(size);
//		splitSuperior.setEnabled(false);
//		splitSuperior.setBackground(Color.GRAY);
		
		
//		
//		panelFecha = new JPanel();
//		
//		panelFecha.add(JLfecha, BorderLayout.NORTH);
//		panelFecha.add(JLhoras, BorderLayout.CENTER);
//		panelFecha.add(JLempleado, BorderLayout.SOUTH);
//		panelFecha.setBackground(Color.WHITE);
//		
//		panelCategoria = new JPanel();
//		panelCategoria.add(JLcate);
//		panelCategoria.setBackground(Color.GRAY);
//		splitSuperior.add(panelFecha);
//		splitSuperior.add(panelCategoria);
//		
//		contentPane.add(splitSuperior, BorderLayout.NORTH);
		contentPane.add(laminaSuperior, BorderLayout.NORTH);
		setContentPane(contentPane);
		pack();
	
	
	}


	
	
		
}


