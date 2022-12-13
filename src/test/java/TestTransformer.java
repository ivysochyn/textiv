import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import pl.put.poznan.transformer.logic.TextTransformer;
import pl.put.poznan.transformer.logic.TextTransformerCreator;

public class TestTransformer {


    @Test
    void TestTextTransformerWrapping(){
        TextTransformer textTransformer;
        textTransformer=new TextTransformer(new String[]{"capitalize","collapsing","num2word"});

        TextTransformer tex= TextTransformerCreator.createTextTransformer(textTransformer);
        assertEquals(1, 2);
    }
}
