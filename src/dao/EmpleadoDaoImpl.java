package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import modelo.Empleado;
import util.HibernateUtil;
/**
 * 
 * @author Two hands Technology
 *
 */
@SuppressWarnings("unchecked")
public class EmpleadoDaoImpl implements EmpleadoDao {

	private static final long serialVersionUID = 6933233236877344248L;
	private Session session;
	private Transaction tx;
	
	private void iniciaSession() throws HibernateException{
		session = HibernateUtil.getSessionFactory().openSession();
		tx = session.beginTransaction();
	}
	private void manejaExcepcion(HibernateException he) throws HibernateException{
	    tx.rollback();
	    throw new HibernateException("Ocurrió un error en la capa de acceso a datos", he);
	}
	
	@Override
	public Long createEmpleado(Empleado empleado) {
		Long dev = null;	
		try{
			iniciaSession();
			dev = (Long) session.save(empleado);
			tx.commit();
		}catch(HibernateException he){
			manejaExcepcion(he);
			throw he;
		}finally {
			session.close();
		}
		return dev;
	}

	@Override
	public Empleado readEmpleado(Long idEmpleado) {
		Empleado dev = null;
		try{
			iniciaSession();
			dev = session.get(Empleado.class, idEmpleado);
		}finally {
			session.close();
		}
		return dev;
	}

	@Override
	public void upDateEmpleado(Empleado empleado) {
		try{
			iniciaSession();
			session.update(empleado);
			tx.commit();
		}catch(HibernateException he){
			manejaExcepcion(he);
			throw he;
		}finally {
			session.close();
		}
		
	}

	@Override
	public void deleteEmpleado(Empleado empleado) {
		try{
			iniciaSession();
			session.delete(empleado);
			tx.commit();
		}catch(HibernateException he){
			manejaExcepcion(he);
			throw he;
		}finally {
			session.close();
		}
		
	}


	@Override
	public List<Empleado> obtenerEmpleados() {
		List<Empleado> dev = null;
		
		try{
			iniciaSession();
			dev = session.createQuery("from Empleado").list();
		}finally {
			session.close();
		}
		return dev;
	}

	@Override
	public List<Empleado> obtenerEmpleadoByName(String nombre) {
	List<Empleado> dev = null;
	try{
		iniciaSession();
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery("from Empleado where nombre=:nombre");
		query.setParameter("nombre", nombre);
		dev = query.list();		
			
	}catch(HibernateException he){
			manejaExcepcion(he);
			throw he;
	}finally{
		session.close();
	}
		return dev;
	}

}
