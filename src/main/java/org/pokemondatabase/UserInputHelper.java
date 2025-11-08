// Autumn Skye
// CEN-3024C 13950
// November 8th, 2025
package org.pokemondatabase;

import org.pokemondatabase.exceptions.InvalidPokemonTypeException;
import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Used for all the methods that accept user Input
 * Checks in the input includes any digits or spaces
 * Checks if the input is a digit or period.
 * Includes methods that get these things from the user:
 * <ul>
 * <li>An Int</li>
 * <li>An Int in a specified range (With or without a specific error message)</li>
 * <li>A BigDecimal</li>
 * <li>A Boolean</li>
 * <li>A String</li>
 * <li>A valid Pokémon Name</li>
 * <li>Valid Pokémon Type(s)</li>
 * </ul>
 */
public class UserInputHelper {
    private final Scanner scnr = new Scanner(System.in);
    private final PokemonTypes pokemonTypes = PokemonTypes.FIRE;
    Text text = new Text();

    /**
     * Attempts to get an Int from the user until it is Valid
     * @param prompt The prompt(question) for the user
     * @param errorMessage The error message
     * @return Int that the user provides
     */
    public int getInt(String prompt, String errorMessage) {
        // Loops until valid int is given
        while (true) {
            try {
                System.out.print(prompt);
                int value = scnr.nextInt();
                scnr.nextLine(); // Used to consume the newline
                return value;
            } catch (InputMismatchException e) {
                System.out.println(errorMessageColors(errorMessage));
                scnr.nextLine(); // Used to consume the newline
            }
        }
    }

    /**
     * Method Overloader.
     * Attempts to get an Int with specified range from the user until it is Valid. Calls getInt
     * method.
     * @param prompt The prompt(question) for the user
     * @param min The minimum number the user is allowed provide
     * @param max The maximum number the user is allowed provide
     * @return A valid int from the user
     */
    public int getIntInRange(String prompt, int min, int max) {
        String errorMessage = text.BRIGHT_RED +
                "Input must be between " + min + " and " + max + "." + text.RESET;

        // Loops until valid int in range is given
        while (true) {
            int value = getInt(prompt, errorMessage);
            if (value >= min && value <= max) {
                return value;
            }
            System.out.println(errorMessageColors(errorMessage));
        }
    }

    /**
     * Method Overloader.
     * Attempts to get an Int with specified range from the user until it is Valid Calls getInt
     * method.
     * @param prompt The prompt(question) for the user
     * @param min The minimum number the user is allowed provide
     * @param max The maximum number the user is allowed provide
     * @param errorMessage Error message that will be printed
     * @return A valid int from the user
     */
    public int getIntInRange(String prompt, int min, int max, String errorMessage) {
        // Loops until valid int in range is given
        while (true) {
            int value = getInt(prompt, "Input must be a number. Please try again.");
            if (value >= min && value <= max) {
                return value;
            }
            System.out.println(errorMessageColors(errorMessage));

        }
    }

    /**
     * Attempts to get a BigDecimal From the user until it is valid
     * @param prompt The prompt(question) for the user
     * @param errorMessage Error message that will be printed
     * @return A valid BigDecimal from the user
     */
    public BigDecimal getBigDecimal(String prompt, String errorMessage) {
        // Loops until valid BigDecimal is given
        while (true) {
            try {
                System.out.print(prompt);
                BigDecimal value = scnr.nextBigDecimal();
                scnr.nextLine(); // clear newline
                return value;
            } catch (InputMismatchException e) {
                System.out.println(errorMessageColors(errorMessage));
                scnr.nextLine(); // Used to consume the newline
            }
        }
    }

    /**
     * Attempts to get a Boolean From the user until it is valid
     * @param question The prompt(question) for the user
     * @return A valid Boolean from the user
     */
    public boolean getBooleanInput(String question) {
        // Loops until valid "y/yes" or "n/no" is given
        while (true) {
            System.out.print(question + " (y or n): ");
            String input = scnr.nextLine().trim().toLowerCase();
            if (input.equals("y") || input.equals("yes")) {
                return true;
            } else if (input.equals("n") || input.equals("no")) {
                return false;
            } else {
                System.out.println(errorMessageColors("Please enter \"y\" or \"n\"."));
            }
        }
    }

    /**
     * Attempts to get a String From the user.
     * @param prompt The prompt(question) for the user
     * @return A String from the user (Trimmed)
     */
    public String getString(String prompt) {
        System.out.print(prompt);
        return scnr.nextLine().trim();
    }

    /**
     * Attempts to get a String From the user until it is valid. Calls getString method.
     * @param prompt The prompt(question) for the user
     * @return A valid String from the user (A Pokémon Name)
     */
    public String getValidPokemonName(String prompt) {
        // Loops until valid String is given
        while (true) {
            String input = getString(prompt);

            if (hasNoDigitsOrSpaces(input)) {
                return input;
            } else {
                System.out.println(errorMessageColors("Invalid Pokémon name. Numbers or spaces are not allowed. Please try again."));

                boolean cancelAddPokemon = getBooleanInput("Would you like to cancel current " +
                        "process?");

                if (cancelAddPokemon) {
                    return null;
                }
            }
        }
    }

    /**
     * Attempts to get a Pokémon Type from the user until it is valid. Calls getString method.
     * @param prompt The prompt(question) for the user.
     * @return A Valid PokémonTypes from the user.
     */
    public PokemonTypes getValidPokemonType(String prompt) {
        // Loops until valid Pokémon Type is given
        while (true) {
            String input = getString(prompt);

            try {
                return pokemonTypes.fromString(input);
            } catch (InvalidPokemonTypeException e) {
                System.out.println(errorMessageColors("\"" + input + "\" is not a valid Pokémon type. " +
                        "Please try again."));
            }
        }
    }

    /**
     * Checks through a string. Checking if each char is either a digit or a space. Returns true
     * if none of them are.
     * @param input String to check
     * @return A Boolean saying if it is a valid String or not.
     */
    public boolean hasNoDigitsOrSpaces(String input) {
        return input.chars().noneMatch(c -> Character.isDigit(c) || Character.isWhitespace(c));
    }

    /**
     * Checks through a string. Checking if each char is either a digit or a period. Returns true
     * if all are either a digit or period.
     * @param input String to check
     * @return A Boolean saying if it is a valid String or not.
     */
    public boolean isDigitOrPeriod(String input) {
        return input.chars().allMatch(c -> Character.isDigit(c) || c == '.');
    }

    /**
     * Used to change the color of any error messages or results.
     * @param errorMessage String containing an error message
     * @return String of the original message colored Bright Red
     */
    public String errorMessageColors(String errorMessage) {
        return text.BRIGHT_RED + errorMessage + text.RESET + "\n";
    }
}
