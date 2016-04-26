package ru.ifmo.ctlab.ml.core.val;

public interface Instance {

    public FeaturesType featuresType();

    /**
     * Distance between missing values is Double.NaN
     */
    public double distance(Instance other, int feature);

    /**
     * All missing values should be in the end!
     */
    public int compare(Instance other, int feature);

    /**
     * Missing value is Double.NaN
     */
    public double numValue(int numId);

    /**
     * Missing value is Integer.MIN_VALUE
     */
    public int intValue(int numId);

}
