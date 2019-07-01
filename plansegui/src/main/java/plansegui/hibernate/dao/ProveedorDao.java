package plansegui.hibernate.dao;

import java.util.List;

import plansegui.hibernate.entities.Proveedor;

public interface ProveedorDao {
	public List<Proveedor> getProveedor();

	public void guardarProveedor(Proveedor proveedor);
}
