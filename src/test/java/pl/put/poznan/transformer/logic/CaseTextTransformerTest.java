package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pl.put.poznan.transformer.logic.NumberToWordTextTransformer;
import pl.put.poznan.transformer.logic.TextTransformer;

import static org.junit.jupiter.api.Assertions.*;

class CaseTextTransformerTest {
  private TextTransformer textCapitalizeTransformer;

  @BeforeEach
  public void createTransformerCapitalize() {
    textCapitalizeTransformer = new TextTransformer(new String[] {"capitalize"});
    textCapitalizeTransformer =
        TextTransformerCreator.createTextTransformer(textCapitalizeTransformer);
  }

  @Test
  public void SentenceTransformationCapitalizeTest() {
    String text = "capitalize text";
    String expected = "Capitalize Text";
    String actual = textCapitalizeTransformer.transform(text);
    assertEquals(expected, actual);
  }

  @Test
  public void SentenceWithNumberTransformationCapitalizeTest() {
    String text = "deposit 100 zloty";
    String expected = "Deposit 100 Zloty";
    String actual = textCapitalizeTransformer.transform(text);
    assertEquals(expected, actual);
  }

  @Test
  public void EmptyStringTransformationCapitalizeTest() {
    String text = "";
    String expected = "";
    String actual = textCapitalizeTransformer.transform(text);
    assertEquals(expected, actual);
  }

  @Test
  public void BigSentenceTransformationCapitalizeTest() {
    String text =
        "i am a student at the poznan university of technology, majoring in computer science";
    String expected =
        "I Am A Student At The Poznan University Of Technology, Majoring In Computer Science";
    String actual = textCapitalizeTransformer.transform(text);
    assertEquals(expected, actual);
  }

  @Test
  public void CapitalizeTransformationTest() {
    String text = "Rent One Hundred Zloty";
    String expected = "Rent One Hundred Zloty";
    String actual = textCapitalizeTransformer.transform(text);
    assertEquals(expected, actual);
  }

  @AfterEach
  public void deleteTransformerCapitalize() {
    textCapitalizeTransformer = null;
  }

  private TextTransformer textUpperTransformer;

  @BeforeEach
  public void createTransformerUpper() {
    textUpperTransformer = new TextTransformer(new String[] {"upper"});
    textUpperTransformer = TextTransformerCreator.createTextTransformer(textUpperTransformer);
  }

  @Test
  public void SentenceTransformationUpperTest() {
    String text = "upper";
    String expected = "UPPER";
    String actual = textUpperTransformer.transform(text);
    assertEquals(expected, actual);
  }

  @Test
  public void SentenceWithNumberTransformationUpperTest() {
    String text = "100 upper words";
    String expected = "100 UPPER WORDS";
    String actual = textUpperTransformer.transform(text);
    assertEquals(expected, actual);
  }

  @Test
  public void EmptyStringTransformationUpperTest() {
    String text = "";
    String expected = "";
    String actual = textUpperTransformer.transform(text);
    assertEquals(expected, actual);
  }

  @Test
  public void BigSentenceTransformationUpperTest() {
    String text =
        "i am a student at the poznan university of technology, majoring in computer science";
    String expected =
        "I AM A STUDENT AT THE POZNAN UNIVERSITY OF TECHNOLOGY, MAJORING IN COMPUTER SCIENCE";
    String actual = textUpperTransformer.transform(text);
    assertEquals(expected, actual);
  }

  @Test
  public void UpperTransformationTest() {
    String text = "UPPER";
    String expected = "UPPER";
    String actual = textUpperTransformer.transform(text);
    assertEquals(expected, actual);
  }

  @AfterEach
  public void deleteTransformerUpper() {
    textUpperTransformer = null;
  }

  private TextTransformer textLowerTransformer;

  @BeforeEach
  public void createTransformerLower() {
    textLowerTransformer = new TextTransformer(new String[] {"lower"});
    textLowerTransformer = TextTransformerCreator.createTextTransformer(textLowerTransformer);
  }

  @Test
  public void SentenceTransformationLowerTest() {
    String text = "UPPER";
    String expected = "upper";
    String actual = textLowerTransformer.transform(text);
    assertEquals(expected, actual);
  }

  @Test
  public void SentenceWithNumberTransformationLowerTest() {
    String text = "100 UPPER WORDS";
    String expected = "100 upper words";
    String actual = textLowerTransformer.transform(text);
    assertEquals(expected, actual);
  }

  @Test
  public void EmptyStringTransformationLowerTest() {
    String text = "";
    String expected = "";
    String actual = textLowerTransformer.transform(text);
    assertEquals(expected, actual);
  }

  @Test
  public void BigSentenceTransformationLowerTest() {
    String text =
        "I AM A STUDENT AT THE POZNAN UNIVERSITY OF TECHNOLOGY, MAJORING IN COMPUTER SCIENCE";
    String expected =
        "i am a student at the poznan university of technology, majoring in computer science";
    String actual = textLowerTransformer.transform(text);
    assertEquals(expected, actual);
  }

  @Test
  public void LowerTransformationTest() {
    String text = "lower";
    String expected = "lower";
    String actual = textLowerTransformer.transform(text);
    assertEquals(expected, actual);
  }

  @AfterEach
  public void deleteTransformerLower() {
    textLowerTransformer = null;
  }
}
