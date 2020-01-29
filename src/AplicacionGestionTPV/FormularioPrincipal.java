package AplicacionGestionTPV;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controladorService.GestionService;
import controladorService.GestionServiceImpl;
import modelo.Configuracion;
import util.ConstantesUtil;
/**
 * 
 * @author Two hands Technology
 *
 */
public class FormularioPrincipal extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JPanel centralPane = new JPanel();
	private JPanel buttonPane;
	public static JButton btnGestionProd,btnGestionCategoria,btnGestionEmpleado,btnGestionVentas,btnGestionFacturas,btnGestionSeguridad, btnGestionConf, btnGestionCajas;
	private double anchoVentana, altoVentana, ancho, alto;
	private JPanel panel;
	private JButton btnSalir;
	private GestionService service = new GestionServiceImpl();
	/**
	 * Create the dialog.
	 */
	public FormularioPrincipal(double xw, double yw, double x, double y) {
		anchoVentana = x*0.570;
		altoVentana =  y*0.54;
		ancho = x;
		alto = y;
		setLocationRelativeTo(null); 
		setResizable(false);
		setBounds((int)ancho/2 - (int)anchoVentana/2, (int)alto/2 - (int)altoVentana/2, (int)anchoVentana, (int)altoVentana);
		getContentPane().setLayout(new BorderLayout());
		
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		setModal(true);
		
		{
			JLabel lblFormularioDeGestion = new JLabel(ConstantesUtil.formularioGestion);
			panel.add(lblFormularioDeGestion);
			lblFormularioDeGestion.setHorizontalAlignment(SwingConstants.CENTER);
			
			lblFormularioDeGestion.setHorizontalAlignment(SwingConstants.CENTER);
			lblFormularioDeGestion.setFont(ConstantesUtil.ancho <= 1280 ? new Font("Lucida Grande", Font.BOLD, 17) : new Font("Lucida Grande", Font.BOLD, 22));
		}
		contentPanel.setBackground(new Color(0, 102, 153));
		contentPanel.setForeground(new Color(0, 102, 153));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null); 
		setResizable(false);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			centralPane.setBackground(new Color(0, 102, 153));
			contentPanel.add(centralPane);
			btnGestionProd = new JButton(ConstantesUtil.productos);	
			btnGestionProd.setBounds((int)(anchoVentana*0.749), (int) (altoVentana*0.088), (int)(anchoVentana*0.232), (int) (altoVentana*0.181));
			btnGestionProd.setFont(ConstantesUtil.ancho <= 1280 ? new Font("Lucida Grande", Font.PLAIN, 13) : new Font("Lucida Grande", Font.PLAIN, 20));
			
			btnGestionCategoria = new JButton(ConstantesUtil.categorias);
			btnGestionCategoria.setBounds((int)(anchoVentana*0.501), (int) (altoVentana*0.088), (int)(anchoVentana*0.232), (int) (altoVentana*0.181));
			btnGestionCategoria.setFont(ConstantesUtil.ancho <= 1280 ? new Font("Lucida Grande", Font.PLAIN, 13) : new Font("Lucida Grande", Font.PLAIN, 20));
			btnGestionEmpleado = new JButton(ConstantesUtil.empleados);
			btnGestionEmpleado.setBounds((int)(anchoVentana*0.254), (int) (altoVentana*0.349), (int)(anchoVentana*0.232), (int) (altoVentana*0.181));
			btnGestionEmpleado.setFont(ConstantesUtil.ancho <= 1280 ? new Font("Lucida Grande", Font.PLAIN, 13) : new Font("Lucida Grande", Font.PLAIN, 20));
			btnGestionVentas = new JButton(ConstantesUtil.ventas);
			btnGestionVentas.setBounds((int)(anchoVentana*0.501), (int) (altoVentana*0.349), (int)(anchoVentana*0.232), (int) (altoVentana*0.181));
			btnGestionVentas.setFont(ConstantesUtil.ancho <= 1280 ? new Font("Lucida Grande", Font.PLAIN, 13) : new Font("Lucida Grande", Font.PLAIN, 20));
			btnGestionSeguridad = new JButton(ConstantesUtil.seguridad); 
			btnGestionSeguridad.setBounds((int)(anchoVentana*0.007), (int) (altoVentana*0.088), (int)(anchoVentana*0.232), (int) (altoVentana*0.181));
			btnGestionSeguridad.setFont(ConstantesUtil.ancho <= 1280 ? new Font("Lucida Grande", Font.PLAIN, 13) : new Font("Lucida Grande", Font.PLAIN, 20));
			btnGestionConf = new JButton(ConstantesUtil.configuracion); 
			btnGestionConf.setBounds((int)(anchoVentana*0.254), (int) (altoVentana*0.088), (int)(anchoVentana*0.232), (int) (altoVentana*0.181));
			btnGestionConf.setFont(ConstantesUtil.ancho <= 1280 ? new Font("Lucida Grande", Font.PLAIN, 13) : new Font("Lucida Grande", Font.PLAIN, 20));
			btnGestionCajas = new JButton(ConstantesUtil.cajas);
			btnGestionCajas.setBounds((int)(anchoVentana*0.007), (int) (altoVentana*0.349), (int)(anchoVentana*0.232), (int) (altoVentana*0.181));
			btnGestionCajas.setFont(ConstantesUtil.ancho <= 1280 ? new Font("Lucida Grande", Font.PLAIN, 13) : new Font("Lucida Grande", Font.PLAIN, 20));
			centralPane.setLayout(null);
			
			btnGestionFacturas= new JButton(ConstantesUtil.facturas);
			btnGestionFacturas.setBounds((int)(anchoVentana*0.749), (int) (altoVentana*0.349), (int)(anchoVentana*0.232), (int) (altoVentana*0.181));
			btnGestionFacturas.setFont(ConstantesUtil.ancho <= 1280 ? new Font("Lucida Grande", Font.PLAIN, 13) : new Font("Lucida Grande", Font.PLAIN, 20));
			
			
			centralPane.add(btnGestionFacturas);
			centralPane.add(btnGestionCategoria);
			centralPane.add(btnGestionProd);
			centralPane.add(btnGestionEmpleado);
			centralPane.add(btnGestionVentas);
			centralPane.add(btnGestionSeguridad);
			centralPane.add(btnGestionCajas);
			centralPane.add(btnGestionConf);
			{
				buttonPane = new JPanel();
				buttonPane.setBounds((int)(anchoVentana*0.007), (int) (altoVentana*0.693), (int)(anchoVentana*0.974), (int) (altoVentana*0.146));
				centralPane.add(buttonPane);
				buttonPane.setLayout(null);
				{
					
					btnSalir = new JButton(ConstantesUtil.salir);
					btnSalir.addActionListener(this);
					btnSalir.setIcon(new ImageIcon(FormularioProducto.class.getResource(ConstantesUtil.pathSalir)));
					btnSalir.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
					btnSalir.setBounds((int)(anchoVentana*0.791), (int) (altoVentana*0.008), (int)(anchoVentana*0.174),(int) (altoVentana*0.125));
					buttonPane.add(btnSalir);
				}
			}
			
			btnGestionCajas.addActionListener(this);
			btnGestionCategoria.addActionListener(this);
			btnGestionConf.addActionListener(this);
			btnGestionEmpleado.addActionListener(this);
			btnGestionProd.addActionListener(this);
			btnGestionVentas.addActionListener(this);
			btnGestionFacturas.addActionListener(this);
			btnGestionSeguridad.addActionListener(this);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();
		
		if(source.getText().equals(ConstantesUtil.productos)){
			FormularioProducto formularioProducto = new FormularioProducto();
//			formularioProducto.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
			formularioProducto.setVisible(true);
//			btnGestionProd.setEnabled(false);
		}else if(source.getText().equals(ConstantesUtil.salir)){
			iniciarButtons();
			dispose();			
		}else if(source.getText().equals(ConstantesUtil.categorias)){
			FormularioCategoria formularioCategoria = new FormularioCategoria();
//			formularioCategoria.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
			formularioCategoria.setVisible(true);
//			btnGestionCategoria.setEnabled(false);
		}else if(source.getText().equals(ConstantesUtil.empleados)){
			FormularioEmpleados formularioEmpleado = new FormularioEmpleados();
//			formularioEmpleado.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
			formularioEmpleado.setVisible(true);
//			btnGestionEmpleado.setEnabled(false);
		}else if(source.getText().equals(ConstantesUtil.configuracion)){	
			FormularioConfiguracion formularioConfiguracion = new FormularioConfiguracion();			
			formularioConfiguracion.setVisible(true);
//			formularioConfiguracion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}else if(source.getText().equals(ConstantesUtil.ventas)){
			FormularioVentas formularioVentas = new FormularioVentas();
			formularioVentas.setVisible(true);
		}else if(source.getText().equals(ConstantesUtil.cajas)){
			FormularioCajas formularioCajas = new FormularioCajas();
			formularioCajas.setVisible(true);
		}else if(source.getText().equals(ConstantesUtil.seguridad)){
			FormularioSeguridad formularioSeguridad = new FormularioSeguridad();
			formularioSeguridad.setVisible(true);
		}else if(source.getText().equals(ConstantesUtil.facturas)){
			FormularioInformes formularioInformes = new FormularioInformes();
			formularioInformes.setVisible(true);
		}
		
		
	}

	private void iniciarButtons() {
		PantallaPrincipal.btnConfiguracion.setEnabled(true);
//		btnGestionProd.setEnabled(true);
//		btnGestionCategoria.setEnabled(true);
//		btnGestionEmpleado.setEnabled(true);
	}

}
