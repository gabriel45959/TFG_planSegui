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

import plansegui.hibernate.dao.DetallePedidoDao;
import plansegui.hibernate.entities.DetallePedido;


@Repository
public class DetallePedidoDaoImpl implements DetallePedidoDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<DetallePedido> getDetallePedido() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

		CriteriaQuery<DetallePedido> cq = criteriaBuilder
				.createQuery(DetallePedido.class);

		Root<DetallePedido> root = cq.from(DetallePedido.class);

		cq.select(root);

		Query query = session.createQuery(cq);

		return query.getResultList();
	}

	@Override
	public void guardarDetallePedido(DetallePedido detallePedido) {
		Session session = sessionFactory.getCurrentSession();

		session.saveOrUpdate(detallePedido);
		session.clear();
	}

	@Override
	public DetallePedido getDetallePedido(Long id) {
		
		return sessionFactory.getCurrentSession().get(DetallePedido.class,id);
	}

}
