package fr.main.sacADos;

import java.util.ArrayList;
import java.util.Iterator;

public class SacADos implements Iterable<Objet> {
    private Double poidsMax;
    private Double poids;
    private ArrayList<Objet> objDansLeSac = new ArrayList<>();

    //SacADos vide
    public SacADos() {
        this.poidsMax = this.poids = 0.0;
    }

    public SacADos(Double poidsMax){
        this.poids = 0.0;
        this.poidsMax = poidsMax;
    }

    public boolean ajouter(Objet objet) {
        if(objet.getPoids() + this.poids < this.poidsMax) {
            this.objDansLeSac.add(objet);
            this.poids += objet.getPoids();
            return true;
        }
        return false; // L'objet est trop lourd pour le sac
    }

    public double getPoidsMax() {
        return this.poidsMax;
    }

    public double getPoids() {
        return this.poids;
    }

    public double getValeur() {
        Double valeur = 0.0;
        for(Objet o : this) {
            valeur += o.getPrix();
        }
        return valeur;
    }

    @Override
    public Iterator<Objet> iterator() {
        return this.objDansLeSac.iterator();
    }

    @Override
    public String toString(){
        return "Le contenu du sac à dos a une valeur de " + getValeur() + " pour un poids total de " + this.poids + ". \n" +
                "Sa capacité maximale est de " + this.poidsMax + " (rempli à " +
                String.format("%,.2f", 100*this.poids/this.poidsMax) + "%)!";
    }
}
