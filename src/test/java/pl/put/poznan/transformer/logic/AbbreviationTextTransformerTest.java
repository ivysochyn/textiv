package pl.put.poznan.transformer.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AbbreviationTextTransformerTest {
  private TextTransformer textTransformerCollapse;
  private TextTransformer textTransformerExpand;

  @BeforeEach
  public void createTransformer() {
    textTransformerCollapse = new TextTransformer(new String[] {"collapsing"});
    textTransformerExpand = new TextTransformer(new String[] {"expanding"});
    textTransformerCollapse = TextTransformerCreator.createTextTransformer(textTransformerCollapse);
    textTransformerExpand = TextTransformerCreator.createTextTransformer(textTransformerExpand);
  }

  @Test
  void testOneWordExpanding() {
    String input = "Dr John Smith";
    String expected = "Doctor John Smith";
    String actual = textTransformerExpand.transform(input);
    assertEquals(expected, actual);
  }

  @Test
  void testOneWordCollapsing() {
    String expected = "Dr John Smith";
    String input = "Doctor John Smith";
    String actual = textTransformerCollapse.transform(input);
    assertEquals(expected, actual);
  }

  @Test
  void multipleWordCollapsing() {

    String expected = "Dr John Smith is working on a new project concerning electronics etc.";
    String input = "Doctor John Smith is working on a new project concerning electronics and so on";
    String actual = textTransformerCollapse.transform(input);
    assertEquals(expected, actual);
  }

  @Test
  void multipleWordExpanding() {
    String input = "Dr John Smith is working on a new project concerning electronics etc.";
    String expected =
        "Doctor John Smith is working on a new project concerning electronics and so on";
    String actual = textTransformerExpand.transform(input);
    assertEquals(expected, actual);
  }
}
