package pl.put.poznan.transformer.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.put.poznan.transformer.rest.TextTransformerController;

public class InversionTextTransformer extends DecoratedTextTransformer {

  private static final Logger logger = LoggerFactory.getLogger(InversionTextTransformer.class);

  public InversionTextTransformer(ITextTransformer textTransformer) {
    super(textTransformer);
  }

  /**
   * This function passes the value of the inverted text further along the layers.
   *
   * @param text We pass to this parameter the text that we want to invert
   * @return Returns the inverted text
   */
  @Override
  public String transform(String text) {
    return textTransformer.transform(inversion(text));
  }

  /**
   * This function is used to invert the text while preserving the case.
   *
   * @param input We pass to this parameter the text that we want to invert
   * @return Returns the inverted text
   */
  public static String inversion(final String input) {
    logger.info(input);
    final char[] charsInput = input.toCharArray();
    final char[] charsOutput = new char[charsInput.length];
    for (int i = 0; i < charsInput.length; i++) {
      final char c = charsInput[i];
      charsOutput[charsInput.length - i - 1] = c;
    }
    logger.debug(new String(charsOutput));
    return new String(charsOutput);
  }
}
