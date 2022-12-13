package pl.put.poznan.transformer.logic;

public class CaseTextTransformer extends DecoratedTextTransformer {
    String state;
   
    @Override
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

    public static String UpperCaseTransform(String strInLowerCase){
        return strInLowerCase.toUpperCase();
    }

    public static String LowerCaseTransform(String strInUpperCase){
        return  strInUpperCase.toLowerCase();
    }

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
