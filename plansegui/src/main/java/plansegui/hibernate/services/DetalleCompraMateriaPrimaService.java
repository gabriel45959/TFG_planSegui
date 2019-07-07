package plansegui.hibernate.services;

import java.util.List;

import plansegui.hibernate.entities.DetalleCompraMateriaPrima;

public interface DetalleCompraMateriaPrimaService {
	public List<DetalleCompraMateriaPrima> getDetalleCompraMateriaPrima();
	
	public DetalleCompraMateriaPrima getDetalleCompraMateriaPrima(Long id);
	
	public void actualizarDetalleCompraMateriaPrima(DetalleCompraMateriaPrima detalleCompraMateriaPrima);
	
	public void guardarDetalleCompraMateriaPrima(DetalleCompraMateriaPrima detalleCompraMateriaPrima);
}
