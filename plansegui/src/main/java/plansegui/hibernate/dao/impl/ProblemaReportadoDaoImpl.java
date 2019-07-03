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

import plansegui.hibernate.dao.ProblemaReportadoDao;
import plansegui.hibernate.entities.ProblemaReportado;


@Repository
public class ProblemaReportadoDaoImpl implements ProblemaReportadoDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<ProblemaReportado> getProblemaReportado() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

		CriteriaQuery<ProblemaReportado> cq = criteriaBuilder
				.createQuery(ProblemaReportado.class);

		Root<ProblemaReportado> root = cq.from(ProblemaReportado.class);

		cq.select(root);

		Query query = session.createQuery(cq);

		return query.getResultList();
	}

	@Override
	public void guardarProblemaReportado(ProblemaReportado problemaReportado) {
		
		Session session = sessionFactory.getCurrentSession();
		
		
		session.saveOrUpdate(problemaReportado);
		
		
		
	}

}
