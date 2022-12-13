package pl.put.poznan.transformer.logic;

public class InversionTextTransformer extends DecoratedTextTransformer {

  public InversionTextTransformer(TextTransformer textTransformer) {
    super(textTransformer);
  }

  public static String inversion(final String input) {
    final char[] charsInput = input.toCharArray();
    final char[] charsOutput = new char[charsInput.length];
    for (int i = 0; i < charsInput.length; i++) {
      final char c = charsInput[i];
      // final char output = Character.isUpperCase(c) ? Character.toLowerCase(c) :
      // Character.toUpperCase(c);
      charsOutput[charsInput.length - i - 1] = c;
    }
    return new String(charsOutput);
  }
}
