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
@Table(name="facturasHechas")
public class FacturaHecha implements Serializable{

	private static final long serialVersionUID = -488059764856213488L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idFactura;
	@Column(name="idCliente")
	private Long idCliente;
	@Column(name="idVenta")
	private Long idVenta;
	@Column(name="idVentaRealizada")
	private Long idVentaRealizada;
	@Column(name="empleado")
	private String empleado;
	@Column(name="fecha")
	private Date fecha;
	@Column(name="numero")
	private Integer numero;
	@Column(name="total")
	private BigDecimal total;
	@Column(name="baseImponible")
	private BigDecimal baseImponible;
	@Column(name="nombre")
	private String nombre;
	@Column(name="dniNif")
	private String dniNif;
	@Column(name="razonSocial")
	private String razonSocial;
	@Column(name="direccion")
	private String direccion;	
	@Column(name="nombreCliente")
	private String nombreCliente;
	@Column(name="dniNifCliente")
	private String dniNifCliente;
	@Column(name="telefonoCliente")
	private String telefonoCliente;
	@Column(name="emailCliente")
	private String emailCliente;
	@Column(name="direccionCliente")
	private String direccionCliente;
	@Column(name="formaPago")
	private Boolean formaPago;
	@Column(name="telefono")
	private String telefono;
	
	
	
	public Long getIdFactura() {
		return idFactura;
	}
	public void setIdFactura(Long idFactura) {
		this.idFactura = idFactura;
	}
	public String getEmpleado() {
		return empleado;
	}
	public void setEmpleado(String empleado) {
		this.empleado = empleado;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
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
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDniNif() {
		return dniNif;
	}
	public void setDniNif(String dniNif) {
		this.dniNif = dniNif;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public String getDniNifCliente() {
		return dniNifCliente;
	}
	public void setDniNifCliente(String dniNifCliente) {
		this.dniNifCliente = dniNifCliente;
	}
	public String getTelefonoCliente() {
		return telefonoCliente;
	}
	public void setTelefonoCliente(String telefonoCliente) {
		this.telefonoCliente = telefonoCliente;
	}
	public String getEmailCliente() {
		return emailCliente;
	}
	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}
	public String getDireccionCliente() {
		return direccionCliente;
	}
	public void setDireccionCliente(String direccionCliente) {
		this.direccionCliente = direccionCliente;
	}
	public Long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
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
	public Boolean getFormaPago() {
		return formaPago;
	}
	public void setFormaPago(Boolean formaPago) {
		this.formaPago = formaPago;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	
}
