package plansegui.hibernate.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "producto", schema = "proceso_fabricacion_tfg")
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pr_id")
	private Long id;

	@Column(name = "pr_nombre")
	private String nombre;

	@OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Ingrediente> ingredientes;

	@OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Maquinaria> maquinaria;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Ingrediente> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(List<Ingrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}

	public List<Maquinaria> getIdMaquinaria() {
		return maquinaria;
	}

	public void setMaquinaria(List<Maquinaria> maquinaria) {
		this.maquinaria = maquinaria;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Producto))
			return false;
		return id != null && id.equals(((Producto) o).getId());
	}

	public void addMaquinaria(Maquinaria maquinaria1) {
		maquinaria.add(maquinaria1);
		maquinaria1.setProducto(this);
	}

	public void removeMaquinaria(Maquinaria maquinaria1) {
		maquinaria.remove(maquinaria1);
		maquinaria1.setProducto(null);
	}

	public void addIngrediente(Ingrediente ingrediente1) {
		ingredientes.add(ingrediente1);
		ingrediente1.setProducto(this);
	}

	public void removeIngrediente(Ingrediente ingrediente1) {
		ingredientes.remove(ingrediente1);
		ingrediente1.setProducto(null);
	}

}
