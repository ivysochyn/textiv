package pl.put.poznan.transformer.logic;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InversionTextTransformerTest {
    private TextTransformer textTransformer;

    @BeforeEach
    public void createTransformer() {
        textTransformer = new TextTransformer(new String[] {"inversion"});
        textTransformer = TextTransformerCreator.createTextTransformer(textTransformer);
    }

    @Test
    public void testTextSingleWordInversion() {
        String text = "Hello";
        String expected = "olleH";
        String actual = textTransformer.transform(text);
        assertEquals(expected, actual);
    }

    @Test
    public void testTextTwoWordsInversion() {
        String text = "Hello World";
        String expected = "dlroW olleH";
        String actual = textTransformer.transform(text);
        assertEquals(expected, actual);
    }

    @Test
    public void testTextNumbersInversion() {
        String text = "123";
        String expected = "321";
        String actual = textTransformer.transform(text);
        assertEquals(expected, actual);
    }

    @Test
    public void testTextCharactersInversion() {
        String text = "$_$;";
        String expected = ";$_$";
        String actual = textTransformer.transform(text);
        assertEquals(expected, actual);
    }

}