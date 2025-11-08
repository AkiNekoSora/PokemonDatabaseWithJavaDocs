// Autumn Skye
// CEN-3024C 13950
// November 8th, 2025
package org.pokemondatabase.GUI;

import java.awt.Container;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import org.pokemondatabase.DBHelper.DBHelper;

/*
 * Used to let the user choose what they would like to do in the system. Contains
 * buttons to go to FileOrManualAddPage, ListPage, ComparePage, CheckNextEvoPage
 * Contains:
 * <ul>
 * <li>Constructor - Builds the base design using GUI helper</ul>
 * <li>goToPage</ul>
 * <li>getMainMenuLayeredPane</ul>
 * <li>getMainPanel - returns the main panel for this page</ul>
 * </ul>
 */
public class MainMenuPage extends JFrame {

    private final JLayeredPane pane;

    private final JButton addPokemonButton;
    private final JButton pokemonListButton;
    private final JButton comparePokemonStatsButton;
    private final JButton checkNextEvolutionButton;

    public DBHelper db;

    /**
     * Class Constructor
     * Builds the base design using GUI helper. Adds the text, buttons and button actions.
     * Connects to the database.
     */
    public MainMenuPage() {
        GuiHelper helper = new GuiHelper(MainMenuPage.this);

        // SET SIZE OF WINDOW
        setSize(1000, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // BUILDS BASE PANEL
        pane = helper.createBasePanel("POKÉMON DATABASE",  "/background.jpg");

        // ADD BUTTONS TO PANEL
        addPokemonButton = helper.addLargeButton("ADD POKÉMON", 200, 100);
        pokemonListButton = helper.addLargeButton("POKÉMON LIST", 200, 250);
        comparePokemonStatsButton = helper.addLargeButton("COMPARE POKÉMON STATS", 200, 400);
        checkNextEvolutionButton = helper.addLargeButton("CHECK NEXT EVOLUTION", 200, 550);

        setContentPane(pane);
        pack();
        setVisible(true);

        if (this.db == null) {
            this.db = new DBHelper();
        }

        if (!this.db.connected) {
            this.goToPage(new ChooseDBPage(this).getMainPanel());
        }

        // ADD BUTTON ACTION
        addPokemonButton.addActionListener((ActionEvent e) -> {
            goToPage(new FileOrManualAddPage(this).getMainPanel());
        });

        // LIST BUTTON ACTION
        pokemonListButton.addActionListener((ActionEvent e) -> {
            goToPage(new ListPage(this).getMainPanel());
        });

        // COMPARE BUTTON ACTION
        comparePokemonStatsButton.addActionListener((ActionEvent e) -> {
            goToPage(new ComparePage(this).getMainPanel());
        });

        // CHECK NEXT EVOLUTION BUTTON ACTION
        checkNextEvolutionButton.addActionListener((ActionEvent e) -> {
            goToPage(new CheckNextEvoPage(this).getMainPanel());
        });
    }

    /**
     * When called it rewrites what page is currently displayed to the screen
     * @param page Container of the page
     */
    public void goToPage(Container page) {
        this.setContentPane(page);
        this.revalidate();
        this.repaint();
    }

    /**
     * returns the main menu layered Panel
     * @return JLayeredPane
     */
    public JLayeredPane getMainMenuLayeredPane() {
        return pane;
    }

}
