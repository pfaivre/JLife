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
 * Creation : 08/01/2014
 * Last modification : 19/12/2014
 *
 * Description : Implémentation basique du jeu de la vie de John Horton Conway.
 */

package jlife;

/**
 * Liste des couleurs utilisables dans la console avec leur code.
 * @author Faivre Pierre
 */
public enum Color {

    NORMAL        ("\u001B[0m"),
    BLACK         ("\u001B[00;30m"),
    GREY          ("\u001B[01;30m"),
    RED           ("\u001B[00;31m"),
    LIGHT_RED     ("\u001B[01;31m"),
    GREEN         ("\u001B[00;32m"),
    LIGHT_GREEN   ("\u001B[01;32m"),
    YELLOW        ("\u001B[00;33m"),
    LIGHT_YELLOW  ("\u001B[01;33m"),
    BLUE          ("\u001B[00;34m"),
    LIGHT_BLUE    ("\u001B[01;34m"),
    PURPLE        ("\u001B[00;35m"),
    LIGHT_PURPLE  ("\u001B[01;35m"),
    CYAN          ("\u001B[00;36m"),
    LIGHT_CYAN    ("\u001B[01;36m"),
    WHITE         ("\u001B[00;37m");
    
    private String code;
    
    Color(String code) {
        this. code = code;
    }
    
    public String toString() {
        return this.code;
    }
}
