package controladorService;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import dao.CajaDao;
import dao.CajaDaoImpl;
import dao.CategoriaDao;
import dao.CategoriaDaoImpl;
import dao.ClienteDao;
import dao.ClienteDaoImpl;
import dao.ConfiguracionDao;
import dao.ConfiguracionDaoImpl;
import dao.EmpleadoDao;
import dao.EmpleadoDaoImpl;
import dao.FacturaDao;
import dao.FacturaDaoImpl;
import dao.FacturaHechaDao;
import dao.FacturaHechaDaoImpl;
import dao.ProductoCambiadoDao;
import dao.ProductoCambiadoDaoImpl;
import dao.ProductoDao;
import dao.ProductoDaoImpl;
import dao.VentaDao;
import dao.VentaDaoImpl;
import modelo.Caja;
import modelo.Categoria;
import modelo.Cliente;
import modelo.Configuracion;
import modelo.Empleado;
import modelo.Factura;
import modelo.FacturaHecha;
import modelo.Producto;
import modelo.ProductoCambiado;
import modelo.Venta;
import util.ConstantesUtil;
/**
 * 
 * @author Two hands Technology
 *
 */
public class GestionServiceImpl implements GestionService{

	private ProductoDao productoDao = new ProductoDaoImpl();
	private CategoriaDao categoriaDao = new CategoriaDaoImpl();
	private EmpleadoDao empleadoDao = new EmpleadoDaoImpl();
	private CajaDao cajaDao = new CajaDaoImpl();
	private ClienteDao clienteDao = new ClienteDaoImpl();
	private FacturaDao facturaDao = new FacturaDaoImpl();
	private FacturaHechaDao facturaHechaDao = new FacturaHechaDaoImpl();
	private ConfiguracionDao configuracionDao = new ConfiguracionDaoImpl();
	private ProductoCambiadoDao productoCambiadoDao = new ProductoCambiadoDaoImpl();
	private VentaDao ventaDao = new VentaDaoImpl();
	
	
	@Override
	public String[][] rellenaTablaProducto() {
		
		List<Producto> lista = null;
		int i=0;
		Producto producto;
		lista = productoDao.obtenerProductos();
		
		String[][] dev = new String[lista.size()][8];
		
		if(lista != null){
			Iterator<Producto> it = lista.iterator();
			while(it.hasNext()){
				producto = it.next();						
				dev[i][0] = producto.getIdProducto().toString();
				dev[i][1] = producto.getNombre();
				dev[i][2] = producto.getMarca();
				dev[i][3] = producto.getDescripcion();
				dev[i][4] = obtenerCategoria(producto.getCategoria());
				dev[i][5] = producto.getPrecio() != null ? producto.getPrecio().toString() : null;
				dev[i][6] = String.valueOf(producto.getStock());
				dev[i][7] = producto.getImagen();
				i++;
			}
		}
		
		return dev;
		
	}
	private String obtenerCategoria(Categoria categoria) {
		String dev = null;
		Categoria cat = null;
		cat = categoriaDao.readCategoria(categoria.getIdCategoria());
		dev = cat.getNombre();
		return dev;
	}
	
	public void insertarProducto(Producto producto, String categoria){
		Categoria cat = categoriaDao.obtenerCategoriaByName(categoria);
		producto.setCategoria(cat);
		productoDao.createProducto(producto);
		
	}
	@Override
	public HashMap<Long, String> cargarComboCategoria() {
		HashMap<Long, String> dev = new HashMap<>();
		List<Categoria> listaCategoria;
		listaCategoria = categoriaDao.obtenerCategorias();
		
		if(listaCategoria != null){
			for (Categoria categoria : listaCategoria) {
				if(!categoria.getNombre().equals(ConstantesUtil.varios)){
					dev.put(categoria.getIdCategoria(), categoria.getNombre());
				}
			}
		}
		
		return dev;
	}
	@Override
	public void eliminarProducto(String idProducto) {
		Long id = Long.parseLong(idProducto);
		Producto producto;
		producto = productoDao.readProducto(id);
		if(producto != null){
			productoDao.deleteProducto(producto);
		}
	}
	@Override
	public void modificarProducto(Producto productoNuevo, String categoria) {
		Producto productoActual;
		productoActual = productoDao.readProducto(productoNuevo.getIdProducto());
		Categoria catNueva = categoriaDao.obtenerCategoriaByName(categoria);
		
		if(productoActual != null && catNueva != null){
			productoActual.setNombre(productoNuevo.getNombre());
			productoActual.setMarca(productoNuevo.getMarca());
			productoActual.setDescripcion(productoNuevo.getDescripcion());
			productoActual.setPrecio(productoNuevo.getPrecio());
			productoActual.setStock(productoNuevo.getStock());
			productoActual.setCategoria(catNueva);
			productoActual.setImagen(productoNuevo.getImagen());
			
			productoDao.upDateProducto(productoActual);
		}
	}
	
