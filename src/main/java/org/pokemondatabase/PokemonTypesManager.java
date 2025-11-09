// Autumn Skye
// CEN-3024C 13950
// November 8th, 2025
package org.pokemondatabase;

import org.pokemondatabase.exceptions.NoDuplicateTypesException;
import java.util.Objects;

/**
 * <header>PURPOSE/PRIMARY FUNCTION</header>
 * <p>
 * Used to hold up to two Pokémon Types. Verifies that a secondary type is not the same as the
 * primary type. Prints a nicer version of types if there is one or two.
 * </p>
 * <header>IMPORTANT DEPENDENCIES</header>
 * <ul>
 *     <li>PokemonTypes: Required to create each PokemonTypesManager</li>
 * </ul>
 */

public class PokemonTypesManager {
    private PokemonTypes primaryType;
    private PokemonTypes secondaryType;

    /**
     * Class Constructor
     * Used to create a PokemonTypesManager with one or 2 Pokémon Types
     * @param pokemonType1 PokemonTypes for primary
     * @param pokemonType2 PokemonTypes for secondary (optional)
     */
    public PokemonTypesManager(PokemonTypes pokemonType1, PokemonTypes pokemonType2) {
        this.primaryType = pokemonType1;

        if (pokemonType2 != null) {
            this.secondaryType = pokemonType2;
        }
    }

    /**
     * Overloaded Constructor. Used to create a PokemonTypesManager with only one Pokémon Types
     * @param pokemonType1 PokemonTypes for primary
     */
    public PokemonTypesManager(PokemonTypes pokemonType1) {
        this.primaryType = pokemonType1;
    }

    // Getters!
    /** @return PokemonTypes for Primary Type */
    public PokemonTypes getPokemonPrimaryType() {return primaryType;}
    /** @return PokemonTypes for Secondary Type */
    public PokemonTypes getPokemonSecondaryType() {return secondaryType;}

    // Setters!
    /** @param pokemonType1 PokemonTypes for primary */
    public void setPokemonPrimaryType(PokemonTypes pokemonType1) {this.primaryType = pokemonType1;}
    /**
     * Verifies that the secondary Type is not a duplicate and throws an exception if it matches.
     * @param pokemonType2 PokemonTypes for secondary
     * */
    public void setPokemonSecondaryType(PokemonTypes pokemonType2) {
        try {
            if (verifyNoDuplicatePokemonTypes(this.primaryType, pokemonType2)) {
                this.secondaryType = pokemonType2;
            } else {
                throw new NoDuplicateTypesException("Secondary type can't be the same as primary.");
            }
        } catch (NoDuplicateTypesException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Called by setPokémonSecondaryType to check if the secondary type matches the first.
     * @param primaryType PokemonTypes for Primary Type
     * @param secondaryType PokemonTypes for Secondary Type
     * @return String of a nice version of the types.
     */
    public boolean verifyNoDuplicatePokemonTypes(PokemonTypes primaryType, PokemonTypes secondaryType) {
        return !Objects.equals(secondaryType, primaryType);
    }

    /**
     * Called by toString method to print the types nicely if there is one or two
     * @return String of a nice version of the types.
     */
    public String getAllPokemonTypes() {
        if (this.secondaryType == null) {
            return this.primaryType.toString();
        } else {
            return this.primaryType + " & " + this.secondaryType;
        }
    }

    /* Method Name: To String Method Override
     * Purpose: Overrides the original toString method to change how it prints when someone
     *          prints the type(s) of a Pokémon. Calls the getAllPokémonTypes method.
     * Parameters: None
     * Return Value: String with either one or two types.
     */

    /**
     * Overrides the original toString method to change how it prints when someone prints the
     * type(s) of a Pokémon. Calls the getAllPokémonTypes method.
     * @return String with either one or two types.
     */
    @Override
    public String toString() {
        return this.getAllPokemonTypes();
    }



}
