package PantallaVentas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import AplicacionGestionTPV.FormularioCategoria;
import AplicacionGestionTPV.FormularioProducto;
import Atxy2k.CustomTextField.RestrictedTextField;
import controladorService.AppService;
import controladorService.AppServiceImpl;
import controladorService.GestionService;
import controladorService.GestionServiceImpl;
import modelo.Categoria;
import modelo.Configuracion;
import modelo.Empleado;
import modelo.Factura;
import modelo.LineaVenta;
import modelo.LineaVentaRealizada;
import modelo.ListaProducto;
import modelo.Producto;
import modelo.ProductoCambiado;
import modelo.ProductosVarios;
import modelo.Venta;
import util.ConstantesUtil;
import util.EstadoEnum;
import util.IVAEnum;
import util.Validador;
/**
 * 
 * @author Two hands Technology
 *
 */
public class PantallaTPV extends JFrame implements ActionListener{

	private JPanel contentPane, laminaFecha,panel,panelLista,panelCalculadora,panelTotal,panelProductos,panelBotonera;
	private JPanel panelBotonesCategoria, panelBotonesProductos, panelCambioCategoria, panelCambioProducto;
	private int filasSeleccionadas, cantidadGridProductos,cantidadGridCategorias;
	private JLabel lblTotal,lblNombreCate,lblEuroCalcu;
	private JTextField tfTotal,tfUnidades, tfTotalCalculadora;
	private RestrictedTextField limiteTf ;
	private AppService appService = new AppServiceImpl();
	private List<Categoria> listaCategorias;
	private List<Producto> listaProductos;
	private List<JPanel> listaPanelesCategoria, listaPanelesProducto;
	private List<Integer> listaPosicionPanelCate, listaPosicionPanelProd;
	private Integer posicionActualCategoria, posicionSiguienteCategoria, posicionAnteriorCategoria;
	private Integer posicionActualProducto, posicionSiguienteProducto, posicionAnteriorProducto;
	private JButton btnUpCategoria, btnDownCategoria, btnUpProductos, btnDownProductos,btnUpUnidades,btnDownUnidades, btnEliminarLinea;
	private JButton btnCobrarTPV, btnCancelar;
	public static JButton btnOpciones;
	private LaminaCategoria laminaCategoria;
	private LaminaProducto laminaProducto;
	private Border border = LineBorder.createBlackLineBorder();
	private String cabecera[] = {"IdLinea","Producto","Precio","Cantidad","Total"};
	private String datos[][];
	private JScrollPane scrollPane;
	private List<String> listaTotales = new ArrayList<>();
	private static DefaultTableModel model;
	private static JTable table;
	private Long idLineaVenta = null;
	private BigDecimal precio, precioBrutoProductoCambiado, precioNetoProductoCambiado;
	private static Boolean lineaSeleccionada = Boolean.FALSE;
	private static Boolean isProductoCambiado = Boolean.FALSE;
	private Panel panelBotonesCalculadora,panelNumeros;
	private JButton btnUno,btnDos,btnTres,btnCero,btnCuatro,btnCinco,btnSeis,btnComa,btnSiete,btnOcho,btnNueve,btnAniadir,btnBorrar;
	private double altoVentana, anchoVentana;
	private GestionService gestionService = new GestionServiceImpl();
	private Configuracion configuracion;
	private ProductoCambiado productoCambiado;
	private Long idProductoCambiado, idLinea;
	private LineaVentaEdit lineaVentaEdit;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaTPV frame = new PantallaTPV();
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
	public PantallaTPV() {
		setBackground(UIManager.getColor("Desktop.background"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		altoVentana = 900 ;
//		anchoVentana = 1440;
		altoVentana = ConstantesUtil.alto;
		anchoVentana = ConstantesUtil.ancho;
		setSize((int)anchoVentana, (int)altoVentana);
		setResizable(false);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 102, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JPanel panelEmpleado = new JPanel();
		panelEmpleado.setBorder(border);
		panelEmpleado.setBackground(Color.WHITE);
//		panelEmpleado.setBounds(18, 13, 560, 139);
		panelEmpleado.setBounds((int)(anchoVentana*0.012), (int)(altoVentana*0.014), (int)(anchoVentana*0.388), (int)(altoVentana*0.154));
		contentPane.add(panelEmpleado);
		panelEmpleado.setLayout(null);
		
		laminaFecha = new LaminaFecha(anchoVentana, altoVentana);
		laminaFecha.setBackground(Color.LIGHT_GRAY);
//		laminaFecha.setBounds(153, 6, 258, 127);
		laminaFecha.setBounds((int)(anchoVentana*0.106), (int)(altoVentana*0.0066), (int)(anchoVentana*0.179), (int)(altoVentana*0.141));
		panelEmpleado.add(laminaFecha);
		
		construirLaminaCategoria();
				
		panel = new JPanel();
		panel.setBorder(border);
//		panel.setBounds(18, 152, 560, 459);
		panel.setBounds((int)(anchoVentana*0.012), (int)(altoVentana*0.168), (int)(anchoVentana*0.388), (int)(altoVentana*0.51));
		contentPane.add(panel);
		panel.setLayout(null);
		
		creartablaListaVenta();
		
		panelTotal = new JPanel();
		panelTotal.setBorder(border);
//		panelTotal.setBounds(0, 398, 560, 60);
		panelTotal.setBounds(0, (int)(altoVentana*0.442), (int)(anchoVentana*0.388), (int)(altoVentana*0.068));
		panel.add(panelTotal);
		panelTotal.setLayout(null);
		
		lblTotal = new JLabel(ConstantesUtil.total);
		lblTotal.setForeground(Color.BLACK);
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setFont(ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 17) : new Font("Lucida Grande", Font.BOLD, 22));
//		lblTotal.setBounds(183, 5, 126, 49);
		lblTotal.setBounds((int)(anchoVentana*0.127), (int)(altoVentana*0.0055), (int)(anchoVentana*0.0875), (int)(altoVentana*0.0544));
		panelTotal.add(lblTotal);
		
		tfTotal = new JTextField();
		tfTotal.setEditable(false);
		tfTotal.setFont(ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 19) : new Font("Lucida Grande", Font.BOLD, 24));
		tfTotal.setHorizontalAlignment(SwingConstants.RIGHT);
//		tfTotal.setBounds(310, 0, 172, 60);
		tfTotal.setBounds((int)(anchoVentana*0.215), 0, (int)(anchoVentana*0.119), (int)(altoVentana*0.0666));
		panelTotal.add(tfTotal);
		tfTotal.setColumns(10);
		
		JLabel lblSimbolo = new JLabel("€");
		lblSimbolo.setFont(ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 22) : new Font("Lucida Grande", Font.PLAIN, 38));
//		lblSimbolo.setBounds(482, 5, 72, 49);
		lblSimbolo.setBounds((int)(anchoVentana*0.334), (int)(altoVentana*0.0055), (int)(anchoVentana*0.05), (int)(altoVentana*0.0544));
		panelTotal.add(lblSimbolo);
		
		panelCalculadora = new JPanel();
		panelCalculadora.setBackground(new Color(66, 61, 61));
		panelCalculadora.setLayout(null);
		panelCalculadora.setBorder(border);		
//		panelCalculadora.setBounds(18, 610, 560, 259);
		panelCalculadora.setBounds((int)(anchoVentana*0.012), (int)(altoVentana*0.677), (int)(anchoVentana*0.388), (int)(altoVentana*0.287));
		contentPane.add(panelCalculadora);
		
		panelNumeros = new Panel();
//		panelNumeros.setBounds(10, 6, 353, 242);
		panelNumeros.setBounds((int)(anchoVentana*0.0069), (int)(altoVentana*0.0066), (int)(anchoVentana*0.245), (int)(altoVentana*0.268));
		panelCalculadora.add(panelNumeros);
		panelNumeros.setLayout(new GridLayout(0, 4, 0, 0));
		
		btnUno = new JButton("1");
		btnUno.addActionListener(this);
		btnUno.setFont(ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 26) : new Font("Lucida Grande", Font.BOLD, 40));
		panelNumeros.add(btnUno);
		
		btnDos = new JButton("2");
		btnDos.addActionListener(this);
		btnDos.setFont(ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 26) : new Font("Lucida Grande", Font.BOLD, 40));
		panelNumeros.add(btnDos);
		
		btnTres = new JButton("3");
		btnTres.addActionListener(this);
		btnTres.setFont(ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 26) : new Font("Lucida Grande", Font.BOLD, 40));
		panelNumeros.add(btnTres);
		
		btnCero = new JButton("0");
		btnCero.addActionListener(this);
		btnCero.setFont(ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 26) : new Font("Lucida Grande", Font.BOLD, 40));
		panelNumeros.add(btnCero);
		
		btnCuatro = new JButton("4");
		btnCuatro.addActionListener(this);
		btnCuatro.setFont(ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 26) : new Font("Lucida Grande", Font.BOLD, 40));
		panelNumeros.add(btnCuatro);
		
		btnCinco = new JButton("5");
		btnCinco.addActionListener(this);
		btnCinco.setFont(ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 26) : new Font("Lucida Grande", Font.BOLD, 40));
		panelNumeros.add(btnCinco);
		
		btnSeis = new JButton("6");
		btnSeis.addActionListener(this);
		btnSeis.setFont(ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 26) : new Font("Lucida Grande", Font.BOLD, 40));
		panelNumeros.add(btnSeis);
		
		btnComa = new JButton(".");
		btnComa.addActionListener(this);
		btnComa.setFont(ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 26) : new Font("Lucida Grande", Font.BOLD, 40));
		panelNumeros.add(btnComa);
		
		btnSiete = new JButton("7");
		btnSiete.addActionListener(this);
		btnSiete.setFont(ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 26) : new Font("Lucida Grande", Font.BOLD, 40));
		panelNumeros.add(btnSiete);
		
		btnOcho = new JButton("8");
		btnOcho.addActionListener(this);
		btnOcho.setFont(ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 26) : new Font("Lucida Grande", Font.BOLD, 40));
		panelNumeros.add(btnOcho);
		
		btnNueve = new JButton("9");
		btnNueve.addActionListener(this);
		btnNueve.setFont(ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 26) : new Font("Lucida Grande", Font.BOLD, 40));
		panelNumeros.add(btnNueve);
		
		panelBotonesCalculadora = new Panel();
//		panelBotonesCalculadora.setBounds(369, 6, 184, 242);
		panelBotonesCalculadora.setBounds((int)(anchoVentana*0.256), (int)(altoVentana*0.0066), (int)(anchoVentana*0.127), (int)(altoVentana*0.268));
//		(int)(anchoVentana*0.0069), (int)(altoVentana*0.0066)
		panelCalculadora.add(panelBotonesCalculadora);
		panelBotonesCalculadora.setLayout(null);
		
		btnBorrar = new JButton("BORRAR");
		btnBorrar.addActionListener(this);
		btnBorrar.setFont(ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 20) : new Font("Lucida Grande", Font.BOLD, 25));
