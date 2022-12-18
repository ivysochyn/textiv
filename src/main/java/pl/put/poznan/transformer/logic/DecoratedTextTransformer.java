package pl.put.poznan.transformer.logic;

public abstract class DecoratedTextTransformer extends TextTransformer {
  TextTransformer textTransformer;

  /**
   * DecoratedTextTransformer constructor with TextTransformer parameter.
   *
   * @param textTransformer TextTransformer object
   */
  public DecoratedTextTransformer(TextTransformer textTransformer) {

    super(textTransformer.getTransforms());
    this.textTransformer = textTransformer;
  }
}
