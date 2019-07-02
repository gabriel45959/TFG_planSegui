package plansegui.hibernate.services;

import java.util.List;

import plansegui.hibernate.entities.EstadoPedido;

public interface EstadoPedidoService {
	public List<EstadoPedido> getEstadoPedido();

	public void guardarEstadoPedido(EstadoPedido estadoPedido);

	public EstadoPedido getEstadoPedido(Long id);
}
