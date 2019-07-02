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

import plansegui.hibernate.dao.ParametroDao;
import plansegui.hibernate.entities.Parametro;

@Repository
public class ParametroDaoImpl implements ParametroDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Parametro> getParametro() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		
		CriteriaQuery<Parametro> cq = criteriaBuilder.createQuery(Parametro.class);
		
		Root<Parametro> root = cq.from(Parametro.class);
		
		cq.select(root);
		
		Query query = session.createQuery(cq);
		
		return query.getResultList();
	}

	@Override
	public void guardarParametro(Parametro parametro) {
	Session session = sessionFactory.getCurrentSession();
		
		session.saveOrUpdate(parametro);
		
		session.clear();
	}

}
