package dao;

import java.io.Serializable;
import java.util.List;

import modelo.FacturaHecha;
/**
 * 
 * @author Two hands Technology
 *
 */
public interface FacturaHechaDao extends Serializable {

	public Long createFacturaHecha(FacturaHecha facturaHecha);
	public FacturaHecha readFacturaHecha(Long idFacturaHecha);
	public void upDateFacturaHecha(FacturaHecha facturaHecha);
	public void deleteFacturaHecha(FacturaHecha facturaHecha);
	public Integer obtenerNumeroFactura();
	public List<FacturaHecha> obtenerFacturas();
}
