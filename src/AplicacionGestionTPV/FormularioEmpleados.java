package AplicacionGestionTPV;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import controladorService.GestionService;
import controladorService.GestionServiceImpl;
import modelo.Empleado;
import util.ConstantesUtil;
import util.Validador;
/**
 * 
 * @author Two hands Technology
 *
 */
public class FormularioEmpleados extends JDialog implements ActionListener{

	private JPanel contentPane,panelTabla;
	private JTable table,modeloTabla;
	private JTextField tfNombre,tfDni,tfEmail,tfTelefono;
	private GestionService service = new GestionServiceImpl();
	private String nombre, cargo, dni, email, telefono, idEmpleado, password;
	private char[] pass;
	private JPasswordField passwordField;
	private JButton btnNuevo, btnGuardModif, btnCancelar, btnEliminar, btnSeleccionar, btnSalir;
	private JScrollPane scrollPane;
	private int filasSeleccionadas;
	private double  altoVentana, anchoVentana;
	private JComboBox cbCargo;

	/**
	 * Create the frame.
	 */
	public FormularioEmpleados() {
		altoVentana = 604;
		anchoVentana = 708;
		setBounds((int)ConstantesUtil.ancho/2 - (int)anchoVentana/2, (int)ConstantesUtil.alto/2 - (int)altoVentana/2, (int)anchoVentana,(int) altoVentana);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 102, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		contentPane.setLayout(null);
		setModal(true);
		setUndecorated(true);
		JPanel panelBotonera = new JPanel();
		panelBotonera.setBounds(6, 219, 696, 108);
		panelBotonera.setLayout(null);
		contentPane.add(panelBotonera);
		Font font = ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 13) : new Font("Lucida Grande", Font.BOLD, 17);
		Font fontButton = ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.PLAIN, 13) : new Font("Lucida Grande", Font.PLAIN, 17);
		
		btnNuevo = new JButton(ConstantesUtil.nuevo);
		btnNuevo.setIcon(new ImageIcon(FormularioEmpleados.class.getResource(ConstantesUtil.pathNuevo)));
		btnNuevo.setFont(fontButton);
		btnNuevo.setEnabled(true);
		btnNuevo.setBounds(21, 6, 147, 96);
		panelBotonera.add(btnNuevo);
		
		btnGuardModif = new JButton(ConstantesUtil.guardar);
		btnGuardModif.setIcon(new ImageIcon(FormularioEmpleados.class.getResource(ConstantesUtil.pathGuardar)));
		btnGuardModif.setFont(fontButton);
		btnGuardModif.setEnabled(false);
		btnGuardModif.setBounds(185, 6, 147, 96);
		panelBotonera.add(btnGuardModif);
		
		btnCancelar = new JButton(ConstantesUtil.cancelar);
		btnCancelar.setIcon(new ImageIcon(FormularioEmpleados.class.getResource(ConstantesUtil.pathCancelar)));
		btnCancelar.setFont(fontButton);
		btnCancelar.setBounds(528, 6, 147, 96);
		panelBotonera.add(btnCancelar);
		
		btnEliminar = new JButton(ConstantesUtil.eliminar);
		btnEliminar.setIcon(new ImageIcon(FormularioEmpleados.class.getResource(ConstantesUtil.pathEliminar)));
		btnEliminar.setFont(fontButton);
		btnEliminar.setEnabled(false);
		btnEliminar.setBounds(358, 6, 147, 96);
		panelBotonera.add(btnEliminar);
		
		JPanel panelTitulo = new JPanel();
		panelTitulo.setBounds(6, 6, 696, 66);
		panelTitulo.setLayout(null);
		contentPane.add(panelTitulo);
		
		JLabel lblFormularioDeEmpleados = new JLabel(ConstantesUtil.formularioEmpleados);
		lblFormularioDeEmpleados.setHorizontalAlignment(SwingConstants.CENTER);
		lblFormularioDeEmpleados.setFont(new Font("Lucida Grande", Font.BOLD, 26));
		lblFormularioDeEmpleados.setBounds(145, 16, 415, 32);
		panelTitulo.add(lblFormularioDeEmpleados);
		
		JPanel panelSalir = new JPanel();
		panelSalir.setBounds(6, 526, 696, 72);
		panelSalir.setLayout(null);
		contentPane.add(panelSalir);
		
		btnSalir = new JButton(ConstantesUtil.salir);
		btnSalir.setIcon(new ImageIcon(FormularioEmpleados.class.getResource(ConstantesUtil.pathSalir)));
		btnSalir.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnSalir.setBounds(534, 6, 143, 61);
		panelSalir.add(btnSalir);
		
		
		
		
		
		
		
		JPanel panelForm = new JPanel();
		panelForm.setBounds(6, 79, 696, 134);
		panelForm.setLayout(null);
		panelForm.setBackground(new Color(0, 102, 153));
		contentPane.add(panelForm);
		
		JLabel lblNombre = new JLabel("Nombre *");
		lblNombre.setForeground(UIManager.getColor("Button.highlight"));
		lblNombre.setFont(font);
		lblNombre.setBounds(9, 6, 93, 26);
		panelForm.add(lblNombre);
		
		JLabel lblCargo = new JLabel("Cargo *");
		lblCargo.setForeground(UIManager.getColor("Button.highlight"));
		lblCargo.setFont(font);
		lblCargo.setBounds(366, 50, 82, 26);
		panelForm.add(lblCargo);
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setForeground(UIManager.getColor("Button.highlight"));
		lblDni.setFont(font);
		lblDni.setBounds(366, 94, 82, 26);
		panelForm.add(lblDni);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(UIManager.getColor("Button.highlight"));
		lblEmail.setFont(font);
		lblEmail.setBounds(366, 6, 76, 26);
		panelForm.add(lblEmail);
		
		JLabel lblTelefono = new JLabel("Teléfono");
		lblTelefono.setForeground(UIManager.getColor("Button.highlight"));
		lblTelefono.setFont(font);
		lblTelefono.setBounds(6, 50, 96, 26);
		panelForm.add(lblTelefono);
		
		JLabel lblPassword = new JLabel("Contraseña *");
		lblPassword.setForeground(UIManager.getColor("Button.highlight"));
		lblPassword.setFont(font);
		lblPassword.setBounds(9, 94, 119, 26);
		panelForm.add(lblPassword);
		
		tfNombre = new JTextField();
		tfNombre.setFont(fontButton);
		tfNombre.setEnabled(false);
		tfNombre.setColumns(10);
		tfNombre.setBounds(124, 4, 230, 32);
		panelForm.add(tfNombre);
		
