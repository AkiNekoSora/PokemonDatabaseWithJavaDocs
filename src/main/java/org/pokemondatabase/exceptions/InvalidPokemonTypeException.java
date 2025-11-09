// Autumn Skye
// CEN-3024C 13950
// November 8th, 2025
package org.pokemondatabase.exceptions;

/**
 * <header>PURPOSE/PRIMARY FUNCTION</header>
 * <p>
 * Exception class used when a user enters an invalid Pokemon type.
 * </p>
 */
public class InvalidPokemonTypeException extends IllegalArgumentException {
    /**
     * Used to return to CMI when a user attempts to enter an invalid Pokemon type.
     * @param message A string message from the PokemonManager Class
     */
    public InvalidPokemonTypeException(String message) {
        super(message);
    }
}
