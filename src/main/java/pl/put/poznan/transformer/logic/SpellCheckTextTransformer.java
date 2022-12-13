package pl.put.poznan.transformer.logic;

public class SpellCheckTextTransformer extends DecoratedTextTransformer {
  public SpellCheckTextTransformer(TextTransformer textTransformer) {
    super(textTransformer);
  }
}
