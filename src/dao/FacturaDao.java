package dao;

import java.io.Serializable;
import java.util.List;

import modelo.Factura;
/**
 * 
 * @author Two hands Technology
 *
 */
public interface FacturaDao extends Serializable{

	public Long createFactura(Factura factura);
	public Factura readFactura(Long idFactura);
	public void upDateFactura(Factura factura);
	public void deleteFactura(Factura factura);
	public List<Factura> obtenerFacturas();
}
