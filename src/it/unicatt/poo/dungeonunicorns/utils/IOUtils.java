package it.unicatt.poo.dungeonunicorns.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import it.unicatt.poo.dungeonunicorns.exceptions.AttributeNotSpecifiedException;

/**
 * A class providing a number of useful functionalities to be used across the
 * whole application.
 * 
 * @author claudiometelli
 *
 */
public class IOUtils {

	/**
	 * Reads a file with a list of attributes and return the attribute specified
	 * 
	 * @param file the file to be read
	 * @param attribute the attribute to find
	 * @return the value of the attribute
	 */
	public static String getAttributeFromConfigFile(Path file, String attribute) throws AttributeNotSpecifiedException {
		String result = null;
		List<String> lines = new ArrayList<String>();
		try {
			lines = Files.readAllLines(file);
		} catch (IOException ioException) {
			System.err.println("IOException: " + ioException.getMessage());
		}
		if (!lines.isEmpty()) {
			for (String line : lines) {
				if (line.substring(1, line.indexOf(":")).equalsIgnoreCase(attribute)) {
					result = line.substring(line.indexOf(":") + 1).trim();
				}
			}
		}
		if(result == null) {
			throw new AttributeNotSpecifiedException(attribute, file.getFileName().toString());
		}
		return result;
	}

	/**
	 * Reads a file with a list of attributes and return the attribute specified as integer
	 * 
	 * @param file the file to be read
	 * @param attribute the attribute to find
	 * @return the value of the attribute
	 */
	public static Integer getIntegerAttributeFromConfigFile(Path file, String attribute) throws AttributeNotSpecifiedException {
		Integer result = null;
		String string = getAttributeFromConfigFile(file, attribute);
		if (string != null && isInteger(string)) {
			result = Integer.valueOf(string);
		}
		if(result == null) {
			throw new AttributeNotSpecifiedException(attribute, file.getFileName().toString());
		}
		return result;
	}

	/**
	 * Check if a string can be converted into an integer
	 * @param str the string to be checked
	 * @return true if integer, false otherwise
	 */
	private static boolean isInteger(String str) {
		boolean result = false;
		try {
			Integer.parseInt(str);
			result = true;
		} catch (NumberFormatException e) {
			result = false;
		}
		return result;
	}
	
	/**
	 * Reads a file with a list of attributes and return the attribute specified as float
	 * 
	 * @param file the file to be read
	 * @param attribute the attribute to find
	 * @return the value of the attribute
	 */
	public static Float getFloatAttributeFromConfigFile(Path file, String attribute) throws AttributeNotSpecifiedException {
		Float result = null;
		String string = getAttributeFromConfigFile(file, attribute);
		if (string != null && isFloat(string)) {
			result = Float.valueOf(string);
		}
		if(result == null) {
			throw new AttributeNotSpecifiedException(attribute, file.getFileName().toString());
		}
		return result;
	}
	
	/**
	 * Check if a string can be converted into an integer
	 * @param str the string to be checked
	 * @return true if integer, false otherwise
	 */
	private static boolean isFloat(String str) {
		boolean result = false;
		try {
			Float.parseFloat(str);
			result = true;
		} catch (NumberFormatException e) {
			result = false;
		}
		return result;
	}
	
}
