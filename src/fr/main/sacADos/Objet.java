package fr.main.sacADos;

import java.util.Comparator;

public class Objet {
    private static final int MAX_LIB_SIZE = 30;
    private final String libellé;
    private final double poids, prix, rapport;

    public Objet(String libellé, double prix, double poids){
        //Si le libellé dépasse la taille maximum, on ne prend que les premiers caractères
        if (libellé.length() <= 30) {
            this.libellé = libellé;
        } else {
            this.libellé = libellé.substring(0,MAX_LIB_SIZE);
        }
        this.prix = prix;
        this.poids = poids;
        this.rapport = prix/poids;
    }

    public String getLibellé(){
        return this.libellé;
    }

    public double getPoids(){
        return this.poids;
    }

    public double getPrix(){
        return this.prix;
    }

    public double getRapport(){
        return this.rapport;
    }

    @Override
    public String toString() {
        return libellé + '[' +
                "poids=" + poids +
                ", prix=" + prix +
                ", rapport=" + rapport +
                ']';
    }

    public static Comparator<Objet> parRapport() {
        return new Comparator<Objet>() {
            public int compare(Objet o1, Objet o2) {
                return Double.compare(o2.getRapport(), o1.getRapport());
            }
        };
    }
}
