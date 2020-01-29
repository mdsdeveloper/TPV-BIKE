package PantallaVentas;

import dao.CategoriaDao;
import dao.CategoriaDaoImpl;
import dao.EmpleadoDao;
import dao.EmpleadoDaoImpl;
import dao.LineaVentaDao;
import dao.LineaVentaDaoImpl;
import dao.ProductoDao;
import dao.ProductoDaoImpl;
import dao.VentaDao;
import dao.VentaDaoImpl;
import modelo.Categoria;
import modelo.Producto;
import util.ConstantesUtil;
/**
 * 
 * @author Two hands Technology
 *
 */
public class Main {

	public static void main(String[] args) {
		
		CategoriaDao categoriaDao = new CategoriaDaoImpl();
		ProductoDao productoDao = new ProductoDaoImpl();
		LineaVentaDao lineaDao = new LineaVentaDaoImpl();
		VentaDao ventaDao = new VentaDaoImpl();
		EmpleadoDao empleadoDao = new EmpleadoDaoImpl();
//		****************************************************categorias****************************
		Categoria c0 = new Categoria();
//		Categoria c1 = new Categoria();
//		Categoria c2 = new Categoria();
//		Categoria c3 = new Categoria();
		c0.setNombre(ConstantesUtil.varios);
		c0.setDescripcion("Genérica");
//		
//		c1.setNombre("Bicicletas man");
//		c1.setDescripcion("Bicicletas para hombres");
//		
//		c2.setNombre("Bicicletas girl");
//		c2.setDescripcion("Biclicletas para mujeres");
//		
//		c3.setNombre("Bicicletas niños");
//		c3.setDescripcion("Bicicletas para niños");
		
		categoriaDao.createCategoria(c0);
//		categoriaDao.createCategoria(c1);
//		categoriaDao.createCategoria(c2);
//		categoriaDao.createCategoria(c3);
//		****************************************************productos********************************		
		Producto p0 = new Producto();
//		Producto p1 = new Producto();
//		Producto p2 = new Producto();
//		Producto p3 = new Producto();
		
		p0.setNombre(ConstantesUtil.varios);
		p0.setDescripcion("Genérico");
		p0.setMarca("Genérica");
		p0.setCategoria(c0);
		
//		p1.setNombre("Bici rodado 29");
//		p1.setDescripcion("Descripcion del p1");
//		p1.setMarca("Shimano");
//		p1.setPrecio(new BigDecimal(200));
//		p1.setStock(5);
//		p1.setCategoria(c1);
//	
//		p2.setNombre("Bici rodado 26");
//		p2.setDescripcion("Descripcion del p2");
//		p2.setMarca("Shimano");
//		p2.setPrecio(new BigDecimal(150));
//		p2.setStock(3);
//		p2.setCategoria(c2);
//		
//		p3.setNombre("Bici rodado 19");
//		p3.setDescripcion("Descripcion del p3");
//		p3.setMarca("Shimano");
//		p3.setPrecio(new BigDecimal(100));
//		p3.setStock(2);
//		p3.setCategoria(c3);
		
		productoDao.createProducto(p0);
//		productoDao.createProducto(p1);
//		productoDao.createProducto(p2);
//		productoDao.createProducto(p3);
//		*********************************************************empleado**********************************************
//		Empleado e1 = new Empleado();
//		Empleado e2 = new Empleado();
//	
//	
//		e1.setCargo("ADMINISTRADOR");
//		e1.setDni("x5993933h");
//		e1.setEmail("mdsmatias@hotmail.com");
//		String nombre = "Matias";
//		e1.setNombre(nombre);
//		e1.setTelefono("999999999");
//		e1.setPassword("1234");
//		
//		e2.setCargo("EMPLEADO");
//		e2.setDni("x5993934h");
//		e2.setEmail("mdsmatias@hotmail.com");
//		e2.setNombre("Juan");
//		e2.setTelefono("8888888");
//		e2.setPassword("12345678");
//		
//		Long emple = empleadoDao.createEmpleado(e1);
//		empleadoDao.createEmpleado(e2);
//		********************************************************Venta********************************************
//		Venta v1 = new Venta();
//		
//		String estado = EstadoEnum.FINALIZADA.toString();
//		List<LineaVenta> listaLinea = new ArrayList<>();
//		
//		v1.setEmpleado(e1);
//		v1.setFecha(new Date());
//		v1.setIVA(16);		
//		v1.setEstado(estado);
//		v1.setFormaPago(false);
//		Empleado eaux = empleadoDao.readEmpleado(emple);
//		v1.setEmpleado(eaux);
//		ventaDao.createVenta(v1);
//		*********************************************************lineaVenta***************************************
	
		
//		LineaVenta l1 = new LineaVenta();
//		LineaVenta l2 = new LineaVenta();
//		LineaVenta l3 = new LineaVenta();
//		
//		l1.setCantidad(3);
//		l1.setProducto(p1.getIdProducto());
//		l1.setTotal(p1.getPrecio().multiply(new BigDecimal(l1.getCantidad())));
//		l1.setImporte(p1.getPrecio());
//		
//		l2.setCantidad(2);
//		l2.setProducto(p2.getIdProducto());
//		l2.setTotal(p2.getPrecio().multiply(new BigDecimal(l2.getCantidad())));
//		l2.setImporte(p2.getPrecio());
//		
//		l3.setCantidad(1);
//		l3.setProducto(p3.getIdProducto());
//		l3.setTotal(p3.getPrecio().multiply(new BigDecimal(l3.getCantidad())));
//		l3.setImporte(p3.getPrecio());
//		
//		lineaDao.createLinea(l1);
//		lineaDao.createLinea(l2);
//		lineaDao.createLinea(l3);
//		
//		listaLinea.add(l1);
//		listaLinea.add(l2);
//		listaLinea.add(l3);
//		****************************************************************************************************
//		LineaVentaRealizada lvRealizada = new LineaVentaRealizada();
//		List<Long> listaProductos = new ArrayList<>();
//		List<Integer> listaCantidadProductos = new ArrayList<>();
//		
//		listaProductos.add(p1.getIdProducto());
//		listaProductos.add(p2.getIdProducto());
//		listaProductos.add(p3.getIdProducto());
//		listaCantidadProductos.add(l1.getCantidad());
//		listaCantidadProductos.add(l2.getCantidad());
//		listaCantidadProductos.add(l3.getCantidad());
//		
//		v1.setIdLineaVentaRealizada(lvRealizada.getIdLineaVenta());
//		BigDecimal bd = l1.getTotal().add(l2.getTotal()).add(l3.getTotal());
//		v1.setImporte(bd);
//		
//		lvRealizada.setTotal(bd);
//		
//		BigDecimal iva = new BigDecimal(v1.getIVA()).divide(new BigDecimal(100));
//		v1.setTotal(v1.getImporte().add(v1.getImporte().multiply(iva)));
//		ventaDao.upDateVenta(v1);
	}

}
