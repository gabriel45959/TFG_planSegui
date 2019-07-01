package plansegui.hibernate.dao;

import java.util.List;

import plansegui.hibernate.entities.Parametro;

public interface ParametroDao {
	public List<Parametro> getParametro();

	public void guardarParametro(Parametro parametro);
}
