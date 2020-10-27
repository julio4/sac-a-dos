package fr.main.sacADos.methode;
import fr.main.sacADos.*;

import java.util.ArrayList;

/**
 * La classe Résolution permet une implémentation plus rapide d'algorithme de résolution
 * Les classes de résolution héritant de celle-ci doivent implémenter la méthode resoudre()
 * Elle comprend quelques méthodes utilitaires pour gérer la résolution d'un sac à dos
 *
 * @author  Jules Doumèche, Gwénolé Martin
 * @version 1.0
 * @since   2020-10
 */
public abstract class Resolution {
    /**
     * Le sac à résoudre
     */
    protected SacADos sac;
    /**
     * L'ensemble d'objets
     */
    ArrayList<Objet> objets;

    /**
     * Constructeur pour instancier une classe de résolution
     *
     * @param sac le sac à résoudre
     */
    protected Resolution(SacADos sac) {
        this.sac = sac;
        this.objets = sac.getObjetsPossibles();
    }

    /**
     * Permet de changer le sac si besoin de résoudre des sac différents
     *
     * @param sac le sac à résoudre
     */
    public void setSac(SacADos sac) {
        this.sac = sac;
    }

    /**
     * Les classes de résolution doivent implémenter une méthode qui permet de résoudre le sac à dos
     */
    //à implémenter
    public abstract void resoudre();

}