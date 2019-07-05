package plansegui.hibernate.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity(name="detalleCompraMateriaPrima")
@Table(name = "detalle_compra_materia_prima", schema = "proceso_fabricacion_tfg")
public class DetalleCompraMateriaPrima {

	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dcmp_id")
	private long id;

	@OneToOne()
	@JoinColumn(name = "dcmp_materia_prima", referencedColumnName = "ma_id")
	private MateriaPrima materiaPrima;
	
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "dcmp_co_materia_prima")
	private CompraMateriaPrima compraMateriaPrima;
	
	@Column(name = "dcmp_cantidad")
	private int cantidad;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public MateriaPrima getMateriaPrima() {
		return materiaPrima;
	}

	public void setMateriaPrima(MateriaPrima materiaPrima) {
		this.materiaPrima = materiaPrima;
	}

	public CompraMateriaPrima getCompraMateriaPrima() {
		return compraMateriaPrima;
	}

	public void setCompraMateriaPrima(CompraMateriaPrima compraMateriaPrima) {
		this.compraMateriaPrima = compraMateriaPrima;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


	
	
	
}