//		tfCargo = new JTextField();
//		tfCargo.setFont(fontButton);
//		tfCargo.setEnabled(false);
//		tfCargo.setColumns(10);
//		tfCargo.setBounds(92, 47, 230, 32);
//		panelForm.add(tfCargo);
		cbCargo = new JComboBox();
		cbCargo.setBounds(462, 50, 230, 32);
		panelForm.add(cbCargo);
		
		tfDni = new JTextField();
		tfDni.setFont(fontButton);
		tfDni.setEnabled(false);
		tfDni.setColumns(10);
		tfDni.setBounds(460, 92, 230, 32);
		panelForm.add(tfDni);
		
		tfEmail = new JTextField();
		tfEmail.setFont(fontButton);
		tfEmail.setEnabled(false);
		tfEmail.setColumns(10);
		tfEmail.setBounds(460, 4, 230, 32);
		panelForm.add(tfEmail);
		
		tfTelefono = new JTextField();
		tfTelefono.setFont(fontButton);
		tfTelefono.setEnabled(false);
		tfTelefono.setColumns(10);
		tfTelefono.setBounds(124, 48, 230, 32);
		panelForm.add(tfTelefono);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(124, 93, 230, 32);
		panelForm.add(passwordField);
		
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
		passwordField.setEnabled(false);
		cbCargo.addItem(ConstantesUtil.administrador);
		cbCargo.addItem(ConstantesUtil.empleado);
		
	}
	
	private void construirTabla() {
		
		panelTabla = new JPanel();
		panelTabla.setBounds(6, 335, 696, 157);
		panelTabla.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 696, 157);
		panelTabla.add(scrollPane);
		contentPane.add(panelTabla);
		
		String cabecera[] = {"IdEmpleado","Nombre","Cargo","DNI","Email","Teléfono"};	
		String datos[][] = service.rellenaTablaEmpleados();
		DefaultTableModel model = new DefaultTableModel(datos, cabecera);
		
		table = new JTable(model);
	
		table.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		table.setBounds(277, 60, 426, 138);
		JTableHeader th = table.getTableHeader();
		th.setBackground(Color.GRAY);
		Font fuente = ConstantesUtil.ancho <= 1440 ? new Font("Lucida Grande", Font.PLAIN, 13) : new Font("Lucida Grande", Font.PLAIN, 17);
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
			    	  passwordField.setEnabled(false);
			    	  
		    	  }
		      }
		   });
	}
	
	private void fillForm() {
		tfNombre.setText((String) modeloTabla.getValueAt(filasSeleccionadas, 1));
		cbCargo.setSelectedItem((String)modeloTabla.getValueAt(filasSeleccionadas, 2));
		tfDni.setText((String) modeloTabla.getValueAt(filasSeleccionadas, 3));
		tfEmail.setText((String)modeloTabla.getValueAt(filasSeleccionadas, 4));
		tfTelefono.setText((String)modeloTabla.getValueAt(filasSeleccionadas, 5));  			    	  
		idEmpleado = (String) modeloTabla.getValueAt(filasSeleccionadas,0);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();
		
		
		if(source.getText().equals(ConstantesUtil.salir)){
			FormularioPrincipal.btnGestionEmpleado.setEnabled(true);
			dispose();
		}else if(source.getText().equals(ConstantesUtil.nuevo)){
			clearForm();
			EnableDisableForm(true);
			btnGuardModif.setEnabled(true);
			tfNombre.requestFocus();			
		}else if(source.getText().equals(ConstantesUtil.guardar)){			
			Empleado empleado = fillObject();			
			if(empleado != null){
				service.insertarEmpleado(empleado);
				clearForm();
				EnableDisableForm(false);
				btnNuevo.requestFocus();
				btnGuardModif.setEnabled(false);
			}						
		}else if(source.getText().equals(ConstantesUtil.modificar)){
			if(idEmpleado != null && !idEmpleado.equals("")){
				Empleado empleado = fillObject();	
				if(empleado != null){
					empleado.setIdEmpleado(Long.parseLong(idEmpleado));
					service.modificarEmpleado(empleado);
					clearForm();
					estadoInicial();
				}				
			}
		}else if(source.getText().equals(ConstantesUtil.eliminar)){
			service.eliminarEmpleado(idEmpleado);
			clearForm();
			estadoInicial();
		}else if(source.getText().equals(ConstantesUtil.cancelar)){
			clearForm();
			estadoInicial();
		}
		contentPane.remove(panelTabla);
		construirTabla();
		
	}
	
	private Empleado fillObject(){
		Empleado empleado = null;
		nombre = tfNombre.getText();
		cargo = cbCargo.getSelectedItem().toString();
		dni = tfDni.getText();
		email = tfEmail.getText();
		telefono = tfTelefono.getText().trim().replace(" ", "");
		pass = passwordField.getPassword();
		password = new String(pass);
		if(validar()){			
			empleado = new Empleado();
			empleado.setNombre(nombre );
			empleado.setCargo(cargo);
			empleado.setDni(dni);
			empleado.setEmail(email);
			empleado.setTelefono(telefono);
			empleado.setPassword(password);
			
		}else{
			JOptionPane.showMessageDialog(this, "Debes rellenar correctamente los campos antes de crear un empleado");
		}
		return empleado;
	}
	private Boolean validar() {
		Boolean dev = Boolean.FALSE;
		
		if(!nombre.equals("") && Validador.isAlfaNumericoConPunto(nombre)){
			dev = true;
		}else{
			return false;
		}
		if(!cargo.equals("") && Validador.isAlfa(cargo)){
			dev = true;
		}else{
			return false;
		}
		if(!dni.equals("")){
			if(Validador.isAlfaNumerico(dni)){
				dev = true;
			}else{
				return false;
			}
		}
		if(!email.equals("")){
			if(Validador.isEmail(email)){
				dev = true;		
			}else{
				return false;
			}
		}
		if(!telefono.equals("")){
			if(Validador.isNumero(telefono)){
				dev = true;
			}else{
				return false;
			}
		}
		
		return dev;
	}
	
	private void EnableDisableForm(boolean siNo) {
		tfNombre.setEnabled(siNo);
		cbCargo.setEnabled(siNo);
		tfDni.setEnabled(siNo);
		tfEmail.setEnabled(siNo);
		tfTelefono.setEnabled(siNo);
		passwordField.setEnabled(siNo);
	}

	private void clearForm() {
		tfNombre.setText("");
		tfDni.setText("");
		tfEmail.setText("");
		tfTelefono.setText("");
		passwordField.setText("");
		idEmpleado = "";
	}
}
