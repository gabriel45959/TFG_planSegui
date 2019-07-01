package plansegui.hibernate.dao;

import java.util.List;

import plansegui.hibernate.entities.Pedido;

public interface PedidoDao {
	
	public List<Pedido> getPedido();

	public void guardarPedido(Pedido pedido);
}
