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
        //tri.sort(Objet.parRapport());
        triRapideRec(tri, 0, tri.size() - 1);

        //Ajout dans le sac
        Iterator<Objet> itTri = tri.iterator();
        while(itTri.hasNext() && sac.getPoids() < sac.getPoidsMax()) {
            sac.ajouter(itTri.next());
        }
        //Sac trié!
    }

    /**
     * Implémentation de l'algorithme de tri rapide Quicksort par récursivité
     * pour trier une liste d'Objets par rapport (décroissant)
     *
     * @param objets la liste d'objets à trier
     * @param premier l'indice minimal
     * @param dernier l'indice maximal
     */
    private void triRapideRec(List<Objet> objets, int premier, int dernier) {
        if(premier < dernier) {
            int pivot = (dernier - premier) / 2 + premier;
            pivot = repartir(objets, premier, dernier, pivot);
            triRapideRec(objets, premier, pivot - 1);
            triRapideRec(objets, pivot + 1, dernier);
        }
    }

    /**
     * Permet de répartir les objets autour du pivot
     *
     * @param objets la liste d'objets à répartir
     * @param premier l'indice minimal
     * @param dernier l'indice maximal
     * @param pivot l'indice du pivot
     */
    private int repartir(List<Objet> objets, int premier, int dernier, int pivot) {
        Collections.swap(objets, pivot, dernier);
        int i = premier;
        for (int j = premier; j < dernier; ++j) {
            if(objets.get(j).getRapport() >= objets.get(dernier).getRapport()) {
                Collections.swap(objets, j, i);
                i++;
            }
        }
        Collections.swap(objets, dernier, i);
        return i;
    }
}