//		btnBorrar.setBounds(6, 6, 172, 68);
//		btnBorrar.setBounds((int)(anchoVentana*0.0041), (int)(altoVentana*0.0066), (int)(anchoVentana*0.119), (int)(altoVentana*0.075));
		btnBorrar.setBounds((int)(anchoVentana*0.0041), (int)(altoVentana*0.197), (int)(anchoVentana*0.119), (int)(altoVentana*0.075));
		panelBotonesCalculadora.add(btnBorrar);
		
		btnAniadir = new JButton("AÑADIR");
		btnAniadir.addActionListener(this);
		btnAniadir.setFont(ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 20) : new Font("Lucida Grande", Font.BOLD, 25));
//		btnAniadir.setBounds(6, 86, 172, 68);
		btnAniadir.setBounds((int)(anchoVentana*0.0041), (int)(altoVentana*0.095), (int)(anchoVentana*0.119), (int)(altoVentana*0.075));
		panelBotonesCalculadora.add(btnAniadir);
		
		tfTotalCalculadora = new JTextField(10);
		
		
		tfTotalCalculadora.setEditable(false);
		tfTotalCalculadora.setHorizontalAlignment(SwingConstants.RIGHT);
		tfTotalCalculadora.setFont(ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 17) : new Font("Lucida Grande", Font.BOLD, 24));
		tfTotalCalculadora.setText("0.00");
		tfTotalCalculadora.setColumns(5);
		
//		tfTotalCalculadora.setBounds(6, 178, 142, 57);
//		tfTotalCalculadora.setBounds((int)(anchoVentana*0.0041), (int)(altoVentana*0.197), (int)(anchoVentana*0.098), (int)(altoVentana*0.063));
		tfTotalCalculadora.setBounds((int)(anchoVentana*0.0041), (int)(altoVentana*0.0066), (int)(anchoVentana*0.098), (int)(altoVentana*0.063));
		limiteTf = new RestrictedTextField(tfTotalCalculadora);
		limiteTf.setLimit(5);
//		limiteTf.setOnlyNums(true);
		panelBotonesCalculadora.add(tfTotalCalculadora);
		
		lblEuroCalcu = new JLabel("€");
		lblEuroCalcu.setHorizontalAlignment(SwingConstants.LEFT);
		lblEuroCalcu.setFont(ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 26) : new Font("Lucida Grande", Font.BOLD, 26));
//		lblEuroCalcu.setBounds(150, 166, 28, 70);
//		lblEuroCalcu.setBounds((int)(anchoVentana*0.104), (int)(altoVentana*0.184), (int)(anchoVentana*0.019), (int)(altoVentana*0.077));
		lblEuroCalcu.setBounds((int)(anchoVentana*0.104), (int)(altoVentana*0.0006), (int)(anchoVentana*0.019), (int)(altoVentana*0.077));
		panelBotonesCalculadora.add(lblEuroCalcu);
		
		
		construirLaminaProducto();
		
		panelBotonera = new JPanel();
		Border border = LineBorder.createBlackLineBorder();
		panelBotonera.setBorder(border);
//		panelBotonera.setBorder(new LineBorder(new Color(0, 0, 0), 0));
//		panelBotonera.setBounds(578, 610, 843, 258);
		panelBotonera.setBounds((int)(anchoVentana*0.401), (int)(altoVentana*0.677), (int)(anchoVentana*0.585), (int)(altoVentana*0.286));
		contentPane.add(panelBotonera);
		panelBotonera.setLayout(null);
		
		JPanel panelContieneUnidades = new JPanel();
		panelContieneUnidades.setBorder(border);
//		panelContieneUnidades.setBounds(0, 1, 296, 258);
		panelContieneUnidades.setBounds(0, 0, (int)(anchoVentana*0.205), (int)(altoVentana*0.286));
		panelBotonera.add(panelContieneUnidades);
		panelContieneUnidades.setLayout(null);
		
		JLabel lblUnidades = new JLabel("Unidades");
		lblUnidades.setHorizontalAlignment(SwingConstants.CENTER);
		lblUnidades.setForeground(Color.BLACK);
		lblUnidades.setFont(ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 15) : new Font("Lucida Grande", Font.BOLD, 22));
//		lblUnidades.setBounds(31, 48, 126, 49);
		lblUnidades.setBounds((int)(anchoVentana*0.0215), (int)(altoVentana*0.0533), (int)(anchoVentana*0.0875), (int)(altoVentana*0.054));
		panelContieneUnidades.add(lblUnidades);
		
		tfUnidades = new JTextField();
		tfUnidades.setText("0");
		tfUnidades.setHorizontalAlignment(SwingConstants.CENTER);
		tfUnidades.setFont(ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 17) : new Font("Lucida Grande", Font.BOLD, 24));
		tfUnidades.setEnabled(false);
		tfUnidades.setColumns(10);
//		tfUnidades.setBounds(160, 42, 106, 60);
		tfUnidades.setBounds((int)(anchoVentana*0.111), (int)(altoVentana*0.0466), (int)(anchoVentana*0.0736), (int)(altoVentana*0.0666));
		panelContieneUnidades.add(tfUnidades);
		
		btnUpUnidades = new JButton("+");
		btnUpUnidades.addActionListener(this);
		btnUpUnidades.setFont(ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 30) : new Font("Lucida Grande", Font.BOLD, 40));
//		btnUpUnidades.setBounds(31, 126, 117, 72);
		btnUpUnidades.setBounds((int)(anchoVentana*0.0215), (int)(altoVentana*0.14), (int)(anchoVentana*0.0812), (int)(altoVentana*0.08));
		panelContieneUnidades.add(btnUpUnidades);
		
		btnDownUnidades = new JButton("-");
		btnDownUnidades.addActionListener(this);
		btnDownUnidades.setFont(ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 30) : new Font("Lucida Grande", Font.BOLD, 40));
//		btnDownUnidades.setBounds(152, 126, 114, 72);
		btnDownUnidades.setBounds((int)(anchoVentana*0.1041), (int)(altoVentana*0.14), (int)(anchoVentana*0.0791), (int)(altoVentana*0.08));
		panelContieneUnidades.add(btnDownUnidades);
		
		JPanel panelContieneBotones = new JPanel();
		panelContieneBotones.setBorder(border);
		panelContieneBotones.setBounds((int)(anchoVentana*0.205), 0, (int)(anchoVentana*0.380), (int)(altoVentana*0.286));
		panelBotonera.add(panelContieneBotones);
		panelContieneBotones.setLayout(null);
		
		JPanel panelBotones = new JPanel();
//		panelBotones.setBounds(548/2-402/2, 258/2-196/2, 402, 196);
		panelBotones.setBounds((int)(anchoVentana*0.0506), (int)(altoVentana*0.0344), (int)(anchoVentana*0.2791), (int)(altoVentana*0.2177));
		panelContieneBotones.add(panelBotones);
		panelBotones.setLayout(new GridLayout(2, 2, 5, 5));
		
		
		btnCobrarTPV = new JButton(ConstantesUtil.cobrar);
		btnCobrarTPV.setIcon(new ImageIcon(PantallaTPV.class.getResource(ConstantesUtil.pathCobrar)));
		btnCobrarTPV.addActionListener(this);
		btnCobrarTPV.setFont(ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 14) : new Font("Lucida Grande", Font.BOLD, 22));
		panelBotones.add(btnCobrarTPV);
		
		btnEliminarLinea = new JButton(ConstantesUtil.eliminarLinea);
		btnEliminarLinea.addActionListener(this);
		btnEliminarLinea.setFont(ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 14) : new Font("Lucida Grande", Font.BOLD, 22));
		panelBotones.add(btnEliminarLinea);
		
		btnOpciones = new JButton("OPCIONES");
		btnOpciones.addActionListener(this);
		btnOpciones.setIcon(new ImageIcon(PantallaTPV.class.getResource(ConstantesUtil.pathOpciones)));
		btnOpciones.setFont(ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 14) : new Font("Lucida Grande", Font.BOLD, 22));
		panelBotones.add(btnOpciones);
		
		btnCancelar = new JButton("CANCELAR");
		btnCancelar.addActionListener(this);
		btnCancelar.setIcon(new ImageIcon(PantallaTPV.class.getResource(ConstantesUtil.pathCancelar)));
		btnCancelar.setFont(ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 14) : new Font("Lucida Grande", Font.BOLD, 22));
		panelBotones.add(btnCancelar);

		crearListaCategorias();
		iniciarDatos();
	} 

	private void iniciarDatos(){
		tfTotal.setText("0.00");
		tfTotalCalculadora.setText("0.00");
		tfUnidades.setText("0");
//		btnCobrarTPV.setEnabled(true);
	}
	private void limpiarSeleccion(){
		lineaSeleccionada = false;
		table.clearSelection();
		tfUnidades.setText("0");
		tfTotalCalculadora.setText("0.00");
	}
	private void creartablaListaVenta(){
		
		panelLista = new JPanel();
		panelLista.setBorder(border);
		panelLista.setBackground(Color.WHITE);
//		panelLista.setBounds(0, 0, 560, 399);
		panelLista.setBounds(0, 0, (int)(anchoVentana*0.3888), (int)(altoVentana*0.4433));
		panelLista.setLayout(null);
		
		scrollPane = new JScrollPane();
//		scrollPane.setBounds(0, 0, 560, 399);		
		scrollPane.setBounds(0, 0, (int)(anchoVentana*0.3888), (int)(altoVentana*0.4433));
		datos = appService.rellenaTablaVenta();	
		
		model = new DefaultTableModel(datos, cabecera);		
		
		table = new JTable(model);
		table = new JTable(model){
			public boolean isCellEditable(int rowIndex, int colIndex){
				return false;
			}
		};
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
		table.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
		table.setFont(ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.PLAIN, 14) : new Font("Lucida Grande", Font.PLAIN, 20));
