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
  * CommandLineArgumentException.java
  * Creation : 08/01/2014
  * Last modification : 08/01/2014
  *
  * Description : Implémentation basique du jeu de la vie de John Horton Conway.
  */

package jlife;

/**
 * Exception lancée quand une erreur est détectée dans les arguments en ligne
 * de commande.
 * @author Faivre Pierre
 */
public class CommandLineArgumentException extends Exception {

    /**
     * Constructs an instance of <code>CommandLineArgumentException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public CommandLineArgumentException(String msg) {
        super(msg);
    }
}
