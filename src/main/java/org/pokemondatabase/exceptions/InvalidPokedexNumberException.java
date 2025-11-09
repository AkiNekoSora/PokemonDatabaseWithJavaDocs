// Autumn Skye
// CEN-3024C 13950
// November 8th, 2025
package org.pokemondatabase.exceptions;

/**
 * <h>PURPOSE/PRIMARY FUNCTION</h>
 * <p>
 * Exception class used when a user enters an invalid Pokédex Number.
 * </p>
 */
public class InvalidPokedexNumberException extends RuntimeException {
    /**
     * Used to return to CMI when a user attempts to enter a Pokédex number already in use or one
     * that is not between 1 and 1164.
     * @param message A string message from the PokemonManager Class
     */
    public InvalidPokedexNumberException(String message) {
        super(message);
    }
}
