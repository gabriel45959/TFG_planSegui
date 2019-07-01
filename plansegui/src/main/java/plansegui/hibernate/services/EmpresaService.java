package plansegui.hibernate.services;

import java.util.List;

import plansegui.hibernate.entities.Empresa;

public interface EmpresaService {
	public List<Empresa> getEmpresa();
	public void guardarEmpresa(Empresa empresa);
}
