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

import plansegui.hibernate.dao.ProveedorDao;
import plansegui.hibernate.entities.Proveedor;

@Repository
public class ProveedorDaoImpl implements ProveedorDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Proveedor> getProveedor() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		
		CriteriaQuery<Proveedor> cq = criteriaBuilder.createQuery(Proveedor.class);
		
		Root<Proveedor> root = cq.from(Proveedor.class);
		
		cq.select(root);
		
		Query query = session.createQuery(cq);
		
		return query.getResultList();
	}

	@Override
	public void guardarProveedor(Proveedor proveedor) {
Session session = sessionFactory.getCurrentSession();
		
		session.saveOrUpdate(proveedor);
		session.clear();
	}

}
