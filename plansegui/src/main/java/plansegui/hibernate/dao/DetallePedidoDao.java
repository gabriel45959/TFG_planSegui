package plansegui.hibernate.dao;

import java.util.List;

import plansegui.hibernate.entities.DetallePedido;

public interface DetallePedidoDao {
	public List<DetallePedido> getDetallePedido();
	
	public void guardarDetallePedido(DetallePedido detallePedido);
	
	public DetallePedido getDetallePedido(Long id);
}
