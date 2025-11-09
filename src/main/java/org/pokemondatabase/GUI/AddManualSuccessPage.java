// Autumn Skye
// CEN-3024C 13950
// November 8th, 2025
package org.pokemondatabase.GUI;

import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JLayeredPane;

/**
 * <h>PURPOSE/PRIMARY FUNCTION</h>
 * <p>
 * Used to display the success results from adding a Pokémon using the GUI
 * </p>
 * <h>IMPORTANT DEPENDENCIES</h>
 * <ul>
 *     <li>AddManualSuccessPage: Sends user here with the success text.</li>
 *     <li>GuiHelper: Used to create the base panel and add important items to the page.</li>
 * </ul>
 * <h>CONTAINS</h>
 * <ul>
 * <li>Constructor - Builds the base design using GUI helper</li>
 * <li>getMainPanel - returns the main panel for this page</li>
 * </ul>
 */
public class AddManualSuccessPage {
    private final JLayeredPane pane;
    private GuiHelper helper;

    /**
     * Class Constructor
     * Builds the base design using GUI helper. Adds the text, buttons and button actions.
     * @param mainApp MainMenuPage Gui main menu
     * @param successText String success text to print to GUI
     */
    public AddManualSuccessPage(MainMenuPage mainApp, String successText) {
        helper = new GuiHelper(mainApp);

        // BUILDS BASE PANEL
        pane = helper.createBasePanel("POKÉMON SUCCESSFULLY ADDED", "/background.jpg");

        // BUILDS THE SUCCESS LABEL WITH AN IMAGE
        helper.addLabelWithImage(successText, 277, 250, 600, 350);

        // BUILDS NEXT BUTTON AND HANDLES WHEN IT IS SELECTED
        JButton nextButton = helper.addSmallButton("NEXT", 805, 680);
        nextButton.addActionListener(e -> {
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
