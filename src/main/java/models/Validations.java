package models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validations {

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    public static boolean validarEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean checkNull(String str) {
        return str != null && !str.isEmpty();
    }
}
