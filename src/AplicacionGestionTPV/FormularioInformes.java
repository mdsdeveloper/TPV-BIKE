package AplicacionGestionTPV;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import PantallaVentas.FacturaImpresa;
import PantallaVentas.PantallaTPV;
import controladorService.GestionService;
import controladorService.GestionServiceImpl;
import modelo.Configuracion;
import modelo.FacturaHecha;
import util.ConstantesUtil;
import util.IVAEnum;
import util.UtilService;
import util.Validador;
import java.awt.FlowLayout;
/**
 * 
 * @author Two hands Technology
 *
 */
public class FormularioInformes extends JDialog implements ActionListener{

	private JPanel contentPanel,panelTabla;
	private JTable table, modeloTabla;
	private JScrollPane scrollPane;
	private int filasSeleccionadas;
	private String idFactura;
	private GestionService service = new GestionServiceImpl();
	private JTextField txtNombreCliente,txtTelefonoCliente,txtCifCliente,txtEmailCliente,txtDireccionCliente,txtFecha;
	private JTextField txtEmpleado,txtBaseimponible,txtFormaDePago,txtNumero,txtNombreempresa,txtRazonsocial,txtCifnif;
	private JTextField txtDireccion,txtTelefono,txtTotal;
	private JButton btnSalir, btnNuevo, btnGuardModif, btnCancelar, btnEliminar;
	private double  altoVentana, anchoVentana, ancho, alto;
	private JButton btnImprimir;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FormularioInformes dialog = new FormularioInformes();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FormularioInformes() {
		altoVentana = 726;
		anchoVentana = 1223;
		ancho = ConstantesUtil.ancho;
		alto = ConstantesUtil.alto;
		setLocationRelativeTo(null); 
		setResizable(false);
		setBounds((int)ancho/2 - (int)anchoVentana/2, (int)alto/2 - (int)altoVentana/2, 1223,726);
		contentPanel = new JPanel();
		getContentPane().setBackground(new Color(0, 102, 153));
		getContentPane().setForeground(new Color(0, 0, 0));
		setModal(true);
		getContentPane().setLayout(null);
		contentPanel.setBackground(new Color(0, 102, 153));
		contentPanel.setBounds(6, 81, 1211, 414);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		setUndecorated(true);
		
		Panel panel = new Panel();
		panel.setBackground(new Color(0, 102, 153));
		panel.setBounds(0, 217, 1211, 191);
		contentPanel.add(panel);
		panel.setLayout(new GridLayout(8, 4, 0, 0));
		
		JLabel lblNombreCliente = new JLabel("Cliente *");
		lblNombreCliente.setForeground(Color.WHITE);
		lblNombreCliente.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		panel.add(lblNombreCliente);
		
		txtNombreCliente = new JTextField();
		txtNombreCliente.setText("Nombre empresa");
		txtNombreCliente.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		txtNombreCliente.setEnabled(false);
		txtNombreCliente.setColumns(10);
		panel.add(txtNombreCliente);
		
		JLabel label_1 = new JLabel("Teléfono");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		panel.add(label_1);
		
		txtTelefonoCliente = new JTextField();
		txtTelefonoCliente.setText("Teléfono");
		txtTelefonoCliente.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		txtTelefonoCliente.setEnabled(false);
		txtTelefonoCliente.setColumns(10);
		panel.add(txtTelefonoCliente);
		
		JLabel label_2 = new JLabel("CIF/NIF *");
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		panel.add(label_2);
		
		txtCifCliente = new JTextField();
		txtCifCliente.setText("CIF / NIF");
		txtCifCliente.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		txtCifCliente.setEnabled(false);
		txtCifCliente.setColumns(10);
		panel.add(txtCifCliente);
		
		JLabel label_3 = new JLabel("Email");
		label_3.setForeground(Color.WHITE);
		label_3.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		panel.add(label_3);
		
		txtEmailCliente = new JTextField();
		txtEmailCliente.setText("Email");
		txtEmailCliente.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		txtEmailCliente.setEnabled(false);
		txtEmailCliente.setColumns(10);
		panel.add(txtEmailCliente);
		
		JLabel label_4 = new JLabel("Dirección *");
		label_4.setForeground(Color.WHITE);
		label_4.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		panel.add(label_4);
		
		txtDireccionCliente = new JTextField();
		txtDireccionCliente.setText("Dirección");
		txtDireccionCliente.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		txtDireccionCliente.setEnabled(false);
		txtDireccionCliente.setColumns(10);
		panel.add(txtDireccionCliente);
		
		JLabel lblFecha = new JLabel("Fecha *");
		lblFecha.setForeground(new Color(255, 255, 255));
		lblFecha.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		panel.add(lblFecha);
		
		txtFecha = new JTextField();
		txtFecha.setEnabled(false);
		txtFecha.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		txtFecha.setText("Fecha");
		panel.add(txtFecha);
		txtFecha.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Empleado");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		panel.add(lblNewLabel);
		
		txtEmpleado = new JTextField();
		txtEmpleado.setEnabled(false);
		txtEmpleado.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		txtEmpleado.setText("Empleado");
		panel.add(txtEmpleado);
		txtEmpleado.setColumns(10);
		
		JLabel lblBaseImponible = new JLabel("Base imponible");
		lblBaseImponible.setForeground(new Color(255, 255, 255));
		lblBaseImponible.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		panel.add(lblBaseImponible);
		
		txtBaseimponible = new JTextField();
		txtBaseimponible.setEnabled(false);
		txtBaseimponible.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		txtBaseimponible.setText("Base Imponible");
		panel.add(txtBaseimponible);
		txtBaseimponible.setColumns(10);
		
		JLabel lblFormaDePago = new JLabel("Forma de pago");
		lblFormaDePago.setForeground(new Color(255, 255, 255));
		lblFormaDePago.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		panel.add(lblFormaDePago);
		
		txtFormaDePago = new JTextField();
		txtFormaDePago.setEnabled(false);
		txtFormaDePago.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		txtFormaDePago.setText("Forma de pago");
		panel.add(txtFormaDePago);
		txtFormaDePago.setColumns(10);
		
		JLabel lblNumero = new JLabel("Número");
		lblNumero.setForeground(new Color(255, 255, 255));
		lblNumero.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		panel.add(lblNumero);
		
		txtNumero = new JTextField();
		txtNumero.setEnabled(false);
		txtNumero.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		txtNumero.setText("Número");
		panel.add(txtNumero);
		txtNumero.setColumns(10);
		
		JLabel lblNombreEmpresa = new JLabel("Nombre empresa");
		lblNombreEmpresa.setForeground(new Color(255, 255, 255));
		lblNombreEmpresa.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		panel.add(lblNombreEmpresa);
		
		txtNombreempresa = new JTextField();
		txtNombreempresa.setForeground(new Color(255, 255, 255));
		txtNombreempresa.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		txtNombreempresa.setEnabled(false);
		txtNombreempresa.setText("Nombre Empresa");
		panel.add(txtNombreempresa);
		txtNombreempresa.setColumns(10);
		
		JLabel lblRazonSocial = new JLabel("Razón Social");
		lblRazonSocial.setForeground(new Color(255, 255, 255));
		lblRazonSocial.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		panel.add(lblRazonSocial);
		
		txtRazonsocial = new JTextField();
		txtRazonsocial.setEnabled(false);
		txtRazonsocial.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		txtRazonsocial.setText("Razon Social");
		panel.add(txtRazonsocial);
		txtRazonsocial.setColumns(10);
		
		JLabel lblCifnif = new JLabel("CIF/NIF");
		lblCifnif.setForeground(new Color(255, 255, 255));
		lblCifnif.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		panel.add(lblCifnif);
		
		txtCifnif = new JTextField();
		txtCifnif.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		txtCifnif.setEnabled(false);
		txtCifnif.setText("CIF / NIF");
		panel.add(txtCifnif);
		txtCifnif.setColumns(10);
		
		JLabel lblDireccion = new JLabel("Dirección");
		lblDireccion.setForeground(new Color(255, 255, 255));
		lblDireccion.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		panel.add(lblDireccion);
		
		txtDireccion = new JTextField();
		txtDireccion.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		txtDireccion.setEnabled(false);
		txtDireccion.setText("Dirección");
		panel.add(txtDireccion);
		txtDireccion.setColumns(10);
		
		JLabel lblTelefono = new JLabel("Teléfono");
		lblTelefono.setForeground(new Color(255, 255, 255));
		lblTelefono.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		panel.add(lblTelefono);
		
		txtTelefono = new JTextField();
		txtTelefono.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		txtTelefono.setEnabled(false);
		txtTelefono.setText("Teléfono");
		panel.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setForeground(new Color(255, 255, 255));
		lblTotal.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		panel.add(lblTotal);
		
		txtTotal = new JTextField();
		txtTotal.setEnabled(false);
		txtTotal.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		txtTotal.setText("Total");
		panel.add(txtTotal);
		txtTotal.setColumns(10);
		
		JPanel panelSalir = new JPanel();
		panelSalir.setLayout(null);
		panelSalir.setBounds(6, 649, 1211, 71);
		getContentPane().add(panelSalir);
		
		btnSalir = new JButton(ConstantesUtil.salir);
		btnSalir.setIcon(new ImageIcon(FormularioEmpleados.class.getResource(ConstantesUtil.pathSalir)));
		btnSalir.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnSalir.setBounds(1048, 6, 143, 61);
		panelSalir.add(btnSalir);
		
		JPanel panelTitulo = new JPanel();
		panelTitulo.setBounds(6, 6, 1211, 66);
		getContentPane().add(panelTitulo);
		panelTitulo.setLayout(null);
		
		JLabel lblFormularioInformes = new JLabel("FORMULARIO FACTURAS");
		lblFormularioInformes.setBounds(443, 16, 336, 32);
		lblFormularioInformes.setHorizontalAlignment(SwingConstants.CENTER);
		lblFormularioInformes.setFont(new Font("Lucida Grande", Font.BOLD, 26));
		panelTitulo.add(lblFormularioInformes);
		
		JPanel panelBotonera = new JPanel();
		panelBotonera.setLayout(null);
		panelBotonera.setBounds(6, 507, 1211, 108);
		getContentPane().add(panelBotonera);
		
		btnNuevo = new JButton(ConstantesUtil.nuevo);
		btnNuevo.setIcon(new ImageIcon(FormularioEmpleados.class.getResource(ConstantesUtil.pathNuevo)));
		btnNuevo.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		btnNuevo.setEnabled(true);
		btnNuevo.setBounds(43, 6, 147, 96);
		panelBotonera.add(btnNuevo);
		
		btnGuardModif = new JButton(ConstantesUtil.guardar);
		btnGuardModif.setIcon(new ImageIcon(FormularioEmpleados.class.getResource(ConstantesUtil.pathGuardar)));
		btnGuardModif.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		btnGuardModif.setEnabled(false);
		btnGuardModif.setBounds(293, 6, 147, 96);
		panelBotonera.add(btnGuardModif);
		
		btnCancelar = new JButton(ConstantesUtil.cancelar);
		btnCancelar.setIcon(new ImageIcon(FormularioEmpleados.class.getResource(ConstantesUtil.pathCancelar)));
		btnCancelar.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		btnCancelar.setBounds(779, 6, 147, 96);
		panelBotonera.add(btnCancelar);
		
		btnEliminar = new JButton(ConstantesUtil.eliminar);
		btnEliminar.setIcon(new ImageIcon(FormularioEmpleados.class.getResource(ConstantesUtil.pathEliminar)));
		btnEliminar.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		btnEliminar.setEnabled(false);
		btnEliminar.setBounds(533, 6, 147, 96);
		panelBotonera.add(btnEliminar);
		
		btnImprimir = new JButton(ConstantesUtil.imprimir);
		btnImprimir.setIcon(new ImageIcon(PantallaTPV.class.getResource(ConstantesUtil.pathPrint)));
		btnImprimir.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		btnImprimir.setEnabled(false);
		btnImprimir.setBounds(1019, 7, 147, 96);
		panelBotonera.add(btnImprimir);
		
		btnNuevo.addActionListener(this);
		btnGuardModif.addActionListener(this);
		btnCancelar.addActionListener(this);
		btnEliminar.addActionListener(this);
		btnSalir.addActionListener(this);
		btnImprimir.addActionListener(this);
		
		construirTabla();
		
		estadoInicial();
	}
	private void estadoInicial() {
		
		EnableDisableForm(false);		
		btnEliminar.setEnabled(false);
		btnGuardModif.setEnabled(false);
		btnImprimir.setEnabled(false);
		btnGuardModif.setText(ConstantesUtil.guardar);
		btnNuevo.setEnabled(true);
		btnNuevo.requestFocus();
		
	}
	private void EnableDisableForm(boolean siNo) {
		txtNombreCliente.setEnabled(siNo);
		txtCifCliente.setEnabled(siNo);
		txtEmailCliente.setEnabled(siNo);
		txtDireccionCliente.setEnabled(siNo);
		txtTelefonoCliente.setEnabled(siNo);
//		txtFecha.setEnabled(siNo);
		txtTotal.setEnabled(siNo);
		txtNumero.setEnabled(siNo);
		txtEmpleado.setEnabled(siNo);
		btnImprimir.setEnabled(siNo);
		
	}
	
