package ru.ifmo.ctlab.ml.core.feat;

public interface Feature<T> {
	public String getName();

	public boolean equals(T x, T y);

	public double distance(T x, T y);

	public int compare(T x, T y);

	public boolean isMissing(T x);
}
