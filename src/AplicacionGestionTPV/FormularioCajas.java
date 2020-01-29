package AplicacionGestionTPV;

import java.awt.Color;
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
import javax.swing.JPanel;
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
import util.ConstantesUtil;

public class FormularioCajas extends JDialog implements ActionListener{

	private JPanel contentPanel;
	private JPanel panelTabla;
	private JScrollPane scrollPane;
	private JTable table, modeloTabla;
	private JTextField txtEmpleado,txtFecha,txtEfectivo,txtTarjeta,txtTotal;
	private JButton btnEliminar,btnCancelar,btnSalir;
	private JLabel label,lblEfectivo,lblTarjeta,lblTotal;
	private GestionService service = new GestionServiceImpl();
	private double  altoVentana, anchoVentana, ancho, alto;
	private int filasSeleccionadas;
	private String idCaja;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FormularioCajas dialog = new FormularioCajas();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FormularioCajas() {
		altoVentana = 732;
		anchoVentana = 680;
		ancho = ConstantesUtil.ancho;
		alto = ConstantesUtil.alto;
		contentPanel = new JPanel();
		getContentPane().setBackground(new Color(0, 102, 153));
		setBounds((int)ancho/2 - (int)anchoVentana/2, (int)alto/2 - (int)altoVentana/2, 680, 732);
		setResizable(false);
		setUndecorated(true);
		getContentPane().setLayout(null);
		contentPanel.setBackground(new Color(0, 102, 153));
		contentPanel.setBounds(6, 84, 668, 439);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		setModal(true);
		Panel panel = new Panel();
		panel.setBackground(new Color(0, 102, 153));
		panel.setBounds(0, 233, 669, 207);
		contentPanel.add(panel);
		panel.setLayout(new GridLayout(0, 2, -195, 0));
	
		JLabel label = new JLabel("Empleado");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		panel.add(label);
		
		
		txtEmpleado = new JTextField();
		txtEmpleado.setEditable(false);
		txtEmpleado.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		txtEmpleado.setEnabled(false);
		txtEmpleado.setColumns(10);
		panel.add(txtEmpleado);
		
		label = new JLabel("Fecha");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		panel.add(label);
			
		txtFecha = new JTextField();
		txtFecha.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		txtFecha.setEnabled(false);
		txtFecha.setColumns(10);
		panel.add(txtFecha);
			
		lblEfectivo = new JLabel("Efectivo");
		lblEfectivo.setForeground(Color.WHITE);
		lblEfectivo.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		panel.add(lblEfectivo);
			
		txtEfectivo = new JTextField();
		txtEfectivo.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		txtEfectivo.setEnabled(false);
		txtEfectivo.setColumns(10);
		panel.add(txtEfectivo);
	
		lblTarjeta = new JLabel("Tarjeta");
		lblTarjeta.setForeground(Color.WHITE);
		lblTarjeta.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		panel.add(lblTarjeta);
			
		txtTarjeta = new JTextField();
		txtTarjeta.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		txtTarjeta.setEnabled(false);
		txtTarjeta.setEditable(false);
		txtTarjeta.setColumns(10);
		panel.add(txtTarjeta);
	
		lblTotal = new JLabel("Total");
		lblTotal.setForeground(Color.WHITE);
		lblTotal.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		panel.add(lblTotal);
	
		txtTotal = new JTextField();
		txtTotal.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		txtTotal.setEnabled(false);
		txtTotal.setColumns(10);
		panel.add(txtTotal);
		
		JPanel panelTitulo = new JPanel();
		panelTitulo.setLayout(null);
		panelTitulo.setBounds(6, 6, 668, 66);
		getContentPane().add(panelTitulo);
			
		JLabel lblFormularioDeCajas = new JLabel("FORMULARIO DE CAJAS");
		lblFormularioDeCajas.setHorizontalAlignment(SwingConstants.CENTER);
		lblFormularioDeCajas.setFont(new Font("Lucida Grande", Font.BOLD, 26));
		lblFormularioDeCajas.setBounds(137, 17, 415, 32);
		panelTitulo.add(lblFormularioDeCajas);
			
		
		JPanel panelSalir = new JPanel();
		panelSalir.setLayout(null);
		panelSalir.setBounds(6, 655, 668, 71);
		getContentPane().add(panelSalir);
			
		btnSalir = new JButton(ConstantesUtil.salir);
		btnSalir.setIcon(new ImageIcon(FormularioEmpleados.class.getResource(ConstantesUtil.pathSalir)));
		btnSalir.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnSalir.setBounds(507, 6, 143, 61);
		panelSalir.add(btnSalir);

		JPanel panelBotonera = new JPanel();
		panelBotonera.setLayout(null);
		panelBotonera.setBounds(6, 535, 668, 108);
		getContentPane().add(panelBotonera);
	
		btnCancelar = new JButton(ConstantesUtil.cancelar);
		btnCancelar.setIcon(new ImageIcon(FormularioEmpleados.class.getResource(ConstantesUtil.pathCancelar)));
		btnCancelar.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		btnCancelar.setBounds(507, 6, 147, 96);
		panelBotonera.add(btnCancelar);
	
		btnEliminar = new JButton(ConstantesUtil.eliminar);
		btnEliminar.setIcon(new ImageIcon(FormularioEmpleados.class.getResource(ConstantesUtil.pathEliminar)));
		btnEliminar.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		btnEliminar.setBounds(19, 6, 147, 96);
		panelBotonera.add(btnEliminar);
		btnCancelar.addActionListener(this);
		btnEliminar.addActionListener(this);
		btnSalir.addActionListener(this);
		
		construirTabla();	
	}
	
	private void clearForm(){
		txtEfectivo.setText("");
		txtEmpleado.setText("");
		txtFecha.setText("");
		txtTarjeta.setText("");
		txtTotal.setText("");
	}
	
	private void construirTabla() {
		panelTabla = new JPanel();
		panelTabla.setBackground(new Color(0, 102, 153));
		panelTabla.setLayout(null);
		panelTabla.setBounds(0, 0, 668, 227);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 668, 227);

		String cabecera[] = {"IdCaja","Empleado","Fecha","Total tarjeta","Total efectivo","Total"};	
		String datos[][] = service.rellenarCajas();
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
			    	  modeloTabla = table;
			    	  clearForm();
			    	  fillForm();		    	      	  
		    	  }
		      }
		   });
		panelTabla.add(scrollPane);
		contentPanel.add(panelTabla);
	}
	private void fillForm(){
		idCaja = (String)  modeloTabla.getValueAt(filasSeleccionadas, 0);
		txtEfectivo.setText((String)  modeloTabla.getValueAt(filasSeleccionadas, 4));
		txtEmpleado.setText((String)  modeloTabla.getValueAt(filasSeleccionadas, 1));
		txtFecha.setText((String)  modeloTabla.getValueAt(filasSeleccionadas, 2));
		txtTarjeta.setText((String)  modeloTabla.getValueAt(filasSeleccionadas, 3));
		txtTotal.setText((String)  modeloTabla.getValueAt(filasSeleccionadas, 5));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();
		
		
		if(source.getText().equals(ConstantesUtil.salir)){
			dispose();		
		}else if(source.getText().equals(ConstantesUtil.eliminar)){
			service.eliminarCaja(idCaja);
			clearForm();
		}else if(source.getText().equals(ConstantesUtil.cancelar)){
			clearForm();
		}
		contentPanel.remove(panelTabla);
		construirTabla();
	}
}
