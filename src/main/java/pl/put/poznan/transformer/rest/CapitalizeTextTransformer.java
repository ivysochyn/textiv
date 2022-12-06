package pl.put.poznan.transformer.rest;

import pl.put.poznan.transformer.logic.TextTransformer;

public class CapitalizeTextTransformer extends TextTransformer {
    public CapitalizeTextTransformer(String[] transforms) {
        super(transforms);
    }
    @Override
    public String transform(String text){
       return text.toUpperCase();
    }
}
