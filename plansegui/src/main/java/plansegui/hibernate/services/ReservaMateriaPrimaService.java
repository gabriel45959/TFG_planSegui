package plansegui.hibernate.services;

import java.util.List;

import plansegui.hibernate.entities.ReservaMateriaPrima;

public interface ReservaMateriaPrimaService {
	public List<ReservaMateriaPrima> getReservaMateriaPrima();

	public ReservaMateriaPrima getReservaMateriaPrima(Long id);

	public void guardarReservaMateriaPrima(
			ReservaMateriaPrima reservaMateriaPrima);
}
