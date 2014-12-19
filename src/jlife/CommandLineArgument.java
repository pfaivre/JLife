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
 * CommandLineArgument.java
 * Creation : 04/10/2013
 * Last modification : 19/12/2014
 *
 * Description : Implémentation basique du jeu de la vie de John Horton Conway.
 */

package jlife;

/**
 * Classe utilisée par CommandLineParser pour stocker les paramètres de la ligne de commande.
 * @see CommandLineParser
 * @author Faivre Pierre
 */
class CommandLineArgument {
    /**
     * Clé du paramètre.
     * Peut être sous forme courte (une lettre) ou sous forme longue.
     */
    private String key;

    /**
     * Valeur optionnelle du paramètre.
     */
    private String value;

    /**
     * Construit un paramètre ne possédant pas de valeur.
     * @param key Clé du paramètre.
     */
    public CommandLineArgument(String key) {
        this.key = key;
        this.value = null;
    }

    /**
     * Construit un paramètre possédant une valeur.
     * @param key Clé du paramètre.
     * @param value Valeur du paramètre.
     */
    public CommandLineArgument(String key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Fournit la clé du paramètre.
     * @return Nom du paramètre sans les tirets.
     */
    public String getKey() {
        return this.key;
    }

    /**
     * Fournit la valeur du paramètre.
     * @return Valeur du paramètre.
     */
    public String getValue() {
        return this.value;
    }
}
