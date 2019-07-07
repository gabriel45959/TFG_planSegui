package plansegui.hibernate.services;

import java.util.List;

import plansegui.hibernate.entities.MateriaPrima;

public interface MateriaPrimaService {

	public List<MateriaPrima> getMateriaPrima();

	public MateriaPrima getMateriaPrima(Long id);

	public void guardarMateriaPrima(MateriaPrima materiaPrima);
}
