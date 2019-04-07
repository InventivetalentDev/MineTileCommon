package org.inventivetalent.minetile;

import com.google.gson.annotations.Expose;

public class GlobalCommand {

	@Expose public String command;

	public GlobalCommand() {
	}

	public GlobalCommand(String command) {
		this.command = command;
	}
}
