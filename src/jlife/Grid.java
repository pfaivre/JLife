/**
  * JLife v0.2
  * Copyright (C) 2014 Faivre Pierre
  *
  * This program is free software; you can redistribute it and/or modify
  * it under the terms of the GNU General Public License as published by
  * the Free Software Foundation; either version 2 of the License, or
  * (at your option) any later version.
  *
  * This program is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
  * GNU General Public License for more details.
  *
  * You should have received a copy of the GNU General Public License along
  * with this program; if not, write to the Free Software Foundation, Inc.,
  * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
  *
  * Grid.java
  * Creation : 29/09/2013
  * Last modification : 02/10/2013
  *
  * Description : Implémentation basique du jeu de la vie de John Horton Conway.
  */

package jlife;

import java.util.ArrayList;
import java.util.Random;

/**
 * Classe définissant la grille contenant les cellules.
 * @author Faivre Pierre
 */
class Grid {

  /**
   * Liste contenant toutes les cellules.
   */
  private ArrayList<Cell> grid;
  
  /**
   * Largeur de la grille en nombre de cellules
   */
  private int width;
  
  /**
   * Hauteur de la grille en nombre de cellules
   */
  private int height;

  /**
   * Nombre d'itérations écoulées depuis l'initialisation de la grille.
   * Représente le temps dans le jeu de la vie.
   */
  private int generation;

  /**
   * Créée une nouvelle grille et la remplit avec le fichier spécifié.
   */
  public Grid() {
    this.generation = 0;
  }

  /**
   * Créée une grille vide de la taille spécifiée.
   * @param width Nombre de cellules en largeur.
   * @param height Nombre de cellules en hauteur.
   */
  public Grid(int width, int height) {
    this.width = width;
    this.height = height;
    this.generation = 0;

    this.grid = new ArrayList<>();
    for (int i = 0; i < width * height; i++) {
      this.grid.add(new Cell());
    }
  }

  /**
   * Créée une grille aléatoire de la taille spécifiée.
   * @param width Nombre de cellules en largeur.
   * @param height Nombre de cellules en hauteur.
   * @param density Densité des cellules vivantes (unité arbitraire de 1 à 10).
   */
  public Grid(int width, int height, int density) {
    boolean state = false;
    Random rand = new Random();
    this.width = width;
    this.height = height;
    this.generation = 0;

    // Vérification sur le paramètre de densité
    if (density < 1)
      density = 1;
    else if (density > 10)
      density = 10;

    this.grid = new ArrayList<>();
    for (int i = 0; i < width * height; i++) {
      // 1 chance sur "11 - density" que la cellule soit vivante.
      if (rand.nextInt(11 - density) + 1 == 1)
        state = true;
      else
        state = false;

      this.grid.add(new Cell(state));
    }
  }

  /**
   * @return La largeur de la grille.
   */
  public int getWidth() {
    return this.width;
  }

  /**
   * @return La hauteur de la grille.
   */
  public int getHeight() {
    return this.height;
  }

  /**
   * @return La génération actuelle.
   */
  public int getGeneration() {
    return this.generation;
  }

  /**
   * @return Le nombre de cellules vivantes sur la grille.
   */
  public int getNbAliveCells() {
    int nbAlive = 0;
    for (int i = 0; i < this.width * this.height; i++) {
      if (this.grid.get(i).isAlive())
        nbAlive++;
    }

    return nbAlive;
  }

  /**
   * Retourne la cellule dont l'indice est passé en paramètre.
   * @param i Numéro de la cellule à retourner.
   * @return Cellule
   */
  public Cell get(int i) {
    return this.grid.get(i);
  }

  /**
   * Modifie la grille pour arriver à l'itération suivante.
   * @return Booléen valant false s'il n'y a plus de cellule vivante
   *         ou si la grille est inerte
   */
  public boolean nextGeneration() {
    int nbAlive = 0;         // Compteur de cellules en vie
    int livingNeighbors = 0; // Compteur de cellules voisines vivantes à une autre cellule

    // On boucle pour déterminer pour chaque cellule le nombre de voisins vivants.
    // Suivant l'état actuel de la cellule on détermine alors son état suivant.
    for (int i = 0; i < this.width * this.height; i++) {
      Cell c = this.grid.get(i);
      livingNeighbors = getLivingNeighbors(i);
      // S'il y a 0 ou 1 ou plus de 4 voisins morts, la cellule sera morte.
      if (livingNeighbors <= 1 || livingNeighbors >= 4) {
        c.setNextGenerationState(false);
      }
      // S'il y a deux voisins, on regarde si la cellule est déjà vivante ou non.
      else if (livingNeighbors == 2) {
        // si oui elle restera vivante.
        if (c.isAlive()) {
          c.setNextGenerationState(true);
        }
        // Sinon elle restera morte.
        else {
          c.setNextGenerationState(false);
        }
      }
      // S'il y a 0 ou 1 voisin, la cellule naîtra.
      else if (livingNeighbors == 3) {
        c.setNextGenerationState(true);
      }
    }

    // On boucle ensuite pour appliquer ces changements.
    for (int i = 0; i < this.width * this.height; i++) {
      this.grid.get(i).changeState();
      // On compte au passage le nombre de cellules restantes.
      if (this.grid.get(i).isAlive())
        nbAlive++;
    }

    this.generation++;
    return nbAlive > 0;
  }

