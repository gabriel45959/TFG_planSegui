package plansegui.hibernate.dao.impl;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import plansegui.hibernate.dao.DetallePedidoDao;
import plansegui.hibernate.entities.DetallePedido;

@Repository
public class DetallePedidoDaoImpl implements DetallePedidoDao {

	private Log log = LogFactory.getLog(DetallePedidoDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	
	@Override
	public List<DetallePedido> getDetallePedido() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

		CriteriaQuery<DetallePedido> cq = criteriaBuilder.createQuery(DetallePedido.class);

		Root<DetallePedido> root = cq.from(DetallePedido.class);
		
		cq.select(root).orderBy(criteriaBuilder.desc(root.get("de_estado")));

		Query query = session.createQuery(cq);
		session.flush();
		session.clear();
		
		return query.getResultList();
	}
	
	@Override
	public void guardarDetallePedido(DetallePedido detallePedido) {
		Session session = sessionFactory.getCurrentSession();

		session.saveOrUpdate(detallePedido);
	}

	@Override
	public DetallePedido getDetallePedido(Long id) {

		return sessionFactory.getCurrentSession().get(DetallePedido.class, id);
	}

	@Override
	public void actualizarDetallePedido(DetallePedido detallePedido) {

		log.info(" DetallePedidoDaoImpl---------------------------------------------------------------------------------------actualizarDetallePedido "
				+ detallePedido.getEstado().getNombre());

		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			
			if(!session.isOpen()){
				session=sessionFactory.openSession();
			}
			
			
			transaction = session.getTransaction();
			
									
			if(!transaction.isActive()) transaction.begin();


			session.saveOrUpdate(detallePedido);
			
			
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		
		}

	}

	@Override
	public List<DetallePedido> getdetallePedidoPorEstado(int idEstado) {
		
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

		CriteriaQuery<DetallePedido> cq = criteriaBuilder.createQuery(DetallePedido.class);

		Root<DetallePedido> root = cq.from(DetallePedido.class);
		
		
		cq.select(root).where(criteriaBuilder.equal(root.get("estado"), idEstado));
		
		
		Query query = session.createQuery(cq);
		
		
		
		return query.getResultList();
	}
	



}
