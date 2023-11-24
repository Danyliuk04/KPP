import org.example.Text;
import org.junit.Test;

import java.util.Random;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class Tests {

    @Test
    public void testTextWithoutMatchingWords() {
        String inputText = "123 456 @#$";
        String expectedOutput = "123 456 @#$";
        String actualOutput = Text.capitalizeLastLetterOfWords(inputText);
        assertEquals(expectedOutput, actualOutput);
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