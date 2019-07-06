package plansegui.hibernate.dao;

import java.util.List;

import plansegui.hibernate.entities.DetallePedido;
import plansegui.hibernate.entities.Pedido;

public interface DetallePedidoDao {
	public List<DetallePedido> getDetallePedido();
	
	public List<DetallePedido> getdetallePedidoPorEstado(int idEstado);
	
	public void guardarDetallePedido(DetallePedido detallePedido);
	
	public DetallePedido getDetallePedido(Long id);
	
	public void actualizarDetallePedido(DetallePedido detallePedido);
	
	
	
}
