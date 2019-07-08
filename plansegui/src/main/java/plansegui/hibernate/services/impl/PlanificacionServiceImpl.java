package plansegui.hibernate.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import plansegui.hibernate.dao.PlanificacionDao;
import plansegui.hibernate.entities.Planificacion;
import plansegui.hibernate.services.PlanificacionService;

@Service
public class PlanificacionServiceImpl implements PlanificacionService {

	@Autowired
	private PlanificacionDao planifiacionDao;
	
	@Override
	@Transactional
	public List<Planificacion> getPlanificacion() {
		return planifiacionDao.getPlanificacion();
	}

	@Override
	@Transactional
	public Planificacion getPlanificacion(Long id) {
		return planifiacionDao.getPlanificacion(id);
	}

	@Override
	@Transactional
	public void guardarPlanificacion(Planificacion planifiacion) {
		planifiacionDao.guardarPlanificacion(planifiacion);
		
	}

	


}
