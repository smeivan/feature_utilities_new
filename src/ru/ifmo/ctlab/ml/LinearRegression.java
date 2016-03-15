package ru.ifmo.ctlab.ml;

import java.util.List;

import Jama.Matrix;
import ru.ifmo.ctlab.ml.core.feat.Feature;

public class LinearRegression<T> {

	final double[][] w;
	final int n, m;

	final List<Feature<T, ?>> f;

	double clear(double val) {
		if (Double.isFinite(val)) {
			return val;
		} else {
			return 0;
		}
	}

	public LinearRegression(List<T> dataset, List<Feature<T, ?>> features, List<Feature<T, ?>> target) {
		f = features;
		n = target.size();
		m = features.size();
		w = new double[n][m];

		for (int tid = 0; tid < n; tid++) {
			double[][] a = new double[m][m];
			double[] b = new double[m];

			for (T instance : dataset) {
				for (int i = 0; i < m; i++) {
					double val = clear(f.get(i).getNumericValue(instance));
					for (int j = 0; j < i; j++) {
						double mul = val * clear(f.get(j).getNumericValue(instance));
						a[i][j] += mul;
						a[j][i] += mul;
					}

					a[i][i] += val * val;

					b[i] += val * clear(target.get(tid).getNumericValue(instance));
				}
			}

			double[][] c = new Matrix(a, m, m).inverse().getArray();

			for (int i = 0; i < m; i++) {
				for (int j = 0; j < m; j++) {
					w[tid][i] += c[j][i] * b[i];
				}
			}
		}
	}

	public double[] solve(T instance) {
		double[] res = new double[n];

		for (int tid = 0; tid < n; tid++) {
			for (int i = 0; i < m; i++) {
				res[tid] += w[tid][i] * clear(f.get(i).getNumericValue(instance));
			}
		}

		return res;
	}

}
