package plansegui.validator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import plansegui.hibernate.entities.Pedido;

@Component
public class RegistrarPedidoValidator implements Validator {

	private Log log = LogFactory.getLog(RegistrarPedidoValidator.class);
	
	@Override
	public boolean supports(Class<?> arg0) {
		return Pedido.class.equals(arg0);
	}

	@Override
	public void validate(Object arg0, Errors err) {
		
		
		
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "empresa.id", "NotEmpty.formularioRegistrarPedido.empresa");
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "nroFactura", "NotEmpty.formularioRegistrarPedido.nroFactura");
		//ValidationUtils.rejectIfEmptyOrWhitespace(err, "fechaEntrega", "NotEmpty.formularioRegistrarPedido.fechaEntrega");
		
		Pedido ped = (Pedido) arg0;
		
		log.info(ped.getEmpresa()+"RegistrarPedidoValidator--------------------------------------------------------------------------validate");
		
		if(ped.getEmpresa() == null){
			err.rejectValue("empresa.id","NotEmpty.formularioRegistrarPedido.empresa");
		}
	}

}
