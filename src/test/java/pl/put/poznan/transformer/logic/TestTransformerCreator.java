package pl.put.poznan.transformer.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import pl.put.poznan.transformer.logic.TextTransformer;
import pl.put.poznan.transformer.logic.TextTransformerCreator;

public class TestTransformerCreator {

  @Test
  void testTextTransformerTransformFlowSingle() {
    TextTransformer mockTextTransformer = mock(TextTransformer.class);
    when(mockTextTransformer.getTransforms()).thenReturn(new String[] {"inversion"});
    doNothing().when(mockTextTransformer).setTransforms(new String[] {"inversion"});
    TextTransformer textTransformer =
        TextTransformerCreator.createTextTransformer(mockTextTransformer);
    verify(mockTextTransformer, times(2)).getTransforms();
    verify(mockTextTransformer, times(1)).setTransforms(new String[] {});
  }

  @Test
  void testTextTransformerTransformFlowTwo() {
    TextTransformer mockTextTransformer = mock(TextTransformer.class);
    when(mockTextTransformer.getTransforms()).thenReturn(new String[] {"inversion", "num2word"});
    doNothing().when(mockTextTransformer).setTransforms(any(String[].class));
    TextTransformer textTransformer =
        TextTransformerCreator.createTextTransformer(mockTextTransformer);
    verify(mockTextTransformer, times(2)).getTransforms();
    verify(mockTextTransformer, times(1)).setTransforms(new String[] {"inversion"});
  }

  @Test
  void testTextTransformerTransformFlowMultiple() {
    String[] transforms =
        new String[] {
          "inversion", "num2word", "upper", "lower", "inversion", "escape", "spellcheck"
        };
    String[] expected =
        new String[] {"inversion", "num2word", "upper", "lower", "inversion", "escape"};
    TextTransformer mockTextTransformer = mock(TextTransformer.class);
    when(mockTextTransformer.getTransforms()).thenReturn(transforms);
    doNothing().when(mockTextTransformer).setTransforms(any(String[].class));
    TextTransformer textTransformer =
        TextTransformerCreator.createTextTransformer(mockTextTransformer);
    verify(mockTextTransformer, times(2)).getTransforms();
    verify(mockTextTransformer, times(1)).setTransforms(expected);
  }

  @Test
  void testTextTransformerTransformFlowEmpty() {
    TextTransformer mockTextTransformer = mock(TextTransformer.class);
    when(mockTextTransformer.getTransforms()).thenReturn(new String[] {});
    TextTransformer textTransformer =
        TextTransformerCreator.createTextTransformer(mockTextTransformer);
    verify(mockTextTransformer, times(1)).getTransforms();
    verify(mockTextTransformer, never()).setTransforms(any(String[].class));
  }

  @Test
  void testTextTransformerTransformFlowDoesntExist() {
    TextTransformer mockTextTransformer = mock(TextTransformer.class);
    when(mockTextTransformer.getTransforms()).thenReturn(new String[] {"doesntExist"});
    TextTransformer textTransformer =
        TextTransformerCreator.createTextTransformer(mockTextTransformer);
    verify(mockTextTransformer, times(1)).getTransforms();
    verify(mockTextTransformer, times(1)).setTransforms(any(String[].class));
  }

  @Test
  void testTextTransformerTransformFlowFirstDoesntExist() {
    String[] transforms =
        new String[] {
          "inversion",
          "num2word",
          "upper",
          "lower",
          "inversion",
          "escape",
          "spellcheck",
          "doesntExist"
        };
    TextTransformer mockTextTransformer = mock(TextTransformer.class);
    when(mockTextTransformer.getTransforms()).thenReturn(transforms);
    TextTransformer textTransformer =
        TextTransformerCreator.createTextTransformer(mockTextTransformer);
    verify(mockTextTransformer, times(1)).getTransforms();
    verify(mockTextTransformer, times(1)).setTransforms(any(String[].class));
  }

  @Test
  void testTextTransformerTransformFlowDoesntExistOrder() {
    TextTransformer mockTextTransformer = mock(TextTransformer.class);
    when(mockTextTransformer.getTransforms()).thenReturn(new String[] {"doesntExist"});
    InOrder inOrder = inOrder(mockTextTransformer);
    TextTransformer textTransformer =
        TextTransformerCreator.createTextTransformer(mockTextTransformer);
    inOrder.verify(mockTextTransformer).getTransforms();
    inOrder.verify(mockTextTransformer).setTransforms(any(String[].class));
    inOrder.verifyNoMoreInteractions();
  }

