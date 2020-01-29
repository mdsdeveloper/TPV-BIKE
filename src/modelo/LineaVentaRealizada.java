package modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
@Table(name="lineaVentasRealizadas")
public class LineaVentaRealizada  implements Serializable{
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idLineaVenta;
	@OneToMany(mappedBy="lineaVentaRealizada", cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
	private List<ListaProducto> listaProductos;
	@Column(name="total")
	private BigDecimal total;
	@Column(name="formaPago")
	private Boolean formaPago;
	@Column(name="fecha")
	private Date fecha;
	
	
	public Long getIdLineaVenta() {
		return idLineaVenta;
	}
	public void setIdLineaVenta(Long idLineaVenta) {
		this.idLineaVenta = idLineaVenta;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public List<ListaProducto> getListaProductos() {
		return listaProductos;
	}
	public void setListaProductos(List<ListaProducto> listaProductos) {
		this.listaProductos = listaProductos;
	}
	public Boolean getFormaPago() {
		return formaPago;
	}
	public void setFormaPago(Boolean formaPago) {
		this.formaPago = formaPago;
	}
	
	
	
}
