package fr.main.sacADos;

import fr.main.sacADos.methode.*;
import fr.main.util.GestionFichier;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * La classe SacADos représente un sac à dos dans le contexte du problème du sac à dos
 * Ce dernier comprend un poid maximal, un poid actuel, une liste d'objet dans le sac
 * et une liste d'objet total
 *
 * @author  Jules Doumèche, Gwénolé Martin
 * @version 1.0
 * @since   2020-10
 */
public class SacADos implements Iterable<Objet> {
    /**
     * Poids maximal que peux supporte le sac
     */
    private final double poidsMax;
    /**
     * Poids actuel du sac
     */
    private double poids;
    /**
     * Liste des objets à mettre dans le sac
     */
    private ArrayList<Objet> objetsPossibles = new ArrayList<>();
    /**
     * Permet de résoudre le sac avec l'algorithme glouton
     */
    private ArrayList<Objet> objDansLeSac = new ArrayList<>();

    /**
     * Constructeur pour instancier un sac vide
     */
    public SacADos() {
        this.poidsMax = this.poids = 0.0;
    }

    /**
     * Constructeur pour instancier un sac à dos avec une liste d'objets
     *
     * @param chemin chemin vers le fichier texte contenant la liste d'objets
     * @param poidsMax le poid maximal du sac
     * @throws IOException Le fichier des objets est invalide
     */
    public SacADos(String chemin, Double poidsMax) throws IOException {
        this.poids = 0.0;
        this.poidsMax = poidsMax;
        this.objetsPossibles = GestionFichier.lireListeObj(chemin);
    }

    /**
     * Résoud le sac avec la méthode indiqué
     *
     * @param METHODE la méthode à utiliser
     * @see Methodes
     */
    public void resoudre(Methodes METHODE) {
        Resolution methodeRes = null;
        switch(METHODE) {
            case GLOUTONNE:
                methodeRes = new ResolutionGloutonne(this);
                break;
            case DYNAMIQUE:
                methodeRes = new ResolutionDynamique(this);
                break;
            case PSE:
                methodeRes = new ResolutionPse(this);
                break;
            default:
                //gestion methode non implémentée
        }
        methodeRes.resoudre();
    }

    /**
     * Permet de vider tout les objets dans le sac
     * Utile pour tester plusieurs algorithmes de résolution sur un seul et unique sac
     */
    public void vider() {
        this.poids = 0.0;
        this.objDansLeSac.clear();
    }

    /**
     * Permet d'ajouter un objet dans le sac s'il y a assez de place
     *
     * @param objet l'objet à ajouter dans le sac
     */
    public void ajouter(Objet objet) {
        if(objet.getPoids() + this.poids <= this.poidsMax) {
            this.objDansLeSac.add(objet);
            this.poids += objet.getPoids();
        }
    }

    /**
     * Renvoie le poid maximal autorisé
     *
     * @return double poidMax
     */
    public double getPoidsMax() {
        return this.poidsMax;
    }

    /**
     * Renvoie le poid actuel du sac
     * Celui-ci correspond à la somme du poid de tout les objets contenu dans le sac
     *
     * @return double poid
     */
    public double getPoids() {
        return this.poids;
    }

    /**
     * Renvoie la valeur actuel du sac
     *
     * @return double valeur
     */
    public double getValeur() {
        double valeur = 0;
        for(Objet o : this) {
            valeur += o.getValeur();
        }
        return valeur;
    }

    /**
     * Renvoie la valeur actuel du sac
     *
     * @return double valeur
     */
    public ArrayList<Objet> getObjetsPossibles() {
        return this.objetsPossibles;
    }

    /**
     * Renvoie l'iterator du sac, qui correspond à celui de sa liste d'objet dans le sac
     *
     * @return Iterator iterator
     */
    @Override
    public Iterator<Objet> iterator() {
        return this.objDansLeSac.iterator();
    }

    /**
     * Représentation en chaîne de caractères du sac
     * Contien la valeur totale, le poid, et les objets
     *
     * @return String sac
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Sac à dos\n");
        sb.append("-Valeur totale: ")
                .append(getValeur())
                .append("\n");
        sb.append("-Poid total: ")
                .append(this.poids)
                .append(" (")
                .append(String.format("%,.2f", 100 * this.poids / this.poidsMax))
                .append("%)\n");
        sb.append("-Poid Maximal: ")
                .append(this.poidsMax)
                .append("\n");
        sb.append("-Objets:\n");
        for (Objet objet : this.objDansLeSac) {
            sb.append("  >").append(objet).append("\n");
        }
        return sb.toString();
    }
}
