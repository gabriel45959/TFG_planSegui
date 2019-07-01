package plansegui.hibernate.services;

import java.util.List;

import plansegui.hibernate.entities.Proveedor;

public interface ProveedorService {
public List<Proveedor> getProveedor();
public void guardarProveedor(Proveedor proveedor);
}
