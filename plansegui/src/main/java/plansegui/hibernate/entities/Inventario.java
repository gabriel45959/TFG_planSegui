package plansegui.hibernate.entities;


import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "Inventario")
@Table(name = "inventario", schema="proceso_fabricacion_tfg")
public class Inventario {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "in_id")
	private Long id;
	
	@OneToOne()
    @JoinColumn(name = "in_id_materia_prima", referencedColumnName = "ma_id")
	private MateriaPrima materiaPrima;
	
	@Column(name = "in_cantidad")
	private int cantidad;
	
	@Column(name = "in_fecha_salida")
	private Date fechaVencimiento;

	@Column(name = "in_fecha_ingreso")
	private Date fechaIngreso;
	
	@Column(name = "in_nro_factura")
	private String nroFactura;

	public Long getId() {
		return id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Inventario))
			return false;
		return id != null && id.equals(((Inventario) o).getId());
	}
	
	public void setId(Long id) {
		this.id = id;
	}


	public MateriaPrima getMateriaPrima() {
		return materiaPrima;
	}


	public void setMateriaPrima(MateriaPrima materiaPrima) {
		this.materiaPrima = materiaPrima;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}


	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}


	public Date getFechaIngreso() {
		return fechaIngreso;
	}


	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

}
