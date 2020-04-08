package com.mycompany.practica1compiladorer.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexValidator {
    private Pattern patttern;

    public RegexValidator(String regex) {
        this.patttern = Pattern.compile("^(" + regex.replaceAll("\\.", "") + ")$");
    }

    public boolean validateInput(String input) {
        Matcher matcher = patttern.matcher(input.replaceAll("\\.", ""));
        return matcher.find();
    }
}
