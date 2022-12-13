package pl.put.poznan.transformer.logic;

public abstract class DecoratedTextTransformer extends TextTransformer {
  TextTransformer textTransformer;

  public DecoratedTextTransformer(TextTransformer textTransformer) {

    super(textTransformer.getTransforms());
    this.textTransformer = textTransformer;
  }
}
