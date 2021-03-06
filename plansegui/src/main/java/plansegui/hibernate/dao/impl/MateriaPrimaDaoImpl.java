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

import plansegui.hibernate.dao.MateriaPrimaDao;
import plansegui.hibernate.entities.MateriaPrima;

@Repository
public class MateriaPrimaDaoImpl implements MateriaPrimaDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<MateriaPrima> getMateriaPrima() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

		CriteriaQuery<MateriaPrima> cq = criteriaBuilder
				.createQuery(MateriaPrima.class);

		Root<MateriaPrima> root = cq.from(MateriaPrima.class);

		cq.select(root);

		Query query = session.createQuery(cq);

		return query.getResultList();
	}

	@Override
	public void guardarMateriaPrima(MateriaPrima materiaPrima) {
		Session session = sessionFactory.getCurrentSession();

		session.saveOrUpdate(materiaPrima);
		session.clear();
	}

	@Override
	public MateriaPrima getMateriaPrima(Long id) {
		return sessionFactory.getCurrentSession().get(MateriaPrima.class, id);
	}

}
