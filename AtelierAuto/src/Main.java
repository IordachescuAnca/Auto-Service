import Angajati.Angajat;
import Angajati.Asistent;
import Angajati.Director;
import Angajati.Mecanic;
import Masini.Autobuz;
import Masini.Camion;
import Masini.Masina;
import Masini.Standard;

import java.time.DateTimeException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.zip.DataFormatException;

public class Main {
    public static void main(String[]args) throws InterruptedException {
        Atelier atelier = new Atelier();
        while(true){
            System.out.println("Meniu");
            System.out.println("1. Angajati");
            System.out.println("2. Masini");
            System.out.println("3. Exit");
            Scanner sc = new Scanner(System.in);

            int alegere = sc.nextInt();
            if(alegere == 1){
                while(true){
                    System.out.println("Meniu angajati");
                    System.out.println("1. Adauga angajat");
                    System.out.println("2. Sterge angajat");
                    System.out.println("3. Afiseaza angajatii");
                    System.out.println("4. Editeaza angajat");
                    System.out.println("5. Calculeaza salariu");
                    System.out.println("6. Vezi bacsisul angajatilor");
                    System.out.println("7. Exit");
                    int alegereAngajat = sc.nextInt();

                    if(alegereAngajat == 1){
                        Scanner sc1 = new Scanner(System.in);
                        System.out.println("Introduce date despre angajat");
                        System.out.println("Introduce nume");
                        String nume = sc1.nextLine();
                        System.out.println("Introduce prenume");
                        String prenume = sc1.nextLine();
                        System.out.println("Introduce data nasterii. Format DD/MM/YYYY");
                        String dataNastere = sc1.nextLine();
                        System.out.println("Introduce data angajarii. Format DD/MM/YYYY");
                        String dataAngajare = sc1.nextLine();
                        System.out.println("Introduce tipul angajatului: Asistent, Director sau Mecanic");
                        String tip = sc1.nextLine();
                        if(tip.toLowerCase().compareTo("asistent") == 0){
                            try{
                                Angajat angajatnou = new Asistent(nume, prenume, dataNastere, dataAngajare);
                                atelier.adaugaAngajat(angajatnou);
                            }
                            catch(IllegalArgumentException| DateTimeException exceptie){
                                System.out.println(exceptie.getMessage());
                            }
                        }
                        else if(tip.toLowerCase().compareTo("mecanic") == 0){
                            try{
                                Angajat angajatnou = new Mecanic(nume, prenume, dataNastere, dataAngajare);
                                atelier.adaugaAngajat(angajatnou);
                            }
                            catch(IllegalArgumentException| DateTimeException exceptie){
                                System.out.println(exceptie.getMessage());
                            }
                        }
                        else if(tip.toLowerCase().compareTo("director") == 0){
                            try{
                                Angajat angajatnou = new Director(nume, prenume, dataNastere, dataAngajare);
                                atelier.adaugaAngajat(angajatnou);
                            }
                            catch(IllegalArgumentException| DateTimeException exceptie){
                                System.out.println(exceptie.getMessage());
                            }
                        }
                        else{
                            System.out.println("Nu exista acest tip de angajat");
                        }
                    }
                    if(alegereAngajat == 2){
                        while(true){
                            System.out.println("Introduce ID angajat");
                            int ID = sc.nextInt();
                            if(atelier.verificaExistentaAngajat(ID) >= 0){
                                atelier.stergeAngajat(ID);
                                break;
                            }
                            else{
                                System.out.println("Nu exista angajat cu acest ID.");
                                TimeUnit.SECONDS.sleep(1);
                            }
                            if(atelier.esteVidaListaAngajati()){
                                System.out.println("Lista de angajati este vida.");
                                break;
                            }
                        }
                    }
                    if(alegereAngajat == 3){
                        atelier.afisatiAngajati();
                    }
                    if(alegereAngajat == 4){
                        while(true){
                            System.out.println("Introduce ID angajat");
                            int ID = sc.nextInt();
                            if(atelier.verificaExistentaAngajat(ID) >= 0){
                                atelier.editeazaAngajat(ID);
                                break;
                            }
                            else{
                                System.out.println("Nu exista angajat cu acest ID.");
                                TimeUnit.SECONDS.sleep(1);
                            }
                            if(atelier.esteVidaListaAngajati()){
                                System.out.println("Lista de angajati este vida.");
                                break;
                            }
                        }
                    }
                    if(alegereAngajat == 5){
                        while(true){
                            System.out.println("Introduce ID angajat");
                            int ID = sc.nextInt();
                            if(atelier.verificaExistentaAngajat(ID) >= 0){
                                atelier.calculareSalariu(ID);
                                break;
                            }
                            else{
                                System.out.println("Nu exista angajat cu acest ID.");
                                TimeUnit.SECONDS.sleep(1);
                            }
                            if(atelier.esteVidaListaAngajati()){
                                System.out.println("Lista de angajati este vida.");
                                break;
                            }
                        }
                    }
                    if(alegereAngajat == 6) atelier.bacsis();
                    if(alegereAngajat == 7) break;
                }
            }

            if(alegere == 2){
                if(atelier.esteVidaListaAngajati()){
                    System.out.println("Lista de angajati este vida. Trebuie sa existe cel putin un angajat ca sa fie atelierul deschis.");
                }
                else{
                    while(true) {
                        atelier.eliminaMasini();
                        System.out.println("Meniu masini");
                        System.out.println("1. Adauga masina");
                        System.out.println("2. Exit");
                        int alegereMasina = sc.nextInt();
                        if (alegereMasina == 1) {
                            System.out.println("Doriti un anumit angajat? Daca da, scrieti ID-ul acestuia. Altfel, scrieti nu");
                            Scanner sc1 = new Scanner(System.in);
                            String dateIntrare = sc1.nextLine();
                            int ID = -1;
                            try {
                                ID = Integer.parseInt(dateIntrare);
                            } catch (NumberFormatException exceptie) {
                                if (dateIntrare.toLowerCase().compareTo("nu") != 0) {
                                    System.out.println("Input invalid");
                                    break;
                                }
                            }

                            System.out.println("Introduce tipul masinii: autobuz, camion, standard");
                            String tipMasina = sc1.nextLine();
                            if (tipMasina.toLowerCase().compareTo("autobuz") == 0 || tipMasina.toLowerCase().compareTo("camion") == 0
                                    || tipMasina.toLowerCase().compareTo("standard") == 0) {
                                System.out.println("Are motor Diesel? Raspuns: da/nu");
                                String motor = sc1.nextLine();
                                System.out.println("Numar kilometri?");
                                int numarKilometri = sc1.nextInt();
                                System.out.println("An vechime?");
                                int an = sc1.nextInt();
                                if (motor.toLowerCase().compareTo("da") == 0 || motor.toLowerCase().compareTo("nu") == 0) {
                                    if(tipMasina.toLowerCase().compareTo("camion") == 0){
                                        System.out.println("Tonaj?");
                                        double tonaj = sc.nextDouble();
                                        Masina nouaMasina = new Camion(numarKilometri, an, motor, tonaj);
                                        if(dateIntrare.toLowerCase().compareTo("nu") == 0){
                                            atelier.adaugaMasina(nouaMasina, tipMasina);
                                        }
                                        else {
                                            if(atelier.verificaExistentaAngajat(ID) >= 0) {
                                                if(atelier.verificaDisponibilitateAngajat(ID, tipMasina)) {
                                                    atelier.adaugaMasina(nouaMasina, ID);
                                                    System.out.println("Camion in curs de reparare la angajatul cu ID-ul " + ID);
                                                }
                                                else{
                                                    System.out.println("Nu mai sunt locuri la acest angajat.Doriti sa fiti repartizat la alt angajat? da/nu");
                                                    sc.nextLine();
                                                    String raspuns = sc.nextLine();
                                                    if(raspuns.toLowerCase().compareTo("da") == 0) {
                                                        atelier.adaugaMasina(nouaMasina, tipMasina);
                                                        System.out.println("Camion in curs de reparare");
                                                    }
                                                    else{
                                                        System.out.println("Doriti sa fiti pus in coada de asteptare?");
                                                        String raspundIntr = sc.nextLine();
                                                        if(raspuns.toLowerCase().compareTo("da") == 0){
                                                            atelier.adaugaInCoada(nouaMasina, ID);
                                                            System.out.println("Pus in coada de asteptare");
                                                        }
                                                        else{
                                                            System.out.println("Ati refuzat toate posibilitatile.");
                                                        }
                                                    }
                                                }
                                            }
                                            else{
                                                System.out.println("Acest angajat nu exista.");
                                            }
                                        }
                                    }
                                    else if(tipMasina.toLowerCase().compareTo("standard") == 0){
                                        System.out.println("Transmisie? automat/manual");
                                        sc.nextLine();
                                        String transmisie = sc.nextLine();
                                        Masina nouaMasina = new Standard(numarKilometri, an, motor, transmisie);
                                        if(dateIntrare.toLowerCase().compareTo("nu") == 0){
                                            atelier.adaugaMasina(nouaMasina, tipMasina);
                                        }
                                        else {
                                            if(atelier.verificaExistentaAngajat(ID) >= 0) {
                                                if(atelier.verificaDisponibilitateAngajat(ID, tipMasina)) {
                                                    atelier.adaugaMasina(nouaMasina, ID);
                                                    System.out.println("Masina standard in curs de reparare la angajatul cu ID-ul " + ID);
                                                }
                                                else{
                                                    System.out.println("Nu mai sunt locuri la acest angajat.Doriti sa fiti repartizat la alt angajat? da/nu");
                                                    sc.nextLine();
                                                    String raspuns = sc.nextLine();
                                                    if(raspuns.toLowerCase().compareTo("da") == 0) {
                                                        atelier.adaugaMasina(nouaMasina, tipMasina);
                                                        System.out.println("Masina standard in curs de reparare.");
                                                    }
                                                    else{
                                                        System.out.println("Doriti sa fiti pus in coada de asteptare?");
                                                        String raspundIntr = sc.nextLine();
                                                        if(raspuns.toLowerCase().compareTo("da") == 0){
                                                            atelier.adaugaInCoada(nouaMasina, ID);
                                                            System.out.println("Pus in coada de asteptare");
                                                        }
                                                        else{
                                                            System.out.println("Ati refuzat toate posibilitatile.");
                                                        }
                                                    }
                                                }
                                            }
                                            else{
                                                System.out.println("Acest angajat nu exista.");
                                            }
                                        }
                                    }
                                    else if(tipMasina.toLowerCase().compareTo("autobuz") == 0){
                                        System.out.println("Numar locuri?");
                                        int locuri = sc.nextInt();
                                        Masina nouaMasina = new Autobuz(numarKilometri, an, motor, locuri);
                                        if(dateIntrare.toLowerCase().compareTo("nu") == 0){
                                            atelier.adaugaMasina(nouaMasina, tipMasina);
                                        }
                                        else {
                                            if(atelier.verificaExistentaAngajat(ID) >= 0) {
                                                if(atelier.verificaDisponibilitateAngajat(ID, tipMasina)) {
                                                    atelier.adaugaMasina(nouaMasina, ID);
                                                    System.out.println("Autobuz in curs de reparare la angajatul cu ID-ul " + ID);
                                                }
                                                else{
                                                    System.out.println("Nu mai sunt locuri la acest angajat.Doriti sa fiti repartizat la alt angajat? da/nu");
                                                    sc.nextLine();
                                                    String raspuns = sc.nextLine();
                                                    if(raspuns.toLowerCase().compareTo("da") == 0) {
                                                        atelier.adaugaMasina(nouaMasina, tipMasina);
                                                    }
                                                    else{
                                                        System.out.println("Doriti sa fiti pus in coada de asteptare?");
                                                        String raspundIntr = sc.nextLine();
                                                        if(raspuns.toLowerCase().compareTo("da") == 0){
                                                            atelier.adaugaInCoada(nouaMasina, ID);
                                                            System.out.println("Pus in coada de asteptare");
                                                        }
                                                        else{
                                                            System.out.println("Ati refuzat toate posibilitatile.");
                                                        }
                                                    }
                                                }
                                            }
                                            else{
                                                System.out.println("Acest angajat nu exista.");
                                            }
                                        }
                                    }
                                }   else {
                                    System.out.println("Nu v-ati pronunat in legatura motorului Diesel");
                                    }
                            }

                        }
                        if (alegereMasina == 2) break;
                        atelier.afiseazaMasiniDinCoada();
                        TimeUnit.SECONDS.sleep(2);
                    }
                }
            }
            if(alegere == 3){
                System.out.println("La revedere!");
                break;
            }
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
