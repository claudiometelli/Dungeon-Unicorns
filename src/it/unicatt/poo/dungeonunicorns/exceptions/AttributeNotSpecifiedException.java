package it.unicatt.poo.dungeonunicorns.exceptions;

public class AttributeNotSpecifiedException extends Exception {
	
	private String attribute;
	private String fileName;

	public AttributeNotSpecifiedException(String attribute, String fileName) {
		this.attribute = attribute;
		this.fileName = fileName;
	}
	
	@Override
	public String getMessage() {
		return "The attribute " + attribute + " is not specified in " + fileName + ".";
	}
}
