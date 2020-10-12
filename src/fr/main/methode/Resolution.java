package fr.main.methode;
import fr.main.sacADos.*;

import java.util.List;

public abstract class Resolution {
    protected List<Objet> objets;

    protected Resolution(List<Objet> objets) {
        this.objets = objets;
    }

    //à implémenter
    public abstract void résoudre(SacADos sac);

}