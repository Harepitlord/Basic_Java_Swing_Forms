package SimpleStudentDatabase;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*public class AdditionalClasses {
}*/

// This class will help us to validate and store email address
class Email {
    private String email;

    // Constructor
    public Email(String s) throws InvalidEmailException {
        this.setEmail(s);
    }

    // This function checks whether the given string is a email or not
    public boolean isEmail(String a) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern p = Pattern.compile(emailRegex);
        Matcher m = p.matcher(a);
        return m.matches();
    }

    // Getter function
    public String getEmail() {
        return this.email;
    }

    // Setter Function
    public void setEmail(String s) throws InvalidEmailException {
        if (s == null || s.length()<6)
            throw new InvalidEmailException("Invalid Email");
        if(isEmail(s))
            this.email = s;
        else
            throw new InvalidEmailException("Invalid Email");
    }
}

// This class is used to format the date when it's been return from the datepicker widget
class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {

    private final String datePattern = "dd-MM-yyyy";
    private final SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

    @Override
    public Object stringToValue(String text) throws ParseException {
        return dateFormatter.parseObject(text);
    }

    @Override
    public String valueToString(Object value) throws ParseException {
        if (value != null) {
            Calendar cal = (Calendar) value;
            return dateFormatter.format(cal.getTime());
        }

        return "";
    }

}