package pl.put.poznan.transformer.logic;

public class EscapeCharactersTextTransformer extends DecoratedTextTransformer {
  public EscapeCharactersTextTransformer(TextTransformer textTransformer) {
    super(textTransformer);
  }

  /**
   * This function passes the value of the changed text further along the layers
   *
   * @param text We pass to this parameter the text, where we want to change symbols to Latex form
   * @return Returns the changed text
   */
  @Override
  public String transform(String text) {
    return Latex(textTransformer.transform(text));
  }

  /**
   * This function is used to change special characters into a form that is understandable to the
   * Latex program
   *
   * @param input We pass to this parameter the text, in which we want to change special characters
   * @return Returns the text with changed special characters that is understandable to the Latex
   *     program
   */
  public static String Latex(String input) {
    input = input.replace("&", "\\&");
    input = input.replace("$", "\\$");
    return input;
  }
}
