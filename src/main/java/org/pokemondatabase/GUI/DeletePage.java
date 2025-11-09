// Autumn Skye
// CEN-3024C 13950
// November 8th, 2025
package org.pokemondatabase.GUI;

import java.awt.Container;
import java.util.List;
import javax.swing.*;

import org.pokemondatabase.Pokemon;

/**
 * <p>
 * Used to ask the user if they are sure they want to delete. If accepted, it deletes
 * the Pokémon from the system and moves to the Delete Success Page
 * </p>
 * <h2>IMPORTANT DEPENDENCIES</h2>
 * <ul>
 *     <li>GuiHelper: Used to create the base panel and add important items to the page.</li>
 * </ul>
 */
public class DeletePage extends JPanel {
    /** List of Pokémon */
    public List<Pokemon> pokemonDB;
    private final JLayeredPane pane;

    /**
     * Class Constructor
     * Builds the base design using GUI helper. Adds the text, buttons and button actions.
     * @param mainApp MainMenuPage Gui main menu
     * @param pokemon Pokémon to be deleted
     * @param pokemonStorage Storage of the Pokémon list
     */
    public DeletePage(MainMenuPage mainApp, Pokemon pokemon, List<Pokemon> pokemonStorage) {
        GuiHelper helper = new GuiHelper(mainApp);

        // BUILDS BASE PANEL
        pane = helper.createBasePanel("VERIFY DELETION",  "/background.jpg");

        String successText = "<html><body style='width:345px; text-align:center; font-size:35pt;'>"
                + "ARE YOU SURE YOU WANT TO DELETE " + pokemon.getPokemonName().toUpperCase()
                + "?</body></html>";
        helper.addLabelWithImage(successText, 280, 280, 600, 200);

        // BUILDS DELETE BUTTON
        JButton deleteButton = helper.addSmallButton("DELETE", 805, 680);

        // DELETES THE POKÉMON AND MOVES TO SUCCESS PAGE
        deleteButton.addActionListener(e -> {
            mainApp.db.pokemon.delete("pokedex_number", String.valueOf(pokemon.getPokedexNumber()));
            mainApp.goToPage(new DeleteSuccessPage(mainApp).getMainPanel());
        });

        // BUILDS BACK BUTTON AND HANDLES WHEN IT IS SELECTED
        JButton backButton = helper.addSmallButton("BACK", 20, 685);
        backButton.addActionListener(e -> {
            mainApp.goToPage(new PokemonInfoPage(mainApp, pokemon, pokemonDB).getMainPanel());
        });
    }

    /**
     * Used to return the main panel of this page
     * @return Container(panel)
     */
    public Container getMainPanel() {
        return pane;
    }
}
