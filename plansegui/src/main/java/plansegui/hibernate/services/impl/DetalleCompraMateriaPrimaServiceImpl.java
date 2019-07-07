package plansegui.hibernate.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import plansegui.hibernate.dao.DetalleCompraMateriaPrimaDao;
import plansegui.hibernate.entities.DetalleCompraMateriaPrima;
import plansegui.hibernate.services.DetalleCompraMateriaPrimaService;
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

	@Override
	@Transactional
	public DetalleCompraMateriaPrima getDetalleCompraMateriaPrima(Long id) {
		return detalleCompraMateriaPrimaDao.getDetalleCompraMateriaPrima(id);
	}

	@Override
	@Transactional
	public void actualizarDetalleCompraMateriaPrima(
			DetalleCompraMateriaPrima detalleCompraMateriaPrima) {
		
		detalleCompraMateriaPrimaDao.actualizarDetalleCompraMateriaPrima(detalleCompraMateriaPrima);
		
	}

}