//		table.setBounds(0, 0, 560, 340);
		table.setRowHeight(25);
		table.setBounds(0, 0, (int)(anchoVentana*0.3888), (int)(altoVentana*0.3777));
		JTableHeader th = table.getTableHeader();
		th.setBackground(Color.LIGHT_GRAY);
		Font fuente = ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 16) : new Font("Lucida Grande", Font.PLAIN, 30);
		th.setFont(fuente);
		th.setForeground(UIManager.getColor("Button.highlight"));
		table.setTableHeader(th);
		
		scrollPane.setViewportView(table);
		panelLista.add(scrollPane);
		table.addMouseListener(new MouseAdapter() 
		   {
		      public void mouseClicked(MouseEvent e) 
		      {
		    	  lineaSeleccionada = true;
		    	  filasSeleccionadas = table.getSelectedRow();
		    	  if(filasSeleccionadas != -1 && e.getClickCount() == 1){
		    		 	String cantidad = (String)String.valueOf(table.getValueAt(filasSeleccionadas, 3));
		    		 	tfUnidades.setText(cantidad);
		    		 	idLineaVenta = Long.valueOf(String.valueOf(table.getValueAt(filasSeleccionadas, 0)));
		    		 	precio = new BigDecimal((String)table.getValueAt(filasSeleccionadas, 2));
		    		 	
		    	  }else if(filasSeleccionadas != -1 && e.getClickCount() == 2){
		    		  	String cantidad = (String)String.valueOf(table.getValueAt(filasSeleccionadas, 3));
		    		  	String nombreP = (String)String.valueOf(table.getValueAt(filasSeleccionadas, 1));
		    		 	idLinea = Long.valueOf(String.valueOf(table.getValueAt(filasSeleccionadas, 0)));
		    		 	BigDecimal precioP = new BigDecimal((String)table.getValueAt(filasSeleccionadas, 2));
		    		 	
		    		 	lineaVentaEdit = new LineaVentaEdit();
		    		 	lineaVentaEdit.setTxtCatidadPCambiado(cantidad);
		    		 	lineaVentaEdit.setTxtNombrePCambiado(nombreP);
		    		 	lineaVentaEdit.setTxtPrecioPCambiado(precioP.toString());
		    		 	isProductoCambiado = Boolean.TRUE;	
		    		 	lineaVentaEdit.setProductoCambiado(isProductoCambiado);
		    		 	isProductoCambiado = Boolean.FALSE;		    		 		    		 	
		    		 	lineaVentaEdit.setVisible(true); 		    		 	
		    	  }
		      }
			
		   });
		
		panel.add(panelLista);		
	}
	/**
	 * Crea un linea de producto cambiado en la bbdd, cuando el usuario hace doble click sobre la linea de pedido y modifica el importe del
	 * producto se crea está linea.
	 * @param idProductoCambiado
	 * @param cantidad
	 */
	private void crearLineaByIdProductoCambiado(String idProductoCambiado, BigDecimal cantidad){
		crearLineaVenta(idProductoCambiado, true, cantidad);
		panel.remove(panelLista);
		creartablaListaVenta();	
		calcularTotal();
		limpiarSeleccion();
	}
	/**
	 * Elimina la linea de pedido por el idLinea.
	 * @param idLinea
	 */
	private void eliminarLineaById(Long idLinea) {
		model.removeRow(filasSeleccionadas);
		appService.eliminarLineaById(idLineaVenta);
		tfUnidades.setText("0");
		calcularTotal();
		limpiarSeleccion();
	}
	/**
	 * Calcula el total de las lineas de pedido del table.
	 */
	public void calcularTotal() {
		String dev = null;
		BigDecimal totalTotal = BigDecimal.ZERO;
		
		listaTotales.clear();
	  	for(int i=0; i < table.getRowCount(); i++){
	  		  listaTotales.add((String) table.getValueAt(i, 4));
	  	  }	  	
		
		for (String total : listaTotales) {
			totalTotal = totalTotal.add(new BigDecimal(total));
		}
		dev = totalTotal.toString();
		
		tfTotal.setText(!dev.equals("0") ? dev : "0.00");	
		
	}
	/**
	 * Crea panel de botones donde se muestran los botones en el TPV
	 */
	private void crearPanelBotonesProducto(){
		
		panelBotonesProductos = new JPanel();
//		panelBotonesProductos.setBounds(149, 6, 688, 349);
		panelBotonesProductos.setBounds((int)(anchoVentana*0.1034),  (int)(altoVentana*0.0066), (int)(anchoVentana*0.4777), (int)(altoVentana*0.3877));
		panelBotonesProductos.setLayout(null);
		
	}
	/**
	 * Crea la lamina de productos que es donde va el panel de botones de productos y los 
	 * botones de subir y bajar el grid de botones
	 */
	private void construirLaminaProducto(){
		laminaProducto = new LaminaProducto(anchoVentana,altoVentana);
		laminaProducto.setLocation((int)(anchoVentana*0.4013), (int)(altoVentana*0.2777));
		
		crearPanelBotonesProducto();
		
		panelCambioProducto = new JPanel();
//		panelCambioProducto.setBounds(6, 361/2 - 261/2, 137, 261);
		panelCambioProducto.setBounds((int)(anchoVentana*0.0041), (int)(altoVentana*0.0555), (int)(anchoVentana*0.0951), (int)(altoVentana*0.29));
		panelCambioProducto.setLayout(new GridLayout(2, 1, 0, 15));
		
		btnUpProductos = new JButton("");
		btnUpProductos.setName(ConstantesUtil.upP);
		btnUpProductos.addActionListener(this);
		btnUpProductos.setIcon(new ImageIcon(PantallaTPV.class.getResource(ConstantesUtil.pathFlechaArriba)));
		panelCambioProducto.add(btnUpProductos);
		
		btnDownProductos = new JButton("");
		btnDownProductos.setName(ConstantesUtil.downP);
		btnDownProductos.addActionListener(this);
		btnDownProductos.setIcon(new ImageIcon(PantallaTPV.class.getResource(ConstantesUtil.pathFlechaAbajo)));
		panelCambioProducto.add(btnDownProductos);
		laminaProducto.add(panelCambioProducto);
		laminaProducto.add(panelBotonesProductos);
		contentPane.add(laminaProducto);
	}
	/**
	 * Crea la lamina de categoría donde van el panel de categoría y los botones de subir y bajar para el 
	 * grid de categoría.
	 */
	private void construirLaminaCategoria() {
		
		laminaCategoria = new LaminaCategoria(anchoVentana,altoVentana);
//		laminaCategoria.setLocation(578, 13);
		laminaCategoria.setLocation((int)(anchoVentana*0.401), (int)(altoVentana*0.014));
		
		panelBotonesCategoria = new JPanel();
//		panelBotonesCategoria.setBounds(149, 49, 688, 182);
		panelBotonesCategoria.setBounds((int)(anchoVentana*0.103), (int)(altoVentana*0.0544), (int)(anchoVentana*0.477), (int)(altoVentana*0.202));
		
		panelBotonesCategoria.setLayout(null);
		panelCambioCategoria = new JPanel();
//		panelCambioCategoria.setBounds(6, 51, 139, 180);
		panelCambioCategoria.setBounds((int)(anchoVentana*0.0041), (int)(altoVentana*0.0566), (int)(anchoVentana*0.096), (int)(altoVentana*0.2));
		
		panelCambioCategoria.setLayout(new GridLayout(2, 0, 15, 15));
		
		btnUpCategoria = new JButton("");
		btnUpCategoria.setName(ConstantesUtil.upC);
		btnUpCategoria.addActionListener(this);
		btnUpCategoria.setIcon(new ImageIcon(PantallaTPV.class.getResource(ConstantesUtil.pathFlechaArriba)));
		panelCambioCategoria.add(btnUpCategoria);
		
		btnDownCategoria = new JButton("");
		btnDownCategoria.setName(ConstantesUtil.downC);
		btnDownCategoria.addActionListener(this);
		btnDownCategoria.setIcon(new ImageIcon(PantallaTPV.class.getResource(ConstantesUtil.pathFlechaAbajo)));
		panelCambioCategoria.add(btnDownCategoria);
		
		laminaCategoria.add(panelCambioCategoria);
		laminaCategoria.add(panelBotonesCategoria);
		contentPane.add(laminaCategoria);
	}
	/**
	 * Crea un lista de categorias para el grid
	 */
	private void crearListaCategorias(){
		listaCategorias = appService.obtenerCategorias();
		List<JButton> listaBotones = new ArrayList<>();
		Font font = new Font("Lucida Grande", Font.PLAIN, 18);
		for (Categoria categoria : listaCategorias) {
			JButton b;
			if(categoria.getImagen() != null ){
				File fichero = new File(categoria.getImagen());
				ImageIcon icon = new ImageIcon(fichero.toString());
				Image conversion = icon.getImage();
				Image tam = conversion.getScaledInstance(146, 50, Image.SCALE_SMOOTH);
				ImageIcon ico = new ImageIcon(tam);
				Icon icono = new ImageIcon(icon.getImage());
				b = new JButton(categoria.getNombre(),ico);
				b.setVerticalTextPosition(SwingConstants.VERTICAL);
	  	  		b.setHorizontalTextPosition(SwingConstants.CENTER);
			}else{
				b = new JButton(categoria.getNombre());
			}
			b.setName(ConstantesUtil.c + "-"+ String.valueOf(categoria.getIdCategoria()));
			b.setFont(font);
			b.addActionListener(this);
			listaBotones.add(b);
		}	
		crearGridLayoutCategoria(listaBotones);		
	}
	/**
	 * Crea un grid de categorías si no llega a la cantidad de 8 categorías le añade el texto sin categoría
	 * @param lista
	 */
	public void crearGridLayoutCategoria(List<JButton> lista){
		int tamanio = lista.size();
		Font font = new Font("Lucida Grande", Font.PLAIN, 18);
		cantidadGridCategorias = lista.size() != 0 ? lista.size()/8 : 1;

		int ultimooGrid = lista.size() != 0 ?  lista.size()%8 != 0 ? 1 : 0 : 0;
		int centinela,r,resto;
		listaPanelesCategoria = new ArrayList<>();
		cantidadGridCategorias +=ultimooGrid;
		JPanel panel;
		

		if(!lista.isEmpty()){ // Si la lista de categorias no esta vacia	
			for(int i=0; i < cantidadGridCategorias; i++){
				panel = new JPanel();
				panel.setLayout(new GridLayout(2, 4,10,10));
				centinela = i;
				if(cantidadGridCategorias == 1){
					r = tamanio;
					resto = 0;
					for(int j=0; j<r; j++){
						panel.add(lista.get(j));
						resto = j;
					}
					for(int k=resto; k < 7; k++){
						JButton aux = new JButton("Sin categoría");
						aux.setFont(font);
						aux.setEnabled(false);
						panel.add(aux);
					}
					
				}else{
					if(i == cantidadGridCategorias -1){
						r = tamanio%8;
						resto = 0;
						if(r != 0){  // Este es para cuando son menos de 8 botones y es el ultimo panel.
							for(int j=0; j<r; j++){
								panel.add(lista.get(centinela*8 + j));
								resto = j;
							}
							if(resto < 7){ // Esto es para rellenar los huecos que quedan de botones sin nada para que conserven los ultimos botones el tamaño.
								for(int k=resto; k < 7; k++){
									JButton aux = new JButton("Sin categoría");
									aux.setFont(font);
									aux.setEnabled(false);
									panel.add(aux);
								}
							}					
						}else{ // Este es para cuanto hay 8 botones de categorias justo
							for(int j=0; j<8; j++){
								panel.add(lista.get(centinela*8 + j));
							}
						}				
					}else{
						if(i == 0){  // Este es para la primera vuelta del bucle
							for(int j=0; j<8; j++){
								panel.add(lista.get(j));
							}
						}else{ // Este es para las vueltas siguientes que no son el ultimo panel.
							for(int j=0; j<8; j++){
								panel.add(lista.get(centinela*8 + j));
							}
						}
						
					}
					
				}
				panel.setSize(panelBotonesCategoria.getWidth(), panelBotonesCategoria.getHeight());
				listaPanelesCategoria.add(panel);				
			}
		}else{  // Si la lista de botones de categoria esta vacia.
			panel = new JPanel();
			panel.setLayout(new GridLayout(2, 4,10,10));
			for(int j=0; j<8; j++){
				JButton aux = new JButton("No tiene producto");
				aux.setFont(font);
				aux.setEnabled(false);
				panel.add(aux);
			}
			panel.setSize(panelBotonesCategoria.getWidth(), panelBotonesCategoria.getHeight());
			listaPanelesCategoria.add(panel);			
		}
		
		listaPosicionPanelCate = new ArrayList<>();		
		for (int i=0; i<listaPanelesCategoria.size(); i++) {
			listaPosicionPanelCate.add(i);
		}
		posicionActualCategoria = new Integer(0);
		if(listaPanelesCategoria.size() != 1){			
			posicionSiguienteCategoria = new Integer(1);
			posicionAnteriorCategoria = null;
		}
		// Añado las primeras categorias al tpv
		panelBotonesCategoria.add(listaPanelesCategoria.get(posicionActualCategoria));
	}
	/**
	 * Crea un grid de botones de productos si no llega a la cantidad de 12 botones les añade el texto sin producto
	 * @param listaBotones
	 */
	private void crearGridLayoutProductos(List<JButton> listaBotones) {
		int tamanio = listaBotones.size();
		cantidadGridProductos = listaBotones.size() != 0 ? listaBotones.size()/12 : 1;
		int ultimooGrid = listaBotones.size() != 0 ? listaBotones.size()%12 != 0 ? 1 : 0 : 0;
		int centinela;
		listaPanelesProducto = new ArrayList<>();
		Font font = new Font("Lucida Grande", Font.PLAIN, 18);
	
		cantidadGridProductos +=ultimooGrid;
		JPanel panel;
		
		if(!listaBotones.isEmpty()){ // Si la lista de de botones esta vacia
			for(int i=0; i < cantidadGridProductos; i++){
				panel = new JPanel();
				panel.setLayout(new GridLayout(3, 4,10,10));
				centinela = i;
				if(cantidadGridProductos == 1){
					int r = tamanio;
					int resto = 0;
					for(int j=0; j<r; j++){
						panel.add(listaBotones.get(j));
						resto = j;
					}
					for(int k=resto; k < 11; k++){
						JButton aux = new JButton("Sin Producto");
						aux.setFont(font);
						aux.setEnabled(false);
						panel.add(aux);
					}
					
				}else{					
				
					if(i == cantidadGridProductos -1){
						int r = listaBotones.size()%12;
						int resto = 0;
						if(r != 0){  // Este es para cuando son menos de 12 botones y es el ultimo panel.
							for(int j=0; j<r; j++){
								panel.add(listaBotones.get(centinela*12 + j));
								resto = j;
							}
							if(resto < 11){ // Esto es para rellenar los huecos que quedan de botones sin nada para que conserven los ultimos botones el tamaño.
								for(int k=resto; k < 11; k++){
									JButton aux = new JButton("Sin Producto");
									aux.setFont(font);
									aux.setEnabled(false);
									panel.add(aux);
								}
							}					
						}else{ // Este es para cuanto hay 8 botones de categorias justo
							for(int j=0; j<12; j++){
								panel.add(listaBotones.get(centinela*12 + j));
							}
						}				
					}else{
						if(i == 0){  // Este es para la primera vuelta del bucle
							for(int j=0; j<12; j++){
								panel.add(listaBotones.get(j));
							}
						}else{ // Este es para las vueltas siguientes que no son el ultimo panel.
							for(int j=0; j<12; j++){
								panel.add(listaBotones.get(centinela*12 + j));
							}
						}
						
					}
				}
				panel.setSize(panelBotonesProductos.getWidth(), panelBotonesProductos.getHeight());
				listaPanelesProducto.add(panel);
				
			}
		}else{  // Si la lista de botones esta vacia.
			panel = new JPanel();
			panel.setLayout(new GridLayout(3, 4,10,10));
			for(int j=0; j<12; j++){
				JButton aux = new JButton("No tiene producto");
				aux.setFont(font);
				aux.setEnabled(false);
				panel.add(aux);
			}
			panel.setSize(panelBotonesProductos.getWidth(), panelBotonesProductos.getHeight());
			listaPanelesProducto.add(panel);
			
		}
		listaPosicionPanelProd = new ArrayList<>();
		for (int i=0; i<listaPanelesProducto.size(); i++) {
			listaPosicionPanelProd.add(i);
		}
		posicionActualProducto = new Integer(0);
		if(listaPanelesProducto.size() != 1){			
			posicionSiguienteProducto = new Integer(1);
			posicionAnteriorProducto = null;
		}
		// Añado los primeras productos al tpv
		laminaProducto.remove(panelBotonesProductos);
		crearPanelBotonesProducto();
		laminaProducto.add(panelBotonesProductos);
		panelBotonesProductos.add(listaPanelesProducto.get(posicionActualProducto));
		
	}
	/**
	 * Crea una lista de productos 
	 * @param id
	 */
	private void crearListaProductos(String id){ 
		listaProductos = appService.obtenerProductos(id);
		List<JButton> listaBotones = new ArrayList<>();
		Font font = new Font("Lucida Grande", Font.PLAIN, 18);
		
		for (Producto producto : listaProductos) {
			JButton b;
			if(producto.getImagen() != null ){
				File fichero = new File(producto.getImagen());
				ImageIcon icon = new ImageIcon(fichero.toString());
	  	  		Image conversion = icon.getImage();
	  	  		Image tam = conversion.getScaledInstance(146, 70, Image.SCALE_SMOOTH);
	  	  		ImageIcon ico = new ImageIcon(tam);
	  	  		b = new JButton(producto.getNombre(),ico);
	  	  		b.setVerticalTextPosition(SwingConstants.VERTICAL);
	  	  		b.setHorizontalTextPosition(SwingConstants.CENTER);
			}else{
				b = new JButton(producto.getNombre());
			}
			b.setName(ConstantesUtil.p + "-" + String.valueOf(producto.getIdProducto()));
			b.setFont(font);
			b.addActionListener(this);
			listaBotones.add(b);
		}	
		
		crearGridLayoutProductos(listaBotones);		
	}
	
	
	/**
	 * Captura los eventos principales de los botones del TPV como categoría, productos, eliminar linea pedido, cancelar venta, etc...
	 */
	@Override
	public void actionPerformed (ActionEvent e){
		JButton source = (JButton) e.getSource();		
		String [] botonProCate;
		String botonPulsado = null;
		String id = null;
		// Capturo si es un boton de categoria o no.
		if(source.getName() != null && source.getName().contains("-") ){
			botonProCate = source.getName().split("-");
			botonPulsado = botonProCate[0];
			id = botonProCate[1];
		}
		if(source.getText().equals("BORRAR")){
			tfTotalCalculadora.setText("0.00");
		}else if(source.getText().equals("AÑADIR")){
			if(tfTotalCalculadora != null && !tfTotalCalculadora.getText().equals("0.00") && tfTotalCalculadora.getText().length() <= 10){
					panel.remove(panelLista);					
					BigDecimal precio = new BigDecimal(ConstantesUtil.obtenerNumeroSinComa(tfTotalCalculadora.getText())).setScale(2, RoundingMode.UP);
					model.addRow(new Object[]{null, ConstantesUtil.varios,precio.toString(),1,precio.toString()});
					table.setModel(model);
					Long idLinea = crearLineaVenta(precio);	
					creartablaListaVenta();	
					calcularTotal();
					limpiarSeleccion();
			}else{
				iniciarDatos();
			}
		}else if(source.getText().equals(ConstantesUtil.cobrar)){
			if(table.getRowCount() != 0){
				EfectivoTarjeta efectivoTarjeta = new EfectivoTarjeta();
				efectivoTarjeta.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				efectivoTarjeta.setVisible(true);
//				btnCobrarTPV.setEnabled(false);
			}			
		}else if(source.getText().equals(ConstantesUtil.opciones)){
			Opciones opciones = new Opciones(this);
			opciones.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			opciones.setVisible(true);
			btnOpciones.setEnabled(!opciones.isShowing());
		}else if(source.getText().equals("CANCELAR")){
			if(model != null && model.getRowCount() != 0){
				appService.eliminarLineas();
				iniciarDatos();					
				panel.remove(panelLista);
				creartablaListaVenta();	
				calcularTotal();
			}
		}else if(source.getText().equals(ConstantesUtil.eliminarLinea)){
			if(model != null && model.getRowCount() != 0 && lineaSeleccionada){
				model.removeRow(filasSeleccionadas);
				appService.eliminarLineaById(idLineaVenta);
				tfUnidades.setText("0");
				calcularTotal();
				limpiarSeleccion();
			}
			
		}else if(source.getText().equals("+")){
			if(model != null && model.getRowCount() != 0 && lineaSeleccionada){
				BigDecimal cantidad;
				BigDecimal nuevoPrecio;
				
				tfUnidades.setText(String.valueOf(Integer.parseInt(tfUnidades.getText()) + 1));
				table.setValueAt(tfUnidades.getText(), filasSeleccionadas, 3);
				cantidad = new BigDecimal(tfUnidades.getText());
				nuevoPrecio =  precio.multiply(cantidad);
				precioBrutoProductoCambiado = nuevoPrecio;
				table.setValueAt(precioBrutoProductoCambiado.toString(), filasSeleccionadas, 4);
				LineaVenta lineaModificada = appService.obtenerLineaVentasById(idLineaVenta);
				lineaModificada.setCantidad(cantidad.intValue());
				lineaModificada.setTotal(precioBrutoProductoCambiado);
				appService.upDateLineaVenta(lineaModificada);
				
				calcularTotal();
			}
		}else if(source.getText().equals("-")){
			if(model != null && model.getRowCount() != 0 && lineaSeleccionada){
				BigDecimal cantidad;
				BigDecimal nuevoPrecio;
				tfUnidades.setText(Integer.parseInt(tfUnidades.getText()) > 0 ? String.valueOf(Integer.parseInt(tfUnidades.getText()) - 1) : "0" );
				table.setValueAt(tfUnidades.getText(), filasSeleccionadas, 3);
				cantidad = new BigDecimal(tfUnidades.getText());
				nuevoPrecio =  precio.multiply(cantidad);
				precioBrutoProductoCambiado = nuevoPrecio;
				table.setValueAt(precioBrutoProductoCambiado.toString(), filasSeleccionadas, 4);
				LineaVenta lineaModificada = appService.obtenerLineaVentasById(idLineaVenta);
				lineaModificada.setCantidad(cantidad.intValue());
				lineaModificada.setTotal(precioBrutoProductoCambiado);
				appService.upDateLineaVenta(lineaModificada);
				calcularTotal();
			}
		}else if(botonPulsado != null && botonPulsado.equals(ConstantesUtil.c)){
			crearListaProductos(id);
			limpiarSeleccion();
		}else if(botonPulsado != null && botonPulsado.equals(ConstantesUtil.p)){
			crearLineaVenta(id, false, new BigDecimal(1));
			panel.remove(panelLista);
			creartablaListaVenta();	
			calcularTotal();
			limpiarSeleccion();
		}else if(source.getName() != null  && (panelBotonesProductos.getComponentCount() != 0 &&  source.getName().equals(ConstantesUtil.downP))){
			JPanel nuevoPanel = null;
			int aux;
			if(cantidadGridProductos == 1){
				aux = posicionActualProducto;
				posicionActualProducto = 0;
				posicionSiguienteProducto = null;
				posicionAnteriorProducto = null;
				nuevoPanel = new JPanel();
				nuevoPanel = listaPanelesProducto.get(posicionActualProducto);
				panelBotonesProductos.removeAll();
				panelBotonesProductos.repaint();
			}else{			
				if(posicionAnteriorProducto == null){
					aux = posicionActualProducto;
					posicionActualProducto = new Integer(listaPosicionPanelProd.size() - 1);
					posicionSiguienteProducto = null;
					posicionAnteriorProducto = posicionActualProducto - 1;
					nuevoPanel = new JPanel();
					nuevoPanel = listaPanelesProducto.get(posicionActualProducto);
					panelBotonesProductos.removeAll();
					panelBotonesProductos.repaint();
					
				}else if(posicionAnteriorProducto == 0){
					aux = posicionActualProducto;
					posicionActualProducto = posicionAnteriorProducto;
					posicionSiguienteProducto = new Integer(aux);
					posicionAnteriorProducto = null;
					nuevoPanel = new JPanel();
					nuevoPanel = listaPanelesProducto.get(posicionActualProducto);
					panelBotonesProductos.removeAll();
					panelBotonesProductos.repaint();
					
				}else{
					aux = posicionActualProducto;
					posicionSiguienteProducto = posicionActualProducto;
					posicionActualProducto = aux - 1;				
					posicionAnteriorProducto = new Integer(posicionActualProducto - 1);
					nuevoPanel = new JPanel();
					nuevoPanel = listaPanelesProducto.get(posicionActualProducto);
					panelBotonesProductos.removeAll();
					panelBotonesProductos.repaint();
				}
			}
			limpiarSeleccion();
			panelBotonesProductos.add(nuevoPanel);
			panelBotonesProductos.repaint();
		}else if(source.getName() != null  && (panelBotonesProductos.getComponentCount() != 0 && source.getName().equals(ConstantesUtil.upP))){
			
			JPanel nuevoPanel = null;
			int aux;
			if(cantidadGridProductos == 1){
				aux = posicionActualProducto;
				posicionActualProducto = 0;
				posicionSiguienteProducto = null;
				posicionAnteriorProducto = null;
				nuevoPanel = new JPanel();
				nuevoPanel = listaPanelesProducto.get(posicionActualProducto);
				panelBotonesProductos.removeAll();
				panelBotonesProductos.repaint();
			}else{		
				
				if(posicionSiguienteProducto == null){
					aux = posicionActualProducto;
					posicionActualProducto = 0;
					posicionSiguienteProducto = new Integer(posicionActualProducto + 1);
					posicionAnteriorProducto = null;
					nuevoPanel = new JPanel();
					nuevoPanel = listaPanelesProducto.get(posicionActualProducto);
					panelBotonesProductos.removeAll();
					panelBotonesProductos.repaint();
					
				}else if(posicionSiguienteProducto == listaPosicionPanelProd.size() - 1){
					aux = posicionActualProducto;
					posicionActualProducto = aux + 1;
					posicionSiguienteProducto = null;
					posicionAnteriorProducto = new Integer(aux);
					nuevoPanel = new JPanel();
					nuevoPanel = listaPanelesProducto.get(posicionActualProducto);
					panelBotonesProductos.removeAll();
					panelBotonesProductos.repaint();
					
				}else{
					aux = posicionActualProducto;
					posicionActualProducto = aux + 1;
					posicionSiguienteProducto = posicionActualProducto + 1;
					posicionAnteriorProducto = new Integer(aux);
					nuevoPanel = new JPanel();
					nuevoPanel = listaPanelesProducto.get(posicionActualProducto);
					panelBotonesProductos.removeAll();
					panelBotonesProductos.repaint();
				}
			}
			panelBotonesProductos.add(nuevoPanel);
			panelBotonesProductos.repaint();
			limpiarSeleccion();
		}else if(source.getName() != null && source.getName().equals(ConstantesUtil.downC)){
			JPanel nuevoPanel = null;
			int aux;
			
			if(cantidadGridCategorias == 1){
				aux = posicionActualCategoria;
				posicionActualCategoria = 0;
				posicionSiguienteCategoria = null;
				posicionAnteriorCategoria = null;
				nuevoPanel = new JPanel();
				nuevoPanel = listaPanelesCategoria.get(posicionActualCategoria);
				panelBotonesCategoria.removeAll();
				panelBotonesCategoria.repaint();
			}else{
				if(posicionAnteriorCategoria == null){
					aux = posicionActualCategoria;
					posicionActualCategoria = new Integer(listaPosicionPanelCate.size() - 1);
					posicionSiguienteCategoria = null;
					posicionAnteriorCategoria = posicionActualCategoria - 1;
					nuevoPanel = new JPanel();
					nuevoPanel = listaPanelesCategoria.get(posicionActualCategoria);
					panelBotonesCategoria.removeAll();
					panelBotonesCategoria.repaint();
					
				}else if(posicionAnteriorCategoria == 0){
					aux = posicionActualCategoria;
					posicionActualCategoria = 0;
					posicionSiguienteCategoria = new Integer(aux);
					posicionAnteriorCategoria = null;
					nuevoPanel = new JPanel();
					nuevoPanel = listaPanelesCategoria.get(posicionActualCategoria);
					panelBotonesCategoria.removeAll();
					panelBotonesCategoria.repaint();
					
				}else{
					aux = posicionActualCategoria;
					posicionSiguienteCategoria = posicionActualCategoria;
					posicionActualCategoria = aux - 1;				
					posicionAnteriorCategoria = new Integer(posicionActualCategoria - 1);
					nuevoPanel = new JPanel();
					nuevoPanel = listaPanelesCategoria.get(posicionActualCategoria);
					panelBotonesCategoria.removeAll();
					panelBotonesCategoria.repaint();
				}
			}
			panelBotonesCategoria.add(nuevoPanel);
			panelBotonesCategoria.repaint();
			limpiarSeleccion();
		}else if(source.getName() != null && source.getName().equals(ConstantesUtil.upC)){
			JPanel nuevoPanel = null;
			int aux;
			
			if(cantidadGridCategorias == 1){
				aux = posicionActualCategoria;
				posicionActualCategoria = 0;
				posicionSiguienteCategoria = null;
				posicionAnteriorCategoria = null;
				nuevoPanel = new JPanel();
				nuevoPanel = listaPanelesCategoria.get(posicionActualCategoria);
				panelBotonesCategoria.removeAll();
				panelBotonesCategoria.repaint();
			}else{
			
				if(posicionSiguienteCategoria == null){
					aux = posicionActualCategoria;
					posicionActualCategoria = 0;
					posicionSiguienteCategoria = new Integer(posicionActualCategoria + 1);
					posicionAnteriorCategoria = null;
					nuevoPanel = new JPanel();
					nuevoPanel = listaPanelesCategoria.get(posicionActualCategoria);
					panelBotonesCategoria.removeAll();
					panelBotonesCategoria.repaint();
					
				}else if(posicionSiguienteCategoria == listaPosicionPanelCate.size() - 1){
					aux = posicionActualCategoria;
					posicionActualCategoria = aux + 1;
					posicionSiguienteCategoria = null;
					posicionAnteriorCategoria = new Integer(aux);
					nuevoPanel = new JPanel();
					nuevoPanel = listaPanelesCategoria.get(posicionActualCategoria);
					panelBotonesCategoria.removeAll();
					panelBotonesCategoria.repaint();
					
				}else{
					aux = posicionActualCategoria;
					posicionActualCategoria = aux + 1;
					posicionSiguienteCategoria = posicionActualCategoria + 1;
					posicionAnteriorCategoria = new Integer(aux);
					nuevoPanel = new JPanel();
					nuevoPanel = listaPanelesCategoria.get(posicionActualCategoria);
					panelBotonesCategoria.removeAll();
					panelBotonesCategoria.repaint();
				}
			}
			panelBotonesCategoria.add(nuevoPanel);
			panelBotonesCategoria.repaint();
		
		}else{
			if(tfTotalCalculadora.getText().equals("0.00") && !source.getText().equals("")){
				tfTotalCalculadora.setText(source.getText());
			}else if(!source.getText().equals("")){
				tfTotalCalculadora.setText(tfTotalCalculadora.getText() + source.getText());
			}
			
		}
		
	}
	private Producto obtenerProductoGenerico(){
		Categoria aux = new Categoria();
		aux = appService.obtenerCategoriaGeneral();
		Producto producto = appService.obtenerProductoGenerico();		
		return producto;
	}
	private Long crearLineaVenta(BigDecimal precio){
		IVAEnum iva = IVAEnum.VEINTIUNO;
		BigDecimal importeAux = BigDecimal.ZERO;		
		BigDecimal totalIva = new BigDecimal(iva.getCodigo());
		totalIva = totalIva.divide(new BigDecimal(100));
		Date fecha = new Date();
		
		LineaVenta lineaVenta = new LineaVenta();
		Producto aux = obtenerProductoGenerico();
		Integer cantidad = 1;
		BigDecimal cant = new BigDecimal(cantidad);		
		lineaVenta.setCantidad(cantidad);
		lineaVenta.setProducto(aux.getIdProducto());
		importeAux = precio;
		BigDecimal baseImponible =importeAux.subtract(importeAux.multiply(totalIva));
		lineaVenta.setFecha(fecha);
		lineaVenta.setImporte(precio);
		lineaVenta.setTotal(precio.multiply(cant));
		lineaVenta.setProductoCambiado(false);
		appService.insertarLineaVenta(lineaVenta);
		return lineaVenta.getIdLineaVenta();
	}
	private void crearLineaVenta(String id, boolean productoCambiado, BigDecimal cantidad2) {
		IVAEnum iva = IVAEnum.VEINTIUNO;
		BigDecimal aux = BigDecimal.ZERO;		
		BigDecimal totalIva = new BigDecimal(iva.getCodigo());
		totalIva = totalIva.divide(new BigDecimal(100));
		Date fecha = new Date();
		
	
		LineaVenta lineaVenta = new LineaVenta();
		
		if(!productoCambiado){
			Producto producto = appService.obtenerProducto(id);
			lineaVenta.setCantidad(cantidad2.intValue());
			lineaVenta.setProducto(producto.getIdProducto());
			aux = producto.getPrecio();
			BigDecimal baseImponible =aux.subtract(aux.multiply(totalIva));
			precioNetoProductoCambiado = baseImponible;
			precioBrutoProductoCambiado = aux;
			
			lineaVenta.setImporte(precioNetoProductoCambiado);
			lineaVenta.setTotal(precioBrutoProductoCambiado.multiply(cantidad2));
			lineaVenta.setProductoCambiado(productoCambiado);
			
		}else{
			ProductoCambiado productoC = appService.obtenerProductoCambiadoById(id);			
			lineaVenta.setCantidad(cantidad2.intValue());
			lineaVenta.setProducto(productoC.getIdProducto());
			aux = precioBrutoProductoCambiado;
			BigDecimal baseImponible =aux.subtract(aux.multiply(totalIva));
			precioNetoProductoCambiado = baseImponible;
			
			lineaVenta.setImporte(precioNetoProductoCambiado);
			lineaVenta.setTotal(productoC.getPrecio().multiply(cantidad2));
			lineaVenta.setProductoCambiado(productoCambiado);
		}	
		lineaVenta.setImporteCambiadoBruto(precioBrutoProductoCambiado);
		lineaVenta.setImporteCambiadoNeto(precioNetoProductoCambiado);
		lineaVenta.setFecha(fecha);
		appService.insertarLineaVenta(lineaVenta);
	}
	
	private void crearVenta(List<LineaVenta> lista, boolean imprimir){
		Venta venta = new Venta();
		List<LineaVenta> listaLineas = lista;
		EstadoEnum estado = EstadoEnum.REALIZADA;
		IVAEnum iva = IVAEnum.VEINTIUNO;
		Date fecha = new Date();
		Long idVenta, idLVR;
		configuracion = gestionService.getConfiguracion();

		Empleado empleado = appService.obtenerEmpleado();		
		venta.setEmpleado(empleado);
		venta.setFecha(fecha);		
		venta.setEstado(estado.name());
		venta.setIVA(iva.getCodigo());
		venta.setFormaPago(ConstantesUtil.getConTarjeta());
		idLVR = crearLineaVentaRealizada(lista);
		venta.setIdLineaVentaRealizada(idLVR);
//		venta.setDetalle(listaLineas);
		List<BigDecimal> listaBigDecimal = new ArrayList<>();
		for (LineaVenta lineaVenta : listaLineas) {
			BigDecimal bd = lineaVenta.getTotal();
//			BigDecimal bd = lineaVenta.getImporteCambiadoBruto();
			listaBigDecimal.add(bd);
		}
		BigDecimal aux = BigDecimal.ZERO;
		for (BigDecimal numero : listaBigDecimal) {
			aux = aux.add(numero);
		}
		BigDecimal totalIva = new BigDecimal(iva.getCodigo());
		totalIva = totalIva.divide(new BigDecimal(100));
		BigDecimal baseImponible =aux.subtract(aux.multiply(totalIva));
		venta.setImporte(baseImponible);
		// FIXME como los precios se suponen con iva el importe será el total con iva incluido.
//		BigDecimal nuevoIva = new BigDecimal(venta.getIVA()).divide(new BigDecimal(100));
//		venta.setTotal(venta.getImporte().add(venta.getImporte().multiply(nuevoIva)));
		venta.setTotal(aux);
		venta.setNumero(appService.obtenerNumeroVenta() != null ? appService.obtenerNumeroVenta() + 1 : 1);
		idVenta = appService.crearVenta(venta);
		
//		Creo una factura
		Factura factura = new Factura();
		factura.setCabecera(configuracion.getCabeceraFactura() != null ? configuracion.getCabeceraFactura() : null);
		factura.setEmpleado(empleado.getNombre());
		factura.setFecha(fecha);
		factura.setIdVenta(idVenta);
		factura.setIdVentaRealizada(idLVR);
		factura.setIVA(iva.getCodigo());
		factura.setPie(configuracion.getPieFactura() != null ? configuracion.getPieFactura() : null);
		factura.setBaseImponible(baseImponible);		
		factura.setTotal(aux);
		factura.setFormaPago(venta.getFormaPago());
		appService.crearFactura(factura);
		
		if(imprimir){
			new ImprimirTicket(listaLineas, idLVR, venta.getFormaPago());	
		}
		
	}
	 private Long crearLineaVentaRealizada(List<LineaVenta> lista) {
		 List<LineaVenta> listaLineas = lista;
		 Long dev = null;
		 BigDecimal importe = BigDecimal.ZERO;
		 BigDecimal aux = BigDecimal.ZERO;
		 BigDecimal cantidad = BigDecimal.ZERO;
		 LineaVentaRealizada lineaVRealizada = new LineaVentaRealizada();
		 ListaProducto listaProducto = null;
		 List<ListaProducto> listaP = new ArrayList<>();
		 Long idLineaVentaRealizada;
		
		 
		 
		 if(lineaVRealizada != null){
			 for (LineaVenta lineaVenta : listaLineas) {
				 aux = lineaVenta.getImporteCambiadoBruto();
				 if(lineaVenta.getCantidad() > 1){
					 cantidad = new BigDecimal(lineaVenta.getCantidad());
					 aux = aux.multiply(cantidad);
				 }
				 importe = importe.add(aux);
				 lineaVRealizada.setFecha(lineaVenta.getFecha());
			 }
			 lineaVRealizada.setTotal(importe);
			 
			 idLineaVentaRealizada = appService.crearLineaVentaRealizada(lineaVRealizada);
			 importe = BigDecimal.ZERO;
			 for (LineaVenta lineaVenta : listaLineas) {
				 aux = lineaVenta.getImporteCambiadoBruto();
				importe = importe.add(aux);
				listaProducto = new ListaProducto();
				listaProducto.setIdProducto(lineaVenta.getIdProducto());
				listaProducto.setCantidad(lineaVenta.getCantidad());
				listaProducto.setLineaVentaRealizada(lineaVRealizada);
				listaProducto.setFecha(lineaVenta.getFecha());
				listaProducto.setIsProductoCambiado(lineaVenta.getProductoCambiado());
				lineaVentaEdit.setProductoCambiado(isProductoCambiado);
				
				listaP.add(listaProducto);
				if(lineaVenta.getIdProducto().equals(ConstantesUtil.idProductosVarios) || lineaVenta.getProductoCambiado()){ // Aca no entra porque la listaLinea no viene con el idProducto 1 cuando es un varios revisar eso
					List<Long> listaIdProductosVarios = new ArrayList<>();
					Long id;
					ProductosVarios productosVarios = new ProductosVarios();
					productosVarios.setIdProducto(lineaVenta.getIdProducto());
					productosVarios.setLineaVentaRealizada(idLineaVentaRealizada);
					productosVarios.setNombre("Varios");
					productosVarios.setPrecio(lineaVenta.getImporteCambiadoBruto());
					productosVarios.setFecha(lineaVenta.getFecha());
					id = appService.crearProductosVarios(productosVarios);
				}
				
			}
			 
			 dev = lineaVRealizada.getIdLineaVenta();
			 for (ListaProducto lp : listaP) {
				 appService.crearListaProducto(lp);
			}	 
			 
		 }		 
		 
		return dev;
	}
	class Cobrar extends JDialog implements ActionListener{

		private JPanel contentPane;
		private JTextField tfTotal,tfEfectivo,tfCambio;
		private JButton btnCobrar,btnAtras;
		private AppService appService = new AppServiceImpl();
		private int alto,ancho;
		private JCheckBox chckbxNewCheckBox;

		public Cobrar(String total) {
//			alto = 382;
//			ancho = 474;
			alto = (int)(altoVentana*0.4244);
			ancho = (int)(anchoVentana*0.3291);
			setUndecorated(true);
			setBounds((int)ConstantesUtil.ancho/2 - ancho/2, (int)ConstantesUtil.alto/2 - alto/2, ancho, alto);
			contentPane = new JPanel();
			contentPane.setBorder(new LineBorder(new Color(65, 105, 170), 10));
//			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			setModal(true);
			tfTotal = new JTextField();
			tfTotal.setEditable(false);
			tfTotal.setHorizontalAlignment(SwingConstants.RIGHT);
			tfTotal.setBackground(Color.BLACK);
			tfTotal.setForeground(Color.GREEN);
			tfTotal.setText(total);
			tfTotal.setFont(ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 17) : new Font("Lucida Grande", Font.BOLD, 22));
//			tfTotal.setBounds(170, 33, 140, 63);
			tfTotal.setBounds((int)(anchoVentana*0.1180), (int)(altoVentana*0.0366), (int)(anchoVentana*0.0972), (int)(altoVentana*0.07));
			contentPane.add(tfTotal);
			tfTotal.setColumns(10);
			
			JLabel lblTotal = new JLabel("TOTAL");
			lblTotal.setFont(ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 17) : new Font("Lucida Grande", Font.BOLD, 28));
			lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
