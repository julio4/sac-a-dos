package fr.main.methode;

import fr.main.sacADos.*;

import java.util.*;

public class ResolutionGloutonne extends Resolution {

    public ResolutionGloutonne(List<Objet> objets) {
        super(objets);
    }

    @Override
    public void résoudre(SacADos sac) {
        //Clone objPossible
        ArrayList<Objet> tri = new ArrayList<>(super.objets);
        //Tri par rapport
        Collections.sort(tri, Objet.parRapport());
        //Ajout dans le sac
        Iterator<Objet> itTri = tri.iterator();
        while(itTri.hasNext() && sac.getPoids() < sac.getPoidsMax()) {
            sac.ajouter(itTri.next());
        }
        //Sac trié!
    }

}