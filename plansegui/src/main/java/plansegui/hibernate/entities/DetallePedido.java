package plansegui.hibernate.entities;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "detallePedido")
@Table(name = "detalle_pedido", schema = "proceso_fabricacion_tfg")
public class DetallePedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "de_id", unique = true)
	private Long id;

	@OneToOne()
	@JoinColumn(name = "de_producto", referencedColumnName = "pr_id")
	private Producto producto;

	@Column(name = "de_cantidad")
	private int cantidad;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "de_pedido")
	private Pedido pedido;

	@OneToOne()
	@JoinColumn(name = "de_estado", referencedColumnName = "es_id")
	private EstadoPedido estado;

	@OneToMany(mappedBy = "detallePedido", orphanRemoval = true)
	private List<Planificacion> planificacion;

	@OneToMany(mappedBy = "detallePedido", orphanRemoval = true)
	private List<CompraMateriaPrima> compraMateriaPrima;

	@OneToMany(mappedBy = "detallePedido", orphanRemoval = true)
	private List<ReservaMateriaPrima> reservaMateriaPrima;
	
	@OneToMany(mappedBy = "detallePedido", orphanRemoval = true)
	private List<ProblemaReportado> problemaReportado;
	

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof DetallePedido))
			return false;
		return id != null && id.equals(((DetallePedido) o).getId());
	}
	public void addProblemaReportado(ProblemaReportado problemaReportado) {
		this.problemaReportado.add(problemaReportado);
		problemaReportado.setDetallePedido(this);
	}

	public void removeProblemaReportado(ProblemaReportado problemaReportado) {
		this.problemaReportado.remove(problemaReportado);
		problemaReportado.setDetallePedido(null);
	}
	public void addPlanificacion(Planificacion planificacion1) {
		planificacion.add(planificacion1);
		planificacion1.setDetallePedido(this);
	}

	public void removePlanificacion(Planificacion planificacion1) {
		planificacion.remove(planificacion1);
		planificacion1.setDetallePedido(null);
	}

	public void addReservaMateriaPrima(ReservaMateriaPrima reservaMateriaPrima1) {
		reservaMateriaPrima.add(reservaMateriaPrima1);
		reservaMateriaPrima1.setDetallePedido(this);
	}

	public void removeReservaMateriaPrima(ReservaMateriaPrima reservaMateriaPrima1) {
		reservaMateriaPrima.remove(reservaMateriaPrima1);
		reservaMateriaPrima1.setDetallePedido(null);
	}
	
	public void addCompraMateriaPrima(CompraMateriaPrima compraMateriaPrima1) {
		compraMateriaPrima.add(compraMateriaPrima1);
		compraMateriaPrima1.setDetallePedido(this);
	}

	public void removeCompraMateriaPrima(CompraMateriaPrima compraMateriaPrima1) {
		compraMateriaPrima.remove(compraMateriaPrima1);
		compraMateriaPrima1.setDetallePedido(null);
	}
	
	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

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

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public EstadoPedido getEstado() {
		return estado;
	}

	public void setEstado(EstadoPedido estado) {
		this.estado = estado;
	}

	public List<Planificacion> getPlanificacion() {
		return planificacion;
	}

	public void setPlanificacion(List<Planificacion> planificacion) {
		this.planificacion = planificacion;
	}

	public List<CompraMateriaPrima> getCompraMateriaPrima() {
		return compraMateriaPrima;
	}

	public void setCompraMateriaPrima(
			List<CompraMateriaPrima> compraMateriaPrima) {
		this.compraMateriaPrima = compraMateriaPrima;
	}

	public List<ReservaMateriaPrima> getReservaMateriaPrima() {
		return reservaMateriaPrima;
	}

	public void setReservaMateriaPrima(List<ReservaMateriaPrima> reservaMateriaPrima) {
		this.reservaMateriaPrima = reservaMateriaPrima;
	}

	public List<ProblemaReportado> getProblemaReportado() {
		return problemaReportado;
	}

	public void setProblemaReportado(List<ProblemaReportado> problemaReportado) {
		this.problemaReportado = problemaReportado;
	}

	
	
}
