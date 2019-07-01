package plansegui.hibernate.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "usuario", schema="proceso_fabricacion_tfg")
public class Usuario {

	@Id
	@Column(name = "nombre_usuario")

	
	@NotNull
	private String nombreUsuario;
	

	@NotNull
	@Column(name = "nombre")
	private String nombre;
	
	@NotNull
	@Column(name = "apellido")
	private String apellido;
	
	@NotNull
	@Column(name = "clave")
	private String clave;
	
	@Column(name = "habilitado", nullable = false,columnDefinition = "TINYINT(1)")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean habilitado;
	
	@OneToMany( mappedBy="usuario", cascade = CascadeType.ALL,  orphanRemoval = true )
	private List<Role> role = new ArrayList<>();
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Usuario))
			return false;
		return nombreUsuario != null && nombreUsuario.equals(((Usuario) o).getNombreUsuario());
	}

	public void addRole(Role role1) {
		role.add(role1);
		role1.setUsuario(this);
	}
	
	public void removeRole(Role role1) {
		role.remove(role1);
		role1.setUsuario(null);
	}
	

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public List<Role> getRole() {
		return role;
	}

	public void setRole(List<Role> role) {
		this.role = role;
	}

	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}
	
	

}
