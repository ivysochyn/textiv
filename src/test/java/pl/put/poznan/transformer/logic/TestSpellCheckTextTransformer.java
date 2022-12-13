package pl.put.poznan.transformer.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import pl.put.poznan.transformer.logic.SpellCheckTextTransformer;
import pl.put.poznan.transformer.logic.TextTransformer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestSpellCheckTextTransformer {
  private TextTransformer textTransformer;

  @BeforeEach
  public void createTransformer() {
    textTransformer = new TextTransformer(new String[] {"spellcheck"});
    textTransformer = TextTransformerCreator.createTextTransformer(textTransformer);
  }

  @Test
  public void testEmptyText() {
    String text = "";
    String expected = "";
    String actual = textTransformer.transform(text);
    assertEquals(expected, actual);
  }

  @Test
  public void testOneWord() {
    String text = "test";
    String expected = "test";
    String actual = textTransformer.transform(text);
    assertEquals(expected, actual);
  }

  @Test
  public void testOneWordWithMistake() {
    String text = "testt";
    String expected = "test";
    String actual = textTransformer.transform(text);
    assertEquals(expected, actual);
  }

  @Test
  public void testOneWordWithMistakeAndSpace() {
    String text = "Testt ";
    String expected = "Test ";
    String actual = textTransformer.transform(text);
    assertEquals(expected, actual);
  }

  @Test
  public void testTwoWordsWithMistake() {
    String text = "accept the test resultss";
    String expected = "accept the test results";
    String actual = textTransformer.transform(text);
    assertEquals(expected, actual);
  }

  @Test
  public void testMultipleWordsWithMistakes() {
    String text = "You can acceept the invitation or you can rejject it.";
    String expected = "You can accept the invitation or you can reject it.";
    String actual = textTransformer.transform(text);
    assertEquals(expected, actual);
  }
}
