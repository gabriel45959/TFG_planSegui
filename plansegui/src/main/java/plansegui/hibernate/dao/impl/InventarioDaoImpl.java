package plansegui.hibernate.dao.impl;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import plansegui.controller.FabricaController;
import plansegui.hibernate.dao.InventarioDao;
import plansegui.hibernate.entities.Ingrediente;
import plansegui.hibernate.entities.Inventario;

@Repository
public class InventarioDaoImpl implements InventarioDao {

	private Log log = LogFactory.getLog(FabricaController.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Inventario> getInventario() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

		CriteriaQuery<Inventario> cq = criteriaBuilder
				.createQuery(Inventario.class);

		Root<Inventario> root = cq.from(Inventario.class);

		cq.select(root);
		
		Query query = session.createQuery(cq);

		return query.getResultList();
	}

	@Override
	public void guardarInventario(Inventario inventario) {
		
		log.info("InventarioDaoImpl--------------------------cantidad: "+inventario.getCantidad()+"--------------------------------------guardarInventario ");
		
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			
			if(!session.isOpen()){
				session=sessionFactory.openSession();
			}
			
			transaction = session.getTransaction();
			
						
			if(!transaction.isActive()) transaction.begin();
			
			session.evict(inventario);//se usa para forzar la actualizacion
			session.update(inventario);
			
			

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		
		}
	}

   public List<Inventario> getInventarioPorMateriaPrima(Long idProduct){

	   Session session = sessionFactory.getCurrentSession();
	   CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
	   
	/*   Query query = session.createQuery("select in_id, in_id_materia_prima, in_cantidad, in_fecha_salida, in_fecha_ingreso, in_nro_factura from inventario"
	   		+ "  WHERE exists(select 1 from ingrediente ing where inventario.in_id_materia_prima=ing.in_materia_prima and ing.in_producto=:pro)",Inventario.class);
	   query.setParameter("pro",idProduct);
	  */ 
	   
	   
	   CriteriaQuery<Inventario> cq = criteriaBuilder.createQuery(Inventario.class);

		Root<Inventario> root = cq.from(Inventario.class);

		 //subquery
	      Subquery<Ingrediente> ingreSubquery = cq.subquery(Ingrediente.class);
	      
	      Root<Ingrediente> SubqueryRoot = ingreSubquery.from(Ingrediente.class);
	      
	      ingreSubquery.select(SubqueryRoot)//subquery selection
	                     .where(criteriaBuilder.equal(SubqueryRoot.get("in_materia_prima"),root.get("in_id_materia_prima")));//subquery restriction
	     
	      
	      cq.select(root).where(
	    	      criteriaBuilder.exists(ingreSubquery));
	      Query query = session.createQuery(cq);
	      
	return query.getResultList();   
	
   }
	
}
