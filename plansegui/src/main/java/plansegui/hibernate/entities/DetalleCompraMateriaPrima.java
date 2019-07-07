package plansegui.hibernate.entities;

import java.util.Date;

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

import org.springframework.lang.NonNull;


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
	
	@NonNull
	@Column(name = "dcmp_nro_factura")
	private String nroFactura;
	
	@Column(name = "dcmp_fecha_de_llegada")
	private Date fechaLlegada;

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

	public String getNroFactura() {
		return nroFactura;
	}

	public void setNroFactura(String nroFactura) {
		this.nroFactura = nroFactura;
	}

	public Date getFechaLlegada() {
		return fechaLlegada;
	}

	public void setFechaLlegada(Date fechaLlegada) {
		this.fechaLlegada = fechaLlegada;
	}


	
	
	
}
