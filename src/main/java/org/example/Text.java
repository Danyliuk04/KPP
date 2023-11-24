package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Text {
    public static String capitalizeLastLetterOfWords(String inputText) {
        if (inputText.isEmpty()) {
            throw new IllegalArgumentException("The text you entered is empty.");
        }

        Pattern pattern = Pattern.compile("\\b(\\w*)(\\w)\\b");
        Matcher matcher = pattern.matcher(inputText);

        StringBuilder result = new StringBuilder();

        boolean found = false;
        while (matcher.find()) {
            found = true;
            String word = matcher.group(1);
            char lastLetter = Character.toUpperCase(matcher.group(2).charAt(0));
            matcher.appendReplacement(result, word + lastLetter);
        }

        if (!found) {
            throw new IllegalArgumentException("The text does not contain the required tokens to replace.");
        }

        matcher.appendTail(result);

        return result.toString();
    }

    public static boolean containsMatchingWords(String inputText) {
        Pattern pattern = Pattern.compile("\\b([a-zA-Z]*)([a-zA-Z])\\b");
        Matcher matcher = pattern.matcher(inputText);

        return matcher.find();
    }
}
