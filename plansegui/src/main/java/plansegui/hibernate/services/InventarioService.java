package plansegui.hibernate.services;

import java.util.List;

import plansegui.hibernate.entities.Inventario;

public interface InventarioService {
  public List<Inventario> getInventario();
  public void guardarInventario(Inventario inventario);
  public List<Inventario> getInventarioPorMateriaPrima(Long idProduct);
}
