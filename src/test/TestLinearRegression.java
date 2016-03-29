package test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import ru.ifmo.ctlab.ml.LinearRegression;
import ru.ifmo.ctlab.ml.core.feat.DoubleArrayFeature;
import ru.ifmo.ctlab.ml.core.feat.NumericFeture;

public class TestLinearRegression {

	static void println(double[] array) {
		for (double val : array) {
			System.out.printf(Locale.ENGLISH, "%7.3f ", val);
		}
		System.out.println();
	}

	public static void main(String[] args) throws FileNotFoundException {

		Random random = new Random();

		int numInst = 100;

		int numS = 10; // Number of source features.
		int numT = 5;// Number of target features.

		int instLen = numS + numT;

		double[][] alpha = new double[numT][numS];

		double snr = 5; // Signal to noise ratio.

		for (int i = 0; i < numT; i++) {
			for (int j = 0; j < numS; j++) {
				alpha[i][j] = random.nextGaussian() * (i + j);
			}
		}

		List<double[]> data = new ArrayList<double[]>();

		for (int id = 0; id < numInst; id++) {
			double[] inst = new double[instLen];

			int index = 0;
			for (int i = 0; i < numS; i++, index++) {
				inst[index] = random.nextGaussian();
			}

			for (int i = 0; i < numT; i++, index++) {

				// Add some noise in data
				inst[index] = random.nextGaussian() / snr;

				for (int j = 0; j < numS; j++) {
					inst[index] += alpha[i][j] * inst[j];
				}
			}

			data.add(inst);
		}

		List<NumericFeture<double[]>> sf = new ArrayList<NumericFeture<double[]>>();
		List<NumericFeture<double[]>> tf = new ArrayList<NumericFeture<double[]>>();
		{
			int index = 0;
			for (int i = 0; i < numS; i++) {
				sf.add(new DoubleArrayFeature(index++));
			}
			for (int i = 0; i < numT; i++) {
				tf.add(new DoubleArrayFeature(index++));
			}
		}

		LinearRegression<double[]> c = new LinearRegression<double[]>(data, sf, tf);

		for (int i = 0; i < numT; i++) {
			println(alpha[i]);
			println(c.getCoef(i));
			System.out.println();
		}

	}
}
