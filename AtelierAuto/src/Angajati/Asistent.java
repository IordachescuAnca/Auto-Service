package Angajati;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;

public class Asistent extends Angajat{
    public Asistent(String nume, String prenume, String dataNastere, String dataAngajarii){
        super(nume, prenume, dataNastere, dataAngajarii);
    }

    public double calculeazaSalariu(){
        LocalDate today = LocalDate.now();
        Period vechime = Period.between(this.dataAngajarii, today);
        return 1.5 * vechime.getYears() * 1000;
    }

    public static void main(String []args){
        try {
            Angajat a = new Asistent("aaabc", "cde", "05/04/2002", "01/04/2020");
        }
        catch(DateTimeException | IllegalArgumentException a){
            System.out.println(a.getMessage());
        }
    }
}