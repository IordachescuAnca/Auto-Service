import Angajati.*;
import Masini.Autobuz;
import Masini.Camion;
import Masini.Masina;
import Masini.Standard;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Atelier {
    private List<Angajat> angajati;
    private List<Masina> masini;
    private HashMap<Integer, List<Masina>> masiniocupate;
    public Atelier(){
        this.angajati = new ArrayList<Angajat>();
        this.masini = new ArrayList<Masina>();
        this.masiniocupate = new HashMap<Integer, List<Masina>>();
    }

    public void adaugaAngajat(Angajat angajatNou){
        this.angajati.add(angajatNou);
        System.out.println("Angajat adaugat.");
    }
    public void stergeAngajat(int ID){
        int indice = this.verificaExistentaAngajat(ID);
        if(indice == -1){
            System.out.println("Nu exista angajat cu ID-ul " + ID);
        }
        else{
            this.angajati.remove(this.angajati.get(indice));
            System.out.println("Angajat sters.");
        }
    }
    public boolean esteVidaListaAngajati(){
        return (this.angajati.size() == 0);
    }
    public void afisatiAngajati(){
        if(this.esteVidaListaAngajati()){
            System.out.println("Lista este vida.");
        }
        for(Angajat angajatActual : this.angajati){
            System.out.println(angajatActual.toString());
        }
    }

    public void calculareSalariu(int ID){
        int indice = this.verificaExistentaAngajat(ID);
        if(indice == -1){
            System.out.println("Nu exista angajat cu ID-ul " + ID);
        }
        else{
            System.out.println("Salariul angajatului cu ID-ul " + ID + " este "  + this.angajati.get(indice).calculeazaSalariu());
        }
    }

    public int verificaExistentaAngajat(int ID){
        int indiceAngajat = -1;
        for(int i = 0; i < this.angajati.size(); ++i){
            Angajat angajatActual = this.angajati.get(i);
            if(angajatActual.getID() == ID){
                indiceAngajat = i;
                break;
            }
        }
        return indiceAngajat;
    }

    public void editeazaAngajat(int ID){
        int indice = this.verificaExistentaAngajat(ID);
        if(indice == -1){
            System.out.println("Nu exista angajat cu ID-ul " + ID);
        }
        else{
            Scanner sc = new Scanner(System.in);
            System.out.println("Introduce numele");
            String nume = sc.nextLine();
            System.out.println("Introduce prenumele");
            String prenume = sc.nextLine();
            System.out.println("Introduce data angajarii. Format: DD/MM/YYYY");
            String dataAngajare = sc.nextLine();
            System.out.println("Introduce data nasterii. Format: DD/MM/YYYY");
            String dataNastere = sc.nextLine();
            try {
                this.angajati.get(indice).setNume(nume);
                this.angajati.get(indice).setPrenume(prenume);
                this.angajati.get(indice).setDataAngajarii(dataAngajare);
                this.angajati.get(indice).setDataNastere(dataNastere);
            }
            catch(DateTimeException|IllegalArgumentException exceptie){
                System.out.println(exceptie.getMessage());
            }
        }
    }

    public boolean verificaDisponibilitateAngajat(int ID, String tip){
        int indice = this.verificaExistentaAngajat(ID);
        List<Masina> masiniOcupateAngajat = this.masiniocupate.get(ID);
        if(masiniOcupateAngajat == null){
            masiniOcupateAngajat = new ArrayList<Masina>();
            this.masiniocupate.put(ID, masiniOcupateAngajat);
        }
        int contorMasiniNormale = 0;
        int contorAutobuzCamion = 0;
        for(int i = 0; i < masiniOcupateAngajat.size(); i++){
            if(masiniOcupateAngajat.get(i).getClass() == Standard.class){
                contorMasiniNormale++;
            }
            else{
                contorAutobuzCamion++;
            }
        }
        if(tip.toLowerCase().compareTo("standard") == 0) {
            contorMasiniNormale++;
            if(contorMasiniNormale > 3) return false;
        }
        if(tip.toLowerCase().compareTo("autobuz") == 0 || tip.toLowerCase().compareTo("camion") == 0){
            contorAutobuzCamion++;
            if(contorAutobuzCamion > 1) return false;
        }
        return true;
    }
    public void adaugaMasina(Masina masinaNoua, int ID){
        int indice = this.verificaExistentaAngajat(ID);
        if(indice == -1){
            System.out.println("Nu exista angajat cu ID-ul " + ID);
            return;
        }
        List<Masina> masiniOcupateAngajat = this.masiniocupate.get(ID);
        if(masiniOcupateAngajat == null){
            masiniOcupateAngajat = new ArrayList<>();
            this.masiniocupate.put(ID, masiniOcupateAngajat);
        }
        masinaNoua.setTimpFinal(LocalDateTime.now().plusMinutes(1));
        masiniOcupateAngajat.add(masinaNoua);
        for(int i = 0; i < this.angajati.size(); i++){
            if(this.angajati.get(i).getID() == ID){
                Angajat angajatActual = this.angajati.get(i);
                angajatActual.setBacsis(angajatActual.getBacsis()+0.01*masinaNoua.calculeazaPolitaAsigurareDiscount());
            }
        }
    }

    public int esteDisponibilOricareAngajat(String tipMasina){
        for(int i = 0; i < this.angajati.size(); i++){
            if(this.verificaDisponibilitateAngajat(this.angajati.get(i).getID(), tipMasina)){
                return i;
            }
        }
        return -1;
    }

    public void adaugaMasina(Masina masinaNoua, String tip){
        int indice = this.esteDisponibilOricareAngajat(tip);
        if(indice == -1){
            System.out.println("Niciun angajat nu este liber. Doriti sa fiti pus in coada de asteptare? da/nu");
            Scanner sc = new Scanner(System.in);
            String decizie = sc.nextLine();
            if(decizie.compareTo("da") == 0){
                this.adaugaInCoada(masinaNoua, -1);
            }
            else{
                System.out.println("Nu mai aveti nicio optiune.");
            }
        }
        else{
            int ID = this.angajati.get(indice).getID();
            this.adaugaMasina(masinaNoua, ID);
            System.out.println("Masina in curs de reparare");
        }
    }

    public int coada(){
        return this.masini.size();
    }

    public void adaugaInCoada(Masina masinaNoua, int ID){
        masinaNoua.setPreferinta(ID);
        this.masini.add(masinaNoua);
    }

    public void afiseazaMasiniDinCoada(){
        System.out.println("Masini din coada: ");
        for(Masina masinaActuala : this.masini){
            System.out.println(masinaActuala.toString());
        }
    }

    public void eliminaMasini(){
        for(Integer ID: this.masiniocupate.keySet()){
            List<Masina> list = this.masiniocupate.get(ID);
            HashMap<Integer, Boolean> viz = new HashMap<Integer, Boolean>();
            for(int i = 0; i < list.size(); i++) {
                Masina masinaActuala = list.get(i);
                if (masinaActuala.getTimpFinal().isBefore(LocalDateTime.now())) {
                    viz.put(i, true);
                }
            }
            for(Integer i: viz.keySet()){
                this.masiniocupate.remove(i);
            }
        }
    }

    public void bacsis(){
        for(Angajat a: this.angajati){
            System.out.println(a.toString() + "\nBacsis: " + a.getBacsis() + "\n");
        }
    }
}
