// Autumn Skye
// CEN-3024C 13950
// November 8th, 2025
package org.pokemondatabase.DBHelper;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

/**
 * <p>
 * Used for the Pokémon table to accept SQL queries (with execute and getExecuteResults) and
 * prepare the SQL queries using Select, insert, update, delete, and selectToTable. Holds all
 * current column types as variables.
 * </p>
 * <h2>IMPORTANT DEPENDENCIES</h2>
 * <ul>
 *     <li>
 *         DBHelper: DBHelper calls this class to bring it into scope when other classes call the
 *         DBHelper.
 *     </li>
 * </ul>
 * <h2>Created using the SQLHelper GUI</h2>
 */
public class Pokemon_DBHelper {
	private final String TABLE_NAME = "Pokemon";

	/**
	 * Defines constant values for the Pokémon database columns.
	 * <p>
	 * These constants are used throughout the application to reference database fields.
	 * </p>
	 * <ul>
	 *     <li>{@code pokedex_number} – Pokémon’s unique Pokédex number.</li>
	 *     <li>{@code pokemon_name} – Pokémon’s name.</li>
	 *     <li>{@code next_evolution_level} – What level the Pokémon evolves next.</li>
	 *     <li>{@code weight} – Pokémon’s weight.</li>
	 *     <li>{@code height} – Pokémon’s height.</li>
	 *     <li>{@code has_been_caught} – States if the Pokémon has been caught.</li>
	 *     <li>{@code pokedex_entry} – Pokédex description of the Pokémon.</li>
	 *     <li>{@code primary_type} – Pokémon’s primary type.</li>
	 *     <li>{@code secondary_type} – Pokémon’s secondary type (if any).</li>
	 * </ul>
	 */
	public static final String pokedex_number = "pokedex_number";
	public static final String pokemon_name = "pokemon_name";
	public static final String next_evolution_level = "next_evolution_level";
	public static final String weight = "weight";
	public static final String height = "height";
	public static final String has_been_caught = "has_been_caught";
	public static final String pokedex_entry = "pokedex_entry";
	public static final String primary_type = "primary_type";
	public static final String secondary_type = "secondary_type";

	private final DBHelper helper;

	/**
	 * Class Constructor
	 * Used to bring DBHelper into the scope
	 * @param dbHelper Database Helper used to give access to the Database
	 */
	public Pokemon_DBHelper(DBHelper dbHelper) {
		this.helper = dbHelper;
	}

	/**
	 * prepares the text of a SQL "select" command.
	 * @param fields the fields to be displayed in the output
	 * @param whatField field to search for
	 * @param whatValue value to search for within whatField
	 * @param sortField use ASC or DESC to specify the sorting order
	 * @param sort the field that the data will be sorted by
	 * @return String with the Prepared SQL Query
	 */
	private String prepareSQL(String fields, String whatField, String whatValue, String sortField, String sort) {
		String query = "SELECT ";
		query += fields == null ? " * FROM " + TABLE_NAME : fields + " FROM " + TABLE_NAME;
		query += whatField != null && whatValue != null ? " WHERE " + whatField + " = \"" + whatValue + "\"" : "";
		query += sort != null && sortField != null ? " order by " + sortField + " " + sort : "";
		return query;
	}

