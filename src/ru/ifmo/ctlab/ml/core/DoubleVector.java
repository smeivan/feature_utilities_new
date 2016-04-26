package ru.ifmo.ctlab.ml.core;

import ru.ifmo.ctlab.ml.core.feat.AbstractIntegerFeature;
import ru.ifmo.ctlab.ml.core.val.FeaturesType;
import ru.ifmo.ctlab.ml.core.val.Instance;

public class DoubleVector implements Instance {

    private final double[] data;
    private final FeaturesType featuresType;

    public DoubleVector(double[] data, FeaturesType featuresType) {
        this.data = data;
        this.featuresType = featuresType;
    }

    @Override
    public FeaturesType featuresType() {
        return featuresType;
    }

    @Override
    public double distance(Instance other, int feature) {
        return Math.abs(numValue(feature) - other.numValue(feature));
    }

    @Override
    public int compare(Instance other, int feature) {
        return Double.compare(numValue(feature), other.numValue(feature));
    }

    @Override
    public double numValue(int numId) {
        return data[numId];
    }

    @Override
    public int intValue(int numId) {
        double val = data[numId];
        if (Double.isNaN(val)) {
            return Integer.MIN_VALUE;
        } else {
            return (int) Math.rint(val);
        }
    }

}
