package com.epam.lab.app.view;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.lab.app.demo.MainController;
import com.epam.lab.app.enums.MenuCommand;

public class UserMenu {
	private static final Logger logger = LogManager.getLogger(UserMenu.class);
	private Scanner scanner;
	private MainController mainController;

	public UserMenu() {
		scanner = new Scanner(System.in);
		mainController = new MainController();
	}

	public void createUI() {
		boolean end = false;
		while (!end) {
			printMenu(Arrays.asList(MenuCommand.values()));
			MenuCommand command = MenuCommand.get(scanner.nextInt());
			switch (command) {
			case PARSE_SAX:
				mainController.parseSax();
				break;
			case PARSE_DOM:
				mainController.parseDom();
				break;
			case PARSE_STAX:
				mainController.parseStax();
				break;
			case VALIDATE:
				mainController.validate();
				break;
			case CONVERT_HTML:
				mainController.convertToHtml();
				break;
			case CHANGE_ROOT:
				mainController.changeRoot();
				break;
			case MARSHAL:
				mainController.marshall();
				break;
			case UNMARSHAL:
				mainController.unMarshall();
				break;
			case EXIT:
				end = true;
				break;
			case DEFAULT:
				logger.info("Wrong input");
				break;
			}
		}
	}

	private void printMenu(List<MenuCommand> commands) {
		logger.info("Print:");
		commands.stream().filter(command -> Objects.nonNull(command.getDescription()))
				.forEach(command -> logger.info(command.getNumber() + " - " + command.getDescription()));
	}
}
