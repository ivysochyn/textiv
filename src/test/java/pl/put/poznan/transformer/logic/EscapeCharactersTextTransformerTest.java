package pl.put.poznan.transformer.logic;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EscapeCharactersTextTransformerTest {
  private TextTransformer textTransformer;

  @BeforeEach
  public void createTransformer() {
    textTransformer = new TextTransformer(new String[] {"escape"});
    textTransformer = TextTransformerCreator.createTextTransformer(textTransformer);
  }

  @Test
  public void testTextAndToLatex() {
    String text = "John Smith & Sons";
    String expected = "John Smith \\& Sons";
    String actual = textTransformer.transform(text);
    assertEquals(expected, actual);
  }

  @Test
  public void testTextDollarToLatex() {
    String text = "John Smith $ Sons";
    String expected = "John Smith \\$ Sons";
    String actual = textTransformer.transform(text);
    assertEquals(expected, actual);
  }
}
