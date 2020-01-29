package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 
 * @author Two hands Technology
 *
 */
public class Validador {

	private static final String txt="abcdefghijklmnñopqrstuvwxyz";
	private static final String PATRON_ALFA="[A-ZÑa-zñ\\s]+";	// Alfabético
	private static final String PATRON_DECIMAL = "[0-9]+([.][0-9]{1,2})?";
	private static final String PATRON_NUMERICO = "[0-9]+"; // Numérico
	private static final String PATRON_ALFA_NUMERICO_PUNTO = "[A-ZÑa-zñ0-9\\s[._,()áéíóúAÉÍÓÚ]]+";
	private static final String PATRON_ALFA_NUMERICO = "[A-ZÑa-zñ0-9\\s-]+";
	private static final String PATRON_DIRECCION = "[A-ZÑa-zñ0-9\\sº[._,()áéíóúAÉÍÓÚ]]+";
	private static final String PATRON_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static Pattern patron;
	private static Matcher matcher;



	public static Boolean isAlfa(String dato){
		Boolean dev = Boolean.FALSE;
		
		dev = Pattern.matches(PATRON_ALFA, dato);	   
		
		return dev;
	}


	public static Boolean isNumero(String dato) {
		Boolean dev = Boolean.FALSE;
		String numero = ConstantesUtil.obtenerNumeroSinComa(dato);
		dev = Pattern.matches(PATRON_NUMERICO, numero);
	
		return dev;
	}
	public static Boolean isDecimal(String dato){
		Boolean dev = Boolean.FALSE;
		String numero = ConstantesUtil.obtenerNumeroSinComa(dato);
		dev = Pattern.matches(PATRON_DECIMAL, numero);
	
		return dev;
	}
	public static Boolean isAlfaNumericoConPunto(String dato) {
		Boolean dev = Boolean.FALSE;
		
		dev = Pattern.matches(PATRON_ALFA_NUMERICO_PUNTO, dato);
		return dev;
	}
	
	public static Boolean isAlfaNumerico(String dato) {
		Boolean dev = Boolean.FALSE;
		
		dev = Pattern.matches(PATRON_ALFA_NUMERICO, dato);
		return dev;
	}
	public static Boolean isDireccion(String dato) {
		Boolean dev = Boolean.FALSE;
		
		dev = Pattern.matches(PATRON_DIRECCION, dato);
		return dev;
	}

	public static Boolean isEmail(String email) {
		Boolean dev = Boolean.FALSE;
		
		dev = Pattern.matches(PATRON_EMAIL, email);
	
	    return dev;
	}
	
}
