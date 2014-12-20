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
 * Cell.java
 * Creation : 29/09/2013
 * Last modification : 20/12/2014
 *
 * Description : Implémentation basique du jeu de la vie de John Horton Conway.
 */

package jlife;

/**
 * La cellule.
 * @author Faivre Pierre
 */
public class Cell {

    /**
     * État de la cellule : true -> vivante et false -> morte.
     */
    private boolean alive;

    /**
     * État de la cellule dans la prochaine génération
     */
    private boolean nextGenerationState;

    /**
     * Créée une nouvelle cellule morte.
     */
    public Cell() {
        this.alive = false;
        this.nextGenerationState = false;
    }

    public Cell(boolean alive) {
        this.alive = alive;
        this.nextGenerationState = alive;
    }

    /**
     * Permet d'indiquer l'état de la cellule à la prochaine génération.
     */
    public void setNextGenerationState(boolean state) {
        this.nextGenerationState = state;
    }

    /**
     * Applique le changement d'état de la cellule.
     */
    public void changeState() {
        this.alive = this.nextGenerationState;
    }

    /**
     * @return L'état actuel de la cellule.
     */
    public boolean isAlive() {
        return this.alive;
    }
}
