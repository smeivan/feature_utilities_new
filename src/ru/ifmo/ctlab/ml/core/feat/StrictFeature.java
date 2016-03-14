package ru.ifmo.ctlab.ml.core.feat;

public abstract class StrictFeature<T> implements Feature<T> {
	public boolean isMissing(T x) {
		return false;
	}
}
