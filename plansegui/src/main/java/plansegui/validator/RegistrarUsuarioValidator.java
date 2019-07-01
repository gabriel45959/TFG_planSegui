package plansegui.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import plansegui.hibernate.entities.Usuario;

@Component
public class RegistrarUsuarioValidator implements Validator{

	@Override
	public boolean supports(Class<?> arg0) {
		
		return Usuario.class.equals(arg0);
	}

	@Override
	public void validate(Object arg0, Errors err) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "nombreUsuario", "NotEmpty.formularioCargarUsuario.usuario");
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "nombre", "NotEmpty.formularioCargarUsuario.nombre");
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "apellido", "NotEmpty.formularioCargarUsuario.apellido");
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "clave", "NotEmpty.formularioCargarUsuario.clave");
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "role[0].rol", "NotEmpty.formularioCargarUsuario.rol");
		
		Usuario usr = (Usuario) arg0;
		
		if(usr.getClave().length()<5 && usr.getClave().length()>1){
			err.rejectValue("clave", "NotEmpty.formularioCargarUsuario.largoClave");
		}
		
		
	}

}
