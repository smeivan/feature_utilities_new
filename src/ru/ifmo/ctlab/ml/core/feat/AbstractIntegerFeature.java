package ru.ifmo.ctlab.ml.core.feat;

public abstract class AbstractIntegerFeature<T> implements IntegerFeature<T> {

	public static final int MISSING_VALUE = Integer.MIN_VALUE;

	@Override
	public int compare(T x, T y) {
		int xValue = getIntegerValue(x);
		int yValue = getIntegerValue(y);

		// Shift integer that MISSING_VALUE >= any integer.
		// Because MIN_VALUE - 1 = MAX_VALUE.
		return Integer.compare(xValue - 1, yValue - 1);
	}

	@Override
	public double distance(T x, T y) {
		int xValue = getIntegerValue(x);
		if (isMissing(xValue)) {
			return Double.NaN;
		}

		int yValue = getIntegerValue(y);
		if (isMissing(yValue)) {
			return Double.NaN;
		}

		return Math.abs(xValue - yValue);
	}

	@Override
	public boolean equals(T x, T y) {
		int xValue = getIntegerValue(x);
		if (isMissing(xValue)) {
			return false;
		}

		int yValue = getIntegerValue(y);
		if (isMissing(yValue)) {
			return false;
		}

		return xValue == yValue;
	}

	public boolean isMissing(int value) {
		return value == MISSING_VALUE;
	}

	@Override
	public double getNumericValue(T x) {
		int value = getIntegerValue(x);
		if (isMissing(value)) {
			return 0;
		} else {
			return value;
		}
	}

	@Override
	public double getFloatValue(T x) {
		int value = getIntegerValue(x);
		if (isMissing(value)) {
			return Double.NaN;
		} else {
			return value;
		}
	}

}
