// Autumn Skye
// CEN-3024C 13950
// November 8th, 2025
package org.pokemondatabase.GUI;

import java.awt.Container;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

/**
 * <header>PURPOSE/PRIMARY FUNCTION</header>
 * <p>
 * Allows the user to choose how they want to add a Pokémon
 * </p>
 <header>IMPORTANT DEPENDENCIES</header>
 * <ul>
 *     <li>FilePage and AddManual: The two buttons on the page.</li>
 *     <li>GuiHelper: Used to create the base panel and add important items to the page.</li>
 * </ul>
 */
public class FileOrManualAddPage extends JFrame {
    private final JLayeredPane pane;
    private GuiHelper helper;

    /**
     * Class Constructor
     * Builds the base design using GUI helper. Adds the text, buttons and button actions.
     * @param mainApp MainMenuPage Gui main menu
     */
    public FileOrManualAddPage(MainMenuPage mainApp) {
        helper = new GuiHelper(mainApp);

        // BUILDS BASE PANEL
        pane = helper.createBasePanel("ADD POKÉMON",  "/background.jpg");

        // BUILDS MANUAL AND TEXT FILE BUTTONS
        JButton addPokemonManualButton = helper.addLargeButton("ADD POKÉMON - MANUALLY",
                200, 250);
        JButton addPokemonTextFileButton = helper.addLargeButton("ADD POKÉMON - FILE", 200, 400);

        // GOES TO MANUAL PAGE IF MANUAL BUTTON CLICKED
        addPokemonManualButton.addActionListener((ActionEvent e) -> {
            mainApp.goToPage(new AddManualPage(mainApp).getMainPanel());
        });

        // GOES TO TEXT FILE ADD PAGE IF TEXT FILE BUTTON CLICKED
        addPokemonTextFileButton.addActionListener((ActionEvent e) -> {
            mainApp.goToPage(new AddFilePage(mainApp).getMainPanel());
        });

        // BUILDS BACK BUTTON AND HANDLES WHEN IT IS SELECTED
        JButton backButton = helper.addSmallButton("BACK", 15, 680);
        backButton.addActionListener(e -> {
            mainApp.goToPage(mainApp.getMainMenuLayeredPane());
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
