package ru.ifmo.ctlab.ml.core.feat;

public interface Feature<K, V> {
	public int compare(K x, K y);

	public double distance(K x, K y);

	public boolean equals(K x, K y);

	// public String getFeatureName();

	public V getFeatureValue(K x);

	public double getNumericValue(K x);

	public boolean isMissing(K x);
}
