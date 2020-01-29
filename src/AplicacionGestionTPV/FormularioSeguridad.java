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
public class FormularioSeguridad extends JDialog implements ActionListener{

	private JPanel contentPanel;
	private JPanel panelTabla;
	private JScrollPane scrollPane;
	private JTable table, modeloTabla;
	private JTextField txtNombre,txtDni,txtEmail,txtTelefono;
	private JPasswordField passwordField;
	private double  altoVentana, anchoVentana, ancho, alto;
	private JComboBox cbCargo;
	private String nombre, cargo, dni, email, telefono, idEmpleado, password;
	private int filasSeleccionadas;
	private GestionService service = new GestionServiceImpl();
	private char[] pass;
	private JButton btnSalir, btnModificar, btnCancelar, btnEliminar;

	/**
	 * Create the dialog.
	 */
	public FormularioSeguridad() {
		contentPanel = new JPanel();
		getContentPane().setBackground(new Color(0, 102, 153));
		altoVentana = 622;
		anchoVentana = 709;
		ancho = ConstantesUtil.ancho;
		alto = ConstantesUtil.alto;
		setLocationRelativeTo(null); 
		setResizable(false);
		setUndecorated(true);
		setBounds((int)ancho/2 - (int)anchoVentana/2, (int)alto/2 - (int)altoVentana/2, 709, 622);
		getContentPane().setLayout(null);
		contentPanel.setBounds(6, 84, 696, 311);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		setModal(true);	
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(0, 102, 153));
		panel.setBounds(0, 0, 696, 134);
		contentPanel.add(panel);
		
		JLabel lblNombre = new JLabel("Nombre *");
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		lblNombre.setBounds(9, 6, 93, 26);
		panel.add(lblNombre);
		JLabel lblCargo = new JLabel("Cargo *");
		lblCargo.setForeground(Color.WHITE);
		lblCargo.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		lblCargo.setBounds(366, 50, 82, 26);
		panel.add(lblCargo);
	
		JLabel lblDni = new JLabel("DNI");
		lblDni.setForeground(Color.WHITE);
		lblDni.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		lblDni.setBounds(366, 94, 82, 26);
		panel.add(lblDni);
	
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		lblEmail.setBounds(366, 6, 76, 26);
		panel.add(lblEmail);
	
		JLabel lblTelefono = new JLabel("Teléfono");
		lblTelefono.setForeground(Color.WHITE);
		lblTelefono.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		lblTelefono.setBounds(6, 50, 96, 26);
		panel.add(lblTelefono);
	
		JLabel lblPass = new JLabel("Contraseña *");
		lblPass.setForeground(Color.WHITE);
		lblPass.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		lblPass.setBounds(9, 94, 119, 26);
		panel.add(lblPass);
	
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		txtNombre.setEnabled(false);
		txtNombre.setColumns(10);
		txtNombre.setBounds(124, 4, 230, 32);
		panel.add(txtNombre);
	
		cbCargo = new JComboBox();
		cbCargo.setEnabled(false);
		cbCargo.setBounds(462, 50, 230, 32);
		panel.add(cbCargo);
	
		txtDni = new JTextField();
		txtDni.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		txtDni.setEnabled(false);
		txtDni.setColumns(10);
		txtDni.setBounds(460, 92, 230, 32);
		panel.add(txtDni);
	
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		txtEmail.setEnabled(false);
		txtEmail.setColumns(10);
		txtEmail.setBounds(460, 4, 230, 32);
		panel.add(txtEmail);
	
		txtTelefono = new JTextField();
		txtTelefono.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		txtTelefono.setEnabled(false);
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(124, 48, 230, 32);
		panel.add(txtTelefono);
	
		passwordField = new JPasswordField();
		passwordField.setEnabled(false);
		passwordField.setBounds(124, 93, 230, 32);
		panel.add(passwordField);		
	
		JPanel panelSalir = new JPanel();
		panelSalir.setLayout(null);
		panelSalir.setBounds(6, 544, 696, 72);
		getContentPane().add(panelSalir);
		
		btnSalir = new JButton(ConstantesUtil.salir);
		btnSalir.setIcon(new ImageIcon(FormularioEmpleados.class.getResource(ConstantesUtil.pathSalir)));
		btnSalir.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnSalir.setBounds(534, 6, 143, 61);
		panelSalir.add(btnSalir);
	
		JPanel panelTitulo = new JPanel();
		panelTitulo.setLayout(null);
		panelTitulo.setBounds(6, 6, 696, 66);
		getContentPane().add(panelTitulo);
		
		JLabel lblFormularioDeSeguridad = new JLabel(ConstantesUtil.formularioSeguridad);
		lblFormularioDeSeguridad.setHorizontalAlignment(SwingConstants.CENTER);
		lblFormularioDeSeguridad.setFont(new Font("Lucida Grande", Font.BOLD, 26));
		lblFormularioDeSeguridad.setBounds(145, 16, 415, 32);
		panelTitulo.add(lblFormularioDeSeguridad);
	
