package fr.main.sacADos;

import java.util.ArrayList;
import java.util.Iterator;

public class SacADos implements Iterable<Objet> {
    private double poidsMax;
    private double poids;
    private ArrayList<Objet> objDansLeSac = new ArrayList<>();

    //SacADos vide
    public SacADos() {
        this.poidsMax = this.poids = 0.0;
    }

    public SacADos(Double poidsMax){
        this.poids = 0.0;
        this.poidsMax = poidsMax;
    }

    public void vider() {
        this.poids = 0.0;
        this.objDansLeSac.clear();
    }

    public boolean ajouter(Objet objet) {
        if(objet.getPoids() + this.poids <= this.poidsMax) {
            this.objDansLeSac.add(objet);
            this.poids += objet.getPoids();
            return true;
        }
        return false;
    }

    public double getPoidsMax() {
        return this.poidsMax;
    }

    public double getPoids() {
        return this.poids;
    }

    public double getValeur() {
        double valeur = 0;
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
        StringBuilder sb = new StringBuilder();
        sb.append("Sac à dos\n");
        sb.append("-Valeur totale: " + getValeur() + "\n");
        sb.append("-Poids total: " + this.poids + " (" + String.format("%,.2f", 100*this.poids/this.poidsMax) + "%)\n");
        sb.append("-Poids Max: " + this.poidsMax + "\n");
        sb.append("-Objets:\n");
        for(int i = 0; i < this.objDansLeSac.size(); ++i) {
            sb.append("  >" + this.objDansLeSac.get(i) + "\n");
        }
        return sb.toString();
    }
}
