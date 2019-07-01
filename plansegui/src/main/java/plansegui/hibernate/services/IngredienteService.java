package plansegui.hibernate.services;

import java.util.List;

import plansegui.hibernate.entities.Ingrediente;

public interface IngredienteService {
  public List<Ingrediente> getIngrediente();
  public void guardarIngrediente(Ingrediente ingrediente);
}
