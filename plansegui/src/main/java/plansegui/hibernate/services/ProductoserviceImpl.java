package plansegui.hibernate.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import plansegui.hibernate.dao.ProductoDao;
import plansegui.hibernate.entities.Producto;

@Service
public class ProductoserviceImpl implements ProductoService {
	
	@Autowired
	private ProductoDao productoDao;
	
	@Override
	@Transactional
	public List<Producto> getProducto() {
		
		return productoDao.getProducto();
	}

	@Override
	@Transactional
	public void guardarProducto(Producto producto) {
		
		productoDao.guardarPoducto(producto);

	}

}
