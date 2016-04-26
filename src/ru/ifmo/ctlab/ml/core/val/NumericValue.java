package ru.ifmo.ctlab.ml.core.val;

import ru.ifmo.ctlab.ml.core.feat.NumericFeature;

public class NumericValue extends ComparableValue implements NumericFeature {

    public static final double MISSING_VALUE = Double.NaN;

    public final int valueId;

    public NumericValue(int valueId, int featId, FeaturesType type) {
        super(featId, type);
        this.valueId = valueId;
    }

    public NumericValue(int valueId, FeaturesType type) {
        super(valueId, type);
        this.valueId = valueId;
    }

    @Override
    public double numFeature(Instance instance) {
        return instance.numValue(valueId);
    }
}
