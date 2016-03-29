package ru.ifmo.ctlab.ml.core.feat;

public abstract class AbstractEnumFeature<T> implements EnumFeature<T> {
	public static final int MISSING_VALUE = Integer.MIN_VALUE;

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
		if (isMissing(xValue)) {
			return Double.NaN;
		}

		int yValue = getOrdinal(y);
		if (isMissing(yValue)) {
			return Double.NaN;
		}

		return (xValue == yValue) ? (0.) : (1.);
	}

	@Override
	public boolean equals(T x, T y) {
		int xValue = getOrdinal(x);
		if (isMissing(xValue)) {
			return false;
		}

		int yValue = getOrdinal(y);
		if (isMissing(yValue)) {
			return false;
		}

		return xValue == yValue;
	}

	public boolean isMissing(int ordinal) {
		return ordinal < 0;
	}

	@Override
	public int getIntegerValue(T x) {
		return getOrdinal(x);
	}

	@Override
	public double getNumericValue(T x) {
		int value = getOrdinal(x);
		if (isMissing(value)) {
			return 0;
		} else {
			return (value + 1.) / dimension();
		}
	}

	@Override
	public double getFloatValue(T x) {
		int value = getOrdinal(x);
		if (isMissing(value)) {
			return Double.NaN;
		} else {
			return value;
		}
	}

}
