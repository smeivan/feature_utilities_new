package ru.ifmo.ctlab.ml.core.feat;

public interface Feature<T> {
	public int compare(T x, T y);

	public double distance(T x, T y);

	public boolean equals(T x, T y);

	public double getFloatValue(T x);
}
