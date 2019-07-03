package plansegui.hibernate.entities;

import java.sql.Date;

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
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;


@Entity(name = "problemas_reportados")
@Table(name = "problemas_reportados", schema="proceso_fabricacion_tfg")
public class ProblemaReportado {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id",unique=true )
	private Long id;
	
	@NotNull
	@Column(name = "pr_fecha_resolucion")
	private Date fechaResolucion;
	
	@NotNull
	@Column(name = "pr_observaciones")
	private String observaciones;
	
	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pr_tipo_problema", referencedColumnName = "id")
	private TipoProblema tipoProblema;
	
	@Column(name = "pr_estado", nullable = false,columnDefinition = "TINYINT(1)")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean estado;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "pr_detalle_pedido")
	private DetallePedido detallePedido;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof ProblemaReportado))
			return false;
		return id != null && id.equals(((ProblemaReportado) o).getId());
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoProblema getTipoProblema() {
		return tipoProblema;
	}

	public void setTipoProblema(TipoProblema tipoProblema) {
		this.tipoProblema = tipoProblema;
	}

	public Date getFechaResolucion() {
		return fechaResolucion;
	}

	public void setFechaResolucion(Date fechaResolucion) {
		this.fechaResolucion = fechaResolucion;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public boolean getEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public DetallePedido getDetallePedido() {
		return detallePedido;
	}

	public void setDetallePedido(DetallePedido detallePedido) {
		this.detallePedido = detallePedido;
	}
	
	
	

}
