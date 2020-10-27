package fr.main;

import java.io.IOException;

import fr.main.sacADos.methode.*;
import fr.main.sacADos.*;
import fr.main.util.*;

/**
 * La classe Appli gère le bon fonctionnement du programme
 * Des utilitaires sont implémenté dans GestionAppli
 *
 * @author  Jules Doumèche, Gwénolé Martin
 * @version 1.0
 * @since   2020-10
 * @see GestionAppli
 */
public class Appli {
    public static final int PRECISION = 2; //Utilisée par l'algo PSE
    public static Double POIDS_MAX;
    public static String CHEMIN;
    public static Methodes METHODE;

    public static void main(String[] args) {
        GestionAppli app = new GestionAppli();

        //Validation des Arguments
        app.start(args);

        //Création d'un sac à dos
        SacADos sac = null;
        try {
            sac = new SacADos(CHEMIN, POIDS_MAX);
        } catch (IOException e) {
            app.affErreur(e);
            System.exit(1);
        }

        //Résolution
        sac.resoudre(METHODE);

        //Résultat
        app.afficher(sac);
    }
}
