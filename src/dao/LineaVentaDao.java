package dao;

import java.io.Serializable;
import java.util.List;

import modelo.LineaVenta;
/**
 * 
 * @author Two hands Technology
 *
 */
public interface LineaVentaDao extends Serializable  {

	public Long createLinea(LineaVenta linea);
	public LineaVenta readLineaVenta(Long idLineaVenta);
	public void upDateLineaVenta(LineaVenta linea);
	public void deleteLineaVenta(LineaVenta linea);
	public List<LineaVenta> obtenerLineaVentas();
	public void deleteAll();
}
