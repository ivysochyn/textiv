package pl.put.poznan.transformer.logic;

import java.util.Arrays;
import java.util.Objects;


public class TextTransformer {

    private String[] transforms;

    public TextTransformer(String[] transforms){
        this.transforms = transforms;

    }

    public String transform(String text){
        // of course, normally it would do something based on the transforms
        return text;
    }

    /**
     * Returns transforms list
     * @return String[]
     */
    public String[] getTransforms() {
        return transforms;
    }
    public void setTransforms(String[] transforms){
        this.transforms=transforms;
    }
}