  /**
   * Compte le nombre de cellules vivantes autour de la cellule spécifiée.
   * @param i Indice de la cellule dans la grille.
   * @return Nombre de cellules vivantes autour de la cellule spécifiée.
   * TODO: Revoir l'algorithme de vérification sur les voisins.
   */
  private int getLivingNeighbors(int i) {
    int count = 0;  // Compteur de voisins vivants
    int x = 0;      // Abscisse de la cellule
    int y = 0;      // Abscisse de la cellule
    Cell c = null;  // Voisin à interroger
    int in = 0;     // Indice dans la grille du voisin à interroger
    int xn = 0;     // Abscisse de la cellule voisine
    int yn = 0;     // Abscisse de la cellule voisine

    // Calcul des coordonnées de notre cellule dans le plan cartésien
    y = i / this.width; // y = indice / largeur (division entière)
    x = i % this.width; // x = reste de cette division

    // Ordre d'interrogation des voisins :
    // 1 2 3
    // 4 # 5
    // 6 7 8

    // Voisin 1 (x-1;y-1)
    xn = x > 0 ? x-1 : this.width-1;
    yn = y > 0 ? y-1 : this.height-1;
    c = this.getCell(xn, yn);              // On récupère la cellule
    count = c.isAlive() ? count+1 : count; // On incrémente si la cellule est vivante

    // Voisin 2 (x;y-1)
    xn = x;
    yn = y > 0 ? y-1 : this.height-1;
    c = this.getCell(xn, yn);              // On récupère la cellule
    count = c.isAlive() ? count+1 : count; // On incrémente si la cellule est vivante

    // Voisin 3 (x+1;y-1)
    xn = x < this.width-1 ? x+1 : 0;
    yn = y > 0 ? y-1 : this.height-1;
    c = this.getCell(xn, yn);              // On récupère la cellule
    count = c.isAlive() ? count+1 : count; // On incrémente si la cellule est vivante

    // Voisin 4 (x-1;y)
    xn = x > 0 ? x-1 : this.width-1;
    yn = y;
    c = this.getCell(xn, yn);              // On récupère la cellule
    count = c.isAlive() ? count+1 : count; // On incrémente si la cellule est vivante

    // Voisin 5 (x+1;y)
    xn = x < this.width-1 ? x+1 : 0;
    yn = y;
    c = this.getCell(xn, yn);              // On récupère la cellule
    count = c.isAlive() ? count+1 : count; // On incrémente si la cellule est vivante

    // Voisin 6 (x-1;y+1)
    xn = x > 0 ? x-1 : this.width-1;
    yn = y < this.height-1 ? y+1 : 0;
    c = this.getCell(xn, yn);              // On récupère la cellule
    count = c.isAlive() ? count+1 : count; // On incrémente si la cellule est vivante

    // Voisin 7 (x;y+1)
    xn = x;
    yn = y < this.height-1 ? y+1 : 0;
    c = this.getCell(xn, yn);              // On récupère la cellule
    count = c.isAlive() ? count+1 : count; // On incrémente si la cellule est vivante

    // Voisin 8 (x+1;y+1)
    xn = x < this.width-1 ? x+1 : 0;
    yn = y < this.height-1 ? y+1 : 0;
    c = this.getCell(xn, yn);              // On récupère la cellule
    count = c.isAlive() ? count+1 : count; // On incrémente si la cellule est vivante

    return count;
  }

  /**
   * Retourne la cellule qui se trouve aux coordonnées cartésiennes (x;y)
   * @param x Abscisse de la cellule dans la grille.
   * @param y Ordonnée de la cellule dans la grille.
   * @return Référence vers la cellule à cet endroit.
   */
  private Cell getCell(int x, int y) {
    return this.grid.get(x + this.width * y);
  }
}

