package org.inventivetalent.minetile;

import com.google.gson.annotations.Expose;

import java.util.UUID;

public class TeleportRequest {

	@Expose public UUID player;
	@Expose public UUID currentServer;
	@Expose public double  x;
	@Expose public double  y;
	@Expose public double  z;

	public TeleportRequest() {
	}

	public TeleportRequest(UUID player, UUID currentServer, double x, double y, double z) {
		this.player = player;
		this.currentServer = currentServer;
		this.x = x;
		this.y = y;
		this.z = z;
	}

}
