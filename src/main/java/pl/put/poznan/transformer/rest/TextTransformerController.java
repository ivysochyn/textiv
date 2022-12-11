package pl.put.poznan.transformer.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.EscapeCharactersTextTransformer;
import pl.put.poznan.transformer.logic.InversionTextTransformer;
import pl.put.poznan.transformer.logic.TextTransformer;

import java.util.Arrays;


@RestController
@RequestMapping("/{text}")
public class TextTransformerController {

    private static final Logger logger = LoggerFactory.getLogger(TextTransformerController.class);

    @RequestMapping(value = "/transforms/inverse",method = RequestMethod.GET, produces = "application/json")
    public String inverse(@PathVariable String text,
                              String[] transforms) {

        // log the parameters
        logger.debug(text);
        logger.debug(Arrays.toString(transforms));

        // perform the transformation, you should run your logic here, below is just a silly example
        TextTransformer transformer = new TextTransformer(transforms);

        String test = Arrays.toString(transforms);

        return InversionTextTransformer.inversion(text);
    }

    @RequestMapping(value = "/transforms/escape",method = RequestMethod.GET, produces = "application/json")
    public String escape(@PathVariable String text,
                          String[] transforms) {

        // log the parameters
        logger.debug(text);
        logger.debug(Arrays.toString(transforms));

        // perform the transformation, you should run your logic here, below is just a silly example
        TextTransformer transformer = new TextTransformer(transforms);

        String test = Arrays.toString(transforms);

        return EscapeCharactersTextTransformer.Latex(text);
    }


    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public String post(@PathVariable String text,
                      @RequestBody String[] transforms) {

        // log the parameters
        logger.debug(text);
        logger.debug(Arrays.toString(transforms));

        // perform the transformation, you should run your logic here, below is just a silly example
        TextTransformer transformer = new TextTransformer(transforms);
        return transformer.transform(text);
    }



}


