package plansegui.hibernate.services;

import java.util.List;

import plansegui.hibernate.entities.Producto;

public interface ProductoService {

	public List<Producto> getProducto();
	
	public void guardarProducto(Producto producto);
	
	public Producto getProducto(Long id);
	
}
