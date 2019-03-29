package org.inventivetalent.minetile;

import com.google.gson.annotations.Expose;

public class ControlRequest {

	@Expose public ControlAction action;

	public ControlRequest() {
	}

	public ControlRequest(ControlAction action) {
		this.action = action;
	}
}
