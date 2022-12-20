package pl.put.poznan.transformer.logic;

import java.util.Arrays;
import java.util.Objects;

public class TextTransformer implements ITextTransformer{

  private String[] transforms;

  public TextTransformer(String[] transforms) {
    this.transforms = transforms;
  }

  /**
   * This method applies all the transforms to the text Should be overriden in subclasses.
   *
   * @param text the text to be transformed
   * @return the transformed text
   */
  public String transform(String text) {
    return text;
  }

  /**
   * Retuns the list of transforms that will be applied to the text.
   *
   * @return String[]
   */
  public String[] getTransforms() {
    return transforms;
  }

  /**
   * Sets the list of transforms that will be applied to the text.
   *
   * @param transforms List of transforms
   */
  public void setTransforms(String[] transforms) {
    this.transforms = transforms;
  }
}
