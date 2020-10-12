package fr.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import fr.main.methode.*;
import fr.main.sacADos.*;
import fr.main.util.GestionFichier;

public class Appli {
    public static Double POIDS_MAX;
    public static String CHEMIN;
    public static String METHODE;

    public static final String DATA_PATH = "data/";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    private static void validerArgs(String[] args) throws IllegalArgumentException {
        if (args.length < 3) {
            String msg = "LANCEMENT DU PROGRAMME\nParamètres : ";
            switch(args.length){
                case 0:
                    msg += "chemin ";
                case 1:
                    msg += "poids-maximal ";
                case 2:
                    msg += "methode\nSyntaxe: ./resoudre-sac-a-dos chemin poids-maximal methode;";
                    break;
                default:
                    break;
            }
            throw new IllegalArgumentException(msg);
        }
        else {
            for (int i = 0; i < args.length; i++) {
                switch (i) {
                    case 0:
                        File cheminValide = new File(DATA_PATH + args[i]);
                        if (!cheminValide.exists()) {
                            cheminValide = new File(args[i]);
                            if (!cheminValide.exists()) {
                                throw new IllegalArgumentException("Chemin invalide, inexistant ou inaccessible: ("
                                        + DATA_PATH + ")" + cheminValide.getPath());
                            }
                            CHEMIN = args[i];
                        }
                        CHEMIN = DATA_PATH + args[i];
                        break;
                    case 1:
                        try {
                            POIDS_MAX = Double.parseDouble(args[i]);
                        } catch (NumberFormatException PoidsNonNumerique) {
                            throw new IllegalArgumentException("Valeur du paramètre du poid maximal invalide: " + args[i]);
                        }
                        break;
                    case 2:
                        //A IMPLEMENTER : CHECK DANS ENUM METHODES
                        if (args[i].equalsIgnoreCase("glouton") ||
                                args[i].equalsIgnoreCase("dynamique") ||
                                args[i].equalsIgnoreCase("pse")) {
                            METHODE = args[i];
                        } else {
                            throw new IllegalArgumentException("Méthode incconue ou non implémentée: " + args[i]);
                        }
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private static void affErreur(Exception e){
        System.out.println(ANSI_RED + "Erreur: " + e.getMessage() + ANSI_RESET);
        System.exit(1);
    }

    public static void main(String[] args) throws FileNotFoundException, GestionFichier.SaisieErroneeException {
        //Validation des Arguments
        try {
            validerArgs(args);
        }
        catch(IllegalArgumentException e) {
            affErreur(e);
        }
        System.out.println(ANSI_GREEN + "Arguments:" + "\nChemin: " + CHEMIN +
                "\nPoid max: " + POIDS_MAX + "\nMéthode: " + METHODE + ANSI_RESET);

        //Création d'un sac à dos
        SacADos sac = null;
        sac = new SacADos(POIDS_MAX);

        //Lecture et création de la liste des objets à mettre dans le sac
        ArrayList<Objet> objetsPossibles = GestionFichier.lireListeObj(CHEMIN);

        //Création de la méthode de résolution
        Resolution methodeRes = null;
        switch(METHODE) {
            case "glouton":
                methodeRes = new ResolutionGloutonne(objetsPossibles);
                break;
            case "dynamique":
                methodeRes = new ResolutionDynamique(objetsPossibles);
                break;
            case "pse":
                methodeRes = new ResolutionPse();
                break;
            default:
                //ERREUR
                break;
        }

        //Résolution
        methodeRes.résoudre(sac);

        //Résultat
        System.out.println(ANSI_GREEN + "SUCCES: " + sac + ANSI_RESET);
    }
}
