package fr.main.methode;

import fr.main.sacADos.*;
import fr.main.util.ABR;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ResolutionPse extends ResolutionGloutonne {

    private double minVal;
    private ABR meilleurRésultat;
    private double poidMax;

    public ResolutionPse(List<Objet> objets) {
        super(objets);
    }

    @Override
    public void résoudre(SacADos sac) {

        //On initialise certaines données
        this.poidMax = sac.getPoidsMax();
        double maxVal = this.minVal = 0;

        //La valeur max si l'on met tout les objets dans le sac sans prendre en compte le poids max
        for(Objet o : super.objets)
            maxVal += o.getPrix();

        //Une première borne minimal qui correspond à un résultat réaliste
        //Cette solution est obtenue grâce à l'algo glouton (d'où son héritage)
        super.résoudre(sac);
        for(Objet o : sac)
            this.minVal += o.getPrix();

        //Si l'algo glouton à trouvé une solution parfaite, inutile de refaire l'algo PSE
        if(maxVal != sac.getValeur()) {

            //On crée maintenant la racine de l'arbre
            ABR root = new ABR();
            //On initialise le meilleur chemin
            this.meilleurRésultat = root;

            //On créer l'abre
            créerFilsRecursifs(0, root, maxVal);

            //On à maintenant la meilleur solution contenu dans this.meilleurRésultat
            //On remonte alors jusqu'à la racine en ajoutant dans le sac les objet
            sac.vider();
            ajouterSolution(sac, this.meilleurRésultat);
        }
        //Le sac est trié avec la meilleure solution trouvé
    }

    private void créerFilsRecursifs(int index, ABR abreActuel, double maxPossible) {

        //On ajoute l'objet suivant dans le fils gauche
        abreActuel.setFilsGauche(super.objets.get(index), index);
        //On crée une copie (on ne rajoute rien) dans le fils droit
        abreActuel.setFilsDroit();

        //On test si une nouvelle meilleure solution possible est trouvée à gauche
        if(abreActuel.getFilsGauche().getValeur() >= this.minVal
                && abreActuel.getFilsGauche().getPoid() <= this.poidMax) {
            this.meilleurRésultat = abreActuel.getFilsGauche();
            this.minVal = this.meilleurRésultat.getValeur();
        }

        //Si il il reste encore des objets à mettre dans le sac et que le poid maximal n'est pas atteint
        if(index < super.objets.size() - 1 && abreActuel.getPoid() < this.poidMax) {
            //Le noeud gauche n'est pas concerné par le potentielMax,
            //Car s'il ne pouvait pas atteindre la borne minimal, il aurait été supprimé au noeud inférieur
            créerFilsRecursifs(index + 1, abreActuel.getFilsGauche(), maxPossible);

            //On calcul le 'potentiel' des sous arbres, en calculant la borne maximale possible
            //Pour cela, on prend la valeur max possible - l'objet que l'on ne va pas ajouter
            double potentielMax = maxPossible - super.objets.get(index).getPrix();

            //, on continue la recherche
            if(potentielMax >= this.minVal ) {
                créerFilsRecursifs(index + 1, abreActuel.getFilsDroit(), potentielMax);
            }
            //Sinon, on abandonne la recherche de ce noeud, car il n'y aura aucune solution correct possible
            //meilleur que celle déjà trouvé
        }
    }

    private void ajouterSolution(SacADos sac, ABR abreActuel) {
        int i = -1;

        //Si l'index est -1, il n'y a aucun objet à ajouter
        if((i = abreActuel.getIndexObjet()) != -1) {
            //On ajoute l'objet
            sac.ajouter(super.objets.get(i));
        }

        //On remonte jusqu'à la racine en ajoutant tout les objets
        if(!abreActuel.estRacine()) {
            ajouterSolution(sac, abreActuel.getParent());
        }
    }
}
