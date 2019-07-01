package plansegui.hibernate.services;

import java.util.List;

import plansegui.hibernate.entities.Usuario;

public interface UsuarioService {
	public List<Usuario> getUsuarios();

	public void guardarUsuario(Usuario usuario);

	public Usuario getUsuario(String nombre);
   
   
}
