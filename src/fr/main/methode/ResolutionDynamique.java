package fr.main.methode;

import fr.main.Appli;
import fr.main.sacADos.*;
import java.util.List;

public class ResolutionDynamique extends Resolution {
    private double[][] table;

    public ResolutionDynamique(List<Objet> objets) {
        super(objets);
    }

    @Override
    public void résoudre(SacADos sac) {
        //On crée une matrice de taille NbObjets * PoidMax sac
        int NB_OBJETS = super.objets.size();
        int PoidsMaxInt = (int)(sac.getPoidsMax() * Math.pow(10.0, Appli.PRECISION));
        table = new double[NB_OBJETS][PoidsMaxInt + 1];

        //On rempli d'abord la première ligne
        for (int j = 0; j <= PoidsMaxInt; j++) {
            if (super.objets.get(0).getPoids() * Math.pow(10.0, Appli.PRECISION) > j)
                this.table[0][j] = 0;
            else
                this.table[0][j] = super.objets.get(0).getPrix();
        }

        //On rempli toutes les autres lignes
        for(int i = 1; i < NB_OBJETS; ++i) {
            for (int j = 0; j <= PoidsMaxInt; ++j)
                if(super.objets.get(i).getPoids() * Math.pow(10.0, Appli.PRECISION) > j)
                    this.table[i][j] = this.table[i - 1][j];
                else
                    this.table[i][j] = Math.max(this.table[i - 1][j],
                            (this.table[i-1][(int)((j - (super.objets.get(i).getPoids() * Math.pow(10.0, Appli.PRECISION))))]
                            + super.objets.get(i).getPrix()));
        }

        //DEBUG : affichage tableau
/*        System.out.print("  i\\j  ");
        for (int j = 0; j <= PoidsMaxInt; ++j)
            System.out.format("%-7.2f", j / Math.pow(10.0, Appli.PRECISION));
        System.out.println();
        System.out.print("-------");
        for (int j = 0; j <= PoidsMaxInt; ++j)
            System.out.format("%-7s", "-------");
        System.out.println();
        for(int i = 0; i < NB_OBJETS; ++i) {
            System.out.format("%-7.2s", super.objets.get(i).getLibellé());
            for (int j = 0; j <= PoidsMaxInt; ++j) {
                System.out.format("%-7.2f", this.table[i][j]);
            }
            System.out.println();
        }*/

        //Remontée du tableau
        int i = NB_OBJETS - 1;
        int j = PoidsMaxInt;
        while(this.table[i][j] == this.table[i][j-1])
            --j;
        while(j > 0) {
            while (i > 0 && this.table[i][j] == this.table[i - 1][j])
                --i;
            j = j - (int) (super.objets.get(i).getPoids() * Math.pow(10.0, Appli.PRECISION));
            if (j >= 0)
                sac.ajouter(super.objets.get(i));
            --i;
        }
    }
}
