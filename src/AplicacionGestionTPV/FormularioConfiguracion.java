package AplicacionGestionTPV;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.math.BigDecimal;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import controladorService.GestionService;
import controladorService.GestionServiceImpl;
import modelo.Configuracion;
import util.ConstantesUtil;
import util.Validador;
/**
 * 
 * @author Two hands Technology
 *
 */
public class FormularioConfiguracion extends JDialog implements ActionListener{

	private JPanel contentPane;
	private File fichero;
	private String rutaIcono, pathDirectorioFactura, inicialCaja;
	private JTextField txtCabecera,txtRazonSocial,tfRuta,txtPieDeFactura,txtNombreEmpresa,txtDniNifEmpresa,txtDireccionEmpresa;
	private JLabel lblFoto;
	private double anchoVentana, altoVentana, ancho, alto;
	private JTextField txtInicialDeCaja;
	private BigDecimal inicialCaja1;
	private GestionService service = new GestionServiceImpl();
	private JTextField txtTelefono;
	private Configuracion configuracion;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormularioConfiguracion frame = new FormularioConfiguracion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FormularioConfiguracion() {
		configuracion = service.getConfiguracion();
		anchoVentana = 660;
		altoVentana =  780;
		ancho = ConstantesUtil.ancho;
		alto = ConstantesUtil.alto;
		setLocationRelativeTo(null); 
		setResizable(false);
		setUndecorated(true);
		setBackground(UIManager.getColor("ColorChooser.background"));
		setBounds((int)ancho/2 - (int)anchoVentana/2, (int)alto/2 - (int)altoVentana/2,660,831);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 102, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setModal(true);
		JLabel lblSelecciona = new JLabel("Selecciona carpeta para guardar los PDF *");
		lblSelecciona.setForeground(UIManager.getColor("ColorChooser.swatchesDefaultRecentColor"));
		lblSelecciona.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		lblSelecciona.setBounds(32, 97, 369, 27);
		contentPane.add(lblSelecciona);
		
		JButton btnSelecciona = new JButton(ConstantesUtil.seleccionarCarpeta);
		btnSelecciona.addActionListener(this);
		btnSelecciona.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnSelecciona.setBounds(32, 136, 334, 38);
		contentPane.add(btnSelecciona);
		
		JPanel panelSalir = new JPanel();
		panelSalir.setLayout(null);
		panelSalir.setBounds(6, 754, 646, 71);
		contentPane.add(panelSalir);
		
		JButton btnSalir = new JButton(ConstantesUtil.salir);
		btnSalir.addActionListener(this);
		btnSalir.setIcon(new ImageIcon(FormularioEmpleados.class.getResource(ConstantesUtil.pathSalir)));
		btnSalir.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnSalir.setBounds(482, 6, 143, 61);
		panelSalir.add(btnSalir);
		
		JButton btnGuardar = new JButton(ConstantesUtil.guardar);
		btnGuardar.setIcon(new ImageIcon(FormularioEmpleados.class.getResource(ConstantesUtil.pathGuardar)));
		btnGuardar.addActionListener(this);
		btnGuardar.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnGuardar.setBounds(20, 6, 143, 61);
		panelSalir.add(btnGuardar);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(6, 6, 646, 66);
		contentPane.add(panel);
		
		JLabel lblFormularioDeConfiguracin = new JLabel(ConstantesUtil.formularioConfiguracion);
		lblFormularioDeConfiguracin.setHorizontalAlignment(SwingConstants.CENTER);
		lblFormularioDeConfiguracin.setFont(new Font("Lucida Grande", Font.BOLD, 26));
		lblFormularioDeConfiguracin.setBounds(92, 17, 469, 32);
		panel.add(lblFormularioDeConfiguracin);
		
		JLabel lblNombrefactura = new JLabel("Cabecera de la factura");
		lblNombrefactura.setForeground(UIManager.getColor("ColorChooser.swatchesDefaultRecentColor"));
		lblNombrefactura.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		lblNombrefactura.setBounds(32, 225, 203, 27);
		contentPane.add(lblNombrefactura);
		
		txtCabecera = new JTextField();
		txtCabecera.setHorizontalAlignment(SwingConstants.CENTER);
		txtCabecera.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		txtCabecera.setBounds(32, 264, 334, 27);
		contentPane.add(txtCabecera);
		txtCabecera.setColumns(10);
		
		JPanel panelFoto = new JPanel();
		Border border = LineBorder.createGrayLineBorder();
		panelFoto.setBounds(463, 149, 189, 220);
		contentPane.add(panelFoto);
		panelFoto.setBackground(new Color(0, 102, 153));
		panelFoto.setLayout(null);
		
		lblFoto = new JLabel("FOTO");
		lblFoto.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblFoto.setForeground(UIManager.getColor("ColorChooser.swatchesDefaultRecentColor"));
		lblFoto.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblFoto.setBorder(border);
		lblFoto.setBounds(6, 39, 176, 110);
		panelFoto.add(lblFoto);
		
		JButton btnSeleccionarIcono = new JButton(ConstantesUtil.seleccionarIcono);
		btnSeleccionarIcono.addActionListener(this);
		btnSeleccionarIcono.setBounds(6, 161, 176, 38);
		panelFoto.add(btnSeleccionarIcono);
		btnSeleccionarIcono.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JLabel lblIconoEmpresa = new JLabel("Icono empresa");
		lblIconoEmpresa.setForeground(UIManager.getColor("ColorChooser.swatchesDefaultRecentColor"));
		lblIconoEmpresa.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconoEmpresa.setBounds(25, 6, 143, 27);
		panelFoto.add(lblIconoEmpresa);
		lblIconoEmpresa.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		
		tfRuta = new JTextField();
		tfRuta.setEnabled(false);
		tfRuta.setText("Directorio");
		tfRuta.setHorizontalAlignment(SwingConstants.CENTER);
		tfRuta.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		tfRuta.setColumns(10);
		tfRuta.setBounds(32, 186, 334, 27);
		contentPane.add(tfRuta);
		
		JLabel lblNombreEmpresa = new JLabel("Pie de la factura");
		lblNombreEmpresa.setForeground(Color.WHITE);
		lblNombreEmpresa.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		lblNombreEmpresa.setBounds(32, 303, 203, 27);
		contentPane.add(lblNombreEmpresa);
		
		txtPieDeFactura = new JTextField();
		txtPieDeFactura.setHorizontalAlignment(SwingConstants.CENTER);
		txtPieDeFactura.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		txtPieDeFactura.setColumns(10);
		txtPieDeFactura.setBounds(32, 342, 334, 27);
		contentPane.add(txtPieDeFactura);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(0, 102, 153));
		panel_1.setBounds(32, 520, 620, 222);
		contentPane.add(panel_1);
		
