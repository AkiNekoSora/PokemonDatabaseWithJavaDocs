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
 * Used to display the success results from Checking the next evolution
 * </p>
 * <h>IMPORTANT DEPENDENCIES</h>
 * <ul>
 *     <li>CheckNextEvoPage: Sends the user here if the comparison is successful.</li>
 *     <li>GuiHelper: Used to create the base panel and add important items to the page.</li>
 * </ul>
 * <h>CONTAINS</h>
 * <ul>
 * <li>Constructor - Builds the base design using GUI helper</li>
 * <li>getMainPanel - returns the main panel for this page</li>
 * </ul>
 */
public class CheckNextEvoSuccessPage {
    private final JLayeredPane pane;
    private GuiHelper helper;

    /**
     * Class Constructor
     * Builds the base design using GUI helper. Adds the text, buttons and button actions.
     * @param mainApp MainMenuPage Gui main menu
     * @param successText String of Success Text
     */
    public CheckNextEvoSuccessPage(MainMenuPage mainApp, String successText) {
        helper = new GuiHelper(mainApp);

        // BUILDS BASE PANEL
        pane = helper.createBasePanel("CHECK NEXT EVOLUTION RESULTS",  "/background.jpg");

        // BUILDS THE SUCCESS LABEL WITH AN IMAGE
        helper.addLabelWithImage(successText, 278, 260, 600, 250);

        // BUILDS NEXT BUTTON AND HANDLES WHEN IT IS SELECTED
        JButton nextButton = helper.addSmallButton("NEXT", 800, 650);
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
