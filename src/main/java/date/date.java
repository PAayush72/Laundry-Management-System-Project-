import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.Calendar;
import java.util.Date;

@FacesValidator("pastDateValidator")
public class date implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        // If the date is null, don't validate
        if (value == null) {
            return;
        }

        Date inputDate = (Date) value;

        // Create a Calendar instance to set today's date with time reset to midnight
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());  // set today's date
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        
        Date today = calendar.getTime();

        // Check if the input date is in the past
        if (inputDate.before(today)) {
            FacesMessage msg = new FacesMessage("Date cannot be in the past.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}
