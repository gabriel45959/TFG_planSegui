package plansegui.hibernate.dao.impl;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import plansegui.hibernate.dao.TipoProblemaDao;
import plansegui.hibernate.entities.TipoProblema;

@Repository
public class TipoProblemaDaoImpl implements TipoProblemaDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<TipoProblema> getTipoProblema() {
		
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

		CriteriaQuery<TipoProblema> cq = criteriaBuilder
				.createQuery(TipoProblema.class);

		Root<TipoProblema> root = cq.from(TipoProblema.class);

		cq.select(root);

		Query query = session.createQuery(cq);

		return query.getResultList();
	}

	@Override
	public void guardarTipoProblema(TipoProblema tipoProblema) {
		Session session = sessionFactory.getCurrentSession();

		session.saveOrUpdate(tipoProblema);
		
	}

	@Override
	public TipoProblema getTipoProblema(Long id) {
		return sessionFactory.getCurrentSession().get(TipoProblema.class,id);
	}

}
