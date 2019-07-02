package plansegui.hibernate.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import plansegui.hibernate.dao.CompraMateriaPrimaDao;
import plansegui.hibernate.entities.CompraMateriaPrima;
import plansegui.hibernate.services.CompraMateriaPrimaService;

@Service
public class CompraMateriaPrimaServiceImpl implements CompraMateriaPrimaService {

	@Autowired
	private CompraMateriaPrimaDao compraMateriaPrimaDao;
	
	@Override
	@Transactional
	public List<CompraMateriaPrima> getCompraMateriaPrima() {
		
		return compraMateriaPrimaDao.getCompraMateriaPrima();
	}

	@Override
	@Transactional
	public void guardarCompraMateriaPrima(CompraMateriaPrima compraMateriaPrima) {
		compraMateriaPrimaDao.guardarCompraMateriaPrima(compraMateriaPrima);

	}

}
