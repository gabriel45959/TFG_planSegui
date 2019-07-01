package plansegui.hibernate.dao;

import java.util.List;

import plansegui.hibernate.entities.Empresa;

public interface EmpresaDao {
	public List<Empresa> getEmpresa();
	public void guardarEmpresa(Empresa empresa);
}
