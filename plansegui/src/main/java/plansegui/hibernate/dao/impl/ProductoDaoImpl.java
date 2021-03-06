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

import plansegui.hibernate.dao.ProductoDao;
import plansegui.hibernate.entities.Producto;

@Repository
public class ProductoDaoImpl implements ProductoDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Producto> getProducto() {

		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

		CriteriaQuery<Producto> cq = criteriaBuilder
				.createQuery(Producto.class);

		Root<Producto> root = cq.from(Producto.class);

		cq.select(root);

		Query query = session.createQuery(cq);

		return query.getResultList();
	}

	@Override
	public void guardarPoducto(Producto producto) {

		Session session = sessionFactory.getCurrentSession();

		session.saveOrUpdate(producto);
		
		session.clear();

	}

	@Override
	public Producto getProducto(Long id) {
		return sessionFactory.getCurrentSession().get(Producto.class, id);
	}

}
