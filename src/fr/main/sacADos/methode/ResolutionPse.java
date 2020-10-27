package fr.main.sacADos.methode;

import fr.main.sacADos.*;
import fr.main.sacADos.methode.util.ABR;

/**
 * La classe ResolutionPse permet de résoudre le problème du sac à dos grâce à
 * l'algorithme de procédure par séparation et évaluation (PSE)
 *
 * @author  Jules Doumèche, Gwénolé Martin
 * @version 1.0
 * @since   2020-10
 */
public class ResolutionPse extends ResolutionGloutonne {

    private double minVal;
    private ABR meilleurRes;
    private double poidMax;

    /**
     * Constructeur pour instancier la classe de résolution
     */
    public ResolutionPse(SacADos sac) {
        super(sac);
    }

    /**
     * Permet de résoudre le sac avec l'algorithme de résolution PSE
     */
    @Override
    public void resoudre() {

        //On initialise certaines données
        this.poidMax = sac.getPoidMax();
        double maxVal = this.minVal = 0;

        //La valeur max si l'on met tout les objets dans le sac sans prendre en compte le poids max
        for(Objet o : super.objets)
            maxVal += o.getValeur();

        //Une première borne minimal qui correspond à un résultat réaliste
        //Cette solution est obtenue grâce à l'algo glouton (d'où son héritage)
        super.resoudre();
        for(Objet o : sac)
            this.minVal += o.getValeur();

        //Si l'algo glouton à trouvé une solution parfaite, inutile de refaire l'algo PSE
        if(maxVal != sac.getValeur()) {

            //On crée maintenant la racine de l'arbre
            ABR root = new ABR();
            //On initialise le meilleur chemin
            this.meilleurRes = root;

            //On créer l'abre
            creeArbreRec(0, root, maxVal);

            //On à maintenant la meilleur solution contenu dans this.meilleurRésultat
            //On remonte alors jusqu'à la racine en ajoutant dans le sac les objet
            sac.vider();
            ajouterSolutionRec(this.meilleurRes);
        }
        //Le sac est trié avec la meilleure solution trouvé
    }

    /**
     * Permet de créer l'arbre binaires des solutions possibles récursivement
     *
     * @param index profondeur/objet à construire
     * @param noeudActuel noeud actuel
     * @param maxPossible correspond à la borne supérieure du noeud actuel
     */
    private void creeArbreRec(int index, ABR noeudActuel, double maxPossible) {

        //On ajoute l'objet suivant dans le fils gauche
        noeudActuel.setFilsGauche(super.objets.get(index), index);
        //On crée une copie (on ne rajoute rien) dans le fils droit
        noeudActuel.setFilsDroit();

        //On test si une nouvelle meilleure solution possible est trouvée à gauche
        if(noeudActuel.getFilsGauche().getValeur() >= this.minVal
                && noeudActuel.getFilsGauche().getPoid() <= this.poidMax) {
            this.meilleurRes = noeudActuel.getFilsGauche();
            this.minVal = this.meilleurRes.getValeur();
        }

        //Si il il reste encore des objets à mettre dans le sac et que le poid maximal n'est pas atteint
        if(index < super.objets.size() - 1 && noeudActuel.getPoid() < this.poidMax) {
            //Le noeud gauche n'est pas concerné par le potentielMax,
            //Car s'il ne pouvait pas atteindre la borne minimal, il aurait été supprimé au noeud inférieur
            creeArbreRec(index + 1, noeudActuel.getFilsGauche(), maxPossible);

            //On calcul le 'potentiel' des sous arbres, en calculant la borne maximale possible
            //Pour cela, on prend la valeur max possible - l'objet que l'on ne va pas ajouter
            double potentielMax = maxPossible - super.objets.get(index).getValeur();

            //, on continue la recherche
            if(potentielMax >= this.minVal ) {
                creeArbreRec(index + 1, noeudActuel.getFilsDroit(), potentielMax);
            }
            //Sinon, on abandonne la recherche de ce noeud, car il n'y aura aucune solution correct possible
            //meilleur que celle déjà trouvé
        }
    }

    /**
     * Ajoute les objets de la solution trouvée en remontant l'arbre à partir du meilleur noeud récursivement
     *
     * @param noeudGagnant noeud correspondant au meilleur résultat trouvé
     */
    private void ajouterSolutionRec(ABR noeudGagnant) {
        int i = -1;

        //Si l'index est -1, il n'y a aucun objet à ajouter
        if((i = noeudGagnant.getIndexObjet()) != -1) {
            //On ajoute l'objet
            sac.ajouter(super.objets.get(i));
        }

        //On remonte jusqu'à la racine en ajoutant tout les objets
        if(!noeudGagnant.estRacine()) {
            ajouterSolutionRec(noeudGagnant.getParent());
        }
    }
}