		txtNombreEmpresa = new JTextField();
		txtNombreEmpresa.setHorizontalAlignment(SwingConstants.CENTER);
		txtNombreEmpresa.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		txtNombreEmpresa.setColumns(10);
		txtNombreEmpresa.setBounds(234, 6, 334, 27);
		panel_1.add(txtNombreEmpresa);
		
		txtDniNifEmpresa = new JTextField();
		txtDniNifEmpresa.setHorizontalAlignment(SwingConstants.CENTER);
		txtDniNifEmpresa.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		txtDniNifEmpresa.setColumns(10);
		txtDniNifEmpresa.setBounds(234, 51, 334, 27);
		panel_1.add(txtDniNifEmpresa);
		
		JLabel lblNombreEmpresa_1 = new JLabel("Nombre empresa *");
		lblNombreEmpresa_1.setForeground(Color.WHITE);
		lblNombreEmpresa_1.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		lblNombreEmpresa_1.setBounds(9, 6, 203, 27);
		panel_1.add(lblNombreEmpresa_1);
		
		JLabel lblDninif = new JLabel("CIF/NIF *");
		lblDninif.setForeground(Color.WHITE);
		lblDninif.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		lblDninif.setBounds(9, 51, 203, 27);
		panel_1.add(lblDninif);
		
		txtDireccionEmpresa = new JTextField();
		txtDireccionEmpresa.setHorizontalAlignment(SwingConstants.CENTER);
		txtDireccionEmpresa.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		txtDireccionEmpresa.setColumns(10);
		txtDireccionEmpresa.setBounds(234, 96, 334, 27);
		panel_1.add(txtDireccionEmpresa);
		
