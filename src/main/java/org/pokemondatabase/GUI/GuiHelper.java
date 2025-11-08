// Autumn Skye
// CEN-3024C 13950
// November 8th, 2025
package org.pokemondatabase.GUI;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import org.pokemondatabase.GUI.CustomGUIDesigns.CustomComboBoxRenderer;
import org.pokemondatabase.GUI.CustomGUIDesigns.CustomComboBoxUI;
import org.pokemondatabase.GUI.CustomGUIDesigns.CustomScrollBarUI;
import org.pokemondatabase.Pokemon;
import org.pokemondatabase.PokemonTypes;
import org.pokemondatabase.PokemonTypesManager;

import static java.lang.Integer.parseInt;

/**
 * Used to be called by all GUI pages to perform necessary GUI designs and processes.
 * Contains:
 * <ul>
 * <li>Constructor - Builds the base design using GUI helper</li>
 * <li>getFont - Used to get font</li>
 * <li>createBasePanel - Called by all pages to create the base of the panel.</li>
 * <li>addButton - Called by other methods in ths class to create a button</li>
 * <li>addSmallButton - Calls add button but makes it small</li>
 * <li>addMediumButton - Calls add button but makes it medium</li>
 * <li>addLargeButton - Calls add button but makes it Large</li>
 * <li>updatePokémonList - Updates the Pokémon List when search is used</li>
 * <li>addPokémonList - Creates a Pokémon list using a ScrollPane and calls to get List items</li>
 * <li>createPokémonListItem - Creates a single list item</li>
 * <li>convertToPokemonList - converts Database to list</li>
 * <li>addLabel - Adds specified text to the panel</li>
 * <li>addLabelWithSpecifics - add specified text to the panel (Specify font and size)</li>
 * <li>addLabelWithImage - Add a text label with an image using addImageScaled</li>
 * <li>addErrorLabel - Adds an error label (Smaller and red)</li>
 * <li>addTextField - Adds a place for the user to enter text</li>
 * <li>addSearchField - Adds a place for the user to enter text (Larger for Search)</li>
 * <li>addTextArea - Adds a bigger place for the user to enter text</li>
 * <li>addDropdown - Add a dropdown the user can use.</li>
 * <li>addImageScaled - Scaled an image to the text</li>
 * <li>addImageIcon - Add an image to the panel</li>
 * <li>addTextBackgroundImage - Add image to the background</li>
 * <li>isDigit - Verifies it is a digit</li>
 * <li>isDigitOrPeriod - verifies it is a digit or period</li>
 * </ul>
 */
public class GuiHelper {
    private MainMenuPage mainApp;
    public JPanel foregroundPanel;

    private final Color titleColor = new Color(36, 37, 40);
    public final Color pokemonRed = new Color(239, 49, 49);

    public final Font titleFont = getFont(52);
    public final Font labelFont = getFont(18);
    public final Font smallButtonFont = getFont(27);
    public final Font inputFont = new Font("Courier New", Font.PLAIN, 16);
    public final Font errorFont = new Font("Courier New", Font.BOLD, 12);
    public final Font successFont = getFont(20);
    public final Font pokemonListFont = getFont(40);
    public final Font bigFont = getFont(50);

    private JScrollPane pokemonScrollPane;

    public final String[] pokemonTypes = {"", "Normal", "Fire", "Fighting", "Water", "Flying", "Grass",
                "Poison", "Electric", "Ground", "Psychic", "Rock", "Ice", "Bug", "Dragon",
                "Ghost", "Dark", "Steel", "Fairy"};

