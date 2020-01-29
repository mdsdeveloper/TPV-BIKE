package PantallaVentas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import AplicacionGestionTPV.FormularioCategoria;
import controladorService.AppService;
import controladorService.AppServiceImpl;
import modelo.Venta;
import util.ConstantesUtil;
import util.UtilService;
/**
 * 
 * @author Two hands Technology
 *
 */
public class CierreCaja extends JDialog  implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JTextField tfRecuento;
	private double alto, ancho;
	private AppService appService = new AppServiceImpl();
	private Date ahora = new Date();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CierreCaja dialog = new CierreCaja();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CierreCaja() {
		getContentPane().setBackground(UIManager.getColor("ColorChooser.background"));
		setBackground(UIManager.getColor("ColorChooser.background"));
		setResizable(false);
		setUndecorated(true);
		alto = 387;
		ancho = 527;
		setBounds((int)ConstantesUtil.ancho/2 - (int)ancho/2, (int)ConstantesUtil.alto/2 - (int)alto/2, 527, 394);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 527, 394);
		contentPanel.setBorder(new LineBorder(new Color(65, 105, 170), 10));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		setModal(true);
		JLabel lblCierreDeCaja = new JLabel("CIERRE DE CAJA");
		lblCierreDeCaja.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblCierreDeCaja.setHorizontalAlignment(SwingConstants.CENTER);
		lblCierreDeCaja.setBounds(6, 19, 515, 26);
		contentPanel.add(lblCierreDeCaja);
		
		JLabel lblRecuento = new JLabel("Recuento");
		lblRecuento.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblRecuento.setBounds(55, 205, 106, 33);
		contentPanel.add(lblRecuento);
		
		tfRecuento = new JTextField();
		tfRecuento.setHorizontalAlignment(SwingConstants.RIGHT);
		tfRecuento.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		tfRecuento.setBounds(159, 205, 180, 33);
		contentPanel.add(tfRecuento);
		tfRecuento.setColumns(10);
		
		JLabel label = new JLabel("€");
		label.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		label.setBounds(339, 203, 61, 36);
		contentPanel.add(label);
		
		JLabel lblDesde = new JLabel("Desde");
		lblDesde.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblDesde.setBounds(55, 95, 68, 26);
		contentPanel.add(lblDesde);
		
		JLabel lblFechadesde = new JLabel(UtilService.obtenerFecha(ConstantesUtil.getFechaCierre() != null ? ConstantesUtil.getFechaCierre() : new Date()));
		lblFechadesde.setHorizontalAlignment(SwingConstants.LEFT);
		lblFechadesde.setBounds(159, 98, 119, 26);
		contentPanel.add(lblFechadesde);
		
		JLabel lblHasta = new JLabel("Hasta");
		lblHasta.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblHasta.setBounds(55, 133, 68, 26);
		contentPanel.add(lblHasta);
		
		JLabel lblFechahasta = new JLabel(UtilService.obtenerFecha(ahora));
		lblFechahasta.setHorizontalAlignment(SwingConstants.LEFT);
		lblFechahasta.setBounds(159, 136, 119, 26);
		contentPanel.add(lblFechahasta);
		{
			JButton okButton = new JButton(ConstantesUtil.aceptar);
			okButton.setBounds(93, 308, 126, 70);
			contentPanel.add(okButton);
			okButton.setFont(new Font("Lucida Grande", Font.BOLD, 15));
			okButton.addActionListener(this);
			getRootPane().setDefaultButton(okButton);
		}
		{
			JButton btnSalir = new JButton(ConstantesUtil.salir);
			btnSalir.setBounds(316, 307, 126, 70);
			contentPanel.add(btnSalir);
			btnSalir.setFont(new Font("Lucida Grande", Font.BOLD, 15));
			btnSalir.setIcon(new ImageIcon(FormularioCategoria.class.getResource(ConstantesUtil.pathSalir)));
			btnSalir.addActionListener(this);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();
		
		if(source.getText().equals(ConstantesUtil.salir)){
			dispose();
		}else if(source.getText().equals(ConstantesUtil.aceptar)){
			List<Venta> lista = null;
			lista = appService.hacerCierreCaja(ahora,tfRecuento.getText());
			
			if(!lista.isEmpty()){
				new CierreImpreso(lista, ahora, tfRecuento.getText(), ConstantesUtil.cierre);
			}
			
			dispose();
		}
		
	}
	

}
