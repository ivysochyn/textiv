import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.put.poznan.transformer.logic.TextTransformer;
import pl.put.poznan.transformer.logic.TextTransformerCreator;

public class testTestTransformer {


    @Test
    void testTextTransformerWrapping(){
        TextTransformer textTransformer;
        textTransformer=new TextTransformer(new String[]{"capitalize","collapsing","num2word"});

        TextTransformer tex= TextTransformerCreator.createTextTransformer(textTransformer);
        System.out.println( tex.transform("S"));
    }
}
