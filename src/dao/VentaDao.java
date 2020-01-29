package dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import modelo.Venta;
/**
 * 
 * @author Two hands Technology
 *
 */
public interface VentaDao extends Serializable  {

	public Long createVenta(Venta venta);
	public Venta readVenta(Long idVenta);
	public void upDateVenta(Venta venta);
	public void deleteVenta(Venta venta);
	public List<Venta> obtenerVentas();
	public List<Venta> cerrarCaja(Date ultimoCierre, Date hoy);
	public Integer obtenerNumeroVenta();
}
