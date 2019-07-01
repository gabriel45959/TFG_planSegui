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

import plansegui.hibernate.entities.ReservaMateriaPrima;

@Repository
public class ReservaMateriaPrimaDaoImpl implements ReservaMateriaPrimaDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<ReservaMateriaPrima> getReservaMateriaPrima() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		
		CriteriaQuery<ReservaMateriaPrima> cq = criteriaBuilder.createQuery(ReservaMateriaPrima.class);
		
		Root<ReservaMateriaPrima> root = cq.from(ReservaMateriaPrima.class);
		
		cq.select(root);
		
		Query query = session.createQuery(cq);
		
		return query.getResultList();
	}

	@Override
	public void guardarReservaMateriaPrima(
			ReservaMateriaPrima reservaMateriaPrima) {
Session session = sessionFactory.getCurrentSession();
		
		session.saveOrUpdate(reservaMateriaPrima);

	}

}
