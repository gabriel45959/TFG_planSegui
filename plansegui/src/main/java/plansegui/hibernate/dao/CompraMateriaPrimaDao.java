package plansegui.hibernate.dao;

import java.util.List;

import plansegui.hibernate.entities.CompraMateriaPrima;

public interface CompraMateriaPrimaDao {
	
	public List<CompraMateriaPrima> getCompraMateriaPrima();

	public void guardarCompraMateriaPrima(CompraMateriaPrima compraMateriaPrima);
}
