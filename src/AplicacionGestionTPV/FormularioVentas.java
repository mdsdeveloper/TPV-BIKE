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
import modelo.Venta;
import util.ConstantesUtil;
import util.IVAEnum;
import util.Validador;
/**
 * 
 * @author Two hands Technology
 *
 */
public class FormularioVentas extends JDialog implements ActionListener{

	private JPanel contentPanel;
	private JPanel panelTabla;
	private JScrollPane scrollPane;
	private JTable table, modeloTabla;
	private JTextField txtEmpleado,txtFecha,txtFormaPago,txtImporte,txtTotal;
	private int filasSeleccionadas;
	private JButton btnSalir, btnModificar, btnCancelar, btnEliminar;
	private GestionService service = new GestionServiceImpl();
	private String idVenta, precio;
	private BigDecimal total;
	private double  altoVentana, anchoVentana, ancho, alto;


	/**
	 * Create the dialog.
	 */
	public FormularioVentas() {
		contentPanel = new JPanel();
		getContentPane().setBackground(new Color(0, 102, 153));
		altoVentana = 686;
		anchoVentana = 681;
		ancho = ConstantesUtil.ancho;
		alto = ConstantesUtil.alto;
		setLocationRelativeTo(null); 
		setResizable(false);
		setUndecorated(true);
		setBounds((int)ancho/2 - (int)anchoVentana/2, (int)alto/2 - (int)altoVentana/2, 681, 686);
		getContentPane().setLayout(null);
		contentPanel.setBackground(new Color(0, 102, 153));
		contentPanel.setBounds(6, 89, 669, 392);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		setModal(true);		
		
		Panel panel = new Panel();
		panel.setBackground(new Color(0, 102, 153));
		panel.setBounds(0, 207, 669, 182);
		contentPanel.add(panel);
		panel.setLayout(new GridLayout(0, 2, -195, 0));
		
		JLabel lblEmplado = new JLabel("Empleado");
		lblEmplado.setForeground(Color.WHITE);
		lblEmplado.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		panel.add(lblEmplado);
		
		txtEmpleado = new JTextField();
		txtEmpleado.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		txtEmpleado.setEnabled(false);
		txtEmpleado.setColumns(10);
		panel.add(txtEmpleado);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setForeground(Color.WHITE);
		lblFecha.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		panel.add(lblFecha);
		
		txtFecha = new JTextField();
		txtFecha.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		txtFecha.setEnabled(false);
		txtFecha.setColumns(10);
		panel.add(txtFecha);
		
		JLabel lblFormaDePago = new JLabel("Forma de pago");
		lblFormaDePago.setForeground(Color.WHITE);
		lblFormaDePago.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		panel.add(lblFormaDePago);
		
		txtFormaPago = new JTextField();
		txtFormaPago.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		txtFormaPago.setEnabled(false);
		txtFormaPago.setColumns(10);
		panel.add(txtFormaPago);
		
		JLabel lblTotal = new JLabel("Importe");
		lblTotal.setForeground(Color.WHITE);
		lblTotal.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		panel.add(lblTotal);
		
		txtImporte = new JTextField();
		txtImporte.setEditable(false);
		txtImporte.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		txtImporte.setEnabled(false);
		txtImporte.setColumns(10);
		panel.add(txtImporte);
		
		JLabel lblTotal_1 = new JLabel("Total");
		lblTotal_1.setForeground(Color.WHITE);
		lblTotal_1.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		panel.add(lblTotal_1);
		
		txtTotal = new JTextField();
		txtTotal.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		txtTotal.setColumns(10);
		panel.add(txtTotal);
		
		JPanel panelSalir = new JPanel();
		panelSalir.setLayout(null);
		panelSalir.setBounds(7, 609, 668, 71);
		getContentPane().add(panelSalir);
		
		btnSalir = new JButton(ConstantesUtil.salir);
		btnSalir.setIcon(new ImageIcon(FormularioEmpleados.class.getResource(ConstantesUtil.pathSalir)));
		btnSalir.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnSalir.setBounds(507, 6, 143, 61);
		panelSalir.add(btnSalir);
		
		JPanel panelBotonera = new JPanel();
		panelBotonera.setBounds(6, 489, 668, 108);
		getContentPane().add(panelBotonera);
		panelBotonera.setLayout(null);
		
		btnModificar = new JButton(ConstantesUtil.modificar);
		btnModificar.setIcon(new ImageIcon(FormularioEmpleados.class.getResource(ConstantesUtil.pathGuardar)));
		btnModificar.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		btnModificar.setEnabled(false);
		btnModificar.setBounds(23, 7, 147, 96);
		panelBotonera.add(btnModificar);
		
		btnCancelar = new JButton(ConstantesUtil.cancelar);
		btnCancelar.setIcon(new ImageIcon(FormularioEmpleados.class.getResource(ConstantesUtil.pathCancelar)));
		btnCancelar.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		btnCancelar.setBounds(507, 6, 147, 96);
		panelBotonera.add(btnCancelar);
		
		btnEliminar = new JButton(ConstantesUtil.eliminar);
		btnEliminar.setIcon(new ImageIcon(FormularioEmpleados.class.getResource(ConstantesUtil.pathEliminar)));
		btnEliminar.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		btnEliminar.setEnabled(false);
		btnEliminar.setBounds(261, 6, 147, 96);
		panelBotonera.add(btnEliminar);
		
		JPanel panelTitulo = new JPanel();
		panelTitulo.setLayout(null);
		panelTitulo.setBounds(6, 6, 668, 66);
		getContentPane().add(panelTitulo);
		
		JLabel lblFormularioDeVentas = new JLabel("FORMULARIO DE VENTAS");
		lblFormularioDeVentas.setHorizontalAlignment(SwingConstants.CENTER);
		lblFormularioDeVentas.setFont(new Font("Lucida Grande", Font.BOLD, 26));
		lblFormularioDeVentas.setBounds(137, 17, 415, 32);
		panelTitulo.add(lblFormularioDeVentas);
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
		
	}
	private void EnableDisableForm(boolean siNo) {
		txtTotal.setEnabled(siNo);
	}
	private void clearForm() {
		txtEmpleado.setText("");
		txtFecha.setText("");
		txtFormaPago.setText("");
		txtImporte.setText("");
		txtTotal.setText("");
	}
	
private void construirTabla() {
		panelTabla = new JPanel();
		panelTabla.setBackground(new Color(0, 102, 153));
		panelTabla.setLayout(null);
		panelTabla.setBounds(0, 0, 663, 201);
		panelTabla.setLayout(null);
		
		
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 663, 201);

		
		
