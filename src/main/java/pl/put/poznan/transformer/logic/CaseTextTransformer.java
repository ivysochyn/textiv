package pl.put.poznan.transformer.logic;

import java.util.StringJoiner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CaseTextTransformer extends DecoratedTextTransformer {
  String state;
  private static final Logger logger = LoggerFactory.getLogger(CaseTextTransformer.class);

  @Override
  /**
   * This method exists to select the method used in the class based on the state variable.
   *
   * @param text String object to transform
   * @return Converted text , when selecting the method
   */
  public String transform(String text) {
    String newText;
    logger.info(text);
    switch (state) {
      case "upper":
        newText = upperCaseTransform(textTransformer.transform(text));
        break;
      case "lower":
        newText = lowerCaseTransform(textTransformer.transform(text));
        break;
      case "capitalize":
        newText = capitalizeCaseTransform(textTransformer.transform(text));
        break;
      default:
        newText = text;
        break;
    }
    logger.debug(textTransformer.transform(newText));
    return textTransformer.transform(newText);
  }

  public CaseTextTransformer(TextTransformer textTransformer, String state) {
    super(textTransformer);
    this.state = state;
  }

  /**
   * This method changes all the letters in the text to uppercase.
   *
   * @param strInLowerCase String object to change all letters to uppercase
   * @return Text in which all letters are uppercase
   */
  public static String upperCaseTransform(String strInLowerCase) {
    return strInLowerCase.toUpperCase();
  }

  /**
   * This method changes all letters in the text to lowercase.
   *
   * @param strInUpperCase String object to change all letters to lowercase
   * @return Text in which all letters are lowercase.
   */
  public static String lowerCaseTransform(String strInUpperCase) {
    return strInUpperCase.toLowerCase();
  }

  /**
   * This method changes first letter in each word to uppercase.
   *
   * @param strInCase String object to change first letter in each word to uppercase
   * @return Text in which first letter in each word to uppercase
   */
  public static String capitalizeCaseTransform(String strInCase) {

    if (strInCase.length() == 0) {
      return strInCase;
    }
    StringJoiner result = new StringJoiner(" ");
    String[] subStr = strInCase.split(" ");
    for (String s : subStr) {
      String cap = s.substring(0, 1).toUpperCase();
      result.add(cap + s.substring(1));
    }
    return String.valueOf(result);
  }
}