	/**
	 * Insert a new record into the database
	 * @param pokedex_number String Pokédex Number
	 * @param pokemon_name String Pokémon Name
	 * @param next_evolution_level Integer Pokémon next evolution level
	 * @param weight String Pokémon Weight
	 * @param height String Pokémon Height
	 * @param has_been_caught Integer Pokémon Caught status
	 * @param pokedex_entry String Pokédex entry
	 * @param primary_type Integer Pokémon Primary Type
	 * @param secondary_type Integer Pokémon Secondary Type
	 */
	public void insert(Integer pokedex_number, String pokemon_name, Integer next_evolution_level, String weight, String height, Integer has_been_caught, String pokedex_entry, Integer primary_type, Integer secondary_type) {
		pokemon_name = pokemon_name != null ? "\"" + pokemon_name + "\"" : null;
		weight = weight != null ? "\"" + weight + "\"" : null;
		height = height != null ? "\"" + height + "\"" : null;
		pokedex_entry = pokedex_entry != null ? "\"" + pokedex_entry + "\"" : null;
		
		Object[] values_ar = {pokedex_number, pokemon_name, next_evolution_level, weight, height, has_been_caught, pokedex_entry, primary_type, secondary_type};
		String[] fields_ar = {Pokemon_DBHelper.pokedex_number, Pokemon_DBHelper.pokemon_name, Pokemon_DBHelper.next_evolution_level, Pokemon_DBHelper.weight, Pokemon_DBHelper.height, Pokemon_DBHelper.has_been_caught, Pokemon_DBHelper.pokedex_entry, Pokemon_DBHelper.primary_type, Pokemon_DBHelper.secondary_type};
		String values = "", fields = "";
		for (int i = 0; i < values_ar.length; i++) {
			if (values_ar[i] != null) {
				values += values_ar[i] + ", ";
				fields += fields_ar[i] + ", ";
			}
		}
		if (!values.isEmpty()) {
			values = values.substring(0, values.length() - 2);
			fields = fields.substring(0, fields.length() - 2);
			helper.execute("INSERT INTO " + TABLE_NAME + "(" + fields + ") values(" + values +
					")" +
					";");
		}
	}

	/**
	 * Remove a record from the database
	 * @param whatField The column you want to reference (Should be the Key)
	 * @param whatValue The value in the column to specify the row to delete
	 */
	public void delete(String whatField, String whatValue) {
		helper.execute("DELETE from " + TABLE_NAME + " where " + whatField + " = " + whatValue +
				";");
	}

	/**
	 * Update a record in the database
	 * @param whatField The column you want to reference (Should be the Key)
	 * @param whatValue The value in the column to specify the row to update
	 * @param whereField What column needs to be updated
	 * @param whereValue What is the new value to be updated
	 */
	public void update(String whatField, String whatValue, String whereField, String whereValue) {
		helper.execute("UPDATE " + TABLE_NAME + " set " + whatField + " = \"" + whatValue +
				"\"" +
				" " +
				"where" +
				" " + whereField + " = \"" + whereValue + "\";");
	}

	/**
	 * Update the caught status in the database
	 * @param currentCaughtStatus Boolean The current caught status
	 * @param whereValue What row will be updated (Pokédex number)
	 */
	public void updateCaughtStatus(Boolean currentCaughtStatus, String whereValue) {
		if (currentCaughtStatus){
			helper.execute("UPDATE " + TABLE_NAME + " set has_been_caught = \"0\" " +
					"where pokedex_number = \"" + whereValue + "\";");
		} else {
			helper.execute("UPDATE " + TABLE_NAME + " set has_been_caught = \"1\" " +
					"where pokedex_number = \"" + whereValue + "\";");
		}
	}

