package controladorService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import PantallaVentas.TuplaDatos;
import dao.CajaDao;
import dao.CajaDaoImpl;
import dao.CategoriaDao;
import dao.CategoriaDaoImpl;
import dao.EmpleadoDao;
import dao.EmpleadoDaoImpl;
import dao.FacturaDao;
import dao.FacturaDaoImpl;
import dao.FacturaHechaDao;
import dao.FacturaHechaDaoImpl;
import dao.LineaVentaDao;
import dao.LineaVentaDaoImpl;
import dao.LineaVentaRealizadaDao;
import dao.LineaVentaRealizadaDaoImpl;
import dao.ListaProductoDao;
import dao.ListaProductoDaoImpl;
import dao.ProductoCambiadoDao;
import dao.ProductoCambiadoDaoImpl;
import dao.ProductoDao;
import dao.ProductoDaoImpl;
import dao.ProductosVariosDao;
import dao.ProductosVariosDaoImpl;
import dao.VentaDao;
import dao.VentaDaoImpl;
import modelo.Caja;
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
import util.ConstantesUtil;
import util.UtilService;
import util.Validador;
/**
 * 
 * @author Two hands Technology
 *
 */
public class AppServiceImpl implements AppService{

	private EmpleadoDao empleadoDao = new EmpleadoDaoImpl();
	private CategoriaDao categoriaDao = new CategoriaDaoImpl();
	private ProductoDao productoDao = new ProductoDaoImpl();
	private LineaVentaDao lineaVentaDao = new LineaVentaDaoImpl();
	private VentaDao ventaDao = new VentaDaoImpl();
	private LineaVentaRealizadaDao lineaVRealizadaDao = new LineaVentaRealizadaDaoImpl();
	private ListaProductoDao listaProductoDao = new ListaProductoDaoImpl();
	private GestionService gestionService = new GestionServiceImpl();
	private CajaDao cajaDao = new CajaDaoImpl();
	private FacturaDao facturaDao = new FacturaDaoImpl();
	private FacturaHechaDao facturaHechaDao = new FacturaHechaDaoImpl();
	private ProductoCambiadoDao productoCambiadoDao = new ProductoCambiadoDaoImpl();
	private ProductosVariosDao productosVariosDao = new ProductosVariosDaoImpl();
	
	@Override
	public Empleado obtenerEmpleado() {
		Empleado dev = null;
		Empleado empleadoSistema = UtilService.getEmpleadoLogado();
		
		if(empleadoSistema != null){
			dev = empleadoSistema;
		}
		return dev;
	}

	@Override
	public List<Categoria> obtenerCategorias() {
		List<Categoria> dev = new ArrayList<>();
		List<Categoria> aux = null;
		aux = categoriaDao.obtenerCategorias();
		
		for (Categoria categoria : aux) {
			if(categoria.getNombre() != null && !categoria.getNombre().equals(ConstantesUtil.varios)){
				dev.add(categoria);
			}
		}
		
		return dev;
	}

	@Override
	public List<Producto> obtenerProductos() {
		List<Producto> dev = null;
		dev = productoDao.obtenerProductos();
		return dev;
	}

	@Override
	public List<Producto> obtenerProductos(String id) {
		List<Producto> dev = null;
		dev = productoDao.obtenerProductosByCategoria(id);
		return dev;
	}

	@Override
	public Producto obtenerProducto(String id) {
		Producto dev = null;
		
		dev = productoDao.readProducto(Long.parseLong(id));
		return dev;
	}

	@Override
	public void insertarLineaVenta(LineaVenta linea) {
		
		lineaVentaDao.createLinea(linea);
	}

	@Override
	public String[][] rellenaTablaVenta() {
		List<LineaVenta> lista = null;
		List<Producto> listaProductos = new ArrayList<>();
		List<ProductoCambiado> listaProductosCambiados = new ArrayList<>();
		String[][] dev = null;
		LineaVenta linea;
		Producto p;
		ProductoCambiado pc;
		int i = 0;
		lista = lineaVentaDao.obtenerLineaVentas();
		
		if(lista != null){
			
			for (LineaVenta lineaVenta : lista) {
				if(!lineaVenta.getProductoCambiado()){
					listaProductos.add(productoDao.readProducto(lineaVenta.getIdProducto()));
				}else{
					listaProductosCambiados.add(productoCambiadoDao.readProductoCambiado(lineaVenta.getIdProducto()));
				}
				
			}	
			
			dev = new String[lista.size()][5];
			Iterator<LineaVenta> it = lista.iterator();
			Iterator<Producto> itP = listaProductos.iterator();
			Iterator<ProductoCambiado> itPC = listaProductosCambiados.iterator();
			while(it.hasNext()){
				linea = it.next();
				if(linea.getProductoCambiado()){
					pc = itPC.next();
					dev[i][0] = linea.getIdLineaVenta().toString();
					dev[i][1] = pc.getNombre();
					dev[i][2] = pc.getPrecio() != null ? pc.getPrecio().toString() : linea.getImporte().toString();
					dev[i][3] = linea.getCantidad().toString();
					dev[i][4] = linea.getTotal().toString();
				}else{
					p = itP.next();
					dev[i][0] = linea.getIdLineaVenta().toString();
					dev[i][1] = p.getNombre();
					dev[i][2] = p.getPrecio() != null ? p.getPrecio().toString() : linea.getImporte().toString();
					dev[i][3] = linea.getCantidad().toString();
					dev[i][4] = linea.getTotal().toString();
				}
				
				i++;
			}
		}
		return dev;
	}

