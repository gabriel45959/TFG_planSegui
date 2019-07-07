package plansegui.hibernate.dao;

import java.util.List;

import plansegui.hibernate.entities.MateriaPrima;

public interface MateriaPrimaDao {
	public List<MateriaPrima> getMateriaPrima();
	public MateriaPrima getMateriaPrima(Long id);
	public void guardarMateriaPrima(MateriaPrima materiaPrima);
}
