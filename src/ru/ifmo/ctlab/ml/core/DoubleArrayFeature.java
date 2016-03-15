package ru.ifmo.ctlab.ml.core;

import ru.ifmo.ctlab.ml.core.feat.AbstractNumericFeature;

public class DoubleArrayFeature extends AbstractNumericFeature<double[]> {

	public final int index;

	public DoubleArrayFeature(int index) {
		this.index = index;
	}

	@Override
	public double getNumericValue(double[] x) {
		return x[index];
	}

	@Override
	public String getFeatureName() {
		return "DoubleArrayFeature(" + index + ")";
	}

}
