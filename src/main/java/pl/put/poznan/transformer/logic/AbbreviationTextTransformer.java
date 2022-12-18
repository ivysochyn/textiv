package pl.put.poznan.transformer.logic;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AbbreviationTextTransformer extends DecoratedTextTransformer {
  String state;
  private static final Logger logger=LoggerFactory.getLogger(AbbreviationTextTransformer.class);
  Map<String,String> dictionary; 

  public AbbreviationTextTransformer(TextTransformer textTransformer, String state) {
    super(textTransformer);
    this.state = state;
    this.dictionary=new HashMap<String,String>();
    dictionary.put("Proffessor", "Prof.");
    dictionary.put("proffessor", "prof.");
    dictionary.put("PROFFESOR", "PROF.");

    dictionary.put("doctor", "dr");
    dictionary.put("Doctor","Dr");
    dictionary.put("DOCTOR","DR");
    dictionary.put("For example", "I.e");
    dictionary.put("for example", "i.e");
    dictionary.put("And so on", "Etc.");
    dictionary.put("AND SO ON", "ETC.");
    dictionary.put("and so on", "etc.");

    
  }
  
  public String collapse(String text){
    logger.info("Input:{}", text);;;
    for (Map.Entry<String,String> entry : dictionary.entrySet()) {
      text=text.replaceAll(entry.getKey(), entry.getValue());
      
    }
    logger.info("Output:{}",text);

    
    return text;
  }
  public String expand(String text){
    logger.info("Input:{}",text);
    for (Map.Entry<String,String> entry : dictionary.entrySet()) {
      text=text.replaceAll(entry.getValue(), entry.getKey());
    }
    logger.info("Output:{}",text);
    return text;
  }
  @Override
  public String transform(String text) {
    if(state=="collapsing"){
      return collapse(textTransformer.transform(text));
    }else{
      return expand(textTransformer.transform(text));
    }
  
  }
}
