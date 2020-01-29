package AplicacionGestionTPV;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import PantallaVentas.FacturaConCliente;
import controladorService.GestionService;
import controladorService.GestionServiceImpl;
import modelo.Cliente;
import util.ConstantesUtil;
import util.Validador;
/**
 * 
 * @author Two hands Technology
 *
 */
public class FormularioCliente extends JDialog implements ActionListener{

	private JPanel contentPane,panelTabla;
	private JTable table, modeloTabla;
	private JButton btnNuevo, btnGuardModif, btnCancelar, btnEliminar, btnSalir;
	private JTextField tfNombreEmpresa,tfDireccion,tfEmail,tfTelefono,tfCifNif;
	private JScrollPane scrollPane;
	private int filasSeleccionadas;
	private String idCliente;
	private GestionService service = new GestionServiceImpl();
	private double  altoVentana, anchoVentana, ancho, alto;
	private FacturaConCliente facturaConCliente;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormularioCliente frame = new FormularioCliente(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param facturaConCliente 
	 */
	public FormularioCliente(FacturaConCliente facturaConCliente) {
		this.facturaConCliente = facturaConCliente;
		altoVentana = 772;
		anchoVentana = 688;
		ancho = ConstantesUtil.ancho;
		alto = ConstantesUtil.alto;
		setLocationRelativeTo(null); 
		setResizable(false);
		setBounds((int)ancho/2 - (int)anchoVentana/2, (int)alto/2 - (int)altoVentana/2, 688, 772);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 102, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setModal(true);
		Font font = ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 13) : new Font("Lucida Grande", Font.BOLD, 17);
		Font fontButton = ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.PLAIN, 13) : new Font("Lucida Grande", Font.PLAIN, 17);
		Panel panel = new Panel();
		panel.setBounds(123, 91, 423, 214);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 2, -195, 0));
		
		JLabel lblNombreEmpresa = new JLabel("Empresa *");
		lblNombreEmpresa.setForeground(Color.WHITE);
		lblNombreEmpresa.setFont(font);
		panel.add(lblNombreEmpresa);
		
		tfNombreEmpresa = new JTextField();
		tfNombreEmpresa.setFont(fontButton);
		panel.add(tfNombreEmpresa);
		tfNombreEmpresa.setText("Nombre empresa");
		tfNombreEmpresa.setColumns(10);
		
		JLabel lblTelefono = new JLabel("Teléfono");
		lblTelefono.setForeground(Color.WHITE);
		lblTelefono.setFont(font);
		panel.add(lblTelefono);
		
		tfTelefono = new JTextField();
		tfTelefono.setFont(fontButton);
		panel.add(tfTelefono);
		tfTelefono.setText("Teléfono");
		tfTelefono.setColumns(10);
		
		JLabel lblDninif = new JLabel("CIF/NIF *");
		lblDninif.setForeground(Color.WHITE);
		lblDninif.setFont(font);
		panel.add(lblDninif);
		
		tfCifNif = new JTextField();
		tfCifNif.setFont(fontButton);
		tfCifNif.setText("CIF / NIF");
		panel.add(tfCifNif);
		tfCifNif.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(font);
		panel.add(lblEmail);
		
		tfEmail = new JTextField();
		tfEmail.setFont(fontButton);
		panel.add(tfEmail);
		tfEmail.setText("Email");
		tfEmail.setColumns(10);
		
		JLabel lblDireccion = new JLabel("Dirección *");
		lblDireccion.setForeground(Color.WHITE);
		lblDireccion.setFont(font);
		panel.add(lblDireccion);
		
		tfDireccion = new JTextField();
		tfDireccion.setFont(fontButton);
		panel.add(tfDireccion);
		tfDireccion.setText("Dirección");
		tfDireccion.setColumns(10);
		
		JPanel panelTitulo = new JPanel();
		panelTitulo.setBounds(10, 0, 668, 66);
		contentPane.add(panelTitulo);
		panelTitulo.setLayout(null);
		
		JLabel lblFormularioClientes = new JLabel(ConstantesUtil.formularioClientes);
		lblFormularioClientes.setBounds(178, 16, 316, 32);
		lblFormularioClientes.setFont(new Font("Lucida Grande", Font.BOLD, 26));
		panelTitulo.add(lblFormularioClientes);
		
		JPanel panelSalir = new JPanel();
		panelSalir.setBounds(10, 673, 668, 71);
		contentPane.add(panelSalir);
		panelSalir.setLayout(null);
		
		btnSalir = new JButton(ConstantesUtil.salir);
		btnSalir.setIcon(new ImageIcon(FormularioEmpleados.class.getResource(ConstantesUtil.pathSalir)));
		btnSalir.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnSalir.setBounds(508, 6, 143, 61);
		panelSalir.add(btnSalir);
		
		JPanel panelBotonera = new JPanel();
		panelBotonera.setLayout(null);
		panelBotonera.setBounds(10, 323, 668, 108);
		contentPane.add(panelBotonera);
		
		btnNuevo = new JButton(ConstantesUtil.nuevo);
		btnNuevo.setIcon(new ImageIcon(FormularioEmpleados.class.getResource(ConstantesUtil.pathNuevo)));
		btnNuevo.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		btnNuevo.setEnabled(true);
		btnNuevo.setBounds(15, 6, 147, 96);
		panelBotonera.add(btnNuevo);
		
		btnGuardModif = new JButton(ConstantesUtil.guardar);
		btnGuardModif.setIcon(new ImageIcon(FormularioEmpleados.class.getResource(ConstantesUtil.pathGuardar)));
		btnGuardModif.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		btnGuardModif.setEnabled(false);
		btnGuardModif.setBounds(179, 6, 147, 96);
		panelBotonera.add(btnGuardModif);
		
		btnCancelar = new JButton(ConstantesUtil.cancelar);
		btnCancelar.setIcon(new ImageIcon(FormularioEmpleados.class.getResource(ConstantesUtil.pathCancelar)));
		btnCancelar.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		btnCancelar.setBounds(507, 6, 147, 96);
		panelBotonera.add(btnCancelar);
		
		btnEliminar = new JButton(ConstantesUtil.eliminar);
		btnEliminar.setIcon(new ImageIcon(FormularioEmpleados.class.getResource(ConstantesUtil.pathEliminar)));
		btnEliminar.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		btnEliminar.setEnabled(false);
		btnEliminar.setBounds(343, 6, 147, 96);
		panelBotonera.add(btnEliminar);
		
		btnNuevo.addActionListener(this);
		btnGuardModif.addActionListener(this);
		btnCancelar.addActionListener(this);
		btnEliminar.addActionListener(this);
		btnSalir.addActionListener(this);
		
		construirTabla();
		
		estadoInicial();
	}
	
	private void estadoInicial() {
		
		EnableDisableForm(false);		
		btnEliminar.setEnabled(false);
		btnGuardModif.setEnabled(false);
		btnGuardModif.setText(ConstantesUtil.guardar);
		btnNuevo.setEnabled(true);
		btnNuevo.requestFocus();
		
	}
	private void EnableDisableForm(boolean siNo) {
		tfNombreEmpresa.setEnabled(siNo);
		tfCifNif.setEnabled(siNo);
		tfEmail.setEnabled(siNo);
		tfDireccion.setEnabled(siNo);
		tfTelefono.setEnabled(siNo);
	}	
	private void clearForm() {
		tfNombreEmpresa.setText("");
		tfCifNif.setText("");
		tfEmail.setText("");
		tfDireccion.setText("");
		tfTelefono.setText("");
	}

	private void construirTabla() {
		
		panelTabla = new JPanel();
		panelTabla.setBounds(10, 443, 668, 205);
		panelTabla.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 668, 205);
		
		
		String cabecera[] = {"IdCliente","Empresa","CIF/NIF","Teléfono","Email","Dirección"};	
		String datos[][] = service.rellenarTablaCliente();
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
		contentPane.add(panelTabla);
	}
	
	private void fillForm() {
		tfNombreEmpresa.setText((String) modeloTabla.getValueAt(filasSeleccionadas, 1));
  	  	tfCifNif.setText((String) modeloTabla.getValueAt(filasSeleccionadas, 2));
  	  	tfTelefono.setText((String) modeloTabla.getValueAt(filasSeleccionadas, 3));
    	tfEmail.setText((String)modeloTabla.getValueAt(filasSeleccionadas, 4));
  		tfDireccion.setText((String)modeloTabla.getValueAt(filasSeleccionadas, 5));
  	  
  		idCliente = (String)  modeloTabla.getValueAt(filasSeleccionadas, 0);
	}
	
	private Cliente fillObject(){
		Cliente cliente = null;
		if(validar()){	
			cliente = new Cliente();
			cliente.setNombre(tfNombreEmpresa.getText());
			cliente.setDniNif(tfCifNif.getText());
			cliente.setEmail(tfEmail.getText());
			cliente.setTelefono(tfTelefono.getText());
			cliente.setDireccion(tfDireccion.getText());
			
		}else{
			JOptionPane.showMessageDialog(this, "Debes rellenar correctamente los campos antes de crear un producto");
		}
		return cliente;
	}
	
	private Boolean validar() {
		Boolean dev = Boolean.FALSE;
		
		if(!tfNombreEmpresa.getText().equals("") && Validador.isAlfaNumericoConPunto(tfNombreEmpresa.getText())){
			dev = true;
		}else{
			return false;
		}
		if(!tfCifNif.getText().equals("") && Validador.isAlfaNumerico(tfCifNif.getText())){
			dev = true;
		}else{
			return false;
		}
		if(!tfEmail.getText().equals("")){
			if(Validador.isEmail(tfEmail.getText())){
				dev = true;	
			}else{
				return false;	
			}
		}
		if(!tfTelefono.getText().equals("")){
			if(Validador.isNumero(tfTelefono.getText())){
				dev = true;
			}else{
				return false;
			}
		}
		if(!tfDireccion.getText().equals("") && Validador.isDireccion(tfDireccion.getText())){
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
//			FormularioPrincipal.btnGestionProd.setEnabled(true);
			if(facturaConCliente != null){
				facturaConCliente.actualizarTableClientes();
			}
			dispose();
		}else if(source.getText().equals(ConstantesUtil.nuevo)){
			clearForm();
			EnableDisableForm(true);
			btnGuardModif.setEnabled(true);
			tfNombreEmpresa.requestFocus();			
		}else if(source.getText().equals(ConstantesUtil.guardar)){			
			Cliente cliente = fillObject();			
			if(cliente != null){
				service.insertarCliente(cliente);
				clearForm();
				EnableDisableForm(false);
				btnNuevo.requestFocus();
				btnGuardModif.setEnabled(false);
			}			
			
		}else if(source.getText().equals(ConstantesUtil.modificar)){
			if(idCliente != null && !idCliente.equals("")){
				Cliente cliente = fillObject();	
				if(cliente != null){
					cliente.setIdCliente(Long.parseLong(idCliente));
					service.modificarCliente(cliente);
					clearForm();
					estadoInicial();
				}
				
			}
		}else if(source.getText().equals(ConstantesUtil.eliminar)){
			service.eliminarCliente(idCliente);
			clearForm();
			estadoInicial();
		}else if(source.getText().equals(ConstantesUtil.cancelar)){
			clearForm();
			estadoInicial();
		}
		contentPane.remove(panelTabla);
		construirTabla();
	}
}
