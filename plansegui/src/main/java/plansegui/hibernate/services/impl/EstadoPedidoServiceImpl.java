package plansegui.hibernate.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import plansegui.hibernate.dao.EstadoPedidoDao;
import plansegui.hibernate.entities.EstadoPedido;
import plansegui.hibernate.services.EstadoPedidoService;

@Service
public class EstadoPedidoServiceImpl implements EstadoPedidoService {

	@Autowired
	private EstadoPedidoDao estadoPedidoDao;
	
	@Override
	@Transactional
	public List<EstadoPedido> getEstadoPedido() {
		
		return estadoPedidoDao.getEstadoPedido();
	}

	@Override
	@Transactional
	public void guardarEstadoPedido(EstadoPedido estadoPedido) {
		estadoPedidoDao.guardarEstadoPedido(estadoPedido);

	}

	@Override
	@Transactional
	public EstadoPedido getEstadoPedido(Long id) {
		
		return estadoPedidoDao.getEstadoPedido(id);
	}

}
