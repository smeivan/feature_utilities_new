package ru.ifmo.ctlab.ml.core.feat;

public abstract class AbstractNumericFeature<T> implements NumericFeture<T> {
	public static final double EPS = 1e-9;

	@Override
	public int compare(T x, T y) {
		return Double.compare(getFloatValue(x), getFloatValue(y));
	}

	@Override
	public double distance(T x, T y) {
		return Math.abs(getFloatValue(x) - getFloatValue(y));
	}

	@Override
	public boolean equals(T x, T y) {
		return Math.abs(getFloatValue(x) - getFloatValue(y)) < EPS;
	}

	@Override
	public double getFloatValue(T x) {
		return getNumericValue(x);
	}

}
