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

import plansegui.hibernate.dao.UsuarioDao;
import plansegui.hibernate.entities.Usuario;

@Repository
public class UsuarioDaoImpl implements UsuarioDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Usuario> getUsuarios() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		
		CriteriaQuery<Usuario> cq = criteriaBuilder.createQuery(Usuario.class);
		
		Root<Usuario> root = cq.from(Usuario.class);
		
		cq.select(root);
		
		Query query = session.createQuery(cq);
		
		return query.getResultList();
	}

	@Override
	public void guardarUsuario(Usuario usuario) {
		
		Session session = sessionFactory.getCurrentSession();
		
		session.saveOrUpdate(usuario);
		session.clear();
		session.close();
	}

	@Override
	public Usuario getUsuario(String nombre) {
		Session session = sessionFactory.getCurrentSession();
		
		Usuario usuario=session.get(Usuario.class,nombre);
		
		return usuario;
	}

}
