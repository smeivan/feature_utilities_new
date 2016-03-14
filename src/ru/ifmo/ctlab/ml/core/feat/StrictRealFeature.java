package ru.ifmo.ctlab.ml.core.feat;


public abstract class StrictRealFeature<T> extends RealFeature<T> {

	@Override
	public boolean isMissing(T x) {
		return false;
	}
}
