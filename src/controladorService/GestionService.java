package controladorService;

import java.util.Date;
import java.util.HashMap;

import modelo.Categoria;
import modelo.Cliente;
import modelo.Configuracion;
import modelo.Empleado;
import modelo.FacturaHecha;
import modelo.Producto;
import modelo.ProductoCambiado;
import modelo.Venta;
/**
 * 
 * @author Two hands Technology
 *
 */
public interface GestionService {

	public String[][] rellenarTablaCliente();
	public String[][] rellenaTablaProducto();
	public void insertarProducto(Producto producto, String categoria);
	public HashMap<Long, String> cargarComboCategoria();
	public void eliminarProducto(String idProducto);
	public void modificarProducto(Producto producto, String categoria);

	public void insertarCategoria(Categoria categoria);
	public void modificarCategoria(Categoria categoria);
	public String [][] rellenaTablaCategoria();
	public void eliminarCategoria(String categoria);
	
	public String [][] rellenaTablaEmpleados();
	public void insertarEmpleado(Empleado empleado);
	public void modificarEmpleado(Empleado empleado);
	public void eliminarEmpleado(String idEmpleado);
	public Empleado getEmpleadoById(Long empleado);
	public Date getUltimaFechaCierre();
	public void insertarCliente(Cliente cliente);
	public void modificarCliente(Cliente cliente);
	public void eliminarCliente(String idCliente);
	public String[][] rellenarTablaVentas();
	public void insertarFacturaHecha(FacturaHecha facturaHecha);
	public void insertarConfiguracion(Configuracion configuracion);
	public Configuracion getConfiguracion();
	public Long insertarProductoCambiado(ProductoCambiado productoCambiado);
	public String[][] rellenarVentas();
	public void modificarVenta(Venta venta);
	public void eliminarVenta(String idVenta);
	public Empleado obtenerEmpleado();
	public String[][] rellenarCajas();
	public void eliminarCaja(String idCaja);
	public String[][] rellenaEmpleados();
	public String[][] rellenarTablaFacturas();
	public void modificarFacturaHecha(FacturaHecha facturaHecha);
	public void eliminarFacturaHecha(String idFactura);
	
}
