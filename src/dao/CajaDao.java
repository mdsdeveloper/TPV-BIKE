package dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import modelo.Caja;
/**
 * 
 * @author Two hands Technology
 *
 */
public interface CajaDao extends Serializable {

	public Long createCaja(Caja caja);
	public Caja readCaja(Long idCaja);
	public void upDateCaja(Caja caja);
	public void deleteCaja(Caja caja);
	public Date getUltimaFechaCierre();
	public List<Caja> obtenerCajas();
}
