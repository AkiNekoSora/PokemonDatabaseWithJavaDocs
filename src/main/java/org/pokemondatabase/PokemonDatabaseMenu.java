// Autumn Skye
// CEN-3024C 13950
// November 8th, 2025
// Only used for Command Line Interface
package org.pokemondatabase;
import java.util.List;

/**
 * <ul>
 * <li>Used to start the system.</li>
 * <li>Prints the Pokémon database main menu.</li>
 * <li>Gets the user's choice of what to do in the database.</li>
 * <li>Calls the method based on the user input.</li>
 * <li>Uses methods from Pokémon Manager</li>
 * </ul>
 * <p>Note: All methods have been comments out to avoid any issues with the updates to the GUI</p>
 */
public class PokemonDatabaseMenu {
    Text text = new Text();

//    /**
//     * Used to start the system and have a while loop that loops until the user wants to exit the
//     * system. Asking for a number from the user every time.
//     * @param pokemonStorage The List of Pokémon is passed to use to hold all Pokémon needed for
//     *                       all methods in the library system
//     */
//    public void pokemonDatabaseMenu(List<Pokemon> pokemonStorage) {
//        UserInputHelper userInputHelper = new UserInputHelper();
//        int userChoice = 1;
//
//        System.out.println(text.BLUE + "------------------------------------");
//        System.out.println("| WELCOME TO THE POKÉMON DATABASE! |");
//        System.out.println("------------------------------------\n" + text.RESET);
//
//        /**
//         * A while loop used to accept the user input to decide what they would like to do until
//         * they choose the "0" option to exit the system.
//         */
//        while (userChoice != 0) {
//            System.out.println(printPokemonDatabaseOptions());
//
//            userChoice = userInputHelper.getIntInRange(
//                    "Please enter a valid option (0 - 8): ", 0, 8);
//
//            if (userChoice != 0) {
//                pokemonDatabaseMenuOptions(userChoice, pokemonStorage);
//            }
//        }
//
//        System.out.println(text.BLUE + "\n---------------------------------");
//        System.out.println("| Exiting the POKÉMON Database! |");
//        System.out.println("---------------------------------\n" + text.RESET);
//    }



    /* Method Name: Pokémon Database Menu Options Method
     * Purpose: Takes in the option the user enters the User Menu Class and calls the method
     *          that matches the choice. Using a switch case to accept the number.
     * Parameters: The choice enter by the user in the UserMenu method. And the List of
     *             Pokémon to use with Pokémon methods.
     * Return Value: Void/None - This is just used to call methods!
     */
//    public void pokemonDatabaseMenuOptions(int userChoice, List<Pokemon> pokemonStorage) {
//        PokemonManager pokemonManager = new PokemonManager();
//
//        // Switch for all Pokémon methods
//        switch (userChoice) {
//            // ADD Pokémon using CLI
//            case 1: System.out.println(pokemonManager.addPokemon(pokemonStorage, mainApp));
//                break;
//            // ADD Pokémon using a File
//            case 2: System.out.println(pokemonManager.addPokemonFromFile(pokemonStorage));
//                break;
//            // REMOVE a Pokémon using CLI
//            case 3: System.out.println(pokemonManager.removePokemonByPokedexID(pokemonStorage));
//                break;
//           // SEARCH Pokémon using CLI
//            case 4: System.out.println(pokemonManager.findPokemonByPokedexID(pokemonStorage));
//                break;
//            // PRINT all Pokémon to the CLI
//            case 5: System.out.println(pokemonManager.printAllPokemon(pokemonStorage));
//                break;
//            // UPDATE Pokémon Information using CLI
//            case 6: System.out.println(pokemonManager.updatePokemonInformation(pokemonStorage));
//                break;
//            // COMPARE Pokémon Weight & Height using CLI
//            case 7: System.out.println(pokemonManager.comparePokemonByPokedexID(pokemonStorage));
//                break;
//            // CHECK next evolution level of a Pokémon using CLI
//            case 8: System.out.println(pokemonManager.checkPokemonNextEvolution(pokemonStorage));
//                break;
//        }
//    }

    /**
     * Used just to print out the menu and ask the user what number they would like to pick.
     * @return String of the options menu
     */
    public String printPokemonDatabaseOptions() {
        Text text = new Text();
        String optionsMenu = """
               Pokémon Database options:
                    0. LEAVE Pokémon Database
                    1. ADD Pokémon with Command Line Interface
                    2. ADD Pokémon using a .txt file
                    3. REMOVE Pokémon with Command Line Interface (Using Pokédex Number)
                    4. SEARCH Pokémon using Command Line Interface (Using Pokédex Number or Pokémon Name)
                    5. PRINT all Pokémon to the Command Line Interface
                    6. UPDATE Pokémon Information using Command Line Interface (Using Pokédex Number)
                    7. COMPARE Pokémon Weight & Height using Command Line Interface (Using Pokédex Number)
                    8. CHECK next evolution level of a Pokémon using Command Line Interface (Using Pokédex Number)
               """;

        return text.GREEN + "\n" + optionsMenu + "\n" + text.RESET;
    }
}
