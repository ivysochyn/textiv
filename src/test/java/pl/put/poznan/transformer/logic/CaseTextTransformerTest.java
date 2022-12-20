package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pl.put.poznan.transformer.logic.NumberToWordTextTransformer;
import pl.put.poznan.transformer.logic.TextTransformer;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CaseTextTransformerTest {
  private ITextTransformer textCapitalizeTransformer;
  private ITextTransformer textLowerTransformer;
  private ITextTransformer textUpperTransformer;

  @BeforeEach
  public void createTransformerCapitalize() {
    textCapitalizeTransformer = new TextTransformer(new String[] {"capitalize"});
    textCapitalizeTransformer =
        TextTransformerCreator.createTextTransformer(textCapitalizeTransformer);
  }

  @BeforeEach
  public void createTransformerUpper() {
    textUpperTransformer = new TextTransformer(new String[] {"upper"});
    textUpperTransformer = TextTransformerCreator.createTextTransformer(textUpperTransformer);
  }

  @BeforeEach
  public void createTransformerLower() {
    textLowerTransformer = new TextTransformer(new String[] {"lower"});
    textLowerTransformer = TextTransformerCreator.createTextTransformer(textLowerTransformer);
  }

  @AfterEach
  public void deleteTransformerCapitalize() {
    textCapitalizeTransformer = null;
  }

  @AfterEach
  public void deleteTransformerUpper() {
    textUpperTransformer = null;
  }

  @AfterEach
  public void deleteTransformerLower() {
    textLowerTransformer = null;
  }

  @Test
  public void sentenceTransformationCapitalizeTest() {
    String text = "capitalize text";
    String expected = "Capitalize Text";
    String actual = textCapitalizeTransformer.transform(text);
    assertEquals(expected, actual);
  }

  @Test
  public void sentenceWithNumberTransformationCapitalizeTest() {
    String text = "deposit 100 zloty";
    String expected = "Deposit 100 Zloty";
    String actual = textCapitalizeTransformer.transform(text);
    assertEquals(expected, actual);
  }

  @Test
  public void emptyStringTransformationCapitalizeTest() {
    String text = "";
    String expected = "";
    String actual = textCapitalizeTransformer.transform(text);
    assertEquals(expected, actual);
  }

  @Test
  public void bigSentenceTransformationCapitalizeTest() {
    String text =
        "i am a student at the poznan university of technology, majoring in computer science";
    String expected =
        "I Am A Student At The Poznan University Of Technology, Majoring In Computer Science";
    String actual = textCapitalizeTransformer.transform(text);
    assertEquals(expected, actual);
  }

  @Test
  public void capitalizeTransformationTest() {
    String text = "Rent One Hundred Zloty";
    String expected = "Rent One Hundred Zloty";
    String actual = textCapitalizeTransformer.transform(text);
    assertEquals(expected, actual);
  }

  @Test
  public void sentenceTransformationUpperTest() {
    String text = "upper";
    String expected = "UPPER";
    String actual = textUpperTransformer.transform(text);
    assertEquals(expected, actual);
  }

  @Test
  public void sentenceWithNumberTransformationUpperTest() {
    String text = "100 upper words";
    String expected = "100 UPPER WORDS";
    String actual = textUpperTransformer.transform(text);
    assertEquals(expected, actual);
  }

  @Test
  public void emptyStringTransformationUpperTest() {
    String text = "";
    String expected = "";
    String actual = textUpperTransformer.transform(text);
    assertEquals(expected, actual);
  }

  @Test
  public void bigSentenceTransformationUpperTest() {
    String text =
        "i am a student at the poznan university of technology, majoring in computer science";
    String expected =
        "I AM A STUDENT AT THE POZNAN UNIVERSITY OF TECHNOLOGY, MAJORING IN COMPUTER SCIENCE";
    String actual = textUpperTransformer.transform(text);
    assertEquals(expected, actual);
  }

  @Test
  public void upperTransformationTest() {
    String text = "UPPER";
    String expected = "UPPER";
    String actual = textUpperTransformer.transform(text);
    assertEquals(expected, actual);
  }

  @Test
  public void sentenceTransformationLowerTest() {
    String text = "UPPER";
    String expected = "upper";
    String actual = textLowerTransformer.transform(text);
    assertEquals(expected, actual);
  }

  @Test
  public void sentenceWithNumberTransformationLowerTest() {
    String text = "100 UPPER WORDS";
    String expected = "100 upper words";
    String actual = textLowerTransformer.transform(text);
    assertEquals(expected, actual);
  }

  @Test
  public void emptyStringTransformationLowerTest() {
    String text = "";
    String expected = "";
    String actual = textLowerTransformer.transform(text);
    assertEquals(expected, actual);
  }

  @Test
  public void bigSentenceTransformationLowerTest() {
    String text =
        "I AM A STUDENT AT THE POZNAN UNIVERSITY OF TECHNOLOGY, MAJORING IN COMPUTER SCIENCE";
    String expected =
        "i am a student at the poznan university of technology, majoring in computer science";
    String actual = textLowerTransformer.transform(text);
    assertEquals(expected, actual);
  }

  @Test
  public void lowerTransformationTest() {
    String text = "lower";
    String expected = "lower";
    String actual = textLowerTransformer.transform(text);
    assertEquals(expected, actual);
  }
}
