package PantallaVentas;

import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controladorService.AppService;
import controladorService.AppServiceImpl;
import modelo.Empleado;
import util.ConstantesUtil;
/**
 * 
 * @author Two hands Technology
 *
 */
public class LaminaFecha extends JPanel implements Runnable{

	public JLabel lblFecha,lblHora, lblEmpleado;
	private String hora, minutos, segundos, fecha;
	private int dia;
	private Calendar calendario;
	private Thread hilo;
	private AppService appService = new AppServiceImpl();
	
	
	public LaminaFecha(double anchoVentana, double altoVentana){
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		setVisible(true);
		
		lblEmpleado = new JLabel("Empleado");
		lblEmpleado.setFont(ConstantesUtil.ancho <= 1280 ? new Font("Lucida Grande", Font.PLAIN, 15) : new Font("Lucida Grande", Font.PLAIN, 18));
		lblEmpleado.setHorizontalAlignment(SwingConstants.CENTER);
//		lblEmpleado.setBounds(16, 91, 236, 30);
		lblEmpleado.setBounds((int)(anchoVentana*0.011),(int)(altoVentana*0.101),(int)(anchoVentana*0.163),(int)(altoVentana*0.033));
		add(lblEmpleado);
		obtenerEmpleado();
		
		lblFecha = new JLabel("Fecha");
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblFecha.setFont(ConstantesUtil.ancho <= 1280 ? new Font("Lucida Grande", Font.PLAIN, 15) : new Font("Lucida Grande", Font.PLAIN, 18));
//		lblFecha.setBounds(6, 6, 246, 33);
		lblFecha.setBounds((int)(anchoVentana*0.0041), (int)(altoVentana*0.0066), (int)(anchoVentana*0.170), (int)(altoVentana*0.036));
		add(lblFecha);
		
		lblHora = new JLabel("Hora");
		lblHora.setFont(ConstantesUtil.ancho <= 1280 ? new Font("Lucida Grande", Font.PLAIN, 17) : new Font("Lucida Grande", Font.BOLD, 20));
		lblHora.setHorizontalAlignment(SwingConstants.CENTER);
//		lblHora.setBounds(6, 43, 246, 36);
		lblHora.setBounds((int)(anchoVentana*0.0041),(int)(altoVentana*0.047),(int)(anchoVentana*0.170),(int)(altoVentana*0.04));
		add(lblHora);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.GREEN);
		separator.setBackground(Color.BLACK);
//		separator.setBounds(6, 83, 246, 12);
		separator.setBounds((int)(anchoVentana*0.0041),(int)(altoVentana*0.092),(int)(anchoVentana*0.170),(int)(altoVentana*0.0133));
		add(separator);
		
		
		
		
//		************************************************** inicia reloj
			hilo = new Thread(this);
			hilo.start();
//	    ***************************************************************				
		
				
	}
	

	private void obtenerEmpleado() {
		Empleado e = appService.obtenerEmpleado();
		lblEmpleado.setText(e != null ? e.getNombre() : "Empleado");
	}


	public void calcula() {
		calendario = new GregorianCalendar();
		Date fechaHoraActual = new Date(System.currentTimeMillis());
		SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
		
		calendario.setTime(fechaHoraActual);
		
		hora = String.valueOf(calendario.get(Calendar.HOUR_OF_DAY));		
		minutos = String.valueOf(calendario.get(Calendar.MINUTE) > 9 ? "" + calendario.get(Calendar.MINUTE) 
		: "0" + calendario.get(Calendar.MINUTE));
		segundos = String.valueOf(calendario.get(Calendar.SECOND) > 9 ? "" + calendario.get(Calendar.SECOND)
		: "0" + calendario.get(Calendar.SECOND));
		
		dia = calendario.get(Calendar.DAY_OF_WEEK);
		switch(dia){
		case 1 : fecha = "Domingo";
			break;
		case 2 : fecha = "Lunes";
			break;
		case 3 : fecha = "Martes";
			break;
		case 4 : fecha = "Miércoles";
			break;
		case 5 : fecha = "Jueves";
			break;
		case 6 : fecha = "Viernes";
			break;
		case 7 : fecha = "Sábado";
			break;
		}
		fecha += " " + date.format(fechaHoraActual);
//		Añado la fecha al label
		lblFecha.setText(fecha);
		lblHora.setText( hora + ":" + minutos + ":" + segundos);
	}
	
	public void run() {
		Thread ct =Thread.currentThread();
		
			while(ct == hilo){
				calcula();			
				try{
					Thread.sleep(1000);
				}catch(InterruptedException e){}
			}
		
	}
}
