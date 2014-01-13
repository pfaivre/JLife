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
  * GridFileReader.java
  * Creation : 12/01/2014
  * Last modification : 13/01/2014
  *
  * Description : Implémentation basique du jeu de la vie de John Horton Conway.
  */

package jlife;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Classe permettant le chargement d'une grille depuis un fichier texte.
 * Le système de lecture est relativement permissif.
 * Tous tous les '#' sont considérés comme des cellules vivantes.
 * Tous les autres caractères sont considérés comme des cellules mortes.
 * La longueur de la plus longue ligne détermine la largeur de la grille.
 * Le nombre de lignes du fichier détermine la hauteur de la grille.
 * @author Faivre Pierre
 */
public class GridFileReader {
    
    private int width;
    private int height;
    private String fileName;
    
    /**
     * @param fileName Chemin du fichier à charger.
     */
    public GridFileReader(String fileName) throws IOException {
        this.fileName = fileName;
        FileReader fr = new FileReader(this.fileName);
        this.width = 0;
        this.height = 0;
        
        // *** Calcul des dimensions de la grille ***
        int lineWidth = 0;
        int c = fr.read(); // Première lecture
        while (c != -1) {  // Tant qu'on n'atteint pas la fin du fichier
            while (c != '\n' && c != -1) { // Tant qu'on atteint pas la fin de ligne
                lineWidth++;
                c = fr.read();
            }
            // Après une fin de ligne, on met à jour les dimensions
            if (lineWidth > this.width) {
                this.width = lineWidth;
            }
            lineWidth = 0;
            this.height++;
            
            c = fr.read(); // Et on continue la lecture
        }

        System.out.println(this.width + "x" + this.height);
        
        fr.close();
    }
    
    /**
     * Créée un ArrayList<Cell> en suivant les motifs du fichier.
     * @return ArrayList<Cell>
     */
    public ArrayList getGrid() throws IOException {
        FileReader fr = new FileReader(fileName);
        ArrayList<Cell> grid = new ArrayList<>();
        
        int posLine = 0;   // Position du curseur sur la ligne.
        int c = fr.read(); // Première lecture
        while (c != -1) {  // Tant qu'on atteint pas la fin du fichier
            while (c != '\n' && c != -1) { // Boucle sur la ligne courante
                grid.add(new Cell(c == '#')); // Ajout d'une cellule vivante si c == '#', morte sinon.
                posLine++;
                c = fr.read();
            }
            // À la fin de la ligne du fichier, on rajoute des cases pour compléter la ligne de la grille.
            for (;posLine < this.width ; posLine++) {
                grid.add(new Cell());
            }
            posLine = 0;
            
            c = fr.read();
        }
        
        fr.close();
        return grid;
    }

    /**
     * Détermine la largeur de la grille définie dans le fichier.
     * @return Nombre de cellules en largeur. C'est-à-dire la longueur de la
     *         plus longue ligne dans le fichier.
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Détermine la hauteur de la grille définie dans le fichier.
     * @return Nombre de cellule en hauteur. C'est-à-dire le nombre de ligne
     *         Contenues dans le fichier.
     */
    public int getHeight() {
        return this.height;
    }
    
}
