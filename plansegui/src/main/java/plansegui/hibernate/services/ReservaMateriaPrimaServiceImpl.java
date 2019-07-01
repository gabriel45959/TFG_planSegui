package plansegui.hibernate.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import plansegui.hibernate.dao.ReservaMateriaPrimaDao;
import plansegui.hibernate.entities.ReservaMateriaPrima;

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

}
