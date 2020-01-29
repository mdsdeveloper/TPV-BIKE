package AplicacionGestionTPV;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import PantallaVentas.PantallaTPV;
import controladorService.AppService;
import controladorService.AppServiceImpl;
import controladorService.GestionService;
import controladorService.GestionServiceImpl;
import dao.EmpleadoDao;
import dao.EmpleadoDaoImpl;
import modelo.Configuracion;
import modelo.Empleado;
import util.ConstantesUtil;
import util.UtilService;
/**
 * 
 * @author Two hands Technology
 *
 */
public class PantallaPrincipal extends JFrame implements ActionListener{

	private double yBtn, posXBtnConf, posXBtnTpv, xFramePass;
	private double xBtn, posYBtnConf, posYBtnTpv, yFramePass;
	public static JButton btnConfiguracion, btnSalir, btnEntrarTpv;
	private double ancho, alto, altoVentana, anchoVentana;
	private Configuracion configuracion;
	private GestionService gestionService = new GestionServiceImpl();
	private AppService appService = new AppServiceImpl();

	private Login login;
	/**
	 * Create the application.
	 */
	public PantallaPrincipal() {
		getContentPane().setForeground(new Color(0, 102, 153));
		getContentPane().setBackground(new Color(0, 102, 153));
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// cuando se cierra la ventana se cierra la aplicación
		getContentPane().setLayout(null);	
		setLocationRelativeTo(null); 
		setResizable(false);
		
		alto = ConstantesUtil.alto;
		ancho = ConstantesUtil.ancho;
		altoVentana = alto/2;
		anchoVentana = ancho/2;
		
		xBtn = anchoVentana/4;
		yBtn = altoVentana/8;
		posXBtnConf = anchoVentana/4 - xBtn/2;
		posXBtnTpv = (anchoVentana - anchoVentana/4) - xBtn/2;
		posYBtnConf = altoVentana - altoVentana/2;
		posYBtnTpv = posYBtnConf;
		
		btnConfiguracion = new JButton(ConstantesUtil.configuracion);
		btnConfiguracion.setFont(ConstantesUtil.ancho <= 1280 ? new Font("Lucida Grande", Font.BOLD, 13) : new Font("Lucida Grande", Font.BOLD, 15));
		btnEntrarTpv = new JButton(ConstantesUtil.tpv);
		btnEntrarTpv.setFont(ConstantesUtil.ancho <= 1280 ? new Font("Lucida Grande", Font.BOLD, 13) : new Font("Lucida Grande", Font.BOLD, 15));
//		btnConfiguracion.setBounds(90, 225, 180, 71 );
//		btnEntrarTpv.setBounds(450, 225, 180, 71);
		btnConfiguracion.setBounds((int)(anchoVentana*0.25) - (int)(ConstantesUtil.ancho*0.155)/2 ,(int)(ConstantesUtil.alto*0.25), (int)(ConstantesUtil.ancho*0.155),(int) (ConstantesUtil.alto*0.0788));
		btnEntrarTpv.setBounds((int)(anchoVentana*0.75) - (int)(ConstantesUtil.ancho*0.155)/2, (int)(ConstantesUtil.alto*0.25), (int)(ConstantesUtil.ancho*0.155), (int) (ConstantesUtil.alto*0.0788));
		setBounds((int)ancho/4, (int)alto/4, (int)ancho/2, (int)alto/2); // x,y,ancho,alto
//		setResizable(false);
		getContentPane().add(btnConfiguracion);
		getContentPane().add(btnEntrarTpv);
		btnConfiguracion.addActionListener(this);
		btnEntrarTpv.addActionListener(this);
		JButton jlMcbike = new JButton();
//		jlMcbike.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		jlMcbike.setForeground(Color.GRAY);
		jlMcbike.setEnabled(false);
		jlMcbike.setAlignmentX(SwingConstants.CENTER);
//		ImageIcon image = new ImageIcon(ConstantesUtil.nombreMc);
//		jlMcbike.setIcon(image);
		jlMcbike.setIcon(new ImageIcon(PantallaPrincipal.class.getResource(ConstantesUtil.nombreMc)));
//		txtrMcbike.setBounds(253, 86,217,127);
		
	
		double altoImagen = ConstantesUtil.alto*0.171;
		double anchoImagen = anchoVentana;
		jlMcbike.setSize((int)anchoImagen, (int)altoImagen);
		int menos = (int)anchoImagen/2;
		jlMcbike.setBounds((int)(anchoVentana*0.5) - menos,(int)(ConstantesUtil.alto*0.075), (int)anchoImagen,(int) altoImagen);
		getContentPane().add(jlMcbike);	
		
		btnSalir = new JButton(ConstantesUtil.salir);
		btnSalir.setIcon(new ImageIcon(FormularioProducto.class.getResource(ConstantesUtil.pathSalir)));
		btnSalir.addActionListener(this);
		btnSalir.setFont(ConstantesUtil.ancho <= 1280 ? new Font("Lucida Grande", Font.BOLD, 13) : new Font("Lucida Grande", Font.BOLD, 15));
//		btnSalir.setBounds((int)anchoVentana/2 - 143/2, 361, 143, 61);
		btnSalir.setBounds((int)(ConstantesUtil.ancho*0.25 )- (int)(ConstantesUtil.ancho*0.049),(int)(ConstantesUtil.alto*0.351), (int)(ConstantesUtil.ancho*0.099),(int) (ConstantesUtil.alto*0.067));
		getContentPane().add(btnSalir);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object botonPulsado = e.getSource();
		xFramePass = anchoVentana/2;
		yFramePass = altoVentana/2;
		if(botonPulsado == btnConfiguracion){
			btnConfiguracion.setEnabled(false);
			login = new Login(xFramePass, yFramePass, ancho, alto, ConstantesUtil.administrador);			
					
//			login.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		}else if(botonPulsado == btnEntrarTpv){
			configuracion = gestionService.getConfiguracion();
			Empleado empleado = gestionService.obtenerEmpleado();
			if(configuracion != null && empleado != null){
				btnEntrarTpv.setEnabled(false);
				login = new Login(xFramePass, yFramePass, ancho, alto, ConstantesUtil.empleado);
			}else{
				JOptionPane.showMessageDialog(null, "Tienes que crear una empresa y un empleado en configuración para acceder al TPV");
			}
			
//			login.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		}else if(botonPulsado == btnSalir){
			System.exit(1);
		}		
	}	
	public class Login extends JDialog implements ActionListener{

