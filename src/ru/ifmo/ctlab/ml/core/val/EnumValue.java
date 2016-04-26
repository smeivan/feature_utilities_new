package ru.ifmo.ctlab.ml.core.val;

import ru.ifmo.ctlab.ml.core.feat.EnumFeature;

public class EnumValue extends IntegerValue implements EnumFeature {

    private final int dimension;

    public EnumValue(int dimension, int valueId, int featId, FeaturesType type) {
        super(valueId, featId, type);
        this.dimension = dimension;
    }

    @Override
    public int dimension() {
        return dimension;
    }

    @Override
    public int getOrdinal(Instance instance) {
        return instance.intValue(valueId);
    }

}
