package fr.main.util;

import fr.main.Appli;
import fr.main.sacADos.Objet;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GestionFichier {

    public static ArrayList<Objet> lireListeObj(String chemin) throws IOException {
        //try-with-resources pour s'assurer que le bf se ferme bien même en cas d'exceptions
        try (BufferedReader bf = new BufferedReader(new FileReader(chemin))) {
            String ligne;
            int nbLignes = 0;
            ArrayList<Objet> objetsPossibles = new ArrayList<>();
            while ((ligne = bf.readLine()) != null) {
                nbLignes++;
                String[] objProp = ligne.split(";");
                if (objProp.length != 3) {
                    throw new IOException("Syntaxe invalide lors de la lecture du fichier ligne " + nbLignes
                            + "\nSyntaxe: Nom Objet ; Poid ; Prix\nExemple: Lampe ; 2.0 ; 30.0");
                }
                for (int i = 0; i < 3; ++i)
                    objProp[i] = objProp[i].trim().replaceAll("\n ", "");
                objetsPossibles.add(new Objet(objProp[0],
                        (Double.parseDouble(objProp[1]) * Math.pow(10.0, Appli.PRECISION) / Math.pow(10.0, Appli.PRECISION)),
                        (Double.parseDouble(objProp[2]) * Math.pow(10.0, Appli.PRECISION) / Math.pow(10.0, Appli.PRECISION))
                ));
            }
            return objetsPossibles;
        } catch (FileNotFoundException e) {
            throw new IOException("Ouverture du fichier impossible");
        } catch (NumberFormatException PoidsNonNumerique) {
            throw new IOException("Erreur de lecture du nombre, les virgules se notent '.'.\n"
                    + "Syntaxe: Nom Objet ; Poid ; Prix\nExemple: Lampe ; 2.0 ; 30.0\n"
                    + PoidsNonNumerique.getMessage());
        }
    }
}
