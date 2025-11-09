// Autumn Skye
// CEN-3024C 13950
// November 8th, 2025
package org.pokemondatabase.GUI;

import java.awt.Container;
import javax.swing.*;

/**
 * <p>
 * Used to display the success results from deleting a Pokémon from the system
 * </p>
 * <h2>IMPORTANT DEPENDENCIES</h2>
 * <ul>
 *     <li>DeletePage: Directs the user to the page if the deletion is successful.</li>
 *     <li>GuiHelper: Used to create the base panel and add important items to the page.</li>
 * </ul>
 */
public class DeleteSuccessPage extends JPanel {
    private final JLayeredPane pane;
    private GuiHelper helper;

    /**
     * Class Constructor
     * Builds the base design using GUI helper. Adds the text, buttons and button actions.
     * @param mainApp MainMenuPage Gui main menu
     */
    public DeleteSuccessPage(MainMenuPage mainApp) {
        helper = new GuiHelper(mainApp);

        // BUILDS BASE PANEL
        pane = helper.createBasePanel("DELETION COMPLETED",  "/background.jpg");

        // BUILDS THE SUCCESS LABEL WITH AN IMAGE
        String successText = "<html><body style='width:345px; text-align:center; font-size:35pt;'>"
                + "POKEMON SUCCESSFULLY DELETED!</body></html>";
        helper.addLabelWithImage(successText, 280, 280, 600, 200);

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
