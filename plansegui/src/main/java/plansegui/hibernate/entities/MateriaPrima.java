package plansegui.hibernate.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="MateriaPrima")
@Table(name = "materia_prima", schema="proceso_fabricacion_tfg")
public class MateriaPrima {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ma_id")
	private long id;
	
	@Column(name = "ma_nombre")
	private String nombre;
	


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

		
 
}
