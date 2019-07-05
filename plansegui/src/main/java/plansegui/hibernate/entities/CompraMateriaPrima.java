package plansegui.hibernate.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name="compraMateriaPrima")
@Table(name = "compra_materia_prima", schema = "proceso_fabricacion_tfg")
public class CompraMateriaPrima {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "co_id")
	private Long id;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "co_detalle_pedido")
	private DetallePedido detallePedido;
	
	
	@OneToMany( mappedBy="compraMateriaPrima", cascade = CascadeType.ALL,  orphanRemoval = true )
	private List<DetalleCompraMateriaPrima> detalleCompraMateriaPrima  = new ArrayList<>();

	

	@Column(name = "co_nro_factura")
	private String nroFactura;

	@Column(name = "co_fecha_de_llegada")
	private Date fechaLlegada;


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof CompraMateriaPrima))
			return false;
		return id != null && id.equals(((CompraMateriaPrima) o).getId());
	}

	public void addDetalleCompraMateriaPrima(DetalleCompraMateriaPrima detalleCompraMateriaPrima1) {
		detalleCompraMateriaPrima.add(detalleCompraMateriaPrima1);
		detalleCompraMateriaPrima1.setCompraMateriaPrima(this);
	}

	public void removeDetalleCompraMateriaPrima(DetalleCompraMateriaPrima detalleCompraMateriaPrima1) {
		detalleCompraMateriaPrima.remove(detalleCompraMateriaPrima1);
		detalleCompraMateriaPrima1.setCompraMateriaPrima(null);
	}
	
	public DetallePedido getDetallePedido() {
		return detallePedido;
	}

	public void setDetallePedido(DetallePedido detallePedido) {
		this.detallePedido = detallePedido;
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

	public List<DetalleCompraMateriaPrima> getDetalleCompraMateriaPrima() {
		return detalleCompraMateriaPrima;
	}

	public void setDetalleCompraMateriaPrima(
			List<DetalleCompraMateriaPrima> detalleCompraMateriaPrima) {
		this.detalleCompraMateriaPrima = detalleCompraMateriaPrima;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
