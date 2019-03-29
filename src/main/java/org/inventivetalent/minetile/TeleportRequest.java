package org.inventivetalent.minetile;

import com.google.gson.annotations.Expose;

import java.util.UUID;

public class TeleportRequest {

	@Expose public UUID player;
	@Expose public UUID currentServer;
	@Expose public int  x;
	@Expose public int  y;
	@Expose public int  z;

	public TeleportRequest() {
	}

	public TeleportRequest(UUID player, UUID currentServer, int x, int y, int z) {
		this.player = player;
		this.currentServer = currentServer;
		this.x = x;
		this.y = y;
		this.z = z;
	}

}
