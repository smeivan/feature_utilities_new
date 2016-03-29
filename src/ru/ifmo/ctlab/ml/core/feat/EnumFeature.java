package ru.ifmo.ctlab.ml.core.feat;

public interface EnumFeature<T> extends IntegerFeature<T> {
	public int dimension();

	public int getOrdinal(T x);

}
