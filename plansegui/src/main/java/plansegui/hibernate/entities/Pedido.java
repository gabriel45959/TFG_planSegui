package plansegui.hibernate.entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity(name = "Pedido")
@Table(name = "Pedido", schema="proceso_fabricacion_tfg")
public class Pedido {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "pe_id",unique=true )
	private long id;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pe_id_empresa", referencedColumnName = "id")
	private Empresa empresa;
	
	@NotNull
	@Column(name = "pe_nro_factura")
	private String nroFactura;
	
	@OneToMany( mappedBy="pedido", cascade = CascadeType.ALL,  orphanRemoval = true )
	private List<DetallePedido> detallePedido = new ArrayList<>();
	

	@Column(name = "pe_fecha_entrega")
	private Date fechaEntrega;
	
	@Column(name = "pe_fecha_creacion")
	private Date fechaCreacion;
	

	@Column(name = "pe_observaciones")
	private String observaciones;
	
	public void addDetallePedido(DetallePedido detallePedido) {
		this.detallePedido.add(detallePedido);
		detallePedido.setPedido(this);
    }
 
    public void removeDetallePedido(DetallePedido detallePedido) {
    	this.detallePedido.remove(detallePedido);
    	detallePedido.setPedido(null);
    }
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getNroFactura() {
		return nroFactura;
	}

	public void setNroFactura(String nroFactura) {
		this.nroFactura = nroFactura;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public List<DetallePedido> getDetallePedido() {
		return detallePedido;
	}

	public void setDetallePedido(List<DetallePedido> detallePedido) {
		this.detallePedido = detallePedido;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	
	
}
