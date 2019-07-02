package plansegui.hibernate.dao;

import java.util.List;

import plansegui.hibernate.entities.Producto;

public interface ProductoDao {
	
   public List<Producto> getProducto();
   
   public void guardarPoducto(Producto producto);
   
   public Producto getProducto(Long id);
   
}
