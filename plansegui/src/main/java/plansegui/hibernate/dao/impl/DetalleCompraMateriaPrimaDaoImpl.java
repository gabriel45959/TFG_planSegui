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

import plansegui.hibernate.dao.DetalleCompraMateriaPrimaDao;
import plansegui.hibernate.entities.DetalleCompraMateriaPrima;

@Repository
public class DetalleCompraMateriaPrimaDaoImpl implements
		DetalleCompraMateriaPrimaDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<DetalleCompraMateriaPrima> getDetalleCompraMateriaPrima() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

		CriteriaQuery<DetalleCompraMateriaPrima> cq = criteriaBuilder
				.createQuery(DetalleCompraMateriaPrima.class);

		Root<DetalleCompraMateriaPrima> root = cq
				.from(DetalleCompraMateriaPrima.class);

		cq.select(root);

		Query query = session.createQuery(cq);

		return query.getResultList();
	}

	@Override
	public void guardarDetalleCompraMateriaPrima(
			DetalleCompraMateriaPrima detalleCompraMateriaPrima) {

		Session session = sessionFactory.getCurrentSession();

		session.saveOrUpdate(detalleCompraMateriaPrima);
		
		session.clear();

	}

}
