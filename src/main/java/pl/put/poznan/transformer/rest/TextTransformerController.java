package pl.put.poznan.transformer.rest;

import pl.put.poznan.transformer.logic.EscapeCharactersTextTransformer;
import pl.put.poznan.transformer.logic.ITextTransformer;
import pl.put.poznan.transformer.logic.InversionTextTransformer;
import pl.put.poznan.transformer.logic.TextTransformer;
import pl.put.poznan.transformer.logic.TextTransformerCreator;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/{text}")
public class TextTransformerController {

  private static final Logger logger = LoggerFactory.getLogger(TextTransformerController.class);

  // FIXME
  /**
   * This method is used to run all the transformations on the text passed using REST API.
   *
   * @throws IOException if the JSON is malformed
   * @param text text to be transformed
   * @param json json containing transformations to be performed
   * @return transformed text
   */
  @PostMapping(value = "/transform")
  public String post(@PathVariable String text, @RequestBody String json) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    JsonNode jsonObject = mapper.readTree(json);
    JsonNode transformsNode = jsonObject.get("transforms");
    String transformsString = transformsNode.toString();
    List<String> transforms = new ArrayList<>();
    // FIXME
    transforms = mapper.readValue(transformsString, new TypeReference<List<String>>() {});
    // transforms = mapper.readValue(transformsString, transforms.getClass());
    // perform the transformation, you should run your logic here, below is just a silly example
    //  TextTransformer transformer = new TextTransformer(transforms);
    // return transformer.transform(text);
    String[] transformsArray = new String[transforms.size()];
    transforms.toArray(transformsArray);
    ITextTransformer textTransformer = new TextTransformer(transformsArray);
    textTransformer = TextTransformerCreator.createTextTransformer(textTransformer);
    String result= textTransformer.transform(text);
    ObjectNode nodeOutput=mapper.createObjectNode();
    nodeOutput.put("input",text);
    ArrayNode arrayNode=mapper.valueToTree(transformsArray);
 
    nodeOutput.set("transforms", arrayNode);
    nodeOutput.put("result", result);
    String outString=mapper.writerWithDefaultPrettyPrinter().writeValueAsString(nodeOutput);
    return outString;
  }
}
