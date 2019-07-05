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
	@Column(name = "es_id",unique=true)
	private Long id;
	
	@Column(name = "es_nombre")
	private String nombre;
	

	@Column(name = "es_descripcion")
	private String descipcion;
	
	@Column(name = "es_valor_visual")
	private String valorVisual;


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


	public String getDescipcion() {
		return descipcion;
	}


	public void setDescipcion(String descipcion) {
		this.descipcion = descipcion;
	}


	public String getValorVisual() {
		return valorVisual;
	}


	public void setValorVisual(String valorVisual) {
		this.valorVisual = valorVisual;
	}
	
	
	
}
