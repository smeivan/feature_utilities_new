package ru.ifmo.ctlab.ml.core.val;

// FIXME Maybe convert this in abstract class?
public interface FeaturesType {

    public String name(int feature);

    public int features();

    public int cmpFeatures();

    public int numFeatures();

    public int intFeatures();

    public int indexOfNumValue(int numId);

    public int indexOfIntValue(int intId);

}
