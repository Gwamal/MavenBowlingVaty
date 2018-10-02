/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenbowling;

import bowling.Frame;
import bowling.MultiPlayerGame;
import bowling.SinglePlayerGame;
import java.util.HashMap;
import java.util.Map;/**

/**
 *
 * @author vanat
 */
public class MultiGame implements MultiPlayerGame {
//    /* liste des joueurs */
   private String[] listeJoueurs;
//    
//    /* liste de tours */
    private int numBoule;
    
    private Map<String, SinglePlayerGame> parties;
    
    /* Tour du joueur */
    private int tourJoueur;
    
    /* Tour general */
    private int tourGen;
    
    
    /**
     * Constructeur
     */
    public MultiGame() {
        tourJoueur = 0;
        numBoule = 1;
        tourGen = 1;
        this.parties = new HashMap<>();
    }
    /**
	 * Démarre une nouvelle partie pour un groupe de joueurs
	 * @param playerName un tableau des noms de joueurs (il faut au moins un joueur)
	 * @return une chaîne de caractères indiquant le prochain joueur,
	 * de la forme "Prochain tir : joueur Bastide, tour n° 5, boule n° 1"
	 * @throws java.lang.Exception si le tableau est vide ou null
	 */
         @Override
	public String startNewGame(String[] playerName) throws Exception {
            this.listeJoueurs = playerName;
            try {
                String nextTir = "Prochain tir: joueur ";
                nextTir+= playerName[0] + ", tour n°1, boule n°1";
                for(int i=0; i < playerName.length; i++){
                    this.parties.put(playerName[i], new SinglePlayerGame());
                }
                return nextTir;
            } catch (Exception e) {
                return "Tableau vide ou null";
            }
        }
	
	/**
	 * Enregistre le nombre de quilles abattues pour le joueur courant, dans le frame courant, pour la boule courante
	 * @param nombreDeQuillesAbattues : nombre de quilles abattue à ce lancer
	 * @return une chaîne de caractères indiquant le prochain joueur,
	 * de la forme "Prochain tir : joueur Bastide, tour n° 5, boule n° 1",
	 * ou bien "Partie terminée" si la partie est terminée.
	 * @throws java.lang.Exception si la partie n'est pas démarrée, ou si elle est terminée.
	 */
        @Override
	public String lancer(int nombreDeQuillesAbattues) throws Exception {
            this.parties.get(listeJoueurs[this.tourJoueur]).lancer(nombreDeQuillesAbattues);
            numBoule++;
            String prochainTir;
            if (numBoule == 3) {
                numBoule = 1;
                 if ( listeJoueurs.length == tourJoueur+1) {
                     this.tourJoueur = 0;
                     this.tourGen++;
                     prochainTir = nextTir(listeJoueurs[tourJoueur]
                        ,tourGen
                        ,numBoule);
                 } else {
                     this.tourJoueur++;
                     prochainTir = nextTir(listeJoueurs[this.tourJoueur]
                        ,tourGen
                        ,numBoule);
                 }
            } else {
                prochainTir = nextTir(listeJoueurs[tourJoueur]
                        ,tourGen
                        ,numBoule);
            }
            return prochainTir;
        }
        
        /**
         * méthode qui construit la String qui indique le prochain joueur
         * qui doit jouer
         * @param joueur
         * @param tour
         * @param boule
         */
        private String nextTir(String joueur, int tour, int boule) {
            String prochainTir = "Prochain tir: joueur ";
            prochainTir +=  joueur + ", tour n°" + tour
                    + ", boule n°" + boule;
            return prochainTir;
        }
	
	/**
	 * Donne le score pour le joueur playerName
	 * @param playerName le nom du joueur recherché
	 * @return le score pour ce joueur
	 * @throws Exception si le playerName ne joue pas dans cette partie
	 */
        @Override
	public int scoreFor(String playerName) throws Exception {
            return  this.parties.get(playerName).score();
        }
}