	@Override
	public void eliminarLineaById(Long idLineaVenta) {
		LineaVenta lineaVenta =	lineaVentaDao.readLineaVenta(idLineaVenta);
		lineaVentaDao.deleteLineaVenta(lineaVenta);
		
	}

	@Override
	public void eliminarLineas() {
		lineaVentaDao.deleteAll();
		
	}

	@Override
	public LineaVenta obtenerLineaVentasById(Long idLineaVenta) {
		LineaVenta linea = lineaVentaDao.readLineaVenta(idLineaVenta);
		return linea;
	}

	@Override
	public void upDateLineaVenta(LineaVenta lineaModificada) {
		lineaVentaDao.upDateLineaVenta(lineaModificada);
		
	}

	@Override
	public List<LineaVenta> obtenerLineas() {
		List<LineaVenta> dev;
		dev = lineaVentaDao.obtenerLineaVentas();
		return dev;
	}

	@Override
	public Long crearVenta(Venta venta) {
		Long dev;
		dev = ventaDao.createVenta(venta);	
		return dev;
	}

	@Override
	public Categoria obtenerCategoriaGeneral() {
		Categoria dev = null;
		dev = categoriaDao.obtenerCategoriaGeneral();
		return dev;
	}

	@Override
	public Producto obtenerProductoGenerico() {
		Producto dev = productoDao.obtenerProductoGenerico();
		
		return dev;
	}

	@Override
	public void modificarProducto(Producto productoNuevo) {
		Producto actual = null;
		actual = productoDao.readProducto(productoNuevo.getIdProducto());
		if(productoNuevo != null && actual !=null){
			actual.setPrecio(productoNuevo.getPrecio());
			actual.setStock(productoNuevo.getStock());
			actual.setDescripcion(productoNuevo.getDescripcion());
			productoDao.upDateProducto(actual);
		}
		
	}

	@Override
	public Long crearLineaVentaRealizada(LineaVentaRealizada lineaVRealizada) {
		Long dev = null;
		dev = lineaVRealizadaDao.createLineaRealizada(lineaVRealizada);	
		return dev;
	}

	@Override
	public void crearListaProducto(ListaProducto listaProducto) {
		listaProductoDao.createListaProducto(listaProducto);
		
	}

	@Override
	public List<Venta> hacerCierreCaja(Date ahora, String recuento) {
		List<Venta> dev= null;
		BigDecimal sumaTotal = BigDecimal.ZERO;
		BigDecimal totalTarjeta = BigDecimal.ZERO;
		BigDecimal totalEfectivo = BigDecimal.ZERO;
		BigDecimal totalDescuadre = BigDecimal.ZERO;
		BigDecimal tatalRecuento = BigDecimal.ZERO;
		Integer numeroVentas = 0;
		Date ultimoCierre = gestionService.getUltimaFechaCierre();
		
		if(ultimoCierre == null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.DAY_OF_YEAR, -1);
			ultimoCierre = calendar.getTime();
		}
		
