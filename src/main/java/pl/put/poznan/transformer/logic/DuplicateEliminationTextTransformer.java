package pl.put.poznan.transformer.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DuplicateEliminationTextTransformer extends DecoratedTextTransformer {
  public DuplicateEliminationTextTransformer(TextTransformer textTransformer) {
    super(textTransformer);
  }

  private static final Logger logger =
      LoggerFactory.getLogger(DuplicateEliminationTextTransformer.class);

  /**
   * This method removes next to each other duplicate words from text.
   *
   * @param text A String object to remove duplications from.
   * @return Text without duplicate words next to each other.
   */
  @Override
  public String transform(String text) {
    logger.info(text);
    String[] words = text.split("\\s+");
    String result = "";

    for (int i = 0; i < words.length; i++) {
      if (i == 0 || !words[i].equals(words[i - 1])) {
        result += words[i] + " ";
      }
    }
    result = result.trim();
    logger.debug(result);
    return textTransformer.transform(result);
  }
}
