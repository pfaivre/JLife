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
 * CommandLineParser.java
 * Creation : 02/10/2013
 * Last modification : 12/01/2014
 *
 * Description : Implémentation basique du jeu de la vie de John Horton Conway.
 */

package jlife;

import java.util.ArrayList;

/**
 * Gère l'interprétation des paramètres fournis en ligne de commande
 *
 * Le format de paramètre accepté est le suivant : — Chaque paramètre dispose
 * d'une clé et d'une valeur optionnelle — La valeur est spécifiée derrière le
 * signe '=', Exemple : -l=100 — Un paramètre raccourcit est précédé du signe
 * '-', Exemple : -l — Un paramètre long est précédé du signe "--", Exemple :
 * --largeur — Un paramètre sans '-' ni "--" est considéré comme une valeur sans
 * clé
 *
 * Exemple d'utilisation : JLife -i -w=50 -h=20 -d=3 /home/pierre/file
 *
 * @author Faivre Pierre
 */
class CommandLineParser {

    /**
     * Contient tous les couples paramètre/valeur.
     */
    private ArrayList<CommandLineArgument> arguments;

    /**
     * Constructeur de la classe.
     *
     * @param args Liste des arguments passé par la méthode main().
     * @param validArguments Liste des arguments autorisés.
     */
    public CommandLineParser(String[] args, String[] validArguments) throws CommandLineArgumentException {
        // Variables utiles pour le parcours de la liste d'arguments
        String key = null;
        String value = null;
        int index = 0;

        // Initialisation de la carte des arguments
        this.arguments = new ArrayList<>();

        // Parcours de la liste des arguments
        for (int i = 0; i < args.length; i++) {
            // Si le paramètre commence par "--" alors c'est un paramètre long
            if (args[i].startsWith("--")) {
                key = args[i].substring(2);
            } // Sinon si il commence par "-" alors c'est un paramètre court
            else if (args[i].startsWith("-")) {
                key = args[i].substring(1);
            } // Sinon c'est une valeur sans clé
            else {
                // On l'ajoute avec la valeur null comme clé
                arguments.add(new CommandLineArgument(null, args[i]));
                // Retourne au début de la boucle et passe au paramètre suivant
                continue;
            }

      // Recherche d'une valeur associée au paramètre
            // La clé contient-elle le signe "=" ?
            index = key.indexOf('=');
            if (index > 0) {
                // Si oui on ajoute ce qu'il y a à droite du signe '=' comme valeur
                value = key.substring(index + 1);
                // Et on ne garde de la clé que la partie gauche
                key = key.substring(0, index);
            } else {
                // Sinon ce paramètre n'a pas de valeur
                value = null;
            }

            // Puis on ajoute ce paramètre à la map.
            this.arguments.add(new CommandLineArgument(key, value));
        }

        // Maintenant que l'on a tous les arguments, on vérifie leur validité.
        String check = this.getInvalidArgument(validArguments);
        if (check != null) {
            throw new CommandLineArgumentException("invalid option -- '" + check);
        }
    }

    /**
     * Indique si les paramètres fournis par l'utilisateur sont valides.
     *
     * @param validArguments Liste des paramètres autorisés.
     * @return Nom du premier paramètre invalide, ou alors null s'ils sont tous
     * valides.
     */
    public final String getInvalidArgument(String[] validArguments) {
        String invalidArgument = null;
        int i = 0;               // Compteur pour la boule de parcours des arguments
        boolean found = false;   // Flag indiquant si un argument invalide a été trouvé
        boolean valid = false;   // Flag indiquant la validité d'un argument

        // Parcours des arguments donnés par l'utilisateur
        while (i < this.arguments.size() && !found) {
            // On commence par considérer que l'argument est invalide
            valid = false;

            // On regarde d'abord si c'est un argument sans clé. Dans ce cas on ne peut pas vérifier sa validité
            if (this.arguments.get(i).getKey() == null) {
                // on le considère alors comme valide
                valid = true;
            } else {
                // On parcourt de la liste des arguments valides pour voir s'il n'y en a pas un qui correspond
                for (int j = 0; j < validArguments.length && !valid; j++) {
                    // Si l'argument étudié figure dans la liste
                    if (this.arguments.get(i).getKey().compareTo(validArguments[j]) == 0) {
                        // Alors on le marque comme valide
                        valid = true;
                    }
                }
            }

            // Si à la fin du parcours des arguments valides on a rien trouvé, alors l'argument n'est pas valide
            if (!valid) {
                // On indique alors qu'on a trouvé un argument invalide
                found = true;
                invalidArgument = this.arguments.get(i).getKey();
            }

            i++;
        }

        return invalidArgument;
    }

    /**
     * Indique si le paramètre spécifié a été fournit par l'utilisateur.
     *
     * @param Paramètre à vérifier.
     * @return True si la paramètre a été fournit par l'utilisateur.
     */
    public boolean isDefined(String parameter) {
        int i = 0;             // Compteur de boucle de parcours de L'ArrayList
        boolean found = false; // Flag indiquant si la clé a été trouvée

        while (i < this.arguments.size() && !found) {
            if (this.arguments.get(i).getKey().compareTo(parameter) == 0) {
                found = true;
            }
            i++;
        }

        return found;
    }

    /**
     * Donne la valeur associée au paramètre.
     *
     * @param parameter Clé du paramètre dont la valeur est recherchée.
     */
    public String getValue(String parameter) {
        int i = 0;           // Compteur de boucle de parcours de L'ArrayList
        String value = null;

        while (i < this.arguments.size() && value == null) {
            if (this.arguments.get(i).getKey().compareTo(parameter) == 0) {
                value = this.arguments.get(i).getValue();
            }
            i++;
        }

        return value;
    }

    /**
     * Donne la valeur numérique entière associée au paramètre.
     *
     * @param parameter Clé du paramètre dont la valeur est recherchée.
     * @return Valeur associée au paramètre convertie en Integer.
     */
    public Integer getIntegerValue(String parameter) {
        Integer value = null;

        try {
            value = new Integer(this.getValue(parameter));
        } catch (NumberFormatException e) {
            value = null;
        }

        return value;
    }

    /**
     * Retourne la dernière valeur qui n'a pas de paramètre associé.
     *
     * @return Une chaîne contenant la dernière valeur sans paramètre.
     */
    public String getLastOrphanValue() {
        int i = this.arguments.size() - 1; // On commence par le dernier indice
        String value = null;

        // On parcours en partant de la fin
        while (i >= 0 && value == null) {
            // Dès qu'on trouve un élément dont la clé est nulle
            if (this.arguments.get(i).getKey() == null) {
                // On le prend
                value = this.arguments.get(i).getValue();
            }
            i--;
        }

        return value;
    }

    /**
     * Donne le nombre total d'arguments fournis par l'utilisateur.
     *
     * @return Nombre d'arguments en comptant ceux sans clé et ceux sans valeur.
     */
    public int argc() {
        return this.arguments.size();
    }
}
