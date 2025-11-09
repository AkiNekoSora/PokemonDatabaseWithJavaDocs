// Autumn Skye
// CEN-3024C 13950
// November 8th, 2025
package org.pokemondatabase;

/**
 * <header>PURPOSE/PRIMARY FUNCTION</header>
 * <p>
 * Used for the colors so that it can make it easier to understand the options and titles in the
 * CMI.
 * </p>
 * <header>IMPORTANT DEPENDENCIES</header>
 * <ul>
 *     <li>All CLI classes: Call this to make the CLI text a bit easier to read.</li>
 * </ul>
 */
public class Text {
    /**
     * Defines ANSI color constants for coloring text in the CLI.
     * <p>
     * These values can be used to modify text color in the CLI.
     * </p>
     * <ul>
     *     <li>{@code RESET} – Resets all text formatting to default.</li>
     *     <li>{@code BRIGHT_RED} – Bright red text color (Errors or warnings).</li>
     *     <li>{@code GREEN} – Bright green text color (Success messages).</li>
     *     <li>{@code BLUE} – Bright blue text color.</li>
     *     <li>{@code MAGENTA} – Magenta text color.</li>
     *     <li>{@code CYAN} – Cyan text color.</li>
     * </ul>
     */
    public final String RESET = "\u001B[0m";
    public final String BRIGHT_RED = "\u001B[31;1m";
    public final String GREEN = "\u001B[32;1m";
    public final String BLUE = "\u001B[34;1m";
    public final String MAGENTA = "\u001B[35m";
    public final String CYAN = "\u001B[36m";
}
