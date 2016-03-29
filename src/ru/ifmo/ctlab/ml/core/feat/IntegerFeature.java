package ru.ifmo.ctlab.ml.core.feat;

public interface IntegerFeature<T> extends NumericFeture<T> {

	public int getIntegerValue(T x);

}
