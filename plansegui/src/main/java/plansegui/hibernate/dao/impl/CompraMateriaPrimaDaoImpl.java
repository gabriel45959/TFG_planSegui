package plansegui.hibernate.dao.impl;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import plansegui.hibernate.dao.CompraMateriaPrimaDao;
import plansegui.hibernate.entities.CompraMateriaPrima;
import plansegui.hibernate.entities.DetalleCompraMateriaPrima;

@Repository
public class CompraMateriaPrimaDaoImpl implements CompraMateriaPrimaDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<CompraMateriaPrima> getCompraMateriaPrima() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

		CriteriaQuery<CompraMateriaPrima> cq = criteriaBuilder
				.createQuery(CompraMateriaPrima.class);

		Root<CompraMateriaPrima> root = cq.from(CompraMateriaPrima.class);

		cq.select(root);

		Query query = session.createQuery(cq);

		return query.getResultList();
	}

	@Override
	public void guardarCompraMateriaPrima(CompraMateriaPrima compraMateriaPrima) {
		
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			
			if(!session.isOpen()){
				session=sessionFactory.openSession();
			}
			
			transaction = session.getTransaction();
			
						
			if(!transaction.isActive()) transaction.begin();

			session.saveOrUpdate(compraMateriaPrima);
			
			session.clear();
			compraMateriaPrima=null;

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		
		}

	
	}

	@Override
	public CompraMateriaPrima getCompraMateriaPrima(Long id) {
		return sessionFactory.getCurrentSession().get(CompraMateriaPrima.class, id);
	}

}
