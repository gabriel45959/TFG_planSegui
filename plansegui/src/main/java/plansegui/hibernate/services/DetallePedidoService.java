package plansegui.hibernate.services;

import java.util.List;

import plansegui.hibernate.entities.DetallePedido;

public interface DetallePedidoService {
	public List<DetallePedido> getDetallePedido();
	
	public List<DetallePedido> getdetallePedidoPorEstado(int idEstado);
	
	public void guardarDetallePedido(DetallePedido detallePedido);
	
	public DetallePedido getDetallePedido(Long id);
	
	public void actualizarDetallePedido(DetallePedido detallePedido);
}
