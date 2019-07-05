package plansegui.hibernate.entities;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ingrediente", schema="proceso_fabricacion_tfg")
public class Ingrediente {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "in_id")
	private Long id;
	
	@OneToOne()
    @JoinColumn(name = "in_producto", referencedColumnName = "pr_id")
	private Producto producto;
	
	@OneToOne()
	@JoinColumn(name = "in_materia_prima", referencedColumnName = "ma_id")
	private MateriaPrima materiaPrima;
	
	@Column(name = "in_porcentaje")
	private int porcentaje;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public MateriaPrima getMateriaPrima() {
		return this.materiaPrima;
	}

	public void setMateriaPrima(MateriaPrima materiaPrima) {
		this.materiaPrima = materiaPrima;
	}

	public int getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(int porcentaje) {
		this.porcentaje = porcentaje;
	}
	
	
	
}
