package modelo;

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
@Table(name="empleados")
public class Empleado {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idEmpleado;
	@Column(name="nombre")
	private String nombre;
	@Column(name="dni")
	private String dni;
	@Column(name="telefono")
	private String telefono;
	@Column(name="email")
	private String email;
	@Column(name="password")
	private String password;
	@Column(name="cargo")
	private String cargo;
// 	@OneToMany(mappedBy="empleados", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
//	private List<Venta> listaVentas = new ArrayList<Venta>();
	
	
	public Long getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(Long idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
//	public List<Venta> getListaVentas() {
//		return listaVentas;
//	}
//	public void setListaVentas(List<Venta> listaVentas) {
//		this.listaVentas = listaVentas;
//	}
//	FIXME revisar este código de cambiar password. falta completar.
	public void cambiarPassword(String password){
		this.password = password;
	}
}
