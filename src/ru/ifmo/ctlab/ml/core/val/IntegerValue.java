package ru.ifmo.ctlab.ml.core.val;

import ru.ifmo.ctlab.ml.core.feat.IntegerFeature;

public class IntegerValue extends NumericValue implements IntegerFeature {

    public static final int MISSING_VALUE = Integer.MIN_VALUE;

    public IntegerValue(int valueId, int featId, FeaturesType type) {
        super(valueId, featId, type);
    }

    @Override
    public int intFeature(Instance instance) {
        return instance.intValue(valueId);
    }

    @Override
    public double numFeature(Instance instance) {
        int intValue = instance.intValue(valueId);
        if (intValue == MISSING_VALUE) {
            return NumericValue.MISSING_VALUE;
        }
        return intValue;
    }

}