		private JPanel contentPane;
		private JTextField user;
		private JPasswordField pass;
		private int xFramePass, yFramePass;
		private String password, usuario, nombre;
		private EmpleadoDao empleadoDao = new EmpleadoDaoImpl();
		/**
		 * Create the frame.
		 */
		public Login(double x, double y, double xw,double yw, String nombre) {
			setTitle("IDENTIFICACIÓN");
			Font font = new Font("Lucida Grande", Font.BOLD, ConstantesUtil.ancho <= 1280 ?  15 :  20);
			setModal(true);
			this.xFramePass = (int) (xw*0.312);
			this.yFramePass = (int) (yw*0.388);
			setBounds((int)xw/2 - xFramePass/2, (int)yw/2 - yFramePass/2, this.xFramePass, this.yFramePass);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
			
			JPanel panel = new JPanel();
			panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
			contentPane.add(panel);
			panel.setLayout(null);
			
			JLabel lblUsuario = new JLabel("Usuario:");
			lblUsuario.setFont(ConstantesUtil.ancho <= 1280 ? new Font("Lucida Grande", Font.PLAIN, 13) : new Font("Lucida Grande", Font.PLAIN, 18));
			lblUsuario.setBounds((int)(xFramePass*0.14), (int)(yFramePass*0.31), (int)(ConstantesUtil.ancho*0.069), (int)(ConstantesUtil.alto*0.069));
			panel.add(lblUsuario);
			
			JLabel lblContrasea = new JLabel("Contraseña:");
			lblContrasea.setFont(ConstantesUtil.ancho <= 1280 ? new Font("Lucida Grande", Font.PLAIN, 13) : new Font("Lucida Grande", Font.PLAIN, 18));
			lblContrasea.setBounds((int)(xFramePass*0.14), (int)(yFramePass*0.40), (int)(ConstantesUtil.ancho*0.083), (int)(ConstantesUtil.alto*0.069));
			panel.add(lblContrasea);
			
			user = new JTextField();
			user.setFont(ConstantesUtil.ancho <= 1280 ? new Font("Lucida Grande", Font.PLAIN, 15) : new Font("Lucida Grande", Font.PLAIN, 18));
			user.setBounds((int)(xFramePass*0.435), (int)(yFramePass*0.36), (int)(ConstantesUtil.ancho*0.118), (int)(ConstantesUtil.alto*0.028));
			panel.add(user);
			user.setColumns(15);
			
			pass = new JPasswordField();
			pass.setFont(ConstantesUtil.ancho <= 1280 ? new Font("Lucida Grande", Font.PLAIN, 15) : new Font("Lucida Grande", Font.PLAIN, 18));
			pass.setBounds((int)(xFramePass*0.435), (int)(yFramePass*0.45), (int)(ConstantesUtil.ancho*0.118), (int)(ConstantesUtil.alto*0.028));
			panel.add(pass);
			JLabel lblAdministrador = new JLabel(nombre);
			lblAdministrador.setFont(font);
			this.nombre = lblAdministrador.getText();
//			lblAdministrador.setFont(new Font("Lucida Grande", Font.BOLD, 22));
			lblAdministrador.setHorizontalAlignment(SwingConstants.CENTER);
			lblAdministrador.setBounds((int)(xFramePass*0.5) - (int)(ConstantesUtil.ancho*0.138)/2, (int)(yFramePass*0.169), (int)(ConstantesUtil.ancho*0.138), (int)(ConstantesUtil.alto*0.028));
			panel.add(lblAdministrador);
			
			JButton btnAcceso = new JButton(ConstantesUtil.acceder);
			btnAcceso.setFont(ConstantesUtil.ancho <= 1280 ? new Font("Lucida Grande", Font.BOLD, 11) : new Font("Lucida Grande", Font.BOLD, 15));
			btnAcceso.addActionListener(this);
			btnAcceso.setBounds(xFramePass - xFramePass/4 - (int)(xFramePass*0.133), (int)(yFramePass*0.598), (int)(ConstantesUtil.ancho*0.083), (int)(ConstantesUtil.alto*0.066));
			panel.add(btnAcceso);
			setResizable(false);
//			setVisible(true);
			
			JButton btnSalir = new JButton(ConstantesUtil.salir);
			btnSalir.setIcon(new ImageIcon(FormularioProducto.class.getResource(ConstantesUtil.pathSalir)));
			btnSalir.setFont(ConstantesUtil.ancho <= 1280 ? new Font("Lucida Grande", Font.BOLD, 11) : new Font("Lucida Grande", Font.BOLD, 15));
			btnSalir.addActionListener(this);
			btnSalir.setBounds(xFramePass/4 - (int)(xFramePass*0.133), (int)(yFramePass*0.598), (int)(ConstantesUtil.ancho*0.083), (int)(ConstantesUtil.alto*0.066));
			panel.add(btnSalir);
			setResizable(false);
			setVisible(true);
			
			addWindowListener(new windowLogin());
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			password = new String(pass.getPassword());
			usuario = user.getText();
			Boolean error = false;
			JButton source = (JButton)e.getSource();
			
			if(source.getText().equals(ConstantesUtil.acceder)){
				if(!usuario.equals("") && !password.equals("")){
				
					List<Empleado> listaEmpleado = empleadoDao.obtenerEmpleados();
					if(listaEmpleado != null && !listaEmpleado.isEmpty()){
						for (Empleado empleado : listaEmpleado) {
							if(compruebaDatos(empleado)){
								error = false;
								break;
							}else{
								error = true;
							}
						}
					}else{
						if(compruebaDatos(null)){
							error = false;
						}else{
							error = true;
						}
					}
				}else{
					error = true;
				}
				clearForm();
				if(error){
					JOptionPane.showMessageDialog(this, "Error en usuario o contraseña");
					btnConfiguracion.setEnabled(true);
					btnEntrarTpv.setEnabled(true);
					dispose();
				}else{
					dispose();
				}
			}else if(source.getText().equals(ConstantesUtil.salir)){
				btnConfiguracion.setEnabled(true);
				btnEntrarTpv.setEnabled(true);
				dispose();
			}
			
		}
		private void clearForm(){
			user.setText("");
			pass.setText("");
		}
		
