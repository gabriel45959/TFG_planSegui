package plansegui.hibernate.services;

import java.util.List;

import plansegui.hibernate.entities.Pedido;

public interface PedidoService {
	
	public List<Pedido> getPedido();
	
	public void guardarPedido(Pedido pedido);
	
   
}
