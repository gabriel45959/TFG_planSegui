package plansegui.hibernate.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "estado_pedido", schema="proceso_fabricacion_tfg")
public class EstadoPedido {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "es_id")
	private long id;
	
	@Column(name = "es_nombre")
	private String nombre;
	

	@Column(name = "es_descripcion")
	private String descipcion;


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDescipcion() {
		return descipcion;
	}


	public void setDescipcion(String descipcion) {
		this.descipcion = descipcion;
	}
	
	
	
}
