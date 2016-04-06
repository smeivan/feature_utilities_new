package ru.ifmo.ctlab.ml;

import java.util.List;

import Jama.Matrix;
import ru.ifmo.ctlab.ml.core.feat.Feature;
import ru.ifmo.ctlab.ml.core.feat.NumericFeture;

public class LinearRegression<T> {

	final double[][] alpha;
	final int n, m;

	public double[] getCoef(int tid) {
		return alpha[tid].clone();
	}

	final List<NumericFeture<T>> f;

	public LinearRegression(List<T> dataset, List<NumericFeture<T>> features, List<NumericFeture<T>> target) {
		f = features;
		n = target.size();
		m = features.size();

		double[][] a = new double[m][m];
		double[][] b = new double[n][m];

		for (T instance : dataset) {
			for (int i = 0; i < m; i++) {
				double val = f.get(i).getNumericValue(instance);
				for (int j = 0; j < i; j++) {
					double mul = val * f.get(j).getNumericValue(instance);
					a[i][j] += mul;
					a[j][i] += mul;
				}

				a[i][i] += val * val;

				for (int tid = 0; tid < n; tid++) {
					b[tid][i] += val * target.get(tid).getNumericValue(instance);
				}
			}
		}

		alpha = (new Matrix(b, n, m)).times((new Matrix(a, m, m)).inverse()).getArray();
	}

	public double[] solve(T instance) {
		double[] res = new double[n];

		for (int tid = 0; tid < n; tid++) {
			for (int i = 0; i < m; i++) {
				res[tid] += alpha[tid][i] * f.get(i).getNumericValue(instance);
			}
		}

		return res;
	}

}
