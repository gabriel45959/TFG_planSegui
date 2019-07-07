package plansegui.hibernate.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import plansegui.hibernate.dao.ReservaMateriaPrimaDao;
import plansegui.hibernate.entities.ReservaMateriaPrima;
import plansegui.hibernate.services.ReservaMateriaPrimaService;

@Service
public class ReservaMateriaPrimaServiceImpl implements
		ReservaMateriaPrimaService {

	@Autowired
	private ReservaMateriaPrimaDao reservaMateriaPrimaDao;

	@Override
	@Transactional
	public List<ReservaMateriaPrima> getReservaMateriaPrima() {

		return reservaMateriaPrimaDao.getReservaMateriaPrima();
	}

	@Override
	@Transactional
	public void guardarReservaMateriaPrima(
			ReservaMateriaPrima reservaMateriaPrima) {
		reservaMateriaPrimaDao.guardarReservaMateriaPrima(reservaMateriaPrima);

	}

	@Override
	@Transactional
	public ReservaMateriaPrima getReservaMateriaPrima(Long id) {

		return reservaMateriaPrimaDao.getReservaMateriaPrima(id);
	}

}
