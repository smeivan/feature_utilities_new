package test;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import ru.ifmo.ctlab.ml.core.feat.AbstractNumericFeature;
import ru.ifmo.ctlab.ml.core.feat.NumericFeture;
import ru.ifmo.ctlab.ml.core.feat.PCA;
import viz.Plotter2D;

public class TestPCA {
	public static void main(String[] args) throws IOException {

		List<Double> dataset = new ArrayList<Double>();

		for (int i = 0; i < 100; i++) {
			dataset.add((double) i);
		}

		List<NumericFeture<Double>> sourse = new ArrayList<NumericFeture<Double>>();

		sourse.add(new AbstractNumericFeature<Double>() {
			@Override
			public double getNumericValue(Double x) {
				return x;
			}
		});

		sourse.add(new AbstractNumericFeature<Double>() {
			@Override
			public double getNumericValue(Double x) {
				return x * x;
			}
		});

		sourse.add(new AbstractNumericFeature<Double>() {
			@Override
			public double getNumericValue(Double x) {
				return Math.sin(x / 33);
			}
		});

		List<NumericFeture<Double>> target = PCA.pca(dataset, sourse, 2);
		RenderedImage image = Plotter2D.plot(dataset, target.get(0), target.get(1), 1024, 1024);
		ImageIO.write(image, "png", new File("plot.png"));

		// JFrame frame = new ImageViewer(image);
		// frame.setVisible(true);
		// frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

	}
}
