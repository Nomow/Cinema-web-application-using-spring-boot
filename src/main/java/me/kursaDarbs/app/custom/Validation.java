package me.kursaDarbs.app.custom;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Pattern;

public class Validation {
    public Boolean HaveOnlyLetters(String data)
    {
        String s = data.replaceAll("\\s+","");
        System.out.println(s);
        return s.chars().allMatch(Character::isLetter);
    }

    public Boolean IsValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    public Boolean IsValidPhoneNumber(String phoneNumber) {

        String countrycode = "+371";

        if(phoneNumber.substring(0, Math.min(phoneNumber.length(), 4)).equals(countrycode)) {
            String number = phoneNumber.substring(4, phoneNumber.length());
            if(number.length() == 8 && number.matches("[0-9]+")) {
                return true;
            }

        }
        return false;
    }

    public boolean isValidURI(String uriStr) {
        try {
            URI uri = new URI(uriStr);
            return true;
        }
        catch (URISyntaxException e) {
            return false;
        }
    }

    public boolean isValidURI(String uriStr, String name) {
        return uriStr.toLowerCase().contains(name.toLowerCase());
    }




}
