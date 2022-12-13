package pl.put.poznan.transformer.logic;

public class DuplicateEliminationTextTransformer extends DecoratedTextTransformer {
  public DuplicateEliminationTextTransformer(TextTransformer textTransformer) {
    super(textTransformer);
  }

  /**
   * This method removes next to each other duplicate words from text.
   *
   * @param text A String object to remove duplications from.
   * @return Text without duplicate words next to each other.
   */
  @Override
  public String transform(String text) {
    String[] words = text.split("\\s+");
    String result = "";

    for (int i = 0; i < words.length; i++) {
      if (i == 0 || !words[i].equals(words[i - 1])) {
        result += words[i] + " ";
      }
    }
    result = result.trim();
    return textTransformer.transform(result);
  }
}
