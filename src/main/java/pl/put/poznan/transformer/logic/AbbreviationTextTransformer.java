package pl.put.poznan.transformer.logic;

public class AbbreviationTextTransformer extends DecoratedTextTransformer {
  String state;

  public AbbreviationTextTransformer(TextTransformer textTransformer, String state) {
    super(textTransformer);
    this.state = state;
  }

  @Override
  public String transform(String text) {
    // TODO:AbbreviationTextTransformer
    return textTransformer.transform(text);
  }
}
