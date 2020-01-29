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

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
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
import modelo.Categoria;
import util.ConstantesUtil;
import util.Validador;
/**
 * 
 * @author Two hands Technology
 *
 */
public class FormularioCategoria extends JDialog implements ActionListener{

	private JPanel contentPane, panelTabla;
	private JTable table, modeloTabla;
	private JTextField tfNombre, tfDescripcion;
	private JLabel lblNombre, lblDescripcion, lblFoto, lblFormularioDeCategoras;
	private JButton btnSeleccionar, btnNuevo, btnGuardModif, btnCancelar, btnEliminar, btnSalir;
	private JScrollPane scrollPane;
	private GestionService service = new GestionServiceImpl();
	private int filasSeleccionadas;
	private String idCategoria, ruta;
	private File fichero;
	private String datos[][];
	private double altoVentana, anchoVentana;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormularioCategoria frame = new FormularioCategoria();
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
	public FormularioCategoria() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		altoVentana = 595;
		anchoVentana = 680;
		setBounds((int)ConstantesUtil.ancho/2 - (int)anchoVentana/2, (int)ConstantesUtil.alto/2 - (int)altoVentana/2, 680, 595);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 102, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setModal(true);
		setUndecorated(true);
		Font font = ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 13) : new Font("Lucida Grande", Font.BOLD, 17);
		Font fontButton = ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.PLAIN, 13) : new Font("Lucida Grande", Font.PLAIN, 17);
		
		JPanel panelForm = new JPanel();
		panelForm.setBackground(new Color(0, 102, 153));
		panelForm.setBounds(6, 84, 668, 82);
		contentPane.add(panelForm);
		panelForm.setLayout(null);
		
		lblNombre = new JLabel("Nombre *");
		lblNombre.setForeground(UIManager.getColor("Button.highlight"));
		lblNombre.setFont(font);
		lblNombre.setBounds(148, 6, 119, 26);
		panelForm.add(lblNombre);
		
		tfNombre = new JTextField();
		tfNombre.setFont(fontButton);
		tfNombre.setEnabled(false);
		tfNombre.setColumns(10);
		tfNombre.setBounds(307, 3, 230, 32);
		panelForm.add(tfNombre);
		
		lblDescripcion = new JLabel("Descripción *");
		lblDescripcion.setForeground(UIManager.getColor("Button.highlight"));
		lblDescripcion.setFont(font);
		lblDescripcion.setBounds(148, 44, 119, 26);
		panelForm.add(lblDescripcion);
		
		tfDescripcion = new JTextField();
		tfDescripcion.setFont(fontButton);
		tfDescripcion.setEnabled(false);
		tfDescripcion.setColumns(10);
		tfDescripcion.setBounds(307, 41, 230, 32);
		panelForm.add(tfDescripcion);
		
		JPanel panelFoto = new JPanel();
		panelFoto.setLayout(null);
		panelFoto.setBackground(new Color(0, 102, 153));
		panelFoto.setBounds(465, 167, 162, 196);
		contentPane.add(panelFoto);
		
		lblFoto = new JLabel(ConstantesUtil.foto);
		lblFoto.setForeground(UIManager.getColor("Button.highlight"));
		lblFoto.setHorizontalAlignment(SwingConstants.CENTER);
		Border border = LineBorder.createGrayLineBorder();
		lblFoto.setBorder(border);
		lblFoto.setBounds(6, 6, 150, 136);
		panelFoto.add(lblFoto);
		
		btnSeleccionar = new JButton(ConstantesUtil.seleccionar);
		btnSeleccionar.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnSeleccionar.setFocusable(false);
		btnSeleccionar.setEnabled(false);
		btnSeleccionar.setBounds(6, 154, 150, 36);
		panelFoto.add(btnSeleccionar);
		
		JPanel panelBotonera = new JPanel();
		panelBotonera.setLayout(null);
		panelBotonera.setBounds(6, 375, 668, 108);
		contentPane.add(panelBotonera);
		
		btnNuevo = new JButton(ConstantesUtil.nueva);
		btnNuevo.setIcon(new ImageIcon(FormularioCategoria.class.getResource(ConstantesUtil.pathNuevo)));
		btnNuevo.setFont(fontButton);
		btnNuevo.setEnabled(true);
		btnNuevo.setBounds(15, 6, 147, 96);
		panelBotonera.add(btnNuevo);
		
		btnGuardModif = new JButton(ConstantesUtil.guardar);
		btnGuardModif.setIcon(new ImageIcon(FormularioCategoria.class.getResource(ConstantesUtil.pathGuardar)));
		btnGuardModif.setFont(fontButton);
		btnGuardModif.setEnabled(false);
		btnGuardModif.setBounds(179, 6, 147, 96);
		panelBotonera.add(btnGuardModif);
		
		btnCancelar = new JButton(ConstantesUtil.cancelar);
		btnCancelar.setIcon(new ImageIcon(FormularioEmpleados.class.getResource(ConstantesUtil.pathCancelar)));
		btnCancelar.setFont(fontButton);
		btnCancelar.setBounds(507, 6, 147, 96);
		panelBotonera.add(btnCancelar);
		
		btnEliminar = new JButton(ConstantesUtil.eliminar);
		btnEliminar.setIcon(new ImageIcon(FormularioCategoria.class.getResource(ConstantesUtil.pathEliminar)));
		btnEliminar.setFont(fontButton);
		btnEliminar.setEnabled(false);
		btnEliminar.setBounds(343, 6, 147, 96);
		panelBotonera.add(btnEliminar);
		
		JPanel panelTitulo = new JPanel();
		panelTitulo.setLayout(null);
		panelTitulo.setBounds(6, 6, 668, 66);
		contentPane.add(panelTitulo);
		
		lblFormularioDeCategoras = new JLabel(ConstantesUtil.formularioCategorias);
		lblFormularioDeCategoras.setHorizontalAlignment(SwingConstants.CENTER);
		lblFormularioDeCategoras.setFont(new Font("Lucida Grande", Font.BOLD, 26));
		lblFormularioDeCategoras.setBounds(137, 17, 415, 32);
		panelTitulo.add(lblFormularioDeCategoras);
		
		JPanel panelSalir = new JPanel();
		panelSalir.setLayout(null);
		panelSalir.setBounds(6, 518, 668, 71);
		contentPane.add(panelSalir);
		
		btnSalir = new JButton(ConstantesUtil.salir);
		btnSalir.setIcon(new ImageIcon(FormularioCategoria.class.getResource(ConstantesUtil.pathSalir)));
		btnSalir.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnSalir.setBounds(507, 6, 143, 61);
		panelSalir.add(btnSalir);
		construirTabla();
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

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();
		
		
		if(source.getText().equals(ConstantesUtil.salir)){
			FormularioPrincipal.btnGestionCategoria.setEnabled(true);
			dispose();
		}else if(source.getText().equals(ConstantesUtil.nueva)){
			clearForm();
			EnableDisableForm(true);
			btnGuardModif.setEnabled(true);
			btnSeleccionar.setEnabled(true);
			btnSeleccionar.setFocusable(true);
			tfNombre.requestFocus();			
		}else if(source.getText().equals(ConstantesUtil.guardar)){			
			Categoria categoria = fillObject();			
			if(categoria != null){
				service.insertarCategoria(categoria);
				clearForm();
				EnableDisableForm(false);
				btnSeleccionar.setFocusable(false);
				btnNuevo.requestFocus();
				btnGuardModif.setEnabled(false);
			}			
			
		}else if(source.getText().equals(ConstantesUtil.modificar)){
			if(idCategoria != null && !idCategoria.equals("")){
				Categoria categoria = fillObject();
				if(categoria != null){
					categoria.setIdCategoria(Long.parseLong(idCategoria));
					service.modificarCategoria(categoria);
					clearForm();
					estadoInicial();
				}
				
			}
		}else if(source.getText().equals(ConstantesUtil.eliminar)){
			service.eliminarCategoria(idCategoria);
			clearForm();
			estadoInicial();
		}else if(source.getText().equals(ConstantesUtil.cancelar)){
			clearForm();
			estadoInicial();
		}else if(source.getText().equals(ConstantesUtil.seleccionar)){
			int resultado;
			CargaFoto frameFoto = new CargaFoto();
			FileNameExtensionFilter filtro = new FileNameExtensionFilter("JPG y PNG","jpg","png");
			frameFoto.fchCargarFoto.setFileFilter(filtro);
			resultado = frameFoto.fchCargarFoto.showOpenDialog(null);
				
			if (JFileChooser.APPROVE_OPTION == resultado){
		        fichero = frameFoto.fchCargarFoto.getSelectedFile();
		        try{
		        	ImageIcon icon = new ImageIcon(fichero.toString());
		        	Icon icono = new ImageIcon(icon.getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_DEFAULT));
		        	lblFoto.setText(null);
		           	lblFoto.setIcon(icono);
		        }catch(Exception ex){
		        	JOptionPane.showMessageDialog(null, "Error abriendo la imagen "+ ex);
		        }
			}
			
		}
		contentPane.remove(panelTabla);
		construirTabla();
		
	}
	private void construirTabla() {
		
		panelTabla = new JPanel();
		panelTabla.setLayout(null);
		panelTabla.setBounds(6, 172, 412, 171);
		contentPane.add(panelTabla);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 412, 171);
		panelTabla.add(scrollPane);
		
		String cabecera[] = {"IdCategoria","Nombre","Descripción","ruta"};	
		String datos[][] = service.rellenaTablaCategoria();
		DefaultTableModel model = new DefaultTableModel(datos, cabecera);
		model.removeRow(0);
		table = new JTable(model);
	
		table.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		table.setBounds(277, 60, 426, 138);
		JTableHeader th = table.getTableHeader();
		th.setBackground(Color.GRAY);
		Font fuente = ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.PLAIN, 13) : new Font("Lucida Grande", Font.PLAIN, 17);
		th.setFont(fuente);
		th.setForeground(UIManager.getColor("Button.highlight"));
		table.setTableHeader(th);
		
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
		table.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(3).setMaxWidth(0);
		table.getColumnModel().getColumn(3).setMinWidth(0);
		table.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(0);
		table.getTableHeader().getColumnModel().getColumn(3).setMinWidth(0);
		scrollPane.setViewportView(table);
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
			    	  btnSeleccionar.setEnabled(true);		    	  
			    	  btnGuardModif.setText(ConstantesUtil.modificar);		
		    	  }
		      }
		   });
	}
	
	private void fillForm() {
		tfNombre.setText((String) modeloTabla.getValueAt(filasSeleccionadas, 1));
		tfDescripcion.setText((String) modeloTabla.getValueAt(filasSeleccionadas, 2));
		try{
			ImageIcon icon = new ImageIcon((String)modeloTabla.getValueAt(filasSeleccionadas, 3));
  	  		Image conversion = icon.getImage();
  	  		Image tam = conversion.getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_SMOOTH);
  	  		ImageIcon ico = new ImageIcon(tam);
	    	lblFoto.setText(null);
	    	lblFoto.setIcon(ico);
		}catch(Exception ex){
			JOptionPane.showMessageDialog(null, "Error abriendo la imagen "+ ex);
		}			    	  
  	  idCategoria = (String)modeloTabla.getValueAt(filasSeleccionadas,0);
	}
	
	private void EnableDisableForm(boolean siNo) {
		tfNombre.setEnabled(siNo);
		tfDescripcion.setEnabled(siNo);
	}
	private Categoria fillObject(){
		Categoria categoria = null;
		if(validar()){		
			ruta = fichero != null ? fichero.getAbsolutePath() : null;
			categoria = new Categoria();
			categoria.setNombre(tfNombre.getText());
			categoria.setDescripcion(tfDescripcion.getText());
			categoria.setImagen(ruta);
			
		}else{
			JOptionPane.showMessageDialog(this, "Debes rellenar correctamente los campos antes de crear una categoría");
		}
		return categoria;
	}
	private Boolean validar() {
		Boolean dev = Boolean.FALSE;
		
		if(!tfNombre.getText().equals("") && Validador.isAlfaNumericoConPunto(tfNombre.getText())){
			dev = true;
		}else{
			return false;
		}
		if(!tfDescripcion.getText().equals("") && Validador.isAlfaNumericoConPunto(tfDescripcion.getText())){
			dev = true;
		}else{
			return false;
		}
		
		return dev;
	}
	private void clearForm() {
		tfNombre.setText("");
		tfDescripcion.setText("");
		idCategoria = "";
		ruta = "";
		lblFoto.setText(null);
		lblFoto.setIcon(null);
		
	}
}
