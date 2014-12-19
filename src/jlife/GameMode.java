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
 * GameMode.java
 * Creation : 08/01/2014
 * Last modification : 19/12/2014
 *
 * Description : Implémentation basique du jeu de la vie de John Horton Conway.
 */

package jlife;

/**
 * Liste des modes de fonctionnement du programme.
 * @author Faivre Pierre
 */
public enum GameMode {
    /**
     * Mode automatique.
     */
    AUTO,

    /**
     * Mode manuel (défilement sur l'appui d'une touche).
     */
    INTERACTIVE,

    /**
     * Mode silencieux. Seules les générations initiale et finale sont affichées.
     */
    QUIET;
}