		JLabel lblRazonSocial = new JLabel("Razón social *");
		lblRazonSocial.setForeground(Color.WHITE);
		lblRazonSocial.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		lblRazonSocial.setBounds(9, 141, 203, 27);
		panel_1.add(lblRazonSocial);
		
		txtRazonSocial = new JTextField();
		txtRazonSocial.setHorizontalAlignment(SwingConstants.CENTER);
		txtRazonSocial.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		txtRazonSocial.setColumns(10);
		txtRazonSocial.setBounds(234, 141, 334, 27);
		panel_1.add(txtRazonSocial);
		
		JLabel lblDireccion = new JLabel("Dirección *");
		lblDireccion.setForeground(Color.WHITE);
		lblDireccion.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		lblDireccion.setBounds(9, 96, 203, 27);
		panel_1.add(lblDireccion);
		
		JLabel lblTelefono = new JLabel("Teléfono *");
		lblTelefono.setForeground(new Color(255, 255, 255));
		lblTelefono.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		lblTelefono.setBounds(9, 186, 203, 27);
		panel_1.add(lblTelefono);
		
		txtTelefono = new JTextField();
		txtTelefono.setHorizontalAlignment(SwingConstants.CENTER);
		txtTelefono.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(234, 186, 334, 27);
		panel_1.add(txtTelefono);
		
		JLabel lblInicialDeCaja = new JLabel("Inicial de caja *");
		lblInicialDeCaja.setForeground(Color.WHITE);
		lblInicialDeCaja.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		lblInicialDeCaja.setBounds(32, 380, 203, 27);
		contentPane.add(lblInicialDeCaja);
		
		txtInicialDeCaja = new JTextField();
		txtInicialDeCaja.setHorizontalAlignment(SwingConstants.CENTER);
		txtInicialDeCaja.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		txtInicialDeCaja.setColumns(10);
		txtInicialDeCaja.setBounds(32, 418, 334, 27);
		contentPane.add(txtInicialDeCaja);
		
		JLabel lblDatosDeLa = new JLabel("Datos de la empresa");
		lblDatosDeLa.setHorizontalAlignment(SwingConstants.CENTER);
		lblDatosDeLa.setForeground(Color.WHITE);
		lblDatosDeLa.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		lblDatosDeLa.setBounds(32, 481, 622, 27);
		contentPane.add(lblDatosDeLa);
		fillForm();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton source = (JButton) e.getSource();
		
