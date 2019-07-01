package plansegui.hibernate.entities;

import java.sql.Date;

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
@Table(name = "planificacion", schema="proceso_fabricacion_tfg")
public class Planificacion {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "pl_id")
	private long id;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "pl_id_detalle_pedido")
	private DetallePedido detallePedido;
	
	@Column(name = "pl_fecha_inicio_estimada")
	private Date fechaInicioEstimada;
	
	@Column(name = "pl_fecha_entrega_estimada")
	private Date fechaEntregaEstimada;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public DetallePedido getDetallePedido() {
		return detallePedido;
	}

	public void setDetallePedido(DetallePedido detallePedido) {
		this.detallePedido = detallePedido;
	}

	public Date getFechaInicioEstimada() {
		return fechaInicioEstimada;
	}

	public void setFechaInicioEstimada(Date fechaInicioEstimada) {
		this.fechaInicioEstimada = fechaInicioEstimada;
	}

	public Date getFechaEntregaEstimada() {
		return fechaEntregaEstimada;
	}

	public void setFechaEntregaEstimada(Date fechaEntregaEstimada) {
		this.fechaEntregaEstimada = fechaEntregaEstimada;
	}
	
	
	
}
