package pl.put.poznan.transformer.logic;

public class CaseTextTransformer extends DecoratedTextTransformer {
    String state;
   
    @Override
   /**
      * <p>
      * This method exists to select the method used in the class based on the state variable .  
      * </p>
      *
      * @param text  String object to transform  
      * @return Converted text , when selecting the method
    */
    public String transform(String text) {
        switch (state) {
            case "upper":
                return UpperCaseTransform(textTransformer.transform(text));
            case "lower":
                return LowerCaseTransform(textTransformer.transform(text));
            case "capitalize":
                return CapitalizeCaseTransform(textTransformer.transform(text));
        }
        return text;
    }

    public CaseTextTransformer(TextTransformer textTransformer, String state) {
        super(textTransformer);
        this.state=state;
    }

    /**
     * <p>
     * This method changes all the letters in the text to uppercase  
     * </p>
     *
     * @param strInLowerCase  String object to change all letters to uppercase  
     * @return Text in which all letters are uppercase
     */
    public static String UpperCaseTransform(String strInLowerCase){
        return strInLowerCase.toUpperCase();
    }


      /**
      * <p>
      * This method changes all letters in the text to lowercase 
      * </p>
      *
      * @param strInUpperCase  String object to change all letters to lowercase
      * @return Text in which all letters are lowercase.
      */
    public static String LowerCaseTransform(String strInUpperCase){
        return  strInUpperCase.toLowerCase();
    }

     /**
       * <p>
       * This method changes first letter in each word to uppercase 
       * </p>
       *
       * @param strInCase  String object to change first letter in each word to uppercase 
       * @return Text in which first letter in each word to uppercase 
       */

    public static String CapitalizeCaseTransform(String strInCase){
        StringBuffer res = new StringBuffer();
        String[] subStr = strInCase.split(" ");
        for (String s : subStr) {
            String cap = s.substring(0, 1).toUpperCase();
            res.insert(res.length(), cap + s.substring(1) + " ");
        }
        String result = String.valueOf(res);
        return result;
    }
}
