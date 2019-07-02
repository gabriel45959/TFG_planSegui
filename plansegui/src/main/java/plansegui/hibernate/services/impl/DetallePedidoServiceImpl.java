package plansegui.hibernate.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import plansegui.hibernate.dao.DetallePedidoDao;
import plansegui.hibernate.entities.DetallePedido;
import plansegui.hibernate.services.DetallePedidoService;

@Service
public class DetallePedidoServiceImpl implements DetallePedidoService {

	@Autowired
	private DetallePedidoDao detallePedidoDao;
	
	@Override
	@Transactional
	public List<DetallePedido> getDetallePedido() {
			
		return detallePedidoDao.getDetallePedido();
	}

	@Override
	@Transactional
	public void guardarDetallePedido(DetallePedido detallePedido) {
		detallePedidoDao.getDetallePedido();

	}

	@Override
	@Transactional
	public DetallePedido getDetallePedido(Long id) {
		
		return detallePedidoDao.getDetallePedido(id);
	}

}