//			lblTotal.setBounds(45, 34, 112, 57);
			lblTotal.setBounds((int)(anchoVentana*0.0312), (int)(altoVentana*0.0377), (int)(anchoVentana*0.0777), (int)(altoVentana*0.0633));
			contentPane.add(lblTotal);
			
			JLabel lblEuro = new JLabel("€");
			lblEuro.setFont(ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 22) : new Font("Lucida Grande", Font.BOLD, 28));
			lblEuro.setHorizontalAlignment(SwingConstants.LEFT);
//			lblEuro.setBounds(312, 33, 61, 63);
			lblEuro.setBounds((int)(anchoVentana*0.2166), (int)(altoVentana*0.0366), (int)(anchoVentana*0.0423), (int)(altoVentana*0.07));
			contentPane.add(lblEuro);
			
			tfEfectivo = new JTextField();
			tfEfectivo.setFont(ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 15) : new Font("Lucida Grande", Font.BOLD, 22));
			tfEfectivo.setHorizontalAlignment(SwingConstants.RIGHT);
//			tfEfectivo.setBounds(170, 126, 140, 46);
			tfEfectivo.setBounds((int)(anchoVentana*0.1180), (int)(altoVentana*0.14), (int)(anchoVentana*0.0972), (int)(altoVentana*0.0511));
			contentPane.add(tfEfectivo);
			tfEfectivo.setColumns(10);
			
			JLabel lblEfectivo = new JLabel("EFECTIVO");
			lblEfectivo.setFont(ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 15) : new Font("Lucida Grande", Font.BOLD, 22));
			lblEfectivo.setHorizontalAlignment(SwingConstants.CENTER);
