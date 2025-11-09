// Autumn Skye
// CEN-3024C 13950
// November 8th, 2025

package org.pokemondatabase;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * <header>PURPOSE/PRIMARY FUNCTION</header>
 * <p>
 * Used to create a new Pokémon.
 * </p>
 */
public class Pokemon {

    // Pokémon Variables!
    private String pokemonName;
    private int pokedexNumber;
    private PokemonTypesManager pokemonTypes;
    private Integer nextEvolutionLevel;
    private BigDecimal pokemonWeightKg;
    private BigDecimal pokemonHeightMeters;
    private Boolean pokemonIsCaught;
    private String pokedexEntry;

    /**
     * Class Constructor
     * Used to create a Pokémon. Calls the method formatPokemonName() for the Pokémon name.
     * @param pokemonName String Pokémon name
     * @param PokedexNumber int Pokédex number
     * @param pokemonTypes PokemonTypesManager Pokémon types (1 or 2)
     * @param nextEvolutionLevel Integer The level the Pokémon will evolve (can be null or 0 if
     *                           the Pokémon does not evolve.
     * @param pokemonWeightPounds BigDecimal weight of the Pokémon
     * @param pokemonHeightMeters BigDecimal height of the Pokémon
     * @param pokemonIsCaught Boolean Has the Pokémon been caught or not
     * @param pokedexEntry String A short sentence or two about the Pokémon in the Pokédex
     */
    public Pokemon(String pokemonName, int PokedexNumber, PokemonTypesManager pokemonTypes,
                   Integer nextEvolutionLevel, BigDecimal pokemonWeightPounds,
                   BigDecimal pokemonHeightMeters,
                   Boolean pokemonIsCaught, String pokedexEntry) {
        this.pokemonName = formatPokemonName(pokemonName);
        this.pokedexNumber = PokedexNumber;
        this.pokemonTypes = pokemonTypes;
        this.nextEvolutionLevel = nextEvolutionLevel;
        this.pokemonWeightKg = pokemonWeightPounds;
        this.pokemonHeightMeters = pokemonHeightMeters;
        this.pokemonIsCaught = pokemonIsCaught;
        this.pokedexEntry = pokedexEntry;
    }

    //GETTERS
    /** @return String Pokémon name */
    public String getPokemonName() {return pokemonName;}
    /** @return int Pokédex number */
    public int getPokedexNumber() {return pokedexNumber;}
    /** @return PokemonTypesManager Pokémon Types(1 or 2) */
    public PokemonTypesManager getPokemonType() {return pokemonTypes;}
    /** @return PokemonTypes Pokémon Primary Type */
    public PokemonTypes getPrimaryType() {return pokemonTypes.getPokemonPrimaryType();}
    /** @return PokemonTypes Pokémon Secondary Type */
    public PokemonTypes getSecondaryType() {return pokemonTypes.getPokemonSecondaryType();}
    /** @return Integer Pokémon Next Evolution Level */
    public Integer getNextEvolutionLevel() {return nextEvolutionLevel;}
    /** @return BigDecimal Pokémon Weight */
    public BigDecimal getPokemonWeightKilograms() {return pokemonWeightKg;}
    /** @return Big Decimal Pokémon Height */
    public BigDecimal getPokemonHeightMeters() {return pokemonHeightMeters;}
    /** @return Boolean Pokémon Caught Status */
    public Boolean getPokemonIsCaught() {return pokemonIsCaught;}
    /** @return String Pokédex Entry */
    public String getPokedexEntry() {return pokedexEntry;}

