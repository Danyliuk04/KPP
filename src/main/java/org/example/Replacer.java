package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Replacer {
    public static String replace(String input) {

        Pattern numbersPattern = Pattern.compile("\\b(\\d+)\\b.*?\\b(\\d+)\\b");
        Matcher numbersMatcher = numbersPattern.matcher(input);

        Pattern domainPattern = Pattern.compile("(http://|https://)([0-9]+)");
        Matcher domainMatcher = domainPattern.matcher(input);

        String domain = "";
        if (domainMatcher.find()) {
            return input;
        }

        String num1 = "";
        String num2 = "";
        if (numbersMatcher.find() && numbersMatcher.groupCount() > 1) {
            num1 = numbersMatcher.group(1);
            num2 = numbersMatcher.group(2);
        } else return input;


        // Replace the first number with the first site domain
        String[] tmp1 = input.split(num1);

        tmp1[0] = tmp1[0] + num2;
        tmp1[1] = tmp1[1].replaceFirst(num2, num1);
        String res = tmp1[0] + tmp1[1];
        return res;
    }
}