package ru.ifmo.ctlab.ml.util;

import java.util.ArrayList;
import java.util.List;

import ru.ifmo.ctlab.ml.core.feat.AbstractNumericFeature;
import ru.ifmo.ctlab.ml.core.feat.EnumFeature;
import ru.ifmo.ctlab.ml.core.feat.Feature;

public class Features {

	public static <T> List<Feature<T, ?>> getDistribution(EnumFeature<T> feature) {

		int d = feature.dimension();
		List<Feature<T, ?>> features = new ArrayList<Feature<T, ?>>();

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
