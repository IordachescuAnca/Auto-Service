package Masini;

public class Camion extends Masina{
    private double tonaj;

    public Camion(int numarKm, int an, String areDiesel, double tonaj) {
        super(numarKm, an, areDiesel);
        if(tonaj < 0){
            throw new IllegalArgumentException("Tonajul nu poate fi negativ");
        }
        this.tonaj = tonaj;
    }

    @Override
    public double calculeazaPolitaAsigurare() {
        return 300 * this.an + (this.numarKm > 800000 ? 700 : 0);
    }

    @Override
    public double calculeazaPolitaAsigurareDiscount() {
        return 0.85 * this.calculeazaPolitaAsigurare();
    }
    public static void main(String []args){
        try {
            Masina a = new Camion(1000000023, 5, "DA", 23);
            System.out.println(a.calculeazaPolitaAsigurareDiscount());
        }
        catch(IllegalArgumentException exceptie){
            System.out.println(exceptie.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Camion{" +
                "tonaj=" + tonaj +
                '}';
    }

    public String getType(){
        return "camion";
    }
}
