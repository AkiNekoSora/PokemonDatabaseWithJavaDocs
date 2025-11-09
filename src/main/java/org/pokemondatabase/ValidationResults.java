// Autumn Skye
// CEN-3024C 13950
// November 8th, 2025
package org.pokemondatabase;

/**
 * <header>PURPOSE/PRIMARY FUNCTION</header>
 * <p>
 * Used to hold a boolean and String for results
 * </p>
 * <header>IMPORTANT DEPENDENCIES</header>
 * <ul>
 *     <li>PokemonManager: Calls this class to pass around results and Pokemon</li>
 * </ul>
 */
public class ValidationResults {
    private final boolean success;
    private String resultString;
    private Pokemon pokemon;

    // CONSTRUCTOR

    /**
     * Class Constructor
     * Used to create a Validation Results with a boolean and String
     * @param success boolean of whether it was successful or not
     * @param resultString String with the final results
     */
    public ValidationResults(boolean success, String resultString) {
        this.success = success;
        this.resultString = resultString;
    }

    /**
     * Overloaded Constructor
     * Used to create a Validation Results with a boolean
     * @param success boolean of whether it was successful or not
     */
    public ValidationResults(boolean success) {
        this.success = success;
    }

    /**
     * Overloaded Constructor
     * Used to create a Validation Results with a boolean, String, and a Pokémon
     * @param success boolean of whether it was successful or not
     * @param resultString String with the final results
     * @param pokemon A Pokémon
     */
    public ValidationResults(boolean success, String resultString, Pokemon pokemon) {
        this.success = success;
        this.resultString = resultString;
        this.pokemon = pokemon;
    }

    /** @return boolean with success results */
    public boolean getIsSuccess() {return success;}
    /** @return String with the results */
    public String getResultString() {return resultString;}
    /** @return A Pokémon */
    public Pokemon getPokemon() {return pokemon;}
}

