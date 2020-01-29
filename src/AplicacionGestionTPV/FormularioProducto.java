package AplicacionGestionTPV;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
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
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import controladorService.GestionService;
import controladorService.GestionServiceImpl;
import modelo.Producto;
import util.ConstantesUtil;
import util.Validador;
/**
 * 
 * @author Two hands Technology
 *
 */
public class FormularioProducto extends JDialog implements ActionListener{

	private JPanel contentPane, panelTabla;
	private JTable table, modeloTabla;
	private JTextField tfNombre, tfMarca, tfDescripcion, tfPrecio, tfCantidad;
	private GestionService service = new GestionServiceImpl();
	private String precio,categoria, idProducto, ruta;
	private int filasSeleccionadas;
	private JButton btnNuevo, btnGuardModif, btnCancelar, btnEliminar, btnSeleccionar, btnSalir;
	private JComboBox cbCategoria;
	private JScrollPane scrollPane;
	private HashMap<Long, String> listaCategorias;
	private File fichero;
	private JLabel lblFoto;
	private BigDecimal pre;
	private double altoVentana, anchoVentana;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormularioProducto frame = new FormularioProducto();
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
	public FormularioProducto() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		altoVentana = 781;
		anchoVentana = 680;
//		altoVentana = (int)(ConstantesUtil.alto*0.8677);
//		anchoVentana = (int)(ConstantesUtil.ancho*0.4722);
		setBounds((int)(ConstantesUtil.ancho/2 - anchoVentana/2), (int)(ConstantesUtil.alto/2 - altoVentana/2), (int)anchoVentana, (int)altoVentana);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 102, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setModal(true);
		setUndecorated(true);
		Font font = ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 13) : new Font("Lucida Grande", Font.BOLD, 17);
		Font fontButton = ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.PLAIN, 13) : new Font("Lucida Grande", Font.PLAIN, 17);
		JPanel panelForm = new JPanel();
		panelForm.setBackground(new Color(0, 102, 153));
		panelForm.setBounds(6, 73, 425, 272);
		contentPane.add(panelForm);
		panelForm.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre *");
		lblNombre.setForeground(UIManager.getColor("Button.highlight"));
		lblNombre.setFont( new Font("Lucida Grande", Font.PLAIN, 17));
		lblNombre.setBounds(9, 6, 119, 26);
		panelForm.add(lblNombre);
		
		JLabel lblMarca = new JLabel("Marca *");
		lblMarca.setForeground(UIManager.getColor("Button.highlight"));
		lblMarca.setFont(font);
		lblMarca.setBounds(9, 50, 119, 26);
		panelForm.add(lblMarca);
		
		JLabel lblDescripcion = new JLabel("Descripción *");
		lblDescripcion.setForeground(UIManager.getColor("Button.highlight"));
		lblDescripcion.setFont(font);
		lblDescripcion.setBounds(9, 94, 119, 26);
		panelForm.add(lblDescripcion);
		
		JLabel lblPrecio = new JLabel("Precio *");
		lblPrecio.setForeground(UIManager.getColor("Button.highlight"));
		lblPrecio.setFont(font);
		lblPrecio.setBounds(9, 140, 119, 26);
		panelForm.add(lblPrecio);
		
		JLabel lblCantidad = new JLabel("Cantidad *");
		lblCantidad.setForeground(UIManager.getColor("Button.highlight"));
		lblCantidad.setFont(font);
		lblCantidad.setBounds(9, 184, 119, 26);
		panelForm.add(lblCantidad);
		
		JLabel lblCategora = new JLabel("Categoría *");
		lblCategora.setForeground(UIManager.getColor("Button.highlight"));
		lblCategora.setFont(font);
		lblCategora.setBounds(9, 228, 119, 26);
		panelForm.add(lblCategora);
		
		tfNombre = new JTextField();
		tfNombre.setFont(fontButton);
		tfNombre.setBounds(168, 6, 230, 32);
		panelForm.add(tfNombre);
		tfNombre.setColumns(10);
		
		tfMarca = new JTextField();
		tfMarca.setFont(fontButton);
		tfMarca.setBounds(168, 50, 230, 32);
		panelForm.add(tfMarca);
		tfMarca.setColumns(10);
		
		tfDescripcion = new JTextField();
		tfDescripcion.setFont(fontButton);
		tfDescripcion.setBounds(168, 94, 230, 32);
		panelForm.add(tfDescripcion);
		tfDescripcion.setColumns(10);
		
		tfPrecio = new JTextField();
		tfPrecio.setFont(fontButton);
		tfPrecio.setColumns(10);
		tfPrecio.setBounds(168, 140, 230, 32);
		panelForm.add(tfPrecio);
		
		tfCantidad = new JTextField();
		tfCantidad.setFont(fontButton);
		tfCantidad.setColumns(10);
		tfCantidad.setBounds(168, 184, 230, 32);
		panelForm.add(tfCantidad);
		
		cbCategoria = new JComboBox();
		cbCategoria.setBounds(168, 228, 230, 32);
		panelForm.add(cbCategoria);
		
		JPanel panelFoto = new JPanel();
		panelFoto.setBackground(new Color(0, 102, 153));
		panelFoto.setBounds(472, 114, 162, 196);
		contentPane.add(panelFoto);
		panelFoto.setLayout(null);
		
		lblFoto = new JLabel("FOTO");
		lblFoto.setForeground(UIManager.getColor("Button.highlight"));
		lblFoto.setHorizontalAlignment(SwingConstants.CENTER);
		Border border = LineBorder.createGrayLineBorder();
		lblFoto.setBorder(border);
		lblFoto.setBounds(6, 6, 150, 136);
		panelFoto.add(lblFoto);
		
		btnSeleccionar = new JButton(ConstantesUtil.seleccionar);
		btnSeleccionar.setFont(ConstantesUtil.ancho <= 1280 ? new Font("Lucida Grande", Font.PLAIN, 13) : new Font("Lucida Grande", Font.PLAIN, 15));
		btnSeleccionar.setBounds(6, 154, 147, 36);
		panelFoto.add(btnSeleccionar);
		
		JPanel panelBotonera = new JPanel();
		panelBotonera.setBounds(6, 357, 668, 108);
		contentPane.add(panelBotonera);
		panelBotonera.setLayout(null);
		
		btnNuevo = new JButton(ConstantesUtil.nuevo);
		btnNuevo.setIcon(new ImageIcon(FormularioProducto.class.getResource(ConstantesUtil.pathNuevo)));
		btnNuevo.setFont(fontButton);
		btnNuevo.setBounds(15, 6, 147, 96);
		panelBotonera.add(btnNuevo);
		
		btnGuardModif = new JButton(ConstantesUtil.guardar);
		btnGuardModif.setIcon(new ImageIcon(FormularioProducto.class.getResource(ConstantesUtil.pathGuardar)));
		btnGuardModif.setFont(fontButton);
		btnGuardModif.setBounds(179, 6, 147, 96);
		panelBotonera.add(btnGuardModif);
		
		btnCancelar = new JButton(ConstantesUtil.cancelar);
		btnCancelar.setIcon(new ImageIcon(FormularioProducto.class.getResource(ConstantesUtil.pathCancelar)));
		btnCancelar.setFont(fontButton);
		btnCancelar.setBounds(507, 6, 147, 96);
		panelBotonera.add(btnCancelar);
		
		construirTabla();
		btnEliminar = new JButton(ConstantesUtil.eliminar);
		btnEliminar.setIcon(new ImageIcon(FormularioProducto.class.getResource(ConstantesUtil.pathEliminar)));
		btnEliminar.setFont(fontButton);
		btnEliminar.setBounds(343, 6, 147, 96);
		panelBotonera.add(btnEliminar);
		
		JPanel panelSalir = new JPanel();
		panelSalir.setBounds(6, 704, 668, 71);
		contentPane.add(panelSalir);
		panelSalir.setLayout(null);
		
		btnSalir = new JButton(ConstantesUtil.salir);
		btnSalir.setIcon(new ImageIcon(FormularioProducto.class.getResource(ConstantesUtil.pathSalir)));
		btnSalir.setFont(ConstantesUtil.ancho <= 1280 ? new Font("Lucida Grande", Font.PLAIN, 13) : new Font("Lucida Grande", Font.PLAIN, 15));
		btnSalir.setBounds(506, 6, 143, 61);		
		panelSalir.add(btnSalir);
		
		JPanel panelTitulo = new JPanel();
		panelTitulo.setBounds(6, 6, 668, 66);
		contentPane.add(panelTitulo);
		panelTitulo.setLayout(null);
		
		JLabel lblFormularioDeProductos = new JLabel(ConstantesUtil.formularioProductos);
		lblFormularioDeProductos.setBounds(137, 17, 415, 32);
		lblFormularioDeProductos.setHorizontalAlignment(SwingConstants.CENTER);
		lblFormularioDeProductos.setFont(new Font("Lucida Grande", Font.BOLD, 26));
		panelTitulo.add(lblFormularioDeProductos);
		
		btnNuevo.addActionListener(this);
		btnGuardModif.addActionListener(this);
		btnCancelar.addActionListener(this);
		btnEliminar.addActionListener(this);
		btnSalir.addActionListener(this);
		btnSeleccionar.addActionListener(this);	
		
		estadoInicial();
	}
	
	private void estadoInicial() {
		
		EnableDisableForm(false);		
		btnEliminar.setEnabled(false);
		btnGuardModif.setEnabled(false);
		btnGuardModif.setText(ConstantesUtil.guardar);
		btnSeleccionar.setEnabled(false);
		btnSeleccionar.setFocusable(false);
		btnNuevo.setEnabled(true);
		btnNuevo.requestFocus();
		
	}

	private void cargarComboCategoria() {
				
		listaCategorias = service.cargarComboCategoria();		
		if(listaCategorias != null){
			listaCategorias.forEach((k,v)->cbCategoria.addItem(v));
		}		
	}

	private void construirTabla() {
		
		panelTabla = new JPanel();
		panelTabla.setBounds(6, 471, 668, 205);
		panelTabla.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 668, 205);
		
		
		String cabecera[] = {"IdProducto","Nombre","Marca","Descripción","Categoria","Precio","Cantidad", "ruta"};	
		String datos[][] = service.rellenaTablaProducto();
		DefaultTableModel model = new DefaultTableModel(datos, cabecera);
		model.removeRow(0);
		table = new JTable(model);
	
		table.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		table.setBounds(277, 60, 426, 138);
		JTableHeader th = table.getTableHeader();
		th.setBackground(Color.GRAY);
		Font fuente = ConstantesUtil.ancho <= 1280 ? new Font("Lucida Grande", Font.PLAIN, 13) : new Font("Lucida Grande", Font.PLAIN, 17);
		th.setFont(fuente);
		th.setForeground(UIManager.getColor("Button.highlight"));
		table.setTableHeader(th);
		scrollPane.setViewportView(table);
		
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
		table.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(3).setMaxWidth(0);
		table.getColumnModel().getColumn(3).setMinWidth(0);
		table.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(0);
		table.getTableHeader().getColumnModel().getColumn(3).setMinWidth(0);
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
		    		  EnableDisableForm(true);
			    	  btnEliminar.setEnabled(true);
			    	  modeloTabla = table;
			    	  clearForm();
			    	  fillForm();
			    	  cargarComboCategoria();			    	 
			    	  Iterator it = listaCategorias.values().iterator();			    	  
			    	  int i=0;
			    	  while (it.hasNext()) {
			    		  String c = categoria;
			    		  String lis = (String) it.next();
						if(lis.equals(categoria)){
							cbCategoria.setSelectedIndex(i);
						}						
						i++;
					}
			    	  btnNuevo.setEnabled(false);
			    	  btnGuardModif.setEnabled(true);
			    	  btnSeleccionar.setEnabled(true);			    	  
			    	  btnGuardModif.setText(ConstantesUtil.modificar); 
 
		    	  }
		      }
		   });
		panelTabla.add(scrollPane);
		contentPane.add(panelTabla);
	}

	private void fillForm() {
		tfNombre.setText((String) modeloTabla.getValueAt(filasSeleccionadas, 1));
  	  	tfMarca.setText((String)modeloTabla.getValueAt(filasSeleccionadas, 2));
  	  	tfDescripcion.setText((String) modeloTabla.getValueAt(filasSeleccionadas, 3));
  	  	tfPrecio.setText((String)modeloTabla.getValueAt(filasSeleccionadas, 5));
  	  	tfCantidad.setText((String)modeloTabla.getValueAt(filasSeleccionadas, 6));
  	  	categoria = (String) modeloTabla.getValueAt(filasSeleccionadas, 4);
  	  	try{
  	  		ImageIcon icon = new ImageIcon((String)modeloTabla.getValueAt(filasSeleccionadas, 7));
  	  		Image conversion = icon.getImage();
  	  		Image tam = conversion.getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_SMOOTH);
  	  		ImageIcon ico = new ImageIcon(tam);
	    	Icon icono = new ImageIcon(icon.getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_DEFAULT));
	    	lblFoto.setText(null);
	    	lblFoto.setIcon(ico);
	  	 }catch(Exception ex){
	  		 JOptionPane.showMessageDialog(null, "Error abriendo la imagen "+ ex);
	  	 } 
  	  
  	  	idProducto = (String)  modeloTabla.getValueAt(filasSeleccionadas, 0);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();
		
		
		if(source.getText().equals(ConstantesUtil.salir)){
			FormularioPrincipal.btnGestionProd.setEnabled(true);
			dispose();
		}else if(source.getText().equals(ConstantesUtil.nuevo)){
			cargarComboCategoria();
			if(cbCategoria.getItemCount() > 0){
				clearForm();
				cargarComboCategoria();
				EnableDisableForm(true);
				btnGuardModif.setEnabled(true);
				btnSeleccionar.setEnabled(true);
				btnSeleccionar.setFocusable(true);
				tfNombre.requestFocus();
			}else{
				JOptionPane.showMessageDialog(this, "Debes crear al menos una categoría antes de crear un producto");
			}
		}else if(source.getText().equals(ConstantesUtil.guardar)){			
			Producto producto = fillObject();			
			if(producto != null){
				service.insertarProducto(producto, categoria);
				clearForm();
				EnableDisableForm(false);
				btnSeleccionar.setFocusable(false);
				btnNuevo.requestFocus();
				btnGuardModif.setEnabled(false);
			}			
			
			
		}else if(source.getText().equals(ConstantesUtil.modificar)){
			if(idProducto != null && !idProducto.equals("")){
				Producto producto = fillObject();
				if(producto != null){
					producto.setIdProducto(Long.parseLong(idProducto));
					service.modificarProducto(producto, categoria);
					clearForm();
					estadoInicial();
				}
				
			}
		}else if(source.getText().equals(ConstantesUtil.eliminar)){
			service.eliminarProducto(idProducto);
			clearForm();
			estadoInicial();
		}else if(source.getText().equals(ConstantesUtil.cancelar)){
			clearForm();
			estadoInicial();
		}else if(source.getText().equals(ConstantesUtil.seleccionar)){
			int resultado;
			CargaFoto frameFoto = new CargaFoto();
			frameFoto.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			FileNameExtensionFilter filtro = new FileNameExtensionFilter("JPG y PNG","jpg","png");
			frameFoto.fchCargarFoto.setFileFilter(filtro);
			resultado = frameFoto.fchCargarFoto.showOpenDialog(null);
				
			if (JFileChooser.APPROVE_OPTION == resultado){
		        fichero = frameFoto.fchCargarFoto.getSelectedFile();
		        try{
		        	ImageIcon icon = new ImageIcon(fichero.getPath());
		  	  		Image conversion = icon.getImage();
		  	  		Image tam = conversion.getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_SMOOTH);
		  	  		ImageIcon ico = new ImageIcon(tam);
			    	lblFoto.setText(null);
			    	lblFoto.setIcon(ico);
		        }catch(Exception ex){
		        	JOptionPane.showMessageDialog(null, "Error abriendo la imagen "+ ex);
		        }
			}
			
		}
		contentPane.remove(panelTabla);
		construirTabla();		
	}
	
	private Producto fillObject(){
		Producto producto = null;
		if(validar()){	
			ruta = fichero != null ? fichero.getAbsolutePath() : "";
			categoria = cbCategoria.getSelectedItem().toString();
			producto = new Producto();
			producto.setNombre(tfNombre.getText());
			producto.setDescripcion(tfDescripcion.getText());
			producto.setMarca(tfMarca.getText());
			producto.setPrecio(pre);
			producto.setStock(Integer.valueOf(tfCantidad.getText()));
			producto.setImagen(ruta);
			
		}else{
			JOptionPane.showMessageDialog(this, "Debes rellenar correctamente los campos antes de crear un producto");
		}
		return producto;
	}

	private Boolean validar() {
		Boolean dev = Boolean.FALSE;
		
		if(!tfNombre.getText().equals("") && Validador.isAlfaNumericoConPunto(tfNombre.getText())){
			dev = true;
		}else{
			return false;
		}
		if(!tfMarca.getText().equals("") && Validador.isAlfaNumericoConPunto(tfMarca.getText())){
			dev = true;
		}else{
			return false;
		}
		if(!tfDescripcion.getText().equals("") && Validador.isAlfaNumericoConPunto(tfDescripcion.getText())){
			dev = true;
		}else{
			return false;
		}
		if(!tfPrecio.getText().equals("") && Validador.isDecimal(tfPrecio.getText())){
			precio = ConstantesUtil.obtenerNumero(tfPrecio.getText());
			pre = new BigDecimal(precio);
			dev = true;		
		}else{
			return false;
		}
		if(!tfCantidad.getText().equals("") && Validador.isNumero(tfCantidad.getText())){
			dev = true;
		}else{
			return false;
		}
		
		return dev;
	}
	private void EnableDisableForm(boolean siNo) {
			tfNombre.setEnabled(siNo);
			tfDescripcion.setEnabled(siNo);
			tfMarca.setEnabled(siNo);
			tfPrecio.setEnabled(siNo);
			tfCantidad.setEnabled(siNo);
			cbCategoria.setEnabled(siNo);
	}
	
	private void clearForm() {
		tfNombre.setText("");
		tfDescripcion.setText("");
		tfMarca.setText("");
		tfPrecio.setText("");
		tfCantidad.setText("");
		cbCategoria.removeAllItems();
		idProducto = "";
		ruta = "";
		lblFoto.setText(null);
		lblFoto.setIcon(null);
	}
}
