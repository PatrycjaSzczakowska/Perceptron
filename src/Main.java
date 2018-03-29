import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;


public class Main extends Application {
    static ArrayList<Dane> dane = new ArrayList<>();
    static ArrayList<Dane> daneWyjsciowe = new ArrayList<>();
    static Perceptron perceptron = new Perceptron(0.25);

    static public void main(String[] args) {


        wczytaj(dane);

        final int liczbaEpok = 1;


        //Uczenie sie perceptronu
        for (int i = 0; i < liczbaEpok; i++) {
            int dummyCounter = 0;
            for (Dane dw : dane) {
                perceptron.ucz(dw);
                if (dummyCounter++ > 30) break;
            }
            Collections.shuffle(dane);
        }


        //Test
        for (Dane dw : dane) {
            int wynik = perceptron.oblicz(dw);
            daneWyjsciowe.add(new Dane(dw.wejscie1, dw.wejscie2, wynik));
        }


        //Dokładnosc
        int poprawne = 0;
        for (Dane d : daneWyjsciowe) {
            if (3 * d.getWejscie1() + 4 > d.getWejscie2() && d.getSpodziewanaOdpowiedz() == 1) {
                poprawne++;
            }
            if (3 * d.getWejscie1() + 4 < d.getWejscie2() && d.getSpodziewanaOdpowiedz() == 0) {
                poprawne++;
            }
        }

        System.out.println("Poprawnie: " + poprawne + "/" + daneWyjsciowe.size());

        launch(args);
    }

    static public ArrayList<Dane> wczytaj(ArrayList<Dane> dane) {
        try (BufferedReader br = new BufferedReader(new FileReader("D:\\STUDIA\\2 rok\\IV semestr\\IAD\\Perceptron\\out\\production\\Perceptron\\plik"))) {
            for (String line; (line = br.readLine()) != null; ) {
                // process the line.
                String[] parts = line.split(" ");
                dane.add(new Dane(Double.parseDouble(parts[0]),
                        Double.parseDouble(parts[1]), Integer.parseInt(parts[2])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return dane;
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Perceptron  f(x)=3x+4");

        final LineChart<Number, Number> sc = new LineChart<>(new NumberAxis(), new NumberAxis());

        XYChart.Series seriesLine = new XYChart.Series();

        Comparator<Dane> daneYComparator = (Dane d1, Dane d2) -> {
            return Double.compare(d1.getWejscie2(), d2.getWejscie2());
        };

        double minY = Collections.min(dane, daneYComparator).getWejscie2();
        double maxY = Collections.max(dane, daneYComparator).getWejscie2();
        double minX = -perceptron.getWaga2() * minY / perceptron.getWaga1() - perceptron.getBias() / perceptron.getWaga1();
        double maxX = -perceptron.getWaga2() * maxY / perceptron.getWaga1() - perceptron.getBias() / perceptron.getWaga1();

        seriesLine.getData().add(new XYChart.Data(minX, minY));
        seriesLine.getData().add(new XYChart.Data(maxX, maxY));


        XYChart.Series seriesPoints0 = new XYChart.Series();
        XYChart.Series seriesPoints1 = new XYChart.Series();
        seriesPoints0.setName("Klasa 0");
        seriesPoints1.setName("Klasa 1");

        for (Dane d : dane) {
            if (d.getSpodziewanaOdpowiedz() == 1) {
                seriesPoints1.getData().add(new XYChart.Data(d.getWejscie1(), d.getWejscie2()));
            } else {
                seriesPoints0.getData().add(new XYChart.Data(d.getWejscie1(), d.getWejscie2()));
            }
        }

        sc.getData().addAll(seriesLine, seriesPoints0, seriesPoints1);
        Scene scene = new Scene(sc, 500, 400);
        scene.getStylesheets().add("chart.css");
        stage.setScene(scene);
        stage.show();
    }

    public void poprawnosc (){

    }
}
