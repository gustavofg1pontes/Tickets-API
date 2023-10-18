package br.com.ifsp.tickets.api.domain.shared.utils;

public class ValidationUtils {

    public static boolean isValidDocument(String s){
        s.replaceAll("[^0-9]", "");
        return (s.length() >= 7 && s.length() <= 10) ||// rg
                (s.matches("^[a-zA-Z]{2}\\d{7}$") || // prontuario
                        s.matches("^[a-zA-Z]{2}\\d{6}[a-zA-Z]$")); // prontuario
    }

    public static boolean isValidMobilePhoneNumber(String s) {
        s = s.replaceAll("[^0-9]", "");
        return s.length() == 11;
    }

    public static boolean isValidPhoneNumber(String s) {
        s = s.replaceAll("[^0-9]", "");
        return s.length() == 10;
    }

    public static boolean isValidEmailAddress(String email) {
        return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    }
}
