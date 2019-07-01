package plansegui.hibernate.dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import plansegui.hibernate.entities.Maquinaria;

@Repository
public class MaquinariaDaoImpl implements MaquinariaDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Maquinaria> getMaquinaria() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

		CriteriaQuery<Maquinaria> cq = criteriaBuilder
				.createQuery(Maquinaria.class);

		Root<Maquinaria> root = cq.from(Maquinaria.class);

		cq.select(root);

		Query query = session.createQuery(cq);

		return query.getResultList();
	}

	@Override
	public void guardarMaquinaria(Maquinaria maquinaria) {
		Session session = sessionFactory.getCurrentSession();

		session.saveOrUpdate(maquinaria);

	}

}
