package plansegui.hibernate.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "maquinaria", schema="proceso_fabricacion_tfg")
public class Maquinaria {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ma_id")
	private long id;
	
	@Column(name = "ma_nombre")
	private String nombre;
	
	@Column(name = "ma_kg_produccion_xhs")
	private int kgProduccionXhs;
	
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "ma_id_producto")
	private Producto producto;

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

	public int getKgProduccionXhs() {
		return kgProduccionXhs;
	}

	public void setKgProduccionXhs(int kgProduccionXhs) {
		this.kgProduccionXhs = kgProduccionXhs;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	

	

}
