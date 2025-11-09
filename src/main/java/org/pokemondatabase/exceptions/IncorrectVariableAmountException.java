// Autumn Skye
// CEN-3024C 13950
// November 8th, 2025
package org.pokemondatabase.exceptions;

/**
 * <h>PURPOSE/PRIMARY FUNCTION</h>
 * <p>
 * Exception class used when a txt file line does not match the required amount of variables.
 * </p>
 */
public class IncorrectVariableAmountException extends RuntimeException {

    /**
     * Used to return to CMI when a line in a txt file line does not match the required variable
     * length.
     * @param message A string message from the PokemonManager Class
     */
    public IncorrectVariableAmountException(String message) {
        super(message);
    }
}
