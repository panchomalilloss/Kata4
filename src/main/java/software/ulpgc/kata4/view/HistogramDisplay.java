package software.ulpgc.kata4.view;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import software.ulpgc.kata4.viewmodel.Histogram;

import javax.swing.*;

public class HistogramDisplay {

    public void show(Histogram histogram) {

        DefaultCategoryDataset dataset = datasetOf(histogram);

        JFreeChart chart = ChartFactory.createBarChart(
                "Movies by decade",
                "Decade",
                "Number of movies",
                dataset
        );

        JFrame frame = new JFrame("IMDb Histogram");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new ChartPanel(chart));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    private DefaultCategoryDataset datasetOf(Histogram histogram) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        histogram.bins().stream()
                .sorted()
                .forEach(bin ->
                        dataset.addValue(
                                histogram.count(bin),
                                "Movies",
                                bin
                        )
                );

        return dataset;
    }
}
