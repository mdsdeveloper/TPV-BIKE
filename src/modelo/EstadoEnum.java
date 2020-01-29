package modelo;
/**
 * 
 * @author Two hands Technology
 *
 */
public enum EstadoEnum {	
	FINALIZADA,
	PROCESO;
	public EstadoEnum getEstado(EstadoEnum estado){
		return estado == EstadoEnum.FINALIZADA ? EstadoEnum.FINALIZADA : EstadoEnum.PROCESO; 
	}
	
}
