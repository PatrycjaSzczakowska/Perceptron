import java.util.Random;

public class Perceptron {
    double waga1;
    double waga2;
    double bias;
    double wspolczynnikUczenia;
    Random rand = new Random();

    public Perceptron(double wspolczynnikUczenia) {

        this.wspolczynnikUczenia = wspolczynnikUczenia;
        this.waga1=rand.nextInt(100)/100.0;
        this.waga2=rand.nextInt(100)/100.0;
        this.bias=rand.nextInt(100)/100.0;
    }
    public int oblicz(Dane dane){
        double wynik= waga1* dane.wejscie1+waga2* dane.wejscie2+bias;
        int odpowiedz=funkcja(wynik);
        return odpowiedz;
    }

    public int ucz(Dane dane){
        double wynik= waga1* dane.wejscie1+waga2* dane.wejscie2+bias;
        int odpowiedz=funkcja(wynik);
        modyfikacja(dane, odpowiedz);
        return odpowiedz;
    }


    public void modyfikacja(Dane dane, int odpowiedz){
        waga1+=wspolczynnikUczenia*(dane.spodziewanaOdpowiedz-odpowiedz)* dane.wejscie1;
        waga2+=wspolczynnikUczenia*(dane.spodziewanaOdpowiedz-odpowiedz)* dane.wejscie2;
        bias+=wspolczynnikUczenia*(dane.spodziewanaOdpowiedz-odpowiedz);
    }

    @Override
    public String toString() {
        return "Perceptron{" +
                "waga1=" + waga1 +
                ", waga2=" + waga2 +
                ", bias=" + bias +
                '}';
    }

    public int funkcja (double x){
        if(x>0){
            return 1;
        }
        else{
            return 0;
        }
    }

    public double getWaga1() {
        return waga1;
    }

    public double getWaga2() {
        return waga2;
    }

    public double getBias() {
        return bias;
    }
}
