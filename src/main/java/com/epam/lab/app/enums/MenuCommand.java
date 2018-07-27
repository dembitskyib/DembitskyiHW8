package com.epam.lab.app.enums;

public enum MenuCommand {
	PARSE_SAX(1, "Use SAX parser"), PARSE_DOM(2, "Use DOM parser"), PARSE_STAX(3, "Use StAX parser"), VALIDATE(4,
			"Validate XML"), CONVERT_HTML(5, "Convert XML to HTML"), CHANGE_ROOT(6, "Change XML root"), MARSHAL(7,
					"Marshal JAXB"), UNMARSHAL(8, "Unmarshal JAXB"), EXIT(9, "Exit"), DEFAULT();
	private int number;
	private String description;

	private MenuCommand() {

	}

	private MenuCommand(int number, String description) {
		this.number = number;
		this.description = description;
	}

	public static MenuCommand get(int number) {
		MenuCommand currentCommand = DEFAULT;
		for (MenuCommand command : MenuCommand.values()) {
			if (command.number == number) {
				currentCommand = command;
			}
		}
		return currentCommand;
	}

	public int getNumber() {
		return number;
	}

	public String getDescription() {
		return description;
	}
}
