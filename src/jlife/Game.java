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
 * Game.java
 * Creation : 02/10/2013
 * Last modification : 12/01/2014
 *
 * Description : Implémentation basique du jeu de la vie de John Horton Conway.
 */

package jlife;

import java.util.Scanner;

/**
 * Classe comprenant une grille.
 * @author Faivre Pierre
 */
class Game {

    // Variables de paramètre
    private GameMode mode;       // Mode de fonctionnement du programme
    private int delay;           // Délai en mode automatique
    private int maxGeneration;   // Nombre maximal de générations
    private int width;           // Largeur de la grille.
    private int height;          // Hauteur de la grille.
    private int density;         // Densité de cellules vivantes en mode aléatoire

    // Grille du jeu
    private Grid grid;

    public Game(String args[]) throws CommandLineArgumentException {
        // Valeur par défaut des paramètres
        this.mode = GameMode.AUTO;
        this.delay = 200;
        this.maxGeneration = 100;
        this.width = 50;
        this.height = 20;
        this.density = 5;

        // Interprétation des paramètres de la ligne de commande
        String[] validArguments = {"a", "auto", "g", "generations", "?", "help",
            "i", "interactive", "q", "quiet",
            "w", "width", "h", "height", "d", "density"};
        CommandLineParser clp = new CommandLineParser(args, validArguments);

        // Analyse des arguments donnés pour changer les paramètre de fonctionnement du programme
        this.loadParameters(clp);

        // Création de la grille.
        this.grid = new Grid(this.width, this.height, this.density);
    }

    /**
     * Étudie les arguments donnés afin de changer les paramètre de fonctionnement du programme.
     * TODO: Réécrire cette fonction pour enlever la redondance (peut nécessiter une réécriture partielle des classes CommandLineParser ou CommandLineArgument).
     * @param clp Arguments donnés par l'utilisateur
     */
    private void loadParameters(CommandLineParser clp) throws CommandLineArgumentException {
        // Affichage de l'aide
        if (clp.isDefined("?") || clp.isDefined("help")) {
            Display.helpMessage();
            System.exit(0);
        }
        
        // Définition du nombre max de générations
        if (clp.isDefined("g") || clp.isDefined("generations")) {
            if (clp.getIntegerValue("g") != null) {
                this.maxGeneration = clp.getIntegerValue("g").intValue();
            } else if (clp.getIntegerValue("generations") != null) {
                this.maxGeneration = clp.getIntegerValue("generations").intValue();
            }
        }

        // Définition du mode [automatique | interactif | silencieux]
        if (clp.isDefined("a") || clp.isDefined("auto")) {
            if (clp.isDefined("i") || clp.isDefined("interactive") || clp.isDefined("q") || clp.isDefined("quiet")) {
                throw new CommandLineArgumentException("Connot load multiples modes");
            }
            this.mode = GameMode.AUTO;
        } else if (clp.isDefined("i") || clp.isDefined("interactive")) {
            if (clp.isDefined("a") || clp.isDefined("auto") || clp.isDefined("q") || clp.isDefined("quiet")) {
                throw new CommandLineArgumentException("Connot load multiples modes");
            }
            this.mode = GameMode.INTERACTIVE;
        } else if (clp.isDefined("q") || clp.isDefined("quiet")) {
            if (clp.isDefined("i") || clp.isDefined("interactive") || clp.isDefined("a") || clp.isDefined("auto")) {
                throw new CommandLineArgumentException("Connot load multiples modes");
            }
            this.mode = GameMode.QUIET;
        }

        // Définition de l'espace de jeu [largeur+hauteur+densité ou fichier]
        if (clp.isDefined("w") || clp.isDefined("width")) {
            if (clp.getIntegerValue("w") != null) {
                this.width = clp.getIntegerValue("w").intValue();
            } else if (clp.getIntegerValue("width") != null) {
                this.width = clp.getIntegerValue("width").intValue();
            }

            if (this.width <= 0) {
                this.width = 1;
            }
        }

        if (clp.isDefined("h") || clp.isDefined("height")) {
            if (clp.getIntegerValue("h") != null) {
                this.height = clp.getIntegerValue("h").intValue();
            } else if (clp.getIntegerValue("height") != null) {
                this.height = clp.getIntegerValue("height").intValue();
            }

            if (this.height <= 0) {
                this.height = 1;
            }
        }

        if (clp.isDefined("d") || clp.isDefined("density")) {
            if (clp.getIntegerValue("d") != null) {
                this.density = clp.getIntegerValue("d").intValue();
            } else if (clp.getIntegerValue("density") != null) {
                this.density = clp.getIntegerValue("density").intValue();
            }

            if (this.density < 1) {
                this.density = 1;
            } else if (this.density > 10) {
                this.density = 10;
            }
        }
    }

    /**
     * Démarre le jeu.
     */
    public void start() {
        System.out.println("JLife 0.2a");

        // Appel d'une méthode différente suivant le mode.
        switch (this.mode) {
            case AUTO:
                this.processDelay();
                break;
            case INTERACTIVE:
                this.processManual();
                break;
            case QUIET:
                this.processQuiet();
                break;
        }
    }

    /**
     * Exécute la grille et attends que l'utilisateur appuie sur une touche
     * après chaque génération.
     */
    private void processManual() {
        int i = 0;
        boolean isEmpty = false;
        Scanner sc = new Scanner(System.in);

        Display.drawGrid(this.grid);
        i = 0;
        while (i < this.maxGeneration && !isEmpty) {
            isEmpty = !this.grid.nextGeneration();
            Display.drawGrid(this.grid);
            sc.nextLine();
            i++;
        }
        sc.close();
    }

    /**
     * Exécute la grille automatiquement avec délai.
     */
    private void processDelay() {
        int i = 0;
        boolean isEmpty = false;

        Display.drawGrid(this.grid);
        i = 0;
        while (i < this.maxGeneration && !isEmpty) {
            isEmpty = !this.grid.nextGeneration();
            Display.drawGrid(this.grid);
            try {
                Thread.sleep(this.delay);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            i++;
        }
    }

    /**
     * Exécute la grille en mode silencieux pour calculer plus vite.
     */
    private void processQuiet() {
        int i = 0;
        boolean isEmpty = false;

        // Affichage de la grille initiale
        Display.drawGrid(this.grid);
        Display.processingMessage("Computing " + this.maxGeneration + " generations...");

        // Calcul
        i = 0;
        while (i < this.maxGeneration && !isEmpty) {
            isEmpty = !this.grid.nextGeneration();
            i++;
        }

        // Affichage de la grille finale
        Display.drawGrid(this.grid);
    }
}
