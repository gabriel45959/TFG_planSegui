package plansegui.hibernate.dao;

import java.util.List;

import plansegui.hibernate.entities.DetalleCompraMateriaPrima;

public interface DetalleCompraMateriaPrimaDao {
	public List<DetalleCompraMateriaPrima> getDetalleCompraMateriaPrima();
	
	public DetalleCompraMateriaPrima getDetalleCompraMateriaPrima(Long id);
	
	public void actualizarDetalleCompraMateriaPrima(DetalleCompraMateriaPrima detalleCompraMateriaPrima);
	
	public void guardarDetalleCompraMateriaPrima(DetalleCompraMateriaPrima detalleCompraMateriaPrima);
	
	
}
