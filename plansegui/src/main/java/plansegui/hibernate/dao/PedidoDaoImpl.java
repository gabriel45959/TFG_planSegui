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

import plansegui.hibernate.entities.Pedido;

@Repository
public class PedidoDaoImpl implements PedidoDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	
	@Override
	public List<Pedido> getPedido() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		
		CriteriaQuery<Pedido> cq = criteriaBuilder.createQuery(Pedido.class);
		
		Root<Pedido> root = cq.from(Pedido.class);
		
		cq.select(root);
		
		Query query = session.createQuery(cq);
		
		return query.getResultList();
	}

	@Override
	public void guardarPedido(Pedido pedido) {
		Session session = sessionFactory.getCurrentSession();
		
		session.saveOrUpdate(pedido);

	}

}
