package ru.ifmo.ctlab.ml.core.feat;

public abstract class AbstractNumericFeature<T> implements Feature<T, Double> {
	public static final double EPS = 1e-9;

	@Override
	public int compare(T x, T y) {
		return Double.compare(getNumericValue(x), getNumericValue(y));
	}

	@Override
	public double distance(T x, T y) {
		return Math.abs(getNumericValue(x) - getNumericValue(y));
	}

	@Override
	public boolean equals(T x, T y) {
		return Math.abs(getNumericValue(x) - getNumericValue(y)) < EPS;
	}

	@Override
	public Double getFeatureValue(T x) {
		return getNumericValue(x);
	}

	@Override
	public boolean isMissing(T x) {
		return Double.isNaN(getNumericValue(x));
	}

}
