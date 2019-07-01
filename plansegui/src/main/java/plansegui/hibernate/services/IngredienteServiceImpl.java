package plansegui.hibernate.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import plansegui.hibernate.dao.IngredienteDao;
import plansegui.hibernate.entities.Ingrediente;

@Service
public class IngredienteServiceImpl implements IngredienteService {

	@Autowired
	private IngredienteDao ingredienteDao;
	
	@Override
	@Transactional
	public List<Ingrediente> getIngrediente() {
		
		return ingredienteDao.getIngrediente();
	}

	@Override
	@Transactional
	public void guardarIngrediente(Ingrediente ingrediente) {
		ingredienteDao.guardarIngrediente(ingrediente);

	}

}
