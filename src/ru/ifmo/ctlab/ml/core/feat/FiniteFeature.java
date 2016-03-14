package ru.ifmo.ctlab.ml.core.feat;

public abstract class FiniteFeature<T> implements Feature<T> {

	public abstract int getValue(T x);

	public abstract int dim();

	public abstract String getName();

	@Override
	public boolean equals(T x, T y) {
		int xValue = getValue(x);
		if (xValue < 0) {
			return false;
		}

		int yValue = getValue(y);
		if (yValue < 0) {
			return false;
		}

		return xValue == yValue;
	}

	@Override
	public double distance(T x, T y) {
		int xValue = getValue(x);
		if (xValue < 0) {
			return Double.NaN;
		}

		int yValue = getValue(y);
		if (yValue < 0) {
			return Double.NaN;
		}

		return (xValue == yValue) ? (0.) : (1.);
	}

	@Override
	public int compare(T x, T y) {
		int xValue = getValue(x);
		int yValue = getValue(y);

		// Cast negative integer to long that greater than any integer,
		// so that all missing values be in the end (like Double.NAN).
		long mask = 0xFFFFFFFFl;
		return Long.compare(xValue & mask, yValue & mask);
	}

	@Override
	public boolean isMissing(T x) {
		return getValue(x) < 0;
	}

}
