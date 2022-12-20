package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pl.put.poznan.transformer.logic.NumberToWordTextTransformer;
import pl.put.poznan.transformer.logic.TextTransformer;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NumberToWordTextTransformerTest {

  private ITextTransformer textTransformer;

  @BeforeEach
  public void createTransformer() {
    textTransformer = new TextTransformer(new String[] {"num2word"});
    textTransformer = TextTransformerCreator.createTextTransformer(textTransformer);
  }

  @Test
  public void singleIntNumberTransformationTest() {
    String text = "deposit 100 zloty";
    String expected = "deposit one hundred zloty";
    String actual = textTransformer.transform(text);
    assertEquals(expected, actual);
  }

  @Test
  public void multipleIntNumberTransformationTest() {
    String text = "deposit 100 zloty and 50 groschen";
    String expected = "deposit one hundred zloty and fifty groschen";
    String actual = textTransformer.transform(text);
    assertEquals(expected, actual);
  }

  @Test
  public void transformationWithoutNumbersTest() {
    String text = "deposit one zloty ";
    String expected = "deposit one zloty ";
    String actual = textTransformer.transform(text);
    assertEquals(expected, actual);
  }

  @Test
  public void transformationEmptyStringTest() {
    String text = "";
    String expected = "";
    String actual = textTransformer.transform(text);
    assertEquals(expected, actual);
  }

  @Test
  public void multipleFloatNumberTransformationTest() {
    String text = "deposit 100.55 zloty and rent 123.22 zloty";
    String first = "deposit one hundred and fifty-five hundredth zloty and rent one";
    String second = " hundred twenty-three and twenty-two hundredth zloty";
    String expected = first + second;
    String actual = textTransformer.transform(text);
    assertEquals(expected, actual);
  }

  @Test
  public void transformationOnlyNumberTest() {
    String text = "1 10 11 12 129 100 999.99";
    String first = "one ten eleven twelve one hundred twenty-nine one ";
    String second = "hundred nine hundred ninety-nine and ninety-nine hundredth";
    String expected = first + second;
    String actual = textTransformer.transform(text);
    assertEquals(expected, actual);
  }

  @Test
  public void transformationWithDuplicateNumberTest() {
    String text = "1 and 1 and 1 and 1 or 10 and 10 and 10 and 10 or 11 and 11 and 11 or 111";
    String first = "one and one and one and one or ten and ten and ten and ";
    String second = "ten or eleven and eleven and eleven or one hundred eleven";
    String expected = first + second;
    String actual = textTransformer.transform(text);
    assertEquals(expected, actual);
  }
}
