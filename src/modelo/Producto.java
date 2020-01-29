package modelo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * 
 * @author Two hands Technology
 *
 */
@Entity
@Table(name="productos")
public class Producto implements Serializable {

	private static final long serialVersionUID = -7702610053304395495L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idProducto;
	@Column(name="nombre")
	private String nombre;
	@Column(name="descripcion")
	private String descripcion;
	@Column(name="precio")
	private BigDecimal precio;
	@Column(name="imagen" , length = 2000)
	private String imagen;
	@Column(name="marca")
	private String marca;
	@Column(name="stock")
	private Integer stock;
	@ManyToOne
	@JoinColumn(name="idCategoria")
	private Categoria categoria;
//	@OneToOne
//	@JoinColumn(name="idLineaVenta")
//	private LineaVenta lineaVenta;
	
	
	public Long getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
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
	
	public BigDecimal getPrecio() {
		return precio;
	}
	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}
	
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
//	public LineaVenta getLineaVenta() {
//		return lineaVenta;
//	}
//	public void setLineaVenta(LineaVenta lineaVenta) {
//		this.lineaVenta = lineaVenta;
//	}
	
	
	
	
	
}
