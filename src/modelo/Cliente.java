package modelo;

import java.io.Serializable;

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
@Table(name="clientes")
public class Cliente implements Serializable{

	private static final long serialVersionUID = 4506013567518728146L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idCliente;
	@Column(name="nombre")
	private String nombre;
	@Column(name="CifNif")
	private String cifNif;
	@Column(name="telefono")
	private String telefono;
	@Column(name="email")
	private String email;
	@Column(name="direccion")
	private String direccion;
	
	
	public Long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDniNif() {
		return cifNif;
	}
	public void setDniNif(String dniNif) {
		this.cifNif = dniNif;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
}
