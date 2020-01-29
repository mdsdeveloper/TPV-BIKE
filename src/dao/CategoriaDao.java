package dao;

import java.io.Serializable;
import java.util.List;

import modelo.Categoria;
/**
 * 
 * @author Two hands Technology
 *
 */
public interface CategoriaDao extends Serializable {

	public Long createCategoria(Categoria categoria);
	public Categoria readCategoria(Long idCategoria);
	public void upDateCategoria(Categoria categoria);
	public void deleteCategoria(Categoria categoria);
	public List<Categoria> obtenerCategorias();
	public Categoria obtenerCategoriaByName(String nombre);
	public Categoria obtenerCategoriaGeneral();
}
