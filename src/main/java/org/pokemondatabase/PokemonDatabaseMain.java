// Autumn Skye
// CEN-3024C 13950
// November 8th, 2025
package org.pokemondatabase;
import org.pokemondatabase.GUI.MainMenuPage;

/**
 *  <p>
 *  The object of this project is to allow a user to enter information about a single Pokémon
 *  and will hold a list of Pokémon. It allows users to add Pokémon using the GUI or a txt
 *  document, remove Pokémon, search for a specific Pokémon using a Pokédex Number, displays a
 *  list of all Pokémon to the GUI, update a Pokémon using the GUI, compare the weight and height
 *  of two specified Pokémon, and check to see when a specified Pokémon will evolve.
 *  </p>
 *  The list of information the system holds for each Pokémon is:
 *         <ul>
 *         <li>Pokémon Name</li>
 *         <li>Pokédex Number</li>
 *         <li>Pokémon Types (1 or 2)</li>
 *         <li>nextEvolutionLevel</li>
 *         <li>Pokémon Weight</li>
 *         <li>Pokémon Height</li>
 *         <li>Has the Pokémon been caught</li>
 *         <li>Pokédex Entry</li>
 *         </ul>
 */
public class PokemonDatabaseMain {
    /**
     * Used to start the Program
     * @param args String[]
     */
    public static void main(String[] args) {
        MainMenuPage mainMenuPage = new MainMenuPage();
    }
}