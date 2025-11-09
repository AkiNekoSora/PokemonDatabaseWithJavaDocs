// Autumn Skye
// CEN-3024C 13950
// November 8th, 2025
package org.pokemondatabase.GUI;

import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JLayeredPane;

/**
 * <p>
 * Used to display the success results from uploading a file with Pokémon
 * </p>
 * <h2>IMPORTANT DEPENDENCIES</h2>
 * <ul>
 *     <li>AddFilePage: Sends the user here with success text.</li>
 *     <li>GuiHelper: Used to create the base panel and add important items to the page.</li>
 * </ul>
 */
public class AddFileSuccessPage {
    private final JLayeredPane pane;

    /* Method Name: CONSTRUCTOR
     * Purpose: Builds the base design using GUI helper
     * Parameters: MainMenuPage, lineCounter as a string
     */

    /**
     * Class Constructor
     * Builds the base design using GUI helper. Adds the text, buttons and button actions.
     * @param mainApp MainMenuPage Gui main menu
     * @param successfulLines String The success text
     */
    public AddFileSuccessPage(MainMenuPage mainApp, String successfulLines) {
        GuiHelper helper = new GuiHelper(mainApp);

        // BUILDS BASE PANEL
        pane = helper.createBasePanel("POKÉMON ADDED",  "/background.jpg");

        String successText = "<html><body style='width:340px; text-align:center;'>"
                + "<span style='font-size:50pt'>"
                + "SUCCESS!<br>YOUR POKÉMON WERE <br>ADDED TO THE DATABASE</span><br><br>"
                + "Number of Pokémon added: " + successfulLines
                + "</body></html>";
        helper.addLabelWithImage(successText, 278, 200, 600, 300);

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