    //SETTERS
    /**
     * @param pokemonName String Pokémon Name
     * @return String success message
     */
    public String setPokemonName(String pokemonName) {
        this.pokemonName = formatPokemonName(pokemonName);
        return "Pokémon Name updated to: " + this.pokemonName;
    }
    /**
     * @param pokedexNumber String Pokédex Number
     * @return String success message
     */
    public String setPokedexNumber(int pokedexNumber) {
        this.pokedexNumber = pokedexNumber;
        return "Pokédex Number has been updated to: " + String.valueOf(this.pokedexNumber);
    }
    /**
     * @param pokemonTypes PokemonTypesManager Pokémon Types (1 or 2)
     * @return String success message
     */
    public String setPokemonType(PokemonTypesManager pokemonTypes) {
        this.pokemonTypes = pokemonTypes;
        return "Pokémon Type has been updated to: " + pokemonTypes;
    }
    /**
     * @param nextEvolutionLevel Integer Pokémon's next evolution number
     * @return String success message
     */
    public String setNextEvolutionLevel(Integer nextEvolutionLevel) {
        this.nextEvolutionLevel = nextEvolutionLevel;
        return "Pokémon Next Evolution as been updated to: " + String.valueOf(this.nextEvolutionLevel);
    }
    /**
     * @param pokemonWeightPounds BigDecimal Pokémon Weight
     * @return String success message
     */
    public String setPokemonWeightPounds(BigDecimal pokemonWeightPounds) {
        this.pokemonWeightKg = pokemonWeightPounds;
        return "Pokémon Weight has been updated to: " + String.valueOf(this.pokemonWeightKg);
    }
    /**
     * @param pokemonHeightMeters BigDecimal Pokémon Height
     * @return String success message
     */
    public String setPokemonHeightMeters(BigDecimal pokemonHeightMeters) {
        this.pokemonHeightMeters = pokemonHeightMeters;
        return "Pokémon Height has been updated to: " + String.valueOf(this.pokemonHeightMeters);
    }
    /**
     * @param pokemonIsCaught Boolean Pokémon Caught Status
     * @return String success message
     */
    public String setPokemonIsCaught(boolean pokemonIsCaught) {
        this.pokemonIsCaught = pokemonIsCaught;
        return "'Has Pokémon been caught?' has been updated to: " + hasPokemonBeenCaught();
    }
    /**
     * @param pokedexEntry String Pokémon entry for Pokédex
     * @return String success message
     */
    public String setPokedexEntry(String pokedexEntry) {
        this.pokedexEntry = pokedexEntry;
        return "Pokémon Name updated to: \"" + pokedexEntry + "\"";
    }

    /**
     * Used to take in the user provided Pokémon name and then format it to have correct
     * capitalization for the name. (First letter capital then lowercase)
     * @param pokemonName String Pokémon Name
     * @return String Formated Pokémon Name
     */
    public String formatPokemonName(String pokemonName) {
        if (pokemonName == null || pokemonName.isEmpty()) {
            return pokemonName;
        }

        pokemonName = pokemonName.toLowerCase().trim();
        char[] chars = pokemonName.toCharArray();
        boolean capitalizeNext = true;

        // Cycles through the String and capitalizes after white space, -, /, or first number
        for (int i = 0; i < chars.length; i++) {
            if (Character.isWhitespace(chars[i]) || chars[i] == '-' || chars[i] == '\'') {
                capitalizeNext = true;
            } else if (capitalizeNext) {
                chars[i] = Character.toUpperCase(chars[i]);
                capitalizeNext = false;
            }
        }
        return new String(chars);
    }

    /**
     * Used to turn the Boolean of the Caught status to "Caught" or "Not Caught"
     * @return String "Caught" or "Not Caught" depending on the status
     */
    public String hasPokemonBeenCaught(){
        String pokemonCaughtAnswer;

        if (pokemonIsCaught) {
            pokemonCaughtAnswer = "Caught";
        } else {
            pokemonCaughtAnswer = "Not Caught";
        }
        return pokemonCaughtAnswer;
    }

    /**
     * Checks to see if the nextEvolution variable is null or 0. If null, it prints "FULLY EVOLVED"
     * @return String with either the Pokédex Next Evolution level or "NO NEXT EVOLUTION."
     */
    public String hasNextEvolution(){
        if (this.nextEvolutionLevel == null || this.nextEvolutionLevel == 0) {
            return "FULLY EVOLVED";
        } else {
            return String.valueOf(this.nextEvolutionLevel);
        }
    }

    /**
     * Checks to see if the Pokédex entry is null. If null, it prints "NO POKÉDEX ENTRY."
     * @return String with either the Pokédex Entry or "NO POKÉDEX ENTRY."
     */
    public String hasPokedexEntry() {
        return Objects.requireNonNullElse(this.pokedexEntry, "NO POKÉDEX ENTRY.");
    }

    /**
     * Overrides the original toString method to change how it prints when someone prints a Pokémon.
     * @return String with all Pokémon information.
     */
    @Override
    public String toString() {
        return  "\n" +
                "    Pokémon Name: " + this.pokemonName + "\n" +
                "    Pokédex Number: " + this.pokedexNumber + "\n" +
                "    Pokédex Type: " + this.pokemonTypes.toString() + "\n" +
                "    Next Evolution Level: " + hasNextEvolution() + "\n" +
                "    Pokémon Weight in Pounds: " + this.pokemonWeightKg + "\n" +
                "    Pokémon Height in Meters: " + this.pokemonHeightMeters + "\n" +
                "    Have you Caught this Pokémon?: " + hasPokemonBeenCaught() + "\n" +
                "    Pokédex Entry: " + hasPokedexEntry() + "\n";
    }

}
