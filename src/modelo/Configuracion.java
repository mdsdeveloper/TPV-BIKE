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
@Table(name="configuracion")
public class Configuracion implements Serializable{

	private static Configuracion instance;
	private static final long serialVersionUID = -1383916108477619468L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idConfiguracion;
	@Column(name="nombreEmpresa")
	private String nombreEmpresa;
	@Column(name="CifNifEmpresa")
	private String cifNif;
	@Column(name="telefonoEmpresa")
	private String telefonoEmpresa;
	@Column(name="emailEmpresa")
	private String emailEmpresa;
	@Column(name="direccionEmpresa")
	private String direccionEmpresa;
	@Column(name="razonSocial")
	private String razonSocial;
	@Column(name="inicialCaja")
	private BigDecimal inicialCaja;
	@Column(name="icono" , length = 2000)
	private String icono;
	@Column(name="pieFactura")
	private String pieFactura;
	@Column(name="cabeceraFactura")
	private String cabeceraFactura;
	@Column(name="directorioCierre")
	private String directorioCierre;
	@Column(name="directorioFactura")
	private String directorioFactura;
	
	private Configuracion(){}
	public static Configuracion getInstance(){
		if(instance == null){
			instance = new Configuracion();
		}
		return instance;
	}
	public Long getIdConfiguracion() {
		return idConfiguracion;
	}
	public void setIdConfiguracion(Long idConfiguracion) {
		this.idConfiguracion = idConfiguracion;
	}
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	public String getCifNif() {
		return cifNif;
	}
	public void setCifNif(String cifNif) {
		this.cifNif = cifNif;
	}
	public String getTelefonoEmpresa() {
		return telefonoEmpresa;
	}
	public void setTelefonoEmpresa(String telefonoEmpresa) {
		this.telefonoEmpresa = telefonoEmpresa;
	}
	public String getEmailEmpresa() {
		return emailEmpresa;
	}
	public void setEmailEmpresa(String emailEmpresa) {
		this.emailEmpresa = emailEmpresa;
	}
	public String getDireccionEmpresa() {
		return direccionEmpresa;
	}
	public void setDireccionEmpresa(String direccionEmpresa) {
		this.direccionEmpresa = direccionEmpresa;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public BigDecimal getInicialCaja() {
		return inicialCaja;
	}
	public void setInicialCaja(BigDecimal inicialCaja) {
		this.inicialCaja = inicialCaja;
	}
	public String getIcono() {
		return icono;
	}
	public void setIcono(String icono) {
		this.icono = icono;
	}
	public String getPieFactura() {
		return pieFactura;
	}
	public void setPieFactura(String pieFactura) {
		this.pieFactura = pieFactura;
	}
	public String getCabeceraFactura() {
		return cabeceraFactura;
	}
	public void setCabeceraFactura(String cabeceraFactura) {
		this.cabeceraFactura = cabeceraFactura;
	}
	public String getDirectorioCierre() {
		return directorioCierre;
	}
	public void setDirectorioCierre(String directorioCierre) {
		this.directorioCierre = directorioCierre;
	}
	public String getDirectorioFactura() {
		return directorioFactura;
	}
	public void setDirectorioFactura(String directorioFactura) {
		this.directorioFactura = directorioFactura;
	}
	
	
}
