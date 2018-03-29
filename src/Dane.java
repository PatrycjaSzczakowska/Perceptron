public class Dane {
    double wejscie1;
    double wejscie2;
    int spodziewanaOdpowiedz;

    public Dane(double wejscie1, double wejscie2, int spodziewanaOdpowiedz) {
        this.wejscie1 = wejscie1;
        this.wejscie2 = wejscie2;
        this.spodziewanaOdpowiedz=spodziewanaOdpowiedz;
    }

    public String toString() {
        return String.valueOf(wejscie1)+" "+String.valueOf(wejscie2)+" "+String.valueOf(spodziewanaOdpowiedz);
    }

    public double getWejscie1() {
        return wejscie1;
    }

    public double getWejscie2() {
        return wejscie2;
    }

    public int getSpodziewanaOdpowiedz() {
        return spodziewanaOdpowiedz;
    }
}
