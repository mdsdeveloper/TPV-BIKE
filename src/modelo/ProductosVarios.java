package modelo;

import java.math.BigDecimal;
import java.util.Date;

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
@Table(name="productosVarios")
public class ProductosVarios {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idProductoVarios;
	@Column(name="nombre")
	private String nombre;
	@Column(name="precio")
	private BigDecimal precio;
	@Column(name="idLineaVentaRealizada")
	private Long lineaVentaRealizada;
	@Column(name="idProducto")
	private Long idProducto;
	@Column(name="fecha")
	private Date fecha;
	
	public Long getIdProductoVarios() {
		return idProductoVarios;
	}
	public void setIdProductoVarios(Long idProductoVarios) {
		this.idProductoVarios = idProductoVarios;
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
	public Long getLineaVentaRealizada() {
		return lineaVentaRealizada;
	}
	public void setLineaVentaRealizada(Long lineaVentaRealizada) {
		this.lineaVentaRealizada = lineaVentaRealizada;
	}
	public Long getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
}
