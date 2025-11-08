// Autumn Skye
// CEN-3024C 13950
// November 8th, 2025
package org.pokemondatabase;
import org.pokemondatabase.exceptions.InvalidPokemonTypeException;

/**
 * A class used to hold the group of Type constants.
 * Methods:
 * <ul>
 * <li>toString method used to print the Type.</li>
 * <li>fromString method used to obtain the type enum.</li>
 * </ul>
 */
public enum PokemonTypes {
    NORMAL,
    FIRE,
    WATER,
    GRASS,
    ELECTRIC,
    ICE,
    FIGHTING,
    POISON,
    GROUND,
    FLYING,
    PSYCHIC,
    BUG,
    ROCK,
    GHOST,
    DARK,
    DRAGON,
    STEEL,
    FAIRY;

    /**
     * Attempts to return the enum value of the input. If it fails it throws an
     * InvalidPokémonTypeException.
     * Defense of Static: This method had to be static because it needed to be called and you
     * cannot instantiate an enum class. Hopefully that is okay!
     * @param input String of a Pokémon
     * @return PokémonTypes of the provided Pokémon String
     */
    public static PokemonTypes fromString(String input) {
        try {
            return PokemonTypes.valueOf(input.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidPokemonTypeException("Invalid Pokémon Type: " + input);
        }
    }

    /**
     * Overrides the original toString method to change how it prints when someone prints a Type.
     * @return String with Type
     */
    @Override
    public String toString() {
        String type = name().toLowerCase();
        return type.substring(0, 1).toUpperCase() + type.substring(1);
    }
}
