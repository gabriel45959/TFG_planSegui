package plansegui.hibernate.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import plansegui.hibernate.dao.UsuarioDao;
import plansegui.hibernate.entities.Usuario;
import plansegui.hibernate.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Override
	@Transactional
	public List<Usuario> getUsuarios() {
		
		return usuarioDao.getUsuarios();
	}

	@Override
	@Transactional
	public void guardarUsuario(Usuario usuario) {
		usuarioDao.guardarUsuario(usuario);
	}

	@Override
	@Transactional
	public Usuario getUsuario(String nombre) {
		
		return usuarioDao.getUsuario(nombre);
	}

}
