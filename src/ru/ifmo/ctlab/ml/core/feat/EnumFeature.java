package ru.ifmo.ctlab.ml.core.feat;

public interface EnumFeature<T> extends Feature<T, Integer> {
	public int dimension();

	public int getOrdinal(T x);

}
