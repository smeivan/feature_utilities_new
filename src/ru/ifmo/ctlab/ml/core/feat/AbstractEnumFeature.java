package ru.ifmo.ctlab.ml.core.feat;

public abstract class AbstractEnumFeature<T> implements EnumFeature<T> {

	@Override
	public int compare(T x, T y) {
		int xValue = getOrdinal(x);
		int yValue = getOrdinal(y);

		// Cast negative integer to long that greater than any integer,
		// so that all missing values be in the end (like Double.NAN).
		long mask = 0xFFFFFFFFl;
		return Long.compare(xValue & mask, yValue & mask);
	}

	@Override
	public double distance(T x, T y) {
		int xValue = getOrdinal(x);
		if (xValue < 0) {
			return Double.NaN;
		}

		int yValue = getOrdinal(y);
		if (yValue < 0) {
			return Double.NaN;
		}

		return (xValue == yValue) ? (0.) : (1.);
	}

	@Override
	public boolean equals(T x, T y) {
		int xValue = getOrdinal(x);
		if (xValue < 0) {
			return false;
		}

		int yValue = getOrdinal(y);
		if (yValue < 0) {
			return false;
		}

		return xValue == yValue;
	}

	@Override
	public double getNumericValue(T x) {
		return (double) getOrdinal(x) / dimension();
	}

	@Override
	public Integer getFeatureValue(T x) {
		return getOrdinal(x);
	}

	@Override
	public boolean isMissing(T x) {
		return getOrdinal(x) < 0;
	}

}
