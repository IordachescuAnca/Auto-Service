package Masini;

import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class Masina {
    private static int counter = 0;
    protected int ID;
    protected int numarKm;
    protected int an;
    protected boolean areDiesel;
    protected LocalDateTime timpFinal;
    protected int preferinta;

    public Masina(int numarKm, int an, String areDiesel){
        if(numarKm < 0){
            throw new IllegalArgumentException("Numarul kilometrilor nu poate fi negativ.");
        }
        this.numarKm = numarKm;

        LocalDate today = LocalDate.now();
        if(an > today.getYear()) {
            throw new IllegalArgumentException("Anul fabricatiei este mai mare decat anul curent.");
        }
        this.an = an;

        if(!(areDiesel.toLowerCase().compareTo("da") == 0 || areDiesel.toLowerCase().compareTo("nu") == 0)) {
            throw new IllegalArgumentException("Nu s-a dat un raspuns concret in privirea motorului Diesel.");
        }
        if(areDiesel.toLowerCase().compareTo("da") == 0) this.areDiesel = true;
        if(areDiesel.toLowerCase().compareTo("nu") == 0) this.areDiesel = false;

        counter++;
        this.ID = counter;
    }

    public abstract double calculeazaPolitaAsigurare();
    public abstract double calculeazaPolitaAsigurareDiscount();

    public void setTimpFinal(LocalDateTime timp){
        this.timpFinal = timp;
    }
    public LocalDateTime getTimpFinal(){
        return this.timpFinal;
    }

    public int getPreferinta() {
        return preferinta;
    }

    public void setPreferinta(int preferinta) {
        this.preferinta = preferinta;
    }

    public abstract String getType();
}
