package Masini;

public class Standard extends Masina{
    private String trasmisie;

    public Standard(int numarKm, int an, String areDiesel, String trasmisie) {
        super(numarKm, an, areDiesel);
        if(!(trasmisie.toLowerCase().compareTo("automat") == 0 || trasmisie.toLowerCase().compareTo("manual") == 0)) {
            throw new IllegalArgumentException("Nu s-a dat un raspuns concret in privirea transmisiei.");
        }
        this.trasmisie = trasmisie.toLowerCase();
    }

    @Override
    public double calculeazaPolitaAsigurare() {
        return 100 * this.an + (this.areDiesel ? 500 : 0) + (this.numarKm > 200000 ? 500 : 0);
    }

    @Override
    public double calculeazaPolitaAsigurareDiscount() {
        return 0.95 * this.calculeazaPolitaAsigurare();
    }

    public static void main(String []args){
        try {
            Masina a = new Standard(1000000123, 10, "da", "automat");
            System.out.println(a.calculeazaPolitaAsigurareDiscount());
        }
        catch(IllegalArgumentException exceptie){
            System.out.println(exceptie.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Standard{" +
                "trasmisie='" + trasmisie + '\'' +
                '}';
    }

    public String getType(){
        return "standard";
    }
}
