package pl.put.poznan.transformer.logic;

public class EscapeCharactersTextTransformer extends DecoratedTextTransformer{
    public EscapeCharactersTextTransformer(TextTransformer textTransformer) {
        super(textTransformer);
    }
}
