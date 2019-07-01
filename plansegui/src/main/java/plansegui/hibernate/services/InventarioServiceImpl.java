package plansegui.hibernate.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import plansegui.hibernate.dao.InventarioDao;
import plansegui.hibernate.entities.Inventario;
@Service
public class InventarioServiceImpl implements InventarioService {

	@Autowired
	private InventarioDao inventarioDao;
	
	@Override
	@Transactional
	public List<Inventario> getInventario() {

		return inventarioDao.getInventario();
	}

	@Override
	@Transactional
	public void guardarInventario(Inventario inventario) {
		inventarioDao.guardarInventario(inventario);

	}

}
