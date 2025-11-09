// Autumn Skye
// CEN-3024C 13950
// November 8th, 2025
package org.pokemondatabase.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

/**
 * <p>
 * Used to let the user choose what database they would like to connect to.
 * </p>
 * <h2>IMPORTANT DEPENDENCIES</h2>
 * <ul>
 *     <li>MainMenuPage: The user will only be sent here if a DB has not been chosen.</li>
 *     <li>GuiHelper: Used to create the base panel and add important items to the page.</li>
 * </ul>
 */
public class ChooseDBPage extends JFrame {
    private final JLayeredPane pane;
    private GuiHelper helper;

    private final JButton fileChooserButton;
    private final JLabel errorFilePath;

    /**
     * Class Constructor
     * Builds the base design using GUI helper. Adds the text, buttons and button actions.
     * @param mainApp MainMenuPage Gui main menu
     */
    public ChooseDBPage(MainMenuPage mainApp) {
        helper = new GuiHelper(mainApp);

        // BUILDS BASE PANEL
        pane = helper.createBasePanel("CHOOSE DATABASE",  "/background.jpg");

        // BUILDS FILE CHOOSE BUTTON AND ERROR LABELS
        fileChooserButton = helper.addLargeButton("CHOOSE DATABASE", 200, 340);
        errorFilePath = helper.addErrorLabel(200, 430, 600);

        // ACTION WHEN FILE CHOOSE BUTTON IS SELECTED
        fileChooserButton.addActionListener((ActionEvent event) -> {
            errorFilePath.setText("");

            // Opens the file chooser
            JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
            int returnVal = fileChooser.showOpenDialog(null);

            // If the user attempts to add a file: get the file location, call addPokemonFrom File
            // GUI using the path.
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                Boolean successfulDatabaseConnection = true;
                File selectedFile = fileChooser.getSelectedFile();
                String path = selectedFile.getAbsolutePath();

                if (path.endsWith(".db") || path.endsWith(".sqlite")) {
                    try {
                        mainApp.db.connect(path);
                    } catch (Exception e) {
                        errorFilePath.setText("Database connection failed");
                        successfulDatabaseConnection = false;
                    }
                } else {
                    errorFilePath.setText("Not a valid database. Please choose a database file.");
                    successfulDatabaseConnection = false;
                }

                if (successfulDatabaseConnection) {
                    mainApp.goToPage(mainApp.getMainMenuLayeredPane());
                }
            }
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
