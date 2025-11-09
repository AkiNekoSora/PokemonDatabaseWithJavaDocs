// Autumn Skye
// CEN-3024C 13950
// November 8th, 2025
package org.pokemondatabase.DBHelper;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

/**
 * <header>PURPOSE/PRIMARY FUNCTION</header>
 * <p>
 * Used for the Pokémon table to accept SQL queries (with execute and getExecuteResults) and
 * prepare the SQL queries using Select, insert, update, delete, and selectToTable. Holds all
 * current column types as variables.
 * </p>
 * <header>IMPORTANT DEPENDENCIES</header>
 * <ul>
 *     <li>
 *         DBHelper: DBHelper calls this class to bring it into scope when other classes call the
 *         DBHelper.
 *     </li>
 * </ul>
 * <h2>Created using the SQLHelper GUI</h2>
 */
public class Types_DBHelper {
	private final String TABLE_NAME = "Types";

	/**
	 * Defines constant values for the Type database columns.
	 * <p>
	 * These constants are used throughout the application to reference database fields.
	 * </p>
	 * <ul>
	 *     <li>{@code type_id} – Type’s unique Type number.</li>
	 *     <li>{@code type_name} – Type’s name.</li>
	 * </ul>
	 */
	public static final String type_id = "type_id";
	public static final String type_name = "type_name";

	private final DBHelper helper;

	/**
	 * Class Constructor
	 * Used to bring DBHelper into the scope
	 * @param dbHelper Brings the Database Helper into scope to be able to access the DB.
	 */
	public Types_DBHelper(DBHelper dbHelper) {
		this.helper = dbHelper;
	}

	/**
	 * Prepares the text of a SQL "select" command.
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
	 * @param type_id Type ID (Primary Key)
	 * @param type_name Type Name
	 */
	public void insert(Integer type_id, String type_name) {
		type_name = type_name != null ? "\"" + type_name + "\"" : null;
		
		Object[] values_ar = {type_id, type_name};
		String[] fields_ar = {Types_DBHelper.type_id, Types_DBHelper.type_name};
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
			helper.execute("INSERT INTO " + TABLE_NAME + "(" + fields + ") values(" + values + ")" +
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
		helper.execute("UPDATE " + TABLE_NAME + " set " + whatField + " = \"" + whatValue + "\" " +
				"where" +
				" " + whereField + " = \"" + whereValue + "\";");
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

	/**
	 * Returns the id of the type when the name of the type is given
	 * @param typeName String Type Name
	 * @return Integer (Type ID)
	 */
	public Integer getTypeIdByName(String typeName) {
		if (typeName == null || typeName.trim().isEmpty()) {return 0;}

		ArrayList<ArrayList<Object>> result = select("type_id", "type_name", typeName, null, null);

		// Checks to verify the result is not empty before returning
		if (result != null && !result.isEmpty() && !result.get(0).isEmpty()) {
			Object value = result.get(0).get(0);
			if (value instanceof Number) {
				return ((Number) value).intValue();
			} else {
				try {
					return Integer.parseInt(value.toString());
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
			}
		}

		return 0;
	}

	/**
	 * Returns the type name when the ID of the type is given
	 * @param typeID Int with the Type ID
	 * @return String (Type Name)
	 */
	public String getTypeNameByID(Integer typeID) {
		if (typeID == null) {return null;}

		ArrayList<ArrayList<Object>> result = select("type_name", "type_id",
				String.valueOf(typeID), null, null);

		// Checks to verify the result is not empty before returning
		if (result != null && !result.isEmpty()) {
			ArrayList<Object> row = result.get(0);
			if (row != null && !row.isEmpty() && row.get(0) != null) {
				return row.get(0).toString();
			}
		}

		return null;
	}

}