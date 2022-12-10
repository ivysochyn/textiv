package pl.put.poznan.transformer.logic;

import org.springframework.util.StringUtils;
import pl.put.poznan.transformer.logic.TextTransformer;

public class CaseTextTransformer extends DecoratedTextTransformer {
    String state;
    public CaseTextTransformer(TextTransformer textTransformer,String state) {

        super(textTransformer);
        this.state=state;
    }





}
