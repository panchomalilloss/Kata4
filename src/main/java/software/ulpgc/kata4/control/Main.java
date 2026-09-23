package software.ulpgc.kata4.control;

import software.ulpgc.kata4.io.RemoteMovieLoader;
import software.ulpgc.kata4.model.Movie;
import software.ulpgc.kata4.view.HistogramDisplay;
import software.ulpgc.kata4.viewmodel.Histogram;
import software.ulpgc.kata4.viewmodel.HistogramBuilder;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Movie> movies = new RemoteMovieLoader(Main::fromTsv).loadAll();

        Histogram histogram =
                new HistogramBuilder(movies)
                        .build(movie -> decadeOf(movie.year()));

        new HistogramDisplay().show(histogram);
    }

    private static int decadeOf(int year) {
        if (year < 0) return -1;
        return (year/10) *10;
    }

    private static Movie fromTsv(String s) {
        return fromTsv(s.split("\t"));
    }

    private static Movie fromTsv(String[] split) {
        return new Movie(split[2], toInt(split[5]), toInt(split[7]));
    }

    private static int toInt(String s) {
        if (s.equals("\\N")) return -1;
        return Integer.parseInt(s);
    }
}
