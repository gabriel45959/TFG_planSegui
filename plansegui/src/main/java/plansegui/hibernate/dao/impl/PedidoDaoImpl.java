package plansegui.hibernate.dao.impl;

import java.util.List;







import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import plansegui.hibernate.dao.PedidoDao;
import plansegui.hibernate.entities.DetallePedido;
import plansegui.hibernate.entities.Pedido;

@Repository
public class PedidoDaoImpl implements PedidoDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Log log = LogFactory.getLog(PedidoDaoImpl.class);
	
	@Override
	public List<Pedido> getPedido() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		
		CriteriaQuery<Pedido> cq = criteriaBuilder.createQuery(Pedido.class);
		
		Root<Pedido> root = cq.from(Pedido.class);
		
		cq.select(root);
		
		Query query = session.createQuery(cq);

		
		return query.getResultList();
	}
	
	

	@Override
	public void guardarPedido(Pedido pedido) {
		log.info(" PedidoDaoImpl---------------------------------------------------------------------------------------guardarPedido ");

		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			
			if(!session.isOpen()){
				session=sessionFactory.openSession();
			}
			
			
			transaction = session.getTransaction();
			
									
			if(!transaction.isActive()) transaction.begin();


			session.saveOrUpdate(pedido);
			
			
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		
		}
		
	}

}
