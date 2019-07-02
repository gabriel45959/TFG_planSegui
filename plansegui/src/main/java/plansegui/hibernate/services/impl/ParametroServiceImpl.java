package plansegui.hibernate.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import plansegui.hibernate.dao.ParametroDao;
import plansegui.hibernate.entities.Parametro;
import plansegui.hibernate.services.ParametroService;

@Service
public class ParametroServiceImpl implements ParametroService {

	@Autowired
	private ParametroDao parametroDao;
	
	@Override
	@Transactional
	public List<Parametro> getParametro() {
		
		return parametroDao.getParametro();
	}

	@Override
	@Transactional
	public void guardarParametro(Parametro parametro) {
		parametroDao.guardarParametro(parametro);

	}

}
