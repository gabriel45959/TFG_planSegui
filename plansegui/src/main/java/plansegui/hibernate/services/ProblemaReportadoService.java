package plansegui.hibernate.services;

import java.util.List;

import plansegui.hibernate.entities.ProblemaReportado;

public interface ProblemaReportadoService {

	public List<ProblemaReportado> getProblemaReportado();
	
	public void guardarProblemaReportado(ProblemaReportado problemaReportado);
	
	
	
}
