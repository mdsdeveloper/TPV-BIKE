package controladorService;

import java.util.Date;
import java.util.List;

import PantallaVentas.TuplaDatos;
import modelo.Categoria;
import modelo.Empleado;
import modelo.Factura;
import modelo.LineaVenta;
import modelo.LineaVentaRealizada;
import modelo.ListaProducto;
import modelo.Producto;
import modelo.ProductoCambiado;
import modelo.ProductosVarios;
import modelo.Venta;
/**
 * 
 * @author Two hands Technology
 *
 */
public interface AppService {

	public Empleado obtenerEmpleado();
	public List<Categoria> obtenerCategorias();
	public List<Producto> obtenerProductos();
	public List<Producto> obtenerProductos(String id);
	public Producto obtenerProducto(String id);
	
	public void insertarLineaVenta(LineaVenta linea);
	public String[][] rellenaTablaVenta();
	public void eliminarLineaById(Long idLineaVenta);
	public void eliminarLineas();
	public LineaVenta obtenerLineaVentasById(Long idLineaVenta);
	public void upDateLineaVenta(LineaVenta lineaModificada);
	public List<LineaVenta> obtenerLineas();
	public Long crearVenta(Venta venta);
	public Categoria obtenerCategoriaGeneral();
	public Producto obtenerProductoGenerico();
	public void modificarProducto(Producto productoNuevo);
	public Long crearLineaVentaRealizada(LineaVentaRealizada lineaVRealizada);
	public void crearListaProducto(ListaProducto listaProducto);
	public List<Venta> hacerCierreCaja(Date ahora, String string);
	public void crearFactura(Factura factura);
	public Integer obtenerNumeroVenta();
	public Integer obtenerNumeroFacturaConCliente();
	public LineaVentaRealizada obtenerLineaVentaRealizadaById(Long idVentaRealizada);
	public List<TuplaDatos> obtenerIdProductoCantidadById(Long idVentaRealizada);
	public ProductoCambiado obtenerProductoCambiadoById(String id);
	public List<Venta> hacerBalanceCaja(Date ahora);
	public Long crearProductosVarios(ProductosVarios productosVarios);
}
