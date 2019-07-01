package plansegui.hibernate.services;

import java.util.List;

import plansegui.hibernate.entities.DetallePedido;

public interface DetallePedidoService {
	public List<DetallePedido> getDetallePedido();
	
	public void guardarDetallePedido(DetallePedido detallePedido);
}