		dev = ventaDao.cerrarCaja(ultimoCierre, ahora);
		if(!dev.isEmpty()){
			for (Venta venta : dev) {
				 sumaTotal = sumaTotal.add(venta.getTotal());
			        if(venta.getFormaPago() == false){
			        	totalEfectivo = totalEfectivo.add(venta.getTotal());
			        }else{
			        	totalTarjeta = totalTarjeta.add(venta.getTotal());
			        } 
			      numeroVentas ++;
			}
			
			if(!recuento.equals("") && Validador.isDecimal(recuento)){
				tatalRecuento = new BigDecimal(recuento);
				totalDescuadre = tatalRecuento.subtract(totalEfectivo);
			}
			Empleado empleado = UtilService.getEmpleadoLogado();
			
			Caja cajaDiaria = new Caja();
			cajaDiaria.setDescuadre(totalDescuadre);
			cajaDiaria.setEfectivo(totalEfectivo);
			cajaDiaria.setFechaCierre(ahora);
			cajaDiaria.setNumero(numeroVentas);
			cajaDiaria.setTarjeta(totalTarjeta);
			cajaDiaria.setTotalCierre(sumaTotal);
			cajaDiaria.setEmpleado(empleado != null ? empleado.getNombre() : "empleado");
			
			cajaDao.createCaja(cajaDiaria);
			ConstantesUtil.setFechaCierre(ahora);
		}
		return dev;
	}

	@Override
	public void crearFactura(Factura factura) {
		
		facturaDao.createFactura(factura);
		
	}

	@Override
	public Integer obtenerNumeroVenta() {
		Integer dev;
		dev = ventaDao.obtenerNumeroVenta();
		return dev;
	}

	@Override
	public Integer obtenerNumeroFacturaConCliente() {
		Integer dev;
		dev = facturaHechaDao.obtenerNumeroFactura();
		return dev;
	}

	@Override
	public LineaVentaRealizada obtenerLineaVentaRealizadaById(Long idVentaRealizada) {
		LineaVentaRealizada lineaVentaRealizada = lineaVRealizadaDao.readLineaVentaRealizada(idVentaRealizada);
		return lineaVentaRealizada;
	}

	@Override
	public List<TuplaDatos> obtenerIdProductoCantidadById(Long idVentaRealizada) {
		List<TuplaDatos> dev = new ArrayList<>();
		List<ListaProducto> listaProductosVendidos = listaProductoDao.obtenerListaProducto();// añadir en obtener lista el flag de productoCambiado true o false
		List<Producto> listaProductos = productoDao.obtenerProductos();
		List<ProductoCambiado> listaProductoCambiado = productoCambiadoDao.obtenerProductos();
		ProductosVarios ProductosVarios;
		
		if(listaProductosVendidos != null && !listaProductosVendidos.isEmpty()){
			for (ListaProducto listaProducto : listaProductosVendidos) {
				if(listaProducto.getLineaVentaRealizada().getIdLineaVenta().equals(idVentaRealizada)){
					TuplaDatos nuevaTupla = new TuplaDatos();
					nuevaTupla.setIdProducto(listaProducto.getIdProducto());
					nuevaTupla.setCantidadProducto(listaProducto.getCantidad());
					nuevaTupla.setIsProductoCambiado(listaProducto.isProductoCambiado());
					if(listaProducto.getIdProducto().equals(ConstantesUtil.idProductosVarios) && !listaProducto.isProductoCambiado()){
						ProductosVarios = productosVariosDao.obtenerProductoVarios(listaProducto.getIdProducto(), idVentaRealizada, listaProducto.getFecha());
						nuevaTupla.setNombreProducto(ConstantesUtil.varios);
						nuevaTupla.setPrecioProducto(ProductosVarios.getPrecio());
					}
					dev.add(nuevaTupla);					
				}
			}
		}
		// me quede aqui, si es un producto varios no tiene precio entonces salta una excepcion tengo que pasarle el precio o ver como hago...
		if(listaProductos != null && !listaProductos.isEmpty()){
			
			if(listaProductoCambiado != null && !listaProductoCambiado.isEmpty()){
				for (ProductoCambiado productoCambiado2 : listaProductoCambiado) {
					for (TuplaDatos tupla : dev) {
						if(productoCambiado2.getIdProducto().equals(tupla.getIdProducto()) && (tupla.getPrecioProducto() == null) && tupla.isProductoCambiado()){
							tupla.setPrecioProducto(productoCambiado2.getPrecio());
							tupla.setNombreProducto(productoCambiado2.getNombre());
						}
					}
				}
			}			
			
			for (Producto producto : listaProductos) {
				for (TuplaDatos tupla : dev) {
					if(producto.getIdProducto().equals(tupla.getIdProducto()) && !tupla.isProductoCambiado()){
						if(!producto.getIdProducto().equals(ConstantesUtil.idProductosVarios)){
							tupla.setPrecioProducto(producto.getPrecio());
							tupla.setNombreProducto(producto.getNombre());
						}
						
					}
				}
			}
			
		}
		return dev;
	}

	@Override
	public ProductoCambiado obtenerProductoCambiadoById(String id) {
		ProductoCambiado dev = null;
		dev = productoCambiadoDao.readProductoCambiado(Long.valueOf(id));
		return dev;
	}

	@Override
	public List<Venta> hacerBalanceCaja(Date ahora) {
		List<Venta> dev= null;
		BigDecimal sumaTotal = BigDecimal.ZERO;
		BigDecimal totalTarjeta = BigDecimal.ZERO;
		BigDecimal totalEfectivo = BigDecimal.ZERO;
		Date ultimoCierre = gestionService.getUltimaFechaCierre();
		
		if(ultimoCierre == null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.DAY_OF_YEAR, -1);
			ultimoCierre = calendar.getTime();
		}
		
		dev = ventaDao.cerrarCaja(ultimoCierre, ahora);
		if(!dev.isEmpty()){
			for (Venta venta : dev) {
				 sumaTotal = sumaTotal.add(venta.getTotal());
			        if(venta.getFormaPago() == false){
			        	totalEfectivo = totalEfectivo.add(venta.getTotal());
			        }else{
			        	totalTarjeta = totalTarjeta.add(venta.getTotal());
			        } 			    
			}			
		}
		return dev;
	}

	@Override
	public Long crearProductosVarios(ProductosVarios productosVarios) {
		Long dev;
		
		dev = productosVariosDao.createProductosVarios(productosVarios);
		
		return dev;
		
	}

}
