package plansegui.hibernate.services;

import java.util.List;

import plansegui.hibernate.entities.Parametro;

public interface ParametroService {
	public List<Parametro> getParametro();

	public void guardarParametro(Parametro parametro);
}
