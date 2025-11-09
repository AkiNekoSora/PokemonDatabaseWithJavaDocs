// Autumn Skye
// CEN-3024C 13950
// November 8th, 2025
package org.pokemondatabase.GUI.CustomGUIDesigns;

import javax.swing.*;
import java.awt.*;

/**
 * <header>PURPOSE/PRIMARY FUNCTION</header>
 * <p>
 * Used to create a custom arrow for the drop-down and Pokémon List. Changes the button direction
 * based on the direction sent to the constructor.
 * </p>
 * <header>IMPORTANT DEPENDENCIES</header>
 * <ul>
 *     <li>CustomComboBoxUI: Called by this class to create the scroll bar arrow.</li>
 *     <li>JButton: Extends this class.</li>
 * </ul>
 * <header>CONTAINS</header>
 * <ul>
 *      <li>Constructor</li>
 *      <li>paintComponent</li>
 *      <li>getPreferredSize</li>
 * </ul>
 */
class CustomScrollBarArrowButton extends JButton {
    private final int direction;
    private final Color arrowColor;

    /**
     * Class Constructor
     * Used to create a Custom Scroll Bar Arrow Button
     * @param direction Int The direction the arrow should go
     * @param arrowColor Color color of the arrow
     */
    public CustomScrollBarArrowButton(int direction, Color arrowColor) {
        this.direction = direction;
        this.arrowColor = arrowColor;
        setOpaque(true);
        setContentAreaFilled(false);
        setBorderPainted(false);
    }

    /**
     * OVERRIDE
     * Used to build the arrow direction.
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int w = getWidth();
        int h = getHeight();

        // ARROW BASE
        Graphics2D arrowBase = (Graphics2D) g.create();
        arrowBase.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        arrowBase.setColor(Color.WHITE);
        arrowBase.fillRect(0, 0, w, h);
        arrowBase.setColor(arrowColor);
        Polygon arrow = new Polygon();

        // ARROW LOCATION
        int centerX = w / 2;
        int centerY = h / 2;

        // Switch used to accept the direction and change the direction of the arrow based on it.
        // I only use 2 directions, but I thought having them all would be safer!
        switch (direction) {
            case SwingConstants.NORTH: // UP
                arrow.addPoint(centerX, centerY - 6);
                arrow.addPoint(centerX - 10, centerY + 4);
                arrow.addPoint(centerX + 10, centerY + 4);
                break;

            case SwingConstants.SOUTH: // DOWN
                arrow.addPoint(centerX, centerY + 6);
                arrow.addPoint(centerX - 10, centerY - 4);
                arrow.addPoint(centerX + 10, centerY - 4);
                break;

            case SwingConstants.WEST: // LEFT
                arrow.addPoint(centerX - 6, centerY);
                arrow.addPoint(centerX + 4, centerY - 10);
                arrow.addPoint(centerX + 4, centerY + 10);
                break;

            case SwingConstants.EAST: // RIGHT
                arrow.addPoint(centerX + 6, centerY);
                arrow.addPoint(centerX - 4, centerY - 10);
                arrow.addPoint(centerX - 4, centerY + 10);
                break;
        }

        arrowBase.fill(arrow);
        arrowBase.dispose();
    }

    /**
     * OVERRIDE
     * Used to override the getPreferredSize method to specify the size I would prefer
     * @return The dimensions
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(40, 30);
    }

}
