package pl.put.poznan.transformer.logic;

public abstract class DecoratedTextTransformer implements ITextTransformer {
  ITextTransformer textTransformer;
  private String[] transforms;

  /**
   * DecoratedTextTransformer constructor with TextTransformer parameter.
   *
   * @param textTransformer TextTransformer object
   */
  public DecoratedTextTransformer(ITextTransformer textTransformer) {

    this.textTransformer = textTransformer;
    this.transforms = textTransformer.getTransforms();
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
