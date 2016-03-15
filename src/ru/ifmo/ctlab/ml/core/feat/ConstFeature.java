package ru.ifmo.ctlab.ml.core.feat;

public class ConstFeature<T> extends AbstractNumericFeature<T> {

	final public double value;

	public ConstFeature(double d) {
		value = d;
	}

	@Override
	public double getNumericValue(T x) {
		return value;
	}
}
