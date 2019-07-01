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

import plansegui.hibernate.entities.Inventario;

@Repository
public class InventarioDaoImpl implements InventarioDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Inventario> getInventario() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

		CriteriaQuery<Inventario> cq = criteriaBuilder
				.createQuery(Inventario.class);

		Root<Inventario> root = cq.from(Inventario.class);

		cq.select(root);

		Query query = session.createQuery(cq);

		return query.getResultList();
	}

	@Override
	public void guardarInventario(Inventario inventario) {
		Session session = sessionFactory.getCurrentSession();

		session.saveOrUpdate(inventario);

	}

}
