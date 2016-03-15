package ru.ifmo.ctlab.ml.core.feat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import Jama.EigenvalueDecomposition;
import Jama.Matrix;

public class PCA {

	static double clear(double val) {
		if (Double.isFinite(val)) {
			return val;
		} else {
			return 0;
		}
	}

	public static <T> List<Feature<T, ?>> pca(List<T> dataset, List<Feature<T, ?>> sourse, int m) {

		int n = sourse.size();

		if (m >= n) {
			// FIXME Add exception?
			return sourse;
		}

		double[] sum1 = new double[n];
		double[][] sum2 = new double[n][];

		int k = dataset.size();

		for (T instance : dataset) {
			for (int i = 0; i < n; i++) {
				sum2[i] = new double[i + 1];

				double x = clear(sourse.get(i).getNumericValue(instance));
				sum1[i] += x;
				sum2[i][i] += x * x;

				for (int j = 0; j <= i; j++) {
					double xy = x * clear(sourse.get(j).getNumericValue(instance));
					sum2[i][j] += xy;
				}
			}
		}

		double[][] cor = new double[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= i; j++) {
				double c = k * sum2[i][j] - sum1[i] * sum1[j];
				double x = Math.sqrt(k * sum2[i][i] - sum1[i] * sum1[i]);
				double y = Math.sqrt(k * sum2[j][j] - sum1[j] * sum1[j]);
				cor[i][j] = cor[j][i] = clear(c / (x * y));
			}
		}

		Matrix matrix = new Matrix(cor, n, n);
		EigenvalueDecomposition e = matrix.eig();

		double[] real = e.getRealEigenvalues();
		double[] imag = e.getImagEigenvalues();
		double[] norm = new double[n];
		for (int i = 0; i < n; i++) {
			norm[i] = real[i] * real[i] + imag[i] * imag[i];
		}

		Integer[] order = new Integer[n];
		for (int i = 0; i < n; i++) {
			order[i] = i;
		}

		Arrays.sort(order, new Comparator<Integer>() {
			@Override
			public int compare(Integer x, Integer y) {
				// FIXME norm[] or real[] ???
				return -Double.compare(norm[y], norm[x]);
			}
		});

		double[][] ev = e.getV().getArray();

		List<Feature<T, ?>> dest = new ArrayList<>();
		for (int f = 0; f < m; f++) {
			final int index = order[f];

			dest.add(new AbstractNumericFeature<T>() {
				@Override
				public double getNumericValue(T instance) {
					double val = 0;

					for (int i = 0; i < n; i++) {
						// FIXME ev[i][index] or ev[index][i] ???
						val += ev[i][index] * clear(sourse.get(i).getNumericValue(instance));
					}

					return val;
				}

			});
		}

		return dest;
	}

}