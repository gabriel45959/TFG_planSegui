package plansegui.hibernate.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import plansegui.hibernate.dao.TipoProblemaDao;
import plansegui.hibernate.entities.TipoProblema;
import plansegui.hibernate.services.TipoProblemaService;

@Service
public class TipoProblemaServiceImpl implements TipoProblemaService {

	@Autowired
	private TipoProblemaDao tipoProblemaDao;

	@Override
	@Transactional
	public List<TipoProblema> getTipoProblema() {
		return tipoProblemaDao.getTipoProblema();
	}

	@Override
	@Transactional
	public void guardarTipoProblema(TipoProblema tipoProblema) {

		tipoProblemaDao.guardarTipoProblema(tipoProblema);

	}

	@Override
	@Transactional
	public TipoProblema getTipoProblema(Long id) {
		return tipoProblemaDao.getTipoProblema(id);
	}

}
