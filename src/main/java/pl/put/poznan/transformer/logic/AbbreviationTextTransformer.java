package pl.put.poznan.transformer.logic;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AbbreviationTextTransformer extends DecoratedTextTransformer {
  String state;
  private static final Logger logger = LoggerFactory.getLogger(AbbreviationTextTransformer.class);
  Map<String, String> dictionary;

  /**
   * Initialize AbbreviationTextTransformer and its dictionary.
   *
   * @param textTransformer A TextTransformer object to be decorated.
   * @param state A String object containing the state of the abbreviation.
   */
  public AbbreviationTextTransformer(ITextTransformer textTransformer, String state) {
    super(textTransformer);
    this.state = state;
    this.dictionary = new HashMap<String, String>();
    dictionary.put("ADVANCE", "ADV.");
    dictionary.put("AMOUNT", "AMT.");
    dictionary.put("AND SO ON", "ETC.");
    dictionary.put("ASSISTANT", "ASST.");
    dictionary.put("ASSOCIATE", "ASSOC.");
    dictionary.put("AVENUE", "AVE.");
    dictionary.put("Advance", "Adv.");
    dictionary.put("Amount", "Amt.");
    dictionary.put("And so on", "Etc.");
    dictionary.put("Assistant", "Asst.");
    dictionary.put("Associate", "Assoc.");
    dictionary.put("Avenue", "Ave.");
    dictionary.put("BOULEVARD", "BLVD.");
    dictionary.put("Boulevard", "Blvd.");
    dictionary.put("COMPANY", "CO.");
    dictionary.put("CORPORATION", "CORP.");
    dictionary.put("Company", "Co.");
    dictionary.put("Corporation", "Corp.");
    dictionary.put("DEPARTMENT", "DEPT.");
    dictionary.put("DEVELOPMENT", "DEV.");
    dictionary.put("DIVISION", "DIV.");
    dictionary.put("DOCTOR", "DR.");
    dictionary.put("Department", "Dept.");
    dictionary.put("Development", "Dev.");
    dictionary.put("Division", "Div.");
    dictionary.put("Doctor", "Dr.");
    dictionary.put("ESTATE", "EST.");
    dictionary.put("EXPRESSWAY", "EXPY.");
    dictionary.put("EXTENSION", "EXT.");
    dictionary.put("Estate", "Est.");
    dictionary.put("Expressway", "Expy.");
    dictionary.put("Extension", "Ext.");
    dictionary.put("FOR INSTANCE", "E.G.");
    dictionary.put("For example", "I.e");
    dictionary.put("For instance", "E.g.");
    dictionary.put("HIGHWAY", "HWY.");
    dictionary.put("Highway", "Hwy.");
    dictionary.put("INSTITUTE", "INST.");
    dictionary.put("Institute", "Inst.");
    dictionary.put("MR", "MR.");
    dictionary.put("MR.", "MR");
    dictionary.put("MRS", "MRS.");
    dictionary.put("MRS.", "MRS");
    dictionary.put("Mr", "Mr.");
    dictionary.put("Mr.", "Mr");
    dictionary.put("Mrs", "Mrs.");
    dictionary.put("Mrs.", "Mrs");
    dictionary.put("NUMBER", "NO.");
    dictionary.put("Number", "No.");
    dictionary.put("PROFFESOR", "PROF.");
    dictionary.put("Proffessor", "Prof.");
    dictionary.put("ROAD", "RD.");
    dictionary.put("Road", "Rd.");
    dictionary.put("STREET", "ST.");
    dictionary.put("Street", "St.");
    dictionary.put("advance", "adv.");
    dictionary.put("amount", "amt.");
    dictionary.put("and so on", "etc.");
    dictionary.put("assistant", "asst.");
    dictionary.put("associate", "assoc.");
    dictionary.put("avenue", "ave.");
    dictionary.put("boulevard", "blvd.");
    dictionary.put("company", "co.");
    dictionary.put("corporation", "corp.");
    dictionary.put("department", "dept.");
    dictionary.put("development", "dev.");
    dictionary.put("division", "div.");
    dictionary.put("doctor", "dr.");
    dictionary.put("estate", "est.");
    dictionary.put("expressway", "expy.");
    dictionary.put("extension", "ext.");
    dictionary.put("for example", "i.e");
    dictionary.put("for instance", "e.g.");
    dictionary.put("highway", "hwy.");
    dictionary.put("institute", "inst.");
    dictionary.put("number", "no.");
    dictionary.put("proffessor", "prof.");
    dictionary.put("road", "rd.");
    dictionary.put("street", "st.");
  }

  /**
   * Returns abbreviation of given word provided that the word is in dictionary.
   *
   * @param text A word to be abbreviated.
   * @return Abbreviation of given word.
   */
  public String collapse(String text) {
    logger.info("Input:{}", text);
    for (Map.Entry<String, String> entry : dictionary.entrySet()) {
      text = text.replaceAll(entry.getKey(), entry.getValue());
    }
    logger.info("Output:{}", text);

    return text;
  }

  /**
   * Returns full word of given abbreviation provided that the word is in dictionary.
   *
   * @param text A abbreviation to be expanded.
   * @return Full word of given abbreviation.
   */
  public String expand(String text) {
    logger.info("Input:{}", text);
    for (Map.Entry<String, String> entry : dictionary.entrySet()) {
      text = text.replaceAll(entry.getValue(), entry.getKey());
    }
    logger.info("Output:{}", text);
    return text;
  }

  @Override
  public String transform(String text) {
    if (text == null) {
      return null;
    }
    if (state.equals( "collapsing")) {
      return textTransformer.transform(collapse(text));
    } else {
      return textTransformer.transform(expand(text));
    }
  }
}