		String cabecera[] = {"IdVenta","Empleado","Fecha","Forma de pago","Importe","Total"};	
		String datos[][] = service.rellenarVentas();
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
			    	  btnModificar.setEnabled(true);	    	  
		    	  }
		      }
		   });
		panelTabla.add(scrollPane);
		contentPanel.add(panelTabla);
	}

	private void fillForm() {
		txtEmpleado.setText((String) modeloTabla.getValueAt(filasSeleccionadas, 1));
		txtFecha.setText((String) modeloTabla.getValueAt(filasSeleccionadas, 2));
		txtFormaPago.setText((String) modeloTabla.getValueAt(filasSeleccionadas, 3));
		txtImporte.setText((String)modeloTabla.getValueAt(filasSeleccionadas, 4));
		txtTotal.setText((String)modeloTabla.getValueAt(filasSeleccionadas, 5));
		idVenta = (String)  modeloTabla.getValueAt(filasSeleccionadas, 0);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();
		
		
		if(source.getText().equals(ConstantesUtil.salir)){
			dispose();		
		}else if(source.getText().equals(ConstantesUtil.modificar)){
			if(idVenta != null && !idVenta.equals("")){
				Venta venta = fillObject();	
				if(venta != null){
					venta.setIdVenta(Long.parseLong(idVenta));
					service.modificarVenta(venta);
				}
				clearForm();
				estadoInicial();
			}
		}else if(source.getText().equals(ConstantesUtil.eliminar)){
			service.eliminarVenta(idVenta);
			clearForm();
			estadoInicial();
		}else if(source.getText().equals(ConstantesUtil.cancelar)){
			clearForm();
			estadoInicial();
		}
		contentPanel.remove(panelTabla);
		construirTabla();
	}
	private Venta fillObject(){
		Venta venta = null;
		if(validar()){	
			venta = new Venta();
			IVAEnum iva = IVAEnum.VEINTIUNO;
			BigDecimal totalIva = new BigDecimal(iva.getCodigo());
			totalIva = totalIva.divide(new BigDecimal(100));
			BigDecimal totalVenta = new BigDecimal(txtTotal.getText());
			BigDecimal baseImponible = totalVenta.subtract(totalVenta.multiply(totalIva));
	
			venta.setImporte(baseImponible);
			venta.setTotal(totalVenta);	
		}
		
		return venta;
	}
	
	private Boolean validar() {
		Boolean dev = Boolean.FALSE;
		
		if(!txtTotal.getText().equals("") && Validador.isDecimal(txtTotal.getText())){
			precio = ConstantesUtil.obtenerNumero(txtTotal.getText());
			total = new BigDecimal(precio);
			dev = true;		
		}else{
			return false;
		}
		
		
		return dev;
	}

}
