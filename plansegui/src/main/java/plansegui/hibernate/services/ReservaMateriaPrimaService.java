package plansegui.hibernate.services;

import java.util.List;

import plansegui.hibernate.entities.ReservaMateriaPrima;

public interface ReservaMateriaPrimaService {
public List<ReservaMateriaPrima> getReservaMateriaPrima();
public void guardarReservaMateriaPrima(ReservaMateriaPrima reservaMateriaPrima);
}
