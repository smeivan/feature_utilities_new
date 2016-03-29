package ru.ifmo.ctlab.ml.core.feat;

public class ConstNumericFeature<T> extends AbstractNumericFeature<T> {

	final public double value;

	public ConstNumericFeature(double d) {
		value = d;
	}

	@Override
	public double getNumericValue(T x) {
		return value;
	}

}
