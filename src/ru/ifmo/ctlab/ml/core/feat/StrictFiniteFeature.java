package ru.ifmo.ctlab.ml.core.feat;

public abstract class StrictFiniteFeature<T> extends FiniteFeature<T> {

	@Override
	public boolean equals(T x, T y) {
		return getValue(x) == getValue(y);
	}

	@Override
	public double distance(T x, T y) {
		return (getValue(x) == getValue(y)) ? (0.) : (1.);
	}

	@Override
	public int compare(T x, T y) {
		return Integer.compare(getValue(x), getValue(y));
	}

	@Override
	public boolean isMissing(T x) {
		return false;
	}

}
