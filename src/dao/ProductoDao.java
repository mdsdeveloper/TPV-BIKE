package dao;

import java.io.Serializable;
import java.util.List;

import modelo.Producto;
/**
 * 
 * @author Two hands Technology
 *
 */
public interface ProductoDao extends Serializable {

	public Long createProducto(Producto producto);
	public Producto readProducto(Long idProducto);
	public void upDateProducto(Producto producto);
	public void deleteProducto(Producto producto);
	public List<Producto> obtenerProductos();
	public List<Producto> obtenerProductosByCategoria(String id);
	public Producto obtenerProductoGenerico();
}
