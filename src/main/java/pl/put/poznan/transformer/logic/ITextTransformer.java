package pl.put.poznan.transformer.logic;
public interface ITextTransformer{
    public String transform(String text);
    public String[] getTransforms();
    public void setTransforms(String[] transforms);
}