//			lblEfectivo.setBounds(45, 126, 112, 46);
			lblEfectivo.setBounds((int)(anchoVentana*0.0312), (int)(altoVentana*0.14), (int)(anchoVentana*0.0777), (int)(altoVentana*0.0511));
			contentPane.add(lblEfectivo);
			
			JLabel label = new JLabel("€");
			label.setHorizontalAlignment(SwingConstants.LEFT);
			label.setFont(ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 20) : new Font("Lucida Grande", Font.BOLD, 26));
//			label.setBounds(312, 113, 61, 63);
			label.setBounds((int)(anchoVentana*0.2166), (int)(altoVentana*0.125), (int)(anchoVentana*0.0423), (int)(altoVentana*0.07));
			contentPane.add(label);
			
			btnCobrar = new JButton(ConstantesUtil.cobrar);
			btnCobrar.setIcon(new ImageIcon(PantallaTPV.class.getResource(ConstantesUtil.pathCobrar)));
			btnCobrar.addActionListener(this);
			btnCobrar.setFont(ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 13) : new Font("Lucida Grande", Font.BOLD, 17));
//			btnCobrar.setBounds(35, 184, 134, 88);
			btnCobrar.setBounds((int) ancho/4 - (int)(anchoVentana*0.12)/2, (int)(altoVentana*0.2044), (int)(anchoVentana*0.12), (int)(altoVentana*0.0977));
			contentPane.add(btnCobrar);		
						
			btnAtras = new JButton(ConstantesUtil.cerrar);
			btnAtras.setIcon(new ImageIcon(FormularioCategoria.class.getResource(ConstantesUtil.pathSalir)));
			btnAtras.addActionListener(this);
			btnAtras.setFont(ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 12) : new Font("Lucida Grande", Font.BOLD, 17));
