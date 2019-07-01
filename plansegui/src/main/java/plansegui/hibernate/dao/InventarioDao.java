package plansegui.hibernate.dao;

import java.util.List;

import plansegui.hibernate.entities.Inventario;

public interface InventarioDao {
	  public List<Inventario> getInventario();
	  public void guardarInventario(Inventario inventario);
}
