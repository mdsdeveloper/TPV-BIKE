package dao;

import modelo.Configuracion;
/**
 * 
 * @author Two hands Technology
 *
 */
public interface ConfiguracionDao {

	public Long createConfiguracion(Configuracion configuracion);
	public Configuracion readConfiguracion(Long idConfiguracion);
	public void upDateConfiguracion(Configuracion configuracion);
	public void deleteConfiguracion(Configuracion configuracion);
	public Configuracion getConfiguracion();
}