		private Boolean compruebaDatos(Empleado empleado){
			Boolean dev = false;
			if(empleado != null && !usuario.equals(ConstantesUtil.superAdmin)){
				if(nombre.equals(ConstantesUtil.administrador)){
					if(empleado.getCargo().equals(ConstantesUtil.administrador)){
						if((empleado.getNombre().equals(usuario) && empleado.getPassword().equals(password))){
							dev = true;
							
							FormularioPrincipal formularioGestion = new FormularioPrincipal(anchoVentana, altoVentana,ancho, alto);
							formularioGestion.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
							formularioGestion.setVisible(true);
						}
					}
				}else {
					if(empleado.getNombre().equals(usuario) && empleado.getPassword().equals(password)){
						dev = true;
						UtilService.empleado = empleado;
//						VentasTpv ventas = new VentasTpv(ancho, alto);
						PantallaTPV tpv = new PantallaTPV();
						tpv.setVisible(true);	
						
					}
				}
			}else{
				if((usuario.equals(ConstantesUtil.superAdmin) && password.equals(ConstantesUtil.passSuperAdmin))){
					if(!btnConfiguracion.isEnabled()){
						dev = true;
						FormularioPrincipal formularioGestion = new FormularioPrincipal(anchoVentana, altoVentana,ancho, alto);
//						formularioGestion.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
						formularioGestion.setVisible(true);
					}else if(!btnEntrarTpv.isEnabled()){
						dev = true;
						UtilService.empleado = empleado;
//						VentasTpv ventas = new VentasTpv(ancho, alto);
						PantallaTPV tpv = new PantallaTPV();
						tpv.setVisible(true);
					}
						
					
				}
			}
			
			return dev;
		}
	}
	
	
	
	class windowLogin extends WindowAdapter{
		
		@Override
		public void windowClosing(WindowEvent e) {
			btnConfiguracion.setEnabled(true);
			btnEntrarTpv.setEnabled(true);
			super.windowClosing(e);
		}
	}
}
