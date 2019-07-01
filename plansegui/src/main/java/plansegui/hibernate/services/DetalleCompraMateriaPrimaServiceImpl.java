package plansegui.hibernate.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import plansegui.hibernate.dao.DetalleCompraMateriaPrimaDao;
import plansegui.hibernate.entities.DetalleCompraMateriaPrima;
@Service
public class DetalleCompraMateriaPrimaServiceImpl implements
		DetalleCompraMateriaPrimaService {

	@Autowired
	private DetalleCompraMateriaPrimaDao detalleCompraMateriaPrimaDao;
	
	@Override
	@Transactional
	public List<DetalleCompraMateriaPrima> getDetalleCompraMateriaPrima() {
		
		return detalleCompraMateriaPrimaDao.getDetalleCompraMateriaPrima();
	}

	@Override
	@Transactional
	public void guardarDetalleCompraMateriaPrima(
			DetalleCompraMateriaPrima detalleCompraMateriaPrima) {
		detalleCompraMateriaPrimaDao.guardarDetalleCompraMateriaPrima(detalleCompraMateriaPrima);

	}

}
