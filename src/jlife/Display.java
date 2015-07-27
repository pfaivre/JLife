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
 * Display.java
 * Creation : 29/09/2013
 * Last modification : 20/12/2014
 *
 * Description : Implémentation basique du jeu de la vie de John Horton Conway.
 */

package jlife;

/**
 * Classe gérant l'interface utilisateur en console.
 * @author Faivre Pierre
 */
public class Display {

    /**
     * Affiche la grille dans la console.
     *
     * @param grid Grille à afficher.
     */
    public static void drawGrid(Grid grid) {
        int count = 0; // Compteur pour connaître la position dans la ligne.
        StringBuilder out = new StringBuilder();

        // Ligne supérieure
        for (int i = 0; i < grid.getWidth(); i++) {
            out.append('_');
        }
        out.append(System.lineSeparator());

        // Contenu de la grille
        for (int i = 0; i < grid.getWidth() * grid.getHeight(); i++) {
            if (grid.get(i).isAlive()) {
                out.append('#');
            } else {
                out.append(' ');
            }

            // Quand on arrive à la fin de la ligne, on insère un saut de ligne.
            count++;
            if (count == grid.getWidth()) {
                out.append('|').append(System.lineSeparator());
                count = 0;
            }
        }
        out.append("Generation : ").append(grid.getGeneration()).append(" ; population : ").append(grid.getPopulation());

        System.out.println(out.toString());
    }

    /**
     * Affiche un message d'erreur fatale. Ces messages doivent être du type
     * "fatal". Pour les alertes, voir warningMessage.
     *
     * @param msg Informations sur le problème.
     */
    public static void errorMessage(String msg) {
        System.out.println(Color.LIGHT_RED + " [-] " + Color.NORMAL + msg);
    }

    /**
     * Affiche un message d'alerte. Ce message indique un problème non bloquant
     * lors du déroulement du programme.
     *
     * @param msg Informations sur le problème.
     */
    public static void warningMessage(String msg) {
        System.out.println(Color.YELLOW + " [!] " + Color.NORMAL + msg);
    }

    /**
     * Affiche un message de réussite. Ces messages indiquent le bon déroulement
     * du programme ou l'achèvement d'une action.
     *
     * @param msg Message.
     */
    public static void successMessage(String msg) {
        System.out.println(Color.LIGHT_GREEN + " [+] " + Color.NORMAL + msg);
    }

    /**
     * Affiche un message concernant une action en cours. Ces messages indiquent
     * le début d'un processus pouvant potentiellement être long.
     *
     * @param msg Message sur l'action effectuée.
     */
    public static void processingMessage(String msg) {
        System.out.println(Color.LIGHT_BLUE + " [*] " + Color.NORMAL + msg);
    }

    /**
     * Affiche une brève aide sur l'utilisation du programme.
     */
    public static void helpMessage() {
        System.out.println("usage : JLife [" + Color.LIGHT_RED + "-g" + Color.NORMAL 
                           + "=" + Color.LIGHT_GREEN + "max_generations" + Color.NORMAL
                           + "] [" + Color.LIGHT_RED + "-i" + Color.NORMAL + "|" + Color.LIGHT_RED
                           + "-a" + Color.NORMAL + "|" + Color.LIGHT_RED + "-q" + Color.NORMAL
                           + "]\n              [[" + Color.LIGHT_RED + "-w" + Color.NORMAL
                           + "=" + Color.LIGHT_GREEN + "width " + Color.LIGHT_RED
                           + "-h" + Color.NORMAL + "=" + Color.LIGHT_GREEN + "height "
                           + Color.LIGHT_RED + "-d" + Color.NORMAL + "=" + Color.LIGHT_GREEN
                           + "density" + Color.NORMAL + "] | [FILE]]");
    }
}
