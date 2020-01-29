package dao;

import java.io.Serializable;
import java.util.List;

import modelo.Cliente;
/**
 * 
 * @author Two hands Technology
 *
 */
public interface ClienteDao extends Serializable{

	public Long createCliente(Cliente cliente);
	public Cliente readCliente(Long idCliente);
	public void upDateCliente(Cliente cliente);
	public void deleteCliente(Cliente cliente);
	public List<Cliente> obtenerClientes();
}
