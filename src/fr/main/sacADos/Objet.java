package fr.main.sacADos;

import java.util.Comparator;

/**
 * La classe Objet représente un objet
 * Celui-ci est composé d'un libellé, d'un poid et d'une valeur
 *
 * @author  Jules Doumèche, Gwénolé Martin
 * @version 1.0
 * @since   2020-10
 */
public class Objet {
    private static final int MAX_LIB_SIZE = 30;
    private final String libelle;
    private final double poid, valeur, rapport;

    /**
     * Constructeur pour instancier un Objet
     *
     * @param libelle le libellé
     * @param poid le poid
     * @param valeur le poid
     */
    public Objet(String libelle, double poid, double valeur){
        //Si le libellé dépasse la taille maximum, on ne prend que les premiers caractères
        if (libelle.length() <= 30)
            this.libelle = libelle;
        else
            this.libelle = libelle.substring(0,MAX_LIB_SIZE);
        this.valeur = valeur;
        this.poid = poid;
        this.rapport = valeur/poid;
    }

    /**
     * Renvoie le libellé de l'objet
     *
     * @return String libelle
     */
    public String getLibelle(){
        return this.libelle;
    }

    /**
     * Renvoie le poid de l'objet
     *
     * @return double poid
     */
    public double getPoid(){
        return this.poid;
    }

    /**
     * Renvoie la valeur de l'objet
     *
     * @return double valeur
     */
    public double getValeur(){
        return this.valeur;
    }

    /**
     * Renvoie le rapport valeur/poid de l'objet
     *
     * @return double rapport
     */
    public double getRapport(){
        return this.rapport;
    }

    /**
     * Représentation en chaîne du caractère de l'objet
     * "Libellé [poids= ?, prix= ?]"
     *
     * @return String objet
     */
    @Override
    public String toString() {
        return libelle + " [" +
                "poids= " + poid +
                ", prix= " + valeur +
                ']';
    }

    /**
     * Permet d'obtenir le comparator d'objet qui permet de trier par ordre décroissant du rapport d'une liste d'objets
     *
     * @return Comparator<Objet> comparator
     */
    public static Comparator<Objet> parRapport() {
        return new Comparator<Objet>() {
            public int compare(Objet o1, Objet o2) {
                return Double.compare(o2.getRapport(), o1.getRapport());
            }
        };
    }
}
