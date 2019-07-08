package plansegui.hibernate.services;

import java.util.List;

import plansegui.hibernate.entities.Planificacion;

public interface PlanificacionService {
	public List<Planificacion> getPlanificacion();
	
	public Planificacion getPlanificacion(Long id);

	public void guardarPlanificacion(Planificacion planifiacion);
}
