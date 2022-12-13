package pl.put.poznan.transformer.logic;


import org.junit.jupiter.api.Test;
import pl.put.poznan.transformer.logic.DuplicateEliminationTextTransformer;
import pl.put.poznan.transformer.logic.TextTransformer;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;


public class TestDuplicateEliminationTextTransformer {
    private TextTransformer textTransformer;

    @BeforeEach
    public void createTransformer() {
        textTransformer = new TextTransformer(new String[]{"duplicate"});
        textTransformer = TextTransformerCreator.createTextTransformer(textTransformer);
    }

    @Test
    public void testMultipleDuplicateEliminations() {
        String text = "duplicate duplicate duplicate duplicate duplicate";
        String expected = "duplicate";
        String actual = textTransformer.transform(text);
        assertEquals(expected, actual);
    }

    @Test
    public void testMultipleDifferentDuplicateEliminations() {
        String text = "duplicate duplicate duplicate different different different different different";
        String expected = "duplicate different";
        String actual = textTransformer.transform(text);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testMultipleDuplicateEliminationsWithSpaces() {
        String text = "duplicate   duplicate   duplicate       duplicate        duplicate";
        String expected = "duplicate";
        String actual = textTransformer.transform(text);
        assertEquals(expected, actual);
    }

    @Test
    public void testWhitespaceAtTheBeginning() {
        String text = "      duplicate duplicate";
        String expected = "duplicate";
        String actual = textTransformer.transform(text);
        assertEquals(expected, actual);
    }

    @Test
    public void testWhitespaceAtTheEnd() {
        String text = "duplicate duplicate       ";
        String expected = "duplicate";
        String actual = textTransformer.transform(text);
        assertEquals(expected, actual);
    }

    @Test
    public void testEmptyString() {
        String text = "";
        String expected = "";
        String actual = textTransformer.transform(text);
        assertEquals(expected, actual);
    }

    @Test
    public void testNoDuplicate() {
        String text = "duplicate";
        String expected = "duplicate";
        String actual = textTransformer.transform(text);
        assertEquals(expected, actual);
    }

    @Test
    public void testNoDuplicateMultipleWords() {
        String text = "duplicate word duplicate";
        String expected = "duplicate word duplicate";
        String actual = textTransformer.transform(text);
        assertEquals(expected, actual);
    }
}
