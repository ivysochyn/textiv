package pl.put.poznan.transformer.rest;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.EscapeCharactersTextTransformer;
import pl.put.poznan.transformer.logic.InversionTextTransformer;
import pl.put.poznan.transformer.logic.TextTransformer;
import pl.put.poznan.transformer.logic.TextTransformerCreator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/{text}")
public class TextTransformerController {

  private static final Logger logger = LoggerFactory.getLogger(TextTransformerController.class);

  @RequestMapping(
      value = "/transforms/inverse",
      method = RequestMethod.GET,
      produces = "application/json")
  public String inverse(@PathVariable String text, String[] transforms) {

    // log the parameters
    logger.debug(text);
    logger.debug(Arrays.toString(transforms));

    // perform the transformation, you should run your logic here, below is just a silly example
    TextTransformer transformer = new TextTransformer(transforms);

    String test = Arrays.toString(transforms);

    return InversionTextTransformer.inversion(text);
  }

  @RequestMapping(
      value = "/transforms/escape",
      method = RequestMethod.GET,
      produces = "application/json")
  public String escape(@PathVariable String text, String[] transforms) {

    // log the parameters
    logger.debug(text);
    logger.debug(Arrays.toString(transforms));

    // perform the transformation, you should run your logic here, below is just a silly example
    TextTransformer transformer = new TextTransformer(transforms);

    String test = Arrays.toString(transforms);

    return EscapeCharactersTextTransformer.Latex(text);
  }

  @PostMapping(value = "/transform")
  public String post(@PathVariable String text, @RequestBody String json) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    JsonNode jsonObject = mapper.readTree(json);
    JsonNode transformsNode = jsonObject.get("transforms");
    String transformsString = transformsNode.toString();
    List<String> transforms = new ArrayList<>();
    transforms = mapper.readValue(transformsString, transforms.getClass());
    // perform the transformation, you should run your logic here, below is just a silly example
    //  TextTransformer transformer = new TextTransformer(transforms);
    // return transformer.transform(text);
    String[] transformsArray = new String[transforms.size()];
    transforms.toArray(transformsArray);
    TextTransformer textTransformer = new TextTransformer(transformsArray);
    textTransformer = TextTransformerCreator.createTextTransformer(textTransformer);
    return textTransformer.transform(text);
  }
}