    /**
     * Class Constructor
     * Accepts the main app and brings it into scope
     * @param mainApp MainMenuPage Gui main menu
     */
    public GuiHelper(MainMenuPage mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Returns the font SHPinscher
     * @param fontSize Size of the font
     * @return Font
     */
    public Font getFont(int fontSize) {
        Font customFont;

        // Try/Catch in case the font is not obtained
        try {
            // Gets the Font
            InputStream is = getClass().getResourceAsStream("/SHPinscher-Regular.otf");
            if (is == null) {
                throw new IOException("Font resource not found.");
            }
            // Creates the font and adds t to the system
            customFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont((float) fontSize);
            GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
            graphicsEnvironment.registerFont(customFont);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            return new Font("Courier New", Font.BOLD, fontSize);
        }
        return customFont;
    }

    /**
     * Creates the layered pane, adds a background and the title.
     * @param title Title at the top of the panel
     * @param backgroundLink Image at the background
     * @return JLayeredPane
     */
    public JLayeredPane createBasePanel(String title, String backgroundLink) {
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1000, 750));

        // ADD BACKGROUND
        ImageIcon backgroundImage = new ImageIcon(getClass().getResource(backgroundLink));
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, 1000, 750);
        layeredPane.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);

        foregroundPanel = new JPanel(null);
        foregroundPanel.setOpaque(false);
        foregroundPanel.setBounds(0, 0, 1000, 750);

        // ADD TITLE
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(titleFont);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setVerticalAlignment(SwingConstants.TOP);
        titleLabel.setBounds(0, 0, 1000, 55);
        titleLabel.setForeground(titleColor);
        foregroundPanel.add(titleLabel);
        layeredPane.add(foregroundPanel, JLayeredPane.PALETTE_LAYER);

        // BUILDS CLOSE BUTTON AND HANDLES WHEN IT IS SELECTED
        JButton closeButton = addTinyButton("X", 920, 5);
        closeButton.addActionListener(e -> {
            mainApp.db.close();
            Window window = SwingUtilities.getWindowAncestor(layeredPane);
            if (window != null) {
                window.dispose();
            }
        });

        return layeredPane;
    }

    /**
     * Creates a button, adds image icon and adds to layered pane
     * @param imagePath String location of the image
     * @param buttonText String text to go on the button
     * @param x int x location
     * @param y int y location
     * @param width int width of the button
     * @param height int height of the button
     * @param font Font used for the text
     * @return JButton
     */
    public JButton addButton(String imagePath, String buttonText, int x, int y, int width, int height, Font font) {
        JButton button = new JButton(buttonText);
        ImageIcon icon = new ImageIcon(getClass().getResource(imagePath));

        // CREATE IMAGE SCALED
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING);
        icon = new ImageIcon(scaledImg);

        // DESIGN BUTTON WTH IMAGE
        button.setIcon(icon);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.CENTER);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setForeground(Color.DARK_GRAY);
        button.setFont(font);
        button.setBounds(x, y, width, height);

        foregroundPanel.add(button);

        return button;
    }

    /**
     * Calls addButton to build small button
     * @param buttonText String text for the button
     * @param x int x location
     * @param y int y location
     * @return JButton
     */
    public JButton addTinyButton(String buttonText, int x, int y) {
        return this.addButton("/smallButton.png", buttonText, x, y, 50, 50, smallButtonFont);
    }

    /**
     * Calls addButton to build small button
     * @param buttonText String text for the button
     * @param x int x location
     * @param y int y location
     * @return JButton
     */
    public JButton addSmallButton(String buttonText, int x, int y) {
        return this.addButton("/smallButton.png", buttonText, x, y, 180, 50, smallButtonFont);
    }

    /**
     * Calls addButton to build medium button
     * @param buttonText String text for the button
     * @param x int x location
     * @param y int y location
     * @return JButton
     */
    public JButton addMediumButton(String buttonText, int x, int y) {
        return this.addButton("/MediumButton.png", buttonText, x, y, 363, 113, bigFont);
    }

    /**
     * Calls addButton to build large button
     * @param buttonText String text for the button
     * @param x int x location
     * @param y int y location
     * @return JButton
     */
    public JButton addLargeButton(String buttonText, int x, int y) {
        return this.addButton("/longButton_Down.png", buttonText, x, y, 600, 135,
                bigFont);
    }

    /**
     * Creates a listPanel, adds each Pokémon to the list panel with createPokémonListItem,
     * creates a scroll pane, adds list panel to it and adds it to the foreground panel
     * @param pokemonDB List of Pokémon
     * @param filteredPokemonList List of Filtered Pokémon
     * @return JScrollPane
     */
    public JScrollPane addPokemonList(List<Pokemon> pokemonDB, List<Pokemon> filteredPokemonList){
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setOpaque(false);

        if (filteredPokemonList == null) {
            // Adds each Pokémon to the list panel using createPokémonListItem
            for (Pokemon pokemon : pokemonDB) {
                listPanel.add(createPokemonListItem(pokemon, pokemonDB));
            }
        } else {
            for (Pokemon pokemon : filteredPokemonList) {
                listPanel.add(createPokemonListItem(pokemon, pokemonDB));
            }
        }

        listPanel.setAlignmentY(Component.TOP_ALIGNMENT);

        // Create JScrollPane and designs it using the CustomGUIDesigns
        pokemonScrollPane = new JScrollPane(listPanel);
        pokemonScrollPane.setBounds(100, 110, 800, 550);
        pokemonScrollPane.setOpaque(false);
        pokemonScrollPane.getViewport().setOpaque(false);
        pokemonScrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        pokemonScrollPane.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
        pokemonScrollPane.setBorder(null);

        foregroundPanel.add(pokemonScrollPane);

        return pokemonScrollPane;
    }

    /**
     * Takes the scrollPane that was created above and replaces the list with the new list give
     * in the parameter
     * @param convertedPokemonDB List of Pokémon converted from the DB
     * @param filteredPokemonList List of Filtered Pokémon
     * @return
     */
    public JScrollPane updatePokemonList(List<Pokemon> convertedPokemonDB,
                                         List<Pokemon> filteredPokemonList) {
        // If there is no scroll pane, call addPokémonList instead
        if (pokemonScrollPane == null) {
            addPokemonList(convertedPokemonDB, filteredPokemonList);
            return pokemonScrollPane;
        }

        // Creates a new listPanel to add to the scrollPane
        JPanel newListPanel = new JPanel();
        newListPanel.setLayout(new BoxLayout(newListPanel, BoxLayout.Y_AXIS));
        newListPanel.setOpaque(false);

        // Adds each Pokémon to the list panel using createPokémonListItem
        for (Pokemon pokemon : filteredPokemonList) {
            newListPanel.add(createPokemonListItem(pokemon, convertedPokemonDB));
        }

        pokemonScrollPane.setViewportView(newListPanel);

        return pokemonScrollPane;
    }

    /**
     * Creates a panel, adds an image depending on if the Pokémon has been caught or not, adds
     * the Pokédex Number and Pokémon Name, adds a button so that it will go to the specified
     * Pokémon page if clicked.
     * @param pokemon Specific Pokémon
     * @param pokemonDB List of Pokémon converted from DB
     * @return JPanel
     */
    public JPanel createPokemonListItem(Pokemon pokemon, List<Pokemon> pokemonDB) {
        // CREATE PANEL FOR POKÉMON IN LIST
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(350, 65));
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 65));
        panel.setOpaque(false);

        // ADD IMAGE BASED ON CAUGHT STATUS
        String imagePath = pokemon.getPokemonIsCaught() ? "/Caught.png" : "/NotCaught.png";
        ImageIcon icon = new ImageIcon(getClass().getResource(imagePath));
        JLabel imageLabel = new JLabel(icon);
        imageLabel.setPreferredSize(new Dimension(57, 58));
        imageLabel.setOpaque(false);

        // ADD POKÉDEX NUMBER AND POKÉMON NAME
        JLabel textLabel =
                new JLabel(String.format( "   %04d", pokemon.getPokedexNumber()) + " " + pokemon.getPokemonName());
        textLabel.setFont(pokemonListFont);
        textLabel.setOpaque(false);

        // ADD THE IMAGE AND LABEL TO THE PANEL
        panel.add(imageLabel, BorderLayout.WEST);
        panel.add(textLabel, BorderLayout.CENTER);

        // CHANGE HAND CURSOR IF HOVERING OVER OPTION
        panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        // IF MOUSE CLICKS ONTO THE PANEL IT GOES TO THE POKÉMON INFO PAGE
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainApp.goToPage(new PokemonInfoPage(mainApp, pokemon, pokemonDB).getMainPanel());
            }
        });
        return panel;
    }

    /**
     * Calls the list of Pokémon in the database and creates a List<Pokémon> out of it.
     * @param pokemonDatabase ArrayList<ArrayList<Object>> from the database
     * @return List<Pokémon> of the database Pokémon
     */
    public List<Pokemon> convertToPokemonList(ArrayList<ArrayList<Object>> pokemonDatabase) {
        List<Pokemon> convertedList = new ArrayList<>();

        // Goes through each row and creates a new Pokémon out of it.
        for (ArrayList<Object> row : pokemonDatabase) {
            int pokedexNumber = parseInt(row.get(0).toString());
            String pokemonName = row.get(1).toString();
            Integer nextEvoLevel = null;
            if (row.get(2) != null) {
                nextEvoLevel = parseInt(row.get(2).toString());
            }
            BigDecimal pokemonWeight = new BigDecimal(row.get(3).toString());
            BigDecimal pokemonHeight = new BigDecimal(row.get(4).toString());
            Boolean hasBeenCaught = parseInt(row.get(5).toString()) != 0;
            String pokedexEntry = null;
            if (row.get(6) != null) {
                pokedexEntry = row.get(6).toString();
            }
            String primaryTypeString = row.get(7) != null ? row.get(7).toString() : "0";
            String secondaryTypeString = row.get(8) != null ? row.get(8).toString() : "0";

            // Converts the Type to the type class
            PokemonTypes primaryType;
            if (primaryTypeString != null && !primaryTypeString.isEmpty()) {
                primaryType = PokemonTypes.fromString(
                        mainApp.db.types.getTypeNameByID(Integer.valueOf(primaryTypeString))
                );
            } else {
                primaryType = null;
            }

            // Checks to see if there is a second type. Makes it null if there isn't, converts it
            // to a Type if it exists
            PokemonTypes secondaryType = null;
            if (secondaryTypeString != null || secondaryTypeString != "null") {
                if (!secondaryTypeString.isEmpty()
                        && !secondaryTypeString.equals("0")) {
                    secondaryType =
                            PokemonTypes.fromString(mainApp.db.types.getTypeNameByID(Integer.valueOf(secondaryTypeString)));
                }
            }

            // Converts both types to PokemonTypesManager
            PokemonTypesManager pokemonTypes;
             if (secondaryType != null) {
                pokemonTypes = new PokemonTypesManager(primaryType, secondaryType);
            } else {
                 pokemonTypes = new PokemonTypesManager(primaryType);
             }

            // Creates the Pokémon and adds to the new list.
            Pokemon pokemon = new Pokemon(
                    pokemonName, pokedexNumber, pokemonTypes, nextEvoLevel,
                    pokemonWeight, pokemonHeight, hasBeenCaught, pokedexEntry
            );

            convertedList.add(pokemon);
        }

        return convertedList;
    }

    /**
     * Used to build a label and add to panel with text specified
     * @param labelText String Text for the label
     * @param x int x location
     * @param y int y location
     * @param width int width
     * @return JLabel
     */
    public JLabel addLabel(String labelText, int x, int y, int width) {
        JLabel label = new JLabel(labelText);
        label.setFont(labelFont);
        label.setForeground(Color.DARK_GRAY);
        label.setBounds(x, y, width, 30);

        foregroundPanel.add(label);
        return label;
    }

    /**
     * Used to build a label and add to panel with text specified and height, font size and color
     * specified.
     * @param labelText String Text for the label
     * @param x int x location
     * @param y int y location
     * @param width int width of label
     * @param height int height of label
     * @param fontSize int font size for the label text
     * @param fontColor Color for the font
     * @return JLabel
     */
    public JLabel addLabelWithSpecifics(String labelText, int x, int y, int width, int height,
                                    int fontSize, Color fontColor) {
        JLabel label = new JLabel(labelText);
        label.setFont(getFont(fontSize));
        label.setForeground(fontColor);
        label.setBounds(x, y, width, height);

        foregroundPanel.add(label);
        return label;
    }

    /**
     * Used to build a label and add to panel with an Image scaled (using addImageScaled)
     * @param labelText String Text for the label
     * @param x int x location
     * @param y int y location
     * @param width int width of label
     * @param height int height of label
     * @return JLabel
     */
    public JLabel addLabelWithImage(String labelText, int x, int y, int width, int height) {
        // CREATE LABEL WITH TEXT SPECIFIED
        JLabel successLabel = new JLabel(labelText);
        successLabel.setFont(successFont);
        successLabel.setForeground(Color.DARK_GRAY);
        successLabel.setBounds(x, y, width, height);
        successLabel.setPreferredSize(new Dimension(width, height));
        successLabel.setOpaque(false);

        // ADD LABEL AND SCALED IMAGES TO THE FOREGROUND PANEL
        foregroundPanel.add(successLabel);
        foregroundPanel.add(addImageScaled("/SuccessBox.png", x, y, width, height));

        return successLabel;
    }

    /* Method Name: addErrorLabel
     * Purpose: used to build a label and add to panel (red and smaller)
     * Parameters: Label Text, Location(x/y), and width
     * Return Value: JLabel
     */

    /**
     * Used to build a label and add to panel (red and smaller)
     * @param x int x location
     * @param y int y location
     * @param width int width of label
     * @return JLabel
     */
    public JLabel addErrorLabel(int x, int y, int width) {
        JLabel errLabel = new JLabel();
        errLabel.setFont(errorFont);
        errLabel.setForeground(pokemonRed);
        errLabel.setBounds(x, y + 48, width, 35);

        foregroundPanel.add(errLabel);
        return errLabel;
    }

    /**
     * Used to build a text field that the user can enter information into
     * @param x int x location
     * @param y int y location
     * @param width int width of textField
     * @return JTextField
     */
    public JTextField addTextField(int x, int y, int width) {
        JTextField field = new JTextField();
        field.setBounds(x, y + 27, width, 28);
        field.setFont(inputFont);
        field.setBorder(BorderFactory.createLineBorder(pokemonRed));

        foregroundPanel.add(field);
        return field;
    }

    /* Method Name: addSearchField
     * Purpose: used to build a text field that the user can enter information into. Larger to
     * look nicer with the Pokémon List
     * Parameters: Location(x/y) and size(width/height)
     * Return Value: JTextField
     */

    /**
     * Used to build a text field that the user can enter information into. Larger to look nicer
     * with the Pokémon List
     * @param x int x location
     * @param y int y location
     * @param width int width of textField
     * @param height int height of TextField
     * @return JTextField
     */
    public JTextField addSearchField(int x, int y, int width, int height) {
        JTextField field = new JTextField();
        field.setBounds(x, y, width, height);
        field.setFont(smallButtonFont);
        field.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

        foregroundPanel.add(field);
        return field;
    }

    /**
     * Used to build a text area that the user can enter information into. It is taller so that
     * the user can enter more text into the field.
     * @param x int x location
     * @param y int y location
     * @param width int width of TextArea
     * @return JTextArea
     */
    public JTextArea addTextArea(int x, int y, int width) {
        // CREATE TEXT AREA
        JTextArea textArea = new JTextArea(10, 20);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(inputFont);

        // ADD A SCROLL PANE IF THE USER ENTERS MORE TEXT
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(x, y + 27, width, 80);
        scrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        scrollPane.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
        scrollPane.setBorder(BorderFactory.createLineBorder(pokemonRed));

        foregroundPanel.add(scrollPane);
        return textArea;
    }

    /**
     * Used to build a dropdown that looks nicer to match the UI
     * @param options List of Strings that has all the options
     * @param x int x location
     * @param y int y location
     * @param width int width of ComboBox
     * @return JComboBox<String>
     */
    public JComboBox<String> addDropdown(String[] options, int x, int y, int width) {
        // CREATE THE DROPDOWN WITH SPECIFIED OPTIONS
        JComboBox<String> dropdown = new JComboBox<>(options);
        dropdown.setBounds(x, y + 27, width, 30);
        dropdown.setFont(inputFont);
        dropdown.setEditable(true);

        // CREATES AN EDITOR TO ALLOW FOR DESIGN EDITING OF THE DROPDOWN
        JTextField editor = (JTextField) dropdown.getEditor().getEditorComponent();
        editor.setBackground(Color.WHITE);
        editor.setForeground(Color.BLACK);
        editor.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));

        // CHANGES THE DROPDOWN DESIGN TO USE THE CUSTOM GUI DESIGNS
        dropdown.setRenderer(new CustomComboBoxRenderer());
        dropdown.setUI(new CustomComboBoxUI());
        dropdown.setBorder(BorderFactory.createLineBorder(pokemonRed));

        foregroundPanel.add(dropdown);
        return dropdown;
    }

    /**
     * Creates an image icon with location and size padding to scale it to the location and size
     * given
     * @param imagePath String of the path location
     * @param x int x location
     * @param y int y location
     * @param width int width of textField
     * @param height int height of TextField
     * @return JLabel
     */
    public JLabel addImageScaled(String imagePath, int x, int y, int width, int height) {
        // PADDING FOR IMAGE
        int imagePadding = 20;
        int imageX = x - imagePadding / 2 - 68;
        int imageY = y - imagePadding / 2;
        int imageWidth = width + imagePadding;
        int imageHeight = height + imagePadding;

        // CREATE IMAGE
        JLabel imageLabel = new JLabel();
        imageLabel.setBounds(imageX, imageY, imageWidth, imageHeight);

        // ATTEMPTS TO GET AND SET IMAGE
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource(imagePath));
            Image scaledImage = icon.getImage().getScaledInstance(width, height,
                    Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaledImage));
            imageLabel.setOpaque(false);
        } catch (Exception e) {
            System.err.println("Failed to load image: " + imagePath);
        }

        return imageLabel;
    }

    /**
     * Creates an image icon with location and size
     * @param imagePath String of the path location
     * @param x int x location
     * @param y int y location
     * @param width int width of Label
     * @param height int height of Label
     * @return JLabel
     */
    public JLabel addImageIcon(String imagePath, int x, int y, int width, int height) {
        // CREATES IMAGE ICON
        ImageIcon icon = new ImageIcon(getClass().getResource(imagePath));
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING);
        icon = new ImageIcon(scaledImg);

        // CREATES AN IMAGE LABEL
        JLabel imageLabel = new JLabel(icon);
        imageLabel.setBounds(x, y, width, height);

        foregroundPanel.add(imageLabel);
        return imageLabel;
    }

    /**
     * Adds an image behind text
     * Returns void since it just adds it to the panel
     * @param imagePath String of the path location
     * @param x int x location
     * @param y int y location
     * @param width int width of Image
     * @param height int height of Image
     */
    public void addTextBackgroundImage(String imagePath, int x, int y, int width, int height) {
        foregroundPanel.add(addImageScaled(imagePath, x, y, width, height));
    }

    /**
     * Checks through a string. Checking if each char is either a digit. Returns true if all are
     * either a digit.
     * @param input String to check
     * @return Boolean of results
     */
    public boolean isDigit(String input) {
        return input.chars().allMatch(Character::isDigit);
    }

    /**
     * Checks through a string. Checking if each char is either a digit or a period. Returns true
     * if all are either a digit or period.
     * @param input String to check
     * @return Boolean of results
     */
    public boolean isDigitOrPeriod(String input) {
        return input.chars().allMatch(c -> Character.isDigit(c) || c == '.');
    }

}
