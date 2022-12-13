package pl.put.poznan.transformer.logic;

public class DuplicateEliminationTextTransformer extends DecoratedTextTransformer {
    public DuplicateEliminationTextTransformer(TextTransformer textTransformer) {
        super(textTransformer);
    }

    @Override
    public String transform(String text) {
        String[] words = text.split(" ");
        String result = "";
        for (int i = 0; i < words.length; i++) {
            if (i == 0 || !words[i].equals(words[i - 1])) {
                result += words[i] + " ";
            }
        }
        return textTransformer.transform(result);
    }
}
