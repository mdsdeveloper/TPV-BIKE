package dao;

import java.io.Serializable;
import java.util.List;

import modelo.Empleado;
/**
 * 
 * @author Two hands Technology
 *
 */
public interface EmpleadoDao extends Serializable {

	public Long createEmpleado(Empleado empleado);
	public Empleado readEmpleado(Long idEmpleado);
	public void upDateEmpleado(Empleado empleado);
	public void deleteEmpleado(Empleado empleado);
	public List<Empleado> obtenerEmpleados();
	public List<Empleado> obtenerEmpleadoByName(String name);
}
