package plansegui.hibernate.dao;

import java.util.List;

import plansegui.hibernate.entities.Ingrediente;

public interface IngredienteDao {
	  public List<Ingrediente> getIngrediente();
	  public void guardarIngrediente(Ingrediente ingrediente);
}
