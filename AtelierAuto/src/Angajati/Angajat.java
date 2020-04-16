package Angajati;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public abstract class Angajat {
    private static int counter = 0;
    protected int ID;
    protected String nume;
    protected String prenume;
    protected LocalDate dataNastere;
    protected LocalDate dataAngajarii;
    protected double bacsis;

    public Angajat(String nume, String prenume, String dataNastere, String dataAngajarii) throws DateTimeException{
        if(nume.length() > 30) throw new IllegalArgumentException("Numele depaseste 30 caractere.");
        if(prenume.length() > 30) throw new IllegalArgumentException("Prenumele depaseste 30 caractere.");
        if(nume.length() == 0) throw new IllegalArgumentException("Numele este null.");
        if(prenume.length() == 0) throw new IllegalArgumentException("Prenumele este null.");
        this.nume = nume;
        this.prenume = prenume;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataAng = LocalDate.parse(dataAngajarii, formatter);
        LocalDate dataNast = LocalDate.parse(dataNastere, formatter);
        LocalDate today = LocalDate.now();
        Period varstaActuala = Period.between(dataNast, today);
        if(varstaActuala.getYears() < 18){
            throw new IllegalArgumentException("Nu este major.");
        }
        if(dataAng.compareTo(today) > 0) throw new IllegalArgumentException("Data este din viitor.");
        this.dataAngajarii = dataAng;
        this.dataNastere = dataNast;
        counter++;
        this.ID = counter;
    }

    public abstract double calculeazaSalariu();

    public int getID(){
        return this.ID;
    }

    @Override
    public String toString() {
        return "ID angajat: " + this.ID + "\nNume: " + this.nume + "\nPrenume: " + this.prenume
                + "\nData angajarii: " + this.dataAngajarii.toString() + "\n";
    }

    public void setNume(String nume){
        if(nume.length() > 30) throw new IllegalArgumentException("Numele depaseste 30 caractere.");
        if(nume.length() == 0) throw new IllegalArgumentException("Numele este null.");
        this.nume = nume;
    }

    public void setPrenume(String prenume){
        if(prenume.length() > 30) throw new IllegalArgumentException("Numele depaseste 30 caractere.");
        if(prenume.length() == 0) throw new IllegalArgumentException("Numele este null.");
        this.prenume = prenume;
    }
    public void setDataNastere(String dataNastere){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataNast = LocalDate.parse(dataNastere, formatter);
        LocalDate today = LocalDate.now();
        Period varstaActuala = Period.between(dataNast, today);
        if(varstaActuala.getYears() < 18){
            throw new IllegalArgumentException("Nu este major.");
        }
        this.dataNastere = dataNast;
        this.bacsis = 0;
    }
    public void setDataAngajarii(String dataAngajarii){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataAng = LocalDate.parse(dataAngajarii, formatter);
        LocalDate today = LocalDate.now();
        if(dataAng.compareTo(today) > 0) throw new IllegalArgumentException("Data este din viitor.");
        this.dataAngajarii = dataAng;
    }

    public double getBacsis(){
        return bacsis;
    }
    public void setBacsis(double bacsis){
        this.bacsis = bacsis;
    }
}