		JPanel panelBotonera = new JPanel();
		panelBotonera.setLayout(null);
		panelBotonera.setBounds(6, 429, 696, 108);
		getContentPane().add(panelBotonera);
		
		btnModificar = new JButton(ConstantesUtil.modificar);
		btnModificar.setIcon(new ImageIcon(FormularioEmpleados.class.getResource(ConstantesUtil.pathGuardar)));
		btnModificar.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		btnModificar.setEnabled(false);
		btnModificar.setBounds(23, 7, 147, 96);
		panelBotonera.add(btnModificar);
	
		btnCancelar = new JButton(ConstantesUtil.cancelar);
		btnCancelar.setIcon(new ImageIcon(FormularioEmpleados.class.getResource(ConstantesUtil.pathCancelar)));
		btnCancelar.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		btnCancelar.setBounds(528, 7, 147, 96);
		panelBotonera.add(btnCancelar);
	
		btnEliminar = new JButton(ConstantesUtil.eliminar);
		btnEliminar.setIcon(new ImageIcon(FormularioEmpleados.class.getResource(ConstantesUtil.pathEliminar)));
		btnEliminar.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		btnEliminar.setEnabled(false);
		btnEliminar.setBounds(272, 7, 147, 96);
		panelBotonera.add(btnEliminar);
		btnModificar.addActionListener(this);
		btnCancelar.addActionListener(this);
		btnEliminar.addActionListener(this);
		btnSalir.addActionListener(this);
		
		construirTabla();
		
		estadoInicial();
	}
	private void estadoInicial() {
		
		EnableDisableForm(false);		
		btnEliminar.setEnabled(false);
		btnModificar.setEnabled(false);
		cbCargo.addItem(ConstantesUtil.administrador);
		cbCargo.addItem(ConstantesUtil.empleado);
		
	}
	private void EnableDisableForm(boolean siNo) {
		txtDni.setEnabled(siNo);
		txtEmail.setEnabled(siNo);
		txtNombre.setEnabled(siNo);
		txtTelefono.setEnabled(siNo);
		cbCargo.setEnabled(siNo);
		passwordField.setEnabled(siNo);
		
	}
	private void clearForm() {
		txtDni.setText("");
		txtEmail.setText("");
		txtNombre.setText("");
		txtTelefono.setText("");
	}
private void construirTabla() {
	
		panelTabla = new JPanel();
		panelTabla.setBounds(0, 154, 696, 157);
		panelTabla.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 696, 157);
		
		
		String cabecera[] = {"IdEmpleado","Nombre","Cargo","DNI","Email","Teléfono", "Contraseña"};	
		String datos[][] = service.rellenaEmpleados();
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
			    	  btnModificar.setEnabled(true);	
			    	  
		    	  }
		      }
		   });
		panelTabla.add(scrollPane);
		contentPanel.add(panelTabla);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();
		
		
		if(source.getText().equals(ConstantesUtil.salir)){
			dispose();		
		}else if(source.getText().equals(ConstantesUtil.modificar)){
			if(idEmpleado != null && !idEmpleado.equals("")){
				Empleado empleado = fillObject();	
				if(empleado != null){
					empleado.setIdEmpleado(Long.parseLong(idEmpleado));
					service.modificarEmpleado(empleado);
				}
				clearForm();
				estadoInicial();
			}
		}else if(source.getText().equals(ConstantesUtil.eliminar)){
			service.eliminarEmpleado(idEmpleado);
			clearForm();
			estadoInicial();
		}else if(source.getText().equals(ConstantesUtil.cancelar)){
			clearForm();
			estadoInicial();
		}
		contentPanel.remove(panelTabla);
		construirTabla();
	}

	private void fillForm() {
		txtNombre.setText((String) modeloTabla.getValueAt(filasSeleccionadas, 1));
		cbCargo.setSelectedItem((String)modeloTabla.getValueAt(filasSeleccionadas, 2));
		txtDni.setText((String) modeloTabla.getValueAt(filasSeleccionadas, 3));
		txtEmail.setText((String)modeloTabla.getValueAt(filasSeleccionadas, 4));
		txtTelefono.setText((String)modeloTabla.getValueAt(filasSeleccionadas, 5));  			    	  
		idEmpleado = (String) modeloTabla.getValueAt(filasSeleccionadas,0);
		passwordField.setText((String) modeloTabla.getValueAt(filasSeleccionadas,6));
	
	}
	
	private Empleado fillObject(){
		Empleado empleado = null;
		nombre = txtNombre.getText();
		cargo = cbCargo.getSelectedItem().toString();
		dni = txtDni.getText();
		email = txtEmail.getText();
		telefono = txtTelefono.getText().trim().replace(" ", "");
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

}
