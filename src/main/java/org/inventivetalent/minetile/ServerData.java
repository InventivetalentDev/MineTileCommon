package org.inventivetalent.minetile;

import com.google.gson.annotations.Expose;

import java.util.UUID;

public class ServerData {

	@Expose public UUID serverId;
	@Expose public String host;
	@Expose public int port;

	public ServerData() {
	}

	public ServerData(UUID serverId, String host, int port) {
		this.serverId = serverId;
		this.host=host;
		this.port=port;
	}

	@Override
	public String toString() {
		return "ServerData{" +
				"serverId=" + serverId +
				", host='" + host + '\'' +
				", port=" + port +
				'}';
	}
}