//			btnAtras.setBounds(310, 184, 134, 88);
			btnAtras.setBounds((int) ancho - ancho/4 - (int)(anchoVentana*0.12)/2, (int)(altoVentana*0.2044), (int)(anchoVentana*0.12), (int)(altoVentana*0.0977));
			contentPane.add(btnAtras);
			
			JLabel lblCambio = new JLabel("CAMBIO");
			lblCambio.setFont(ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 15) : new Font("Lucida Grande", Font.BOLD, 22));
			lblCambio.setHorizontalAlignment(SwingConstants.CENTER);
//			lblCambio.setBounds(35, 284, 134, 63);
			lblCambio.setBounds((int)(anchoVentana*0.0243),  (int)(altoVentana*0.315), (int)(anchoVentana*0.093),  (int)(altoVentana*0.07));
			contentPane.add(lblCambio);
			
			tfCambio = new JTextField();
			tfCambio.setHorizontalAlignment(SwingConstants.RIGHT);
			tfCambio.setFont(ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 17) : new Font("Lucida Grande", Font.BOLD, 22));
			tfCambio.setEnabled(false);
//			tfCambio.setBounds(170, 282, 140, 65);
			tfCambio.setBounds((int)(anchoVentana*0.118), (int)(altoVentana*0.3133), (int)(anchoVentana*0.0972), (int)(altoVentana*0.0722));
			contentPane.add(tfCambio);
			tfCambio.setColumns(10);
			
			JLabel label_1 = new JLabel("€");
			label_1.setHorizontalAlignment(SwingConstants.LEFT);
			label_1.setFont(ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 21) : new Font("Lucida Grande", Font.BOLD, 26));
