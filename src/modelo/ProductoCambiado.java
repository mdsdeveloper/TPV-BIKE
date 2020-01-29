package modelo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Two hands Technology
 *
 */
@Entity
@Table(name="productoCambiado")
public class ProductoCambiado implements Serializable {

	private static final long serialVersionUID = -1065662248680187538L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idProducto;
	@Column(name="nombre")
	private String nombre;
	@Column(name="precio")
	private BigDecimal precio;
	@Column(name="cantidad")
	private BigDecimal cantidad;
	
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
	public BigDecimal getPrecio() {
		return precio;
	}
	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}
	public BigDecimal getCantidad() {
		return cantidad;
	}
	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}
	
	
}