	@Override
	public void insertarCategoria(Categoria categoria) {
		categoriaDao.createCategoria(categoria);		
	}
	@Override
	public void modificarCategoria(Categoria categoriaNueva) {
		Categoria categoriaActual = categoriaDao.readCategoria(categoriaNueva.getIdCategoria());
		
		if(categoriaNueva != null && categoriaActual != null){
			categoriaActual.setNombre(categoriaNueva.getNombre());
			categoriaActual.setDescripcion(categoriaNueva.getDescripcion());
			categoriaActual.setImagen(categoriaNueva.getImagen());
			
			categoriaDao.upDateCategoria(categoriaActual);
		}
		
	}
	@Override
	public String[][] rellenaTablaCategoria() {
		List<Categoria> lista = null;
		String[][] dev = null;
		int i=0;
		Categoria categoria;
		lista = categoriaDao.obtenerCategorias();
		if(lista != null){
			dev = new String[lista.size()][4];
		
			Iterator<Categoria> it = lista.iterator();
			while(it.hasNext()){
				categoria = it.next();						
				dev[i][0] = categoria.getIdCategoria().toString();
				dev[i][1] = categoria.getNombre();
				dev[i][2] = categoria.getDescripcion();
				dev[i][3] = categoria.getImagen();
				i++;
			}
		}
		
		return dev;
		
	}
	@Override
	public void eliminarCategoria(String idCategoria) {
		Long id = Long.parseLong(idCategoria);
		Categoria categoria;
		categoria = categoriaDao.readCategoria(id);
		if(categoria != null){
			categoriaDao.deleteCategoria(categoria);
		}		
	}
	@Override
	public String[][] rellenaTablaEmpleados() {
		List<Empleado> lista = null;
		int i=0;
		Empleado empleado;
		lista = empleadoDao.obtenerEmpleados();
		
		String[][] dev = new String[lista.size()][6];
		
		if(lista != null){
			Iterator<Empleado> it = lista.iterator();
			while(it.hasNext()){
				empleado = it.next();						
				dev[i][0] = empleado.getIdEmpleado().toString();
				dev[i][1] = empleado.getNombre();
				dev[i][2] = empleado.getCargo();
				dev[i][3] = empleado.getDni();
				dev[i][4] = empleado.getEmail();
				dev[i][5] = empleado.getTelefono();
				i++;
			}
		}
		
		return dev;
	}
	@Override
	public String[][] rellenaEmpleados() {
		List<Empleado> lista = null;
		int i=0;
		Empleado empleado;
		lista = empleadoDao.obtenerEmpleados();
		
		String[][] dev = new String[lista.size()][7];
		
		if(lista != null){
			Iterator<Empleado> it = lista.iterator();
			while(it.hasNext()){
				empleado = it.next();						
				dev[i][0] = empleado.getIdEmpleado().toString();
				dev[i][1] = empleado.getNombre();
				dev[i][2] = empleado.getCargo();
				dev[i][3] = empleado.getDni();
				dev[i][4] = empleado.getEmail();
				dev[i][5] = empleado.getTelefono();
				dev[i][6] = empleado.getPassword();
				i++;
			}
		}
		
		return dev;
	}
	
