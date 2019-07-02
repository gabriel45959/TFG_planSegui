package plansegui.hibernate.dao;

import java.util.List;

import plansegui.hibernate.entities.TipoProblema;

public interface TipoProblemaDao {
	
	public List<TipoProblema> getTipoProblema();
	
	public void guardarTipoProblema(TipoProblema tipoProblema);
	
	public TipoProblema getTipoProblema(Long id);
}
