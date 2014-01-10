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
  * JLife.java
  * Creation : 29/09/2013
  * Last modification : 03/10/2013
  *
  * Description : Implémentation basique du jeu de la vie de John Horton Conway.
  */

package jlife;

/**
 * Classe principale.
 * @author Faivre Pierre
 */
class JLife {

  public static void main(String args[]) {
    Game game = null;

    try {
      game = new Game(args);
      game.start();
    }
    catch (Exception e) {
      Display.errorMessage(e.getMessage());
    }
  }

}

