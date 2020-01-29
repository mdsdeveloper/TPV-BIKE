package modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
/**
 * 
 * @author Two hands Technology
 *
 */
@Entity
@Table(name="categorias")
public class Categoria implements Serializable{

	private static final long serialVersionUID = 8987913836383874454L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idCategoria;
	@Column(name="nombre")
	private String nombre;
	@Column(name="descripcion")
	private String descripcion;
	@Column(name="imagen" , length = 2000)
	private String imagen;
	@OneToMany(mappedBy="categoria", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Producto> listaProductos;
	
	
	public Long getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public List<Producto> getListaProductos() {
		return listaProductos;
	}
	public void setListaProductos(List<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}
	public Boolean existeProducto(Long producto){
		Producto productoExiste = null;
		Boolean existe = null;
		if(getListaProductos() != null){
			List<Producto> lista = getListaProductos();
			for (Producto p : lista) {
				if(p.getIdProducto().equals(producto)){
					productoExiste = p;
				}
			}			
		}
		return productoExiste != null ? Boolean.TRUE : Boolean.FALSE;		
	}
}
