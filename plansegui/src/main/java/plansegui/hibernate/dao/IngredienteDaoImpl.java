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

import plansegui.hibernate.entities.Ingrediente;

@Repository
public class IngredienteDaoImpl implements IngredienteDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Ingrediente> getIngrediente() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

		CriteriaQuery<Ingrediente> cq = criteriaBuilder
				.createQuery(Ingrediente.class);

		Root<Ingrediente> root = cq.from(Ingrediente.class);

		cq.select(root);

		Query query = session.createQuery(cq);

		return query.getResultList();
	}

	@Override
	public void guardarIngrediente(Ingrediente ingrediente) {
		Session session = sessionFactory.getCurrentSession();

		session.saveOrUpdate(ingrediente);

	}

}
