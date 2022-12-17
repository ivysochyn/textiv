package pl.put.poznan.transformer.app.index;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class TextTransformerWeb {

  @GetMapping("/")
  public String textForm(Model model) {
    InputText inputText = new InputText();
    model.addAttribute("inputText", inputText);
    return "index";
  }

  @PostMapping("/")
  public String textSubmit(@ModelAttribute InputText text, Model model) {
    model.addAttribute("inputText", text);
    return "result";
  }
}
