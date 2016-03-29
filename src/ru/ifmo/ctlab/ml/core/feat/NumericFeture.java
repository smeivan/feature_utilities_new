package ru.ifmo.ctlab.ml.core.feat;

public interface NumericFeture<T> extends Feature<T> {

	public double getNumericValue(T x);

}
