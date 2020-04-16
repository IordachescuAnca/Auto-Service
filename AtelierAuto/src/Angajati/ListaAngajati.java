package Angajati;

import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.List;

public class ListaAngajati {
    private List<Angajat> angajati;
    public ListaAngajati(){
        this.angajati = new ArrayList<Angajat>();
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
    public boolean esteVida(){
        return (this.angajati.size() == 0);
    }
    public void afisatiAngajati(){
        if(this.esteVida()){
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

}
