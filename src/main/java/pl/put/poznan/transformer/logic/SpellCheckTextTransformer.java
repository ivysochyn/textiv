package pl.put.poznan.transformer.logic;

import java.util.List;
import org.languagetool.JLanguageTool;
import org.languagetool.language.BritishEnglish;
import org.languagetool.rules.RuleMatch;

public class SpellCheckTextTransformer extends DecoratedTextTransformer {
  public SpellCheckTextTransformer(TextTransformer textTransformer) {
    super(textTransformer);
  }

  /**
   * Function that checks spelling of the text and returns the text with the mistakes fixed.
   *
   * @param text Text to be spell checked and fixed.
   * @return Spell fixed text.
   */
  private String checkSpelling(String text) throws Exception {
    JLanguageTool langTool = new JLanguageTool(new BritishEnglish());
    List<RuleMatch> matches = langTool.check(text);

    int offset = 0;
    for (RuleMatch match : matches) {
      // if the match is a spelling or grammer mistake
      if (match.getRule().getId().equals("MORFOLOGIK_RULE_EN_GB")
          || match.getRule().getId().equals("ENGLISH_WORD_REPEAT_BEGINNING_RULE")) {
        if (match.getSuggestedReplacements().size() > 0) {
          String suggestion = match.getSuggestedReplacements().get(0);

          text =
              text.substring(0, match.getFromPos() + offset)
                  + suggestion
                  + text.substring(match.getToPos() + offset);
          offset += suggestion.length() - match.getToPos() + match.getFromPos();
        }
      }
    }
    return text;
  }

  @Override
  public String transform(String text) {
    try {
      text = checkSpelling(text);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return textTransformer.transform(text);
  }
}
