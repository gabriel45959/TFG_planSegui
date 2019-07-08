package plansegui.hibernate.dao;

import java.util.List;

import plansegui.hibernate.entities.CompraMateriaPrima;
import plansegui.hibernate.entities.Planificacion;

public interface PlanificacionDao {
	public List<Planificacion> getPlanificacion();
	
	public Planificacion getPlanificacion(Long id);

	public void guardarPlanificacion(Planificacion planifiacion);
}
