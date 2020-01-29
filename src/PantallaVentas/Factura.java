package PantallaVentas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import AplicacionGestionTPV.FormularioEmpleados;
import modelo.FacturaHecha;
import util.ConstantesUtil;
/**
 * 
 * @author Two hands Technology
 *
 */
public class Factura extends JDialog implements ActionListener {

	private JPanel contentPane;
	private JTextField txtNFactura,txtRazonSocial,txtNombreCliente,txtFechaFactura,txtBaseImponible,txtDniNifCliente;
	private JTextField txtTotal,txtDireccionCliente,txtNombreEmpresa,txtDninif,txtEmailCliente,txtEmpleado,txtTelefonoCliente;
	private double anchoVentana, altoVentana, ancho, alto;
	private JButton btnSalir,btnImprimir;
	private FacturaHecha facturaHecha;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Factura frame = new Factura(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param facturaHecha 
	 */
	public Factura(FacturaHecha facturaHecha) {
		this.facturaHecha = facturaHecha;
		setModal(true);
		anchoVentana = 545;
		altoVentana =  798;
//		ancho = ConstantesUtil.ancho;
//		alto = ConstantesUtil.alto;
		ancho = 1440;
		alto = 900;
		setLocationRelativeTo(null); 
		setResizable(false);
		setBounds((int)ancho/2 - (int)anchoVentana/2, (int)alto/2 - (int)altoVentana/2,(int) anchoVentana,(int) altoVentana);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 102, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setModal(true);
		setUndecorated(true);
		
		Panel panel = new Panel();
		panel.setBounds(10, 133, 529, 500);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(16, 2, -150, 0));
		Font font = ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 13) : new Font("Lucida Grande", Font.BOLD, 17);
		Font fontButton = ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.PLAIN, 13) : new Font("Lucida Grande", Font.PLAIN, 17);
		JLabel lblNFactura = new JLabel("Nº factura");
		lblNFactura.setForeground(UIManager.getColor("Button.highlight"));
		lblNFactura.setFont(font);
		panel.add(lblNFactura);
		
		txtNFactura = new JTextField();
		txtNFactura.setEditable(false);
		txtNFactura.setText("Nº factura");
		txtNFactura.setFont(fontButton);
		txtNFactura.setColumns(10);
		panel.add(txtNFactura);
		
		JLabel lblNombreCliente = new JLabel("Empresa cliente");
		lblNombreCliente.setForeground(UIManager.getColor("Button.highlight"));
		lblNombreCliente.setFont(font);
		panel.add(lblNombreCliente);
		
		txtNombreCliente = new JTextField();
		txtNombreCliente.setEditable(false);
		txtNombreCliente.setText("Nombre  empresa cliente");
		txtNombreCliente.setFont(fontButton);
		txtNombreCliente.setColumns(10);
		panel.add(txtNombreCliente);
		
		JLabel lblFechaFactura = new JLabel("Fecha factura");
		lblFechaFactura.setForeground(UIManager.getColor("Button.highlight"));
		lblFechaFactura.setFont(font);
		panel.add(lblFechaFactura);
		
		txtFechaFactura = new JTextField();
		txtFechaFactura.setEditable(false);
		txtFechaFactura.setText("Fecha factura");
		txtFechaFactura.setFont(fontButton);
		txtFechaFactura.setColumns(10);
		panel.add(txtFechaFactura);
		
		JLabel lblDniNifCliente = new JLabel("CIF/NIF cliente");
		lblDniNifCliente.setForeground(UIManager.getColor("Button.highlight"));
		lblDniNifCliente.setFont(font);
		panel.add(lblDniNifCliente);
		
		txtDniNifCliente = new JTextField();
		txtDniNifCliente.setEditable(false);
		txtDniNifCliente.setText("CIF/NIF cliente");
		txtDniNifCliente.setFont(fontButton);
		txtDniNifCliente.setColumns(10);
		panel.add(txtDniNifCliente);
		
		JLabel lblEmailCliente = new JLabel("Email cliente");
		lblEmailCliente.setForeground(UIManager.getColor("Button.highlight"));
		lblEmailCliente.setFont(font);
		panel.add(lblEmailCliente);
		
		txtEmailCliente = new JTextField();
		txtEmailCliente.setEditable(false);
		txtEmailCliente.setFont(fontButton);
		txtEmailCliente.setText("Email cliente");
		panel.add(txtEmailCliente);
		txtEmailCliente.setColumns(10);
		
		JLabel lblBaseImponible = new JLabel("Base imponible");
		lblBaseImponible.setForeground(UIManager.getColor("Button.highlight"));
		lblBaseImponible.setFont(font);
		panel.add(lblBaseImponible);
		
		txtBaseImponible = new JTextField();
		txtBaseImponible.setEditable(false);
		txtBaseImponible.setText("Base imponible");
		txtBaseImponible.setFont(fontButton);
		txtBaseImponible.setColumns(10);
		panel.add(txtBaseImponible);
		
		JLabel lblTelefonoCliente = new JLabel("Teléfono cliente");
		lblTelefonoCliente.setForeground(UIManager.getColor("Button.highlight"));
		lblTelefonoCliente.setFont(font);
		panel.add(lblTelefonoCliente);
		
		txtTelefonoCliente = new JTextField();
		txtTelefonoCliente.setEditable(false);
		txtTelefonoCliente.setFont(fontButton);
		txtTelefonoCliente.setText("Teléfono cliente");
		panel.add(txtTelefonoCliente);
		txtTelefonoCliente.setColumns(10);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setForeground(UIManager.getColor("Button.highlight"));
		lblTotal.setFont(font);
		panel.add(lblTotal);
		
		txtTotal = new JTextField();
		txtTotal.setEditable(false);
		txtTotal.setText("Total");
		txtTotal.setFont(fontButton);
		txtTotal.setColumns(10);
		panel.add(txtTotal);
		
		JLabel lblDireccionCliente = new JLabel("Dirección");
		lblDireccionCliente.setForeground(UIManager.getColor("Button.highlight"));
		lblDireccionCliente.setFont(font);
		panel.add(lblDireccionCliente);
		
		txtDireccionCliente = new JTextField();
		txtDireccionCliente.setEditable(false);
		txtDireccionCliente.setText("Dirección");
		txtDireccionCliente.setFont(fontButton);
		txtDireccionCliente.setColumns(10);
		panel.add(txtDireccionCliente);
		
		JLabel lblNombre = new JLabel("Empresa");
		lblNombre.setForeground(UIManager.getColor("Button.highlight"));
		lblNombre.setFont(font);
		panel.add(lblNombre);
		
		txtNombreEmpresa = new JTextField();
		txtNombreEmpresa.setEditable(false);
		txtNombreEmpresa.setFont(fontButton);
		txtNombreEmpresa.setText("Nombre empresa");
		panel.add(txtNombreEmpresa);
		txtNombreEmpresa.setColumns(10);
		
		JLabel lblDninif = new JLabel("CIF/NIF");
		lblDninif.setForeground(UIManager.getColor("Button.highlight"));
		lblDninif.setFont(font);
		panel.add(lblDninif);
		
		txtDninif = new JTextField();
		txtDninif.setEditable(false);
		txtDninif.setFont(fontButton);
		txtDninif.setText("CIF/NIF");
		panel.add(txtDninif);
		txtDninif.setColumns(10);
		
		JLabel lblRazonSocial = new JLabel("Razón social");
		lblRazonSocial.setForeground(UIManager.getColor("Button.highlight"));
		lblRazonSocial.setFont(font);
		panel.add(lblRazonSocial);
		
		txtRazonSocial = new JTextField();
		txtRazonSocial.setEditable(false);
		txtRazonSocial.setFont(fontButton);
		txtRazonSocial.setText("Razón social");
		panel.add(txtRazonSocial);
		txtRazonSocial.setColumns(10);
		
		JLabel lblEmpleado = new JLabel("Empleado");
		lblEmpleado.setForeground(UIManager.getColor("Button.highlight"));
		lblEmpleado.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		panel.add(lblEmpleado);
		
		txtEmpleado = new JTextField();
		txtEmpleado.setEditable(false);
		txtEmpleado.setFont(fontButton);
		txtEmpleado.setText("Empleado");
		panel.add(txtEmpleado);
		txtEmpleado.setColumns(10);
		
		JPanel panelSalir = new JPanel();
		panelSalir.setLayout(null);
		panelSalir.setBounds(6, 721, 533, 71);
		contentPane.add(panelSalir);
		
		btnSalir = new JButton(ConstantesUtil.salir);
		btnSalir.addActionListener(this);
		btnSalir.setIcon(new ImageIcon(FormularioEmpleados.class.getResource(ConstantesUtil.pathSalir)));
		btnSalir.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnSalir.setBounds(384, 6, 143, 61);
		panelSalir.add(btnSalir);
		
		btnImprimir = new JButton(ConstantesUtil.imprimir);
		btnImprimir.setIcon(new ImageIcon(PantallaTPV.class.getResource(ConstantesUtil.pathPrint)));
		btnImprimir.addActionListener(this);
		btnImprimir.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnImprimir.setBounds(6, 6, 143, 61);
		panelSalir.add(btnImprimir);
		
		JPanel panelTitulo = new JPanel();
		panelTitulo.setLayout(null);
		panelTitulo.setBounds(10, 6, 529, 66);
		contentPane.add(panelTitulo);
		
		JLabel lblFactura = new JLabel(ConstantesUtil.factura);
		lblFactura.setHorizontalAlignment(SwingConstants.CENTER);
		lblFactura.setFont(new Font("Lucida Grande", Font.BOLD, 26));
		lblFactura.setBounds(102, 16, 316, 32);
		panelTitulo.add(lblFactura);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();
		
		
		if(source.getText().equals(ConstantesUtil.salir)){
			dispose();
		}else if(source.getText().equals(ConstantesUtil.imprimir)){			
			new FacturaImpresa(facturaHecha);
			dispose();
		}
		
	}

	public String getTxtNFactura() {
		return txtNFactura.getText();
	}

	public void setTxtNFactura(String txtNFactura) {
		this.txtNFactura.setText(txtNFactura);
	}

	public String getTxtRazonSocial() {
		return txtRazonSocial.getText();
	}

	public void setTxtRazonSocial(String txtRazonSocial) {
		this.txtRazonSocial.setText(txtRazonSocial);
	}

	public String getTxtNombreCliente() {
		return txtNombreCliente.getText();
	}

	public void setTxtNombreCliente(String txtNombreCliente) {
		this.txtNombreCliente.setText(txtNombreCliente);
	}

	public String getTxtFechaFactura() {
		return txtFechaFactura.getText();
	}

	public void setTxtFechaFactura(String txtFechaFactura) {
		this.txtFechaFactura.setText(txtFechaFactura);
	}

	public String getTxtBaseImponible() {
		return txtBaseImponible.getText();
	}

	public void setTxtBaseImponible(String txtBaseImponible) {
		this.txtBaseImponible.setText(txtBaseImponible);
	}

	public String getTxtDniNifCliente() {
		return txtDniNifCliente.getText();
	}

	public void setTxtDniNifCliente(String txtDniNifCliente) {
		this.txtDniNifCliente.setText(txtDniNifCliente);
	}

	public String getTxtTotal() {
		return txtTotal.getText();
	}

	public void setTxtTotal(String txtTotal) {
		this.txtTotal.setText(txtTotal);
	}

	public String getTxtDireccionCliente() {
		return txtDireccionCliente.getText();
	}

	public void setTxtDireccionCliente(String txtDireccionCliente) {
		this.txtDireccionCliente.setText(txtDireccionCliente);
	}

	public String getTxtNombreEmpresa() {
		return txtNombreEmpresa.getText();
	}

	public void setTxtNombreEmpresa(String txtNombreEmpresa) {
		this.txtNombreEmpresa.setText(txtNombreEmpresa);
	}

	public String getTxtDninif() {
		return txtDninif.getText();
	}

	public void setTxtDninif(String txtDninif) {
		this.txtDninif.setText(txtDninif);
	}

	public String getTxtEmailCliente() {
		return txtEmailCliente.getText();
	}

	public void setTxtEmailCliente(String txtEmailCliente) {
		this.txtEmailCliente.setText(txtEmailCliente);
	}

	public String getTxtEmpleado() {
		return txtEmpleado.getText();
	}

	public void setTxtEmpleado(String txtEmpleado) {
		this.txtEmpleado.setText(txtEmpleado);
	}

	public String getTxtTelefonoCliente() {
		return txtTelefonoCliente.getText();
	}

	public void setTxtTelefonoCliente(String txtTelefonoCliente) {
		this.txtTelefonoCliente.setText(txtTelefonoCliente);
	}
	
	

}
