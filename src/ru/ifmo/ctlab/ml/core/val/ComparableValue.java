package ru.ifmo.ctlab.ml.core.val;

import ru.ifmo.ctlab.ml.core.feat.Feature;

public class ComparableValue implements Feature {

    public final int featId;
    public final FeaturesType type;

    public ComparableValue(int featId, FeaturesType type) {
        this.featId = featId;
        this.type = type;
    }

    @Override
    public int compare(Instance x, Instance y) {
        return x.compare(y, featId);
    }

    @Override
    public double distance(Instance x, Instance y) {
        return x.distance(y, featId);
    }

    @Override
    public String toString() {
        return type.name(featId);
    }

    @Override
    public String name() {
        return type.name(featId);
    }

}
