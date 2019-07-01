package plansegui.hibernate.dao;

import java.util.List;

import plansegui.hibernate.entities.Maquinaria;

public interface MaquinariaDao {
	  public List<Maquinaria> getMaquinaria();
	  public void guardarMaquinaria(Maquinaria maquinaria); 
}
