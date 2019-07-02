package plansegui.hibernate.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import plansegui.hibernate.dao.ProveedorDao;
import plansegui.hibernate.entities.Proveedor;
import plansegui.hibernate.services.ProveedorService;
@Service
public class ProveedorServiceImpl implements ProveedorService {

	@Autowired
	private ProveedorDao proveedorDao;
	
	@Override
	@Transactional
	public List<Proveedor> getProveedor() {
		
		return proveedorDao.getProveedor();
	}

	@Override
	@Transactional
	public void guardarProveedor(Proveedor proveedor) {
		proveedorDao.guardarProveedor(proveedor);

	}

}
