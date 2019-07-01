package plansegui.hibernate.dao;

import java.util.List;

import plansegui.hibernate.entities.EstadoPedido;

public interface EstadoPedidoDao {
	public List<EstadoPedido> getEstadoPedido();

	public void guardarEstadoPedido(EstadoPedido estadoPedido);
}