	/**
	 * Updates all columns in the database
	 * @param newPokedexNumber String NEW Pokédex Number
	 * @param newPokemonName String NEW Pokémon Name
	 * @param newEvoLevel Integer NEW Pokémon next evolution number
	 * @param newWeight Pokémon NEW Weight
	 * @param newHeight Pokémon NEW Height
	 * @param newCaughtStatus Integer NEW Pokémon Caught status
	 * @param newEntry String NEW Pokédex entry
	 * @param newPrimaryType Integer NEW Pokémon Primary Type
	 * @param newSecondaryType Integer NEW Pokémon Secondary Type
	 * @param currentPokedexNumber String OLD Pokédex Number
	 */
	public void updateAll(String newPokedexNumber, String newPokemonName, String newEvoLevel,
						  String newWeight, String newHeight, String newCaughtStatus,
						  String newEntry, String newPrimaryType,
						  String newSecondaryType, String currentPokedexNumber) {

		//NAME
		helper.execute("UPDATE " + TABLE_NAME + " set pokemon_name = \"" + newPokemonName +
				"\" where pokedex_number = \"" + currentPokedexNumber + "\";");

		// POKEDEX NUMBER
		helper.execute("UPDATE " + TABLE_NAME + " set next_evolution_level = \"" + newEvoLevel +
				"\" where pokedex_number = \"" + currentPokedexNumber + "\";");

		// WEIGHT
		helper.execute("UPDATE " + TABLE_NAME + " set weight = \"" + newWeight +
				"\" where pokedex_number = \"" + currentPokedexNumber + "\";");

		//HEIGHT
		helper.execute("UPDATE " + TABLE_NAME + " set height = \"" + newHeight +
				"\" where pokedex_number = \"" + currentPokedexNumber + "\";");

		//CAUGHT STATUS
		helper.execute("UPDATE " + TABLE_NAME + " set has_been_caught = \"" + newCaughtStatus +
				"\" where pokedex_number = \"" + currentPokedexNumber + "\";");

		//POKEDEX ENTRY
		helper.execute("UPDATE " + TABLE_NAME + " set pokedex_entry = \"" + newEntry +
				"\" where pokedex_number = \"" + currentPokedexNumber + "\";");

		//PRIMARY TYPE
		helper.execute("UPDATE " + TABLE_NAME + " set primary_type = \"" + newPrimaryType +
				"\" where pokedex_number = \"" + currentPokedexNumber + "\";");

		//SECONDARY TYPE
		helper.execute("UPDATE " + TABLE_NAME + " set secondary_type = \"" + newSecondaryType +
				"\" where pokedex_number = \"" + currentPokedexNumber + "\";");

		//POKEDEX NUMBER
		helper.execute("UPDATE " + TABLE_NAME + " set pokedex_number = \"" + newPokedexNumber +
				"\" where pokedex_number = \"" + currentPokedexNumber + "\";");
	}

	/**
	 * Completes a SQL "select" command
	 * @param fields The fields to be displayed in the output
	 * @param whatField The field to search within
	 * @param whatValue The value to search for within whatField
	 * @param sortField The field that the data will be sorted by
	 * @param sort Use ASC or DESC to specify the sorting order
	 * @return {ArrayList<ArrayList<Object>>} - this means it returns a 2D Array of object, can be any type.
	 */
	public ArrayList<ArrayList<Object>> select(String fields, String whatField, String whatValue, String sortField, String sort) {
		return helper.executeQuery(prepareSQL(fields, whatField, whatValue, sortField, sort));
	}

	/**
	 * Performs a search of the database, where String "query" is the command that would be
	 * entered on the command line.
	 * Used to run all other queries that do not fit into the select statement.
	 * Used when you expect a return to the screen
	 * @param query The SQL command that would be entered at the command line for a SQL query
	 * @return {ArrayList<ArrayList<Object>>} - this means it returns a 2D Array of object, can be any type.
	 */
	public ArrayList<ArrayList<Object>> getExecuteResult(String query) {
		return helper.executeQuery(query);
	}

	/**
	 * Performs a search of the database, where String "query" is the command that would be
	 * entered on the command line.
	 * Does the same thing as the getExecuteResult method but does not return anything.
	 * Used when you are not expecting anything to be returned to the screen
	 * @param query The SQL command that would be entered at the command line for a SQL query
	 */
	public void execute(String query) {
		helper.execute(query);
	}

	/**
	 * Performs a search of the database, where String query is the SQL command that would be
	 * entered on the command line.
	 * @param fields the fields to be displayed in the output
	 * @param whatField the field to search within
	 * @param whatValue value to search for within whatField
	 * @param sortField the field that the data will be sorted by
	 * @param sort use ASC or DESC to specify the sorting order
	 * @return Uses a vector of vectors (A table) to store data
	 */
	public DefaultTableModel selectToTable(String fields, String whatField, String whatValue, String sortField, String sort) {
		return helper.executeQueryToTable(prepareSQL(fields, whatField, whatValue, sortField,
				sort));
	}

}