	@Override
	public void insertarEmpleado(Empleado empleado) {
		empleadoDao.createEmpleado(empleado);		
	}
	@Override
	public void modificarEmpleado(Empleado empleadoNuevo) {
		Empleado empleadoActual = null;
		if(empleadoNuevo != null){
			empleadoActual = empleadoDao.readEmpleado(empleadoNuevo.getIdEmpleado());
		}
		
		if(empleadoActual != null){
			empleadoActual.setNombre(empleadoNuevo.getNombre());
			empleadoActual.setCargo(empleadoNuevo.getCargo());
			empleadoActual.setDni(empleadoNuevo.getDni());
			empleadoActual.setTelefono(empleadoNuevo.getTelefono());
			empleadoActual.setEmail(empleadoNuevo.getEmail());
			empleadoActual.setPassword(!empleadoNuevo.getPassword().equals("") ? empleadoNuevo.getPassword() : empleadoActual.getPassword());
			
			empleadoDao.upDateEmpleado(empleadoActual);
		}
		
	}
	@Override
	public void eliminarEmpleado(String idEmpleado) {
		Long id = Long.parseLong(idEmpleado);
		Empleado empleado;
		empleado = empleadoDao.readEmpleado(id);
		if(empleado != null){
			empleadoDao.deleteEmpleado(empleado);
		}
		
	}
	@Override
	public Empleado getEmpleadoById(Long empleado) {
		Empleado dev = null;
		dev = empleadoDao.readEmpleado(empleado);
		return dev;
	}
	@Override
	public Date getUltimaFechaCierre() {
		Date dev = null;
		dev = cajaDao.getUltimaFechaCierre();
		
		return dev;
	}
	@Override
	public void insertarCliente(Cliente cliente) {
		clienteDao.createCliente(cliente);
		
	}
	@Override
	public void modificarCliente(Cliente clienteNuevo) {
		Cliente clienteActual = null;
		if(clienteNuevo != null ){
			clienteActual = clienteDao.readCliente(clienteNuevo.getIdCliente());
		}
		if(clienteActual != null){
			clienteActual.setNombre(clienteNuevo.getNombre());
			clienteActual.setDniNif(clienteNuevo.getDniNif());
			clienteActual.setEmail(clienteNuevo.getEmail());
			clienteActual.setTelefono(clienteNuevo.getTelefono());
			clienteActual.setDireccion(clienteNuevo.getDireccion());
			
			clienteDao.upDateCliente(clienteActual);
		}		
	}
	@Override
	public void eliminarCliente(String idCliente) {
		Long id = Long.parseLong(idCliente);
		Cliente cliente;
		cliente = clienteDao.readCliente(id);
		if(cliente != null){
			clienteDao.deleteCliente(cliente);
		}		
	}
	@Override
	public String[][] rellenarTablaCliente() {
		List<Cliente> lista = null;
		int i=0;
		Cliente cliente;
		lista = clienteDao.obtenerClientes() != null ? clienteDao.obtenerClientes() : null;
		
		String[][] dev = new String[lista.size()][6];
		
		if(lista != null){
			Iterator<Cliente> it = lista.iterator();
			while(it.hasNext()){
				cliente = it.next();						
				dev[i][0] = cliente.getIdCliente().toString();
				dev[i][1] = cliente.getNombre();
				dev[i][2] = cliente.getDniNif();
				dev[i][3] = cliente.getTelefono();
				dev[i][4] = cliente.getEmail();
				dev[i][5] = cliente.getDireccion();
				i++;
			}
		}
		
		return dev;
	}
	@Override
	public String[][] rellenarTablaVentas() {
		List<Venta> lista = null;
		int i=0;
		Venta venta;
		Long idEmpleado;
		Empleado empleado;
		lista = ventaDao.obtenerVentas() != null ? ventaDao.obtenerVentas() : null;
		
		String[][] dev = new String[lista.size()][8];
		
		if(lista != null){
			Iterator<Venta> it = lista.iterator();
			
			while(it.hasNext()){
				venta = it.next();						
				dev[i][0] = venta.getIdVenta().toString();
				dev[i][1] = venta.getFecha().toString();
				idEmpleado = venta.getEmpleado();
				empleado = empleadoDao.readEmpleado(idEmpleado);
				dev[i][2] = empleado.getNombre();
				dev[i][3] = venta.getTotal().toString();
				dev[i][4] = venta.getImporte().toString();
				dev[i][5] = venta.getIdVenta().toString();
				dev[i][6] = venta.getIdLineaVentaRealizada().toString();
				dev[i][7] = venta.getFormaPago().toString();
				i++;
			}
		}
		
		return dev;
	}
	@Override
	public String[][] rellenarTablaFacturas() {
		List<FacturaHecha> lista = null;
		int i=0;
		FacturaHecha facturaHecha;
		lista = facturaHechaDao.obtenerFacturas() != null ? facturaHechaDao.obtenerFacturas() : null;
		
		String [][] dev = new String[lista.size()][17];
		
		if(lista != null){
			Iterator<FacturaHecha> it = lista.iterator();
			while(it.hasNext()){
				facturaHecha = it.next();
				dev[i][0] = facturaHecha.getIdFactura().toString();
				dev[i][1] = facturaHecha.getNombreCliente();
				dev[i][2] = facturaHecha.getDniNifCliente();
				dev[i][3] = facturaHecha.getTelefonoCliente();
				dev[i][4] = facturaHecha.getEmailCliente();
				dev[i][5] = facturaHecha.getDireccionCliente();
				dev[i][6] = facturaHecha.getFecha().toString();
				dev[i][7] = facturaHecha.getEmpleado();
				dev[i][8] = facturaHecha.getFormaPago() != null ? "Tarjeta" : "Efectivo";
				dev[i][9] = facturaHecha.getNumero().toString();
				dev[i][10] = facturaHecha.getBaseImponible().toString();
				dev[i][11] = facturaHecha.getNombre();
				dev[i][12] = facturaHecha.getRazonSocial();
				dev[i][13] = facturaHecha.getDniNif();
				dev[i][14] = facturaHecha.getDireccion();
				dev[i][15] = facturaHecha.getTelefono();
				dev[i][16] = facturaHecha.getTotal().toString();
				i++;						
			}
		}
		return dev;
	}
	
