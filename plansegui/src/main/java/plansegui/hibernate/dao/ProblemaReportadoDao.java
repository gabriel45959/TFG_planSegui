package plansegui.hibernate.dao;

import java.util.List;

import plansegui.hibernate.entities.ProblemaReportado;

public interface ProblemaReportadoDao {

	public List<ProblemaReportado> getProblemaReportado();
	
	public void guardarProblemaReportado(ProblemaReportado problemaReportado);
	
}
