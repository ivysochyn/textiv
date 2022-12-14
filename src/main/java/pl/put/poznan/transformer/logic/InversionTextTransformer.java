package pl.put.poznan.transformer.logic;

public class InversionTextTransformer extends DecoratedTextTransformer {

  public InversionTextTransformer(TextTransformer textTransformer) {
    super(textTransformer);
  }

  /**
   * This function passes the value of the inverted text further along the layers
   *
   * @param text We pass to this parameter the text that we want to invert
   * @return Returns the inverted text
   */

  @Override
  public String transform(String text) {
    return inversion(textTransformer.transform(text));
  }

  /**
   * This function is used to invert the text while preserving the case
   * @param input We pass to this parameter the text that we want to invert
   * @return Returns the inverted text
   */

  public static String inversion(final String input) {
    final char[] charsInput = input.toCharArray();
    final char[] charsOutput = new char[charsInput.length];
    for (int i = 0; i < charsInput.length; i++) {
      final char c = charsInput[i];
      charsOutput[charsInput.length - i - 1] = c;
    }
    return new String(charsOutput);
  }
}
