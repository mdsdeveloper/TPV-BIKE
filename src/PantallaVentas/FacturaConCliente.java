package PantallaVentas;

import java.awt.Color;
import java.awt.EventQueue;
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
import javax.swing.JFrame;
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

import AplicacionGestionTPV.FormularioCliente;
import AplicacionGestionTPV.FormularioEmpleados;
import controladorService.AppService;
import controladorService.AppServiceImpl;
import controladorService.GestionService;
import controladorService.GestionServiceImpl;
import modelo.Configuracion;
import modelo.FacturaHecha;
import util.ConstantesUtil;
import util.UtilService;
/**
 * 
 * @author Two hands Technology
 *
 */
public class FacturaConCliente extends JDialog implements ActionListener {

	private JPanel contentPane,panelTabla,panelTablaClientes;
	private JTable table, modeloTabla, tableClientes, modeloTablaCliente;
	private JScrollPane scrollPane,scrollPaneClientes;
	private JButton btnAniadirCliente, btnCrearFactura, btnEliminarVenta, btnCancelar,btnSalir,btnAniadirVenta;

	private int filasSeleccionadas,filasSeleccionadasClientes;
	private GestionService service = new GestionServiceImpl();
	private boolean tablaSeleccionada,tablaSeleccionadaCliente;
	private JLabel lblVentas,lblClientes,lblDatosDeLa,lblNumerofactura,lblNombreCliente,lblFechafactura,lblTelefonoCliente,lblBaseImponible,lblCifNif,lblTotal,lblDireccionCliente;
	private Panel panel;
	private JTextField txtNombreCliente,txtFechafactura,txtTelefonoCliente,txtBaseImponible,txtCifNif,txtTotal,txtDireccion;
	private JButton btnEliminarCliente;
	private JButton btnNuevocliente;
	private double anchoVentana, altoVentana, ancho, alto;
	private BigDecimal baseImponible,total;
	private String direccionCliente,dniNifCliente,empleado,emailCliente;
	private Date fechaFactura;
	private AppService appService = new AppServiceImpl();
	private String telfonoCliente,idCliente, idFactura, idVenta, idLineaVentaRealizada, formaPago;
	private GestionService gestionService = new GestionServiceImpl();
	private Configuracion configuracion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FacturaConCliente frame = new FacturaConCliente();
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
	public FacturaConCliente() {
		anchoVentana = 1009;
		altoVentana =  776;
		ancho = ConstantesUtil.ancho;
		alto = ConstantesUtil.alto;
		setLocationRelativeTo(null); 
		setResizable(false);
		setBounds((int)ancho/2 - (int)anchoVentana/2, (int)alto/2 - (int)altoVentana/2,(int) anchoVentana,(int) altoVentana);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 102, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setModal(true);
		
		JPanel panelTitulo = new JPanel();
		panelTitulo.setLayout(null);
		panelTitulo.setBounds(6, 6, 997, 66);
		contentPane.add(panelTitulo);
		
		Font font = ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 13) : new Font("Lucida Grande", Font.BOLD, 17);
		Font fontButton = ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.PLAIN, 13) : new Font("Lucida Grande", Font.PLAIN, 17);
		JLabel lblFacturas = new JLabel("FACTURAS");
		lblFacturas.setHorizontalAlignment(SwingConstants.CENTER);
		lblFacturas.setFont(ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.PLAIN, 20) : new Font("Lucida Grande", Font.BOLD, 26));
		lblFacturas.setBounds(341, 16, 316, 32);
		panelTitulo.add(lblFacturas);
		
		JPanel panelSalir = new JPanel();
		panelSalir.setLayout(null);
		panelSalir.setBounds(6, 673, 997, 71);
		contentPane.add(panelSalir);
		
		btnSalir = new JButton(ConstantesUtil.salir);
		btnSalir.setIcon(new ImageIcon(FormularioEmpleados.class.getResource(ConstantesUtil.pathSalir)));
		btnSalir.setFont(ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.PLAIN, 13) : new Font("Lucida Grande", Font.PLAIN, 15));
		btnSalir.setBounds(834, 6, 143, 61);
		panelSalir.add(btnSalir);
		
		btnNuevocliente = new JButton(ConstantesUtil.nuevoCliente);
		btnNuevocliente.setIcon(new ImageIcon(FormularioEmpleados.class.getResource(ConstantesUtil.pathNuevo)));
		btnNuevocliente.setFont(fontButton);
		btnNuevocliente.setBounds(19, 6, 191, 61);
		panelSalir.add(btnNuevocliente);
		

		btnCrearFactura = new JButton(ConstantesUtil.crearFactura);
		btnCrearFactura.setBounds(423, 6, 200, 59);
		panelSalir.add(btnCrearFactura);
		btnCrearFactura.setIcon(new ImageIcon(FormularioEmpleados.class.getResource(ConstantesUtil.pathGuardar)));
		btnCrearFactura.setFont(fontButton);
		btnCrearFactura.addActionListener(this);
		
		JPanel panelBotonera = new JPanel();
		panelBotonera.setLayout(null);
		panelBotonera.setBounds(6, 343, 997, 108);
		contentPane.add(panelBotonera);
		

		btnAniadirCliente = new JButton(ConstantesUtil.aniadirCliente);
		btnAniadirCliente.setIcon(new ImageIcon(FormularioEmpleados.class.getResource(ConstantesUtil.pathNuevo)));
		btnAniadirCliente.setFont(fontButton);
		btnAniadirCliente.setEnabled(true);
		btnAniadirCliente.setBounds(201, 7, 191, 96);
		panelBotonera.add(btnAniadirCliente);
		
		btnCancelar = new JButton(ConstantesUtil.cancelar);
		btnCancelar.setIcon(new ImageIcon(FormularioEmpleados.class.getResource(ConstantesUtil.pathCancelar)));
		btnCancelar.setFont(fontButton);
		btnCancelar.setBounds(840, 6, 151, 96);
		panelBotonera.add(btnCancelar);
		
		btnEliminarVenta = new JButton(ConstantesUtil.eliminarVenta);
		btnEliminarVenta.setIcon(new ImageIcon(FormularioEmpleados.class.getResource(ConstantesUtil.pathEliminar)));
		btnEliminarVenta.setFont(fontButton);
		btnEliminarVenta.setBounds(404, 5, 206, 96);
		panelBotonera.add(btnEliminarVenta);
		
		btnAniadirVenta = new JButton(ConstantesUtil.aniadirVenta);
		btnAniadirVenta.setIcon(new ImageIcon(FormularioEmpleados.class.getResource(ConstantesUtil.pathNuevo)));
		btnAniadirVenta.setFont(fontButton);
		btnAniadirVenta.setBounds(6, 6, 183, 96);
		panelBotonera.add(btnAniadirVenta);
		
		btnEliminarCliente = new JButton(ConstantesUtil.eliminarCliente);
		btnEliminarCliente.setIcon(new ImageIcon(FormularioEmpleados.class.getResource(ConstantesUtil.pathEliminar)));
		btnEliminarCliente.setFont(fontButton);
		btnEliminarCliente.setBounds(622, 5, 206, 96);
		panelBotonera.add(btnEliminarCliente);
		
		btnAniadirCliente.addActionListener(this);
		btnCancelar.addActionListener(this);
		btnEliminarVenta.addActionListener(this);
		btnSalir.addActionListener(this);
		btnAniadirVenta.addActionListener(this);
		btnEliminarCliente.addActionListener(this);
		btnNuevocliente.addActionListener(this);
		
		
		
		lblVentas = new JLabel("VENTAS");
		lblVentas.setForeground(UIManager.getColor("Button.highlight"));
		lblVentas.setHorizontalAlignment(SwingConstants.CENTER);
		lblVentas.setFont(ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.PLAIN, 20) : new Font("Lucida Grande", Font.BOLD, 26));
		lblVentas.setBounds(105, 84, 316, 32);
		contentPane.add(lblVentas);
		
		lblClientes = new JLabel("CLIENTES");
		lblClientes.setHorizontalAlignment(SwingConstants.CENTER);
		lblClientes.setForeground(Color.WHITE);
		lblClientes.setFont(ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.PLAIN, 20) :new Font("Lucida Grande", Font.BOLD, 26));
		lblClientes.setBounds(624, 84, 316, 32);
		contentPane.add(lblClientes);
		
		lblDatosDeLa = new JLabel("DATOS DE LA FACTURA");
		lblDatosDeLa.setHorizontalAlignment(SwingConstants.CENTER);
		lblDatosDeLa.setForeground(Color.WHITE);
		lblDatosDeLa.setFont(ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.PLAIN, 20) :new Font("Lucida Grande", Font.BOLD, 26));
		lblDatosDeLa.setBounds(6, 463, 997, 32);
		contentPane.add(lblDatosDeLa);
		
		panel = new Panel();
		panel.setBounds(6, 501, 997, 166);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 4, 0, 0));
		
		
		lblNombreCliente = new JLabel("Nombre");
		lblNombreCliente.setForeground(UIManager.getColor("Button.highlight"));
		lblNombreCliente.setFont(font);
		panel.add(lblNombreCliente);
		
		txtNombreCliente = new JTextField();
		txtNombreCliente.setEditable(false);
		txtNombreCliente.setFont(fontButton);
		txtNombreCliente.setText("Nombre cliente");
		panel.add(txtNombreCliente);
		txtNombreCliente.setColumns(10);
		
		lblFechafactura = new JLabel("Fecha factura");
		lblFechafactura.setForeground(UIManager.getColor("Button.highlight"));
		lblFechafactura.setFont(font);
		panel.add(lblFechafactura);
		
		txtFechafactura = new JTextField();
		txtFechafactura.setEditable(false);
		txtFechafactura.setFont(fontButton);
		txtFechafactura.setText("Fecha factura");
		panel.add(txtFechafactura);
		txtFechafactura.setColumns(10);
		
		lblCifNif = new JLabel("CIF/NIF");
		lblCifNif.setForeground(UIManager.getColor("Button.highlight"));
		lblCifNif.setFont(font);
		panel.add(lblCifNif);
		
		txtCifNif = new JTextField();
		txtCifNif.setEditable(false);
		txtCifNif.setFont(fontButton);
		txtCifNif.setText("CIF/NIF");
		panel.add(txtCifNif);
		txtCifNif.setColumns(10);
		
		lblBaseImponible = new JLabel("Base imponible");
		lblBaseImponible.setForeground(UIManager.getColor("Button.highlight"));
		lblBaseImponible.setFont(font);
		panel.add(lblBaseImponible);
		
		txtBaseImponible = new JTextField();
		txtBaseImponible.setEditable(false);
		txtBaseImponible.setFont(fontButton);
		txtBaseImponible.setText("Base imponible");
		panel.add(txtBaseImponible);
		txtBaseImponible.setColumns(10);
		
		lblTelefonoCliente = new JLabel("Teléfono");
		lblTelefonoCliente.setForeground(UIManager.getColor("Button.highlight"));
		lblTelefonoCliente.setFont(font);
		panel.add(lblTelefonoCliente);
		
		txtTelefonoCliente = new JTextField();
		txtTelefonoCliente.setEditable(false);
		txtTelefonoCliente.setFont(fontButton);
		txtTelefonoCliente.setText("Teléfono cliente");
		panel.add(txtTelefonoCliente);
		txtTelefonoCliente.setColumns(10);
		
		lblTotal = new JLabel("Total");
		lblTotal.setForeground(UIManager.getColor("Button.highlight"));
		lblTotal.setFont(font);
		panel.add(lblTotal);
		
		txtTotal = new JTextField();
		txtTotal.setEditable(false);
		txtTotal.setFont(fontButton);
		txtTotal.setText("Total");
		panel.add(txtTotal);
		txtTotal.setColumns(10);
		
		lblDireccionCliente = new JLabel("Dirección");
		lblDireccionCliente.setForeground(UIManager.getColor("Button.highlight"));
		lblDireccionCliente.setFont(font);
		panel.add(lblDireccionCliente);
		
		txtDireccion = new JTextField();
		txtDireccion.setEditable(false);
		txtDireccion.setFont(fontButton);
		txtDireccion.setText("Dirección");
		panel.add(txtDireccion);
		txtDireccion.setColumns(10);
		
		construirTablaVentas();	
		construirTablaClientes();
		estadoInicial();
	}
	
	private void construirTablaVentas() {
		
		panelTabla = new JPanel();
		panelTabla.setBounds(6, 126, 493, 205);
		panelTabla.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 493, 205);
		
		
		String cabecera[] = {"IdFactura","Fecha","Empleado","Total","Base imponible","IdVenta","idLineaVentaRealizada","formaPago"};	
		String datos[][] = service.rellenarTablaVentas();
		DefaultTableModel model = new DefaultTableModel(datos, cabecera);
				
		table = new JTable(model);
	
		table.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		table.setBounds(277, 60, 426, 138);
		JTableHeader th = table.getTableHeader();
		th.setBackground(Color.GRAY);
		Font fuente = ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.PLAIN, 13) : new Font("Lucida Grande", Font.PLAIN, 17);
		th.setFont(fuente);
		table.setTableHeader(th);
		scrollPane.setViewportView(table);
		
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
		table.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(4).setMaxWidth(0);
		table.getColumnModel().getColumn(4).setMinWidth(0);
		table.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(0);
		table.getTableHeader().getColumnModel().getColumn(4).setMinWidth(0);
		table.getColumnModel().getColumn(5).setMaxWidth(0);
		table.getColumnModel().getColumn(5).setMinWidth(0);
		table.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(0);
		table.getTableHeader().getColumnModel().getColumn(5).setMinWidth(0);
		table.getColumnModel().getColumn(6).setMaxWidth(0);
		table.getColumnModel().getColumn(6).setMinWidth(0);
		table.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(0);
		table.getTableHeader().getColumnModel().getColumn(6).setMinWidth(0);
		table.getColumnModel().getColumn(7).setMaxWidth(0);
		table.getColumnModel().getColumn(7).setMinWidth(0);
		table.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(0);
		table.getTableHeader().getColumnModel().getColumn(7).setMinWidth(0);
		table.addMouseListener(new MouseAdapter() 
		   {
		      public void mouseClicked(MouseEvent e) 
		      {
		    	  filasSeleccionadas = table.getSelectedRow();
		    	  if(filasSeleccionadas != -1){
		    		  tablaSeleccionada = true;
			    	  btnEliminarVenta.setEnabled(true);
			    	  modeloTabla = table;	
			    	  idFactura = (String) modeloTabla.getValueAt(filasSeleccionadas, 0);
		    		  idVenta = (String) modeloTabla.getValueAt(filasSeleccionadas, 5);
		    		  idLineaVentaRealizada = (String) modeloTabla.getValueAt(filasSeleccionadas, 6);
		    		  formaPago = (String) modeloTabla.getValueAt(filasSeleccionadas, 7);
		    	  }
		      }
		   });
		panelTabla.add(scrollPane);
		contentPane.add(panelTabla);		
		
	}
	
	private void construirTablaClientes() {
		panelTablaClientes = new JPanel();
		panelTablaClientes.setLayout(null);
		panelTablaClientes.setBounds(550, 126, 453, 205);
		contentPane.add(panelTablaClientes);
		
		scrollPaneClientes = new JScrollPane();
		scrollPaneClientes.setBounds(0, 0, 453, 205);
		Font fuente = ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.PLAIN, 13) : new Font("Lucida Grande", Font.PLAIN, 17);
		String cabeceraClientes[] = {"IdCliente","Empresa","CIF/NIF","Teléfono","Email","Dirección"};	
		String datosClientes[][] = service.rellenarTablaCliente();
		DefaultTableModel modelCliente = new DefaultTableModel(datosClientes, cabeceraClientes);	
		
		tableClientes = new JTable(modelCliente);
		
		tableClientes.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		tableClientes.setBounds(277, 60, 426, 138);
		JTableHeader thCliente = tableClientes.getTableHeader();
		thCliente.setBackground(Color.GRAY);
		thCliente.setFont(fuente);
		tableClientes.setTableHeader(thCliente);
		scrollPaneClientes.setViewportView(tableClientes);
		
		tableClientes.getColumnModel().getColumn(0).setMaxWidth(0);
		tableClientes.getColumnModel().getColumn(0).setMinWidth(0);
		tableClientes.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
		tableClientes.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
		tableClientes.getColumnModel().getColumn(3).setMaxWidth(0);
		tableClientes.getColumnModel().getColumn(3).setMinWidth(0);
		tableClientes.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(0);
		tableClientes.getTableHeader().getColumnModel().getColumn(3).setMinWidth(0);
		tableClientes.getColumnModel().getColumn(4).setMaxWidth(0);
		tableClientes.getColumnModel().getColumn(4).setMinWidth(0);
		tableClientes.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(0);
		tableClientes.getTableHeader().getColumnModel().getColumn(4).setMinWidth(0);
		
		tableClientes.addMouseListener(new MouseAdapter() 
		   {
		      public void mouseClicked(MouseEvent e) 
		      {
		    	  filasSeleccionadasClientes = tableClientes.getSelectedRow();
		    	  if(filasSeleccionadasClientes != -1){
		    		  tablaSeleccionadaCliente = true;
		    		  modeloTablaCliente = tableClientes;	
		    		  idCliente = (String) modeloTablaCliente.getValueAt(filasSeleccionadasClientes, 0);
		    	  }
		      }
		   });
		panelTablaClientes.add(scrollPaneClientes);
		contentPane.add(panelTablaClientes);
	}
	
	
	private void clearFormVentas() {
		txtFechafactura.setText("");
		txtBaseImponible.setText("");
		txtTotal.setText("");
	}
	private void clearFormClientes() {
		txtTelefonoCliente.setText("");
		txtDireccion.setText("");
		txtCifNif.setText("");
		txtNombreCliente.setText("");
		emailCliente = "";
		dniNifCliente = "";
		telfonoCliente = "";
		direccionCliente = "";
	}
	private void fillFormVentas() {
		empleado = (String) modeloTabla.getValueAt(filasSeleccionadas, 2);
		fechaFactura =  UtilService.obtenerFechaByString((String) modeloTabla.getValueAt(filasSeleccionadas, 1));
		total = new BigDecimal((String)modeloTabla.getValueAt(filasSeleccionadas, 3));
		baseImponible = new BigDecimal((String) modeloTabla.getValueAt(filasSeleccionadas, 4));		
		txtFechafactura.setText(UtilService.obtenerFecha(fechaFactura));
		txtTotal.setText(total.toString());
		txtBaseImponible.setText(baseImponible.toString());
	}
	private void fillFormClientes() {
		emailCliente = (String) modeloTablaCliente.getValueAt(filasSeleccionadasClientes, 4);
		txtNombreCliente.setText((String) modeloTablaCliente.getValueAt(filasSeleccionadasClientes, 1));
		dniNifCliente = (String) modeloTablaCliente.getValueAt(filasSeleccionadasClientes, 2);
		txtCifNif.setText(dniNifCliente);
		telfonoCliente = (String) modeloTablaCliente.getValueAt(filasSeleccionadasClientes, 3);
		direccionCliente = (String) modeloTablaCliente.getValueAt(filasSeleccionadasClientes, 5);
		txtDireccion.setText(direccionCliente);
	}
	private FacturaHecha fillObject(){
		FacturaHecha dev = new FacturaHecha();
		configuracion = gestionService.getConfiguracion();
		if(configuracion != null){
			dev.setBaseImponible(baseImponible);
			dev.setDireccion(configuracion.getDireccionEmpresa() != null ? configuracion.getDireccionEmpresa() : "");
			dev.setDireccionCliente(direccionCliente);
			dev.setDniNif(configuracion.getCifNif() != null ? configuracion.getCifNif() : "");
			dev.setDniNifCliente(dniNifCliente);
			dev.setEmailCliente(emailCliente);
			dev.setEmpleado(empleado);
			dev.setFecha(fechaFactura);
			dev.setNombre(configuracion.getNombreEmpresa() != null ? configuracion.getNombreEmpresa() : "");
			dev.setNombreCliente(txtNombreCliente.getText());
			dev.setNumero(appService.obtenerNumeroFacturaConCliente() != null ? appService.obtenerNumeroFacturaConCliente() + 1 : 1);
			dev.setRazonSocial(configuracion.getRazonSocial() != null ? configuracion.getRazonSocial() : "");
			dev.setTelefonoCliente(telfonoCliente);
			dev.setTotal(total);
			dev.setIdCliente(idCliente != null ? Long.valueOf(idCliente) : null);
			dev.setIdVenta(Long.valueOf(idVenta));
			dev.setIdVentaRealizada(Long.valueOf(idLineaVentaRealizada));
			dev.setFormaPago(Boolean.valueOf(formaPago));
		}
		
		
		return dev;
	}
	
	private void estadoInicial() {
		
		btnAniadirVenta.requestFocus();
		tablaSeleccionada = false;
		tablaSeleccionadaCliente = false;
		
	}	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();
		
		
		if(source.getText().equals(ConstantesUtil.salir)){
//			FormularioPrincipal.btnGestionProd.setEnabled(true);
			dispose();
		}else if(source.getText().equals(ConstantesUtil.nuevoCliente)){
			FormularioCliente formularioCliente = new FormularioCliente(this);
			formularioCliente.setVisible(true);
			formularioCliente.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);		
		}else if(source.getText().equals(ConstantesUtil.aniadirCliente)){
			if(tablaSeleccionadaCliente){
				clearFormClientes();
				fillFormClientes();
				modeloTablaCliente.clearSelection();
				tablaSeleccionadaCliente = false;

			}else{
				JOptionPane.showMessageDialog(null, "Debes seleccionar un cliente para añadirlo !!!");
			}
						
		}else if(source.getText().equals(ConstantesUtil.aniadirVenta)){
			if(tablaSeleccionada){
				clearFormVentas();
				fillFormVentas();
				modeloTabla.clearSelection();
				tablaSeleccionada = false;
			}else{
				JOptionPane.showMessageDialog(null, "Debes seleccionar una venta para añadirla a datos de facturas !!!");
			}
		}else if(source.getText().equals(ConstantesUtil.crearFactura)){	
			if(idCliente != null){
				FacturaHecha facturaHecha = fillObject();			
				if(facturaHecha != null){
	//				imprimo factura
					Factura factura = new Factura(facturaHecha);
					factura.setTxtBaseImponible(facturaHecha.getBaseImponible().toString());
					factura.setTxtDireccionCliente(facturaHecha.getDireccionCliente());
					factura.setTxtDninif(facturaHecha.getDniNif());
					factura.setTxtDniNifCliente(facturaHecha.getDniNifCliente());
					factura.setTxtEmailCliente(facturaHecha.getEmailCliente());
					factura.setTxtEmpleado(facturaHecha.getEmpleado());
					factura.setTxtFechaFactura(UtilService.obtenerFecha(facturaHecha.getFecha()));
					factura.setTxtNFactura(String.valueOf(facturaHecha.getNumero()));
					factura.setTxtNombreCliente(facturaHecha.getNombreCliente());
					factura.setTxtNombreEmpresa(facturaHecha.getNombre());
					factura.setTxtRazonSocial(facturaHecha.getRazonSocial());
					factura.setTxtTelefonoCliente(facturaHecha.getTelefonoCliente());
					factura.setTxtTotal(facturaHecha.getTotal().toString());
					factura.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					factura.setVisible(true);
					service.insertarFacturaHecha(facturaHecha);
					facturaHecha = null;
					clearFormVentas();
					clearFormClientes();
					estadoInicial();
				}	
				
				
			}else{
				JOptionPane.showMessageDialog(null, "Debes seleccionar un cliente para añadir para crear la factura !!!");
			}
		}else if(source.getText().equals(ConstantesUtil.eliminarVenta)){
				clearFormVentas();
				estadoInicial();
		}else if(source.getText().equals(ConstantesUtil.eliminarCliente)){
			clearFormClientes();
			estadoInicial();
		}else if(source.getText().equals(ConstantesUtil.cancelar)){
			clearFormVentas();
			clearFormClientes();
			estadoInicial();
		}
	}
	
	public void actualizarTableClientes(){
		contentPane.remove(panelTablaClientes);
		construirTablaClientes();
	}

}
