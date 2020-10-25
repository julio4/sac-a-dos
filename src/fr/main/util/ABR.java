package fr.main.util;

import fr.main.sacADos.Objet;

import java.util.ArrayList;

public class ABR {
    private ABR filsGauche, filsDroit, parent;
    private int profondeur;
    private double valeur;
    private double poid;
    private int indexObjet;

    public ABR(){
        this.parent = this; //le parent est lui même --> racine
        this.profondeur = 0;
        this.poid = this.valeur = 0.0;
    }

    public ABR(ABR parent, double valeur, double poid, int index){
        this.parent = parent;
        this.profondeur = parent.profondeur + 1;
        this.poid = poid;
        this.valeur = valeur;
        this.indexObjet = index;
    }

    public void setFilsGauche(Objet o, int index) {
        this.filsGauche = new ABR(this, this.valeur + o.getPrix(), this.poid + o.getPoids(), index);
    }

    public void setFilsDroit() {
        this.filsDroit = new ABR(this, this.valeur, this.poid, -1);
    }

    public ABR getFilsGauche() {
        return this.filsGauche;
    }

    public ABR getFilsDroit() {
        return this.filsDroit;
    }

    public double getValeur() {
        return this.valeur;
    }

    public double getPoid() {
        return this.poid;
    }

    public int getIndexObjet() {
        return this.indexObjet;
    }

    public boolean estRacine() {
        return this.profondeur == 0;
    }

    public int getProfondeur() {
        return this.profondeur;
    }

    public ABR getParent() {
        return this.parent;
    }
}