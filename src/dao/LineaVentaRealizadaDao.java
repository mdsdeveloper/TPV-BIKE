package dao;
/**
 * 
 * @author Two hands solution
 *
 */

import java.io.Serializable;

import modelo.LineaVentaRealizada;
/**
 * 
 * @author Two hands Technology
 *
 */
public interface LineaVentaRealizadaDao extends Serializable{

	public Long createLineaRealizada(LineaVentaRealizada lineaVentaRealizada);
	public LineaVentaRealizada readLineaVentaRealizada(Long idLineaVentaRealizada);
	public void upDateLineaVentaRealizada(LineaVentaRealizada lineaVentaRealizada);
	public void deleteLineaVentaRealizada(LineaVentaRealizada lineaVentaRealizada);
	
}
