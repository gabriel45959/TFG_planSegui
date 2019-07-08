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

import plansegui.hibernate.dao.PlanificacionDao;
import plansegui.hibernate.entities.Planificacion;

@Repository
public class PlanificacionDaoImpl implements PlanificacionDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public List<Planificacion> getPlanificacion() {
		
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

		CriteriaQuery<Planificacion> cq = criteriaBuilder
				.createQuery(Planificacion.class);

		Root<Planificacion> root = cq.from(Planificacion.class);

		cq.select(root).orderBy(criteriaBuilder.desc(root.get("fechaEntregaEstimada")));

		Query query = session.createQuery(cq);

		return query.getResultList();
	}

	@Override
	public Planificacion getPlanificacion(Long id) {
				return sessionFactory.getCurrentSession().get(Planificacion.class, id);
	}

	@Override
	public void guardarPlanificacion(Planificacion planificacion) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			
			if(!session.isOpen()){
				session=sessionFactory.openSession();
			}
			
			transaction = session.getTransaction();
			
						
			if(!transaction.isActive()) transaction.begin();

			session.saveOrUpdate(planificacion);
			
			//session.clear();
			//compraMateriaPrima=null;

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		
		}

	}

}
