
import bowling.SinglePlayerGame;
import com.mycompany.mavenbowling.MultiGame;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vanat
 */
public class MultiPlayerGameTest {
    private MultiGame game;
    private final String[] listeJoueurs = {"Patrick", "Jean", "Paul"};
    @Before
    public void setUpMulti() {
        game = new MultiGame();
    }
    /**
     * Test la création de joueurs 
     */
    @Test
    public void testCreaJoueur() throws Exception {
        game.startNewGame(listeJoueurs);
        assertEquals("Prochain tir: joueur Patrick, tour n°1, boule n°1" ,
                game.startNewGame(listeJoueurs));
           
    }
    
    /**
     * Test le compte des scores pour un joueur 
     */
    @Test
    public void testScoreSimple() throws Exception {
        game.startNewGame(listeJoueurs);
        rollMany(3, 3);
        assertEquals(6,game.scoreFor(listeJoueurs[0]));
    }
    
    /** 
     * Test l'affichage après lancer
     */
    @Test
    public void testAffich() throws Exception {
        game.startNewGame(listeJoueurs);
        rollMany(5,3);
        assertEquals("Prochain tir: joueur Patrick, tour n°2, boule n°1",
                game.lancer(3));
    }
    
    /**
     * Test grande Partie
     */
    @Test
    public void testGrandePartie() throws Exception {
        game.startNewGame(listeJoueurs);
        rollMany(12, 3); // 12 points par joueur
	rollStrike(); // 10 -> 22 1er joueur
   	rollMany(6, 0); // 22 1er, 12, 2e, 12 3e
        assertEquals(22,game.scoreFor(listeJoueurs[0]));
    }
    /**
     * Effectue plusieurs lancer
     * @param n
     * @param pins
     * @throws Exception 
     */
    private void rollMany(int n, int pins) throws Exception {
		for (int i = 0; i < n; i++) {
			game.lancer(pins);
		}
	}
    
    	private void rollSpare() throws Exception {
		game.lancer(7);
		game.lancer(3);
	}

	private void rollStrike() throws Exception {
		game.lancer(10);
	}
}