	@Override
	public void insertarFacturaHecha(FacturaHecha facturaHecha) {
		facturaHechaDao.createFacturaHecha(facturaHecha);		
	}
	@Override
	public void insertarConfiguracion(Configuracion configuracion) {
		Configuracion conf = null;
		
		
		conf = configuracionDao.getConfiguracion();
		if(conf == null){
			configuracionDao.createConfiguracion(configuracion);
		}else{
			conf.setCabeceraFactura(configuracion.getCabeceraFactura());
			conf.setCifNif(configuracion.getCifNif());
			conf.setDireccionEmpresa(configuracion.getDireccionEmpresa());
			conf.setDirectorioCierre(configuracion.getDirectorioCierre());
			conf.setDirectorioFactura(configuracion.getDirectorioFactura());
			conf.setEmailEmpresa(configuracion.getEmailEmpresa());
			conf.setIcono(configuracion.getIcono());
			conf.setInicialCaja(configuracion.getInicialCaja());
			conf.setNombreEmpresa(configuracion.getNombreEmpresa());
			conf.setPieFactura(configuracion.getPieFactura());
			conf.setRazonSocial(configuracion.getRazonSocial());
			conf.setTelefonoEmpresa(configuracion.getTelefonoEmpresa());
			
			configuracionDao.upDateConfiguracion(conf);
		}
		
	}
	@Override
	public Configuracion getConfiguracion() {
		Configuracion dev = configuracionDao.getConfiguracion();
		return dev;
	}
	@Override
	public Long insertarProductoCambiado(ProductoCambiado productoCambiado) {
		Long dev = productoCambiadoDao.createProductoCambiado(productoCambiado);
		return dev;
	}
	@Override
	public String[][] rellenarVentas() {
		List<Venta> lista = null;
		int i=0;
		lista = ventaDao.obtenerVentas();
		Venta venta;
		String[][] dev = new String[lista.size()][6];
		
		if(lista != null){
			Iterator<Venta> it = lista.iterator();
			while(it.hasNext()){
				venta = it.next();						
				dev[i][0] = venta.getIdVenta().toString();
				dev[i][1] = empleadoDao.readEmpleado(venta.getEmpleado()).getNombre();
				dev[i][2] = venta.getFecha().toString();
				dev[i][3] = venta.getFormaPago() == false ? "Efectivo" : "Tarjeta";
				dev[i][4] = venta.getImporte().toString();
				dev[i][5] = venta.getTotal().toString();
				i++;
			}
		}
		
		return dev;
	}
	