//			label_1.setBounds(312, 282, 61, 63);
			label_1.setBounds((int)(anchoVentana*0.2166), (int)(altoVentana*0.3133), (int)(anchoVentana*0.0423), (int)(altoVentana*0.07));
			contentPane.add(label_1);
			
			chckbxNewCheckBox = new JCheckBox(ConstantesUtil.imprimirTicket);
			chckbxNewCheckBox.setHorizontalAlignment(SwingConstants.LEFT);
			chckbxNewCheckBox.setFont(new Font("Lucida Grande", Font.BOLD, 17));
			chckbxNewCheckBox.setBounds(32, 100, 409, 23);
			contentPane.add(chckbxNewCheckBox);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton) e.getSource();
			
			if(source.getText().equals(ConstantesUtil.cerrar)){
				
				dispose();
			}else if(source.getText().equals(ConstantesUtil.cobrar)){
				if(tfEfectivo.getText() != null && validar()){
					BigDecimal total = new BigDecimal(tfTotal.getText());
					BigDecimal efectivo = new BigDecimal(ConstantesUtil.obtenerNumeroSinComa(tfEfectivo.getText()));
					if(efectivo.compareTo(total) == -1){
						JOptionPane.showMessageDialog(this, "Falta efectivo !!!");
						tfEfectivo.setText("");
					}else{
						total = efectivo.subtract(total).setScale(2);
						ConstantesUtil.cambio = total.toString();
						ConstantesUtil.pagoEfectivo = efectivo.setScale(2).toString();
						tfCambio.setText(total.toString());
						List<LineaVenta> listaLineas = appService.obtenerLineas();
						appService.eliminarLineas();
						iniciarDatos();					
						panel.remove(panelLista);
						creartablaListaVenta();	
						calcularTotal();
						crearVenta(listaLineas, chckbxNewCheckBox.isSelected());						
					}					
				}else{
					JOptionPane.showMessageDialog(this, "Efectivo incorrecto !!!");
					tfEfectivo.setText("");
				}
			}			
		}
		
		private Boolean validar(){
			Boolean dev = Boolean.FALSE;
			
			if(!tfEfectivo.getText().equals("") && Validador.isNumero(tfEfectivo.getText())){
				dev = true;
			}else{
				dev = false;
			}			
			return dev;
		}
	}
	 
	 class EfectivoTarjeta extends JDialog implements ActionListener{

			private JPanel contentPane;
			private int alto, ancho;
			private JButton btnEfectivo, btnTarjeta;
			/**
			 * Create the frame.
			 */
			public EfectivoTarjeta() {
				setResizable(false);
				setUndecorated(true);
//				alto = 243;
//				ancho = 450;
				alto = (int)(altoVentana*0.27);
				ancho = (int)(anchoVentana*0.3125);
				setBounds((int)ConstantesUtil.ancho/2 - ancho/2, (int)ConstantesUtil.alto/2 - alto/2, ancho, alto);
				contentPane = new JPanel();
				contentPane.setBorder(new LineBorder(new Color(65, 105, 170), 10));
//				contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				setContentPane(contentPane);
				contentPane.setLayout(null);
				setModal(true);
				btnEfectivo = new JButton("EFECTIVO");
				btnEfectivo.addActionListener(this);
				btnEfectivo.setFont(ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 13) : new Font("Lucida Grande", Font.BOLD, 15));
//				btnEfectivo.setBounds(ancho/2 - 157, 129, 147, 96);
				btnEfectivo.setBounds((int)(anchoVentana*0.0472), (int)(altoVentana*0.1433), (int)(anchoVentana*0.1020), (int)(altoVentana*0.1066));
				contentPane.add(btnEfectivo);
				
				JLabel lblDollar = new JLabel();
				lblDollar.setIcon(new ImageIcon(PantallaTPV.class.getResource(ConstantesUtil.pathEfectivo)));
				lblDollar.setHorizontalAlignment(SwingConstants.CENTER);
//				lblDollar.setBounds(ancho/2 - 157, 30, 147, 87);
				lblDollar.setBounds((int)(anchoVentana*0.0472), (int)(altoVentana*0.0333), (int)(anchoVentana*0.1020), (int)(altoVentana*0.0966));
				contentPane.add(lblDollar);
				
				btnTarjeta = new JButton("TARJETA");
				btnTarjeta.addActionListener(this);
				btnTarjeta.setFont(ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 13) : new Font("Lucida Grande", Font.BOLD, 15));
//				btnTarjeta.setBounds(ancho/2 + 10, 129, 147, 96);
				btnTarjeta.setBounds((int)(anchoVentana*0.1631), (int)(altoVentana*0.1433), (int)(anchoVentana*0.1020), (int)(altoVentana*0.1066));
				contentPane.add(btnTarjeta);
				
				JLabel lblTarjeta = new JLabel();
				lblTarjeta.setIcon(new ImageIcon(PantallaTPV.class.getResource(ConstantesUtil.pathTarjeta)));
				lblTarjeta.setHorizontalAlignment(SwingConstants.CENTER);
//				lblTarjeta.setBounds(ancho/2 + 10, 30, 147, 87);
				lblTarjeta.setBounds((int)(anchoVentana*0.1631), (int)(altoVentana*0.0333), (int)(anchoVentana*0.1020), (int)(altoVentana*0.0966));
				contentPane.add(lblTarjeta);
			}
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton source = (JButton) e.getSource();
				
				if(source.getText().equals(ConstantesUtil.efectivo)){
					ConstantesUtil.setCobroConTarjeta(false);
					Cobrar frame = new Cobrar(tfTotal.getText());
					frame.setVisible(true);
					limpiarSeleccion();
					calcularTotal();
					dispose();
					
				}else{
					ConstantesUtil.setCobroConTarjeta(true);
					Tarjeta tarjeta = new Tarjeta(tfTotal.getText());
					tarjeta.setVisible(true);
					limpiarSeleccion();
					calcularTotal();
					dispose();					
				}
				
			}

		}
	 
	 
	 class Tarjeta extends JDialog implements ActionListener{

			private JPanel contentPane;
			private JButton btnCobrar,btnAtras;
			private JTextField tfTotal;
			private int alto,ancho;
			private JCheckBox chckbxNewCheckBox;
			/**
			 * Create the frame.
			 */
			public Tarjeta(String total) {
				setResizable(false);
				setUndecorated(true);
//				alto = 300;
//				ancho = 450;
				alto = (int)(altoVentana*0.3333);
				ancho = (int)(anchoVentana*0.3125);
				setBounds((int)ConstantesUtil.ancho/2 - ancho/2, (int)ConstantesUtil.alto/2 - alto/2, ancho, alto);
				contentPane = new JPanel();
				contentPane.setBorder(new LineBorder(new Color(65, 105, 170), 10));
//				contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				setContentPane(contentPane);
				contentPane.setLayout(null);
				setModal(true);
				JLabel lblTotal = new JLabel("TOTAL");
				lblTotal.setFont(ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 17) : new Font("Lucida Grande", Font.BOLD, 28));
				lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
//				lblTotal.setBounds(45, 34, 112, 57);
				lblTotal.setBounds((int)(anchoVentana*0.0312), (int)(altoVentana*0.0377), (int)(anchoVentana*0.0777), (int)(altoVentana*0.0633));
				contentPane.add(lblTotal);
				
				JLabel lblEuro = new JLabel("€");
				lblEuro.setFont(ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 22) : new Font("Lucida Grande", Font.BOLD, 28));
				lblEuro.setHorizontalAlignment(SwingConstants.LEFT);
//				lblEuro.setBounds(312, 33, 61, 63);
				lblEuro.setBounds((int)(anchoVentana*0.2166), (int)(altoVentana*0.0366), (int)(anchoVentana*0.0423), (int)(altoVentana*0.07));
				contentPane.add(lblEuro);
				
				tfTotal = new JTextField();
				tfTotal.setEditable(false);
				tfTotal.setHorizontalAlignment(SwingConstants.RIGHT);
				tfTotal.setBackground(Color.BLACK);
				tfTotal.setForeground(Color.GREEN);
				tfTotal.setText(total);
				tfTotal.setFont(ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 17) : new Font("Lucida Grande", Font.BOLD, 22));
