package ru.ifmo.ctlab.ml.util;

import java.util.ArrayList;
import java.util.List;

import ru.ifmo.ctlab.ml.core.feat.AbstractNumericFeature;
import ru.ifmo.ctlab.ml.core.feat.EnumFeature;
import ru.ifmo.ctlab.ml.core.feat.Feature;
import ru.ifmo.ctlab.ml.core.feat.NumericFeture;
import weka.core.Instance;

public class Features {

	public static <T> List<NumericFeture<T>> getDistribution(final EnumFeature<T> feature) {

		int d = feature.dimension();
		List<NumericFeture<T>> features = new ArrayList<NumericFeture<T>>();

		for (int i = 0; i < d; i++) {
			final int index = i;
			features.add(new AbstractNumericFeature<T>() {

				@Override
				public double getNumericValue(T x) {
					int ord = feature.getOrdinal(x);

					if (ord < 0) {
						return Double.NaN;
					}

					if (ord == index) {
						return 1.0;
					}

					return 0.0;
				}

			});
		}

		return features;
	}

}