  @Test
  void testTextTransformerTransformFlowEmptyOrder() {
    TextTransformer mockTextTransformer = mock(TextTransformer.class);
    when(mockTextTransformer.getTransforms()).thenReturn(new String[] {});
    InOrder inOrder = inOrder(mockTextTransformer);
    TextTransformer textTransformer =
        TextTransformerCreator.createTextTransformer(mockTextTransformer);
    inOrder.verify(mockTextTransformer).getTransforms();
    inOrder.verifyNoMoreInteractions();
  }

  @Test
  void testTextTransformerTransformFlowSingleOrder() {
    TextTransformer mockTextTransformer = mock(TextTransformer.class);
    when(mockTextTransformer.getTransforms()).thenReturn(new String[] {"inversion"});
    InOrder inOrder = inOrder(mockTextTransformer);
    TextTransformer textTransformer =
        TextTransformerCreator.createTextTransformer(mockTextTransformer);
    inOrder.verify(mockTextTransformer).getTransforms();
    inOrder.verify(mockTextTransformer).setTransforms(new String[] {});
    inOrder.verify(mockTextTransformer).getTransforms();
    inOrder.verifyNoMoreInteractions();
  }

  @Test
  void testTextTransformerTransformFlowTwoOrder() {
    TextTransformer mockTextTransformer = mock(TextTransformer.class);
    when(mockTextTransformer.getTransforms()).thenReturn(new String[] {"inversion", "num2word"});
    InOrder inOrder = inOrder(mockTextTransformer);
    TextTransformer textTransformer =
        TextTransformerCreator.createTextTransformer(mockTextTransformer);
    inOrder.verify(mockTextTransformer).getTransforms();
    inOrder.verify(mockTextTransformer).setTransforms(new String[] {"inversion"});
    inOrder.verify(mockTextTransformer).getTransforms();
    inOrder.verifyNoMoreInteractions();
  }

  @Test
  void testTextTransformerRegularFlow() {
    TextTransformer textTransformer = new TextTransformer(new String[] {"inversion", "num2word"});
    textTransformer = TextTransformerCreator.createTextTransformer(textTransformer);
    String actual = textTransformer.transform("Do you like 2 go to the 3 floor?");
    String expected = "?roolf three eht ot og two ekil uoy oD";
    assertEquals(expected, actual);
  }

  @Test
  void testTextTransformerRegularFlowEmpty() {
    TextTransformer textTransformer = new TextTransformer(new String[] {});
    textTransformer = TextTransformerCreator.createTextTransformer(textTransformer);
    String actual = textTransformer.transform("Do you like 2 go to the 3 floor?");
    String expected = "Do you like 2 go to the 3 floor?";
    assertEquals(expected, actual);
  }

  @Test
  void testTextTransformerRegularFlowSingle() {
    TextTransformer textTransformer = new TextTransformer(new String[] {"inversion"});
    textTransformer = TextTransformerCreator.createTextTransformer(textTransformer);
    String actual = textTransformer.transform("Do you like 2 go to the 3 floor?");
    String expected = "?roolf 3 eht ot og 2 ekil uoy oD";
    assertEquals(expected, actual);
  }

  @Test
  void testTextTransformerRegularFlowTwoDuplicate() {
    TextTransformer textTransformer = new TextTransformer(new String[] {"inversion", "inversion"});
    textTransformer = TextTransformerCreator.createTextTransformer(textTransformer);
    String actual = textTransformer.transform("Do you like 2 go to the 3 floor?");
    String expected = "Do you like 2 go to the 3 floor?";
    assertEquals(expected, actual);
  }

  @Test
  void testTextTransformerRegularMultiple() {
    TextTransformer textTransformer =
        new TextTransformer(
            new String[] {
              "spellcheck",
              "inversion",
              "num2word",
              "upper",
              "lower",
              "escape",
              "expanding",
              "collapsing"
            });
    textTransformer = TextTransformerCreator.createTextTransformer(textTransformer);
    String actual = textTransformer.transform("Do you like 2 go to the 3 floor?");
    String expected = "?roolf three eht ot og two ekil uoy od";
    assertEquals(expected, actual);
  }
}
