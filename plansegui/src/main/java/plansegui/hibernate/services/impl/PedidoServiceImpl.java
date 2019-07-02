package plansegui.hibernate.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import plansegui.hibernate.dao.PedidoDao;
import plansegui.hibernate.entities.Pedido;
import plansegui.hibernate.services.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService {

	@Autowired
	private PedidoDao pedidoDao;
	
	@Override
	@Transactional
	public List<Pedido> getPedido() {
		
		return pedidoDao.getPedido();
	}

	@Override
	@Transactional
	public void guardarPedido(Pedido pedido) {
		
		pedidoDao.guardarPedido(pedido);

	}

}
