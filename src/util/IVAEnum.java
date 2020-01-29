package util;
/**
 * 
 * @author Two hands Technology
 *
 */
public enum IVAEnum {
	DIEZ(10,"10"),
	VEINTIUNO(21,"21"),
	CUATRO(4,"4");
	
	private String valor;
	private int code;
	
	private IVAEnum(int code, String value) {
		this.code = code;
		this.valor = value;
	}
	
	public String getValor(){
		return valor;
	}
	public int getCodigo(){
		return code;
	}
}
