package pl.put.poznan.transformer.app.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TextTransformerWeb {

  @GetMapping("/")
  String index() {
    return "index";
  }
}
