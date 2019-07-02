package plansegui.hibernate.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import plansegui.hibernate.dao.MaquinariaDao;
import plansegui.hibernate.entities.Maquinaria;
import plansegui.hibernate.services.MaquinariaService;

@Service
public class MaquinariaServiceImpl implements MaquinariaService {

	@Autowired
	private MaquinariaDao maquinariaDao;

	@Override
	@Transactional
	public List<Maquinaria> getMaquinaria() {

		return maquinariaDao.getMaquinaria();
	}

	@Override
	@Transactional
	public void guardarMaquinaria(Maquinaria maquinaria) {
		maquinariaDao.guardarMaquinaria(maquinaria);

	}

}
