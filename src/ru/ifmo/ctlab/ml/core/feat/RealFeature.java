package ru.ifmo.ctlab.ml.core.feat;


public abstract class RealFeature<T> implements Feature<T> {

	public static final double EPS = 1e-9;

	public abstract String getName();

	public abstract double getValue(T x);

	@Override
	public boolean equals(T x, T y) {
		return Math.abs(getValue(x) - getValue(y)) < EPS;
	}

	@Override
	public double distance(T x, T y) {
		return Math.abs(getValue(x) - getValue(y));
	}

	@Override
	public int compare(T x, T y) {
		return Double.compare(getValue(x), getValue(y));
	}

	@Override
	public boolean isMissing(T x) {
		return Double.isNaN(getValue(x));
	}
}
