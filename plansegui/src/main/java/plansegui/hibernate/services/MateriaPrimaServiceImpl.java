package plansegui.hibernate.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import plansegui.hibernate.dao.MateriaPrimaDao;
import plansegui.hibernate.entities.MateriaPrima;

@Service
public class MateriaPrimaServiceImpl implements MateriaPrimaService {

	@Autowired
	private MateriaPrimaDao materiaPrimaDao;
	
	@Override
	@Transactional
	public List<MateriaPrima> getMateriaPrima() {
		
		return materiaPrimaDao.getMateriaPrima();
	}

	@Override
	@Transactional
	public void guardarMateriaPrima(MateriaPrima materiaPrima) {
		materiaPrimaDao.guardarMateriaPrima(materiaPrima);

	}

}