	private void clearForm() {
		txtNombreCliente.setText("");
		txtBaseimponible.setText("");
		txtCifCliente.setText("");
		txtCifnif.setText("");
		txtDireccion.setText("");
		txtDireccionCliente.setText("");
		txtEmailCliente.setText("");
		txtEmpleado.setText("");
		txtFecha.setText("");
		txtFormaDePago.setText("");
		txtNombreempresa.setText("");
		txtNumero.setText("");
		txtRazonsocial.setText("");
		txtTelefono.setText("");
		txtTelefonoCliente.setText("");
		txtTotal.setText("");
	}
private void construirTabla() {

		panelTabla = new JPanel();
		panelTabla.setBounds(0, 0, 1211, 211);
		panelTabla.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 1211, 211);
		
		
		String cabecera[] = {"IdFactura","Cliente","CIF/NIF","Teléfono","Email","Dirección","Fecha", "Empleado","F.pago","N.factura","Precio","Nombre","R.social","CIF/NIF","Dirección","Teléfono","Total"};	
		String datos[][] = service.rellenarTablaFacturas();
		DefaultTableModel model = new DefaultTableModel(datos, cabecera);
		
		table = new JTable(model);
	
		table.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		table.setBounds(277, 60, 426, 138);
		JTableHeader th = table.getTableHeader();
		th.setBackground(Color.GRAY);
		Font fuente = ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.PLAIN, 13) : new Font("Lucida Grande", Font.PLAIN, 17);
		th.setFont(fuente);
		th.setForeground(UIManager.getColor("Button.highlight"));
		table.setTableHeader(th);
		scrollPane.setViewportView(table);
		
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
		table.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
		table.addMouseListener(new MouseAdapter() 
		   {
		      public void mouseClicked(MouseEvent e) 
		      {
		    	  filasSeleccionadas = table.getSelectedRow();
		    	  if(filasSeleccionadas != -1){
		    		  EnableDisableForm(true);
			    	  btnEliminar.setEnabled(true);
			    	  modeloTabla = table;
			    	  clearForm();
			    	  fillForm();		    	  
			    	  btnNuevo.setEnabled(false);
			    	  btnGuardModif.setEnabled(true);	    	  
			    	  btnGuardModif.setText(ConstantesUtil.modificar); 
		    	  }
		      }
		   });
		panelTabla.add(scrollPane);
		contentPanel.add(panelTabla);
	}
	
	private void fillForm() {
		txtNombreCliente.setText((String) modeloTabla.getValueAt(filasSeleccionadas, 1));
	  	txtCifCliente.setText((String) modeloTabla.getValueAt(filasSeleccionadas, 2));
	  	txtTelefonoCliente.setText((String) modeloTabla.getValueAt(filasSeleccionadas, 3));
	  	txtEmailCliente.setText((String)modeloTabla.getValueAt(filasSeleccionadas, 4));
		txtDireccionCliente.setText((String)modeloTabla.getValueAt(filasSeleccionadas, 5));
		txtFecha.setText((String)modeloTabla.getValueAt(filasSeleccionadas, 6));
		txtEmpleado.setText((String)modeloTabla.getValueAt(filasSeleccionadas, 7));
		txtFormaDePago.setText((String)modeloTabla.getValueAt(filasSeleccionadas, 8));
		txtNumero.setText((String)modeloTabla.getValueAt(filasSeleccionadas, 9));
		txtBaseimponible.setText((String)modeloTabla.getValueAt(filasSeleccionadas, 10));
		txtNombreempresa.setText((String)modeloTabla.getValueAt(filasSeleccionadas, 11));
		txtRazonsocial.setText((String)modeloTabla.getValueAt(filasSeleccionadas, 12));
		txtCifnif.setText((String)modeloTabla.getValueAt(filasSeleccionadas, 13));
		txtDireccion.setText((String)modeloTabla.getValueAt(filasSeleccionadas, 14));
		txtTelefono.setText((String)modeloTabla.getValueAt(filasSeleccionadas, 15));
		txtTotal.setText((String)modeloTabla.getValueAt(filasSeleccionadas, 16));
	  
		idFactura = (String)  modeloTabla.getValueAt(filasSeleccionadas, 0);
	}
	
	private FacturaHecha fillObject(){
		FacturaHecha facturaHecha = null;
		if(validar()){	
			IVAEnum iva = IVAEnum.VEINTIUNO;
			BigDecimal importeAux = BigDecimal.ZERO;		
			BigDecimal totalIva = new BigDecimal(iva.getCodigo());
			totalIva = totalIva.divide(new BigDecimal(100));
			
			facturaHecha = new FacturaHecha();
			facturaHecha.setNombreCliente(txtNombreCliente.getText());
			facturaHecha.setDniNifCliente(txtCifCliente.getText());
			facturaHecha.setEmailCliente(txtEmailCliente.getText());
			facturaHecha.setTelefonoCliente(txtTelefonoCliente.getText());
			facturaHecha.setDireccionCliente(txtDireccionCliente.getText());
			facturaHecha.setFecha(UtilService.obtenerFechaByString(txtFecha.getText()));
			facturaHecha.setEmpleado(txtEmpleado.getText());
			facturaHecha.setNumero(Integer.valueOf(txtNumero.getText()));
			BigDecimal base = new BigDecimal(txtTotal.getText());
			facturaHecha.setBaseImponible(base.subtract(base.multiply(totalIva)));
			facturaHecha.setTotal(base);
		}else{
			JOptionPane.showMessageDialog(this, "Debes rellenar correctamente los campos !!!");
		}
		return facturaHecha;
	}
	
	private FacturaHecha fillObjectNuevo(){
		FacturaHecha facturaHecha = null;
		if(validar()){	
			Configuracion configuracion;
			IVAEnum iva = IVAEnum.VEINTIUNO;
			BigDecimal importeAux = BigDecimal.ZERO;		
			BigDecimal totalIva = new BigDecimal(iva.getCodigo());
			totalIva = totalIva.divide(new BigDecimal(100));
			
			facturaHecha = new FacturaHecha();
			facturaHecha.setNombreCliente(txtNombreCliente.getText());
			facturaHecha.setDniNifCliente(txtCifCliente.getText());
			facturaHecha.setEmailCliente(txtEmailCliente.getText());
			facturaHecha.setTelefonoCliente(txtTelefonoCliente.getText());
			facturaHecha.setDireccionCliente(txtDireccionCliente.getText());
			facturaHecha.setFecha(new Date());
			facturaHecha.setEmpleado(txtEmpleado.getText());
			facturaHecha.setNumero(Integer.valueOf(txtNumero.getText()));
			BigDecimal base = new BigDecimal(txtTotal.getText());
			facturaHecha.setBaseImponible(base.subtract(base.multiply(totalIva)));
			facturaHecha.setTotal(base);
			
			configuracion = service.getConfiguracion();
			facturaHecha.setDireccion(configuracion.getDireccionEmpresa());
			facturaHecha.setDniNif(configuracion.getCifNif());
			facturaHecha.setNombre(configuracion.getNombreEmpresa());
			facturaHecha.setTelefono(configuracion.getTelefonoEmpresa());
			facturaHecha.setRazonSocial(configuracion.getRazonSocial());
			facturaHecha.setFormaPago(true);
			
			
		}else{
			JOptionPane.showMessageDialog(this, "Debes rellenar correctamente los campos !!!");
		}
		return facturaHecha;
	}
	
	private Boolean validar() {
		Boolean dev = Boolean.FALSE;
		
		if(!txtNombreCliente.getText().equals("") && Validador.isAlfaNumericoConPunto(txtNombreCliente.getText())){
			dev = true;
		}else{
			return false;
		}
		if(!txtCifCliente.getText().equals("") && Validador.isAlfaNumerico(txtCifCliente.getText())){
			dev = true;
		}else{
			return false;
		}
		if(!txtNumero.getText().equals("") && Validador.isNumero(txtNumero.getText())){
			dev = true;
		}else{
			return false;
		}
		if(!txtEmailCliente.getText().equals("")){
			if(Validador.isEmail(txtEmailCliente.getText())){
				dev = true;	
			}else{
				return false;	
			}
		}
		if(!txtTelefonoCliente.getText().equals("")){
			if(Validador.isNumero(txtTelefonoCliente.getText())){
				dev = true;
			}else{
				return false;
			}
		}
		if(!txtDireccionCliente.getText().equals("") && Validador.isDireccion(txtDireccionCliente.getText())){
			dev = true;
		}else{
			return false;
		}
		if(!txtTotal.getText().equals("") && Validador.isDecimal(txtTotal.getText())){
			dev = true;
		}else{
			return false;
		}
		return dev;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();
		
		
		if(source.getText().equals(ConstantesUtil.salir)){
//			
			dispose();
		}else if(source.getText().equals(ConstantesUtil.nuevo)){
			clearForm();
			EnableDisableForm(true);
			btnGuardModif.setEnabled(true);
			txtNombreCliente.requestFocus();			
		}else if(source.getText().equals(ConstantesUtil.guardar)){			
			FacturaHecha facturaHecha = fillObjectNuevo();			
			if(facturaHecha != null){
				service.insertarFacturaHecha(facturaHecha);
				clearForm();
				EnableDisableForm(false);
				btnNuevo.requestFocus();
				btnGuardModif.setEnabled(false);
			}					
			
		}else if(source.getText().equals(ConstantesUtil.modificar)){
			if(idFactura != null && !idFactura.equals("")){
				FacturaHecha facturaHecha = fillObject();
				if(facturaHecha != null){
					facturaHecha.setIdFactura(Long.parseLong(idFactura));
					service.modificarFacturaHecha(facturaHecha);
				}
				clearForm();
				estadoInicial();
			}
		}else if(source.getText().equals(ConstantesUtil.eliminar)){
			service.eliminarFacturaHecha(idFactura);
			clearForm();
			estadoInicial();
		}else if(source.getText().equals(ConstantesUtil.cancelar)){
			clearForm();
			estadoInicial();
		}else if(source.getText().equals(ConstantesUtil.imprimir)){		
			FacturaHecha facturaHecha = fillObjectNuevo();			
			if(facturaHecha != null){
				new FacturaImpresa(facturaHecha);
			}
			clearForm();
			estadoInicial();
		}
		contentPanel.remove(panelTabla);
		construirTabla();
	}

}

