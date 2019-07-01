package plansegui.hibernate.services;

import java.util.List;

import plansegui.hibernate.entities.Maquinaria;

public interface MaquinariaService {
  public List<Maquinaria> getMaquinaria();
  public void guardarMaquinaria(Maquinaria maquinaria); 
}
