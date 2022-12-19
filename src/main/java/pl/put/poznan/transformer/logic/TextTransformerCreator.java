package pl.put.poznan.transformer.logic;

import java.util.Arrays;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TextTransformerCreator {

  private static final Logger logger = LoggerFactory.getLogger(TextTransformerCreator.class);

  public TextTransformerCreator(String[] transforms) {}

  /**
   * Creates DecoratedTextTransformer containing Decorated objects of class TextTransformer of types
   * based on the contents of String[] transforms.
   *
   * <p>Possible tags: expanding, collapsing, capitalize, upper, lower, duplicate, escape,
   * spellcheck, inversion, num2word.
   *
   * @param textTransformer TextTransformer object to be decorated
   * @return TextTransformer
   */
  public static TextTransformer createTextTransformer(TextTransformer textTransformer) {
    String[] transforms = textTransformer.getTransforms();
    if (transforms.length > 0) {
      textTransformer.setTransforms(Arrays.copyOfRange(transforms, 0, transforms.length - 1));
      int len = transforms.length - 1;
      if (Objects.equals(transforms[len], "expanding")
          || Objects.equals(transforms[len], "collapsing")) {
        logger.debug("Creating ExpandingTextTransformer");
        textTransformer =
            createTextTransformer(
                new AbbreviationTextTransformer(textTransformer, transforms[len]));
      } else if (Objects.equals(transforms[len], "capitalize")
          || Objects.equals(transforms[len], "upper")
          || Objects.equals(transforms[len], "lower")) {
        logger.debug("Creating CapitalizeTextTransformer");
        textTransformer =
            createTextTransformer(new CaseTextTransformer(textTransformer, transforms[len]));
      } else if (Objects.equals(transforms[len], "duplicate")) {
        logger.debug("Creating DuplicateTextTransformer");
        textTransformer =
            createTextTransformer(new DuplicateEliminationTextTransformer(textTransformer));
      } else if (Objects.equals(transforms[len], "escape")) {
        logger.debug("Creating EscapeTextTransformer");
        textTransformer =
            createTextTransformer(new EscapeCharactersTextTransformer(textTransformer));

      } else if (Objects.equals(transforms[len], "spellcheck")) {
        logger.debug("Creating SpellcheckTextTransformer");
        textTransformer = createTextTransformer(new SpellCheckTextTransformer(textTransformer));
      } else if (Objects.equals(transforms[len], "inversion")) {
        logger.debug("Creating InversionTextTransformer");
        textTransformer = createTextTransformer(new InversionTextTransformer(textTransformer));
      } else if (Objects.equals(transforms[len], "num2word")) {
        logger.debug("Creating Num2WordTextTransformer");
        textTransformer = createTextTransformer(new NumberToWordTextTransformer(textTransformer));
      }
    }
    return textTransformer;
  }
}
