// Autumn Skye
// CEN-3024C 13950
// November 8th, 2025
package org.pokemondatabase.GUI.CustomGUIDesigns;

import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.*;
import javax.swing.plaf.basic.ComboPopup;
import java.awt.*;

/**
 * Used to as the base to call all other custom design choices. (Scroll Lists)
 * </p>
 * <h2>IMPORTANT DEPENDENCIES</h2>
 * <ul>
 *     <li>GuiHelper: Called by this class to create the scroll bar.</li>
 *     <li>BasicComboBoxUI: Extends this class.</li>
 * </ul>
 */
public class CustomComboBoxUI extends BasicComboBoxUI {
    /** A color constant: Pokémon blue theme (RGB: 88, 112, 248). */
    public final Color pokemonBlue = new Color(88, 112, 248);

    /**
     * OVERRIDE
     * Used to call the customArrowButton class to create the arrow for the GUI. Sending the
     * specified color.
     * @return JButton
     */
    @Override
    protected JButton createArrowButton() {
        JButton button = new CustomArrowButton(pokemonBlue);
        button.setBackground(Color.WHITE);
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        button.setFocusPainted(false);
        button.setContentAreaFilled(true);

        return button;
    }

    /**
     * OVERRIDE
     * Used to create the popup for the drop-down. Calls the CustomScrollBarUI and set the
     * scroller to the design specified and the background White. Contains createScroller method.
     * @return ComboPopup
     */
    @Override
    protected ComboPopup createPopup() {
        BasicComboPopup popup = new BasicComboPopup(comboBox) {
            /**
             * OVERRIDE
             * Calls the CustomScrollBarUI and set the scroller to the design specified and the
             * background White. Contains createScroller method.
             * @return JScrollPane
             */
            @Override
            protected JScrollPane createScroller() {
                JScrollPane scrollPane = super.createScroller();
                scrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
                scrollPane.getVerticalScrollBar().setBackground(Color.WHITE);
                return scrollPane;
            }
        };
        return popup;
    }
}
