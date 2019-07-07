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

import plansegui.hibernate.dao.DetalleCompraMateriaPrimaDao;
import plansegui.hibernate.entities.DetalleCompraMateriaPrima;
import plansegui.hibernate.entities.DetallePedido;

@Repository
public class DetalleCompraMateriaPrimaDaoImpl implements
		DetalleCompraMateriaPrimaDao {

	private Log log = LogFactory.getLog(DetalleCompraMateriaPrimaDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<DetalleCompraMateriaPrima> getDetalleCompraMateriaPrima() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

		CriteriaQuery<DetalleCompraMateriaPrima> cq = criteriaBuilder
				.createQuery(DetalleCompraMateriaPrima.class);

		Root<DetalleCompraMateriaPrima> root = cq
				.from(DetalleCompraMateriaPrima.class);

		cq.select(root);

		Query query = session.createQuery(cq);
		

		return query.getResultList();
	}

	@Override
	public void guardarDetalleCompraMateriaPrima(
			DetalleCompraMateriaPrima detalleCompraMateriaPrima) {

		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			
			if(!session.isOpen()){
				session=sessionFactory.openSession();
			}
			
			
			transaction = session.getTransaction();
			
						
			if(!transaction.isActive()) transaction.begin();

			session.saveOrUpdate(detalleCompraMateriaPrima);


		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		
		}

	}
	@Override
	public void actualizarDetalleCompraMateriaPrima(DetalleCompraMateriaPrima detalleCompraMateriaPrima) {

		log.info(" DetalleCompraMateriaPrimaDaoImpl---------------------------------------------------------------------------------------actualizarDetalleCompraMateriaPrima ");

		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			
			if(!session.isOpen()){
				session=sessionFactory.openSession();
			}
			
			
			transaction = session.getTransaction();
			
									
			if(!transaction.isActive()) transaction.begin();


			session.saveOrUpdate(detalleCompraMateriaPrima);
			
			
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		
		}

	}

	@Override
	public DetalleCompraMateriaPrima getDetalleCompraMateriaPrima(Long id) {
		
		return sessionFactory.getCurrentSession().get(DetalleCompraMateriaPrima.class, id);
	}

}
