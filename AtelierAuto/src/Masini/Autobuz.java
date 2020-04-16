package Masini;

public class Autobuz extends Masina {
    private int numarLocuri;

    public Autobuz(int numarKm, int an, String areDiesel, int numarLocuri) {
        super(numarKm, an, areDiesel);
        if(numarLocuri < 0){
            throw new IllegalArgumentException("Numarul locurilor nu poate fi negativ");
        }
        this.numarLocuri = numarLocuri;
    }

    @Override
    public double calculeazaPolitaAsigurare() {
        int politaAsigurare = 200 * this.an + (this.areDiesel ? 1000 : 0);
        if(this.numarKm > 200000){
            politaAsigurare += 1000;
        }
        else if(this.numarKm > 100000){
            politaAsigurare += 500;
        }
        return politaAsigurare;
    }

    @Override
    public double calculeazaPolitaAsigurareDiscount() {
        return 0.9 * this.calculeazaPolitaAsigurare();
    }

    @Override
    public String toString() {
        return "Autobuz{" +
                "numarLocuri=" + numarLocuri +
                '}';
    }

    public String getType(){
        return "autobuz";
    }
}
