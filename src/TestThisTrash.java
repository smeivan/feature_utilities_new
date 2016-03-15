import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.List;

import ru.ifmo.ctlab.ml.ArffDataSet;
import ru.ifmo.ctlab.ml.LinearRegression;
import ru.ifmo.ctlab.ml.core.feat.ConstFeature;
import ru.ifmo.ctlab.ml.core.feat.EnumFeature;
import ru.ifmo.ctlab.ml.core.feat.Feature;
import ru.ifmo.ctlab.ml.core.feat.PCA;
import ru.ifmo.ctlab.ml.util.Features;
import ru.ifmo.ctlab.ml.util.Pair;
import weka.classifiers.Classifier;
import weka.core.Instance;
import weka.core.Instances;

public class TestThisTrash {

	static int argMax(double[] d) {
		int maxIndex = 0;
		for (int i = 1; i < d.length; i++) {
			if (d[i] > d[maxIndex]) {
				maxIndex = i;
			}
		}

		return maxIndex;
	}

	public static void main(String[] args) throws FileNotFoundException {

		File dataFolder = new File("data");

		int iter = 1000;

		try (PrintWriter result = new PrintWriter(new File("result.txt"))) {

			for (File arff : dataFolder.listFiles()) {

				if (arff.length() > 10000) {
					continue;
				}

				if (--iter <= 0) {
					break;
				}

				try {

					Instances instances = new Instances(new FileReader(arff));
					instances.setClassIndex(instances.numAttributes() - 1);

					int aScore = 0, bScore = 0, cScore = 0, all = instances.numInstances();
					long aTime = 0, bTime = 0, cTime = 0;
					{
						long start = System.nanoTime();
						try {
							Pair<List<Feature<Instance, ?>>, EnumFeature<Instance>> p = ArffDataSet.getFeatureSpace(instances);
							List<Feature<Instance, ?>> f = p.x;

							// FIXME push this in classifier?
							f.add(new ConstFeature<Instance>(1.0));

							List<Feature<Instance, ?>> t = Features.getDistribution(p.y);
							LinearRegression<Instance> c = new LinearRegression<Instance>(instances, f, t);

							for (Instance instance : instances) {
								int x = argMax(c.solve(instance));
								int y = (int) instance.classValue();

								if (x == y) {
									++aScore;
								}
							}
							System.out.print(aScore + " ");
						} catch (Exception err) {
							System.out.print(err.getMessage() + " ");
						}
						long finish = System.nanoTime();
						aTime = finish - start;
					}

					System.out.flush();

					{
						long start = System.nanoTime();
						try {
							Pair<List<Feature<Instance, ?>>, EnumFeature<Instance>> p = ArffDataSet.getFeatureSpace(instances);
							List<Feature<Instance, ?>> f = p.x;

							f = PCA.pca(instances, f, Math.min((f.size() + 1) / 2, 5));

							// FIXME push this in classifier?
							f.add(new ConstFeature<Instance>(1.0));

							List<Feature<Instance, ?>> t = Features.getDistribution(p.y);
							LinearRegression<Instance> c = new LinearRegression<Instance>(instances, f, t);

							for (Instance instance : instances) {
								int x = argMax(c.solve(instance));
								int y = (int) instance.classValue();

								if (x == y) {
									++bScore;
								}
							}
							System.out.print(bScore + " ");
						} catch (Exception err) {
							System.out.print(err.getMessage() + " ");
						}
						long finish = System.nanoTime();
						bTime = finish - start;
					}

					System.out.flush();

					{
						long start = System.nanoTime();
						try {
							Classifier c = new weka.classifiers.functions.SMO();
							c.buildClassifier(instances);

							for (Instance instance : instances) {
								int x = argMax(c.distributionForInstance(instance));
								int y = (int) instance.classValue();

								if (x == y) {
									++cScore;
								}
							}
							System.out.print(cScore + " ");
						} catch (Exception err) {
							System.out.print(err.getMessage() + " ");
						}
						long finish = System.nanoTime();
						cTime = finish - start;
					}

					result.printf("%10s ", arff.getName());
					result.printf("        %10d  %10d  %10d  /  %10d", aScore, bScore, cScore, all);
					result.printf("        %10d  %10d  %10d", aTime, bTime, cTime);
					result.println();

					System.out.println();

				} catch (Exception err) {
					System.out.println(err.getMessage());
				}
			}
		}

	}
}
