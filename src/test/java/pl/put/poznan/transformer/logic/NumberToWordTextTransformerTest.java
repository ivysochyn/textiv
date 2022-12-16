package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pl.put.poznan.transformer.logic.NumberToWordTextTransformer;
import pl.put.poznan.transformer.logic.TextTransformer;

import static org.junit.jupiter.api.Assertions.*;

class NumberToWordTextTransformerTest {

    private TextTransformer textTransformer;

    @BeforeEach
    public void createTransformer() {
        textTransformer = new TextTransformer(new String[] {"num2word"});
        textTransformer = TextTransformerCreator.createTextTransformer(textTransformer);
    }

    @Test
    public void SingleIntNumberTransformationTest() {
        String text = "deposit 100 zloty";
        String expected = "deposit one hundred zloty";
        String actual = textTransformer.transform(text);
        assertEquals(expected, actual);
    }
    @Test
    public void MultipleIntNumberTransformationTest() {
        String text = "deposit 100 zloty and 50 groschen";
        String expected = "deposit one hundred zloty and fifty groschen";
        String actual = textTransformer.transform(text);
        assertEquals(expected, actual);
    }

    @Test
    public void TransformationWithoutNumbersTest() {
        String text = "deposit one zloty ";
        String expected = "deposit one zloty ";
        String actual = textTransformer.transform(text);
        assertEquals(expected, actual);
    }

    @Test
    public void TransformationEmptyStringTest() {
        String text = "";
        String expected = "";
        String actual = textTransformer.transform(text);
        assertEquals(expected, actual);
    }

    @Test
    public void MultipleFloatNumberTransformationTest() {
        String text = "deposit 100.55 zloty and rent 123.22 zloty";
        String expected = "deposit one hundred and fifty-five hundredth zloty and rent one hundred twenty-three and twenty-two hundredth zloty";
        String actual = textTransformer.transform(text);
        assertEquals(expected, actual);
    }

    @Test
    public void TransformationOnlyNumberTest() {
        String text = "1 10 11 12 129 100 999.99";
        String expected = "one ten eleven twelve one hundred twenty-nine one hundred nine hundred ninety-nine and ninety-nine hundredth";
        String actual = textTransformer.transform(text);
        assertEquals(expected, actual);
    }


    @Test
    public void TransformationWithDuplicateNumberTest() {
        String text = "1 and 1 and 1 and 1 or 10 and 10 and 10 and 10 or 11 and 11 and 11 or 111";
        String expected = "one and one and one and one or ten and ten and ten and ten or eleven and eleven and eleven or one hundred eleven";

        String actual = textTransformer.transform(text);
        assertEquals(expected, actual);
    }
}
