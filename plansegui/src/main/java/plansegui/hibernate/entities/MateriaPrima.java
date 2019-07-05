package plansegui.hibernate.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity(name="MateriaPrima")
@Table(name = "materia_prima", schema="proceso_fabricacion_tfg")
public class MateriaPrima {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ma_id")
	private long id;
	
	@Column(name = "ma_nombre")
	private String nombre;
	
	
	@Column(name = "ma_kg_o_cantidad", nullable = false,columnDefinition = "TINYINT(1)")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean kgOCantidad;
	
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

	public boolean isKgOCantidad() {
		return kgOCantidad;
	}

	public void setKgOCantidad(boolean kgOCantidad) {
		this.kgOCantidad = kgOCantidad;
	}

		
 
}
