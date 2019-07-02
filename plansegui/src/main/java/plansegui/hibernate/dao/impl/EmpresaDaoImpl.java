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

import plansegui.hibernate.dao.EmpresaDao;
import plansegui.hibernate.entities.Empresa;

@Repository
public class EmpresaDaoImpl implements EmpresaDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Empresa> getEmpresa() {
		Session session = sessionFactory.getCurrentSession();

		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

		CriteriaQuery<Empresa> cq = criteriaBuilder.createQuery(Empresa.class);

		Root<Empresa> root = cq.from(Empresa.class);

		cq.select(root);

		Query query = session.createQuery(cq);

		return query.getResultList();
	}

	@Override
	public void guardarEmpresa(Empresa empresa) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(empresa);
		
		session.clear();
	}

	@Override
	public Empresa buscarEmpresa(long id) {
	  
		
		return sessionFactory.getCurrentSession().get(Empresa.class,id);
		
	}

}