		if(source.getText().equals(ConstantesUtil.seleccionarCarpeta)){
			
			int resultado;
			SelecionaDirectorio frame = new SelecionaDirectorio();
			frame.fchCargarRuta.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			resultado = frame.fchCargarRuta.showOpenDialog(null);
			if (JFileChooser.APPROVE_OPTION == resultado){
		        pathDirectorioFactura = frame.fchCargarRuta.getSelectedFile().getPath();
		        try{
		        	
		        	tfRuta.setText(pathDirectorioFactura);
		        }catch(Exception ex){
		        	JOptionPane.showMessageDialog(null, "Error abriendo la imagen "+ ex);
		        }
			}		
		}else if(source.getText().equals(ConstantesUtil.salir)){
			dispose();
		}else if(source.getText().equals(ConstantesUtil.seleccionarIcono)){
			int resultado;
			CargaFoto frameFoto = new CargaFoto();
			frameFoto.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			FileNameExtensionFilter filtro = new FileNameExtensionFilter("JPG y PNG","jpg","png");
			frameFoto.fchCargarFoto.setFileFilter(filtro);
			resultado = frameFoto.fchCargarFoto.showOpenDialog(null);
				
			if (JFileChooser.APPROVE_OPTION == resultado){
		        fichero = frameFoto.fchCargarFoto.getSelectedFile();
		        rutaIcono = fichero != null ? fichero.getAbsolutePath() : null;
		        try{
		        	ImageIcon icon = new ImageIcon(fichero.toString());
		        	Icon icono = new ImageIcon(icon.getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_DEFAULT));
		        	lblFoto.setText(null);
		           	lblFoto.setIcon(icono);
		        }catch(Exception ex){
		        	JOptionPane.showMessageDialog(null, "Error abriendo la imagen "+ ex);
		        }
			}
		}else if(source.getText().equals(ConstantesUtil.guardar)){
			Configuracion configuracion = fillObject();
			if(configuracion != null){
				service.insertarConfiguracion(configuracion);
				
	           	JOptionPane.showMessageDialog(null, "Guardado con éxito");
	           	dispose();
			}
			
		}
		
		
	}
	private void fillForm(){
		if(configuracion != null){
			
			txtCabecera.setText(configuracion.getCabeceraFactura());
			txtDireccionEmpresa.setText(configuracion.getDireccionEmpresa());
			txtDniNifEmpresa.setText(configuracion.getCifNif());
			txtInicialDeCaja.setText(configuracion.getInicialCaja().toString());
			txtNombreEmpresa.setText(configuracion.getNombreEmpresa());
			txtPieDeFactura.setText(configuracion.getPieFactura());
			txtRazonSocial.setText(configuracion.getRazonSocial());
			txtTelefono.setText(configuracion.getTelefonoEmpresa());
			tfRuta.setText(configuracion.getDirectorioFactura());
			try{
	        	ImageIcon icon = new ImageIcon(configuracion.getIcono() != null ? configuracion.getIcono() : null);
	        	Icon icono = new ImageIcon(icon.getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_DEFAULT));
	        	lblFoto.setText(null);
	           	lblFoto.setIcon(icono);
	        }catch(Exception ex){
	        	JOptionPane.showMessageDialog(null, "Error abriendo la imagen "+ ex);
	        }
			
		}
	}
	private Configuracion fillObject(){
		Configuracion configuracion = null;
		if(validar()){	
			configuracion = Configuracion.getInstance();
			configuracion.setIcono(rutaIcono != null ? rutaIcono : null);
			configuracion.setCifNif(txtDniNifEmpresa.getText());
			configuracion.setDireccionEmpresa(txtDireccionEmpresa.getText() );
//		FIXME	configuracion.setDirectorioCierre(falta direccion cierre);
			configuracion.setDirectorioFactura(pathDirectorioFactura);
//			configuracion.setEmailEmpresa(falta email empresa);
			configuracion.setInicialCaja(inicialCaja1);
			configuracion.setCabeceraFactura(txtCabecera.getText());
			configuracion.setNombreEmpresa(txtNombreEmpresa.getText());
			configuracion.setPieFactura(txtPieDeFactura.getText());
			configuracion.setRazonSocial(txtRazonSocial.getText());
			configuracion.setTelefonoEmpresa(txtTelefono.getText());
			
		}else{
			JOptionPane.showMessageDialog(this, "Debes rellenar correctamente los campos para crear facturas");
		}
		return configuracion;
	}
	private Boolean validar() {
		Boolean dev = Boolean.FALSE;
		
		if(!txtInicialDeCaja.getText().equals("") && Validador.isDecimal(txtInicialDeCaja.getText())){
			dev = true;		
		}else{
			return false;
		}
		if(!txtNombreEmpresa.getText().equals("") && Validador.isAlfaNumerico(txtNombreEmpresa.getText())){
			dev = true;
		}else{
			return false;
		}
		if(!txtTelefono.getText().equals("") && Validador.isNumero(txtTelefono.getText())){
			dev = true;
		}else{
			return false;
		}
		
		if(!txtDireccionEmpresa.getText().equals("") && Validador.isDireccion(txtDireccionEmpresa.getText())){
			dev = true;
		}else{
			return false;
		}
		
		if(!txtDniNifEmpresa.getText().equals("") && Validador.isAlfaNumerico(txtDniNifEmpresa.getText())){
			dev = true;
		}else{
			return false;
		}
		if(!txtRazonSocial.getText().equals("")){
			dev = true;
		}else{
			return false;
		}
		if(!txtInicialDeCaja.getText().equals("") && Validador.isDecimal(txtInicialDeCaja.getText())){
			inicialCaja = ConstantesUtil.obtenerNumero(txtInicialDeCaja.getText());
			inicialCaja1 = new BigDecimal(inicialCaja);
			dev = true;		
		}else{
			return false;
		}
		return dev;
	}
}
