package pl.put.poznan.transformer.logic;

import org.springframework.util.StringUtils;
import pl.put.poznan.transformer.logic.TextTransformer;

public class CaseTextTransformer extends DecoratedTextTransformer {
  String state;

  /**
   * Initialize the decorator with the state of the transformer.
   *
   * @param textTransformer TextTransformer
   * @param state a string that represents whether the text should be uppercased or lowercased
   */
  public CaseTextTransformer(TextTransformer textTransformer, String state) {
    super(textTransformer);
    this.state = state;
  }
}
