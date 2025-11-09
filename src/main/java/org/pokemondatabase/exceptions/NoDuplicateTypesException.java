// Autumn Skye
// CEN-3024C 13950
// November 8th, 2025
package org.pokemondatabase.exceptions;

/**
 * <p>
 * Exception class used when a user tries to enter a duplicate type.
 * </p>
 */
public class NoDuplicateTypesException extends RuntimeException {
    /**
     * Used to return to CMI when a user attempts to enter the same type twice
     * @param message A string message from the Types Class
     */
    public NoDuplicateTypesException(String message) {
        super(message);
    }
}
