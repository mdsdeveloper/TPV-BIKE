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
@Table(name="facturas")
public class Factura implements Serializable{

	private static final long serialVersionUID = -5397750020801388556L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idFactura;
	@Column(name="cabecera")
	private String cabecera;
	@Column(name="pie")
	private String pie;
	@Column(name="empleado")
	private String empleado;
	@Column(name="iva")
	private Integer IVA;
	@Column(name="fecha")
	private Date fecha;
	@Column(name="idVenta")
	private Long idVenta;
	@Column(name="idVentaRealizada")
	private Long idVentaRealizada;
	@Column(name="total")
	private BigDecimal total;
	@Column(name="baseImponible")
	private BigDecimal baseImponible;
	@Column(name="formaPago")
	private Boolean formaPago;
	
	
	public Long getIdFactura() {
		return idFactura;
	}
	public void setIdFactura(Long idFactura) {
		this.idFactura = idFactura;
	}
	public String getCabecera() {
		return cabecera;
	}
	public void setCabecera(String cabecera) {
		this.cabecera = cabecera;
	}
	public String getPie() {
		return pie;
	}
	public void setPie(String pie) {
		this.pie = pie;
	}
	public String getEmpleado() {
		return empleado;
	}
	public void setEmpleado(String empleado) {
		this.empleado = empleado;
	}
	public Integer getIVA() {
		return IVA;
	}
	public void setIVA(Integer iVA) {
		IVA = iVA;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Long getIdVenta() {
		return idVenta;
	}
	public void setIdVenta(Long idVenta) {
		this.idVenta = idVenta;
	}
	public Long getIdVentaRealizada() {
		return idVentaRealizada;
	}
	public void setIdVentaRealizada(Long idVentaRealizada) {
		this.idVentaRealizada = idVentaRealizada;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public BigDecimal getBaseImponible() {
		return baseImponible;
	}
	public void setBaseImponible(BigDecimal baseImponible) {
		this.baseImponible = baseImponible;
	}
	public Boolean getFormaPago() {
		return formaPago;
	}
	public void setFormaPago(Boolean formaPago) {
		this.formaPago = formaPago;
	}
	
}
