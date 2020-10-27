package fr.main.sacADos.methode;

import fr.main.sacADos.*;
import java.util.*;

/**
 * La classe ResolutionGloutonne permet de résoudre le problème du sac à dos grâce à
 * l'algorithme glouton
 *
 * @author  Jules Doumèche, Gwénolé Martin
 * @version 1.0
 * @since   2020-10
 */
public class ResolutionGloutonne extends Resolution {

    /**
     * Constructeur pour instancier la classe de résolution
     *
     * @param sac le sac à résoudre
     */
    public ResolutionGloutonne(SacADos sac) {
        super(sac);
    }

    /**
     * Permet de résoudre le sac avec l'algorithme glouton
     */
    @Override
    public void resoudre() {
        //Clone objPossible
        ArrayList<Objet> tri = new ArrayList<>(objets);
        //Tri par rapport
        tri.sort(Objet.parRapport());
        //Ajout dans le sac
        Iterator<Objet> itTri = tri.iterator();
        while(itTri.hasNext() && sac.getPoids() < sac.getPoidsMax()) {
            sac.ajouter(itTri.next());
        }
        //Sac trié!
    }
}