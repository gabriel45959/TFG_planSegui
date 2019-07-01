package plansegui.hibernate.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "proveedor", schema="proceso_fabricacion_tfg")
public class Proveedor {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "prov_id")
	private long id;
	
	@Column(name = "prov_nombre")
	private String cantidad;
	
	@Column(name = "prov_direccion")
	private String direccion;
	
	@Column(name = "porv_telefono")
	private String telefono;
	
	@Column(name = "prov_materia_prima")
	private long materiaPrima;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public long getMateriaPrima() {
		return materiaPrima;
	}

	public void setMateriaPrima(long materiaPrima) {
		this.materiaPrima = materiaPrima;
	}
	
	

}
