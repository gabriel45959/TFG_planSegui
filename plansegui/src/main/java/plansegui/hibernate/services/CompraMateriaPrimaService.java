package plansegui.hibernate.services;

import java.util.List;

import plansegui.hibernate.entities.CompraMateriaPrima;

public interface CompraMateriaPrimaService {
	
	public List<CompraMateriaPrima> getCompraMateriaPrima();
	
	public void guardarCompraMateriaPrima(CompraMateriaPrima compraMateriaPrima);

}
