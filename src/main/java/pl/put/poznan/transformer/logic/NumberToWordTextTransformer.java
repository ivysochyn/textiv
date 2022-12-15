package pl.put.poznan.transformer.logic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberToWordTextTransformer extends DecoratedTextTransformer{
    @Override
    public String transform(String text) {
        return NumberInStringFormatToText(text);
        }


    public NumberToWordTextTransformer(TextTransformer textTransformer) {
        super(textTransformer);
    }

    static public class ScaleUnit
    {
        private final int exponent;
        private final String[] names;
        private ScaleUnit(int exponent, String...names)
        {
            this.exponent = exponent;
            this.names = names;
        }
        public int getExponent()
        {
            return exponent;
        }
        public String getName(int index)
        {
            return names[index];
        }
    }
    static private final ScaleUnit[] SCALE_UNITS = new ScaleUnit[]
            {
                    new ScaleUnit(2, "hundred", "hundred"),
                    new ScaleUnit(-1, "tenth", "tenth"),
                    new ScaleUnit(-2, "hundredth", "hundredth"),
            };
    public enum Scale
    {
        SHORT;
        public String getName(int exponent)
        {
            for (ScaleUnit unit : SCALE_UNITS)
            {
                if (unit.getExponent() == exponent)
                {
                    return unit.getName(this.ordinal());
                }
            }
            return "";
        }
    }
    static public Scale SCALE = Scale.SHORT;
   
    static abstract public class AbstractProcessor
    {
        static protected final String SEPARATOR = " ";
        static protected final int NO_VALUE = -1;

        public String getName(long value)
        {
            return getName(Long.toString(value));
        }

        abstract public String getName(String value);
    }
    static public class UnitProcessor extends AbstractProcessor
    {
        static private final String[] TOKENS = new String[]
                {
                        "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"
                };
        @Override
        public String getName(String value)
        {
            StringBuilder buffer = new StringBuilder();
            int offset = NO_VALUE;
            int number;
            if (value.length() > 3)
            {
                number = Integer.valueOf(value.substring(value.length() - 3), 10);
            }
            else
            {
                number = Integer.valueOf(value, 10);
            }
            number %= 100;
            if (number < 10)
            {
                offset = (number % 10) - 1;
            //number /= 10;
            }
            else if (number < 20)
            {
                offset = (number % 20) - 1;
            //number /= 100;
            }
            if (offset != NO_VALUE )
            {
                buffer.append(TOKENS[offset]);
            }
            return buffer.toString();
        }
    }
    static public class TensProcessor extends AbstractProcessor
    {
        static private final String[] TOKENS = new String[]
                {
                        "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"
                };
        static private final String UNION_SEPARATOR = "-";
        private final UnitProcessor unitProcessor = new UnitProcessor();
        @Override
        public String getName(String value)
        {
            StringBuilder buffer = new StringBuilder();
            boolean tensFound = false;
            int number;
            if (value.length() > 3)
            {
                number = Integer.valueOf(value.substring(value.length() - 3), 10);
            }
            else
            {
                number = Integer.valueOf(value, 10);
            }
            number %= 100;   // keep only two digits
            if (number >= 20)
            {
                buffer.append(TOKENS[(number / 10) - 2]);
                number %= 10;
                tensFound = true;
            }
            else
            {
                number %= 20;
            }
            if (number != 0)
            {
                if (tensFound)
                {
                    buffer.append(UNION_SEPARATOR);
                }
                buffer.append(unitProcessor.getName(number));
            }
            return buffer.toString();
        }
    }
    static public class HundredProcessor extends AbstractProcessor
    {
        private final UnitProcessor unitProcessor = new UnitProcessor();
        private final TensProcessor tensProcessor = new TensProcessor();
        @Override
        public String getName(String value)
        {
            StringBuilder buffer = new StringBuilder();
            int number;
            if (value.isEmpty())
            {
                number = 0;
            }
            else if (value.length() > 4)
            {
                number = Integer.valueOf(value.substring(value.length() - 4), 10);
            }
            else
            {
                number = Integer.valueOf(value, 10);
            }
            number %= 1000;  // keep at least three digits
            if (number >= 100)
            {
                buffer.append(unitProcessor.getName(number / 100));
                buffer.append(SEPARATOR);
                int EXPONENT = 2;
                buffer.append(SCALE.getName(EXPONENT));
            }
            String tensName = tensProcessor.getName(number % 100);
            if (!tensName.isEmpty() && (number >= 100))
            {
                buffer.append(SEPARATOR);
            }
            buffer.append(tensName);
            return buffer.toString();
        }
    }

    /**
     * <p>
     * This class includes methods of the classes above that deal with individual cases in number substitution for a word. 
     * </p>
     */
    static public class DefaultProcessor extends AbstractProcessor
    {
        static private final String MINUS = "minus";
        static private final String UNION_AND = "and";
        static private final String ZERO_TOKEN = "zero";
        private final AbstractProcessor processor = new HundredProcessor();
      /**
      * <p>
      * This method that returns a number in word format.
      * </p>
      *
      * @param value Number in string format
      * @return Number in words 
      */

        @Override
        public String getName(String value)
        {
            if (value.contains(".")){
                if(value.contains("-")){
                    if (value.length() > 7) {
                        return "This number is outside the range -999.99 to 999.99";}
                }
                else{
                    if (value.length() > 6) {
                        return "This number is outside the range -999.99 to 999.99";}
                }
            }
            else {
                if (value.contains("-")) {
                    if (value.length() > 4) {
                        return "This number is outside the range -999 to 999";
                    }
                }else {
                    if (value.length() > 3) {
                        return "This number is outside the range -999 to 999";
                    }
                }
            }
            boolean negative = false;
            if (value.startsWith("-"))
            {
                negative = true;
                value = value.substring(1);
            }
            int decimals = value.indexOf(".");
            String decimalValue = null;
            if (0 <= decimals)
            {
                decimalValue = value.substring(decimals + 1);
                value = value.substring(0, decimals);
            }
            String name = processor.getName(value);
            if (name.isEmpty())
            {
                name = ZERO_TOKEN;
            }
            else if (negative)
            {
                name = MINUS.concat(SEPARATOR).concat(name);
            }
            if (!(null == decimalValue || decimalValue.isEmpty()))
            {
                name = name.concat(SEPARATOR).concat(UNION_AND).concat(SEPARATOR)
                        .concat(processor.getName(decimalValue)).concat(SEPARATOR).concat(SCALE.getName(-decimalValue.length()));
            }
            return name;
        }
    }
    
    /**
    * <p>
    * This method captures the numbers from the string.
    * </p>
    *
    * @param str A sentence containing numbers 
    * @return Number in string format
    */
    public static String NubmerInString(String str) {

        StringBuffer res = new StringBuffer();
        Pattern pat = Pattern.compile("[-]?[0-9]+(.[0-9]+)?");
        Matcher matcher = pat.matcher(str);
        while (matcher.find()) {
            res.insert(res.length(), matcher.group() + " ");
        }
        String result = String.valueOf(res);
        return result;
    }

    static public AbstractProcessor processor;

    /**
     * <p>
     * This method inserts the transformed numbers into the sentence in the correct order, using the methods described above. 
     * </p>
     *
     * @param str A sentence containing numbers
     * @return Numbers in words format into the sentence in the correct order
     */
    public static String NumberInStringFormatToText(String text) {
        processor = new DefaultProcessor();

        String strValues = NubmerInString(text);
        String[] subStr = strValues.split(" ");

        for (String val : subStr) {
            text = text.replace(val, processor.getName(val));
        }
        return text;


    }

}

