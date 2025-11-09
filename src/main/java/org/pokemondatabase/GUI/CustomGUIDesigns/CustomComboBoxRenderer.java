// Autumn Skye
// CEN-3024C 13950
// November 8th, 2025
package org.pokemondatabase.GUI.CustomGUIDesigns;

import javax.swing.*;
import java.awt.*;

/**
 * <p>
 * Extends DefaultListCellRenderer
 * Used to change how the JList elements are displayed (Drop-Down and Pokémon List)
 * </p>
 * <h2>IMPORTANT DEPENDENCIES</h2>
 * <ul>
 *     <li>GuiHelper: Called by this class to create the combo box renderer.</li>
 *     <li>DefaultListCallRender: Extends this class.</li>
 * </ul>
 */
public class CustomComboBoxRenderer extends DefaultListCellRenderer {
    /**
     * OVERRIDE
     * Used to override the getListCellRendererComponent method to which is called for each list
     * item to display them how it is specified below.
     * @param list The JList we're painting.
     * @param value The value returned by list.getModel().getElementAt(index).
     * @param index The cells index.
     * @param isSelected True if the specified cell was selected.
     * @param cellHasFocus True if the specified cell has the focus.
     * @return Component label
     */
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                  boolean isSelected, boolean cellHasFocus) {
        JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        // Changes the colors based on if the item is selected
        if (isSelected) {
            label.setBackground(Color.RED);
            label.setForeground(Color.WHITE);
        } else {
            label.setBackground(Color.WHITE);
            label.setForeground(Color.DARK_GRAY);
        }

        return label;
    }
}