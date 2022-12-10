package pl.put.poznan.transformer.logic;

import java.util.Arrays;
import java.util.Objects;

public class TextTransformerCreator {

   public TextTransformerCreator(String[] transforms){

   }
    /**
     * @return TextTransformer
     *<p>Creates DecoratedTextTransformer containing Decorated objects of class TextTransformer of types based on the contents of String[] transforms.</p>
     *
     * <p>Possible tags: expanding, collapsing, capitalize, upper, lower, duplicate, escape, spellcheck, inversion, num2word</p>
     */
    public static TextTransformer  createTextTransformer(TextTransformer textTransformer){
        String[] transforms= textTransformer.getTransforms();
        if(transforms.length>0){
            textTransformer.setTransforms(Arrays.copyOfRange(transforms, 1, transforms.length));

            if(Objects.equals(transforms[0], "expanding")||Objects.equals(transforms[0],"collapsing")) {

                textTransformer = createTextTransformer(new AbbreviationTextTransformer(textTransformer,transforms[0]));
            }else if(Objects.equals(transforms[0], "capitalize") || Objects.equals(transforms[0], "upper") || Objects.equals(transforms[0], "lower")){
                textTransformer=createTextTransformer(new CaseTextTransformer(textTransformer,transforms[0]));
            }else if(Objects.equals(transforms[0],"duplicate")){
                textTransformer=createTextTransformer(new DuplicateEliminationTextTransformer(textTransformer));
            }
            else if(Objects.equals(transforms[0],"escape")){
                textTransformer=createTextTransformer(new EscapeCharactersTextTransformer(textTransformer));


            } else if (Objects.equals(transforms[0],"spellcheck")) {
                textTransformer=createTextTransformer(new SpellCheckTextTransformer(textTransformer));
            } else if (Objects.equals(transforms[0],"inversion")) {
                textTransformer=createTextTransformer(new InversionTextTransformer(textTransformer));
            } else if (Objects.equals(transforms[0],"num2word")) {
                textTransformer=createTextTransformer(new NumberToWordTextTransformer(textTransformer));

            }

        }



        return  textTransformer;
    }
}
