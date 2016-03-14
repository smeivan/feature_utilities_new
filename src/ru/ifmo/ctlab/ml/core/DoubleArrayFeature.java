package ru.ifmo.ctlab.ml.core;

import ru.ifmo.ctlab.ml.core.feat.StrictRealFeature;

public class DoubleArrayFeature extends StrictRealFeature<double[]> {

	public final int index;

	public DoubleArrayFeature(int index) {
		this.index = index;
	}

	@Override
	public String getName() {
		return "DoubleArrayFeature(" + index + ")";
	}

	@Override
	public double getValue(double[] x) {
		return x[index];
	}

}
