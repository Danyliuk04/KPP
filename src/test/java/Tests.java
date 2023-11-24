import org.example.Replacer;
import org.example.Text;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class Tests {

    @Test
    public void testTextWithoutMatchingWords() {
        String inputText = "123 456 @#$";
        String expectedOutput = "123 456 @#$";
        String actualOutput = Text.capitalizeLastLetterOfWords(inputText);
        assertEquals(expectedOutput, actualOutput);
    }


    @ParameterizedTest

    @NullSource
    @EmptySource
    void testShouldNotMatch(String input) {
        boolean match = MatcherPractice.match(input);
        assertFalse(match);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "http://mysite.com/orders/123/details/0000,http://mysite.com/orders/0000/details/123",
            "http://mysite.com/orders/12,http://mysite.com/orders/12",
            "https://mysite.com/orders/123/details/456/info/789,https://mysite.com/orders/456/details/123/info/789",
            "http://123.com/orders/456,http://123.com/orders/456",
            "http://mysite.com/orders/456123,http://mysite.com/orders/456123",
    }, delimiter = ',')
    void shouldMatch(String input, String expected) {
        String actual = Replacer.replace(input);
        assertEquals(expected, actual);
    }
    @Test
    public void testTextWithMatchingWords() {
        String inputText = "hello world";
        String expectedOutput = "hellO worlD";
        String actualOutput = Text.capitalizeLastLetterOfWords(inputText);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testTextWithMultipleMatchingWords() {
        String inputText = "In recent years, Java has seen significant updates, with the introduction of modules in Java 9 and other enhancements in subsequent versions. This keeps Java relevant and adaptable to the evolving needs of the tech industry.";
        String expectedOutput = "IN recenT yearS, JavA haS seeN significanT updateS, witH thE introductioN oF moduleS iN JavA 9 anD otheR enhancementS iN subsequenT versionS. ThiS keepS JavA relevanT anD adaptablE tO thE evolvinG needS oF thE tecH industrY.";
        String actualOutput = Text.capitalizeLastLetterOfWords(inputText);
        assertEquals(expectedOutput, actualOutput);
    }


}
