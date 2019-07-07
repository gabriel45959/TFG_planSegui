package plansegui.hibernate.dao;

import java.util.List;

import plansegui.hibernate.entities.ReservaMateriaPrima;

public interface ReservaMateriaPrimaDao {
	public List<ReservaMateriaPrima> getReservaMateriaPrima();

	public ReservaMateriaPrima getReservaMateriaPrima(Long id);

	public void guardarReservaMateriaPrima(ReservaMateriaPrima reservaMateriaPrima);
}
