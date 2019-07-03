package plansegui.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import plansegui.hibernate.entities.ProblemaReportado;

@Component
public class ProblemaReportadoValidator implements Validator{

	@Override
	public boolean supports(Class<?> arg0) {
		
		return ProblemaReportado.class.equals(arg0);
	}

	@Override
	public void validate(Object arg0, Errors err) {
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "tipoProblema", "NotEmpty.formularioProblemaReportado.tipoProblema");
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "observaciones", "NotEmpty.formularioProblemaReportado.observaciones");
		
		ProblemaReportado proRep = (ProblemaReportado) arg0;
		
		if(proRep.getFechaResolucion() == null){
			ValidationUtils.rejectIfEmptyOrWhitespace(err, "fechaResolucion", "NotEmpty.formularioProblemaReportado.fechaResolucion");
		}
		
		
	}

}
