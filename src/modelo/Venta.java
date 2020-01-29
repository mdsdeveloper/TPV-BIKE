package modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
@Table(name="ventas")
public class Venta implements Serializable{

	private static final long serialVersionUID = -1805004677406450397L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idVenta;
	@Column(name="empleado")
	private Long empleado;
	@Column(name="fecha")
	private Date fecha;
	@Column(name="iva")
	private Integer IVA;
	@Column(name="importe")
	private BigDecimal importe;
	@Column(name="total")
	private BigDecimal total;
	@Column(name="estado")
	private String estado;
	@Column(name="idLineaVentaRealizada")
	private Long idLineaVentaRealizada;
	@Column(name="formaPago")
	private Boolean formaPago;
	@Column(name="numero")
	private Integer numero;
//	@OneToMany(mappedBy="venta", cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
//	private List<LineaVenta> detalle;
	
	
	
	public Long getIdVenta() {
		return idVenta;
	}
	public void setIdVenta(Long idVenta) {
		this.idVenta = idVenta;
	}
	public Long getEmpleado() {
		return empleado;
	}
	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado.getIdEmpleado();
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Integer getIVA() {
		return IVA;
	}
	public void setIVA(Integer iVA) {
		IVA = iVA;
	}
	public BigDecimal getImporte() {
		return importe;
	}
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Long getIdLineaVentaRealizada() {
		return idLineaVentaRealizada;
	}
	public void setIdLineaVentaRealizada(Long idLineaVentaRealizada) {
		this.idLineaVentaRealizada = idLineaVentaRealizada;
	}
	public Boolean getFormaPago() {
		return formaPago;
	}
	public void setFormaPago(Boolean formaPago) {
		this.formaPago = formaPago;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	
	
//	public List<LineaVenta> getDetalle() {
//		return detalle;
//	}
//	public void setDetalle(List<LineaVenta> detalle) {
//		this.detalle = detalle;
//	}	
//	public LineaVenta getLinea(LineaVenta lineaVenta){
//		LineaVenta dev = null;
//		
//		for (LineaVenta lv : detalle) {
//			if(lv.equals(lineaVenta)){
//				dev = lv;
//			}
//		}
//		return dev;
//	}

}
