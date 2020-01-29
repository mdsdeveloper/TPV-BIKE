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
@Table(name="cajas")
public class Caja implements Serializable{

	private static final long serialVersionUID = 7772948370358904058L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idCaja;
	@Column(name="fechaCierre")
	private Date fechaCierre;
	@Column(name="numeroVentas")
	private Integer numero;
	@Column(name="totalEfectivo")
	private BigDecimal efectivo;
	@Column(name="totalTarjeta")
	private BigDecimal tarjeta;
	@Column(name="totalCierre")
	private BigDecimal totalCierre;
	@Column(name="descuadre")
	private BigDecimal descuadre;
	@Column(name="empleado")
	private String empleado;
	
	
	public Long getIdCaja() {
		return idCaja;
	}
	public void setIdCaja(Long idCaja) {
		this.idCaja = idCaja;
	}
	public Date getFechaCierre() {
		return fechaCierre;
	}
	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public BigDecimal getEfectivo() {
		return efectivo;
	}
	public void setEfectivo(BigDecimal efectivo) {
		this.efectivo = efectivo;
	}
	public BigDecimal getTarjeta() {
		return tarjeta;
	}
	public void setTarjeta(BigDecimal tarjeta) {
		this.tarjeta = tarjeta;
	}
	public BigDecimal getTotalCierre() {
		return totalCierre;
	}
	public void setTotalCierre(BigDecimal totalCierre) {
		this.totalCierre = totalCierre;
	}
	public BigDecimal getDescuadre() {
		return descuadre;
	}
	public void setDescuadre(BigDecimal descuadre) {
		this.descuadre = descuadre;
	}
	public String getEmpleado() {
		return empleado;
	}
	public void setEmpleado(String empleado) {
		this.empleado = empleado;
	}
	
}
