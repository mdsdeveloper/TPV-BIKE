package PantallaVentas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import AplicacionGestionTPV.FormularioCategoria;
import AplicacionGestionTPV.PantallaPrincipal;
import controladorService.AppService;
import controladorService.AppServiceImpl;
import modelo.Venta;
import util.ConstantesUtil;
/**
 * 
 * @author Two hands Technology
 *
 */
public class Opciones extends JDialog implements ActionListener{

	private JPanel contentPane;
	private double alto, ancho;
	private JButton btnFactura, btnExit, btnSalir, btnCierreCaja, btnBalance;
	private PantallaTPV PantallaTPV;
	private FacturaConCliente formularioFactura;
	private AppService appService = new AppServiceImpl();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Opciones opciones = new Opciones(new PantallaTPV());
					opciones.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param PantallaTPV 
	 */
	public Opciones(PantallaTPV PantallaTPV) {
		this.PantallaTPV = PantallaTPV;
		setModal(true);
		setForeground(UIManager.getColor("ColorChooser.background"));
		setBackground(UIManager.getColor("Desktop.background"));
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setUndecorated(true);
//		alto = 400;
//		ancho = 404;
		alto = ConstantesUtil.alto*0.4444;
		ancho = ConstantesUtil.ancho*0.2805;
		setBounds((int)ConstantesUtil.ancho/2 - (int)ancho/2, (int)ConstantesUtil.alto/2 - (int)alto/2, (int)ancho, (int)alto);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.background"));
		contentPane.setBorder(new LineBorder(new Color(65, 105, 170), 10));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Panel panel = new Panel();
		panel.setBackground(UIManager.getColor("Button.background"));
//		panel.setBounds(10, 10, 384, 380);
		panel.setBounds((int)(ancho*0.0247), (int)(alto*0.025), (int)(ancho*0.954), (int)(alto*0.958));
		contentPane.add(panel);
		panel.setLayout(null);
		
		btnSalir = new JButton(ConstantesUtil.cerrar);
//		btnSalir.setBounds(199, 302, 142, 72);
		btnSalir.setBounds((int) (ancho/2), (int)(ConstantesUtil.alto*0.3355), (int)(ConstantesUtil.ancho*0.12), (int)(ConstantesUtil.alto*0.08));
		btnSalir.setIcon(new ImageIcon(FormularioCategoria.class.getResource(ConstantesUtil.pathSalir)));
		btnSalir.addActionListener(this);
		btnSalir.setFont(ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 13) : new Font("Lucida Grande", Font.BOLD, 15));
		panel.add(btnSalir);
		
		btnCierreCaja = new JButton(ConstantesUtil.cierre);
//		btnCierreCaja.setBounds(36, 91, 142, 72);
		btnCierreCaja.setBounds((int) (ancho/4) - (int)(ConstantesUtil.ancho*0.12)/2, (int)(ConstantesUtil.alto*0.1011), (int)(ConstantesUtil.ancho*0.12), (int)(ConstantesUtil.alto*0.08));
		btnCierreCaja.addActionListener(this);
		btnCierreCaja.setFont(ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 13) : new Font("Lucida Grande", Font.BOLD, 15));
		panel.add(btnCierreCaja);
		
		btnBalance = new JButton(ConstantesUtil.balance);
		btnBalance.addActionListener(this);
		btnBalance.setBounds(198, 90, 142, 72);
		btnBalance.setBounds((int) (ancho/2), (int)(ConstantesUtil.alto*0.1011), (int)(ConstantesUtil.ancho*0.12), (int)(ConstantesUtil.alto*0.08));
		btnBalance.setIcon(new ImageIcon(FormularioCategoria.class.getResource(ConstantesUtil.pathBalance)));
		btnBalance.setFont(ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 13) : new Font("Lucida Grande", Font.BOLD, 15));
		panel.add(btnBalance);
		
		JLabel lblOpciones = new JLabel("OPCIONES DE VENTA");
//		lblOpciones.setBounds(6, 18, 372, 42);
		lblOpciones.setBounds((int)(ConstantesUtil.ancho*0.0041), (int)(ConstantesUtil.alto*0.02), (int)(ConstantesUtil.ancho*0.2583), (int)(ConstantesUtil.alto*0.0466));
		lblOpciones.setFont(ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 17) : new Font("Lucida Grande", Font.BOLD, 22));
		lblOpciones.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblOpciones);
		
		btnFactura = new JButton(ConstantesUtil.factura);
		btnFactura.addActionListener(this);
//		btnFactura.setBounds(36, 181, 142, 72);
		btnFactura.setBounds((int) (ancho/4) - (int)(ConstantesUtil.ancho*0.12)/2, (int)(ConstantesUtil.alto*0.2011), (int)(ConstantesUtil.ancho*0.12), (int)(ConstantesUtil.alto*0.08));
		btnFactura.setIcon(new ImageIcon(PantallaTPV.class.getResource(ConstantesUtil.pathPrint)));
		btnFactura.setFont(ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 13) : new Font("Lucida Grande", Font.BOLD, 15));
		panel.add(btnFactura);
		
		btnExit = new JButton(ConstantesUtil.exit);
//		btnExit.setBounds(199, 181, 142, 72);
		btnExit.setBounds((int) (ancho/2), (int)(ConstantesUtil.alto*0.2011), (int)(ConstantesUtil.ancho*0.12), (int)(ConstantesUtil.alto*0.08));
		btnExit.setIcon(new ImageIcon(FormularioCategoria.class.getResource(ConstantesUtil.pathExit)));
		btnExit.addActionListener(this);
		btnExit.setFont(ConstantesUtil.ancho < 1440 ? new Font("Lucida Grande", Font.BOLD, 13) : new Font("Lucida Grande", Font.BOLD, 15));
		panel.add(btnExit);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();
		
		if(source.getText().equals(ConstantesUtil.cerrar)){
			PantallaTPV.btnOpciones.setEnabled(true);
			dispose();		
		}else if(source.getText().equals(ConstantesUtil.exit)){
			PantallaPrincipal.btnEntrarTpv.setEnabled(true);
			PantallaTPV.cerrarTPV();
			dispose();	
			
		}else if(source.getText().equals(ConstantesUtil.cierre)){
			CierreCaja dialog = new CierreCaja();
			dialog.setVisible(true);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);			
		}else if(source.getText().equals(ConstantesUtil.factura)){
			formularioFactura = new FacturaConCliente();
			formularioFactura.setVisible(true);
			formularioFactura.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			
		}else if(source.getText().equals(ConstantesUtil.balance)){
			List<Venta> listaVenta;
			Date ahora = new Date();
			listaVenta = appService.hacerBalanceCaja(ahora);
			
			if(!listaVenta.isEmpty()){
				new CierreImpreso(listaVenta, ahora, "", ConstantesUtil.balance);
			}
		}
		
	}
}
