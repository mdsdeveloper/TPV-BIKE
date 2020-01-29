package dao;

import java.io.Serializable;
import java.util.List;

import modelo.ListaProducto;
/**
 * 
 * @author Two hands Technology
 *
 */
public interface ListaProductoDao extends Serializable{

	public Long createListaProducto(ListaProducto listaProducto);
	public ListaProducto readListaProducto(Long idListaProducto);
	public void upDateListaProducto(ListaProducto listaProducto);
	public void deleteListaProducto(ListaProducto listaProducto);
	public List<ListaProducto> obtenerListaProducto();
}
