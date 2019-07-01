package plansegui.hibernate.dao;

import java.util.List;

import plansegui.hibernate.entities.Usuario;

public interface UsuarioDao {
	public List<Usuario> getUsuarios();

	public void guardarUsuario(Usuario usuario);

	public Usuario getUsuario(String nombre);
}
