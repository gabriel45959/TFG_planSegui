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

@Entity(name="ReservaMateriaPrima")
@Table(name = "reserva_materia_prima", schema="proceso_fabricacion_tfg")
public class ReservaMateriaPrima {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "re_id")
	private Long id;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "re_id_detalle_pedido")
	private DetallePedido detallePedido; 
	
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "re_id_materia_prima")
	private MateriaPrima materiaPrima;
	
	
	@Column(name = "re_cantidad")
	private int camtidad;
	
	@Column(name = "re_fecha_ingreso")
	private Date fechaIngreso;
	
	@Column(name = "re_fecha_egreso")
	private Date fechaEgreso;
	
	@Column(name = "re_nro_factura")
	private String nroFactura;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public DetallePedido getDetallePedido() {
		return detallePedido;
	}

	public void setDetallePedido(DetallePedido detallepedido) {
		this.detallePedido = detallepedido;
	}

	public MateriaPrima getMateriaPrima() {
		return materiaPrima;
	}

	public void setMateriaPrima(MateriaPrima materiaPrima) {
		this.materiaPrima = materiaPrima;
	}

	public int getCamtidad() {
		return camtidad;
	}

	public void setCamtidad(int camtidad) {
		this.camtidad = camtidad;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof MateriaPrima))
			return false;
		return id != null && id.equals(((MateriaPrima) o).getId());
	}
	

}
