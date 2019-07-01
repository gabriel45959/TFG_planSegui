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

import plansegui.hibernate.entities.EstadoPedido;

@Repository
public class EstadoPedidoDaoImpl implements EstadoPedidoDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<EstadoPedido> getEstadoPedido() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		
		CriteriaQuery<EstadoPedido> cq = criteriaBuilder.createQuery(EstadoPedido.class);
		
		Root<EstadoPedido> root = cq.from(EstadoPedido.class);
		
		cq.select(root);
		
		Query query = session.createQuery(cq);
		
		return query.getResultList();
	}

	@Override
	public void guardarEstadoPedido(EstadoPedido estadoPedido) {
Session session = sessionFactory.getCurrentSession();
		
		session.saveOrUpdate(estadoPedido);
		

	}

}
