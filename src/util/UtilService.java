package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import modelo.Empleado;
/**
 * 
 * @author Two hands Technology
 *
 */
public class UtilService {

	public static Empleado empleado;

	public static Empleado getEmpleadoLogado() {
		return empleado;
	}

	public static String obtenerFecha(Date fechaCierre) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		return format.format(fechaCierre);
	}
	
	public static Date obtenerFechaByString(String fecha){
		Date dia = null;
		String fechaParseada = parseaFullDateString(fecha);
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		try {
			 dia = format.parse(fechaParseada);
		} catch (ParseException e) {
			e.printStackTrace();
		}	
		return dia;
	}
	
	public static String parseaFullDateString(String fecha){
		String[] s = fecha.split("-");
		String[] s1 = s[2].split(" ");
		String nuevoDia = s1[0]+"/"+s[1]+"/"+s[0];
		return nuevoDia;
	}

	public static String obtenerHora(Date hoy) {
		 SimpleDateFormat formateador = new SimpleDateFormat("hh:mm:ss");
		 return formateador.format(hoy);
	}
	
	
}
