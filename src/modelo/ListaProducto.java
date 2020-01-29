package modelo;

import java.io.Serializable;
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
@Table(name="listaProducto")
public class ListaProducto implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name="idProducto")
	private Long idProducto;
	@Column(name="cantidad")
	private Integer cantidad;
	@ManyToOne
	@JoinColumn(name="idLineaVentaRealizada")
	private LineaVentaRealizada lineaVentaRealizada;
	@Column(name="fecha")
	private Date fecha;
	@Column(name="cambiado")
	private Boolean cambiado;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}
	public LineaVentaRealizada getLineaVentaRealizada() {
		return lineaVentaRealizada;
	}
	public void setLineaVentaRealizada(LineaVentaRealizada lineaVentaRealizada) {
		this.lineaVentaRealizada = lineaVentaRealizada;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Boolean isProductoCambiado() {
		return cambiado;
	}
	public void setIsProductoCambiado(Boolean productoCambiado){
		this.cambiado = productoCambiado;
	}
	
	
}