	@Override
	public void modificarVenta(Venta venta) {
		Venta ventaActual = null;
		if(venta != null ){
			ventaActual = ventaDao.readVenta(venta.getIdVenta());
		}
		if(ventaActual != null){
			ventaActual.setImporte(venta.getImporte());
			ventaActual.setTotal(venta.getTotal());
			
			ventaDao.upDateVenta(ventaActual);
		}
		
	}
	@Override
	public void eliminarVenta(String idVenta) {
		Long id = Long.parseLong(idVenta);
		Venta venta;
		venta = ventaDao.readVenta(id);
		if(venta != null){
			ventaDao.deleteVenta(venta);
		}
		
	}
	@Override
	public Empleado obtenerEmpleado() {
		List<Empleado> lista = null;
		Empleado dev = null;
		
		lista = empleadoDao.obtenerEmpleados();
		
		if(lista != null && !lista.isEmpty()){
			dev = lista.iterator().next();
		}
		
		return dev;
	}
	@Override
	public String[][] rellenarCajas() {
		List<Caja> lista = null;
		int i=0;
		lista = cajaDao.obtenerCajas();
		Caja caja;
		String[][] dev = new String[lista.size()][6];
		
		if(lista != null){
			Iterator<Caja> it = lista.iterator();
			while(it.hasNext()){
				caja = it.next();						
				dev[i][0] = caja.getIdCaja().toString();
				dev[i][1] = caja.getEmpleado();
				dev[i][2] = caja.getFechaCierre().toString();
				dev[i][3] = caja.getTarjeta().toString();
				dev[i][4] = caja.getEfectivo().toString();
				dev[i][5] = caja.getTotalCierre().toString();
				i++;
			}
		}
		
		return dev;
	}
	@Override
	public void eliminarCaja(String idCaja) {
		Long id = Long.parseLong(idCaja);
		Caja caja;
		caja = cajaDao.readCaja(id);
		if(caja != null){
			cajaDao.deleteCaja(caja);
		}
		
	}
	@Override
	public void modificarFacturaHecha(FacturaHecha facturaNueva) {
		FacturaHecha facturaActual= null;
		if(facturaNueva != null ){
			facturaActual = facturaHechaDao.readFacturaHecha(facturaNueva.getIdFactura());
		}
		if(facturaActual != null){
			facturaActual.setNombreCliente(facturaNueva.getNombreCliente());
			facturaActual.setDniNifCliente(facturaNueva.getDniNifCliente());
			facturaActual.setEmailCliente(facturaNueva.getEmailCliente());
			facturaActual.setTelefonoCliente(facturaNueva.getTelefonoCliente());
			facturaActual.setDireccionCliente(facturaNueva.getDireccionCliente());
			facturaActual.setFecha(facturaNueva.getFecha());
			facturaActual.setEmpleado(facturaNueva.getEmpleado());
			facturaActual.setBaseImponible(facturaNueva.getBaseImponible());
			facturaActual.setTotal(facturaNueva.getTotal());
			facturaActual.setNumero(facturaNueva.getNumero());
			
			
			facturaHechaDao.upDateFacturaHecha(facturaActual);
		}
		
	}
	@Override
	public void eliminarFacturaHecha(String idFactura) {
		Long id = Long.parseLong(idFactura);
		FacturaHecha facturaHecha;
		facturaHecha = facturaHechaDao.readFacturaHecha(id);
		if(facturaHecha != null){
			facturaHechaDao.deleteFacturaHecha(facturaHecha);
		}
		
	}
	

}
