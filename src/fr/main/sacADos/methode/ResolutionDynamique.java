package fr.main.sacADos.methode;

import fr.main.Appli;
import fr.main.sacADos.*;

/**
 * La classe ResolutionDynamique permet de résoudre le problème du sac à dos grâce à
 * la programation dynamique
 *
 * @author  Jules Doumèche, Gwénolé Martin
 * @version 1.0
 * @since   2020-10
 */
public class ResolutionDynamique extends Resolution {

    /**
     * Constructeur pour instancier la classe de résolution
     *
     * @param sac le sac à résoudre
     */
    public ResolutionDynamique(SacADos sac) {
        super(sac);
    }

    /**
     * Permet de résoudre le sac avec la programmation dynamique
     */
    @Override
    public void resoudre() {
        //On crée une matrice de taille NbObjets * PoidMax sac
        int NB_OBJETS = objets.size();
        int PoidsMaxInt = (int)(sac.getPoidsMax() * Math.pow(10.0, Appli.PRECISION));
        double[][] table = new double[NB_OBJETS][PoidsMaxInt + 1];

        //On rempli d'abord la première ligne
        for (int j = 0; j <= PoidsMaxInt; j++) {
            if (objets.get(0).getPoids() * Math.pow(10.0, Appli.PRECISION) > j)
                table[0][j] = 0;
            else
                table[0][j] = objets.get(0).getValeur();
        }

        //On rempli toutes les autres lignes
        for(int i = 1; i < NB_OBJETS; ++i) {
            for (int j = 0; j <= PoidsMaxInt; ++j)
                if(objets.get(i).getPoids() * Math.pow(10.0, Appli.PRECISION) > j)
                    table[i][j] = table[i - 1][j];
                else
                    table[i][j] = Math.max(table[i - 1][j],
                            (table[i-1][(int)((j - (objets.get(i).getPoids() * Math.pow(10.0, Appli.PRECISION))))]
                            + objets.get(i).getValeur()));
        }

        //DEBUG : affichage tableau
        /*
        System.out.print("  i\\j  ");
        for (int j = 0; j <= PoidsMaxInt; ++j)
            System.out.format("%-7.2f", j / Math.pow(10.0, Appli.PRECISION));
        System.out.println();
        System.out.print("-------");
        for (int j = 0; j <= PoidsMaxInt; ++j)
            System.out.format("%-7s", "-------");
        System.out.println();
        for(int i = 0; i < NB_OBJETS; ++i) {
            System.out.format("%-7.2s", objets.get(i).getLibelle());
            for (int j = 0; j <= PoidsMaxInt; ++j) {
                System.out.format("%-7.2f", table[i][j]); //-7.{{ Appli.PRECISION }}f
            }
            System.out.println();
        }*/


        //Remontée du tableau
        int i = NB_OBJETS - 1;
        int j = PoidsMaxInt;
        while(table[i][j] == table[i][j-1])
            --j;
        while(j > 0) {
            while (i > 0 && table[i][j] == table[i - 1][j])
                --i;
            j = j - (int) (objets.get(i).getPoids() * Math.pow(10.0, Appli.PRECISION));
            if (j >= 0)
                sac.ajouter(objets.get(i));
            --i;
        }
    }
}
