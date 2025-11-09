// Autumn Skye
// CEN-3024C 13950
// November 8th, 2025
package org.pokemondatabase.GUI.CustomGUIDesigns;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

/**
 * <header>PURPOSE/PRIMARY FUNCTION</header>
 * <p>
 * Used to create a custom scroll bar and the track for the lists.
 * </p>
 * <header>IMPORTANT DEPENDENCIES</header>
 * <ul>
 *     <li>GuiHelper: Called by this class to create the scroll bar.</li>
 *     <li>CustomComboBoxUI: Called by this class to create the scroll bar.</li>
 *     <li>BasicScrollBarUI: Extends this class.</li>
 * </ul>
 * <header>CONTAINS</header>
 * <ul>
 *      <li>configureScrollBarColors</li>
 *      <li>paintThumb</li>
 *      <li>getPreferredSize</li>
 *      <li>paintTrack</li>
 *      <li>createDecreaseButton</li>
 *      <li>createIncreaseButton</li>
 * </ul>
 */
public class CustomScrollBarUI extends BasicScrollBarUI {
    /** A color constant: Pokémon blue theme (RGB: 88, 112, 248). */
    public final Color pokemonBlue = new Color(88, 112, 248);
    /** A color constant: Pokémon Dark blue theme (RGB: 42, 61, 172). */
    public final Color pokemonDarkBlue = new Color(42, 61, 172);
    private final int scrollBarCorners = 12;

    /**
     * OVERRIDE
     * Used to detail the colors of the scroll bar and track
     * Returns void (I am hoping it is okay to return void for the design side!)
     */
    @Override
    protected void configureScrollBarColors() {
        thumbColor = pokemonBlue;
        trackColor = Color.WHITE;
        thumbDarkShadowColor = pokemonDarkBlue;
        thumbHighlightColor = pokemonBlue;
        thumbLightShadowColor = pokemonDarkBlue;
    }

    /**
     * OVERRIDE
     * Used to detail the colors of the scroll bar and track
     * Returns void (I am hoping it is okay to return void for the design side!)
     * @param g the graphics
     * @param c the component
     * @param scrollBarBounds the thumb bounds
     */
    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle scrollBarBounds) {
        // Creates scroll bar base
        Graphics2D scrollBarBase = (Graphics2D) g.create();
        scrollBarBase.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        scrollBarBase.setColor(thumbColor != null ? thumbColor : Color.GRAY);

        // SCROLL BAR RECTANGLE
        scrollBarBase.fillRoundRect(
                scrollBarBounds.x,
                scrollBarBounds.y,
                scrollBarBounds.width,
                scrollBarBounds.height,
                scrollBarCorners,
                scrollBarCorners
        );

        scrollBarBase.dispose();
    }

    /**
     * OVERRIDE
     * Used to override the getPreferredSize method to specify the size I would prefer the arrow
     * to be.
     * @param c the <code>JScrollBar</code> that's delegating this method to us
     * @return Dimensions
     */
    @Override
    public Dimension getPreferredSize(JComponent c) {
        if (scrollbar.getOrientation() == JScrollBar.VERTICAL) {
            return new Dimension(24, super.getPreferredSize(c).height);
        } else {
            return new Dimension(super.getPreferredSize(c).width, 24);
        }
    }

    /**
     * OVERRIDE
     * Used to override the getPreferredSize method to specify the size and design of the scroll
     * bar track.
     * Returns void (I am hoping it is okay to return void for the design side!)
     * @param g the graphics
     * @param c the component
     * @param trackBounds the track bounds
     */
    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        // Creates scroll bar track base
        Graphics2D trackBoundsBase = (Graphics2D) g.create();
        trackBoundsBase.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        trackBoundsBase.setColor(Color.WHITE);

        // Used to keep the entire track one color
        trackBoundsBase.fillRoundRect(
                trackBounds.x,
                trackBounds.y,
                trackBounds.width,
                trackBounds.height,
                0,
                0
        );

        trackBoundsBase.dispose();
    }

    /**
     * Used to override the createIncreaseButton and calls the CustomScrollBarArrowButton with
     * the up direction
     * @param orientation the orientation
     * @return JButton
     */
    @Override
    protected JButton createIncreaseButton(int orientation) {
        return new CustomScrollBarArrowButton(orientation, pokemonBlue);
    }

    /**
     * OVERRIDE
     * Used to override the createDecreaseButton and calls the CustomScrollBarArrowButton with
     * the down direction
     * @param orientation the orientation
     * @return JButton
     */
    @Override
    protected JButton createDecreaseButton(int orientation) {
        return new CustomScrollBarArrowButton(orientation, pokemonBlue);
    }
}
