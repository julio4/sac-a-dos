package fr.main.util;

import fr.main.sacADos.Objet;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GestionFichier {
    public static ArrayList<Objet> lireListeObj(String chemin) throws SaisieErroneeException, FileNotFoundException {
        try {
            BufferedReader bf = new BufferedReader(new FileReader(chemin));
            String ligne;
            int nbLignes = 0;
            ArrayList<Objet> objetsPossibles = new ArrayList<>();
            while ((ligne = bf.readLine()) != null) {
                nbLignes++;
                String objProp[] = ligne.split(";");
                if(objProp.length != 3) {
                    throw new SaisieErroneeException("Syntaxe invalide lors de la lecture du fichier ligne " + nbLignes
                            + "\nSyntaxe: Nom Objet ; Poid ; Prix\nExemple: Lampe ; 2.0 ; 30.0");
                }
                for(int i = 0 ; i < 3; ++i)
                    objProp[i] = objProp[i].trim().replaceAll("\n ", "");
                objetsPossibles.add(new Objet(objProp[0],
                        Double.valueOf(objProp[1]),
                        Double.valueOf(objProp[2])));
            }
            bf.close();
            return objetsPossibles;
        }
        catch (FileNotFoundException e) {
            throw new FileNotFoundException("Ouverture du fichier impossible");
        }
        catch (NumberFormatException | IOException PoidsNonNumerique) {
            throw new SaisieErroneeException("Erreur de lecture du nombre, les virgules se notent '.'.\n"
                    + "Syntaxe: Nom Objet ; Poid ; Prix\nExemple: Lampe ; 2.0 ; 30.0\n"
                    + PoidsNonNumerique.getMessage());
        }
    }

    public static class SaisieErroneeException extends IOException {
        public SaisieErroneeException() {
            super();
        }
        public SaisieErroneeException(String s) {
            super(s);
        }
    }
}
