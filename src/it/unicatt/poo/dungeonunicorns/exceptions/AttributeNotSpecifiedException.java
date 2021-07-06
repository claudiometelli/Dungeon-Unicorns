package it.unicatt.poo.dungeonunicorns.exceptions;

/**
 * An exception called when the attribute to be find in IOUtils is not found
 * 
 * @author claudiometelli
 * @version 1.0.0
 *
 */
public class AttributeNotSpecifiedException extends Exception {
	
	/**
	 * The attribute to be find in the file
	 */
	private String attribute;
	/**
	 * The name of the file
	 */
	private String fileName;

	/**
	 * Constructor for AttributeNotSpecifiedException
	 * 
	 * @param attribute - the attribute to be find in the file
	 * @param fileName - the name of the file
	 */
	public AttributeNotSpecifiedException(String attribute, String fileName) {
		this.attribute = attribute;
		this.fileName = fileName;
	}
	
	@Override
	public String getMessage() {
		return "The attribute " + attribute + " is not specified in " + fileName + ".";
	}
}
