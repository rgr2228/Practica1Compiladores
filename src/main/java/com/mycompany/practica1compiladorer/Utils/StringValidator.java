package com.mycompany.practica1compiladorer.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringValidator {
    private Pattern patttern;

    public StringValidator(String regex) {
        String FormattedRegex = FormatRegex.formatRegEx(regex).replaceAll("\\.", "");
        this.patttern = Pattern.compile("^(" + FormattedRegex + ")$");
    }

    public boolean validateInput(String input) {
        Matcher matcher = patttern.matcher(input.replaceAll("\\.|\\s|\\t|\\n", ""));
        return matcher.find();
    }

}
