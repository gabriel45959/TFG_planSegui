package plansegui.hibernate.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import plansegui.hibernate.dao.ProblemaReportadoDao;
import plansegui.hibernate.entities.ProblemaReportado;
import plansegui.hibernate.services.ProblemaReportadoService;


@Service
public class ProblemaReportadoServiceImpl implements ProblemaReportadoService {

	@Autowired
	private ProblemaReportadoDao problemaReportadoDao;
	
	@Override
	@Transactional
	public List<ProblemaReportado> getProblemaReportado() {
		
		return problemaReportadoDao.getProblemaReportado();
	}

	@Override
	@Transactional
	public void guardarProblemaReportado(ProblemaReportado problemaReportado) {
	
		problemaReportadoDao.guardarProblemaReportado(problemaReportado);

	}

}
