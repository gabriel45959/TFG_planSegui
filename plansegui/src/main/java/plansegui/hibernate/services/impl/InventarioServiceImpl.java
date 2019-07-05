package plansegui.hibernate.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import plansegui.hibernate.dao.InventarioDao;
import plansegui.hibernate.entities.Inventario;
import plansegui.hibernate.services.InventarioService;
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
	@Override
	@Transactional
	public List<Inventario> getInventarioPorMateriaPrima(Long idProduct){
		return inventarioDao.getInventarioPorMateriaPrima(idProduct);
	}

}
