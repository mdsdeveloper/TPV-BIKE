package dao;

import java.io.Serializable;
import java.util.List;

import modelo.ProductoCambiado;
/**
 * 
 * @author Two hands Technology
 *
 */
public interface ProductoCambiadoDao extends Serializable{

	public Long createProductoCambiado(ProductoCambiado productoCambiado);
	public ProductoCambiado readProductoCambiado(Long idProductoCambiado);
	public void upDateProductoCambiado(ProductoCambiado productoCambiado);
	public void deleteProductoCambiado(ProductoCambiado productoCambiado);
	public List<ProductoCambiado> obtenerProductos();
}
