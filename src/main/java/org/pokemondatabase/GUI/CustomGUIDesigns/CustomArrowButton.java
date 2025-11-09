// Autumn Skye
// CEN-3024C 13950
// November 8th, 2025
package org.pokemondatabase.GUI.CustomGUIDesigns;

import javax.swing.*;
import java.awt.*;

/**
 * <h>PURPOSE/PRIMARY FUNCTION</h>
 * <p>
 * Used to create a custom arrow for the drop-down and Pokémon List. Makes the button wider and
 * bigger.
 * </p>
 * <h>IMPORTANT DEPENDENCIES</h>
 * <ul>
 *     <li>CustomComboBoxUI: Called by this class to create the arrow.</li>
 *     <li>JButton: Extends this class.</li>
 * </ul>
 * <h>CONTAINS</h>
 * <ul>
 *     <li>Constructor</li>
 *     <li>paintComponent</li>
 *     <li>getPreferredSize</li>
 * </ul>
 */
class CustomArrowButton extends JButton {
    private final Color arrowColor;

    /**
     * Class Constructor
     * Used to create a new arrow
     * @param arrowColor Color Specified color of the arrow
     */
    public CustomArrowButton(Color arrowColor) {
        this.arrowColor = arrowColor;
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setOpaque(false);
    }

    /* Method Name: paintComponent @OVERRIDE
     * Purpose: used to build the arrow. Add color and detail how the arrow is shaped
     * Parameters: Graphics (To build the arrow on)
     * Return Value: void (I am hoping it is okay to return void for the design side!)
     */

    /**
     * OVERRIDE
     * Used to build the arrow. Add color and detail how the arrow is shaped
     * Returns void (I am hoping it is okay to return void for the design side!)
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int w = getWidth();
        int h = getHeight();

        Graphics2D arrowBase = (Graphics2D) g.create();
        arrowBase.setColor(arrowColor);
        arrowBase.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Arrow Size
        int arrowWidth = 23;
        int arrowHeight = 14;

        // Arrow Location
        int xCenter = w / 2;
        int yCenter = h / 2;

        // Builds the Arrow
        // POINTS
        int[] xPoints = {
                xCenter - arrowWidth / 2, // LEFT
                xCenter + arrowWidth / 2, // RIGHT
                xCenter                   // BOTTOM
        };
        // SIDES
        int[] yPoints = {
                yCenter - arrowHeight / 2, // LEFT
                yCenter - arrowHeight / 2, // RIGHT
                yCenter + arrowHeight / 2  // BOTTOM
        };

        arrowBase.fillPolygon(xPoints, yPoints, 3);
        arrowBase.dispose();
    }

    /* Method Name: getPreferredSize @OVERRIDE
     * Purpose: used to override the getPreferredSize method to specify the size I would prefer
     * the arrow to be.
     * Parameters: None
     * Return: Dimensions
     */

    /**
     * OVERRIDE
     * Used to override the getPreferredSize method to specify the size I would prefer the arrow
     * to be.
     * @return The dimensions of the arrow
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(30, 25);
    }
}
