package plansegui.hibernate.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import plansegui.hibernate.dao.EmpresaDao;
import plansegui.hibernate.entities.Empresa;

@Service
public class EmpresaServiceImpl implements EmpresaService {

	@Autowired
	private EmpresaDao empresaDao;
	
	@Override
	@Transactional
	public List<Empresa> getEmpresa() {
		
		return empresaDao.getEmpresa();
	}

	@Override
	@Transactional
	public void guardarEmpresa(Empresa empresa) {
	
		empresaDao.guardarEmpresa(empresa);

	}

}
