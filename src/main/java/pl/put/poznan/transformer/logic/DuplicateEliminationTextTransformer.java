package pl.put.poznan.transformer.logic;

public class DuplicateEliminationTextTransformer extends DecoratedTextTransformer {
    public DuplicateEliminationTextTransformer(TextTransformer textTransformer) {
        super(textTransformer);
    }
}