//				tfTotal.setBounds(170, 33, 140, 63);
				tfTotal.setBounds((int)(anchoVentana*0.118), (int)(altoVentana*0.0366), (int)(anchoVentana*0.0972), (int)(altoVentana*0.07));
				contentPane.add(tfTotal);
				tfTotal.setColumns(10);
				btnCobrar = new JButton(ConstantesUtil.cobrar);
				btnCobrar.setIcon(new ImageIcon(PantallaTPV.class.getResource(ConstantesUtil.pathCobrar)));
				btnCobrar.addActionListener(this);
				btnCobrar.setFont(ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 13) : new Font("Lucida Grande", Font.BOLD, 17));
//				btnCobrar.setBounds(ancho/2 - 154 , alto/2 +10, 134, 88);
				btnCobrar.setBounds((int) ancho/4 - (int)(anchoVentana*0.12)/2 , (int)(altoVentana*0.1777), (int)(anchoVentana*0.12), (int)(altoVentana*0.0977));
				contentPane.add(btnCobrar);
				
				btnAtras = new JButton(ConstantesUtil.cerrar);
				btnAtras.setIcon(new ImageIcon(FormularioCategoria.class.getResource(ConstantesUtil.pathSalir)));
				btnAtras.addActionListener(this);
				btnAtras.setFont(ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 13) : new Font("Lucida Grande", Font.BOLD, 17));
//				btnAtras.setBounds(ancho - ancho/2 + 20, alto/2 +10, 134, 88);
				btnAtras.setBounds((int) ancho - ancho/4 - (int)(anchoVentana*0.12)/2, (int)(altoVentana*0.1777), (int)(anchoVentana*0.12), (int)(altoVentana*0.0977));
				contentPane.add(btnAtras);
				chckbxNewCheckBox = new JCheckBox(ConstantesUtil.imprimirTicket);
				chckbxNewCheckBox.setFont(new Font("Lucida Grande", Font.BOLD, 17));
				chckbxNewCheckBox.setBounds(26, 112, 282, 30);
				contentPane.add(chckbxNewCheckBox);
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				JButton source = (JButton) e.getSource();
				
				if(source.getText().equals(ConstantesUtil.cerrar)){
//					btnCobrarTPV.setEnabled(true);
					dispose();
				}else if(source.getText().equals(ConstantesUtil.cobrar)){
					BigDecimal total = new BigDecimal(tfTotal.getText());
					List<LineaVenta> listaLineas = appService.obtenerLineas();
					appService.eliminarLineas();
					iniciarDatos();					
					panel.remove(panelLista);
					creartablaListaVenta();	
					calcularTotal();
					crearVenta(listaLineas, chckbxNewCheckBox.isSelected());
					
					dispose();
					}
				}	
				
		}
	 
	 
	 
	 
	 public class LineaVentaEdit extends JDialog implements ActionListener{

			private final JPanel contentPanel = new JPanel();
			private double alto, ancho;
			private JTextField txtNombrePCambiado,txtCatidadPCambiado,txtPrecioPCambiado;
			private JButton btnGuardar,btnAceptar,btnSalir;
			private String precio;
			private BigDecimal pre;
			private GestionService service = new GestionServiceImpl();
			private Boolean IsproductoCambiado = Boolean.FALSE;

			/**
			 * Create the dialog.
			 */
			public LineaVentaEdit() {
				alto = 323;
				ancho = 450;
				setBounds((int)ConstantesUtil.ancho/2 - (int)ancho/2, (int)ConstantesUtil.alto/2 - (int)alto/2, 450, 323);	

				getContentPane().setLayout(null);
				contentPanel.setBackground(new Color(0, 102, 153));
				contentPanel.setBounds(0, 0, 450, 239);
				contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
				getContentPane().add(contentPanel);
				contentPanel.setLayout(null);
				
				JLabel lblEditarlinea = new JLabel(ConstantesUtil.preguntaLineaPedido);
				lblEditarlinea.setHorizontalAlignment(SwingConstants.CENTER);
				lblEditarlinea.setFont(new Font("Lucida Grande", Font.BOLD, 20));
				lblEditarlinea.setForeground(Color.WHITE);
				lblEditarlinea.setBounds(6, 27, 438, 28);
				contentPanel.add(lblEditarlinea);
				
				JLabel lblNombre = new JLabel("Nombre");
				lblNombre.setForeground(Color.WHITE);
				lblNombre.setFont(new Font("Lucida Grande", Font.BOLD, 17));
				lblNombre.setBounds(6, 83, 119, 26);
				contentPanel.add(lblNombre);
				
				txtNombrePCambiado = new JTextField();
				txtNombrePCambiado.setEditable(false);
				txtNombrePCambiado.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
				txtNombrePCambiado.setColumns(10);
				txtNombrePCambiado.setBounds(148, 81, 230, 32);
				contentPanel.add(txtNombrePCambiado);
				
				JLabel lblPrecio = new JLabel("Precio");
				lblPrecio.setForeground(Color.WHITE);
				lblPrecio.setFont(new Font("Lucida Grande", Font.BOLD, 17));
				lblPrecio.setBounds(6, 127, 119, 26);
				contentPanel.add(lblPrecio);
				
				txtPrecioPCambiado = new JTextField();
				txtPrecioPCambiado.setEditable(false);
				txtPrecioPCambiado.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
				txtPrecioPCambiado.setColumns(10);
				txtPrecioPCambiado.setBounds(148, 125, 230, 32);
				contentPanel.add(txtPrecioPCambiado);
				
				JLabel lblCantidad = new JLabel("Cantidad");
				lblCantidad.setForeground(Color.WHITE);
				lblCantidad.setFont(new Font("Lucida Grande", Font.BOLD, 17));
				lblCantidad.setBounds(6, 171, 119, 26);
				contentPanel.add(lblCantidad);
				
				txtCatidadPCambiado = new JTextField();
				txtCatidadPCambiado.setEditable(false);
				txtCatidadPCambiado.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
				txtCatidadPCambiado.setColumns(10);
				txtCatidadPCambiado.setBounds(148, 169, 230, 32);
				contentPanel.add(txtCatidadPCambiado);
				setModal(true);
				setResizable(false);
				setUndecorated(true);
				{
					JPanel buttonPane = new JPanel();
					buttonPane.setBounds(0, 240, 450, 82);
					getContentPane().add(buttonPane);
					buttonPane.setLayout(null);
					
					btnSalir = new JButton(ConstantesUtil.salir);
					btnSalir.setFont(new Font("Lucida Grande", Font.BOLD, 15));
					btnSalir.setBounds(6, 6, 126, 70);
					btnSalir.setIcon(new ImageIcon(FormularioCategoria.class.getResource(ConstantesUtil.pathSalir)));
					btnSalir.addActionListener(this);
					buttonPane.add(btnSalir);
					
					btnAceptar = new JButton(ConstantesUtil.aceptar);
					btnAceptar.setFont(new Font("Lucida Grande", Font.BOLD, 15));
					btnAceptar.setBounds(318, 6, 126, 70);
					btnAceptar.addActionListener(this);
					buttonPane.add(btnAceptar);
					
					btnGuardar = new JButton(ConstantesUtil.guardar);
					btnGuardar.setIcon(new ImageIcon(FormularioProducto.class.getResource(ConstantesUtil.pathGuardar)));
					btnGuardar.setEnabled(false);
					btnGuardar.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
					btnGuardar.setBounds(151, 6, 147, 70);
					btnGuardar.addActionListener(this);
					buttonPane.add(btnGuardar);
				}
			}
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton source = (JButton) e.getSource();
				
				if(source.getText().equals(ConstantesUtil.salir)){
					btnAceptar.setEnabled(true);
					dispose();
				}else if(source.getText().equals(ConstantesUtil.aceptar)){
					txtPrecioPCambiado.setEditable(true);
					txtPrecioPCambiado.setFocusable(true);
					txtPrecioPCambiado.requestFocus();
					btnGuardar.setEnabled(true);
					btnAceptar.setEnabled(false);
					btnSalir.setEnabled(false);
					eliminarLineaById(idLinea);
				}else if(source.getText().equals(ConstantesUtil.guardar)){
					
					productoCambiado = fillObject();
					idProductoCambiado = null;
					if(productoCambiado != null){
						idProductoCambiado = service.insertarProductoCambiado(productoCambiado);
						crearLineaByIdProductoCambiado(idProductoCambiado.toString(), productoCambiado.getCantidad());
						btnGuardar.setEnabled(false);
						btnAceptar.setEnabled(true);
						btnSalir.setEnabled(true);
						dispose();
					}else{
						JOptionPane.showMessageDialog(this,"Tienes que introducir un importe correcto !!!");
					}
					
				}
				
			}
			private ProductoCambiado fillObject(){
				ProductoCambiado producto = null;
				if(validar()){	
					producto = new ProductoCambiado();
					producto.setNombre(txtNombrePCambiado.getText());
					producto.setCantidad(new BigDecimal(txtCatidadPCambiado.getText()));
					
					producto.setPrecio(pre);
				}
				
				return producto;
			}
			
			private Boolean validar() {
				Boolean dev = Boolean.FALSE;
				
				if(!txtPrecioPCambiado.getText().equals("") && Validador.isDecimal(txtPrecioPCambiado.getText())){
					precio = ConstantesUtil.obtenerNumeroSinComa(txtPrecioPCambiado.getText());
					pre = new BigDecimal(precio).setScale(2, RoundingMode.UP);
					precioBrutoProductoCambiado = pre;
					dev = true;		
				}else{
					return false;
				}
				
				return dev;
			}
			private void clearForm() {
				txtNombrePCambiado.setText("");
				txtPrecioPCambiado.setText("");
				txtCatidadPCambiado.setText("");
				precioBrutoProductoCambiado = BigDecimal.ZERO;
				precioNetoProductoCambiado = BigDecimal.ZERO;
			}

			public String getTxtNombrePCambiado() {
				return txtNombrePCambiado.getText();
			}

			public void setTxtNombrePCambiado(String txtNombrePCambiado) {
				this.txtNombrePCambiado.setText(txtNombrePCambiado);
			}

			public String getTxtCatidadPCambiado() {
				return txtCatidadPCambiado.getText();
			}

			public void setTxtCatidadPCambiado(String txtCatidadPCambiado) {
				this.txtCatidadPCambiado.setText(txtCatidadPCambiado);
			}

			public String getTxtPrecioPCambiado() {
				return txtPrecioPCambiado.getText();
			}

			public void setTxtPrecioPCambiado(String txtPrecioPCambiado) {
				this.txtPrecioPCambiado.setText(txtPrecioPCambiado);
			}		
			
			public void setProductoCambiado(Boolean productoCambiado){
				this.IsproductoCambiado = productoCambiado;
			}
			public Boolean getProductoCambiado(){
				return IsproductoCambiado;
			}
			
		}
	 
	 
	 public void cerrarTPV(){
		 dispose();
	 }


}
