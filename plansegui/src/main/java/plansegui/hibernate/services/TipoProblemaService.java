package plansegui.hibernate.services;

import java.util.List;

import plansegui.hibernate.entities.TipoProblema;

public interface TipoProblemaService {
	public List<TipoProblema> getTipoProblema();
	
	public void guardarTipoProblema(TipoProblema tipoProblema);
	
	public TipoProblema getTipoProblema(Long id);
	
}
