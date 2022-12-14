package pl.put.poznan.transformer.logic;

public class EscapeCharactersTextTransformer extends DecoratedTextTransformer {
  public EscapeCharactersTextTransformer(TextTransformer textTransformer) {
    super(textTransformer);
  }

  @Override
  public String transform(String text) {
    return Latex(textTransformer.transform(text));
  }

  public static String Latex(String input) {
    String result = input.replaceAll("&", "/&");
    String FinalResult = result.replace("$", "/$");
    return FinalResult;
  }
}
